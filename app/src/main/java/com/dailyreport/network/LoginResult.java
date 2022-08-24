package com.dailyreport.network;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoginResult {

    @SerializedName("rsCd")
    public String rsCd;

    @SerializedName("jsonStringDatas")
    public String jsonStringDatas;

    @SerializedName("responseText")
    public String responseText;

    // toString()을 Override 해주지 않으면 객체 주소값을 출력함
    @Override
    public String toString() {
        return "PostResult{" +
                "result=" + rsCd +
                ", responseText='" + responseText + '\'' +
                '}';
    }

    public String getRsCd() { return rsCd; }
    public void setRsCd(String rsCd) {
        this.rsCd = rsCd;
    }
    public String getJsonStringDatas() { return jsonStringDatas; }
    public void setJsonStringDatas(String jsonStringDatas) { this.jsonStringDatas = jsonStringDatas; }
    public String getResponseText() {
        return responseText;
    }
    public void setResponseText(String responseText) {
        this.responseText = responseText;
    }

}
