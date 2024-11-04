package org.zerock.apiserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.zerock.apiserver.domain.Todo;
import org.zerock.apiserver.dto.PageRequestDTO;
import org.zerock.apiserver.dto.PageResponseDTO;
import org.zerock.apiserver.dto.TodoDTO;
import org.zerock.apiserver.service.TodoService;
import org.zerock.apiserver.service.TodoServiceImpl;

import java.util.List;
import java.util.Map;
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

    @PostMapping("/")
    public Map<String, Long> register(@RequestBody TodoDTO todoDTO){
        Long tno = todoService.register(todoDTO);
        System.out.println(tno);
        return Map.of("tno", tno);
    }

    @PutMapping("/{tno}")
    public Map<String, String> modify(@PathVariable("tno") Long tno,
                                      @RequestBody TodoDTO todoDTO) {
        todoDTO.setTno(tno);
        todoService.modify(todoDTO);
        return Map.of("result", "success");
    }

    @DeleteMapping("/{tno}")
    public Map<String, String> remove(@PathVariable Long tno){
        todoService.remove(tno);
        return Map.of("result", "success");
    }

}
