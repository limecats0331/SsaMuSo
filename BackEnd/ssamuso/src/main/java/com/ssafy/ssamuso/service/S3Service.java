package com.ssafy.ssamuso.service;


import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

public interface S3Service {
    public String upload(MultipartFile multipartFile, String changedFileName) throws Exception;
    public String upload(MultipartFile multipartFile) throws Exception;

    public String delete(String fileName) throws Exception;
}
