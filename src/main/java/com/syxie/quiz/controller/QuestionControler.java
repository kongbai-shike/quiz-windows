package com.syxie.quiz.controller;

import com.syxie.quiz.model.question.QSBean;
import com.syxie.quiz.model.question.QSBeanOut;
import com.syxie.quiz.model.question.QSBeanPage;
import com.syxie.quiz.model.question.QSBeanOutManage;
import com.syxie.quiz.model.Result;
import com.syxie.quiz.model.user.User;
import com.syxie.quiz.service.QuestionService;
import com.syxie.quiz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class QuestionControler {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private UserService userService;

    @PostMapping("/addQuestion")
    public Result addQuestion(@RequestBody QSBean question) {
        int result = questionService.insertQuestion(question);
        if (result > 0){
            return Result.success("插入新问题成功");
        }else{
            return Result.error("插入失败");
        }
    }

    // 添加更新题目接口
    @PostMapping("/updateQuestion")
    public Result updateQuestion(@RequestBody QSBean question) {
        int result = questionService.updateQuestion(question);
        if (result > 0){
            return Result.success("更新问题成功");
        }else{
            return Result.error("更新失败");
        }
    }

    @GetMapping("/delQuestion")
    public Result deleteQuestion(Integer id) {
        boolean success = questionService.delQuestion(id);
        if (success) {
            return Result.success("用户成功删除");
        }
        return Result.error("用户不存在或已被删除");
    }

    @GetMapping("/getQuestion")
    public Result getQuestion() {
        List<QSBeanOut> qsBeanOut = questionService.getQuestions();
        return Result.success(qsBeanOut);
    }

    @GetMapping("/questions")
    public Result getPage(@RequestParam(defaultValue="1")Integer page, @RequestParam(defaultValue="5")Integer pageSize){
        QSBeanPage qsBeanPage=questionService.page(page, pageSize);
        return Result.success(qsBeanPage);
    }

    @GetMapping("/findQuestion")
    public Result findQuestion(@RequestParam String keyword) {
        List<QSBeanOutManage> qsBeanOut = questionService.findByKeyword(keyword);
        return Result.success(qsBeanOut);
    }

    @PostMapping("/updateUser")
    public Result updateUser(@RequestBody User user) {
        boolean success = userService.updateUser(user);
        if (success) {
            return Result.success("用户更新成功");
        } else {
            return Result.error("用户更新失败");
        }
    }
}