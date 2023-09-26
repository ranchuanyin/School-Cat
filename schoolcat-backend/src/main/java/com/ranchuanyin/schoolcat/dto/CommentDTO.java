package com.ranchuanyin.schoolcat.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class CommentDTO {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long articleId;
    private Integer pageNum;
    private Integer pageSize = 5;
}
