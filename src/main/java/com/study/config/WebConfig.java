package com.study.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web 설정을 담당하는 Config
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 메시지소스의 경로를 등록합니다.
     */
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:/messages/message");
        messageSource.setDefaultEncoding("UTF-8");

        return messageSource;
    }

    /**
     * 검증에 필요한 메시지소스의 경로를 등록합니다.
     */
    @Bean
    public MessageSource validationMessageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:/messages/validation");
        messageSource.setDefaultEncoding("UTF-8");

        return messageSource;
    }

    /**
     * Validatior에 검증메시지소스를 등록합니다.
     */
    @Override
    public Validator getValidator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(validationMessageSource());

        return bean;
    }
}
