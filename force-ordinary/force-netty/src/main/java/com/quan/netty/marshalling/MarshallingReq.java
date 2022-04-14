package com.quan.netty.marshalling;

/**
 * @author Force-oneself
 * @description MarshallingReq
 * @date 2022-04-14
 */
public class MarshallingReq {

    private String address;
    private String phoneNumber;
    private String productName;
    private int subReqID;
    private String userName;

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

    public void setSubReqID(int subReqID) {
        this.subReqID = subReqID;
    }

    public int getSubReqID() {
        return subReqID;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }
}
