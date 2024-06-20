package org.example.parsing;

import org.example.parsing.info.HostInfo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface LinkParsingService {

    Boolean checkFormat(String link);

    default boolean matchFormat(String pattern, String link){
        Pattern pattern1 = Pattern.compile(pattern);
        Matcher matcher = pattern1.matcher(link);
        return  matcher.matches();
    }

    HostInfo getHostInfo();



}
