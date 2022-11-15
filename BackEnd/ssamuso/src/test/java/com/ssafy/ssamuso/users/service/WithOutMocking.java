package com.ssafy.ssamuso.users.service;


import com.ssafy.ssamuso.service.UserMyPageService;
import com.ssafy.ssamuso.domain.dto.UserMyPageDTO;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@Disabled
@SpringBootTest
public class WithOutMocking {

    @Autowired
    UserMyPageService userMyPageService;

    @Test
    void 데이터베이스_연결테스트() throws Exception {
        //When
        UserMyPageDTO info = userMyPageService.findMyPageInfo("kim");

        //Then
        System.out.println("info = " + info);
    }
}
