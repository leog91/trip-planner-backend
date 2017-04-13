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

import java.util.Collection;

/**
 * Created by Leonardo on 6/4/2017.
 */


@RestController
@RequestMapping("/item")
public class ItemRest {


    @Autowired
    private ItemRepository itemRepository;





    @RequestMapping(value = "/item/{name}", method = RequestMethod.GET)
    Collection<Item> readItems(@PathVariable String name) {
        return this.itemRepository.findByName(name);
    }


    @RequestMapping(value = "/item/{date1}/{date2}", method = RequestMethod.GET)
    Collection<Item> readBetweenDates(@PathVariable String date1,String date2) {
        LocalDate dateA = new LocalDate().withYear(2009).withMonthOfYear(3).withDayOfMonth(4);
        //LocalDate dateB = new LocalDate().withYear(2010).withMonthOfYear(3).withDayOfMonth(4);
        LocalDate dateB = LocalDate.now();
        return this.itemRepository.findByDateBetween(dateA,dateB);
    }




    @RequestMapping(value = "/category/{category}", method = RequestMethod.GET)
    Collection<Item> readByCategory(@PathVariable String category) {
        return this.itemRepository.findByCategory(category);
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
