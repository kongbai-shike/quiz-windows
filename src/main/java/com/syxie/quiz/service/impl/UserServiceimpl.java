package com.syxie.quiz.service.impl;

import com.syxie.quiz.mapper.UserMapper;
import com.syxie.quiz.model.question.PageBean;
import com.syxie.quiz.model.Result;
import com.syxie.quiz.model.user.User;
import com.syxie.quiz.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceimpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public Result saveUser(String username, String password, String checkpassword, String userRoleStr){
        //此处的逻辑代码；
        if (StringUtils.isAnyBlank(username, password, checkpassword)) {
            return Result.error("用户名或密码为空");
        }

        if (!password.equals(checkpassword)) {
            return Result.error("两次输入的密码不一致");
        }

        String regex = "^[a-zA-Z0-9]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(username);
        if (!matcher.matches()) {
            return Result.error("用户名包含特殊字符");
        }

        //查询数据库，确定是否已经存在用户名;
        //to add...
        int userExist = userMapper.existsByName(username);
        if(userExist > 0){
            return Result.error("用户已经存在！");
        }

        //对密码进行加密;
        final String SALT = "com.quiz";
        String encrptedPassword = DigestUtils.md5DigestAsHex((SALT + password).getBytes());

        User user = new User();
        user.setUserName(username);
        user.setUserPassword(encrptedPassword);

        // 设置用户角色，默认为普通用户
        Integer userRole = 0; // 默认普通用户
        if (StringUtils.isNotBlank(userRoleStr)) {
            try {
                userRole = Integer.parseInt(userRoleStr);
                // 验证角色值是否合法（0-普通用户，1-管理员）
                if (userRole != 0 && userRole != 1) {
                    userRole = 0; // 非法值设为默认
                }
            } catch (NumberFormatException e) {
                userRole = 0; // 转换失败设为默认
            }
        }
        user.setUserRole(userRole);

        user.setIsDelete(0);

        Date now = new Date();
        user.setCreateTime(now);
        user.setUpdateTime(now);

        //4.插入到数据库；
        int result = userMapper.saveUser(user);

        if (result > 0)
            return Result.success("新增用户成功");
        else
            return Result.error("注册用户失败");

    }

    public User login(String username, String password){
        //对密码进行加密;
        final String SALT = "com.quiz";
        String encrptedPassword = DigestUtils.md5DigestAsHex((SALT + password).getBytes());

        return userMapper.getByNameAndPassword(username,encrptedPassword);
    }

    public boolean deleteUserById(Long id) {
        int result = userMapper.deleteUserById(id);
        return result > 0;
    }

    public boolean deleteUserByName(String username) {
        int result = userMapper.deleteByUsername(username);
        return result > 0;
    }

    public PageBean page(Integer page, Integer pageSize){
        //获取总的记录数；
        Integer total=userMapper.count();

        //获取分页查询结果列表；
        Integer start = (page-1)*pageSize;
        List<User> userList=userMapper.page(start, pageSize);

        //封装PageBean对象；
        PageBean pageBean = new PageBean();
        pageBean.setTotal(total);
        pageBean.setRow(userList);

        return pageBean;
    }

    public List<User> findByName(String keyword){
        List<User> userList=userMapper.findByName(keyword);
        for(User user:userList){
            user.setUserPassword("*******");
        }
        return userList;
    }

    @Override
    public boolean updateUser(User user) {
        // 如果密码不为空，则对密码进行加密
        if (StringUtils.isNotBlank(user.getUserPassword())) {
            final String SALT = "com.quiz";
            String encryptedPassword = DigestUtils.md5DigestAsHex((SALT + user.getUserPassword()).getBytes());
            user.setUserPassword(encryptedPassword);
        } else {
            // 如果密码为空，则保持原密码不变
            user.setUserPassword(null);
        }

        user.setUpdateTime(new Date());
        int result = userMapper.updateUser(user);
        return result > 0;
    }


}
