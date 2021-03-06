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
import java.util.List;
import java.util.stream.Stream;

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

    @Autowired
    private ItemRest itemRest = new ItemRest();

    @RequestMapping(value = "/read/{email}", method = RequestMethod.GET)
    User readItems(@PathVariable String email) {
        return this.userRepository.findOne(email);
    }


    @RequestMapping(value = "/logIn/{email}", method = RequestMethod.GET)
    public ResponseEntity<?> logIn(@PathVariable String email) {

        User user = this.userRepository.findOne(email);

        if (user == null) {
            user = new UserBuilder().withEmail(email).build();
            userRepository.save(user);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @RequestMapping(value = "/addCategory/{email}/{category}", method = RequestMethod.GET)
    public ResponseEntity<?> addCategory(@PathVariable String email, @PathVariable String category) {

        User user = this.userRepository.findOne(email);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        user.addCategory(category);
        this.userRepository.save(user);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/removeCategory/{email}/{category}", method = RequestMethod.GET)
    public ResponseEntity<?> removeCategory(@PathVariable String email, @PathVariable String category) {

        User user = this.userRepository.findOne(email);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


        Collection<Item> items = itemRest.readByCategoryAndUser(email, category);

        for (Item i : items) {
            i.toGeneralCategory();
            itemRepository.save(i);
        }

        user.removeCategory(category);
        this.userRepository.save(user);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @RequestMapping(value = "/saveSettings/{email}/{code}/{groupSize}", method = RequestMethod.POST)
    public ResponseEntity<?> saveSettings(@PathVariable String email, @PathVariable String code, @PathVariable int groupSize) {

        User user = this.userRepository.findOne(email);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        user.setGroupSize(groupSize);
        user.setCurrentCurrency(code);

        this.userRepository.save(user);

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
