package com.unq.tip;

import com.unq.tip.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.Collection;
import java.util.List;

/**
 * Created by Leonardo on 6/4/2017.
 */


@RestController
@RequestMapping("/item")
public class ItemRest {


    @Autowired
    private ItemRepository itemRepository;


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



    @RequestMapping(value = "/item/{name}", method = RequestMethod.GET)
    Collection<Item> readItems(@PathVariable String name) {
        return this.itemRepository.findByName(name);
    }



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
