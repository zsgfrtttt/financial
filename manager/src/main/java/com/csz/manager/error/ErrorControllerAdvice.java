package com.csz.manager.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice(basePackages = {"com.csz.manager.controller"})
public class ErrorControllerAdvice {

    @ExceptionHandler({Exception.class})
    @ResponseBody
    public ResponseEntity handleError(Exception e) {
        ErrorEnum errorEnum = ErrorEnum.getValueByCode(e.getMessage());
        Map<String, Object> attrs = new HashMap<>();
        attrs.put("code", errorEnum.getCode());
        attrs.put("canRetry", errorEnum.isCanRetry());
        attrs.put("message", errorEnum.getMsg());
        attrs.put("tag", "advice");
        ResponseEntity entity = new ResponseEntity(attrs, HttpStatus.INTERNAL_SERVER_ERROR);
        return entity;
    }
}
