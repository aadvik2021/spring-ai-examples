package com.nielsen.ess.utility.service;

import java.util.Map;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.mcp.SyncMcpToolCallbackProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class ChatService {


  @Autowired
  ChatModel chatModel;

  @Autowired
  private HttpServletRequest httpServletRequest;

  // @Autowired
  // ChatModel chatModel;

  @Autowired
  private SyncMcpToolCallbackProvider toolCallbackProvider;

  public String promptAnswer(String message) {
    PromptTemplate promptTemplate =
        new PromptTemplate("Whenever a tool is invoke pass {userToken} as toolParam name authToken."
            + "If none of tool are called then by default call executeQuery and pass message as toolParam and pass "
            + "{userToken} as authToken");
    promptTemplate.add("userToken", httpServletRequest.getHeader("X-NIQ-TOKEN"));
    return ChatClient.create(chatModel).prompt(message).system(promptTemplate.render())
        .toolContext(Map.of("userId", "aakash"))
        // .system(
        // "if none of tools are called the by default call executeQuery tool and pass message")
        // .tools(new PackageManagerService())
        .toolCallbacks(toolCallbackProvider.getToolCallbacks()).call().content();
  }

}
