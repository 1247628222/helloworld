package com.lyl.helloworld.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lyl.helloworld.entity.Content;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;


@Resource
public interface DemoMapper  extends BaseMapper<Content> {
    
}
