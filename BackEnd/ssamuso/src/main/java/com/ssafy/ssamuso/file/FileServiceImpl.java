package com.ssafy.ssamuso.file;

import com.ssafy.ssamuso.domain.entity.Board;
import com.ssafy.ssamuso.domain.entity.File;
import com.ssafy.ssamuso.s3.S3Service;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

@Service
@AllArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;
    private final S3Service s3Service;


    @Override
    public String fileUpload(Long brdId, MultipartFile multipartFile) throws Exception {
//        System.out.println(multipartFile.getOriginalFilename());
//        System.out.println(multipartFile.getContentType());
//        System.out.println(multipartFile.getName());
        String newFileName = "files/" + getDateToString() + "-" + UUID.randomUUID();
        String url = s3Service.upload(multipartFile, newFileName);
        File file = File.builder()
                .originalName(multipartFile.getOriginalFilename())
                .changedName(newFileName + multipartFile.getOriginalFilename())
                .url(url)
                .board(Board.builder().id(brdId).build())
                .build();
//        System.out.println(file);
        fileRepository.save(file);
        return url;
    }


    @Override
    public ArrayList<File> findByBoardId(long brdId) {
        return fileRepository.findByBoardId(brdId);
    }

    @Override
    public String delete(Long brdId) {
        return null;

    }

    @Override
    public ArrayList<String> findUrlByBoardId(Long brdId) {
        ArrayList<File> files = findByBoardId(brdId);
        ArrayList<String> result = new ArrayList<>();
        for (File file : files) {
            result.add(file.getUrl());
        }
        return result;
    }

    private String getDateToString() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        return format.format(now);
    }


}
