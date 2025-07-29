package com.nielsen.ess.utility;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import jakarta.servlet.http.HttpServletRequest;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  @Primary
  public WebClient.Builder webClientBuilder() {
    System.out.println("Using WebClient from builder: ");
    return WebClient.builder().filter(filterFunction());
  }

  private ExchangeFilterFunction filterFunction() {
    return ExchangeFilterFunction.ofRequestProcessor(req -> {
      RequestAttributes attribs = RequestContextHolder.getRequestAttributes();
      String token = null;
      if (attribs instanceof ServletRequestAttributes) {
        HttpServletRequest request =
            (HttpServletRequest) ((ServletRequestAttributes) attribs).getRequest();
        token = request.getHeader("X-NIQ-TOKEN");
      }
      System.out.println("from filter function " + token);
      if (token != null) {
        final String tokenToBePassedToMCPclient = token;
        ClientRequest modifiedClientRequest = ClientRequest.from(req).headers(h -> {
          h.add("X-NIQ-TOKEN", tokenToBePassedToMCPclient);
        }).build();

        return Mono.just(modifiedClientRequest);
      }
      System.out.println("user id is null from filter function");
      return Mono.just(req);

    });

  }
}
