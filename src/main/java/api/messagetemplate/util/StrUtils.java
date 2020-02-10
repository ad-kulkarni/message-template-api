package api.messagetemplate.util;

import java.util.regex.Pattern;

public class StrUtils {

    public static final String WORD_MATCH_REGEX = "\\b";
    public static final String TOKEN = "$";
    public static final String TOKEN_REGEX = "\\" + TOKEN;

    public static boolean isTokenWordPresent(String templateText, String key) {
        return Pattern.compile(StrUtils.TOKEN_REGEX + StrUtils.WORD_MATCH_REGEX + key + StrUtils.WORD_MATCH_REGEX).matcher(templateText).find();
    }

    public static String getEscapedTokenWord(String key) {
        return Pattern.quote(TOKEN + key);
    }
}
