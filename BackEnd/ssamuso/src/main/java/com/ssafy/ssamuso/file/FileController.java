package com.ssafy.ssamuso.file;


import com.ssafy.ssamuso.domain.entity.Board;
import com.ssafy.ssamuso.domain.entity.File;
import com.ssafy.ssamuso.s3.S3Service;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@RestController
@AllArgsConstructor
public class FileController {

    private final FileService fileService;
    private final S3Service s3Service;

    @PostMapping("/upload")
    @Transactional
    public ResponseEntity<?> brdImgUpload(long brdId, @RequestParam("images") List<MultipartFile> multipartFiles) {
        System.out.println(brdId);
        Map<String, Object> result = new HashMap<String, Object>();
        String temp = null;
        try {
            ArrayList<String> img_ids = new ArrayList<>();
            for (MultipartFile multipartFile : multipartFiles) {

                fileService.fileUpload(brdId, multipartFile);
            }

            result.put("msg", "OK");
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result.put("msg", "ERROR");
            return new ResponseEntity<>(result, HttpStatus.OK);
        }

    }

    @GetMapping("/file/{brdid}")
    public ResponseEntity<?> findByBrdId(@PathVariable long brdId) {
        Map<String, Object> result = new HashMap<String, Object>();

        try {
            ArrayList<File> files = fileService.findByBoardId(brdId);

            result.put("files", files);
            if (files.size() != 0) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            result.put("msg", "ERROR");
            return new ResponseEntity<>(result, HttpStatus.NOT_IMPLEMENTED);
        }

    }
}
