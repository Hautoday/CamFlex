package com.example.camflex.chattingactivity;

public class ChatMessage {
    public String message;
    public boolean isSent; // 내가 보낸 메시지면 true, 상대방이면 false

    public ChatMessage(String message, boolean isSent) {
        this.message = message;
        this.isSent = isSent;
    }
}
