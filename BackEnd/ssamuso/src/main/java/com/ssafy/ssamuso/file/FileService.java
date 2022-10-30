package com.ssafy.ssamuso.file;

import com.ssafy.ssamuso.domain.entity.File;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

public interface FileService {

    void save(File file);

    String fileUpload(long brdId, MultipartFile multipartFile);

    String findByChangedName(String fileName);

    ArrayList<File> findByBoardId(long brdId);

}
