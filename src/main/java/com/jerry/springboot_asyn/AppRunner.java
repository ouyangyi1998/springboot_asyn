package com.jerry.springboot_asyn;

import com.jerry.springboot_asyn.entity.User;
import com.jerry.springboot_asyn.service.GitHubLookupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

@Component
@Slf4j
public class AppRunner implements CommandLineRunner {
    private final GitHubLookupService gitHubLookupService;

    public AppRunner(GitHubLookupService gitHubLookupService) {
        this.gitHubLookupService = gitHubLookupService;
    }

    @Override
    public void run(String... args) throws Exception {
        long start=System.currentTimeMillis();
        Future<User> page1=gitHubLookupService.findUser("PivotalSoftware");
        Future<User> page2=gitHubLookupService.findUser("CloudFoundry");
        Future<User> page3=gitHubLookupService.findUser("Spring-Projects");

        while (!(page1.isDone()&&page2.isDone()&&page3.isDone()))
        {
            Thread.sleep(10);
        }
        log.info("elapsed time"+(System.currentTimeMillis()-start));
        log.info("-->"+page1.get());
        log.info("-->"+page2.get());
        log.info("-->"+page3.get());
    }
}
