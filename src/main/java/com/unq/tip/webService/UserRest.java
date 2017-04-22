package com.unq.tip.webService;

import com.unq.tip.model.Item;
import com.unq.tip.model.User;
import com.unq.tip.model.builder.ItemBuilder;
import com.unq.tip.model.builder.UserBuilder;
import com.unq.tip.repository.ItemRepository;
import com.unq.tip.repository.UserRepository;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by Leonardo on 16/4/2017.
 */


@RestController
@RequestMapping("/user")
public class UserRest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemRepository itemRepository;


    @RequestMapping(value = "/read/{email}", method = RequestMethod.GET)
    Collection<User> readItems(@PathVariable String email) {
        return this.userRepository.findByEmail(email);
    }





    @RequestMapping(
            value = "/addUser",
            method = RequestMethod.GET)//,
    //consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> addUserr() {

        User user = new UserBuilder().withEmail("email1").build();

        userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @RequestMapping(
            value = "/addItemUser",
            method = RequestMethod.GET)//,
    //consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> addItemUserr() {

        String user = "email1";
//userRepository.findByEmail()
        User user1 =  userRepository.findOne(user);

        if (user1 == null){
            //check http status
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }



        Item item1 = new ItemBuilder().withUser(user).build();
        itemRepository.save(item1);






        //findByEmail(user).get(0);
       // user1.addItem(item1);

        userRepository.save(user1);
        return new ResponseEntity<>(HttpStatus.OK);
    }






}
