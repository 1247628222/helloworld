package com.lyl.helloworld.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Random;

@RestController
public class FileUploadController {
    @PostMapping("upload")
    public String uploadFile(@RequestParam("fileName") MultipartFile muFile){
        System.out.println(muFile);
        //判断文件是否为空
        if (muFile.isEmpty()) {
            return "-1";
        }
        String FileName = getFilePathName(muFile,false);
        String filePathName=System.getProperty("user.dir").replaceAll("\\\\", "/")+FileName;
        try {
            File file = new File(filePathName);
            File fileParent = file.getParentFile();
            if(!fileParent.exists()){
                fileParent.mkdirs();
            }
            muFile.transferTo(file);
            System.out.println("文件存放路径：" + filePathName);
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return FileName;
    }
    private static String getFilePathName(MultipartFile muFile,boolean isRetain){
        String fileName = muFile.getOriginalFilename();
        String name=fileName;
        String prefix="";
        if(fileName.indexOf('.')!=-1) {
            name=fileName.substring(0,fileName.indexOf('.'));
            prefix=fileName.substring(fileName.lastIndexOf("."));
        }

        LocalDate date = LocalDate.now();
        StringBuilder filePathName=new StringBuilder("/upload/");
        filePathName.append(date.getYear());
        filePathName.append("/");
        filePathName.append(date.getMonthValue());
        filePathName.append("/");
        filePathName.append(date.getDayOfMonth());
        filePathName.append("/");
        Random r = new Random();
        int pix=r.ints(1, (100 + 1)).findFirst().getAsInt();
        filePathName.append(System.currentTimeMillis());
        filePathName.append(""+pix);
        //文件名超过64字符则截取
        if(isRetain){
            filePathName.append("_");
            if(name.length()>64){
                name=name.substring(0,64);
            }
            filePathName.append(name);
        }
        filePathName.append(prefix);
        return filePathName.toString();
    }
}
