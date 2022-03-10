package devsearch.gateway.security;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
public class CustomCorsConfiguration extends org.springframework.web.cors.CorsConfiguration {

    @Bean
    public CorsWebFilter corsFilter() {
	org.springframework.web.cors.CorsConfiguration corsConfiguration = new org.springframework.web.cors.CorsConfiguration();
	corsConfiguration.setAllowCredentials(true);
	corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://127.0.0.1:3000"));
	corsConfiguration.setAllowedMethods(Arrays.asList("*"));

//	corsConfiguration.addAllowedHeader("origin");
//	corsConfiguration.addAllowedHeader("content-type");
//	corsConfiguration.addAllowedHeader("accept");
//	corsConfiguration.addAllowedHeader("authorization");
//	corsConfiguration.addAllowedHeader("cookie");

	corsConfiguration.setAllowedHeaders(Arrays.asList("*"));
	corsConfiguration.addExposedHeader(HttpHeaders.AUTHORIZATION);
	// TODO Add constants
	corsConfiguration.addExposedHeader("UserId");
	UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	source.registerCorsConfiguration("/**", corsConfiguration);
	return new CorsWebFilter(source);
    }
}
