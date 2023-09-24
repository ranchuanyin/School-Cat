package com.ranchuanyin.schoolcat.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 事物的id
     */
    private Long thingId;

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