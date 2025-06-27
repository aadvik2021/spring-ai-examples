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
    
    //@Autowired
    //ChatModel chatModel;
    
    @Autowired
    private SyncMcpToolCallbackProvider toolCallbackProvider;

    public String promptAnswer(String message) {
	return ChatClient.create(chatModel)
	        .prompt(message)
	        //.tools(new PackageManagerService())
	         .toolCallbacks(toolCallbackProvider.getToolCallbacks())
	        .toolContext(Map.of("userId","aakash" ))
	        .call()
	        .content();
    }

}
