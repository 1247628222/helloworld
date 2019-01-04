package com.lyl.helloworld.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * 下载文件
 * @author 10552
 *
 */
@Controller
@RequestMapping( value="/upload",method=RequestMethod.GET)
public class
DownloadController {
    private  Logger log=LoggerFactory.getLogger(DownloadController.class);
    @Value("${server.context-path}")
    private String ctx;
    /**
     * 下载文件
     */
    @RequestMapping("/**")
    public void defaultPath(HttpServletRequest request,HttpServletResponse res){
        File file = new File(System.getProperty("user.dir").replaceAll("\\\\", "/")+request.getRequestURI().replace(ctx, ""));
        res.setContentType(new MimetypesFileTypeMap().getContentType(file));
        res.setContentLengthLong(file.length());
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = res.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(file));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            log.error("未找到找文件："+e);
            //e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
