package com.jerry.springboot_asyn.service;

import com.jerry.springboot_asyn.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Future;

@Service
@Slf4j
public class GitHubLookupService {
    private final RestTemplate restTemplate;

    public GitHubLookupService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }
    @Async
    public Future<User> findUser(String user) throws Exception
    {
        log.info("looking up"+user);
        String url=String.format("https://api.github.com/users/%s",user);
        User res=restTemplate.getForObject(url,User.class);
        Thread.sleep(1000);
        return new AsyncResult<>(res);
    }
}
