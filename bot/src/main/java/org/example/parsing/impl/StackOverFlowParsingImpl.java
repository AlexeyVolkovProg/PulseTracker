package org.example.parsing.impl;

import lombok.Getter;
import org.example.parsing.LinkParsingService;
import org.example.parsing.info.HostInfo;
import org.springframework.stereotype.Component;

@Component
@Getter
public class StackOverFlowParsingImpl implements LinkParsingService {

    private final HostInfo StackOverFlowPattern = HostInfo.STACK_OVER_FLOW;

    @Override
    public Boolean checkFormat(String link) {
        return matchFormat(StackOverFlowPattern.getLinkPattern(), link);
    }

    @Override
    public HostInfo getHostInfo() {
        return StackOverFlowPattern;
    }


}
