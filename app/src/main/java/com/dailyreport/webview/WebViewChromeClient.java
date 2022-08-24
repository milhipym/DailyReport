package com.dailyreport.webview;

import android.os.Message;
import android.util.Log;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class WebViewChromeClient extends WebChromeClient {

    public WebViewChromeClient() {
        super();
    }

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        Log.d("YYYM", "onProgressChanged: "+view+", newPro:"+newProgress);
        super.onProgressChanged(view, newProgress);
    }

    @Override
    public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {
        Log.d("YYYM", "onCreateWindow: "+view+" ,isDialogd:"+isDialog+", isUserGesture:"+isUserGesture+" , result:"+resultMsg);
        return super.onCreateWindow(view, isDialog, isUserGesture, resultMsg);
    }

    @Override
    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
        Log.d("YYYM", "onJsAlert: "+view+" ,url:"+url+", msg:"+message+", result:"+result);
        return super.onJsAlert(view, url, message, result);
    }

    @Override
    public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
        Log.d("YYYM", "onJsConfirm: "+view+", url"+url+" ,msg:"+message+" ,result:"+result);
        return super.onJsConfirm(view, url, message, result);
    }
}
