package com.ranchuanyin.schoolcat.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentVO implements Serializable {

    private static final long serialVersionUID = 1L;
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

    private String toCommentUserName;

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
    private Date createTime;

    private String userName;

    private String avatar;

    private List<CommentVO> children;


}
