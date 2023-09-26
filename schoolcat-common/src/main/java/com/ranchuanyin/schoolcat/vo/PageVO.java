package com.ranchuanyin.schoolcat.vo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class PageVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private long total;
    private long size;
    private long current;
}
