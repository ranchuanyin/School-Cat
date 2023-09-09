package com.ranchuanyin.schoolcat.units;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginAndRun implements Serializable {
    @Serial
    private static final long serialVersionUID = 2L;
    private long Id;
    private boolean Login;
    private boolean Release;
}
