package com.ssafy.ssamuso.file;

import com.ssafy.ssamuso.domain.entity.Board;
import com.ssafy.ssamuso.domain.entity.File;
import com.ssafy.ssamuso.domain.entity.User;
import com.ssafy.ssamuso.repository.FileRepository;
import com.ssafy.ssamuso.service.FileService;
import com.ssafy.ssamuso.service.FileServiceImpl;
import com.ssafy.ssamuso.service.S3Service;
import com.ssafy.ssamuso.util.EntityMaker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class FileServiceTest {

    @Mock
    FileRepository fileRepository;
    @Mock
    S3Service s3Service;

    private FileService fileService = new FileServiceImpl(fileRepository, s3Service);

//    @Test
    void fileUploadTest() throws Exception {
//        MultipartFile multipartFile = new MultipartFile


        Mockito.doReturn(EntityMaker.makeFile(1L)).when(fileRepository).save(any(File.class));
        Mockito.doReturn("testUrl").when(s3Service).upload(any(MultipartFile.class));

    }



}
