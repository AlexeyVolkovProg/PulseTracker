package org.example.commands.info;

import lombok.Getter;

@Getter
public enum CommandInfo {

    START("/start", "Запуск бота"),
    HELP("/help", "Показать список доступных команд с их описанием"),
    TRACK("/track", "<ссылка на ресурс без скобок> Начать отслеживать ресурс, находящийся по указанной ссылке"),
    UN_TRACK("/untrack", "<ссылка на ресурс без скобок> Прекратить отслеживать ресурс, находящийся по указанной ссылке"),
    LIST("/list", "Показывает список отслеживаемых вами ресурсов");


    private final String commandName;
    private final String description;

    CommandInfo(String commandName, String description) {
        this.commandName = commandName;
        this.description = description;
    }
}
