package org.example;



import io.grpc.stub.StreamObserver;
import jokeApp.JokeServiceGrpc.JokeServiceImplBase;
import jokeApp.JokeApp.JokeRequest;
import jokeApp.JokeApp.JokeResponse;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;


public class JokeServiceImpl extends JokeServiceImplBase {
    private final String API_KEY = "A+MSgg8IBx3SnuZqk1YTPA==cc1s12gn7MMHAnC4";
    private final String JOKE_URL = "https://api.api-ninjas.com/v1/jokes?limit=1";

    @Override
    public void generateJoke(
            JokeRequest request,
            StreamObserver<JokeResponse> responseObserver) {
        String joke = getJoke();
        if(joke == null) joke = " Who will win if mexican and black jump from the climb?    Society";
        JokeResponse response = JokeResponse.newBuilder().setJoke(joke).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    private String getJoke() {
        String joke = "";
        try (HttpClient client = HttpClient.newHttpClient()) {
            // Prepare the HttpRequest
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(JOKE_URL))
                    .header("X-Api-Key", API_KEY) // Replace with your actual API key
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            joke = response.body();
            System.out.println("Response body: " + response.body());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
        return joke;
    }
}
