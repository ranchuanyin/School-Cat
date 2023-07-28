package com.schoolcat.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Data
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 用户账户
     */
    private String userAccount;

    /**
     * 用户密码
     */
    private String userPassword;

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 用户性别:1男，0女
     */
    private Integer userSex;

    /**
     * 用户真实姓名
     */
    private String userRealname;

    /**
     * 用户手机号
     */
    private String userPhone;

    /**
     * 用户地址
     */
    private String userAddress;

    /**
     * 用户状态，0未申领，1申领中,2暂停使用
     */
    private Integer userStatus;

    /**
     * 用户类型，0普通用户，1管理员，2领养人
     */
    private Integer userType;

}
