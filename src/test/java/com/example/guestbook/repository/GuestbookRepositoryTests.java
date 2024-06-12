 package com.example.guestbook.repository;

import java.util.List;
import java.util.stream.IntStream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.guestbook.entity.Guestbook;
import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
public class GuestbookRepositoryTests {
    @Autowired 
    private GuestbookRepository guestbookRepository;
    @Test
    public void insertDummies() {
        IntStream.rangeClosed(1, 301).forEach( i -> {
            Guestbook guestbook = Guestbook.builder()
                .title("Title..." + i)
                .content("Content..." + i)
                .writer("user"+ (i % 10))
                .build();
            guestbookRepository.save(guestbook);
        });
        List<Guestbook> guestbooks = guestbookRepository.findAll();
        Assertions.assertThat(guestbooks.size()).isEqualTo(301);
    }
}