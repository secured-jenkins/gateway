package com.example.gateway;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.gateway.configurations.LoadBalancerConfiguration;

@RestController
@EnableDiscoveryClient
@SpringBootApplication
@LoadBalancerClient(name = "layeredApp",configuration=LoadBalancerConfiguration.class)

public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}
	
	@GetMapping(path="/")
	public String testing() {
		return "Gateway is running";
	}
	@Autowired
    private DiscoveryClient discoveryClient;
	@GetMapping(path="/getServices")
	public List<String> services() {
		return this.discoveryClient.getServices();
	}
	@GetMapping(path="/getService/{service}")
	public List<ServiceInstance> fun(@PathVariable("service") String service) {
		List<ServiceInstance> services =  this.discoveryClient.getInstances(service);
		System.out.println(services.get(0).getUri());
		System.out.println(services.get(0).getMetadata());
		System.out.println(services.get(0).getScheme());
		System.out.println(services.get(0).toString());
		return services;
		
	}

}
