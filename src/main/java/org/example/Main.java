package org.example;


import io.grpc.Server;
import io.grpc.ServerBuilder;


import java.io.IOException;

public class Main {
    private static final int PORT = 8080;
    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(PORT).addService(new JokeServiceImpl()).build();
        server.start();
        System.out.println("Server started on port " + PORT);
        server.awaitTermination();
    }
}