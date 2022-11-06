package com.ssafy.ssamuso.s3;


import com.amazonaws.AmazonClientException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

@RestController
@AllArgsConstructor
public class S3Controller {

    private final S3Service s3Service;

    @PostMapping("/fileupload")
    public ResponseEntity<?> upload(@RequestParam("images") List<MultipartFile> multipartFiles) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        String temp = null;
        try {
            ArrayList<Integer> img_ids = new ArrayList<Integer>();
            for (MultipartFile multipartFile : multipartFiles) {
                temp = s3Service.upload(multipartFile, String.valueOf(UUID.randomUUID()));

            }
            result.put("msg", temp);
            return new ResponseEntity<>(result, HttpStatus.OK);

        } catch (Exception e) {
            result.put("msg", "ERROR");
            return new ResponseEntity<>(result, HttpStatus.OK);
        }

    }


    @DeleteMapping("/file")
    public ResponseEntity<?> deleteFile(@RequestParam String fileName) throws Exception {

        s3Service.delete(fileName);

        return new ResponseEntity<>("OK",HttpStatus.OK);

    }
}
