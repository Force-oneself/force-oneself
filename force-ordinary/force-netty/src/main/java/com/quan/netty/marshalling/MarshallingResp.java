package com.quan.netty.marshalling;

/**
 * @author Force-oneself
 * @description MarshallingResp
 * @date 2022-04-14
 */
public class MarshallingResp {

    private int subReqID;
    private int respCode;
    private String desc;

    public void setSubReqID(int subReqID) {
        this.subReqID = subReqID;
    }

    public int getSubReqID() {
        return subReqID;
    }

    public void setRespCode(int respCode) {
        this.respCode = respCode;
    }

    public int getRespCode() {
        return respCode;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
