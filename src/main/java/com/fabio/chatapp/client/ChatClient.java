package com.fabio.chatapp.client;

import jakarta.websocket.*;
import java.net.URI;
import java.util.Scanner;

@ClientEndpoint
public class ChatClient {
    private Session session;

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        System.out.println("Connected to server");
        System.out.println("Enter messages to send (type 'exit' to quit):");
    }

    @OnMessage
    public void onMessage(String message) {
        System.out.println(message);
    }

    public void sendMessage(String message) {
        try {
            session.getBasicRemote().sendText(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            ChatClient client = new ChatClient();
            container.connectToServer(client, new URI("ws://localhost:8080/chat"));

            Scanner scanner = new Scanner(System.in);
            String message;
            while (!(message = scanner.nextLine()).equalsIgnoreCase("exit")) {
                client.sendMessage(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}