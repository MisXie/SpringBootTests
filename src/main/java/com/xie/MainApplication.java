package com.xie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Administrator on 2019-03-18.
 */
@SpringBootApplication
@PropertySource({"file:${user.dir}/config/conf.properties"})
@RestController
public class MainApplication extends SpringBootServletInitializer{

  @Autowired
  Environment environment;

  @Bean
  public RestTemplate restTemplate(){
    return new RestTemplate();
  }

  @Override
  protected SpringApplicationBuilder configure(
          SpringApplicationBuilder builder) {
    return builder.sources(this.getClass());
  }

  @GetMapping("/hello")
  public String hello(){
    return environment.getProperty("user.dir");
  }

  public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication.run(MainApplication.class, args);

  }
}
