package com.ssafy.ssamuso.file;

import com.ssafy.ssamuso.domain.entity.File;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

public interface FileService {


    String fileUpload(Long brdId, MultipartFile multipartFile) throws Exception;

    ArrayList<File> findByBoardId(long brdId);

    String delete(Long brdId) throws Exception;

    ArrayList<String> findUrlByBoardId(Long brdId);

}
