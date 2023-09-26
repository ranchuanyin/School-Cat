package com.ranchuanyin.schoolcat.dto;

import com.ranchuanyin.schoolcat.vo.CommentVO;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class WriteACommentDTO {

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

}
