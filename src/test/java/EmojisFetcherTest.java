import org.junit.Test;
import org.mockito.Mockito;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class EmojisFetcherTest {
    private static final String MOCKED_JSON_RESPONSE = "{\"name\":\"happy\",\"category\":\"Smileys\",\"group\":\"face-positive\",\"htmlCode\":[\"&#x1f601;\",\"&#x1f603;\",\"&#x1f604;\"]}";
    private static final String NETWORK_FAILURE_EXCEPTION_MESSAGE = "Network failure";

    @Test
    public void shouldFetchEmojisSuccessfully() throws Exception {
        // Given
        HttpClient client = Mockito.mock(HttpClient.class);
        HttpRequest request = Mockito.mock(HttpRequest.class);
        HttpResponse<String> response = Mockito.mock(HttpResponse.class);

        Mockito.when(client.send(request, HttpResponse.BodyHandlers.ofString())).thenReturn(response);
        Mockito.when(response.body()).thenReturn(MOCKED_JSON_RESPONSE);

        EmojiParser parser = Mockito.mock(EmojiParser.class);
        List<String> mockedEmojis = Arrays.asList("&#x1f601;", "&#x1f603;", "&#x1f604;");
        Mockito.when(parser.parseEmojis(MOCKED_JSON_RESPONSE)).thenReturn(mockedEmojis);

        EmojisFetcher fetcher = new EmojisFetcher(client, request, parser);

        // When
        List<String> result = fetcher.fetchEmojis(3);

        // Then
        List<String> expected = List.of("&#x1f601;", "&#x1f603;", "&#x1f604;");

        assertEquals(expected, result);
    }

    @Test
    public void shouldReturnFallbackEmojisOnNetworkFailure() throws Exception {
        // Given
        HttpClient client = Mockito.mock(HttpClient.class);
        HttpRequest request = Mockito.mock(HttpRequest.class);
        EmojiParser parser = Mockito.mock(EmojiParser.class);
        EmojisFetcher fetcher = new EmojisFetcher(client, request, parser);

        Mockito.when(client.send(request, HttpResponse.BodyHandlers.ofString())).thenThrow(new RuntimeException(NETWORK_FAILURE_EXCEPTION_MESSAGE));

        // When
        List<String> result = fetcher.fetchEmojis(3);

        // Then
        List<String> expected = List.of("\uD83D\uDE22", "\uD83D\uDE22", "\uD83D\uDE22");
        assertEquals(expected, result);
    }
}
