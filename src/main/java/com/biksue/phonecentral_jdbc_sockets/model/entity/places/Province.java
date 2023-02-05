package com.biksue.phonecentral_jdbc_sockets.model.entity.places;

import com.biksue.phonecentral_jdbc_sockets.model.entity.abstracts.Place;

import java.util.Objects;

public class Province extends Place {
    private Long idCountry;

    public Province(String name, Long idCountry) {
        super(name);
        this.idCountry = idCountry;
    }

    public Province() {
       super("");
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Province{" +
                "idCountry=" + idCountry +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Province province = (Province) o;
        return Objects.equals(idCountry, province.idCountry);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), idCountry);
    }

    public Long getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(Long idCountry) {
        this.idCountry = idCountry;
    }
}
