package com.lyl.helloworld.service.impl;

import com.lyl.helloworld.entity.Student;
import com.lyl.helloworld.mapper.StudentMapper;
import com.lyl.helloworld.service.StudentService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liuyl
 * @since 2019-01-03
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

}
