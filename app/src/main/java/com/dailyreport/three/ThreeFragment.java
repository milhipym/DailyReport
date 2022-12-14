package com.dailyreport.three;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
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
        initSetting2();
        return rootview;
    }

    private void initSetting2() {
        btapi = rootview.findViewById(R.id.five_buttonapi);
        btapi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                //intent.addCategory(Intent.CATEGORY_BROWSABLE);
                //intent.setData(Uri.parse("dongbusign://aerox"));
                intent.setData(Uri.parse("dongbusign://aerox?serviceID=3202208C8100006&custNo=19031826785&serverPublicKeyVer=2016072501&serverPublicKey=B4BFE7420646FA0D858D2E492444ED8C159BD179AEEC078AE7D34A8DFB0B988C89F672B5B930F82A2835925CCBC68C27124FC8222A9F97668DA22A17BFC31BCA23A88DD65417BCAA7892465460D6B0E6AF1A65195C09FAC2DFC3B240DBD98B77B138F81AFD23DF12160C689EFA4C82E13DAF0672A0728716CA50A3045F5CD1530BA2DCFD35245030568FAF7ACEAAC6E3CC2E83C0176DA929AFDFBF8E8D5A505742303068801179E22946F4C608AF0516275A5BDCC153C2EE6B25F126926C1D7C7DAEE09852F821713BFA8DEF0DAC844F8E8906337821F792A0F176B7C10E26C2AE64873E2FE6F79CCF84850ED0E6921F5A74DFD5164DDF837B44316259A9AA7B&ins_plhd_dvn=1&dvcd=2&ment=&userAge=38&userSex=2&browser=chrome&BIO_SIG_TOKEN_NO=83051042186316403475&sigTypeDvcd=inc_p_bio&srvDvcd=lti&encVal=303000044399252"));
                startActivity(intent);

            }
        });
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
                Log.d("YYYM", "isSuccessful : ?????? \n" + result.toString());
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

                Log.d("YYYM", "isSuccessful : ?????? \n" + result.toString());

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

                Log.d("YYYM", "isSuccessful : ?????? \n" + result.toString());

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
        param.put("dvcd", "2"); // 1 ??????, 2 ??????
        param.put("encVal", "3030000044399068");  // ????????????
        param.put("sigTypeDvcd", "z_isPae"); // ??????????????????
        param.put("srvDvcd", "L");  // ??????/?????????/?????? ????????????

        AeroxSignAPI api = retrofit.create(AeroxSignAPI.class);
        api.signSend(param).enqueue(new Callback<SignResult>() {
            @Override
            public void onResponse(Call<SignResult> call, Response<SignResult> response) {


                if(response.isSuccessful()){
                    SignResult result = response.body();
                    Log.d("YYYM", "isSuccessful : ?????? \n" + result.toString());

                    String rsCd = result.getRsCd();
                    String responseText = result.getResponseText();
                    tvapi.setText(result.toString());
                    if(responseText == null) { responseText = ""; }
                }
                else{
                    Log.d("YYYM", "isSuccessful : ?????? \n"+response.errorBody());
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
