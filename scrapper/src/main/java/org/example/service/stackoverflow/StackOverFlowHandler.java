package org.example.service.stackoverflow;


import org.example.client.stackoverflow.impl.StackOverFlowClientImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StackOverFlowHandler {

    private final StackOverFlowClientImpl stackOverFlowClient;

    public StackOverFlowHandler(StackOverFlowClientImpl stackOverFlowClient) {
        this.stackOverFlowClient = stackOverFlowClient;
    }

    public List<String> handleQuestionAnswers() {
        //пока что заглушка на конкретный запрос, далее номера вопросов будут приходить с модуля Bot
        return stackOverFlowClient.fetchListAnswers("59029274")
                .getAnswerResponseList().stream()
                .map(Object::toString)
                .collect(Collectors.toList());
    }

    public List<String> handleQuestionsComments() {
        //заглушка
        return stackOverFlowClient.fetchListComments("59029274")
                .getCommentResponseList().stream()
                .map(Object::toString)
                .collect(Collectors.toList());
    }


    public List<String> handleQuestionRelatedAnswerResponse() {
        //заглушка
        return stackOverFlowClient.fetchListRelatedAnswers("59029274")
                .getRelatedQuestions()
                .stream()
                .map(Object::toString)
                .collect(Collectors.toList());
    }


}
