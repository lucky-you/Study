package com.zhowin.study.model;

/**
 * 礼物的model
 */
public class ThatGiftList {

    private int sendID;

    private String sendName;

    private int receiveID;

    private String receiveName;

    private String giftName;

    private String giftNumber;

    public ThatGiftList(int sendID, String sendName, int receiveID, String receiveName, String giftName, String giftNumber) {
        this.sendID = sendID;
        this.sendName = sendName;
        this.receiveID = receiveID;
        this.receiveName = receiveName;
        this.giftName = giftName;
        this.giftNumber = giftNumber;
    }

    public int getSendID() {
        return sendID;
    }

    public void setSendID(int sendID) {
        this.sendID = sendID;
    }

    public String getSendName() {
        return sendName;
    }

    public void setSendName(String sendName) {
        this.sendName = sendName;
    }

    public int getReceiveID() {
        return receiveID;
    }

    public void setReceiveID(int receiveID) {
        this.receiveID = receiveID;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    public String getGiftName() {
        return giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }

    public String getGiftNumber() {
        return giftNumber;
    }

    public void setGiftNumber(String giftNumber) {
        this.giftNumber = giftNumber;
    }
}
