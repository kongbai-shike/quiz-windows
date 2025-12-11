package com.syxie.quiz.service;

import com.syxie.quiz.model.question.PageBean;
import com.syxie.quiz.model.Result;
import com.syxie.quiz.model.user.User;

import java.util.List;

public interface UserService {

    //add user to mysql
    public Result saveUser(String username, String password, String checkpassword, String userRoleStr);
    public boolean deleteUserById(Long id);
    public boolean deleteUserByName(String username);
    public PageBean page(Integer page, Integer pageSize);
    public List<User> findByName(String keyword);
    public boolean updateUser(User user);
    public User login(String username, String password);

}
