package main;

import main.model.feigncurr.Currency;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "feign-currency-client", url = "${CURRENCY_API_URL}")
public interface FeignCurrencyClient {

    @RequestMapping("/{date}.json?app_id=${APP_ID_CURRENCY}&symbols={symbol}")
    Currency getCurrency(@PathVariable String date, @PathVariable String symbol);
}
