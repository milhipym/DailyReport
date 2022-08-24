package com.dailyreport.network;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AeroxSignAPI {

    //@POST("posts")
    @POST("dsm/zcommon/c/web/infra/ptgrCpltRc.do")
    Call<SignResult> signSend(@Body HashMap<String, Object> param) ;

    @POST("dsm/dvcphone/zcommon/b/savBioResult.do")
    Call<SaveResult> saveSend(@Body HashMap<String, Object> param) ;

    @POST("dsm/zcommon/callDoAppLogin.do")
    Call<LoginResult> loginSend(@Body HashMap<String, Object> param) ;

    @POST("dsm/zcommon/callDoAppLogin.do")
    Call<LoginResult2> loginSend2(@Body HashMap<String, Object> param) ;

    @POST("dsm/zcommon/callMtWeek.do")
    Call<GetWeekUsage> getweekusage(@Body HashMap<String, Object> param) ;
}
