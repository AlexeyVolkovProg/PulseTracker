package org.example.service;

import org.example.client.ScrapperClient;
import org.example.dto.ResponseResult;
import org.example.scrapperrestapi.dto.error.ApiErrorResponse;
import org.example.scrapperrestapi.dto.request.AddLinkRequest;
import org.example.scrapperrestapi.dto.request.RemoveLinkRequest;
import org.example.scrapperrestapi.dto.response.LinkResponse;
import org.example.scrapperrestapi.dto.response.ListLinksResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ScrapperService {

    private final ScrapperClient scrapperClient;

    public ScrapperService(ScrapperClient scrapperClient) {
        this.scrapperClient = scrapperClient;
    }


    public ResponseResult registerChat(Long chatId){
        ResponseEntity<?> response = scrapperClient.registerChat(chatId);
        return handleResponse(response);
    }

    public ResponseResult deleteChat(Long chatId) {
        ResponseEntity<?> response = scrapperClient.deleteChat(chatId);
        return handleResponse(response);
    }


    public ResponseResult addLinkToChat(Long tgChatId, String link){
        AddLinkRequest addLinkRequest = new AddLinkRequest(link);
        System.out.println(addLinkRequest);
        ResponseEntity<LinkResponse> response = scrapperClient.addTrackLink(tgChatId, addLinkRequest);
        return handleResponse(response);
    }


    public ResponseResult removeLinkFromChat(Long tgChatId, String link) {
        RemoveLinkRequest removeLinkRequest = new RemoveLinkRequest(link);
        ResponseEntity<LinkResponse> response = scrapperClient.deleteTrackLink(tgChatId, removeLinkRequest);
        return handleResponse(response);
    }


    public ResponseResult getLinksFromChat(Long tgChatId){
        ResponseEntity<ListLinksResponse> response = scrapperClient.getTrackLinks(tgChatId);
        return handleListLinkResponse(response);
    }


    /**
     * Метод для отслеживания возможных ошибок при взаимодействии с ScrapperApi
     */
    public ResponseResult handleResponse(ResponseEntity<?> response){
        if (response.getStatusCode().is2xxSuccessful()){
            return new ResponseResult(true, null, null);
        }else{
            ApiErrorResponse error = (ApiErrorResponse) response.getBody();
            assert error != null; // todo заменить более надежной проверкой
            return new ResponseResult(false, error.getExceptionName(), error.getDescription());
        }
    }

    public ResponseResult handleListLinkResponse(ResponseEntity<?> response){
        if (response.getStatusCode().is2xxSuccessful()){
            ListLinksResponse listLinksResponse = (ListLinksResponse) response.getBody();
            assert listLinksResponse != null; // todo заменить более надежной
            return new ResponseResult(true, null, null, listLinksResponse.getLinkResponsesList());
        }else{
            ApiErrorResponse error = (ApiErrorResponse) response.getBody();
            assert error != null; // todo заменить более надежной проверкой
            return new ResponseResult(false, error.getExceptionName(), error.getDescription());
        }
    }
















}
