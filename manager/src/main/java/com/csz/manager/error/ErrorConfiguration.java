package com.csz.manager.error;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * 错误处理配置
 */
@Configuration
public class ErrorConfiguration {

    @Bean
    public AppErrorController basicErrorController(ErrorAttributes errorAttributes,
                                                     ServerProperties serverProperties,
                                                     ObjectProvider<List<ErrorViewResolver>> errorViewResolversProvider) {
        return new AppErrorController(errorAttributes, serverProperties.getError(),errorViewResolversProvider.getIfAvailable());
    }
}
