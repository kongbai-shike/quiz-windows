package com.syxie.quiz.service;

import com.syxie.quiz.model.question.QSBean;
import com.syxie.quiz.model.question.QSBeanOut;
import com.syxie.quiz.model.question.QSBeanPage;
import com.syxie.quiz.model.question.QSBeanOutManage;

import java.util.List;

public interface QuestionService {
    public int insertQuestion(QSBean qsBean);
    public boolean delQuestion(Integer id);
    public List<QSBeanOut> getQuestions();
    public QSBeanPage page(Integer page, Integer pageSize);
    public  List<QSBeanOutManage> findByKeyword(String keyword);
    public int updateQuestion(QSBean qsBean);

}
