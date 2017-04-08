package com.unq.tip.repository;


import com.unq.tip.model.Price;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by leog on 03/04/17.
 */



@RepositoryRestResource
public interface PriceRepository extends CrudRepository<Price, Long> {
}
