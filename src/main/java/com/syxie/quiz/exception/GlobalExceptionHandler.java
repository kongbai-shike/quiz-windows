//package com.syxie.quiz.exception;
//
//import com.syxie.quiz.model.Result;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//@Slf4j
//@RestControllerAdvice
//public class GlobalExceptionHandler {
//
//    @ExceptionHandler(Exception.class)
//    public Result exceptionhandle(Exception ex){
//        //ex.printStackTrace();
//        log.error("发生异常:",ex);
//        return Result.error("操作失败，请联系管理员！");
//    }
//}