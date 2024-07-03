package org.example.parsing.impl;

import lombok.Getter;
import org.example.parsing.LinkParsingService;
import org.example.parsing.info.HostInfo;
import org.springframework.stereotype.Component;


@Component
@Getter
public class GithubParsingImpl implements LinkParsingService {

    private final HostInfo GITHUB_PATTERN = HostInfo.GITHUB;
    @Override
    public Boolean checkFormat(String link) {
        return matchFormat(GITHUB_PATTERN.getLinkPattern(), link);
    }

    @Override
    public HostInfo getHostInfo() {
        return GITHUB_PATTERN;
    }
}
