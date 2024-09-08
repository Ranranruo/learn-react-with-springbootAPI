package org.zerock.apiserver.repository;

import lombok.extern.log4j.Log4j2; // Log4j2 사용
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.apiserver.domain.Todo;

import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest
@Log4j2 // Log4j2 애노테이션
// 테스트 코드 작성
public class TodoRepositoryTests {
    @Autowired
    private TodoRepository todoRepository;

    // 레포지토리 생성 테스트
    @Test
    public void test1() {
        Assertions.assertNotNull(todoRepository);
        System.out.println(todoRepository.getClass().getName());
    }

    // todo insert 테스트
    @Test
    public void testInsert(){
        Todo todo = Todo.builder()
                .title("Title")
                .content("Content...")
                .dueDate(LocalDate.of(2023, 12, 30))
                .build();
        Todo result = todoRepository.save(todo);
        System.out.println(result.toString());
    }

    // todo get 테스트
    @Test
    public void testRead() {
        Long tno = 1L;

        Optional<Todo> result = todoRepository.findById(tno);

        Todo todo = result.orElseThrow();

        System.out.println(todo);
    }

    // todo update 테스트
    @Test
    public void testUpdate(){
        // 1. 기존 데이터 로딩
        Long tno = 1L;

        Optional<Todo> result = todoRepository.findById(tno);

        Todo todo = result.orElseThrow();
        // 2. 엔티티 객체 수정
        todo.setTitle("Update Title");
        todo.setContent("Update Content");
        todo.setComplete(true);

        Todo Updated = todoRepository.save(todo);

        System.out.println(Updated.toString());
    }
}
