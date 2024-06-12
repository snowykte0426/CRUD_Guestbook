package com.example.guestbook.service;

import com.example.guestbook.dto.GuestbookDTO;
import com.example.guestbook.dto.PageRequestDTO;
import com.example.guestbook.dto.PageResponseDTO;
import com.example.guestbook.entity.Guestbook;

public interface GuestbookService {
    
    Long register(GuestbookDTO guestbookDTO);

    PageResponseDTO<GuestbookDTO, Guestbook> list(PageRequestDTO pageRequestDTO);

    GuestbookDTO read(Long gno);

    void update(GuestbookDTO guestbookDTO);

    void remove(Long gno);

    default Guestbook dtoToEntity(GuestbookDTO guestbookDTO) {
        
        Guestbook guestbook = Guestbook.builder()
            .gno(guestbookDTO.getGno())
            .title(guestbookDTO.getTitle())
            .content(guestbookDTO.getContent())
            .writer(guestbookDTO.getWriter())
            .build();
        
        return guestbook;
    }

    default GuestbookDTO entityToDto(Guestbook guestbook) {

        GuestbookDTO guestbookDTO = GuestbookDTO.builder()
            .gno(guestbook.getGno())
            .title(guestbook.getTitle())
            .content(guestbook.getContent())
            .writer(guestbook.getWriter())
            .regDate(guestbook.getRegDate())
            .modDate(guestbook.getModDate())
            .build();
        
        return guestbookDTO;
    }
}
