package com.unq.tip.model;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import sun.awt.Symbol;

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

    /*
        public String requestFromApi()throws IOException{


            String sURL = "http://freegeoip.net/json/"; //just a string

            // Connect to the URL using java's native library
            URL url = new URL(sURL);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();

            // Convert to a JSON object to print data
            JsonParser jp = new JsonParser(); //from gson
            JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent())); //Convert the input stream to a json element
            JsonObject rootobj = root.getAsJsonObject(); //May be an array, may be an object.
            String zipcode = rootobj.get("zip_code").getAsString(); //just grab the zipcode


            return zipcode;
        }
    */
    public String requestFromApiYahoo() throws IOException {

        //add code date


        String sURL = "http://query.yahooapis.com/v1/public/yql?q=SELECT%20*%20FROM%20yahoo.finance.historicaldata%20WHERE%20symbol%20=%20%22"
                + "ARS" +
                "=X%22%20AND%20startDate%20=%20%22" +
                "2012-01-01" +
                "%22%20AND%20endDate%20=%20%22" +
                "2012-01-02" +
                "%22&format=json&env=store://datatables.org/alltableswithkeys";
        //String sURL = "http://query.yahooapis.com/v1/public/yql?q=SELECT%20*%20FROM%20yahoo.finance.historicaldata%20WHERE%20symbol%20=%20%22SEK=X%22%20AND%20startDate%20=%20%222014-10-02%22%20AND%20endDate%20=%20%222014-10-02%22&format=json&env=store://datatables.org/alltableswithkeys"; //just a string


        // Connect to the URL using java's native library
        URL url = new URL(sURL);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();

        // Convert to a JSON object to print data
        JsonParser jp = new JsonParser(); //from gson
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent())); //Convert the input stream to a json element
        JsonObject rootobj = root.getAsJsonObject(); //May be an array, may be an object.

        //object Working !
        // String zipcode = rootobj.getAsJsonObject("query").getAsJsonObject("results").getAsJsonObject("quote").toString();


        //OpenStockValue
        String zipcode = rootobj.getAsJsonObject("query").getAsJsonObject("results").getAsJsonObject("quote").get("Open").getAsString();


        //[query] -> [results] -> [quote] -> Symbol

        return zipcode;
    }

    public String requestFromApiYahoo(String code, LocalDate date) throws IOException {


        //LocalDate date = LocalDate.now().withYear(2017).withMonthOfYear(1).withDayOfMonth(1);
//!!!! stock weekend values are down, 'cose  there s not activity ??
// date.getDayOfWeek() == weekend() saturday -> friday || sunday -> monday

        LocalDate datePlusOne = date.plusDays(1);


        String dateFrom = date.toString();

        String datePlus = datePlusOne.toString();

        String sURL = "http://query.yahooapis.com/v1/public/yql?q=SELECT%20*%20FROM%20yahoo.finance.historicaldata%20WHERE%20symbol%20=%20%22"
                + code +
                // + "ARS" +
                "=X%22%20AND%20startDate%20=%20%22" +
                dateFrom +
                "%22%20AND%20endDate%20=%20%22" +
                datePlus +
                "%22&format=json&env=store://datatables.org/alltableswithkeys";
        //String sURL = "http://query.yahooapis.com/v1/public/yql?q=SELECT%20*%20FROM%20yahoo.finance.historicaldata%20WHERE%20symbol%20=%20%22SEK=X%22%20AND%20startDate%20=%20%222014-10-02%22%20AND%20endDate%20=%20%222014-10-02%22&format=json&env=store://datatables.org/alltableswithkeys"; //just a string


        // Connect to the URL using java's native library
        URL url = new URL(sURL);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();

        // Convert to a JSON object to print data
        JsonParser jp = new JsonParser(); //from gson
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent())); //Convert the input stream to a json element
        JsonObject rootobj = root.getAsJsonObject(); //May be an array, may be an object.

        //object Working !
        // String zipcode = rootobj.getAsJsonObject("query").getAsJsonObject("results").getAsJsonObject("quote").toString();


        //OpenStockValue
        String zipcode = rootobj.getAsJsonObject("query").getAsJsonObject("results").getAsJsonObject("quote").get("Open").getAsString();


        //[query] -> [results] -> [quote] -> Symbol

        return zipcode;
    }


    public Currency() {
    }


}
