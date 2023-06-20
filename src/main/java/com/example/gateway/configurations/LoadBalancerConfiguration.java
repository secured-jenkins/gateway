package com.example.gateway.configurations;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class LoadBalancerConfiguration {
//	@Bean
//	public ServiceInstanceListSupplier discoveryClientServiceInstanceListSupplier(
//			ConfigurableApplicationContext context) {
//		System.out.println("Configuring Load balancer to prefer same instance");
//		return ServiceInstanceListSupplier.builder().withBlockingDiscoveryClient().withSameInstancePreference()
//				.build(context);
//	}
	@Bean
    @LoadBalanced
    WebClient.Builder builder() {
        return WebClient.builder();
    }
    
    @Bean
    WebClient webClient(WebClient.Builder builder) {
        return builder.build();
    }
}
