package com.fabio.chatapp.server;

import org.glassfish.tyrus.server.Server;

public class ChatServerMain {
    public static void main(String[] args) {
        Server server = new Server("localhost", 8025, "/", null, ChatServer.class);
        try {
            server.start();
            System.out.println("Chat server started on port 8025. Press enter to stop it.");
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            server.stop();
        }
    }
}