package com.quan.clickhouse.entity;

import com.quan.clickhouse.enums.Duration;
import com.quan.clickhouse.enums.Type;

import java.util.Date;

public class PricePaid {

    private long price;
    private Date date;
    private String postcode1;
    private String postcode2;

    private Type type;
    private boolean isNew;
    private Duration duration;
    private String addr1;
    private String addr2;
    private String street;
    private String locality;
    private String town;
    private String district;
    private String county;

    // Getter and Setter methods
    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPostcode1() {
        return postcode1;
    }

    public void setPostcode1(String postcode1) {
        this.postcode1 = postcode1;
    }

    public String getPostcode2() {
        return postcode2;
    }

    public void setPostcode2(String postcode2) {
        this.postcode2 = postcode2;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean isNew) {
        this.isNew = isNew;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public String getAddr1() {
        return addr1;
    }

    public void setAddr1(String addr1) {
        this.addr1 = addr1;
    }

    public String getAddr2() {
        return addr2;
    }

    public void setAddr2(String addr2) {
        this.addr2 = addr2;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    @Override
    public String toString() {
        return "UkPricePaid{" +
                "price=" + price +
                ", date=" + date +
                ", postcode1='" + postcode1 + '\'' +
                ", postcode2='" + postcode2 + '\'' +
                ", type=" + type +
                ", isNew=" + isNew +
                ", duration=" + duration +
                ", addr1='" + addr1 + '\'' +
                ", addr2='" + addr2 + '\'' +
                ", street='" + street + '\'' +
                ", locality='" + locality + '\'' +
                ", town='" + town + '\'' +
                ", district='" + district + '\'' +
                ", county='" + county + '\'' +
                '}';
    }
}
