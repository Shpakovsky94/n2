
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class EmojiParser {
    private ObjectMapper objectMapper;

    public EmojiParser() {
        objectMapper = new ObjectMapper();
    }

    public List<String> parseEmojis(String json) {
        try {
            Emoji emoji = objectMapper.readValue(json, Emoji.class);
            return Arrays.asList(emoji.getHtmlCodes());
        } catch (IOException e) {
            return Collections.singletonList("\uD83D\uDE22"); // fallback sad face emoji
        }
    }
}
