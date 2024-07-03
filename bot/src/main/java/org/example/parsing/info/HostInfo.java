package org.example.parsing.info;


import lombok.Getter;

@Getter
public enum HostInfo {

    GITHUB("github.com", "^(https://github\\.com/)([\\w-]+)/([\\w-]+)(/)?$"),
    STACK_OVER_FLOW("stackoverflow.com",
            "https:\\/\\/stackoverflow\\.com\\/questions\\/\\d+\\/[^\\/?#]+");

    final String resourceNameURL;
    final String linkPattern;

    HostInfo(String resourceNameURL, String linkPattern){
        this.resourceNameURL = resourceNameURL;
        this.linkPattern = linkPattern;
    }
}
