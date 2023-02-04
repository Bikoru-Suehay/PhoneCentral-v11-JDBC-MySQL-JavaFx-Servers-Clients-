package com.biksue.phonecentral_jdbc_sockets.model.entity;

import java.util.Objects;

public class Numbers {
    private Long idClient;
    private String number;

    @Override
    public String toString() {
        return "Numbers{" +
                "idClient='" + idClient + '\'' +
                ", number='" + number + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Numbers numbers = (Numbers) o;
        return Objects.equals(idClient, numbers.idClient) && Objects.equals(number, numbers.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idClient, number);
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }
}
