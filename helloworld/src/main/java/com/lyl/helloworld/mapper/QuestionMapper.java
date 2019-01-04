package com.lyl.helloworld.mapper;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.lyl.helloworld.entity.Question;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lyl.helloworld.entity.QuestionStudentVO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liuyl
 * @since 2019-01-03
 */

public interface QuestionMapper extends BaseMapper<Question> {
    @Select("SELECT tb_question.*,tb_student.`name` FROM tb_question,tb_student WHERE tb_question.student_id=tb_student.id")
    List<QuestionStudentVO> getQuestionStudent(Pagination page);
}
