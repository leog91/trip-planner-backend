package com.unq.tip.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Leonardo on 16/4/2017.
 */


@Entity
public class User {

    @Id
    private String email;

    private int groupSize;

/*
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
*/

   /* @OneToMany()
    private Set<Item> items = new HashSet<>();
*/

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User(String email,int groupSize) {
        this.email = email;
        this.groupSize = groupSize;
    }

    public User(){}

    //public void addItem(Item item){this.items.add(item);}


}
