package com.ssafy.ssamuso.file;

import com.ssafy.ssamuso.domain.entity.File;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;

    @Override
    public void save(File file) {
        fileRepository.save(file);
    }

    @Override
    public String fileUpload(long brdId, MultipartFile multipartFile) {
        return null;
    }

    @Override
    public String findByChangedName(String fileName) {
        return null;
    }

    @Override
    public ArrayList<File> findByBoardId(long brdId) {
        return null;
    }


}
