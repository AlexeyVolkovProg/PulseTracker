package org.example.client.stackoverflow.impl;

import org.example.client.stackoverflow.StackOverFlowClient;
import org.example.client.stackoverflow.StackOverFlowQuestionsService;
import org.example.client.stackoverflow.dto.AnswersResponse;
import org.example.client.stackoverflow.dto.CommentsResponse;
import org.example.client.stackoverflow.dto.RelatedAnswersResponse;

public class StackOverFlowClientImpl implements StackOverFlowClient {

    private final StackOverFlowQuestionsService stackOverFlowQuestionsService;

    public StackOverFlowClientImpl(StackOverFlowQuestionsService stackOverFlowQuestionsService) {
        this.stackOverFlowQuestionsService = stackOverFlowQuestionsService;
    }

    @Override
    public AnswersResponse fetchListAnswers(String questionId) {
        return stackOverFlowQuestionsService.getAnswersEvents(questionId, null);
    }

    @Override
    public CommentsResponse fetchListComments(String questionId) {
        return stackOverFlowQuestionsService.getCommentsEvents(questionId, null);
    }

    @Override
    public RelatedAnswersResponse fetchListRelatedAnswers(String questionId) {
        return stackOverFlowQuestionsService.getRelatedAnswersEvents(questionId, null);
    }
}
