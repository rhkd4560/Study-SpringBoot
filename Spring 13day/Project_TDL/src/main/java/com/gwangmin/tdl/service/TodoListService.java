package com.gwangmin.tdl.service;

import com.gwangmin.tdl.domain.ToDoList;
import com.gwangmin.tdl.repository.ToDoListRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoListService {
    private final ToDoListRepository toDoListRepository;

    public TodoListService(ToDoListRepository toDoListRepository){
        this.toDoListRepository = toDoListRepository;
    }

    public List<ToDoList> findList() {
        return toDoListRepository.findAll();
    }
}
