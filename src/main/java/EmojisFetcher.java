import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class EmojisFetcher {
    private static final String FALLBACK_EMOJI = "\uD83D\uDE22"; // sad face emoji

    private final HttpClient client;
    private final HttpRequest request;
    private final EmojiParser parser;

    public EmojisFetcher(HttpClient httpClient, HttpRequest httpRequest, EmojiParser emojiParser) {
        this.client = httpClient;
        this.request = httpRequest;
        this.parser = emojiParser;
    }

    public List<String> fetchEmojis(int emojisCount) {
        List<String> emojis = new ArrayList<>();

        // Fetch random emojis from the API
        while (emojis.size() < emojisCount) {
            try {
                // Send the HTTP request and receive the response
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                // Use the parser to extract the emojis from the JSON response
                String json = response.body();
                List<String> responseEmojis = parser.parseEmojis(json);
                for (String e : responseEmojis) {
                    if (emojis.size() < emojisCount) {
                        emojis.add(e);
                    }
                }
            } catch (Exception e) {
                // If there is an exception while sending the HTTP request or receiving the response,use the fallback emoji
                emojis.add(FALLBACK_EMOJI);
            }
        }
        return emojis;
    }
}
