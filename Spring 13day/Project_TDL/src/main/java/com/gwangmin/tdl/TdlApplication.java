package com.gwangmin.tdl;

import com.gwangmin.tdl.domain.ToDoList;
import com.gwangmin.tdl.repository.ToDoListRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

@SpringBootApplication
public class TdlApplication {

    public static void main(String[] args) {
        SpringApplication.run(TdlApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(ToDoListRepository toDoListRepository) throws Exception {
        return (args) -> {

            IntStream.rangeClosed(1, 20).forEach(index -> toDoListRepository.save(ToDoList.builder()
                    .description("설명" + index)
                    .status(true)
                    .completedDate(LocalDateTime.now())
                    .createdDate(LocalDateTime.now())
                    .build()
            ));
        };
    }

}
