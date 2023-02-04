package com.biksue.phonecentral_jdbc_sockets.model.entity.places;

import com.biksue.phonecentral_jdbc_sockets.model.entity.abstracts.Place;

public class Country extends Place {
    private Long rate;
    public Country(String name) {
        super(name);
    }

    public Country() {
        super("");
    }

    public Long getRate() {
        return rate;
    }

    public void setRate(Long rate) {
        this.rate = rate;
    }
}
