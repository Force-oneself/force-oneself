package com.quan.netty.marshalling;

import java.io.Serializable;

/**
 * @author Force-oneself
 * @description MarshallingResp
 * @date 2022-04-14
 */
public class MarshallingResp implements Serializable {

    private static final long serialVersionUID = 1690212024000173617L;
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
