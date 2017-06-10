package com.unq.tip.webService;

import com.unq.tip.model.Item;
import com.unq.tip.model.Trip;
import com.unq.tip.model.builder.ItemBuilder;
import com.unq.tip.model.builder.TripBuilder;
import com.unq.tip.repository.ItemRepository;
import com.unq.tip.repository.TripRepository;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Leonardo on 9/6/2017.
 */

@RestController
@RequestMapping("/trip")
public class TripRest {


    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private ItemRest itemRest ;//= new ItemRest();


    @RequestMapping(value = "/user/{userEmail}", method = RequestMethod.GET)
    Collection<Trip> findByUser(@PathVariable String userEmail) {

        return this.tripRepository.findByUser(userEmail);
    }

    @RequestMapping(value = "/items/{id}", method = RequestMethod.GET)
    List<Item> findTripItems(@PathVariable Long id) {


        Trip trip = this.tripRepository.findOne(id);
        if(trip != null ){
            return itemRepository.findByDateBetweenAndUser(trip.getDateFrom(),trip.getDateTo(),trip.getUser());
        }
        else
        {//safe
            return new ArrayList<Item>();
        }
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> deleteById(@PathVariable Long id) {


        this.tripRepository.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }



    @RequestMapping(value = "/add/{email}/{dayFrom}/{monthFrom}/{yearFrom}/{dayTo}/{monthTo}/{yearTo}/{name}/{info}", method = RequestMethod.GET)
    Trip addTrip(@PathVariable String email,
                 @PathVariable Integer dayFrom, @PathVariable Integer monthFrom, @PathVariable Integer yearFrom,
                 @PathVariable Integer dayTo, @PathVariable Integer monthTo, @PathVariable Integer yearTo,
                 @PathVariable String name, @PathVariable String info) {

        LocalDate dateFrom = LocalDate.now().withDayOfMonth(dayFrom).withMonthOfYear(monthFrom).withYear(yearFrom);
        LocalDate dateTo = LocalDate.now().withDayOfMonth(dayTo).withMonthOfYear(monthTo).withYear(yearTo);
        Trip trip= new TripBuilder()
                .withDateFrom(dateFrom)
                .withDateTo(dateTo)
                .withInfo(info)
                .withUser(email)
                .withName(name)
                .build();
        return this.tripRepository.save(trip);
    }





}
