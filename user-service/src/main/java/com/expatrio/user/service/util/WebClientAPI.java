package com.expatrio.user.service.util;

import com.expatrio.user.service.beans.UserDetails;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class WebClientAPI {

    private static final HttpClient client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).build();
    private Gson gson = new Gson();

    @Value("${uri.api.user-api-service}")
    String userAPIURL;

    public UserDetails getUserFromUserService(String email) throws IOException {

        HttpRequest request = HttpRequest.newBuilder(URI.create(userAPIURL + "/email/" + email)).GET().build();
        HttpResponse<String> httpResponseCompletableFuture =
                null;
        try {
            httpResponseCompletableFuture = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return getResponse(httpResponseCompletableFuture.body());
    }

    private UserDetails getResponse(String json) {
        return gson.fromJson(json, UserDetails.class);
    }

    public boolean validateUser(String jwt, String email) throws IOException, InterruptedException {


//        HttpRequest request = HttpRequest.newBuilder(URI.create(userAPIURL + "/validate")).;
        ValidateUser validateUser = new ValidateUser(jwt, email);

        HttpRequest request = HttpRequest.newBuilder(URI.create(userAPIURL + "validate"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(String.valueOf(validateUser))).build();

        HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());
        System.out.println("------------response--------->>"+response);

        return new Boolean(response.body());
    }
}

class ValidateUser {
    private String jwt;
    private String email;

    public ValidateUser(String jwt, String email) {
        this.jwt = jwt;
        this.email = email;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
