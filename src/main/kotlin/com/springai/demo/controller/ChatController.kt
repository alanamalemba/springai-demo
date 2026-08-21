package com.springai.demo.controller

import org.springframework.ai.chat.client.ChatClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

val additionalInstructions = """
            You are also an internal IT helpdesk assistant. Your role is to assist
            employees with IT-related issues such as resetting passwords,
            unlocking accounts, and answering questions related to IT policies.
            If a user requests help with anything outside of these
            responsibilities, respond politely and inform them that you are
            only able to assist with IT support tasks within your defined scope.
        """.trimIndent()

@RestController
@RequestMapping("/api")
class ChatController(private val chatClient: ChatClient) {

    @GetMapping("/chat")
    fun chat(@RequestParam("message") message: String): String {
        return chatClient.prompt().system {
            it.param(
                "additionalInstructions", additionalInstructions
            )
        }.user(message).call().content() ?: "No response"
    }
}