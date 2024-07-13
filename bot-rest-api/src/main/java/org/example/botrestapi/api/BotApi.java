package org.example.botrestapi.api;

import org.example.botrestapi.dto.LinkUpdate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface BotApi {
    /**
     * paths:
     *   /updates:
     *     post:
     *       summary: Отправить обновление
     *       requestBody:
     *         content:
     *           application/json:
     *             schema:
     *               $ref: '#/components/schemas/LinkUpdate'
     *         required: true
     *       responses:
     *         '200':
     *           description: Обновление обработано
     *         '400':
     *           description: Некорректные параметры запроса
     *           content:
     *             application/json:
     *               schema:
     *                 $ref: '#/components/schemas/ApiErrorResponse'
     */
    @PostMapping("/updates")
    ResponseEntity<?> sendUpdate(@RequestBody LinkUpdate linkUpdate);
}
