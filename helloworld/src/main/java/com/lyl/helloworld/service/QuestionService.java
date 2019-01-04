package com.lyl.helloworld.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.lyl.helloworld.entity.Question;
import com.baomidou.mybatisplus.service.IService;
import com.lyl.helloworld.entity.QuestionStudentVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liuyl
 * @since 2019-01-03
 */
public interface QuestionService extends IService<Question> {
    Page<QuestionStudentVO> getQuestionStudent(Page<QuestionStudentVO> page);
}
