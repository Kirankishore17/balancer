package com.load.balancer;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class BalancerComponent {

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*").allowedMethods("*").allowedHeaders("*");
			}
		};
	}

//	@Bean
//	CorsConfigurationSource corsConfigurationSource() {
//		CorsConfiguration configuration = new CorsConfiguration();
//		configuration.setAllowedOrigins(Arrays.asList("*"));
//		configuration.setAllowedMethods(Arrays.asList("GET","POST"));
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		source.registerCorsConfiguration("/**", configuration);
//		return source;
//	}


//	@Bean
//	JettyServerCustomizer disableSniHostCheck() {
//		return (server) -> {
//			for (Connector connector : server.getConnectors()) {
//				if (connector instanceof ServerConnector serverConnector) {
//					HttpConnectionFactory connectionFactory = serverConnector
//							.getConnectionFactory(HttpConnectionFactory.class);
//					if (connectionFactory != null) {
//						SecureRequestCustomizer secureRequestCustomizer = connectionFactory.getHttpConfiguration()
//								.getCustomizer(SecureRequestCustomizer.class);
//						if (secureRequestCustomizer != null) {
//							secureRequestCustomizer.setSniHostCheck(false);
//						}
//					}
//				}
//			}
//		};
//	}

}
