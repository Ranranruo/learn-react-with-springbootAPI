package org.zerock.apiserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.apiserver.domain.Todo;
import org.zerock.apiserver.dto.PageRequestDTO;
import org.zerock.apiserver.dto.PageResponseDTO;
import org.zerock.apiserver.dto.TodoDTO;
import org.zerock.apiserver.service.TodoService;
import org.zerock.apiserver.service.TodoServiceImpl;

import java.util.List;
import java.util.OptionalInt;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todo")
public class TodoController {
    private final TodoServiceImpl todoService;

    @GetMapping("/{tno}")
    public TodoDTO get(@PathVariable("tno") Long tno){
        return todoService.get(tno);
    }

    @GetMapping("/list")
    public PageResponseDTO<TodoDTO> list(PageRequestDTO pageRequestDTO) {
        return todoService.getList(pageRequestDTO);
    }
}
