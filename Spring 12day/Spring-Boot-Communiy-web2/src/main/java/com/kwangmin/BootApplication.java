package com.kwangmin;

import com.kwangmin.domain.Board;
import com.kwangmin.domain.User;
import com.kwangmin.domain.enums.BoardType;
import com.kwangmin.repository.BoardRepository;
import com.kwangmin.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

@SpringBootApplication
public class BootApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(UserRepository userRepository,
                                    BoardRepository boardRepository) throws Exception{
        return (args) -> {
            User user = userRepository.save(User.builder()
                .name("havi")
                .password("test")
                .email("havi@gmail.com")
                .createdDate(LocalDateTime.now())
                .build());

            IntStream.rangeClosed(1,200).forEach(index ->
                    boardRepository.save(Board.builder()
                        .title("게시글"+index)
                        .subTitle("순서"+index)
                        .content("콘텐츠")
                        .boardType(BoardType.free)
                        .createdDate(LocalDateTime.now())
                        .updatedDate(LocalDateTime.now())
                        .user(user).build())
            );
        };
    }

}

