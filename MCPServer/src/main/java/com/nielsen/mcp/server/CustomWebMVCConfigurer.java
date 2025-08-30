package com.nielsen.mcp.server;

import org.springframework.context.annotation.Configuration;


@Configuration
public class CustomWebMVCConfigurer {
}/*
  * implements WebMvcConfigurer { private final CustomInterceptor interceptor;
  * 
  * CustomWebMVCConfigurer(CustomInterceptor interceptor) { this.interceptor = interceptor; }
  * 
  * @Override public void addInterceptors(InterceptorRegistry registry) { //
  * registry.addInterceptor(interceptor).addPathPatterns("/**");
  * System.out.println("interceptor is added"); } }
  */
