package com.unq.tip.model;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 * Created by Leonardo on 7/4/2017.
 */
@Entity
public class Currency {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String codeFrom;

    private String codeTo;

    private LocalDate date;

    private int ratio;

    public int getRatio() {
        return ratio;
    }

    public void setRatio(int ratio) {
        this.ratio = ratio;
    }

    public String getCodeFrom() {
        return codeFrom;
    }

    public void setCodeFrom(String codeFrom) {
        this.codeFrom = codeFrom;
    }

    public String getCodeTo() {
        return codeTo;
    }

    public void setCodeTo(String codeTo) {
        this.codeTo = codeTo;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Currency(String codeFrom, String codeTo, LocalDate date, int ratio) {
        this.codeFrom = codeFrom;
        this.codeTo = codeTo;
        this.date = date;
        this.ratio = ratio;
    }

    public Currency() {
    }


}
