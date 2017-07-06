package com.unq.tip.webService;

import com.unq.tip.model.Currency;
import com.unq.tip.model.builder.CurrencyBuilder;
import com.unq.tip.repository.CurrencyRepository;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Collection;

/**
 * Created by Leonardo on 7/4/2017.
 */


@RestController
@RequestMapping("/currency")
public class CurrencyRest {


    @Autowired
    private CurrencyRepository currencyRepository;


    //calc
    @RequestMapping(value = "/coeff/{codeFrom}/{codeTo}/{day}/{month}/{year}", method = RequestMethod.GET)
    String coefByCodeAndDateF(@PathVariable Integer day, @PathVariable Integer month, @PathVariable Integer year, @PathVariable String codeFrom, @PathVariable String codeTo) throws IOException {

        Currency currency = new Currency();

        LocalDate date = new LocalDate().withYear(year).withMonthOfYear(month).withDayOfMonth(day);

        Currency res = currencyRepository.findByDateAndCodeFromAndCodeTo(date, codeFrom, codeTo);

        String coef;

        if (res == null) {
            coef = currency.fakeRequest(date, codeFrom, codeTo);

            CurrencyBuilder currencyBuilder = new CurrencyBuilder();
            Currency currencyb = currencyBuilder
                    .withDate(date)
                    .withCodeTo(codeTo)
                    .withCodeFrom(codeFrom)
                    .withRatio(Float.parseFloat(coef))
                    .build();

            currencyRepository.save(currencyb);
        } else {
            coef = res.getRatio().toString();
        }

        return coef;
    }

    @RequestMapping(value = "/coef/{code}/{day}/{month}/{year}", method = RequestMethod.GET)
    String coefByCodeAndDate(@PathVariable Integer day, @PathVariable Integer month, @PathVariable Integer year, @PathVariable String code) throws IOException {
        //ToUSD
        Currency currency = new Currency();

        LocalDate date = new LocalDate().withYear(year).withMonthOfYear(month).withDayOfMonth(day);

        Currency res = currencyRepository.findByDateAndCodeFromAndCodeTo(date, "USD", code);

        String coef;


        if (res == null) {

            coef = currency.requestFromApiYahoo(code, date);

            CurrencyBuilder currencyBuilder = new CurrencyBuilder();
            Currency currencyb = currencyBuilder
                    .withDate(date)
                    .withCodeTo(code)
                    .withCodeFrom("USD")
                    .withRatio(Float.parseFloat(coef))
                    .build();

            currencyRepository.save(currencyb);
        } else {
            coef = res.getRatio().toString();
        }

        return coef;
    }


    @RequestMapping(value = "/date/{day}/{month}/{year}/{codeFrom}/{codeTo}", method = RequestMethod.GET)
    Collection<Currency> readAllByDate(@PathVariable Integer day, @PathVariable Integer month, @PathVariable Integer year, @PathVariable String codeFrom, @PathVariable String codeTo) {

        LocalDate date = LocalDate.now().withDayOfMonth(day).withMonthOfYear(month).withYear(year);
        return this.currencyRepository.findByDate(date);
    }


    @RequestMapping(value = "/dates/{day}/{month}/{year}/{codeFrom}/{codeTo}", method = RequestMethod.GET)
    Currency readBydate(@PathVariable Integer day, @PathVariable Integer month, @PathVariable Integer year, @PathVariable String codeFrom, @PathVariable String codeTo) {

        LocalDate date = LocalDate.now().withDayOfMonth(day).withMonthOfYear(month).withYear(year);
        return this.currencyRepository.findByDateAndCodeFromAndCodeTo(date, codeFrom, codeTo);
    }


    @RequestMapping(value = "/add/{day}/{month}/{year}/{ratio}/{codeFrom}/{codeTo}", method = RequestMethod.GET)
    Currency addRatio(@PathVariable Integer day, @PathVariable Integer month, @PathVariable Integer year, @PathVariable String codeFrom, @PathVariable String codeTo, @PathVariable Float ratio) {

        LocalDate date = LocalDate.now().withDayOfMonth(day).withMonthOfYear(month).withYear(year);

        Currency currency = this.currencyRepository.findByDateAndCodeFromAndCodeTo(date, codeFrom, codeTo);

        if (currency == null) {
            currency = new CurrencyBuilder()
                    .withDate(date)
                    .withCodeFrom(codeFrom)
                    .withCodeTo(codeTo)
                    .withRatio(ratio)
                    .build();
        } else {
            currency.setRatio(ratio);
        }

        return this.currencyRepository.save(currency);
    }


    @RequestMapping(value = "/adds/{dayFrom}/{monthFrom}/{yearFrom}/{dayTo}/{monthTo}/{yearTo}/{ratio}/{codeFrom}/{codeTo}", method = RequestMethod.GET)
    Currency addRatios(@PathVariable Integer dayFrom, @PathVariable Integer monthFrom, @PathVariable Integer yearFrom,
                       @PathVariable Integer dayTo, @PathVariable Integer monthTo, @PathVariable Integer yearTo,
                       @PathVariable String codeFrom, @PathVariable String codeTo, @PathVariable Float ratio) {


        LocalDate dateFrom = LocalDate.now().withDayOfMonth(dayFrom).withMonthOfYear(monthFrom).withYear(yearFrom);
        LocalDate dateTo = LocalDate.now().withDayOfMonth(dayTo).withMonthOfYear(monthTo).withYear(yearTo);

        if (dateFrom.isAfter(dateTo)) {
            LocalDate aux = dateTo;
            dateTo = dateFrom;
            dateFrom = aux;
        }

        while (dateFrom.isBefore(dateTo)) {
            this.addRatio(dateFrom.getDayOfMonth(), dateFrom.getMonthOfYear(), dateFrom.getYear(), codeFrom, codeTo, ratio);
            dateFrom = dateFrom.plusDays(1);
        }

        return this.addRatio(dateFrom.getDayOfMonth(), dateFrom.getMonthOfYear(), dateFrom.getYear(), codeFrom, codeTo, ratio);
    }


    @RequestMapping(value = "/load", method = RequestMethod.GET)
    Currency load() {
        LocalDate date = LocalDate.now().withDayOfMonth(1).withMonthOfYear(4).withYear(2017);

        float ratio = 16;

        int i;
        for (i = 1; i < 14; i++) {

            Currency currency = new CurrencyBuilder()
                    .withDate(date.plusDays(i))
                    .withCodeFrom("USD")
                    .withCodeTo("ARS")
                    .withRatio(ratio)
                    .build();

            this.currencyRepository.save(currency);
        }

        Currency currency = new CurrencyBuilder()
                .withDate(date)
                .withCodeFrom("USD")
                .withCodeTo("ARS")
                .withRatio(ratio)
                .build();
        return this.currencyRepository.save(currency);
    }


}
