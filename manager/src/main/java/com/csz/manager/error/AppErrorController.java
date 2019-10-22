package com.csz.manager.error;

import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.web.servlet.error.ErrorAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


public class AppErrorController extends BasicErrorController {

    public AppErrorController(ErrorAttributes errorAttributes, ErrorProperties errorProperties, List<ErrorViewResolver> errorViewResolvers) {
        super(errorAttributes, errorProperties, errorViewResolvers);
    }

    @Override
    protected Map<String, Object> getErrorAttributes(HttpServletRequest request, boolean includeStackTrace) {
        Map<String, Object> attributes = super.getErrorAttributes(request, includeStackTrace);
        attributes.remove("timestamp");
        attributes.remove("error");
        attributes.remove("path");
        attributes.remove("status");

        ErrorEnum value = ErrorEnum.getValueByCode((String) attributes.get("message"));
        attributes.put("code",value.getCode());
        attributes.put("message",value.getMsg());
        attributes.put("canRetry",value.isCanRetry());
        return attributes;
    }
}
