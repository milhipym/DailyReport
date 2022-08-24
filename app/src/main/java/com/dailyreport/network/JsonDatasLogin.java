package com.dailyreport.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JsonDatasLogin {
    @SerializedName("ErrorMsg")
    @Expose
    private String errorMsg;
    @SerializedName("ErrorCode")
    @Expose
    private String errorCode;


    // toString()을 Override 해주지 않으면 객체 주소값을 출력함
    @Override
    public String toString() {
        return "{" +
                "errorMsg=" + errorMsg +
                ", errorCode='" + errorCode + '\'' +
                '}';
    }


    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

}
