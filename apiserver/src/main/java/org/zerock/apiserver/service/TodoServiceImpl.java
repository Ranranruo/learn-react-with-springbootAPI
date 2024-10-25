package org.zerock.apiserver.service;

import lombok.RequiredArgsConstructor;
import org.hibernate.query.Page;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.zerock.apiserver.domain.Todo;
import org.zerock.apiserver.dto.PageRequestDTO;
import org.zerock.apiserver.dto.PageResponseDTO;
import org.zerock.apiserver.dto.TodoDTO;
import org.zerock.apiserver.repository.TodoRepository;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService{

    private final TodoRepository todoRepository;

    @Override
    public TodoDTO get(Long tno) {
        Optional<Todo> result = todoRepository.findById(tno);
        Todo todo = result.orElseThrow();
        return entityToDTO(todo);
    }

    @Override
    public Long register(TodoDTO dto) {
        Todo todo = dtoToEntity(dto);
        Todo result = todoRepository.save(todo);
        return result.getTno();
    }

    @Override
    public void modify(TodoDTO dto) {
        Optional<Todo> result = todoRepository.findById(dto.getTno());
        Todo todo = result.orElseThrow();
        todo.setTitle(dto.getTitle());
        todo.setContent(dto.getContent());
        todo.setComplete(dto.isComplete());
        todo.setDueDate(dto.getDueDate());

        todoRepository.save(todo);
    }

    @Override
    public void remove(Long tno) {
        todoRepository.deleteById(tno);
    }

    @Override
    public PageResponseDTO<TodoDTO> getList(PageRequestDTO pageRequestDTO){
        Optional<List<Todo>> result = todoRepository.search1(pageRequestDTO);

        int totalCount = todoRepository.getTotalPageCount();
            List<Todo> todoList = result.get();
        if (todoList.size() > 0) {
            List<TodoDTO> todoDTOList = new ArrayList<>();

            for (Todo todo : todoList) {
                todoDTOList.add(entityToDTO(todo));
            }

            PageResponseDTO<TodoDTO> pageResponseDTO = new PageResponseDTO<>(todoDTOList, pageRequestDTO, totalCount);
            return pageResponseDTO;
        }
        return null;
    }


}
