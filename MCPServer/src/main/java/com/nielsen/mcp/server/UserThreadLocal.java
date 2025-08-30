package com.nielsen.mcp.server;

import org.springframework.stereotype.Component;

@Component
public class UserThreadLocal extends ThreadLocal<String> {

  private static final ThreadLocal<String> userTokenThreadLocal = new ThreadLocal<String>();

  public static String getUserToken() {
    return userTokenThreadLocal.get();
  }

  public static void setUserToken(String userToken) {
    userTokenThreadLocal.set(userToken);
  }

  public static void clear() {
    userTokenThreadLocal.remove();
  }



}
