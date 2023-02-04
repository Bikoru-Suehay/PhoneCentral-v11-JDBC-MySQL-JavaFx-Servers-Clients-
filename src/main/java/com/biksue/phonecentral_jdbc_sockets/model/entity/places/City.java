package com.biksue.phonecentral_jdbc_sockets.model.entity.places;


import com.biksue.phonecentral_jdbc_sockets.model.entity.abstracts.Place;

import java.util.Objects;

public class City extends Place {
    private Long idCountry;
    private Long idProvince;

    public City(String name, Long idCountry, Long idProvince) {
        super(name);
        this.idCountry = idCountry;
        this.idProvince = idProvince;
    }

    public City() {
        super("");
    }

    @Override
    public String toString() {
        return "City{" +
                "idCountry=" + idCountry +
                ", idProvince=" + idProvince +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        City city = (City) o;
        return Objects.equals(idCountry, city.idCountry) && Objects.equals(idProvince, city.idProvince);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), idCountry, idProvince);
    }

    public Long getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(Long idCountry) {
        this.idCountry = idCountry;
    }

    public Long getIdProvince() {
        return idProvince;
    }

    public void setIdProvince(Long idProvince) {
        this.idProvince = idProvince;
    }
}
