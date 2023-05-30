package com.ranchuanyin.schoolcat.common.toolclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginAndRun implements Serializable {
    @Serial
    private static final long serialVersionUID = 2L;
    private Long Id;
    private Boolean Login;
    private Boolean release;
}
