package com.unq.tip.model.builder;

import com.unq.tip.model.User;

/**
 * Created by Leonardo on 16/4/2017.
 */
public class UserBuilder {

    String email = "";

    int groupSize = 0;

    String currentCurrency = "USD";


    public UserBuilder withEmail(String email){
        this.email = email;
        return this;
    }

    public UserBuilder withGroupSize(int groupSize){
        this.groupSize =groupSize;
        return this;
    }

    public UserBuilder withCurrentCurrency (String currentCurrency){
        this.currentCurrency = currentCurrency;
        return this;
    }


    public User build(){
      return new User(email,groupSize,currentCurrency);
    }

}
