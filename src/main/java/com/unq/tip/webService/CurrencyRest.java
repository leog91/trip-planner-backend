package com.unq.tip.webService;

import com.unq.tip.model.Currency;
import com.unq.tip.model.builder.CurrencyBuilder;
import com.unq.tip.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * Created by Leonardo on 7/4/2017.
 */


@RestController
@RequestMapping("/currency")
public class CurrencyRest {


    @Autowired
    private CurrencyRepository currencyRepository;

    @RequestMapping(value = "/date/{date}/{codeFrom}/{codeTo}", method = RequestMethod.GET)
    Collection<Currency> readCurrency(@PathVariable String date, String codeFrom, String codeTo) {
        return this.currencyRepository.findByDate(codeFrom, codeTo, date);
    }

/*
    //check design

    @RequestMapping(value = "/save/{date}/{codeFrom}/{codeTo}/{coef}", method = RequestMethod.POST)
    Collection<Currency> setCurrency(@PathVariable String date, String codeFrom, String codeTo, String coef) {
        Currency currency = new CurrencyBuilder().withDate(date).withCodeFrom(codeFrom).withCodeTo(codeTo).build();
        currencyRepository.save(currency);
        return this.currencyRepository.findByDate(codeFrom, codeTo, date);
    }

*/




/*
    @RequestMapping(value = "/date/{date}/{codeFrom}/{codeTo}", method = RequestMethod.GET)
    Collection<Currency> readItems(@PathVariable String date, String codeFrom, String codeTo) {
        return this.currencyRepository.findByDateFromTo(codeFrom, codeTo, date);
    }

*/
}
