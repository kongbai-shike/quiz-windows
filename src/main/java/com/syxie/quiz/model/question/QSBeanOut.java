package com.syxie.quiz.model.question;

import java.util.List;

public class QSBeanOut{
    private String question;
    private List<AnsBean> answers;
    //setter.getter.

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<AnsBean> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnsBean> answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "QSBeanOut{" +
                "question='" + question + '\'' +
                ", answers=" + answers +
                '}';
    }
}