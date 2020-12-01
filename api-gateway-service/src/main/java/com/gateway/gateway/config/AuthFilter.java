package com.gateway.gateway.config;

import com.gateway.gateway.beans.AuthTokenModel;
import com.gateway.gateway.service.AuthService;
import io.netty.util.internal.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilterConfig> {

    @Value(("${uri.auth-service}"))
    private String authServiceUrl;

    private static Logger log = LoggerFactory.getLogger(AuthFilter.class);

    @Override
    public GatewayFilter apply(final AuthFilterConfig config) {
        return (exchange, chain) -> {
            final ServerHttpRequest request = exchange.getRequest();

            final boolean authorization = request.getHeaders().containsKey("Authorization");
            log.debug("authorization " + authorization);

            final  String authorizationHeader = request.getHeaders().get("Authorization").get(0);

            try {
                final String newToken = getAuthorizationToken(authorizationHeader);

                final ServerHttpRequest modifiedRequest = exchange.getRequest().mutate()
                        .header("Authorization", newToken).build();
                return chain.filter(exchange.mutate().request(modifiedRequest).build());
            } catch (Exception e) {
                log.error(e.getMessage());
                return this.onError(exchange, "Modified Request " + e.getMessage(), HttpStatus.UNAUTHORIZED);
            }
        };
    }

    private Mono<Void> onError(final ServerWebExchange exchange, final String err, final HttpStatus httpStatus) {
        log.error("Gateway Auth Error {}", err);
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }

    HttpClient client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).build();

    private String getAuthorizationToken(final String token) throws InterruptedException {
        HttpRequest request = HttpRequest.newBuilder(URI.create(authServiceUrl+"/api/v1/auth/validate")).setHeader("content-type", "application/json")
                .header("Authorization", token).build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String newToken = response.body();

            if(StringUtil.isNullOrEmpty(newToken)) {
                    return newToken;
            }
        } catch (IOException |InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

}
