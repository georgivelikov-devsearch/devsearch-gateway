package devsearch.gateway.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class AuthorizationHeaderFilter extends AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config> {

    @Autowired
    Environment env;

    public AuthorizationHeaderFilter() {
	super(Config.class);
    }

    public static class Config {

    }

    @Override
    public GatewayFilter apply(Config config) {
	return (exchange, chain) -> {
	    ServerHttpRequest request = exchange.getRequest();
	    if (!request.getHeaders().containsKey("Authorization")) {
		return onError(exchange, "No authorization header", HttpStatus.UNAUTHORIZED);

	    }

	    return chain.filter(exchange);
	};
    }

    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
	ServerHttpResponse response = exchange.getResponse();
	response.setStatusCode(httpStatus);

	return response.setComplete();
    }
}
