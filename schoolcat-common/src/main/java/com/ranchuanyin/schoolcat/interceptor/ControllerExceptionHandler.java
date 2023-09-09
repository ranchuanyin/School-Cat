package com.ranchuanyin.schoolcat.interceptor;


import com.ranchuanyin.schoolcat.exception.SchoolCatException;
import com.ranchuanyin.schoolcat.units.RestBean;
import jakarta.validation.constraints.Null;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ControllerExceptionHandler {
    private static final Logger LOG = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    /**
     * 所有异常统一处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public RestBean<Null> exceptionHandler(Exception e) throws Exception {
        return RestBean.failure(500, "系统出现异常，请联系管理员");
    }

    /**
     * 业务异常统一处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = SchoolCatException.class)
    @ResponseBody
    public RestBean<Null> exceptionHandler(SchoolCatException e) {
        LOG.error("业务异常：{}", e.getE().getDesc());
        return RestBean.failure(500, e.getE().getDesc());
    }

    /**
     * 校验异常统一处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public RestBean<Null> exceptionHandler(BindException e) {
        LOG.error("校验异常：{}", e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return RestBean.failure(500, e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }

    /**
     * 校验异常统一处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = RuntimeException.class)
    @ResponseBody
    public RestBean exceptionHandler(RuntimeException e) {
        throw e;
    }
}
