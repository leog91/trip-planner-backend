package com.unq.tip.webService;

import com.unq.tip.model.Item;
import com.unq.tip.model.builder.ItemBuilder;
import com.unq.tip.repository.CurrencyRepository;
import com.unq.tip.repository.ItemRepository;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Leonardo on 6/4/2017.
 */

//@CrossOrigin(origins = "http://localhost:9000", maxAge = 3600)
@RestController
@RequestMapping("/item")
public class ItemRest {


    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private CurrencyRest currencyRest;



    @RequestMapping(value = "/add/{email}/{day}/{month}/{year}/{name}/{amount}/{currency}/{category}/{groupSize}", method = RequestMethod.GET)
    Item addItem(@PathVariable String email, @PathVariable Integer day, @PathVariable Integer month, @PathVariable Integer year, @PathVariable String name,
                 @PathVariable Float amount, @PathVariable String currency, @PathVariable String category, @PathVariable Integer groupSize) {

        LocalDate date = LocalDate.now().withDayOfMonth(day).withMonthOfYear(month).withYear(year);
        Item item = new ItemBuilder()
                .withDate(date)
                .withCurrency(currency)
                .withUser(email)
                .withName(name)
                .withAmount(amount)
                .withCategory(category)
                .withGroupSize(groupSize)
                .build();
        return this.itemRepository.save(item);
    }

    @RequestMapping(value = "/update/{id}/{email}/{day}/{month}/{year}/{name}/{amount}/{currency}/{category}/{groupSize}", method = RequestMethod.GET)
    Item updateItem(@PathVariable Long id, @PathVariable String email, @PathVariable Integer day, @PathVariable Integer month, @PathVariable Integer year, @PathVariable String name,
                 @PathVariable Float amount, @PathVariable String currency, @PathVariable String category, @PathVariable Integer groupSize) {

        LocalDate date = LocalDate.now().withDayOfMonth(day).withMonthOfYear(month).withYear(year);


        Item item = this.itemRepository.findOne(id);
        //item.setUser(email);
        item.setDate(date);
        item.setName(name);
        item.setAmmount(amount);
        //item.setCurrency(currency);
        item.setCategory(category);
        //item.setGroupSize(groupSize);



        return this.itemRepository.save(item);
    }








    Float sumItems(String currency, Collection<Item> items) throws IOException{

        Float res = new Float(0);

        for (Item i: items) {


            Float iAmount = i.getAmmount();
            /*
            String iCurrency = i.getCurrency();
            LocalDate iDate = i.getDate();

            String sValue = currencyRest.coefByCodeAndDate(iDate.getDayOfMonth(), iDate.getDayOfMonth(), iDate.getYear(), iCurrency);

            Float value =Float.parseFloat(sValue);

           res = res + value*iAmount;
*/
            res = res + iAmount;
        }

        return res;
    }


    @RequestMapping(value = "/item/{name}", method = RequestMethod.GET)
    Collection<Item> readItems(@PathVariable String name) {
        return this.itemRepository.findByName(name);
    }


    @RequestMapping(value = "/user/{userEmail}", method = RequestMethod.GET)
    Collection<Item> findByUser(@PathVariable String userEmail) {

        return this.itemRepository.findByUser(userEmail);
    }

    @RequestMapping(value = "/userSum/{userEmail}/{currency}", method = RequestMethod.GET)
    Float findByUserSum(@PathVariable String userEmail,@PathVariable String currency) throws IOException {

        return this.sumItems(currency,this.itemRepository.findByUser(userEmail));

    }




    @RequestMapping(value = "/readId/{id}", method = RequestMethod.GET)
    public Item findById(@PathVariable Long id) {
        return this.itemRepository.findOne(id);
    }


    //consider flag like is valid
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> deleteById(@PathVariable Long id) {


        this.itemRepository.delete(id);

        //this.itemRepository.findOne(id); update

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @RequestMapping(value = "/betweendates/{userEmail}/{dayFrom}/{monthFrom}/{yearFrom}/{dayTo}/{monthTo}/{yearTo}", method = RequestMethod.GET)
    Collection<Item> betweenDates(@PathVariable String userEmail, @PathVariable Integer dayFrom, @PathVariable Integer monthFrom, @PathVariable Integer yearFrom,
                                  @PathVariable Integer dayTo, @PathVariable Integer monthTo, @PathVariable Integer yearTo) {

        LocalDate dateFrom = LocalDate.now().withDayOfMonth(dayFrom).withMonthOfYear(monthFrom).withYear(yearFrom);
        LocalDate dateTo = LocalDate.now().withDayOfMonth(dayTo).withMonthOfYear(monthTo).withYear(yearTo);


        return this.itemRepository.findByDateBetweenAndUser(dateFrom, dateTo, userEmail);
    }


    @RequestMapping(value = "/category/{category}", method = RequestMethod.GET)
    Collection<Item> readByCategory(@PathVariable String category) {
        return this.itemRepository.findByCategory(category);
    }

    @RequestMapping(value = "/categoryuser/{userEmail}/{category}", method = RequestMethod.GET)
    Collection<Item> readByCategoryAndUser(@PathVariable String userEmail, @PathVariable String category) {
        return this.itemRepository.findByCategoryAndUser(category, userEmail);
    }


    @RequestMapping(value = "/init", method = RequestMethod.GET)
    Collection<Item> init() {

        LocalDate date = new LocalDate().withYear(2016).withMonthOfYear(5).withDayOfMonth(2);


        Item item1 = new ItemBuilder().withDate(date).withName("Fideos").withCategory("General").withUser("leog91").withCurrency("ARS").withAmount(15).build();
        itemRepository.save(item1);
        Item item2 = new ItemBuilder().withDate(date).withName("Agua caliente").withUser("leog91").withCategory("General").withCurrency("ARS").withAmount(1).build();
        itemRepository.save(item2);
        Item item3 = new ItemBuilder().withDate(date).withName("Cafe").withUser("leog91").withCategory("General").withCurrency("ARS").withAmount(10).build();
        itemRepository.save(item3);
        Item item4 = new ItemBuilder().withDate(date).withName("Fernet").withUser("leog91").withCategory("General").withCurrency("ARS").withAmount(30).build();
        itemRepository.save(item4);
        Item item5 = new ItemBuilder().withDate(date).withName("Colectivo").withUser("leog91").withCategory("General").withCurrency("ARS").withAmount(6).build();
        itemRepository.save(item5);
        Item item6 = new ItemBuilder().withDate(date).withName("Termo").withUser("leog91").withCategory("General").withCurrency("ARS").withAmount(100).build();
        itemRepository.save(item6);
        Item item7 = new ItemBuilder().withDate(date.plusDays(1)).withName("Chicles").withUser("leog91").withCategory("General").withCurrency("ARS").withAmount(5).build();
        itemRepository.save(item7);
        Item item8 = new ItemBuilder().withDate(date.plusDays(2)).withName("Papel").withUser("leog91").withCategory("General").withCurrency("ARS").withAmount(6).build();
        itemRepository.save(item8);
        Item item9 = new ItemBuilder().withDate(date.plusDays(5)).withName("3 Bananas").withUser("leog91").withCategory("General").withCurrency("ARS").withAmount(10).build();
        itemRepository.save(item9);
        Item item10 = new ItemBuilder().withDate(date.plusDays(7)).withName("Libro").withUser("leog91").withCategory("General").withCurrency("ARS").withAmount(76).build();
        itemRepository.save(item10);

        return this.itemRepository.findByName("Fideos");
    }



    @RequestMapping(value = "/inib", method = RequestMethod.GET)
    Collection<Item> initb() {

        LocalDate date = new LocalDate().withYear(2016).withMonthOfYear(5).withDayOfMonth(2);


        Item item1 = new ItemBuilder().withDate(date).withName("Fideos").withCategory("abs").withUser("leog91").withCurrency("ARS").withAmount(15).build();
        itemRepository.save(item1);
        Item item2 = new ItemBuilder().withDate(date).withName("Agua caliente").withUser("leog91").withCategory("abs").withCurrency("ARS").withAmount(1).build();
        itemRepository.save(item2);
        Item item3 = new ItemBuilder().withDate(date).withName("Cafe").withUser("leog91").withCategory("Food").withCurrency("ARS").withAmount(10).build();
        itemRepository.save(item3);
        Item item4 = new ItemBuilder().withDate(date).withName("Fernet").withUser("leog91").withCategory("Food").withCurrency("ARS").withAmount(30).build();
        itemRepository.save(item4);
        Item item5 = new ItemBuilder().withDate(date).withName("Colectivo").withUser("leog91").withCategory("Food").withCurrency("ARS").withAmount(6).build();
        itemRepository.save(item5);
        Item item6 = new ItemBuilder().withDate(date).withName("Termo").withUser("leog91").withCategory("General").withCurrency("ARS").withAmount(100).build();
        itemRepository.save(item6);
        Item item7 = new ItemBuilder().withDate(date.plusDays(1)).withName("Chicles").withUser("leog91").withCategory("General").withCurrency("ARS").withAmount(5).build();
        itemRepository.save(item7);
        Item item8 = new ItemBuilder().withDate(date.plusDays(2)).withName("Papel").withUser("leog91").withCategory("General").withCurrency("ARS").withAmount(6).build();
        itemRepository.save(item8);
        Item item9 = new ItemBuilder().withDate(date.plusDays(5)).withName("3 Bananas").withUser("leog91").withCategory("General").withCurrency("ARS").withAmount(10).build();
        itemRepository.save(item9);
        Item item10 = new ItemBuilder().withDate(date.plusDays(7)).withName("Libro").withUser("leog91").withCategory("General").withCurrency("ARS").withAmount(76).build();
        itemRepository.save(item10);

        return this.itemRepository.findByName("Fideos");
    }

    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    Collection<String> categories() {
        List<String> c = new ArrayList<String>();
        c.add("Food");
        c.add("Transport");
        c.add("Lodging");
        return c;
    }


    public ItemRest(){}



}
