package org.example.client.stackoverflow;

import org.example.client.stackoverflow.dto.AnswersResponse;
import org.example.client.stackoverflow.dto.CommentsResponse;
import org.example.client.stackoverflow.dto.RelatedAnswersResponse;

public interface StackOverFlowClient {
    AnswersResponse fetchListAnswers(String questionId);

    CommentsResponse fetchListComments(String questionId);

    RelatedAnswersResponse fetchListRelatedAnswers(String questionId);

}
