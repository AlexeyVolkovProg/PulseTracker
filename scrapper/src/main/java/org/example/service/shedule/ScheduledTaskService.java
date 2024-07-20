package org.example.service.shedule;


import org.example.service.github.GithubRepoHandler;
import org.example.service.stackoverflow.StackOverFlowHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledTaskService {

    private final GithubRepoHandler githubRepoHandler;

    private final StackOverFlowHandler stackOverFlowHandler;

    @Value("${scheduling.fixed-delay}")
    private long fixedDelay;

    public ScheduledTaskService(GithubRepoHandler githubRepoHandler, StackOverFlowHandler stackOverFlowHandler) {
        this.githubRepoHandler = githubRepoHandler;
        this.stackOverFlowHandler = stackOverFlowHandler;
    }

    //пример метода, который будет работать как фоновая задача
//    @Scheduled(fixedDelay = 5000)
//    public void updateData(){
//        //githubRepoHandler.handleEventsRepoInfo().forEach(System.out::println);
//        stackOverFlowHandler.handleQuestionAnswers().forEach(System.out::println);
//    }

}
