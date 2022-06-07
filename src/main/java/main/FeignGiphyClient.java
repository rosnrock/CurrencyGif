package main;

import main.model.feigngif.Data;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "feign-giphy-client", url = "${GIPHY_API_URL}")
public interface FeignGiphyClient {

    @RequestMapping("/random?api_key=${APP_ID_GIPHY}&tag={tag}")
    Data getGif(@PathVariable String tag);
}
