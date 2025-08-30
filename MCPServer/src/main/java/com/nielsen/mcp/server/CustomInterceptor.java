package com.nielsen.mcp.server;

import org.springframework.stereotype.Component;

@Component
public class CustomInterceptor {
}/*
  * //implements HandlerInterceptor {
  * 
  * 
  * 
  * @Override public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
  * Object handler) throws Exception { final String userHeader = request.getHeader("X-NIQ-TOKEN");
  * System.out.println("--------- Request header value : " + userHeader);
  * 
  * System.out.println("-------------- preHandle--------------------------------------");
  * System.out.println(Thread.currentThread().getName()); if (Objects.nonNull(userHeader)) {
  * UserThreadLocal.setUserToken(request.getHeader("X-NIQ-TOKEN"));
  * UserTokenStatic.setUserToken(userHeader); } return true; }
  * 
  * @Override public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
  * Object handler, Exception ex) throws Exception { UserThreadLocal.clear();
  * UserTokenStatic.setUserToken(null); }
  * 
  * }
  */
