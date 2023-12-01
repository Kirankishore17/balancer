package com.load.balancer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class BalancerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BalancerApplication.class, args);
	}

//	private final WebClient.Builder loadBalancedWebClientBuilder;
//	private final ReactorLoadBalancerExchangeFilterFunction lbFunction;
//
//	public BalancerApplication(WebClient.Builder webClientBuilder,
//	      ReactorLoadBalancerExchangeFilterFunction lbFunction) {
//	    this.loadBalancedWebClientBuilder = webClientBuilder;
//	    this.lbFunction = lbFunction;
//	  }

}
