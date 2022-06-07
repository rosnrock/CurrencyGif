package main;

import main.model.feigncurr.Currency;
import main.model.feigncurr.Rates;
import main.model.feigngif.Data;
import main.model.feigngif.Downsized;
import main.model.feigngif.Gif;
import main.model.feigngif.Images;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainServiceTest {

    private MainService mainService = new MainService();
    private Currency currency = new Currency();
    private Data data = new Data();

    @BeforeEach
    void setUp() {
        Rates rates = new Rates();
        rates.setAdditionalProperty("EUR", 0.9);
        currency.setRates(rates);

        Downsized downsized = new Downsized();
        downsized.setUrl("test.url/");
        Images images = new Images();
        images.setDownsized(downsized);
        Gif gif = new Gif();
        gif.setImages(images);
        data.setGif(gif);
    }

    @Test
    void getCurrencyValue() {
        assertEquals(0.9, mainService.getCurrencyValue(currency, "EUR"));
    }

    @Test
    void getGifUrl() {
        assertEquals("test.url/", mainService.getGifUrl(data));
    }
}