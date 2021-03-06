package com.example.feignrestconsumer;

import com.example.restproducer.controller.GreetingController;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

@Component("greetingClient")
@FeignClient(name = "rest-producer", url = "http://localhost:9090", fallback = GreetingClient.GreetingClientFallback.class)
public interface GreetingClient extends GreetingController {
    @Component
    public static class GreetingClientFallback implements GreetingClient {
        @Override
        public String greeting(@PathVariable("username") String username) {
            return "Hello User!";
        }
    }
}