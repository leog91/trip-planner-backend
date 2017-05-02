package com.unq.tip.repository;

import com.unq.tip.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by Leonardo on 16/4/2017.
 */

@RepositoryRestResource
public interface UserRepository extends CrudRepository<User, String> {

    List<User> findByEmail(String email);


}
