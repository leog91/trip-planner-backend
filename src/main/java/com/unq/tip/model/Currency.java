package com.unq.tip.model;

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

    private String date;

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Currency(String codeFrom, String codeTo, String date) {
        this.codeFrom = codeFrom;
        this.codeTo = codeTo;
        this.date = date;
    }

    public Currency() {
    }


}
