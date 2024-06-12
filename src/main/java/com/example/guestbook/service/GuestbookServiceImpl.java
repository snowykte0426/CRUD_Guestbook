package com.example.guestbook.service;

import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.guestbook.dto.GuestbookDTO;
import com.example.guestbook.dto.PageRequestDTO;
import com.example.guestbook.dto.PageResponseDTO;
import com.example.guestbook.entity.Guestbook;
import com.example.guestbook.repository.GuestbookRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class GuestbookServiceImpl implements GuestbookService {

    private final GuestbookRepository guestbookRepository;

    @Override
    public Long register(GuestbookDTO guestbookDTO) {
        
        log.info("register -> " + guestbookDTO);

        Guestbook guestbook = dtoToEntity(guestbookDTO);

        Guestbook result = guestbookRepository.save(guestbook);

        log.info("registered -> " + result);

        return result.getGno();

    }

    @Override
    public PageResponseDTO<GuestbookDTO, Guestbook> list(PageRequestDTO pageRequestDTO) {
        
        log.info("get guestbook list");

        Sort sort = Sort.by("gno").descending();

        Pageable pageable = pageRequestDTO.getPageable(sort);
        
        Page<Guestbook> findAllResult = guestbookRepository.findAll(pageable);

        Function<Guestbook, GuestbookDTO> fn = guestbook -> entityToDto(guestbook);

        return new PageResponseDTO<>(findAllResult, fn);

    }

    @Override
    public GuestbookDTO read(Long gno) {
        
        log.info("get guestbook (gno : " + gno + ")");

        Optional<Guestbook> findByIdResult = guestbookRepository.findById(gno);

        return findByIdResult.isPresent() ? entityToDto(findByIdResult.get()) : null;
    }

    @Override
    public void update(GuestbookDTO guestbookDTO) {
        
        log.info("update guestbook (gno : " + guestbookDTO.getGno() + ")");

        guestbookRepository.findById(guestbookDTO.getGno())
            .ifPresent( guestbook -> {
                guestbook.updateContent(guestbookDTO.getContent());
                guestbook.updateTitle(guestbookDTO.getTitle());
            }
        );

    }

    @Override
    public void remove(Long gno) {

        log.info("remove guestbook (gno : " + gno + ")");

        guestbookRepository.deleteById(gno);
    }

}