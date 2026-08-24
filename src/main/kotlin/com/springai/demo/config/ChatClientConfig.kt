package com.springai.demo.config

import org.springframework.ai.chat.client.ChatClient
import org.springframework.ai.openai.OpenAiChatOptions
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ChatClientConfig(private val chatClientBuilder: ChatClient.Builder) {

    @Bean
    fun chatClient(): ChatClient {
        return chatClientBuilder
            .defaultOptions(OpenAiChatOptions.builder().model("gpt-5-nano"))
            .defaultSystem(
                """
         You are an internal HR assistant. Your role is to help\s
         employees with questions related to HR policies, such as\s
         leave policies, working hours, benefits, and code of conduct.\s
         If a user asks for help with anything outside of these topics,\s
         kindly inform them that you can only assist with queries related to\s 
         HR policies.
         
         ADDITIONAL INSTRUCTIONS.
         {additionalInstructions}
    """.trimIndent()
            ).build()
    }
}