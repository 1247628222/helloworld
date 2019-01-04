package com.lyl.helloworld.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.lyl.helloworld.entity.Question;
import com.lyl.helloworld.entity.QuestionStudentVO;
import com.lyl.helloworld.mapper.QuestionMapper;
import com.lyl.helloworld.service.QuestionService;
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
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements QuestionService {
    @Override
    public Page<QuestionStudentVO> getQuestionStudent(Page<QuestionStudentVO> page) {
        return page.setRecords(this.baseMapper.getQuestionStudent(page));
    }

}
