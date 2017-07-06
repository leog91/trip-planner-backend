package com.unq.tip.model;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.joda.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


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

    private Float ratio;


    /**
     * Stock market is closed on weekends
     */
    public LocalDate checkDate(LocalDate date) {

        LocalDate checked = date;

        if (date.getDayOfWeek() == 6) {
            checked = date.minusDays(1);
        }
        if (date.getDayOfWeek() == 7) {
            checked = date.plusDays(1);
        }

        return checked;
    }


    public String fakeRequest(LocalDate date, String codeFrom, String codeTo) {

        String res = "10";

        if (codeFrom.equals(codeTo)) {
            res = "1";
        }
        if (codeFrom.equals("ARS") && codeTo.equals("USD")) {
            res = "0.06";
        }
        if (codeFrom.equals("USD") && codeTo.equals("ARS")) {
            res = "16";
        }
        if (codeFrom.equals("BOB") && codeTo.equals("USD")) {
            res = "0.144";
        }
        if (codeFrom.equals("BOB") && codeTo.equals("ARS")) {
            res = "2.37";
        }
        if (codeFrom.equals("PEN") && codeTo.equals("USD")) {
            res = "0.307";
        }
        if (codeFrom.equals("PEN") && codeTo.equals("ARS")) {
            res = "5.04";
        }
        if (codeFrom.equals("EUR") && codeTo.equals("USD")) {
            res = "1.14";
        }
        if (codeFrom.equals("EUR") && codeTo.equals("ARS")) {
            res = "18.7";
        }


        return res;
    }

    public String requestFromApiYahoo(String code, LocalDate date) throws IOException {

        String checkedDate = checkDate(date).toString();

        String sURL = "http://query.yahooapis.com/v1/public/yql?q=SELECT%20*%20FROM%20yahoo.finance.historicaldata%20WHERE%20symbol%20=%20%22"
                + code +
                "=X%22%20AND%20startDate%20=%20%22" +
                checkedDate +
                "%22%20AND%20endDate%20=%20%22" +
                checkedDate +
                "%22&format=json&env=store://datatables.org/alltableswithkeys";

        //String sURL = "http://query.yahooapis.com/v1/public/yql?q=SELECT%20*%20FROM%20yahoo.finance.historicaldata%20WHERE%20symbol%20=%20%22SEK=X%22%20AND%20startDate%20=%20%222014-10-02%22%20AND%20endDate%20=%20%222014-10-02%22&format=json&env=store://datatables.org/alltableswithkeys";

        //java's native library
        URL url = new URL(sURL);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();

        JsonParser jp = new JsonParser(); //from gson
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent())); //Convert the input stream to a json element
        JsonObject rootobj = root.getAsJsonObject(); //May be an array, may be an object.

        String openStockValue = rootobj.getAsJsonObject("query").getAsJsonObject("results").getAsJsonObject("quote").get("Open").getAsString();

        return openStockValue;
    }


    public Float getRatio() {
        return ratio;
    }

    public void setRatio(Float ratio) {
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

    public Currency(String codeFrom, String codeTo, LocalDate date, Float ratio) {
        this.codeFrom = codeFrom;
        this.codeTo = codeTo;
        this.date = date;
        this.ratio = ratio;
    }


    public Currency() {
    }


}
