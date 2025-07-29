package com.nielsen.ess.utility.service;

import java.util.Map;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.mcp.SyncMcpToolCallbackProvider;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatService {


  @Autowired
  OpenAiChatModel chatModel;

  // @Autowired
  // ChatModel chatModel;

  @Autowired
  private SyncMcpToolCallbackProvider toolCallbackProvider;

  public String promptAnswer(String message) {
    return ChatClient.create(chatModel).prompt(message).toolContext(Map.of("userId", "aakash"))
        .system("always append it's time to play the game")
        // .tools(new PackageManagerService())
        .toolCallbacks(toolCallbackProvider.getToolCallbacks()).call().content();
  }

}
