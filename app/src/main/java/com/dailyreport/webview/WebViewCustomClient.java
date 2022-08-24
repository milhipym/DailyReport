package com.dailyreport.webview;

import android.graphics.Bitmap;
import android.util.Log;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;

public class WebViewCustomClient extends WebViewClient {

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        Log.d("YYYM", "shouldOverrideUrlLoading: "+request+", view:"+view);
        return super.shouldOverrideUrlLoading(view, request);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        Log.d("YYYM", "onPageStarted: "+view+" , url:"+url+" favicon:,"+favicon);
        super.onPageStarted(view, url, favicon);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        Log.d("YYYM", "onPageFinished: "+url);
        super.onPageFinished(view, url);
    }

    @Nullable
    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
        //Log.d("YYYM", "shouldInterceptRequest: "+view+" ,"+request);
        return super.shouldInterceptRequest(view, request);
    }

    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        Log.d("YYYM", "onReceivedError: "+view+" ,req:"+request+" ,errer:"+error);
        super.onReceivedError(view, request, error);
    }

    @Override
    public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
        Log.d("YYYM", "onReceivedHttpError: "+view+" ,req:"+request+" ,err:"+errorResponse);
        super.onReceivedHttpError(view, request, errorResponse);
    }
}
