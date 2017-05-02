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
