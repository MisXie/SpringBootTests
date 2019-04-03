package com.xie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2019-04-03.
 */
@RestController
public class RestTemplateTest {

  @Autowired
  RestTemplate restTemplate;

  @GetMapping("/getuser")
  public String getUser(@PathParam("id") String id, @PathParam("name") String name) {
    System.out.println("response id=" + id + "name=" + name);
    return "xie " + id;
  }

  @GetMapping("/getuser2/{id}/{name}")
  public String getUser2(@PathVariable("id") String id, @PathVariable("name") String cc) {
    System.out.println("response id=" + id + "name=" + cc);
    return "xie " + id;
  }

  @GetMapping("/getorder")
  public String getOrder(@PathParam("id") String id) {
    return "banana " + id;
  }
  @GetMapping("/get2/{id}")
  public void get2(@PathVariable("id") String id) {
    Map<String ,Object> param = new HashMap<>();
    param.put("id",id);
    param.put("name","xie");
    String s = restTemplate.getForObject("http://localhost:8089/getuser?id={id}&name={name}", String.class,param);

  }

  @GetMapping("/get/{id}")
  public void get(@PathVariable("id") String id) {
    ResponseEntity<String> userEntity = restTemplate.getForEntity("http://localhost:8089/getuser?id=" + id, String.class);
    ResponseEntity<String> orderEntity = restTemplate.getForEntity("http://localhost:8089/getorder?id=" + id, String.class);

    String result = userEntity.getBody();
    MediaType mediaType = userEntity.getHeaders().getContentType();
    HttpStatus httpStatus = userEntity.getStatusCode();
  }
}
