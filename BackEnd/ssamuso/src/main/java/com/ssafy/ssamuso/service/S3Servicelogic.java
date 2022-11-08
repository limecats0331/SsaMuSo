package com.ssafy.ssamuso.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;

import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class S3Servicelogic implements S3Service {
    private final AmazonS3Client amazonS3Client;
    private static final Tika tika = new Tika();
    @Value("${cloud.aws.s3.bucket}")
    public String bucket; // S3 버킷 이름

    @Override
    public String upload(MultipartFile multipartFile, String changedFileName) throws Exception {

        String fileName = multipartFile.getOriginalFilename().toLowerCase();
        if (validImgFile(multipartFile.getInputStream())) {
            File uploadFile = convert(multipartFile);
            return upload(uploadFile, changedFileName);
        } else {
            throw new Exception("파일 확장자 에러");
        }

    }

    @Override
    public String upload(MultipartFile multipartFile) throws Exception {

        String fileName = multipartFile.getOriginalFilename().toLowerCase();

        if (fileName.endsWith(".png") || fileName.endsWith(".jpeg") || fileName.endsWith(".pdf") || fileName.endsWith(".jpg")
                || fileName.endsWith(".gif") || fileName.endsWith(".webp")) {

            File uploadFile = convert(multipartFile);
            String newFileName = getDateToString() + "-" + UUID.randomUUID();

            return upload(uploadFile, newFileName);
        } else {
            throw new Exception("파일 확장자 에러");
        }
    }

    @Override
    public String delete(String fileName) throws Exception {
        DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest(this.bucket, fileName);
        //Delete
        amazonS3Client.deleteObject(deleteObjectRequest);
//        System.out.println(String.format("[%s] deletion complete", fileName));

        return null;
    }

    // S3로 파일 업로드하기
    private String upload(File uploadFile, String changedFileName) throws Exception {

        String fileName = changedFileName + uploadFile.getName(); // S3에 저장된 파일 이름
        String uploadImageUrl = putS3(uploadFile, fileName); // s3로 업로드

        removeNewFile(uploadFile);
        return uploadImageUrl;
    }

    private void removeNewFile(File targetFile) throws Exception {

        if (targetFile.delete()) {
            return;
        } else {
            throw new Exception("delete File Error");
        }
    }

    // S3로 업로드
    private String putS3(File uploadFile, String fileName) {

        amazonS3Client.putObject(
                new PutObjectRequest(bucket, fileName, uploadFile).withCannedAcl(CannedAccessControlList.PublicRead));
        return amazonS3Client.getUrl(bucket, fileName).toString();
    }


    // 로컬에 파일 업로드 하기
    private File convert(MultipartFile file) throws IOException {

        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();


        return convFile;
    }

    private String getDateToString() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        return format.format(now);
    }

    public static boolean validImgFile(InputStream inputStream) {
        try {
            List<String> notValidTypeList = Arrays.asList("image/jpeg", "image/pjpeg", "image/png", "image/gif", "image/bmp", "image/x-windows-bmp");

            String mimeType = tika.detect(inputStream);
            System.out.println("MimeType : " + mimeType);

            boolean isValid = notValidTypeList.stream().anyMatch(notValidType -> notValidType.equalsIgnoreCase(mimeType));

            return isValid;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }



}
