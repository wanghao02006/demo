package com.example.demo.controller;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * Created by wh on 2017/7/3.
 */
@Controller
@RequestMapping("/upload")
public class UploadController {

    public static final Resource PICTURES_DIR = new FileSystemResource("./pictures");
    static {
        try {
            PICTURES_DIR.getFile().mkdirs();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("")
    public String uploadPage(){
        return "upload/uploadPage";
    }

    @RequestMapping(value = "",method = RequestMethod.POST)
    public String onUpload(@RequestParam(value = "file") MultipartFile multipartFile) throws IOException {
        String filename = multipartFile.getOriginalFilename();
        File tempFile = File.createTempFile("pic",getFileExtension(filename),PICTURES_DIR.getFile());
        try(InputStream in = multipartFile.getInputStream();
            OutputStream out = new FileOutputStream(tempFile)){
            IOUtils.copy(in,out);
        }
        return "upload/uploadPage";
    }

    private String getFileExtension(String filename) {
        return filename.substring(filename.lastIndexOf("."));
    }

}
