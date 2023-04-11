import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Emoji {
    private String name;
    private String category;
    private String group;
    @JsonProperty("htmlCode")
    private String[] htmlCodes;
    private String[] unicode;
}

