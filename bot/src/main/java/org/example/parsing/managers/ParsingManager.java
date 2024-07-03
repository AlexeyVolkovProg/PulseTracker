package org.example.parsing.managers;


import org.example.parsing.LinkParsingService;
import org.example.parsing.info.HostInfo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ParsingManager {

    private final List<LinkParsingService> listOfParsers;

    public ParsingManager(List<LinkParsingService> listServices){
        this.listOfParsers = listServices;
    }

    public HostInfo findResource(String link) {
        for (LinkParsingService linkParser : listOfParsers) {
            if (linkParser.checkFormat(link)) {
                return linkParser.getHostInfo();
            }
        }
        return null;
    }

}
