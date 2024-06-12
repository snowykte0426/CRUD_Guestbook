package com.example.guestbook.dto;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PageRequestDTO {
    
    private int page;       // 요청한 페이지 번호
    private int size;       // 한 페이지에 담기는 방명록 수

    public PageRequestDTO() {
        // Default values
        this.page = 1;
        this.size = 10;
    }

    public Pageable getPageable(Sort sort) {

        return PageRequest.of(page - 1, size, sort);
    }
}
