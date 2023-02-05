package com.example.httpplayground.connection

import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Component
class KeepAliveConfiguration: WebMvcConfigurer {

    @Bean
    fun setFilterRegistration(): FilterRegistrationBean<KeepAliveFilter> {
        val filterRegistrationBean = FilterRegistrationBean(KeepAliveFilter())
        filterRegistrationBean.addUrlPatterns("/*")
        return filterRegistrationBean
    }
}