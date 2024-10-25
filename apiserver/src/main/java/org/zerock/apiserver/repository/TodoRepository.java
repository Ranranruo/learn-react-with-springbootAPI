package org.zerock.apiserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zerock.apiserver.domain.Todo;
import org.zerock.apiserver.dto.PageRequestDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    @Query(value = "SELECT * FROM todo ORDER BY due_date DESC, tno DESC LIMIT :#{#pageRequestDTO.size} OFFSET ((:#{#pageRequestDTO.page} - 1) * :#{#pageRequestDTO.size})", nativeQuery = true)
    Optional<List<Todo>> search1(@Param("pageRequestDTO") PageRequestDTO pageRequestDTO);

    @Query(value = "SELECT COUNT(*) FROM todo", nativeQuery = true)
    int getTotalPageCount();
}
