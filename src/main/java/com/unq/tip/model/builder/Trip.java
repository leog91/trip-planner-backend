package com.unq.tip.model.builder;

import org.joda.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by leog on 23/05/17.
 */

@Entity
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    public String user;
    public LocalDate dateFrom;
    public LocalDate dateTo;
    public String info;




}
