package com.ranchuanyin.schoolcat.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CatAccountVO {
    private Long id;

    private String email;

    private String username;


    private String avatar;

    private Integer experience;

    private String JWT;
}
