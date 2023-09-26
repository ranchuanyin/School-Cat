package com.ranchuanyin.schoolcat.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName sc_comment
 */
@TableName(value ="sc_comment")
@Data
public class ScComment implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;


    /**
     * 评论类型（暂时）
     */
    private String type;

    /**
     * 
     */
    private Long articleId;

    /**
     * 根评论
     */
    private Long rootId;

    /**
     * 所回复的目标评论的userid
     */
    private Integer toCommentUserId;

    /**
     * 回复目标评论id
     */
    private Integer toCommentId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 
     */
    private Long createBy;

    /**
     * 
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 
     */
    private Long updateBy;

    /**
     * 
     */
    private Date updateTime;

    /**
     * 
     */
    private Integer delFlag;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}