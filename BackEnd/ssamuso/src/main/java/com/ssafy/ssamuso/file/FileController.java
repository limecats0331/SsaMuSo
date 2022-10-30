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

        Map<String, Object> result = new HashMap<String, Object>();
        String temp = null;
        try {
            ArrayList<Integer> img_ids = new ArrayList<Integer>();
            for (MultipartFile multipartFile : multipartFiles) {
                String originalFileName = multipartFile.getOriginalFilename();
                String uuid = String.valueOf(UUID.randomUUID());
                File file = File.builder()
                        .originalName(originalFileName)
                        .changedName(uuid)
                        .board(Board.builder().id(brdId).build())
                        .build();
                fileService.save(file);
                temp = s3Service.upload(multipartFile, originalFileName, uuid);
            }

            result.put("msg", temp);
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
