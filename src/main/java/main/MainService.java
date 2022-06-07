package main;

import main.model.feigncurr.Currency;
import main.model.feigngif.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Locale;

@SpringBootApplication
@RestController
@EnableFeignClients
public class MainService {

    @Autowired
    private FeignCurrencyClient feignCurrencyClient;
    @Autowired
    private FeignGiphyClient feignGiphyClient;

    public static void main(String[] args) throws IOException {
        SpringApplication.run(MainService.class, args);
    }

    @RequestMapping("/getCurrency&symbol={symbol}")
    public String getCurrency(@PathVariable String symbol) {

        String gifUrl = "";
        String hint;
        symbol = symbol.toUpperCase(Locale.ROOT);

        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
        double currencyToday = getCurrencyValue(feignCurrencyClient.getCurrency(today.toString(), symbol), symbol);
        double currencyYesterday = getCurrencyValue(feignCurrencyClient.getCurrency(yesterday.toString(), symbol), symbol);
        double currencyDiffer = currencyToday - currencyYesterday;

        if (currencyToday >= currencyYesterday) {
            gifUrl = getGifUrl(feignGiphyClient.getGif("rich"));
            hint = symbol + " укрепляется к USD: " + currencyDiffer;
        } else {
            gifUrl = getGifUrl(feignGiphyClient.getGif("broke"));
            hint = symbol + " ослабевает к USD: " + currencyDiffer;
        }

        return "<!DOCTYPE html> <html> <head> <title>Currency Rate</title> </head> <body> " +
                "<img src=\"" + gifUrl + "\" /> " +
                "<span> " + hint + " </span> " +
                "</body> </html>";
    }

    public double getCurrencyValue(Currency currency, String symbol) {
        return (double) currency.getRates().getAdditionalProperties().get(symbol);
    }

    public String getGifUrl(Data data) {
        return data.getGif().getImages().getDownsized().getUrl();
    }
}
