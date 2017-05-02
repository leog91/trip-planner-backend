package com.unq.tip.repository;

/**
 * Created by Leonardo on 7/4/2017.
 */

import com.unq.tip.model.Currency;
import org.joda.time.LocalDate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Date;
import java.util.List;


@RepositoryRestResource
public interface CurrencyRepository extends CrudRepository<Currency, Long> {


    //List<Currency> findByDate(LocalDate date,String codeFrom, String codeTo);

    List<Currency> findByDate(LocalDate date);

    List<Currency> findByDateAndCodeFromAndCodeTo(LocalDate date,String codeFrom, String codeTo);



    /*
    Currency findByCodeFromAndCodeToAndDate(Str);

    private String codeFrom;

    private String codeTo;

    private LocalDate date;

*/
    //t
    //Currency findCurrency(Currency currency);

}
