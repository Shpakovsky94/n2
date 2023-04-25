import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.List;

public class Main {
    public static final String CORRECT_URL = "https://emojihub.yurace.pro/api/random";
    public static final String FAIL_URL = "https://some-fail.com/api";

    public static void main(String[] args) {
        String URL = args.length > 0 && args[0].equals("successful") ? CORRECT_URL : FAIL_URL;

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
