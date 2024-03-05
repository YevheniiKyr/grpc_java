package org.example;
import jokeApp.JokeServiceGrpc;
import jokeApp.JokeServiceGrpc.JokeServiceBlockingStub;
import jokeApp.JokeApp.JokeRequest;
import jokeApp.JokeApp.JokeResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import java.io.IOException;

public class Client {
    public static void main(String[] args)  {
        ManagedChannel channel = ManagedChannelBuilder.forTarget("0.0.0.0:5000").usePlaintext().build();
        JokeServiceBlockingStub stub = JokeServiceGrpc.newBlockingStub(channel);
        JokeRequest request = JokeRequest.newBuilder().setAge(10).setMood(5).setGender("trance").build();
        JokeResponse response = stub.generateJoke(request);
        System.out.println(response.getJoke());
        channel.shutdownNow();
    }
}
