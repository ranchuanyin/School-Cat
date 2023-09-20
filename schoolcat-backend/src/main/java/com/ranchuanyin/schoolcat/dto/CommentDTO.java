package com.ranchuanyin.schoolcat.dto;

import lombok.Data;

@Data
public class CommentDTO {
    private Long articleId;
    private Integer pageNum;
    private Integer pageSize;
}
