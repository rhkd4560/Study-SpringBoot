package com.community;


import com.community.controller.BookController;
import com.community.domain.Book;
import com.community.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.time.LocalDateTime;

import static org.hamcrest.Matchers.contains;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
public class BookControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookService bookService;

    @Test
    public void Book_MVC_테스트() throws Exception{
        Book book = new Book("Spring Boot Book", LocalDateTime.now());
        given(bookService.getBookList()).willReturn(Collections.singletonList(book));



        mvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(view().name("book")) //뷰이름이 book인지?
                .andExpect(model().attributeExists("bookList")) //모델의 bookList가 존재하는지?
                .andExpect(model().attribute("bookList",contains(book))); //프로퍼티가 book객체를 포함하는지);
    }
}
