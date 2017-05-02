package com.unq.tip.repository;


import java.util.Date;
import java.util.List;

import com.unq.tip.model.Item;
import org.joda.time.LocalDate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by leog on 01/04/17.
 */
@RepositoryRestResource
public interface ItemRepository extends CrudRepository<Item, Long> {


    List<Item> findByName(String name);

    List<Item> findByCategory(String category);

    List<Item> findByDateBetween(LocalDate date1, LocalDate date2);

    // List<Item> findByDate(Date date);

    //ck
    List<Item> findByDateBetweenAndUser(LocalDate date1, LocalDate date2, String user);


}
