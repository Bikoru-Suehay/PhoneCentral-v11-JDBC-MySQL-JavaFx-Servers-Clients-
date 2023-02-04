package com.biksue.phonecentral_jdbc_sockets.model.entity;

import java.util.ArrayList;
import java.util.Objects;

public class Client {
    private Long id;
    private Long centralId;
    private Long idCountry;
    private ArrayList<String> phoneNumbers = new ArrayList<>();
    private String type;
    private String name;
    private String lastName;

    public Client() {
    }

    public Client(Long centralId, String type, String name, String lastName) {
        this.centralId = centralId;
        this.type = type;
        this.name = name;
        this.lastName = lastName;
    }

    public Long getCentralId() {
        return centralId;
    }

    public void setCentralId(Long centralId) {
        this.centralId = centralId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ArrayList<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(ArrayList<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(Long idCountry) {
        this.idCountry = idCountry;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id) && Objects.equals(centralId, client.centralId) && Objects.equals(phoneNumbers, client.phoneNumbers) && Objects.equals(type, client.type) && Objects.equals(name, client.name) && Objects.equals(lastName, client.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, centralId, phoneNumbers, type, name, lastName);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id='" + id + '\'' +
                ", centralId='" + centralId + '\'' +
                ", phoneNumbers=" + phoneNumbers +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
