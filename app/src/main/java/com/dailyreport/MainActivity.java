package com.dailyreport;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.Toast;

import com.dailyreport.five.FiveFragment;
import com.dailyreport.four.FourFragment;
import com.dailyreport.monitoring.DbMonitorFragment;
import com.dailyreport.one.TodayFragment;
import com.dailyreport.three.ThreeFragment;
import com.dailyreport.two.TwoFragment;
import com.dailyreport.webview.MyAndroidBridge;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.FitnessOptions;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.fitness.request.DataReadRequest;
import com.google.android.gms.fitness.result.DataReadResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarMenuView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.Calendar;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    public NavigationBarView navigationBarView;
    public FragmentTransaction transaction;
    public static Context mContext;
    public WebView mWebview ;
    public static final int REQUEST_CHECK_PERMISSION_RECOGNITION 		= 1014;
    public static final int REQUEST_OAUTH_REQUEST_CODE = 1015;
    public String startTimeFit = "";
    public String endTimeFit = "";
    public String callbackId = "";
    public int dailyStepTotal = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setting();
        mContext = this;
        FragmentTransaction trInitPage = getSupportFragmentManager().beginTransaction();
        FiveFragment fiveFragment = new FiveFragment();
        trInitPage.replace(R.id.mainframelayout, fiveFragment).commit();
    }

    private void setting() {

        navigationBarView = findViewById(R.id.mainbottomframe);
        navigationBarView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                transaction = getSupportFragmentManager().beginTransaction();

                switch (item.getItemId())
                {
                    case R.id.today:
                        TodayFragment todayFragment = new TodayFragment();
                        transaction.replace(R.id.mainframelayout, todayFragment).commit();
                        break;
                    case R.id.today2:
                        TwoFragment twoFragment = new TwoFragment();
                        transaction.replace(R.id.mainframelayout, twoFragment).commit();
                        break;
                    case R.id.today3:
                        ThreeFragment threeFragment = new ThreeFragment();
                        transaction.replace(R.id.mainframelayout, threeFragment).commit();
                        break;
                    case R.id.today4:
                        FourFragment fourFragment = new FourFragment();
                        transaction.replace(R.id.mainframelayout, fourFragment).commit();
                        break;
                    case R.id.today5:
                        FiveFragment fiveFragment = new FiveFragment();
                        transaction.replace(R.id.mainframelayout, fiveFragment).commit();
                        break;

                }
                return true;
            }
        });

    }//end init();


    public void changeFragment(String screenName)
    {
        Log.d("YYYM", "changeFragment: "+screenName);
        if (screenName.equals("DbMonitorFragment"))
        {
            DbMonitorFragment dbMonitorFragment = new DbMonitorFragment();
            transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.mainframelayout, dbMonitorFragment).commit();
        }

    }


    public void requestPermission(final String permission, final int reqCode)
    {
        if(permission.equals(Manifest.permission.ACTIVITY_RECOGNITION) ) {
            ActivityCompat.requestPermissions(this, new String[] { permission }, reqCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode)
        {
            case REQUEST_CHECK_PERMISSION_RECOGNITION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    checkFitnessPermission();
                }
                else
                {
                    //얼럿으로 경고창?
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        Log.d("YYYM", "onActivityResult: "+resultCode+", data:"+data);
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_OAUTH_REQUEST_CODE) //구글핏_OAuth_인증_로그인
        {
            if (resultCode == Activity.RESULT_OK){
                Log.d("YYYM", "onActivityResult:  구글핏권한: " + resultCode + ", :" + data);
                subscribe(DataType.TYPE_STEP_COUNT_DELTA);
            }
            else if (requestCode == Activity.RESULT_CANCELED){
                Log.d("YYYM", "onActivityResult:  구글핏권한 취소: " + resultCode + ", :" + data);
                //캔슬 내역 회신
            }
        }
    }

    public void checkFitnessPermission()
    {
        //STEP1. 구글핏 필요권한 set
        FitnessOptions fitnessOptions = FitnessOptions.builder()
                .addDataType(DataType.TYPE_STEP_COUNT_DELTA) //걸음수
                .build();

        //STEP2. 구글핏 권한요청
        if (!GoogleSignIn.hasPermissions(GoogleSignIn.getLastSignedInAccount(this), fitnessOptions))
        {
            GoogleSignIn.requestPermissions(
                    this,
                    REQUEST_OAUTH_REQUEST_CODE,
                    GoogleSignIn.getLastSignedInAccount(this),
                    fitnessOptions);
        }
        else //구글핏 기승인시
        {
            subscribe(DataType.TYPE_STEP_COUNT_DELTA);
        }
    }

    /*구글핏과 관련된 다른앱이 설치되지 않는 경우.
    백그라운드 및 앱이 실행되지 않을때 기록되지 않을수 있음.*/
    public void subscribe(DataType dataType) {
        Fitness.getRecordingClient(this, GoogleSignIn.getLastSignedInAccount(this))
                .subscribe(dataType)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Log.d("YYYM", "Successfully subscribed!");
                            readData(dataType);
                        }
                        else{ Log.d("YYYM", "There was a problem subscribing.", task.getException()); }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(mContext, "addOnFailureListener"+e, Toast.LENGTH_SHORT).show();
                    }
                });
    }
    //실제 데이터 가져오기
    public void readData(DataType dataType){
        final Calendar cal = Calendar.getInstance();
        // 시작 시간
        int sYYYY = Integer.parseInt(startTimeFit.substring(0,4));
        int sMM =  Integer.parseInt(startTimeFit.substring(4,6));
        int sDD = Integer.parseInt(startTimeFit.substring(6,8));
        cal.set(sYYYY, sMM-1, sDD,0, 0, 0);
        long startTime = cal.getTimeInMillis();

        // 종료 시간
        int eYYYY = Integer.parseInt(endTimeFit.substring(0,4));
        int eMM =  Integer.parseInt(endTimeFit.substring(4,6));
        int eDD = Integer.parseInt(endTimeFit.substring(6,8));
        cal.set(eYYYY, eMM-1, eDD,23, 59, 59);
        long endTime = cal.getTimeInMillis();
        // 시작 시간
/*      cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        long startTime = cal.getTimeInMillis();
        // 종료 시간
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
        long endTime = cal.getTimeInMillis();*/
        Log.d("YYYM", "readData: startTime:"+startTime +", endTime:"+endTime);

        Fitness.getHistoryClient(this, GoogleSignIn.getLastSignedInAccount(this))
                .readData(new DataReadRequest.Builder()
                .read(DataType.TYPE_STEP_COUNT_DELTA)
                .setTimeRange(startTime, endTime, TimeUnit.MILLISECONDS)
                .build())
                .addOnSuccessListener(new OnSuccessListener<DataReadResponse>() {
                    @Override
                    public void onSuccess(DataReadResponse dataReadResponse) {
                        DataSet dataStepSet = dataReadResponse.getDataSet(DataType.TYPE_STEP_COUNT_DELTA);
                        Log.d("YYYM", "dataStepSet: "+dataStepSet.getDataType().getName());
                        for (DataPoint dp : dataStepSet.getDataPoints()){
                            long timeLong = dp.getTimestamp(TimeUnit.MILLISECONDS);
                            long timelongHours = (timeLong/1000) / 60 / 60%24;
                            long timelongMinuts = (timeLong/1000) / 60 % 60;
                            long timelongSeconds = (timeLong/1000) % 60;
                            String time = String.format("%02d:%02d:%02d", timelongHours, timelongMinuts, timelongSeconds);
                            Log.d("YYYM", "\ttime: "+time);
                            for (Field field : dp.getDataType().getFields()) {
                                Log.d("YYYM", "\tField: " + field.getName() + " Value: " + dp.getValue(field));
                                Log.d("YYYM", "user_input: "+dp.getOriginalDataSource().getStreamName());
                                if (!"user_input".equals(dp.getOriginalDataSource().getStreamName()))
                                {
                                    int step = dp.getValue(field).asInt();
                                    if (dataType == DataType.TYPE_STEP_COUNT_DELTA)
                                    { dailyStepTotal += step; }
                                    Log.d("YYYM", "dailyStepTotal: "+dailyStepTotal);
                                }
                            }
                        }
                        mWebview.loadUrl("javascript:web_callback('" + dailyStepTotal + "')");
                        dailyStepTotal = 0;
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("YYYM", "getHistoryClient_onFailure");
                    }
                });
            Log.d("YYYM", "final_dailyStepTotal: "+dailyStepTotal);
            //mWebview.loadUrl("javascript:web_callback('dailyStepTotal');");
            //mWebview.loadUrl("javascript:web_callback('" + dailyStepTotal + "')");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}