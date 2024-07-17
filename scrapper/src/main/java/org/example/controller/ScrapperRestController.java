package org.example.controller;

import org.example.scrapperrestapi.api.ScrapperApi;
import org.example.scrapperrestapi.dto.request.AddLinkRequest;
import org.example.scrapperrestapi.dto.request.RemoveLinkRequest;
import org.example.scrapperrestapi.dto.response.LinkResponse;
import org.example.scrapperrestapi.dto.response.ListLinksResponse;
import org.example.service.jdbc.LinkServiceImpl;
import org.example.service.jdbc.TgChatServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/scrapper-api")
public class ScrapperRestController implements ScrapperApi {

    private LinkServiceImpl linkService;

    private TgChatServiceImpl tgChatService;

    public ScrapperRestController(LinkServiceImpl linkService, TgChatServiceImpl tgChatService) {
        this.linkService = linkService;
        this.tgChatService = tgChatService;
    }


    @Override
    @PostMapping("/tg-chat/{id}")
    public ResponseEntity<?> registerChat(@PathVariable Integer id) {
        tgChatService.register(id);
        return null;
    }

    @Override
    @DeleteMapping("/tg-chat/{id}")
    public ResponseEntity<?> deleteChat(@PathVariable Integer id) {
        tgChatService.unregister(id);
        return null;
    }

    @Override
    @GetMapping("/links")
    public ResponseEntity<ListLinksResponse> getTrackLinks(@RequestParam("Tg-Chat-Id") Integer tgChatId) {
        //будет реализован при подключении БД
        return null;
    }

    @Override
    @PostMapping("/links")
    public ResponseEntity<LinkResponse> addTrackLink(@RequestParam("Tg-Chat-Id") Integer tgChatId,
                                                     @RequestBody AddLinkRequest addLink) {
        linkService.add(tgChatId, addLink.getLink());
        return null;
    }

    @Override
    @DeleteMapping("/links")
    public ResponseEntity<LinkResponse> deleteTrackLink(@RequestParam("Tg-Chat-Id") Integer tgChatId,
                                                        @RequestBody RemoveLinkRequest removeLinkRequest) {
        linkService.remove(tgChatId, removeLinkRequest.getLink());
        return null;
    }
}
