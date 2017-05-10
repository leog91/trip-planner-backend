package com.unq.tip.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Leonardo on 16/4/2017.
 */


@Entity
public class User {

    @Id
    private String email;

    private int groupSize;

    private String currentCurrency;

    @ElementCollection
    private Set<String> categories = new HashSet<>();

    public Set<String> getCategories() {
        return categories;
    }

    public void setCategories(Set<String> categories) {
        this.categories = categories;
    }

    public void addCategory(String category){
        this.categories.add(category);
    }

    public void removeCategory(String category){
        this.categories.remove(category);
    }

    public String getCurrentCurrency() {
        return currentCurrency;
    }

    public void setCurrentCurrency(String currentCurrency) {
        this.currentCurrency = currentCurrency;
    }

    public int getGroupSize() {
        return groupSize;
    }

    public void setGroupSize(int groupSize) {
        this.groupSize = groupSize;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User(String email, int groupSize, String currentCurrency) {
        this.email = email;
        this.groupSize = groupSize;
        this.currentCurrency = currentCurrency;
    }

    public User() {
    }


}
