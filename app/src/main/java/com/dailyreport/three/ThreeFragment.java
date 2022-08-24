package com.dailyreport.three;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.dailyreport.MainActivity;
import com.dailyreport.R;
import com.dailyreport.network.AeroxSignAPI;
import com.dailyreport.network.GetWeekUsage;
import com.dailyreport.network.LoginResult;
import com.dailyreport.network.LoginResult2;
import com.dailyreport.network.SaveResult;
import com.dailyreport.network.SignResult;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ThreeFragment extends Fragment {
    public ViewGroup rootview;
    private Button   btapi, btapi2, btapi3, btapi4;
    private TextView tvapi, tvapi2, tvapi3, tvapi4;

    public String APLRealUrl   = "https://cdsm.mdbins.com:8485/";
    public String APLDevApiUrl = "http://10.88.23.23:18084/";
    public String DSMRealUrl   = "https://dsm-test.dbins.net/";

    public int networkLimitTime = 600;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = (ViewGroup) inflater.inflate(R.layout.fragment_shap_three, container, false);
        if (savedInstanceState != null) {

        }
        initSetting();
        return rootview;
    }

    private void initSetting() {
        btapi = rootview.findViewById(R.id.buttonapi);
        tvapi = rootview.findViewById(R.id.tvapi);
        btapi2 = rootview.findViewById(R.id.buttonapi2);
        tvapi2 = rootview.findViewById(R.id.tvapi2);
        btapi3 = rootview.findViewById(R.id.buttonapi3);
        tvapi3 = rootview.findViewById(R.id.tvapi3);
        btapi4 = rootview.findViewById(R.id.buttonapi4);
        tvapi4 = rootview.findViewById(R.id.tvapi4);

        btapi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendApl();
            }
        });

        btapi2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendDsm();
            }
        });

        btapi3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendDsmJson();
            }
        });

        btapi4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { getWeekUsages(); }
        });
    }

    private void getWeekUsages() {
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(networkLimitTime, TimeUnit.SECONDS)
                .readTimeout(networkLimitTime, TimeUnit.SECONDS)
                .writeTimeout(networkLimitTime, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DSMRealUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("userId", "81600612");
        AeroxSignAPI api = retrofit.create(AeroxSignAPI.class);
        api.getweekusage(param).enqueue(new Callback<GetWeekUsage>() {
            @Override
            public void onResponse(Call<GetWeekUsage> call, Response<GetWeekUsage> response) {
                Log.d("YYYM", "onResponse1: "+response.toString());
                Log.d("YYYM", "onResponse2: "+response.body());
                Log.d("YYYM", "onResponse3: "+response.raw());

                GetWeekUsage result = response.body();
                Log.d("YYYM", "isSuccessful : 성공 \n" + result.toString());
                tvapi4.setText(result.toString());
            }

            @Override
            public void onFailure(Call<GetWeekUsage> call, Throwable t) {
                tvapi4.setText(t.toString());
            }
        });
    }

    private void sendDsmJson() {
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(networkLimitTime, TimeUnit.SECONDS)
                .readTimeout(networkLimitTime, TimeUnit.SECONDS)
                .writeTimeout(networkLimitTime, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DSMRealUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        // POST
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("transKeyYn", "2");
        param.put("userId", "81600612");
        param.put("passWd", "xmin73@M");
        AeroxSignAPI api = retrofit.create(AeroxSignAPI.class);
        api.loginSend2(param).enqueue(new Callback<LoginResult2>() {
            @Override
            public void onResponse(Call<LoginResult2> call, Response<LoginResult2> response) {
                Log.d("YYYM", "onResponse1: "+response.toString());
                Log.d("YYYM", "onResponse2: "+response.body());
                Log.d("YYYM", "onResponse3: "+response.raw());
                LoginResult2 result = response.body();

                Log.d("YYYM", "isSuccessful : 성공 \n" + result.toString());

                String rsCd = result.getRsCd();
                tvapi3.setText(result.toString());
            }

            @Override
            public void onFailure(Call<LoginResult2> call, Throwable t) {
                tvapi3.setText(t.toString());
            }
        });

    }

    private void sendDsm() {
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(networkLimitTime, TimeUnit.SECONDS)
                .readTimeout(networkLimitTime, TimeUnit.SECONDS)
                .writeTimeout(networkLimitTime, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DSMRealUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        // POST
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("transKeyYn", "2");
        param.put("userId", "81600612");
        param.put("passWd", "xmin73@M");
        AeroxSignAPI api = retrofit.create(AeroxSignAPI.class);
        api.loginSend(param).enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
            LoginResult result = response.body();

                Log.d("YYYM", "isSuccessful : 성공 \n" + result.toString());

                String rsCd = result.getRsCd();
                tvapi2.setText(result.toString());
            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
                tvapi2.setText(t.toString());
            }
        });

    }

    private void sendApl() {
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(networkLimitTime, TimeUnit.SECONDS)
                .readTimeout(networkLimitTime, TimeUnit.SECONDS)
                .writeTimeout(networkLimitTime, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APLDevApiUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        // POST
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("custNo", "81600612");
        param.put("dvcd", "2"); // 1 인증, 2 서명
        param.put("encVal", "3030000044399068");  // 알림번호
        param.put("sigTypeDvcd", "z_isPae"); // 서명구분코드
        param.put("srvDvcd", "L");  // 장기/자동차/기타 구분코드

        AeroxSignAPI api = retrofit.create(AeroxSignAPI.class);
        api.signSend(param).enqueue(new Callback<SignResult>() {
            @Override
            public void onResponse(Call<SignResult> call, Response<SignResult> response) {


                if(response.isSuccessful()){
                    SignResult result = response.body();
                    Log.d("YYYM", "isSuccessful : 성공 \n" + result.toString());

                    String rsCd = result.getRsCd();
                    String responseText = result.getResponseText();
                    tvapi.setText(result.toString());
                    if(responseText == null) { responseText = ""; }
                }
                else{
                    Log.d("YYYM", "isSuccessful : 실패 \n"+response.errorBody());
                    //retryPopup(networkErrorMsg);
                }
            }

            @Override
            public void onFailure(Call<SignResult> call, Throwable t) {
                tvapi.setText(t.toString());
                Log.d("YYYM", "onFailure: "+ call.toString());
            }
        });
    }


}
