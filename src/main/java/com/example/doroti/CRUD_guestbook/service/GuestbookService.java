package com.example.doroti.CRUD_guestbook.service;

import com.example.doroti.CRUD_guestbook.dto.GuestbookDTO;
import com.example.doroti.CRUD_guestbook.entity.Guestbook;

public interface GuestbookService {

    Long register(GuestbookDTO guestbookDTO);

    default Guestbook dtoToEntity(GuestbookDTO guestbookDTO) {
        return Guestbook.builder()
                .gno(guestbookDTO.getGno())
                .title(guestbookDTO.getTitle())
                .content(guestbookDTO.getContent())
                .writer(guestbookDTO.getWriter())
                .build();
    }
}
