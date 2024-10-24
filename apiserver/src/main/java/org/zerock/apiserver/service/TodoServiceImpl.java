package org.zerock.apiserver.service;

import lombok.RequiredArgsConstructor;
import org.hibernate.query.Page;
import org.springframework.stereotype.Service;
import org.zerock.apiserver.domain.Todo;
import org.zerock.apiserver.dto.PageRequestDTO;
import org.zerock.apiserver.dto.PageResponseDTO;
import org.zerock.apiserver.dto.TodoDTO;
import org.zerock.apiserver.repository.TodoRepository;

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
        System.out.println("getlist");
        System.out.println(pageRequestDTO);
        System.out.println(result.isPresent());
        int totalCount = todoRepository.getTotalPageCount();
        if(result.isPresent()){
            List<Todo> todoList = result.get();
            List<TodoDTO> todoDTOList = new ArrayList<TodoDTO>();
            for(int i = 0; i < todoList.size(); i++){
                todoDTOList.add(entityToDTO(todoList.get(i)));
            }
            PageResponseDTO<TodoDTO> pageResponseDTO = new PageResponseDTO<TodoDTO>(todoDTOList, pageRequestDTO, totalCount);
            System.out.println(pageResponseDTO);
            return pageResponseDTO;
        }
        return null;
    }
}
