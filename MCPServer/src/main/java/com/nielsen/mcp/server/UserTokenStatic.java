package com.nielsen.mcp.server;

public class UserTokenStatic {

  private static String userToken = null;

  public static void setUserToken(String userToken) {
    UserTokenStatic.userToken = userToken;
  }

  public static String getUserToken() {
    return userToken;
  }

}
