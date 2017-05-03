package com.unq.tip.webService;

import com.unq.tip.model.Item;
import com.unq.tip.model.builder.ItemBuilder;
import com.unq.tip.repository.ItemRepository;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @RequestMapping(value = "/add/{email}/{day}/{month}/{year}/{name}/{amount}/{currency}/{category}/{groupSize}", method = RequestMethod.GET)
    Item addItem(@PathVariable String email, @PathVariable Integer day, @PathVariable Integer month, @PathVariable Integer year, @PathVariable String name,
                 @PathVariable Integer amount, @PathVariable String currency, @PathVariable String category, @PathVariable Integer groupSize) {

        LocalDate date = LocalDate.now().withDayOfMonth(day).withMonthOfYear(month).withYear(year);
        // http://localhost:8080/item/leog91@gmail.com/29/3/2017/baseName/0/ARS/general
        Item item = new ItemBuilder()
                .withDate(date)
                .withCurrency(currency)
                .withUser(email)
                .withName(name)
                .withGroupSize(groupSize)
                .build();
        return this.itemRepository.save(item);
    }


    @RequestMapping(value = "/item/{name}", method = RequestMethod.GET)
    Collection<Item> readItems(@PathVariable String name) {
        return this.itemRepository.findByName(name);
    }


    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    Collection<String> categories() {
        List<String> c = new ArrayList<String>();
        c.add("Food");
        c.add("Transport");
        c.add("Lodging");
        return c;
    }


    @RequestMapping(value = "/item/{date1}/{date2}", method = RequestMethod.GET)
    Collection<Item> readBetweenDates(@PathVariable String date1, String date2) {
        LocalDate dateA = new LocalDate().withYear(2009).withMonthOfYear(3).withDayOfMonth(4);
        //LocalDate dateB = new LocalDate().withYear(2010).withMonthOfYear(3).withDayOfMonth(4);
        LocalDate dateB = LocalDate.now();
        return this.itemRepository.findByDateBetween(dateA, dateB);
    }


    //
    //
    @RequestMapping(value = "/item/{date1}/{date2}/{user}", method = RequestMethod.GET)
    Collection<Item> readBetweenDatesAndUser(@PathVariable String date1, String date2, String user) {
        LocalDate dateA = new LocalDate().withYear(2009).withMonthOfYear(3).withDayOfMonth(4);
        //LocalDate dateB = new LocalDate().withYear(2010).withMonthOfYear(3).withDayOfMonth(4);
        LocalDate dateB = LocalDate.now();
        return this.itemRepository.findByDateBetweenAndUser(dateA, dateB, "user2");
    }

    @RequestMapping(value = "/user/{userEmail}", method = RequestMethod.GET)
    Collection<Item> findByUser(@PathVariable String userEmail) {

        return this.itemRepository.findByUser("leog91@gmail.com");
        //return this.itemRepository.findByUser(userEmail);
    }

    @RequestMapping(value = "/betweendates/{userEmail}/{dayFrom}/{monthFrom}/{yearFrom}/{dayTo}/{monthTo}/{yearTo}", method = RequestMethod.GET)
    Collection<Item> betweenDates(@PathVariable String userEmail, @PathVariable Integer dayFrom, @PathVariable Integer monthFrom, @PathVariable Integer yearFrom,
                                  @PathVariable Integer dayTo, @PathVariable Integer monthTo, @PathVariable Integer yearTo) {

        LocalDate dateFrom = LocalDate.now().withDayOfMonth(dayFrom).withMonthOfYear(monthFrom).withYear(yearFrom);
        LocalDate dateTo = LocalDate.now().withDayOfMonth(dayTo).withMonthOfYear(monthTo).withYear(yearTo);

        return this.itemRepository.findByDateBetweenAndUser(dateFrom, dateTo, "leog91@gmail.com");
        //return this.itemRepository.findByUser("leog91@gmail.com");
        //return this.itemRepository.findByUser(userEmail);
    }


    @RequestMapping(value = "/category/{category}", method = RequestMethod.GET)
    Collection<Item> readByCategory(@PathVariable String category) {
        return this.itemRepository.findByCategory(category);
    }


    @RequestMapping(
            value = "/itemWithUser",
            method = RequestMethod.GET)//,
    //consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> addItemWithUser() {

        Item item1 = new ItemBuilder().withUser("user2").withDate(new LocalDate()).build();

        itemRepository.save(item1);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @RequestMapping(
            value = "/user",
            method = RequestMethod.GET)//,
    //consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> addUser() {

        Item item1 = new ItemBuilder().withDate(new LocalDate()).build();

        itemRepository.save(item1);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @RequestMapping(value = "/date", method = RequestMethod.GET)
    Collection<Item> addDate() {

        //new ItemBuilder().withDate(date);
        Item item = new ItemBuilder().build();

        itemRepository.save(item);
        return this.itemRepository.findByName("bassename");

        //return this.itemRepository.findByName();
    }


    @RequestMapping(value = "/init", method = RequestMethod.GET)
    Collection<Item> init() {


        LocalDate date = new LocalDate().withYear(2016).withMonthOfYear(5).withDayOfMonth(2);


        Item item1 = new ItemBuilder().withDate(date).withName("Fideos").withCategory("General").withUser("leog91@gmail.com").withCurrency("ARS").withAmount(15).build();
        itemRepository.save(item1);
        Item item2 = new ItemBuilder().withDate(date).withName("Agua caliente").withUser("leog91@gmail.com").withCategory("General").withCurrency("ARS").withAmount(1).build();
        itemRepository.save(item2);
        Item item3 = new ItemBuilder().withDate(date).withName("Cafe").withUser("leog91@gmail.com").withCategory("General").withCurrency("ARS").withAmount(10).build();
        itemRepository.save(item3);
        Item item4 = new ItemBuilder().withDate(date).withName("Fernet").withUser("leog91@gmail.com").withCategory("General").withCurrency("ARS").withAmount(30).build();
        itemRepository.save(item4);
        Item item5 = new ItemBuilder().withDate(date).withName("Colectivo").withUser("leog91@gmail.com").withCategory("General").withCurrency("ARS").withAmount(6).build();
        itemRepository.save(item5);
        Item item6 = new ItemBuilder().withDate(date).withName("Termo").withUser("leog91@gmail.com").withCategory("General").withCurrency("ARS").withAmount(100).build();
        itemRepository.save(item6);
        Item item7 = new ItemBuilder().withDate(date.plusDays(1)).withName("Chicles").withUser("leog91@gmail.com").withCategory("General").withCurrency("ARS").withAmount(5).build();
        itemRepository.save(item7);
        Item item8 = new ItemBuilder().withDate(date.plusDays(2)).withName("Papel").withUser("leog91@gmail.com").withCategory("General").withCurrency("ARS").withAmount(6).build();
        itemRepository.save(item8);
        Item item9 = new ItemBuilder().withDate(date.plusDays(5)).withName("3 Bananas").withUser("leog91@gmail.com").withCategory("General").withCurrency("ARS").withAmount(10).build();
        itemRepository.save(item9);
        Item item10 = new ItemBuilder().withDate(date.plusDays(7)).withName("Libro").withUser("leog91@gmail.com").withCategory("General").withCurrency("ARS").withAmount(76).build();
        itemRepository.save(item10);


        return this.itemRepository.findByName("Fideos");

        //return this.itemRepository.findByName();
    }






/*
    @RequestMapping(value = "/item/", method = RequestMethod.GET)
    public ResponseEntity<List<Item>> listAllUsers() {




        List<Item> items = itemRepository.findAll().iterator().to;

        List<Item> items = itemRepository.findAllUsers();

        if (items.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Item>>(items, HttpStatus.OK);
    }
*/





/*
    //test
    @RequestMapping(value = "/item/{name}", method = RequestMethod.POST)
    Item createItem(@PathVariable Item item) {
        return this.itemRepository.save(item);
    }
*/




/*



    //test
    @RequestMapping(value = "/item/{name}", method = RequestMethod.POST)
    Item createItemByName(@PathVariable String name) {
        Item item = new ItemBuilder().withName(name).build();
        return this.itemRepository.save(item);
    }

*/


}
