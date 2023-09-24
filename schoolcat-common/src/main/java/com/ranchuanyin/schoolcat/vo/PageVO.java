package com.ranchuanyin.schoolcat.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PageVO {
    private long total;
    private long size;
    private long current;
}
