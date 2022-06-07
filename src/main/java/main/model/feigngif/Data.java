package main.model.feigngif;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "data"
})
public class Data {

    @JsonProperty("data")
    private Gif gif;

    @JsonProperty("data")
    public Gif getGif() {
        return gif;
    }

    @JsonProperty("data")
    public void setGif(Gif gif) {
        this.gif = gif;
    }
}