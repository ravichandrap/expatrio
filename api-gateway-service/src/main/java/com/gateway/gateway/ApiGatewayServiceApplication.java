package com.gateway.gateway;

import com.gateway.gateway.config.AuthFilter;
import com.gateway.gateway.config.AuthFilterConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.util.function.Function;

@SpringBootApplication
public class ApiGatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayServiceApplication.class, args);
	}

	@Value(("${uri.auth-service}"))
	private String authService;

	@Value(("${uri.user-service}"))
	private String userService;

	@Autowired
	AuthFilter authFilter;

	@Bean
	public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
//		return builder.routes()
//				.route(r-> r.path("/api/v1/authenticate/**").uri(authService))
//				.route(r -> r.path("/api/v1/user/**").uri(userService))
//				.build();

		return builder.routes().route(firstServiceRouting()).build();
	}


	private final Function<PredicateSpec, Buildable<Route>> firstServiceRouting() {
		return r -> r.path("/api/v1/login/**").uri(authService);
//				.filters(f -> f.filter(authFilter.apply(new AuthFilterConfig()))).uri(authService);
	}
}
