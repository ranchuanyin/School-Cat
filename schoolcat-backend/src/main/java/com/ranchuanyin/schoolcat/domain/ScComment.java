package com.ranchuanyin.schoolcat.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;


    /**
     * 评论类型（暂时）
     */
    private String type;

    /**
     * 
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long articleId;

    /**
     * 根评论
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long rootId;

    /**
     * 所回复的目标评论的userid
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long toCommentUserId;

    /**
     * 回复目标评论id
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long toCommentId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long createBy;

    /**
     * 
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
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