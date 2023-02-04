package com.biksue.phonecentral_jdbc_sockets.model.entity.abstracts;

import java.util.Objects;

public abstract class Call {
    protected Long id;
    protected Long remittentId;
    protected Long destinationId;
    protected long lifeTime;
    protected Long date;
    protected double rate;

    public Call(Long remittentId, Long destinationId, Long lifeTime, Long date) {
        this.remittentId = remittentId;
        this.destinationId = destinationId;
        this.lifeTime = lifeTime;
        this.date = date;
        estimateRate();
        estimatePrice();
    }

    public Call() {
    }

    public abstract void estimatePrice();

    //falta darle un valor a rate
    public void estimateRate() {

    }

    @Override
    public String toString() {
        return "Call{" +
                "id=" + id +
                ", remittentId=" + remittentId +
                ", destinationId=" + destinationId +
                ", lifeTime=" + lifeTime +
                ", date=" + date +
                ", rate=" + rate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Call call = (Call) o;
        return lifeTime == call.lifeTime && Double.compare(call.rate, rate) == 0 && Objects.equals(id, call.id) && Objects.equals(remittentId, call.remittentId) && Objects.equals(destinationId, call.destinationId) && Objects.equals(date, call.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, remittentId, destinationId, lifeTime, date, rate);
    }

    public void setLifeTime(long lifeTime) {
        this.lifeTime = lifeTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public Long getRemittentId() {
        return remittentId;
    }

    public void setRemittentId(Long remittentId) {
        this.remittentId = remittentId;
    }

    public Long getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(Long destinationId) {
        this.destinationId = destinationId;
    }

    public long getLifeTime() {
        return lifeTime;
    }

    public void setLifeTime(Long lifeTime) {
        this.lifeTime = lifeTime;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }
}
