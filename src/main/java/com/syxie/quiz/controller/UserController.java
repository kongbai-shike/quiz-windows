package com.syxie.quiz.controller;

import com.syxie.quiz.model.question.PageBean;
import com.syxie.quiz.model.Result;
import com.syxie.quiz.model.user.User;
import com.syxie.quiz.service.UserService;
import com.syxie.quiz.utils.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(@RequestBody Map<String, String> registerData){
        String username = registerData.get("username");
        String password = registerData.get("password");
        String checkpassword = registerData.get("checkpassword");

        if (StringUtils.isAnyBlank(username, password, checkpassword)) {
            return Result.error("用户名或密码为空");
        }
        final String role = "0";  //普通用户;
        Result result = userService.saveUser(username, password, checkpassword,role);
        return result;
    }

    @PostMapping("/addUser")
    public Result addUser(@RequestBody Map<String, String> userData){
        String username = userData.get("username");
        String password = userData.get("password");
        String checkpassword = userData.get("checkpassword");
        String role = userData.get("userrole");

        if (StringUtils.isAnyBlank(username, password, checkpassword, role)) {
            return Result.error("用户名或密码为空");
        }
        Result result = userService.saveUser(username, password, checkpassword,role);
        return result;
    }

    @PostMapping("/login")
    public Result login(@RequestBody Map<String, String> loginData){
        String username = loginData.get("username");
        String password = loginData.get("password");

        if (StringUtils.isAnyBlank(username, password)) {
            return Result.error("用户名或密码为空");
        }

        User userResult = userService.login(username, password);
        if(userResult != null){
            if (userResult.getIsDelete() != null && userResult.getIsDelete() == 1) {
                return Result.error("账号已被删除或禁用");
            }

            // 将角色写入 JWT claims（可选，但推荐）
            String token = JwtUtil.generateToken(
                    userResult.getUserName(),
                    Map.of("role", userResult.getUserRole(), "uid", userResult.getId())
            );

            // 同时在响应里返回 token 与 role，前端路由守卫使用
            Map<String, Object> data = new HashMap<>();
            data.put("token", token);
            data.put("role", userResult.getUserRole()); // 0-普通，1-管理员

            Result result = Result.success("用户登录成功");
            result.setData(data);
            return result;
        } else {
            return Result.error("用户登录失败");
        }
    }

    @GetMapping("/deleteById")
    public Result deleteUserById(Long id) {
        boolean success = userService.deleteUserById(id);
        if (success) {
            return Result.success("用户已删除");
        }
        return Result.error("用户不存在或已被删除");
    }

    @GetMapping("/deleteByName")
    public Result deleteUser(String username) {
        boolean success = userService.deleteUserByName(username);
        if (success) {
            return Result.success("用户已删除");
        }
        return Result.error("用户不存在或已被删除");
    }

    @GetMapping("/users")
    public Result getPage(@RequestParam(defaultValue="1")Integer page, @RequestParam(defaultValue="5")Integer pageSize){
        PageBean pageBean=userService.page(page, pageSize);
        return Result.success(pageBean);
    }

    @GetMapping("/findUser")
    public Result getUser(String keyword){
        List<User> users=userService.findByName(keyword);
        return Result.success(users);
    }
}