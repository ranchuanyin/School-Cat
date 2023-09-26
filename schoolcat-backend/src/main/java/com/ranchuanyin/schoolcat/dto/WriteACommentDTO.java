package com.ranchuanyin.schoolcat.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class WriteACommentDTO {

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

}
