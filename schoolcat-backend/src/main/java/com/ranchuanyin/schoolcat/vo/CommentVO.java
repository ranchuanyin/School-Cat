package com.ranchuanyin.schoolcat.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentVO {
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

    private String toCommentUserName;

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

    private String userName;

    private String avatar;

    private List<CommentVO> children;


}
