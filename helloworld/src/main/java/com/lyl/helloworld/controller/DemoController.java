package com.lyl.helloworld.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.lyl.helloworld.entity.Content;
import com.lyl.helloworld.mapper.DemoMapper;
import com.lyl.helloworld.service.impl.IDemoService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
@RestController
public class DemoController {
    private final static Logger logger = LoggerFactory.getLogger(DemoController.class);
    @Autowired
    IDemoService demoService;

    @ApiOperation(value="获取新闻信息",notes="获取新闻信息")
    @GetMapping("/get")
    public List<Content> getNewslist(Model model, HttpServletRequest request, @RequestParam(name = "id") Integer column_id){
        List<Content> list=demoService.selectList(new EntityWrapper<Content>());
        return demoService.selectList(new EntityWrapper<Content>().eq("id",column_id));
    }

    @GetMapping("/abc")
    public String getNewslist(){
        return "123";
    }
}
