package com.lyl.helloworld.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lyl.helloworld.entity.Content;
import com.lyl.helloworld.mapper.DemoMapper;
import com.lyl.helloworld.service.DemoService;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
public   class IDemoService  extends ServiceImpl<DemoMapper,Content> implements DemoService {

}
