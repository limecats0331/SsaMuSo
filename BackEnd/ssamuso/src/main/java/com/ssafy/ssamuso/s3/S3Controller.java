package com.ssafy.ssamuso.s3;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
public class S3Controller {

    private final S3Service s3Service;

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam("images") List<MultipartFile> multipartFiles) throws IOException {

        Map<String, Object> result = new HashMap<String, Object>();
        String temp=null;
        try {
            ArrayList<Integer> img_ids = new ArrayList<Integer>();
            for (MultipartFile multipartFile : multipartFiles) {
                temp = s3Service.upload(multipartFile, multipartFile.getOriginalFilename());
            }


            result.put("msg", temp);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result.put("msg", "ERROR");
            return new ResponseEntity<>(result, HttpStatus.OK);
        }

    }
}
