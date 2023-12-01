package com.load.balancer.controller;

import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.load.balancer.model.MovieFilePath;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
public class MainController {

	private final WebClient.Builder loadBalancedWebClientBuilder;
	private final ReactorLoadBalancerExchangeFilterFunction lbFunction;

	public MainController(WebClient.Builder webClientBuilder, ReactorLoadBalancerExchangeFilterFunction lbFunction) {
		this.loadBalancedWebClientBuilder = webClientBuilder;
		this.lbFunction = lbFunction;
	}

	@GetMapping(path = "/getlist")
	public Mono<String> getList() {
		log.info("New Request to getList() ");
		String temp = "Temp response from Main Controller";
		return WebClient.builder().filter(lbFunction).build().get().uri("http://localhost/getlist").retrieve()
				.bodyToMono(String.class);
	}

	@GetMapping(path = "/browse/list")
	public Mono<String> getMovieList() {
		log.info("Request to fetch movie list");
		return WebClient.builder().filter(lbFunction).build().get().uri("http://localhost/movie/list").retrieve()
				.bodyToMono(String.class);
	}

	@PostMapping(path = "/browse/movie/poster", produces = { MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE })
	public Mono<byte[]> getPoster(@RequestBody MovieFilePath body) {
		log.info("Request to fetch movie poster");
		return WebClient.builder().filter(lbFunction).build().post().uri("http://localhost/movie/img")
				.accept(MediaType.IMAGE_JPEG, MediaType.IMAGE_PNG)
			    .contentType(MediaType.APPLICATION_JSON)
				.body(Mono.just(body), MovieFilePath.class)
				.retrieve()
				.bodyToMono(byte[].class);
	}
}
