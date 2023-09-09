package com.ranchuanyin.schoolcat.domain;


import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;


@Data
@NoArgsConstructor
@TableName(value = "cat_account")
public class CatAccount implements Serializable {
    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     *
     */
    private String email;

    /**
     *
     */
    private String username;

    /**
     *
     */
    private String password;

    private String avatar;

    @TableField(fill = FieldFill.INSERT)
    private Integer experience;

    @TableField(fill = FieldFill.INSERT)
    private Date insertTime;

    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

}