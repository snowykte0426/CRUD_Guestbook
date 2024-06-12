package com.example.doroti.CRUD_guestbook.service;

import com.example.doroti.CRUD_guestbook.dto.GuestbookDTO;
import com.example.doroti.CRUD_guestbook.entity.Guestbook;
import com.example.doroti.CRUD_guestbook.repository.GuestbookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@RequiredArgsConstructor
@Transactional
public class GuestbookServiceImpl implements GuestbookService {

    private final GuestbookRepository guestbookRepository;

    @Override
    public Long register(GuestbookDTO guestbookDTO) {
        log.info("register -> " + guestbookDTO);
        Guestbook guestbook = dtoToEntity(guestbookDTO);
        Guestbook result = guestbookRepository.save(guestbook);
        log.info("register -> " + result);
        return result.getGno();
    }
}
