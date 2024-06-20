package org.example.database;


import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Класс заглушка, для хранения коллекции отлеживаемых ссылок
 */
@Component
@Getter
@Setter
public class TrackLinkDB {

    private Map<Long, Set<String>> trackLinks = new HashMap<>();


    /**
     * Добавление для конкретного пользователя
     * @param idPerson id пользователя
     * @param link ссылка, которую хотим добавить
     */
    public void addLink(Long idPerson, String link){
        if (trackLinks.containsKey(idPerson)){
            trackLinks.get(idPerson).add(link);
        }else{
            Set<String> newSetLinks = new HashSet<>();
            newSetLinks.add(link);
            trackLinks.put(idPerson, newSetLinks);
        }
    }


    /**
     * Удаление для конкретного пользователя
     * @param idPerson id пользователя
     * @param link ссылка, которую хотим удалить
     */
    public void removeLink(Long idPerson, String link){
        if (trackLinks.containsKey(idPerson) && trackLinks.get(idPerson).contains(link)){
            trackLinks.get(idPerson).remove(link);
        }
    }

    /**
     * Получаем множество всех ссылок, отслеживаемых для конкретного пользователя
     * @param idPerson id пользователя
     * @return null в случае если ничего не отслеживалось
     */
    public Set<String> getLinksSet(Long idPerson){
        if (trackLinks.containsKey(idPerson) && !trackLinks.get(idPerson).isEmpty()){
            return trackLinks.get(idPerson);
        }else{
            return null;
        }
    }


}
