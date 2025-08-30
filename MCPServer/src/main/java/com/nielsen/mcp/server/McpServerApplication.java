package com.nielsen.mcp.server;

import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class McpServerApplication {

  @Autowired
  private ESSService essService;

  public static void main(String[] args) {
    SpringApplication.run(McpServerApplication.class, args);
  }


  @Bean
  ToolCallbackProvider toolsForESS() {
    return MethodToolCallbackProvider.builder().toolObjects(essService).build();
  }
}
