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


    /*
    @RequestMapping(value = "/date/{date}/{codeFrom}/{codeTo}", method = RequestMethod.GET)
    Collection<Currency> readCurrency(@PathVariable String date, String codeFrom, String codeTo) {
        return this.currencyRepository.findByDate(codeFrom, codeTo, date);
    }
*/


    @RequestMapping(value = "/date/{day}/{month}/{year}/{codeFrom}/{codeTo}", method = RequestMethod.GET)
    Collection<Currency> readCurrency(@PathVariable Integer day, @PathVariable Integer month, @PathVariable Integer year, @PathVariable String codeFrom, @PathVariable String codeTo) {


        LocalDate date = LocalDate.now().withDayOfMonth(day).withMonthOfYear(month).withYear(year);
        //return this.currencyRepository.findByDate(codeFrom, codeTo, date);
        //return this.currencyRepository.findByDate(date,codeFrom,codeTo);
        return this.currencyRepository.findByDate(date);
    }


    @RequestMapping(value = "/dates/{day}/{month}/{year}/{codeFrom}/{codeTo}", method = RequestMethod.GET)
    Collection<Currency> readTest(@PathVariable Integer day, @PathVariable Integer month, @PathVariable Integer year, @PathVariable String codeFrom, @PathVariable String codeTo) {


//withCode
        LocalDate date = LocalDate.now().withDayOfMonth(day).withMonthOfYear(month).withYear(year);
        //return this.currencyRepository.findByDate(codeFrom, codeTo, date);
        //return this.currencyRepository.findByDate(date,codeFrom,codeTo);
        return this.currencyRepository.findByDateAndCodeFromAndCodeTo(date, codeFrom, codeTo);
    }


    @RequestMapping(value = "/test", method = RequestMethod.GET)
    Currency addCT() {

        Currency currency = new CurrencyBuilder()
                .withDate(LocalDate.now())
                .withCodeFrom("COP")
                .withCodeTo("BOL")
                .withRatio(3)
                .build();

        return this.currencyRepository.save(currency);

    }

    @RequestMapping(value = "/apit", method = RequestMethod.GET)
    String apiT() throws IOException {

        Currency currency = new Currency();



        //return  currency.requestFromApi();
        return  currency.requestFromApiYahoo();
    }




    /*

    @RequestMapping(value = "/find/{day}/{month}/{year}/{codeFrom}/{codeTo}/{ratio}", method = RequestMethod.GET)
    Currency findRatio(@PathVariable Integer day, @PathVariable Integer month, @PathVariable Integer year, @PathVariable String codeFrom, @PathVariable String codeTo, @PathVariable Integer ratio) {


        LocalDate date = LocalDate.now().withDayOfMonth(day).withMonthOfYear(month).withYear(year);

        Currency currency = new CurrencyBuilder()
                .withDate(date)
                .withCodeFrom(codeFrom)
                .withCodeTo(codeTo)
                .withRatio(ratio)
                .build();

        Currency res =  this.currencyRepository.findCurrency(currency);


        if ( res != null){
            res = Currency.req

            this.currencyRepository.save(res);
        }




        //t

        return this.currencyRepository.findCurrency(currency);
        //return this.currencyRepository.save(currency);
    }
*/

    @RequestMapping(value = "/add/{day}/{month}/{year}/{codeFrom}/{codeTo}/{ratio}", method = RequestMethod.GET)
    Currency addRatio(@PathVariable Integer day, @PathVariable Integer month, @PathVariable Integer year, @PathVariable String codeFrom, @PathVariable String codeTo, @PathVariable Integer ratio) {

/*
        int iday = Integer.parseInt(day);
        int imonth = Integer.parseInt(month);
        int iyear = Integer.parseInt(year);
        int iratio = Integer.parseInt(ratio);
*/


        LocalDate date = LocalDate.now().withDayOfMonth(day).withMonthOfYear(month).withYear(year);

        //LocalDate date = LocalDate.now().withDayOfMonth(iday).withMonthOfYear(imonth).withYear(iyear);

        //

        Currency currency = new CurrencyBuilder()
                .withDate(date)
                .withCodeFrom(codeFrom)
                .withCodeTo(codeTo)
                .withRatio(ratio)
                .build();
        return this.currencyRepository.save(currency);
    }


    @RequestMapping(value = "/load", method = RequestMethod.GET)
    Currency load() {
        LocalDate date = LocalDate.now().withDayOfMonth(1).withMonthOfYear(4).withYear(2017);

        int i;
        for (i = 1; i < 14; i++) {

            Currency currency = new CurrencyBuilder()
                    .withDate(date.plusDays(i))
                    .withCodeFrom("USD")
                    .withCodeTo("ARS")
                    .withRatio(15)
                    .build();

            this.currencyRepository.save(currency);
            //date.plusDays(i);
        }

        Currency currency = new CurrencyBuilder()
                .withDate(date)
                .withCodeFrom("USD")
                .withCodeTo("ARS")
                .withRatio(15)
                .build();
        return this.currencyRepository.save(currency);
    }


    @RequestMapping(value = "/loadd", method = RequestMethod.GET)
    Currency loadd() {
        LocalDate date = LocalDate.now().withDayOfMonth(1).withMonthOfYear(4).withYear(2017);


        Currency currency = new CurrencyBuilder()
                .withDate(date.plusDays(1))
                .withCodeFrom("USD")
                .withCodeTo("ARS")
                .withRatio(15)
                .build();
        this.currencyRepository.save(currency);

        Currency currency2 = new CurrencyBuilder()
                .withDate(date.plusDays(2))
                .withCodeFrom("USD")
                .withCodeTo("ARS")
                .withRatio(15)
                .build();
        this.currencyRepository.save(currency2);


        Currency currency3 = new CurrencyBuilder()
                .withDate(date.plusDays(3))
                .withCodeFrom("USD")
                .withCodeTo("ARS")
                .withRatio(15)
                .build();
        return this.currencyRepository.save(currency3);
    }


    @RequestMapping(value = "/loadSameDay", method = RequestMethod.GET)
    Currency loadSameDay() {
        LocalDate date = LocalDate.now().withDayOfMonth(1).withMonthOfYear(4).withYear(2017);

        int i;
        for (i = 0; i < 14; i++) {

            Currency currency = new CurrencyBuilder()
                    .withDate(date)
                    .withCodeFrom("BOL")
                    .withCodeTo("COP")
                    .withRatio(15)
                    .build();

            this.currencyRepository.save(currency);
            date.plusDays(i);
        }

        Currency currency = new CurrencyBuilder()
                .withDate(date)
                .withCodeFrom("BOL")
                .withCodeTo("COP")
                .withRatio(15)
                .build();
        return this.currencyRepository.save(currency);
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
