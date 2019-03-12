package com.gwangmin.tdl.repository;

import com.gwangmin.tdl.domain.ToDoList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoListRepository extends JpaRepository<ToDoList, Integer> {

}
