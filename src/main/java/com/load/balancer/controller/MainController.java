package com.load.balancer.controller;

import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
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
	
	@GetMapping(path = "/hello")
	public String getGreeting() {
		log.info("New Request ");
		String temp = "Temp response from Main Controller";
		return temp;
	}

	@GetMapping(path = "/browse/list")
	public Mono<String> getMovieList() {
		log.info("Request to fetch movie list");
		return WebClient.builder().filter(lbFunction).build().get().uri("http://localhost/movie/list").retrieve()
				.bodyToMono(String.class);
	}

	@GetMapping(path = "/browse/movie/poster", produces = { MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE })
	public Mono<byte[]> getPoster(@RequestParam String name) {
		log.info("Request to fetch movie poster");
		return WebClient.builder().filter(lbFunction).build().get().uri("http://localhost/movie/img?name="+name)
				.accept(MediaType.IMAGE_JPEG, MediaType.IMAGE_PNG)
				.retrieve()
				.bodyToMono(byte[].class);
	}

	@GetMapping(path = "/browse/movie/watch", produces = "video/mp4")
	public Mono<InputStreamResource> getMovie(@RequestParam String name) {
		log.info("Request to fetch movie video");
		return WebClient.builder().filter(lbFunction).build().get().uri("http://localhost/movie/video?name="+name)
//				.accept(MediaType.APPLICATION_OCTET_STREAM)
				.retrieve()
				.bodyToMono(InputStreamResource.class);
	}

}
