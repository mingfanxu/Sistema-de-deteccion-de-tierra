package com.wing.yjyw.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理 在 Spring Boot 项目中统一管理和处理所有可能出现的异常。这种机制简化了项目中的异常处理逻辑，提升了代码的可维护性和用户体验
 */
@ControllerAdvice(annotations = {RestController.class, Controller.class}) //在 Spring Boot 项目中统一管理和处理所有可能出现的异常。这种机制简化了项目中的异常处理逻辑，提升了代码的可维护性和用户体验
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 异常处理方法
     * @return
     */
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public R<String> exceptionHandler(SQLIntegrityConstraintViolationException ex){
        log.error(ex.getMessage());

        if(ex.getMessage().contains("Duplicate entry")){
            String[] split = ex.getMessage().split(" ");
            String msg = split[2] + "已存在";
            return R.error(msg);
        }

        return R.error("未知错误");
    }

    /**
     * 异常处理方法
     * @return
     */
    @ExceptionHandler(CustomException.class)
    public R<String> exceptionHandler(CustomException ex){
        log.error(ex.getMessage());

        return R.error(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<R> handleException(Exception ex) {
        R r = new R();
        r.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        r.setMsg(ex.getMessage());
        return new ResponseEntity<>(r, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
