package com.ssafy.ssamuso.board.repository;


import com.mysema.commons.lang.Assert;
import com.ssafy.ssamuso.board.BoardDeleteRepository;
import com.ssafy.ssamuso.domain.entity.Board;
import com.ssafy.ssamuso.domain.entity.BoardDelete;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class BoardDeleteRepositoryTest {

    @Autowired
    BoardDeleteRepository boardDeleteRepository;

    @Test
    void save() throws Exception    {

        BoardDelete boardDelete = BoardDelete.builder()
                .content("testcontent")
                .title("testtitle")
                .appMax(0)
                .feMax(3)
                .beMax(2)
                .embMax(0)
                .username("testuser")
                .build();


        BoardDelete result =  boardDeleteRepository.save(boardDelete);
        Assertions.assertEquals(boardDelete,result);

    }
}
