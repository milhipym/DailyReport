package com.dailyreport.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoginResult2 {

    @SerializedName("rsCd")
    @Expose
    private String rsCd;
    @SerializedName("responseText")
    @Expose
    private String responseText;
    @SerializedName("jsonDatas")
    @Expose
    private JsonDatasLogin jsonDatas;
    @SerializedName("currentPageNo")
    @Expose
    private Integer currentPageNo;
    @SerializedName("totCnt")
    @Expose
    private Integer totCnt;
    @SerializedName("countPerPage")
    @Expose
    private Integer countPerPage;
    @SerializedName("jsonStringDatas")
    @Expose
    private String jsonStringDatas;
    @SerializedName("messageShowYN")
    @Expose
    private String messageShowYN;
    @SerializedName("svcInfo")
    @Expose
    private String svcInfo;


    // toString()을 Override 해주지 않으면 객체 주소값을 출력함
    @Override
    public String toString() {
        return "PostResult{" +
                "result=" + rsCd +
                ", responseText='" + responseText + '\'' +
                ", jsonDatas='" + jsonDatas +
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

    public JsonDatasLogin getJsonDatas() {
        return jsonDatas;
    }

    public void setJsonDatas(JsonDatasLogin jsonDatas) {
        this.jsonDatas = jsonDatas;
    }

    public Integer getCurrentPageNo() {
        return currentPageNo;
    }

    public void setCurrentPageNo(Integer currentPageNo) {
        this.currentPageNo = currentPageNo;
    }

    public Integer getTotCnt() {
        return totCnt;
    }

    public void setTotCnt(Integer totCnt) {
        this.totCnt = totCnt;
    }

    public Integer getCountPerPage() {
        return countPerPage;
    }

    public void setCountPerPage(Integer countPerPage) {
        this.countPerPage = countPerPage;
    }

    public String getJsonStringDatas() {
        return jsonStringDatas;
    }

    public void setJsonStringDatas(String jsonStringDatas) {
        this.jsonStringDatas = jsonStringDatas;
    }

    public String getMessageShowYN() {
        return messageShowYN;
    }

    public void setMessageShowYN(String messageShowYN) {
        this.messageShowYN = messageShowYN;
    }

    public String getSvcInfo() {
        return svcInfo;
    }

    public void setSvcInfo(String svcInfo) {
        this.svcInfo = svcInfo;
    }

}
