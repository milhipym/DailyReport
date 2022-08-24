package com.dailyreport.network;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class JsonDatasGetWeekUsages {

    @SerializedName("getWeekUsage")
    @Expose
    private List<GetWeekUsageList> getWeekUsage = null;

    public List<GetWeekUsageList> getGetWeekUsage() {
        return getWeekUsage;
    }

    public void setGetWeekUsage(List<GetWeekUsageList> getWeekUsage) {
        this.getWeekUsage = getWeekUsage;
    }

    // toString()을 Override 해주지 않으면 객체 주소값을 출력함
    @Override
    public String toString() {
        return "{" +
                "getWeekUsage=" + getWeekUsage +
                '}';
    }
}
