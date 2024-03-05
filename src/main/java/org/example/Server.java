package org.example;

import io.grpc.ServerBuilder;

import java.io.IOException;

public class Server {
    private static final int PORT = 5000;
    public static void main(String[] args) throws IOException, InterruptedException {
        io.grpc.Server server = ServerBuilder.forPort(PORT).addService(new JokeServiceImpl()).build();
        server.start();
        System.out.println("Server started on port " + PORT);
        server.awaitTermination();
    }
}
