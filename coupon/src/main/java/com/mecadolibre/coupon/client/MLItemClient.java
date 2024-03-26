package com.mecadolibre.coupon.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mecadolibre.coupon.dto.MLItemResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class MLItemClient {

    private static final String URL = "https://api.mercadolibre.com/items/";

    public MLItemResponse getItem(String itemId) {
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL + itemId))
                .GET()
                .build();
        MLItemResponse itemResponse = null;

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            int statusCode = response.statusCode();
            String responseBody = response.body();
            ObjectMapper mapper = new ObjectMapper();
            itemResponse = mapper.readValue(responseBody, MLItemResponse.class);

            System.out.println("Status Code: " + statusCode);
            System.out.println("Response Body: " + responseBody);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return itemResponse;

    }
}
