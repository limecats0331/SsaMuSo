package com.ssafy.ssamuso.file.service;


import com.ssafy.ssamuso.file.FileRepository;
import com.ssafy.ssamuso.file.FileService;
import com.ssafy.ssamuso.s3.S3Service;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class FileServiceTest {

    @Mock
    FileService fileService;

    @Mock
    FileRepository fileRepository;

    @Mock
    S3Service s3Service;

    @Test
    void createFile(){

        Mockito.doReturn(fileService).when(fileRepository);

    }
}
