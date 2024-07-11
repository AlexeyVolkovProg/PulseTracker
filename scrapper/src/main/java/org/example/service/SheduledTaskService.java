package org.example.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SheduledTaskService {

    private final GithubRepoHandler githubRepoHandler;

    private final StackOverFlowHandler stackOverFlowHandler;

    @Value("${scheduling.fixed-delay}")
    private long fixedDelay;

    public SheduledTaskService(GithubRepoHandler githubRepoHandler, StackOverFlowHandler stackOverFlowHandler) {
        this.githubRepoHandler = githubRepoHandler;
        this.stackOverFlowHandler = stackOverFlowHandler;
    }

    //пока что пример метода, который будет работать как фоновая задача
    @Scheduled(fixedDelay = 5000)
    public void updateData(){
//        githubRepoHandler.handleEventsRepoInfo().forEach(System.out::println);
        stackOverFlowHandler.handleQuestionAnswers().forEach(System.out::println);
    }

}
