package com.lyl.helloworld.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.lyl.helloworld.entity.Question;
import com.lyl.helloworld.entity.QuestionStudentVO;
import com.lyl.helloworld.service.QuestionService;
import com.lyl.helloworld.service.StudentService;
import com.lyl.helloworld.service.impl.QuestionServiceImpl;
import com.lyl.helloworld.service.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CommonController {
    @Autowired
    QuestionServiceImpl questionService;

    @Autowired
    StudentServiceImpl studentService;

    @GetMapping("/getAllQuestionByPage/{page}/{size}")
    public Map<String, Object> getAllQuestionByPage(@PathVariable Integer page, @PathVariable Integer size) {
        Map<String, Object> map = new HashMap<>();
        Page<Question> questionPage = questionService.selectPage(new Page<>(page, size));
        if (questionPage.getRecords().size() == 0) {
            map.put("code", 400);
        } else {
            map.put("code", 200);
            map.put("data", questionPage);
        }
        return map;
    }

    @GetMapping("/getAllQuestionWithStudentByPage/{page}/{size}")
    public Map<String, Object> getAllQuestionWithStudentByPage(@PathVariable Integer page, @PathVariable Integer size) {
        Map<String, Object> map = new HashMap<>();
        Page<QuestionStudentVO> questionStudent = questionService.getQuestionStudent(new Page<>(page, size));
        if (questionStudent.getRecords().size() == 0) {
            map.put("code", 400);
        } else {
            map.put("code", 200);
            map.put("data", questionStudent);
        }
        return map;
    }

}
