package com.dailyreport.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetWeekUsage {

    @SerializedName("rsCd")
    @Expose
    private String rsCd;
    @SerializedName("responseText")
    @Expose
    private Object responseText;
    @SerializedName("jsonDatas")
    @Expose
    private JsonDatasGetWeekUsages jsonDatas;
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
    private Object jsonStringDatas;
    @SerializedName("messageShowYN")
    @Expose
    private String messageShowYN;

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

    public Object getResponseText() {
        return responseText;
    }

    public void setResponseText(Object responseText) {
        this.responseText = responseText;
    }

    public JsonDatasGetWeekUsages getJsonDatas() {
        return jsonDatas;
    }

    public void setJsonDatas(JsonDatasGetWeekUsages jsonDatas) {
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

    public Object getJsonStringDatas() {
        return jsonStringDatas;
    }

    public void setJsonStringDatas(Object jsonStringDatas) {
        this.jsonStringDatas = jsonStringDatas;
    }

    public String getMessageShowYN() {
        return messageShowYN;
    }

    public void setMessageShowYN(String messageShowYN) {
        this.messageShowYN = messageShowYN;
    }

}