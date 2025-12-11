package com.syxie.quiz.utils;

import com.syxie.quiz.model.question.AnsBean;
import com.syxie.quiz.model.question.QSBeanOut;
import com.syxie.quiz.model.question.QSBeanOutManage;
import com.syxie.quiz.model.question.Question;

import java.util.ArrayList;
import java.util.List;

public class Tools {

    public static List<QSBeanOut> convertToQSBeanList(List<Question> questionList) {
        List<QSBeanOut> result = new ArrayList<>();

        for (Question question : questionList) {
            QSBeanOut qsBean = new QSBeanOut();
            qsBean.setQuestion(question.getQuestionText());

            List<AnsBean> answers = new ArrayList<>();
            answers.add(new AnsBean(question.getAnswer1Text(), question.getAnswer1Correct()));
            answers.add(new AnsBean(question.getAnswer2Text(), question.getAnswer2Correct()));
            answers.add(new AnsBean(question.getAnswer3Text(), question.getAnswer3Correct()));
            answers.add(new AnsBean(question.getAnswer4Text(), question.getAnswer4Correct()));

            qsBean.setAnswers(answers);
            result.add(qsBean);
        }
        return result;
    }

    public static List<QSBeanOutManage> convertToQSBeanManageList(List<Question> questionList) {
        List<QSBeanOutManage> result = new ArrayList<>();
        if (questionList == null) {
            return result;
        }

        for (Question question : questionList) {
            // 跳过已删除的问题
            if (question.getIsDelete() != null && question.getIsDelete() == 1) {
                continue;
            }

            QSBeanOutManage bean = new QSBeanOutManage();
            bean.setQuestionText(question.getQuestionText());
            bean.setId(question.getId());

            // 构建选项列表（固定4个选项）
            List<String> options = new ArrayList<>(4);
            options.add(question.getAnswer1Text() != null ? question.getAnswer1Text() : "");
            options.add(question.getAnswer2Text() != null ? question.getAnswer2Text() : "");
            options.add(question.getAnswer3Text() != null ? question.getAnswer3Text() : "");
            options.add(question.getAnswer4Text() != null ? question.getAnswer4Text() : "");
            bean.setOptions(options);

            // 查找正确答案文本
            bean.setAnswer(determineCorrectAnswer(question));
            result.add(bean);
        }

        return result;
    }

    private static String determineCorrectAnswer(Question question) {
        if (question.getAnswer1Correct() != null && question.getAnswer1Correct()) {
            return "a";  // 返回字母而不是文本
        }
        if (question.getAnswer2Correct() != null && question.getAnswer2Correct()) {
            return "b";
        }
        if (question.getAnswer3Correct() != null && question.getAnswer3Correct()) {
            return "c";
        }
        if (question.getAnswer4Correct() != null && question.getAnswer4Correct()) {
            return "d";
        }
        return ""; // 没有正确答案时返回空字符串
    }


}
