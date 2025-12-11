// 修改实现类实现接口
package com.syxie.quiz.service.impl;

import com.syxie.quiz.model.question.*;
import com.syxie.quiz.utils.Tools;
import com.syxie.quiz.mapper.QuestionMapper;
import com.syxie.quiz.service.QuestionService;  // 导入接口
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class QuestionServiceimpl implements QuestionService {  // 实现接口

    @Autowired
    private QuestionMapper questionMapper;

    @Override  // 添加注解
    public int insertQuestion(QSBean qsBean){
        String ans = qsBean.getAnswer();
        if (!List.of("a", "b", "c", "d").contains(ans.toLowerCase())) {
            throw new IllegalArgumentException("Answer must be one of: a, b, c, or d");
        }

        Question q = new Question();
        q.setQuestionText(qsBean.getQuestion());

        q.setAnswer1Text(qsBean.getOptiona());
        q.setAnswer1Correct("a".equalsIgnoreCase(ans));

        q.setAnswer2Text(qsBean.getOptionb());
        q.setAnswer2Correct("b".equalsIgnoreCase(ans));

        q.setAnswer3Text(qsBean.getOptionc());
        q.setAnswer3Correct("c".equalsIgnoreCase(ans));

        q.setAnswer4Text(qsBean.getOptiond());
        q.setAnswer4Correct("d".equalsIgnoreCase(ans));

        q.setIsDelete(0);
        q.setCreateTime(new Date());
        q.setUpdateTime(new Date());

        int result = questionMapper.insertQuestion(q);
        return result;
    }

    // 添加更新方法实现
    @Override
    public int updateQuestion(QSBean qsBean) {
        String ans = qsBean.getAnswer();
        if (!List.of("a", "b", "c", "d").contains(ans.toLowerCase())) {
            throw new IllegalArgumentException("Answer must be one of: a, b, c, or d");
        }

        Question q = new Question();
        q.setId(qsBean.getId()); // 设置要更新的题目ID
        q.setQuestionText(qsBean.getQuestion());

        q.setAnswer1Text(qsBean.getOptiona());
        q.setAnswer1Correct("a".equalsIgnoreCase(ans));

        q.setAnswer2Text(qsBean.getOptionb());
        q.setAnswer2Correct("b".equalsIgnoreCase(ans));

        q.setAnswer3Text(qsBean.getOptionc());
        q.setAnswer3Correct("c".equalsIgnoreCase(ans));

        q.setAnswer4Text(qsBean.getOptiond());
        q.setAnswer4Correct("d".equalsIgnoreCase(ans));

        q.setUpdateTime(new Date()); // 设置更新时间

        int result = questionMapper.updateQuestion(q);
        return result;
    }

    public boolean delQuestion(Integer id){
        int result = questionMapper.delQuestionById(id);
        return result>0;
    }

    public List<QSBeanOut> getQuestions(){
        //获取question list;
        List<Question> questionList = questionMapper.getQuestions();
        //将question list转化为QSBeanOut;
        List<QSBeanOut> qsBeanOutList = Tools.convertToQSBeanList(questionList);
        return qsBeanOutList;
    }

    public QSBeanPage page(Integer page, Integer pageSize){
        //获取总的记录数；
        Integer total = questionMapper.count();

        //获取分页查询结果列表；
        Integer start = (page-1)*pageSize;
        List<Question> questionList = questionMapper.page(start, pageSize);

        //将questionList转化为QSBeanOutManage列表
        List<QSBeanOutManage> qsBeanOutManageList = Tools.convertToQSBeanManageList(questionList);  // 修正变量类型

        //封装QSBeanPage对象；
        QSBeanPage qsBeanPage = new QSBeanPage();
        qsBeanPage.setTotal(total);
        qsBeanPage.setQsBeanList(qsBeanOutManageList);  // 修正：传入列表

        return qsBeanPage;
    }

    public List<QSBeanOutManage> findByKeyword(String keyword) {
        List<Question> questionList = questionMapper.findByKeyword(keyword);  // 修正：使用 questionMapper
        List<QSBeanOutManage> qsBeanOutManageList = Tools.convertToQSBeanManageList(questionList);  // 修正变量名
        return qsBeanOutManageList;
    }
}