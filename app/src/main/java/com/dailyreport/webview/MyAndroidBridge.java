package com.dailyreport.webview;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import androidx.core.content.ContextCompat;

import com.dailyreport.MainActivity;

public class MyAndroidBridge {
    private Handler mHandler = null;
    private WebView mWebView = null;
    private Context mContext = null;


    public MyAndroidBridge(WebView _mWebView, Context context){
        mWebView = _mWebView;
        mHandler = new Handler();
        mContext = context;
    }

    @JavascriptInterface
    public void rangeWalking(String callbaId, String startData, String endData)
    //public void rangeWalking()
    {
        //#1. fit read permission - ACTIVITY_RECOGNITION
        //#2. OAuth 2.0 Client ID 로그인 - 구글 아이디
        ((MainActivity)MainActivity.mContext).startTimeFit = startData;
        ((MainActivity)MainActivity.mContext).endTimeFit = endData;
        ((MainActivity)MainActivity.mContext).callbackId = callbaId;
        //String callbaId ="";
        Log.d("YYYM", "rangeWalking: "+startData+", end:"+endData);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            if (ContextCompat.checkSelfPermission
                    (MainActivity.mContext, Manifest.permission.ACTIVITY_RECOGNITION) != PackageManager.PERMISSION_GRANTED)
            {
                ((MainActivity)MainActivity.mContext).requestPermission(Manifest.permission.ACTIVITY_RECOGNITION,
                        MainActivity.REQUEST_CHECK_PERMISSION_RECOGNITION);
            }
            else{
                ((MainActivity)MainActivity.mContext).checkFitnessPermission();
            }
        }
        else //
        {
            ((MainActivity)MainActivity.mContext).checkFitnessPermission();
        }

    }

    @JavascriptInterface
    public void call_log(String msg){
        Log.i(getClass().getName(),"call_log() msg:"+ msg);
    }

    @JavascriptInterface
    public void callMessage(){
        //Toast.makeText(mContext, "Web에서 호출된 메시지입니다.", Toast.LENGTH_SHORT).show(); // Web으로 호출을 반환시, 동일 Activity로는 수행이 불가능하기때문에 스레드를 하나 생성해 호출해주어야합니다.
        Log.i(getClass().getName(),"callMessage()을 호출");
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                try{
                    mWebView.loadUrl("javascript:web_callback('콜백메시지입니다. test');");
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    @JavascriptInterface
    public void UploadFile()
    {
        Log.d("YYYM", "UploadFile.");

        String path = mContext.getExternalFilesDir(Environment.DIRECTORY_DCIM).getAbsolutePath();

    }

    @JavascriptInterface
    public void DownloadFile(String callbaId, String strSumthing)
    {
        Log.d("YYYM", "UploadFile."+callbaId+ ", ");


    }

}
