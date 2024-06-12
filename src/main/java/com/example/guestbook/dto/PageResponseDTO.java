package com.example.guestbook.dto;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import lombok.Data;

@Data
public class PageResponseDTO<DTO, EN> {
    
    private List<DTO> dtoList;
    private int page;                   // 현재 페이지
    private int start;                  // 시작 페이지
    private int end;                    // 끝 페이지
    private boolean prev;               // 이전 페이지 목록 여부
    private boolean next;               // 다음 페이지 목록 여부
    private List<Integer> pageList;     // 페이지 목록

    public PageResponseDTO(Page<EN> pageList, Function<EN, DTO> fn) {

        this.dtoList = pageList.stream().map(fn).collect(Collectors.toList());

        Pageable pageable = pageList.getPageable();
        int totalPages = pageList.getTotalPages();
        int pageSize = pageable.getPageSize();
        
        this.page = pageable.getPageNumber() + 1;
        
        int tempEnd = (int)(Math.ceil(this.page / (double)pageSize)) * pageSize;
        
        this.start = tempEnd - pageSize + 1;
        this.end = tempEnd < totalPages ? tempEnd : totalPages;
        this.prev = this.start != 1;
        this.next = this.end != totalPages;
        this.pageList = IntStream.rangeClosed(this.start, this.end).boxed().collect(Collectors.toList());
    }

}
