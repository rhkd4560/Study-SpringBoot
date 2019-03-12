package com.gwangmin.tdl.controller;

import com.gwangmin.tdl.service.TodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/tdl")
public class ToDoListController {
    @Autowired
    TodoListService todoListService;

//    @GetMapping({"", "/"})
//    public String board(@RequestParam(value = "idx", defaultValue = "0") Integer idx, Model model) {
//        model.addAttribute("board", todoListService.findBoardByIdx(idx));
//        return "/tdl/form";
//    }
    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("todoList", todoListService.findList());
        return "/tdl/list";
    }


}
