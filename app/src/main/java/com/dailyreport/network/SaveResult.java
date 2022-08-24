package com.dailyreport.network;

import com.google.gson.annotations.SerializedName;

public class SaveResult {

    @SerializedName("rsCd")
    private String rsCd;

    @SerializedName("jsonStringDatas")
    private String jsonStringDatas;

    // toString()을 Override 해주지 않으면 객체 주소값을 출력함
    @Override
    public String toString() {
        return "PostResult{" +
                "result=" + rsCd +
                '}';
    }

    public String getRsCd() {
        return rsCd;
    }

    public void setRsCd(String rsCd) {
        this.rsCd = rsCd;
    }

    public String getJsonStringDatas() {
        return jsonStringDatas;
    }

    public void setJsonStringDatas(String jsonStringDatas) {
        this.jsonStringDatas = jsonStringDatas;
    }
}
