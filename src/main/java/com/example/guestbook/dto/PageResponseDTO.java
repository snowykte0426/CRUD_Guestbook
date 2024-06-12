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
    private int page;
    private int start;
    private int end;
    private boolean prev;
    private boolean next;
    private List<Integer> pageList;
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