package devsearch.gateway.security;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
public class CustomCorsConfiguration extends org.springframework.web.cors.CorsConfiguration {

    @Bean
    public CorsWebFilter corsFilter() {
	org.springframework.web.cors.CorsConfiguration corsConfiguration = new org.springframework.web.cors.CorsConfiguration();
	corsConfiguration.setAllowCredentials(true);
	corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
	corsConfiguration.setAllowedMethods(Arrays.asList("*"));
	// corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT",
	// "DELETE", "OPTIONS", "HEAD"));

	/*
	 * corsConfiguration.addAllowedHeader("origin");
	 * corsConfiguration.addAllowedHeader("content-type");
	 * corsConfiguration.addAllowedHeader("accept");
	 * corsConfiguration.addAllowedHeader("authorization");
	 * corsConfiguration.addAllowedHeader("cookie");
	 */
	corsConfiguration.setAllowedHeaders(Arrays.asList("*"));
	UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	source.registerCorsConfiguration("/**", corsConfiguration);
	return new CorsWebFilter(source);
    }
}
