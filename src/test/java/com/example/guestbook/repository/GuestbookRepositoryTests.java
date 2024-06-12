 package com.example.guestbook.repository;

import java.util.List;
import java.util.stream.IntStream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import com.example.guestbook.entity.Guestbook;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
public class GuestbookRepositoryTests {
    
    @Autowired 
    private GuestbookRepository guestbookRepository;

    @Test
    // @Commit
    public void insertDummies() {
        // Given
        IntStream.rangeClosed(1, 301).forEach( i -> {
            Guestbook guestbook = Guestbook.builder()
                .title("Title..." + i)
                .content("Content..." + i)
                .writer("user"+ (i % 10))
                .build(); 
            
            guestbookRepository.save(guestbook);
        });

        // When
        List<Guestbook> guestbooks = guestbookRepository.findAll();

        // Then
        Assertions.assertThat(guestbooks.size()).isEqualTo(301);

    }
}