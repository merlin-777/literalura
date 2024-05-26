package com.x.literalura.service;

import org.springframework.stereotype.Component;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

@Component
public class ApiConsumer {
    private final HttpClient client;

    public ApiConsumer() {
        this.client = HttpClient.newHttpClient();
    }

    public String fetchData(String url) {
        HttpRequest request = createRequest(url, null);
        return sendRequest(request);
    }

    public String fetchData(String url, Map<String, String> headers) {
        HttpRequest request = createRequest(url, headers);
        return sendRequest(request);
    }

    private HttpRequest createRequest(String url, Map<String, String> headers) {
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET();

        if (headers != null) {
            headers.forEach(builder::header);
        }

        return builder.build();
    }

    private String sendRequest(HttpRequest request) {
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() >= 200 && response.statusCode() < 300) {
                return response.body();
            } else {
                throw new RuntimeException("Request error: " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            Thread.currentThread().interrupt(); // Re-interrupt the thread
            throw new RuntimeException("Error sending request: " + e.getMessage(), e);
        }
    }
}
