package com.syxie.quiz.mapper;

import com.syxie.quiz.model.user.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper //运行时，框架会自动实现实现类对象，并将对象实例交由IoC容器管理；
public interface UserMapper{

    //查询全部用户；
    @Select("select * from user")
    public List<User> list();

    @Insert("insert into user(userName, userPassword,isDelete,userRole,createTime, updateTime)"+
            "values(#{userName}, #{userPassword}, #{isDelete}, #{userRole}, #{createTime}, #{updateTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int saveUser(User user);

    @Select("SELECT COUNT(*) FROM user WHERE userName = #{username} AND isDelete = 0")
    public int existsByName(@Param("username") String username);

    //根据id删除用户；
    @Update("UPDATE user SET isDelete = 1, updateTime = NOW() WHERE id = #{id} AND isDelete = 0")
    public int deleteUserById(@Param("id") Long id);

    //根据username删除用户；
    @Update("UPDATE user SET isDelete = 1, updateTime = NOW() WHERE userName = #{username} AND isDelete = 0")
    public int deleteByUsername(@Param("username") String username);

    @Select("SELECT COUNT(*) FROM user WHERE isDelete=0")
    public int count();

    @Select("SELECT * FROM user WHERE isDelete=0 limit #{start},#{pageSize}")
    public List<User> page(Integer start, Integer pageSize);

    @Select("SELECT * FROM user WHERE username LIKE CONCAT('%', #{keyword}, '%') AND isDelete=0")
    List<User> findByName(String keyword);

    @Select("select * from user where userName=#{username} AND userPassword=#{password}")
    public User getByNameAndPassword(String username, String password);

    @Update("<script>" +
            "UPDATE user SET " +
            "<if test='userName != null'>userName = #{userName},</if>" +
            "<if test='userPassword != null'>userPassword = #{userPassword},</if>" +
            "<if test='userRole != null'>userRole = #{userRole},</if>" +
            "updateTime = #{updateTime} " +
            "WHERE id = #{id} AND isDelete = 0" +
            "</script>")
    int updateUser(User user);


}
