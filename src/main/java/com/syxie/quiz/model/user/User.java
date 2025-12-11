package com.syxie.quiz.model.user;

//import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

//@Data
@Getter
@Setter
public class User {
    /** 用户ID */
    private Long id;

    /** 用户名 */
    private String userName;

    /* 密码*/
    private String userPassword;

    /* 是否删除 */
    private Integer isDelete;

    /* 用户角色：0-普通用户，1-管理员 */
    private Integer userRole;

    /* 创建时间 */
    private Date createTime;

    /*更新时间 */
    private Date updateTime;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", isDelete=" + isDelete +
                ", userRole=" + userRole +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}