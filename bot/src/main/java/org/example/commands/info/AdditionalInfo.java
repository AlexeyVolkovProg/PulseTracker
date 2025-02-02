package org.example.commands.info;

import lombok.Getter;

@Getter
public enum AdditionalInfo {
    UNKNOWN_COMMAND("Была введена неизвестная команда"),
    INCORRECT_ARGS("Ошибка кол-ва аргументов команды. Корректное кол-во:"),
    CHECK_LINK("Производим проверку ссылки"),
    LINK_ADD_ERROR("Не удалось добавить ссылку, возможно ссылки  " +
            "данного ресурса не поддерживаются в данный момент или была допущена ошибка в URL"),
    LINK_REMOVE_ERROR("Не удалось удалить ссылку, скорее всего она не отслеживалась вами ранее"),
    EMPTY_LINK_LIST("Список отслеживаемых вами ссылок на данный момент пуст"),
    START_MESSAGE("""
            Приветствую.\s
            Данный бот поможет тебе получать уведомления об обновлениях в репозиториях Github, а также об обновлениях обсуждений вопросов на SOF.
            Для просмотра доступных команд напиши /help"""),
    ADD_LINK_MESSAGE("Вы успешно добавили новую ссылку для отслеживания. Распознанный ресурс: "),
    REMOVE_LINK_MESSAGE("Вы успешно удалили ссылку, связанную с ресурсом "),
    REPEAT_LINK_ERROR("Данная ссылку уже отслеживается для вас");

    private final String message;

    AdditionalInfo(String message) {
        this.message = message;
    }
}
