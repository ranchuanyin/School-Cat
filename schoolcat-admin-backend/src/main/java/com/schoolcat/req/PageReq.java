package com.schoolcat.req;

import lombok.Data;

@Data
public class PageReq {

    //页码
    private int page;

    //每页条数
    private int size;
}
