package com.lyl.helloworld.util;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Random;


public class FileUtil {
    /**
     *    上传单个临时文件
     *    注意：临时文件存放位置由系统环境决定，可能会被删除一般用于保存缓存文件
     * @param muFile 文件对象
     * @return 返回文件磁盘访问路径
     */
    public static String uploadTmpFile(MultipartFile muFile) {
        File tmpDir = FileUtils.getTempDirectory();
        System.out.println("得到系统临时文件路径：" + tmpDir.getAbsolutePath());
        String tmpFileName = (Math.random() * 10000 + "").replace(".", "") + "_" + muFile.getOriginalFilename();
        try {
            muFile.transferTo(new File(tmpDir, tmpFileName));
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return tmpFileName;
    }
    /**
     *    上传多个临时文件
     *    注意：临时文件存放位置由系统环境决定，可能会被删除一般用于保存缓存文件
     * @param muFile 文件对象
     * @return 返回多个文件磁盘访问路径以分号分割
     */
    public static String uploadTmpFiles(MultipartFile[] muFiles) {
        File tmpDir = FileUtils.getTempDirectory();
        System.out.println("得到系统临时文件路径：" + tmpDir.getAbsolutePath());
        StringBuilder fileNames=new StringBuilder();
        for(int i=0;i<muFiles.length;i++) {
            String tmpFileName = (Math.random() * 10000 + "").replace(".", "") + "_" + muFiles[i].getOriginalFilename();
            try {
                muFiles[i].transferTo(new File(tmpDir, tmpFileName));
                fileNames.append(tmpFileName);
                fileNames.append(";");
            } catch (IllegalStateException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return fileNames.deleteCharAt(fileNames.length()-1).toString();
    }
    /**
     *    上传单个文件
     *    注意：此方法返回文件的相对访问url
     * @param muFile 文件对象
     * @return 返回文件的相对url
     */
    public static String uploadFile(MultipartFile muFile) {
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
    /**
     *    上传多个个文件
     *    注意：此方法返回文件的相对访问url
     * @param muFiles 文件对象组
     * @return 返回文件的相对url组，以分号分割
     */
    public static String uploadFiles(MultipartFile[] muFiles) {
        StringBuilder fileUrls=new StringBuilder();
        String fileDir=System.getProperty("user.dir").replaceAll("\\\\", "/");
        for(int i=0;i<muFiles.length;i++) {
            String FileName = getFilePathName(muFiles[i],false);
            String filePathName=fileDir+FileName;
            try {
                File file = new File(filePathName);
                File fileParent = file.getParentFile();
                if(!fileParent.exists()){
                    fileParent.mkdirs();
                }
                muFiles[i].transferTo(file);
                System.out.println("文件存放路径：" + filePathName);
                fileUrls.append(FileName);
                fileUrls.append(";");
            } catch (IllegalStateException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return fileUrls.deleteCharAt(fileUrls.length()-1).toString();
    }
    /**
     *      获取文件名
     * @param muFile 文件
     * @param isRetain 是否保留源文件名
     * @return 返回文件名，以当前年月日作为前缀路径
     */
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
