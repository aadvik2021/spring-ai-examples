package com.nielsen.ess.utility.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    @Autowired
    private ChatService chatService;

    @GetMapping(value = "/chat")
    public String prompt(@RequestParam("chatMessage") String message) {
	return chatService.promptAnswer(message);
    }

}
