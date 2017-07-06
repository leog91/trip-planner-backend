package com.unq.tip.repository;

import com.unq.tip.model.Trip;
import org.joda.time.LocalDate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by Leonardo on 9/6/2017.
 */

@RepositoryRestResource
public interface TripRepository extends CrudRepository<Trip, Long> {

    List<Trip> findByName(String name);

    List<Trip> findByUser(String user);


}
