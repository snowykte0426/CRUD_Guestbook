package com.example.guestbook.service;

import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.guestbook.dto.GuestbookDTO;
import com.example.guestbook.dto.PageRequestDTO;
import com.example.guestbook.dto.PageResponseDTO;
import com.example.guestbook.entity.Guestbook;
import com.example.guestbook.repository.GuestbookRepository;
import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
public class GuestbookServiceTests {
    @Autowired
    private GuestbookService guestbookService;
    @Autowired
    private GuestbookRepository guestbookRepository;
    @Test
    public void registerTest() {
        String title = "Test title";
        String content = "Test content";
        String writer = "Test writer";
        GuestbookDTO guestbookDTO = GuestbookDTO.builder()
            .title(title)
            .content(content)
            .writer(writer)
            .build();
        Long registeredGno = guestbookService.register(guestbookDTO);
        Optional<Guestbook> resultOptional = guestbookRepository.findById(registeredGno);
        Guestbook guestbook = null;
        if(resultOptional.isPresent()) {
            guestbook = resultOptional.get();
        }
        Assertions.assertThat(guestbook.getTitle()).isEqualTo(title);
        Assertions.assertThat(guestbook.getContent()).isEqualTo(content);
        Assertions.assertThat(guestbook.getWriter()).isEqualTo(writer);
    }
    @Test
    public void listTest() {
        int page = 10;
        int size = 10;
        int startPage = 1;
        int endPage = startPage + size - 1;
        PageRequestDTO pageRequestDTO = new PageRequestDTO(page, size);
        PageResponseDTO<GuestbookDTO, Guestbook> pageResponseDTO = guestbookService.list(pageRequestDTO);
        System.out.println(pageResponseDTO);
        Assertions.assertThat(pageResponseDTO.getDtoList().stream().findAny().get()).isInstanceOf(GuestbookDTO.class);
        Assertions.assertThat(pageResponseDTO.getPage()).isEqualTo(page);
        Assertions.assertThat(pageResponseDTO.getStart()).isEqualTo(startPage);
        Assertions.assertThat(pageResponseDTO.getEnd()).isEqualTo(endPage);
        Assertions.assertThat(pageResponseDTO.isPrev()).isEqualTo(false);
        Assertions.assertThat(pageResponseDTO.isNext()).isEqualTo(true);
        Assertions.assertThat(pageResponseDTO.getPageList().size()).isEqualTo(size);
    }
    @Test
    public void readTest() {
        Long gno = 313L;
        GuestbookDTO guestbookDTO = guestbookService.read(gno);
        Assertions.assertThat(guestbookDTO.getGno()).isEqualTo(gno);
    }
    @Test
    public void updateTest() {
        Long gno = 313L;
        String title = "Updated";
        String content = "Updated";
        GuestbookDTO guestbookDTO = GuestbookDTO.builder()
            .gno(gno)
            .title(title)
            .content(content)
            .build();
        guestbookService.update(guestbookDTO);
        guestbookRepository.findById(gno).ifPresent((guestbook) -> {
            Assertions.assertThat(guestbook.getTitle()).isEqualTo(title);
            Assertions.assertThat(guestbook.getContent()).isEqualTo(content);
            Assertions.assertThat(guestbook.getModDate()).isAfter(guestbook.getRegDate());
        });
    }
    @Test
    public void removeTest() {
        Long gno = 313L;
        guestbookService.remove(gno);
        Assertions.assertThat(guestbookRepository.findById(gno).isEmpty()).isTrue();
    }
}