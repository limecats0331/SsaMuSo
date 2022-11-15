package com.ssafy.ssamuso.repository;


import com.ssafy.ssamuso.domain.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface BoardRepository extends CrudRepository<Board,Long> {
    Page<Board> findAll (Pageable pageable);


}
