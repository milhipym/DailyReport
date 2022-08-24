package com.dailyreport.network;

import com.google.gson.annotations.SerializedName;

public class SignResult {

    @SerializedName("rsCd")
    private String rsCd;

    @SerializedName("responseText")
    private String responseText;

    // toString()을 Override 해주지 않으면 객체 주소값을 출력함
    @Override
    public String toString() {
        return "PostResult{" +
                "rsCd=" + rsCd +
                ", responseText='" + responseText + '\'' +
                '}';
    }

    public String getRsCd() {
        return rsCd;
    }

    public void setRsCd(String rsCd) {
        this.rsCd = rsCd;
    }

    public String getResponseText() {
        return responseText;
    }

    public void setResponseText(String responseText) {
        this.responseText = responseText;
    }

}
