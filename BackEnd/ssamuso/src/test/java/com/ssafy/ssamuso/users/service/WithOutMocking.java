package com.ssafy.ssamuso.users.service;


import com.ssafy.ssamuso.users.dto.UserMyPage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WithOutMocking {

    @Autowired
    UserMyPageService userMyPageService;

    @Test
    void 데이터베이스_연결테스트() throws Exception {
        //When
        UserMyPage info = userMyPageService.findMyPageInfo("kim");

        //Then
        System.out.println("info = " + info);
    }
}
