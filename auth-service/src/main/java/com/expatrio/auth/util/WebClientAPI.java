package com.expatrio.auth.util;

import com.expatrio.auth.beans.AuthRequest;
import com.expatrio.auth.exception.UserNotFoundRuntimeException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class WebClientAPI {
    private static final HttpClient client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).build();

    @Value("${uri.api.user-service}")
    String userServiceURL;

    public Boolean getUserFromUserService(String email) throws IOException, InterruptedException {

        final HttpRequest request = getHttpRequest(email);
        final HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());

            if(response.statusCode() != HttpStatus.NOT_FOUND.value()
                || response.statusCode() != HttpStatus.UNAUTHORIZED.value()) {
                throw new UserNotFoundRuntimeException(email);
            }
            return new GsonBuilder().create().fromJson(response.body(), Boolean.class);
    }

    private HttpRequest getHttpRequest(AuthRequest user) {
        return HttpRequest.newBuilder(URI.create(userServiceURL + "/validate/"))
                .POST(HttpRequest.BodyPublishers.ofString(new GsonBuilder().create().toJson(user))).build();
    }

    private HttpRequest getHttpRequest(String email) {
        return HttpRequest.newBuilder(URI.create(userServiceURL + "/validate/email/"))
                .POST(HttpRequest.BodyPublishers.ofString(new GsonBuilder().create().toJson(email))).build();
    }

    public AuthRequest validate(AuthRequest user) throws IOException, InterruptedException {

        final HttpRequest request = getHttpRequest(user);
        final HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        if(response.statusCode() != HttpStatus.NOT_FOUND.value()
                || response.statusCode() != HttpStatus.UNAUTHORIZED.value()) {
            throw new UserNotFoundRuntimeException(user.getPassword());
        }
        return new GsonBuilder().create().fromJson(response.body(), AuthRequest.class);
    }

}