package org.example.controller;

import org.example.model.Chat;
import org.example.scrapperrestapi.api.ScrapperApi;
import org.example.scrapperrestapi.dto.request.AddLinkRequest;
import org.example.scrapperrestapi.dto.request.RemoveLinkRequest;
import org.example.scrapperrestapi.dto.response.LinkResponse;
import org.example.scrapperrestapi.dto.response.ListLinksResponse;
import org.example.service.jpa.LinkServiceImpl;
import org.example.service.jpa.TgChatServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/scrapper-api")
public class ScrapperRestController implements ScrapperApi {

    private final LinkServiceImpl linkService;

    private final TgChatServiceImpl tgChatService;

    public ScrapperRestController(LinkServiceImpl linkService, TgChatServiceImpl tgChatService) {
        this.linkService = linkService;
        this.tgChatService = tgChatService;
    }


    @Override
    @PostMapping("/tg-chat/{id}")
    public ResponseEntity<?> registerChat(@PathVariable("id") Long id) {
        tgChatService.register(id);
        return ResponseEntity.ok().build();
    }

    @Override
    @DeleteMapping("/tg-chat/{id}")
    public ResponseEntity<?> deleteChat(@PathVariable("id") Long id) {
        tgChatService.unregister(id);
        return ResponseEntity.ok().build();
    }

    @Override
    @GetMapping("/links")
    public ResponseEntity<ListLinksResponse> getTrackLinks(@RequestParam("Tg-Chat-Id") Long tgChatId) {
        return ResponseEntity.ok(linkService.listAll(tgChatId));
    }

    @Override
    @PostMapping("/links")
    public ResponseEntity<LinkResponse> addTrackLink(
            @RequestParam("Tg-Chat-Id") Long tgChatId,
            @RequestBody AddLinkRequest addLink) {
        return ResponseEntity.ok(tgChatService.addLinkToChat(tgChatId, addLink.getLink()));
    }

    @Override
    @DeleteMapping("/links")
    public ResponseEntity<LinkResponse> deleteTrackLink(
            @RequestParam("Tg-Chat-Id") Long tgChatId,
            @RequestBody RemoveLinkRequest removeLinkRequest) {
        return ResponseEntity.ok(linkService.removeLinkFromChat(tgChatId, removeLinkRequest.getLink()));
    }
}
