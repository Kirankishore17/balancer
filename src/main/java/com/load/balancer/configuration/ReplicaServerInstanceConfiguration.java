package com.load.balancer.configuration;

import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReplicaServerInstanceConfiguration {

    @Bean
    ServiceInstanceListSupplier serviceInstanceListSupplier() {
        return new InstanceSupplier("replica-server");
    }

}
