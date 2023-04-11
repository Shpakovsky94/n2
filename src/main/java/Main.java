import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.List;

public class Main {
    public static final String URL = "https://emojihub.yurace.pro/api/random";

    public static void main(String[] args) {
        // Create the HTTP client and request
        HttpClient client = HttpUtil.createHttpClient();
        HttpRequest request = HttpUtil.createHttpRequest(URL);

        // Create the parser to extract the emojis from the JSON response
        EmojiParser parser = new EmojiParser();

        EmojisFetcher emojisFetcher = new EmojisFetcher(client, request, parser);

        List<String> emojis = emojisFetcher.fetchEmojis(3);

        // Print the resulting emojis
        System.out.println("Random Emojis: " + String.join(" ", emojis));
    }
}
