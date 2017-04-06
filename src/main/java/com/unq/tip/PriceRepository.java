package com.unq.tip;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by leog on 03/04/17.
 */



@RepositoryRestResource
public interface PriceRepository extends CrudRepository<Price, Long> {
}
