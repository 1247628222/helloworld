package com.lyl.helloworld.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.lyl.helloworld.entity.Content;
import com.lyl.helloworld.service.impl.IDemoService;
import com.lyl.helloworld.util.RedisUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
@RestController
public class DemoController {

    protected Logger logger =  LoggerFactory.getLogger(this.getClass());
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
    @Autowired
    private StringRedisTemplate template;

    @RequestMapping("/setValue")
    public String setValue(){
        if(!template.hasKey("shabao")){
            template.opsForValue().append("shabao", "我是你大爷");
            return "使用redis缓存保存数据成功";
        }else{
            template.delete("shabao");
            return "key已存在";
}
    }



    @RequestMapping("/getValue")
    public String getValue(){

        if(!template.hasKey("shabao")){
            return "key不存在，请先保存数据";
        }else{
            String shabao = template.opsForValue().get("shabao");//根据key获取缓存中的val
            return "获取到缓存中的数据：shabao="+shabao;
        }
    }




    @RequestMapping("/setValue2")
    public boolean setValue2(){
        return new RedisUtil().set("abc","我是你爸爸");
    }
    @RequestMapping("/getValue2")
    public String getValue2(){
        String a = (String) new RedisUtil().get("abc");
        return a;
    }

}
