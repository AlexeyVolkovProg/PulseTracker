package org.example.client.stackoverflow;

import org.example.client.stackoverflow.dto.AnswersResponse;
import org.example.client.stackoverflow.dto.CommentsResponse;
import org.example.client.stackoverflow.dto.RelatedAnswersResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;


/**
 * Отвечает за отправку запросов к API StackOverFlow
 */
public interface StackOverFlowQuestionsService {

    @GetExchange("/questions/{id}/answers")
    AnswersResponse getAnswersEvents(@PathVariable("id") String questionId,  @RequestParam(value = "site", defaultValue = "stackoverflow") String site);

    @GetExchange("/questions/{id}/comments")
    CommentsResponse getCommentsEvents(
            @PathVariable("id") String questionId, @RequestParam(value = "site", defaultValue = "stackoverflow") String site
    );

    @GetExchange("/questions/{id}/related")
    RelatedAnswersResponse getRelatedAnswersEvents(
            @PathVariable("id") String questionId, @RequestParam(value = "site", defaultValue = "stackoverflow") String site);


}
