package com.expatrio.auth.util;

import com.expatrio.auth.beans.UserProfile;
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

    @Value("${uri.api.user-service}")
    String userServiceURL;

    public UserProfile getUserFromUserService(String email) throws IOException {

        HttpRequest request = HttpRequest.newBuilder(URI.create(userServiceURL + "/email/" + email)).GET().build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return getResponse(response.body());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private UserProfile getResponse(String json) {
        return gson.fromJson(json, UserProfile.class);
    }
}
