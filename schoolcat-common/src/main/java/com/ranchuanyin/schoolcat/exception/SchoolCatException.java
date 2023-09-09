package com.ranchuanyin.schoolcat.exception;


import com.ranchuanyin.schoolcat.enums.SchoolCatExceptionEnum;

public class SchoolCatException extends RuntimeException {
    private SchoolCatExceptionEnum e;

    public SchoolCatException(SchoolCatExceptionEnum e) {
        this.e = e;
    }

    public SchoolCatExceptionEnum getE() {
        return e;
    }

    public void setE(SchoolCatExceptionEnum e) {
        this.e = e;
    }

    /**
     * 不写入堆栈信息，提高性能
     */
    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
