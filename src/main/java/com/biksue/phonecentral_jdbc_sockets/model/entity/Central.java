package com.biksue.phonecentral_jdbc_sockets.model.entity;

import java.util.Objects;

public class Central {
    protected Long id;
    protected String name;
    protected Long country;
    protected Long province;
    protected Long city;

    public Central() {
    }

    public Central(String name, Long country, Long province, Long city) {
        this.name = name;
        this.country = country;
        this.province = province;
        this.city = city;
    }

    @Override
    public String toString() {
        return "Central{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Central central = (Central) o;
        return Objects.equals(id, central.id) && Objects.equals(name, central.name) && Objects.equals(country, central.country) && Objects.equals(province, central.province) && Objects.equals(city, central.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, country, province, city);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCountry() {
        return country;
    }

    public void setCountry(Long country) {
        this.country = country;
    }

    public Long getProvince() {
        return province;
    }

    public void setProvince(Long province) {
        this.province = province;
    }

    public Long getCity() {
        return city;
    }

    public void setCity(Long city) {
        this.city = city;
    }
}
