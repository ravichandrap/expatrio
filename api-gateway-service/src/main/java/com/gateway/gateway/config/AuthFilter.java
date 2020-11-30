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
import org.springframework.context.annotation.Lazy;
import org.springframework.cloud.gateway.support.Configurable;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
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

            AuthTokenModel authTokenModel = new AuthTokenModel();
            final  String authorizationHeader = request.getHeaders().get("Authorization").get(0);
            String jwt = null;

            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                authTokenModel.setToken(authorizationHeader.substring(7));
                authTokenModel.setType("jwt");
            }

            try {
                final String newToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyYy5yZWRkeUBvdXRsb29rLmNvbSIsImV4cCI6MTYwNjcxMDA3MCwiaWF0IjoxNjA2Njc0MDcwfQ.eSqqswGnVk5_4cNl80iG3gElAhIf-CcDR6BoO4trRzg";
//                        getAuthorizationToken(authTokenModel.getToken());
                System.out.println("newToken:::: "+newToken);
                final ServerHttpRequest modifiedRequest = exchange.getRequest().mutate()
                        .header("Authorization", "Bearer " + newToken).build();

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
        HttpRequest request = HttpRequest.newBuilder(URI.create(authServiceUrl))
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
