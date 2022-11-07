package com.ssafy.ssamuso.board;

import com.ssafy.ssamuso.domain.entity.BoardDelete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardDeleteRepository extends CrudRepository<BoardDelete,Long> {
}
