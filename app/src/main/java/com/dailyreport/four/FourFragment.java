package com.dailyreport.four;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.dailyreport.R;
import com.dailyreport.webview.MyAndroidBridge;
import com.dailyreport.webview.WebViewChromeClient;
import com.dailyreport.webview.WebViewCustomClient;

public class FourFragment extends Fragment {
    public ViewGroup rootview;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootview = (ViewGroup) inflater.inflate(R.layout.fragment_shap_one, container, false);
        webSetting();
        return rootview;
    }

    public boolean webSetting(){
/*
        WebViewClient()는 웹페이지 로딩할때 생기는 콜백함수로 구성.
         (onPageStarted, shouldOverridingUrlLoading, onPageFinishied)
        WebChromeClient()는 웹페이지에서 일어나는 액션들에 관한 콜백함수로 구성.
         (onProgressChanged, onCreateWindow, onCloseWindow)
        https://kimdabang.tistory.com/entry/안드로이드-WebViewClient와-WebChromeClient
*/
        WebView webView = rootview.findViewById(R.id.webView);
        MyAndroidBridge myAndroidBridge = new MyAndroidBridge(webView, getContext());
        webView.addJavascriptInterface(myAndroidBridge, "aos");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(false);
        webView.getSettings().setDefaultTextEncodingName("utf-8");
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);//자바스크립트가 자동으로 창을 열수 있게
        webView.getSettings().setLoadsImagesAutomatically(true);       //이미지 자동 로드
        webView.getSettings().setLoadWithOverviewMode(true);           //컨텐츠가 웹뷰보다 클떄 스크린크기에 맞추기
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE); //웹뷰 성능개선
        webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);    //웹뷰 성능개선_하드웨어가속
        //webView.setWebViewClient(new WebViewClient());  //웹뷰 콘트롤을 위한 셋팅_기본
        webView.setWebViewClient(new WebViewCustomClient());       //웹뷰 콘트롤을 위한 셋팅_커스텀
        webView.setWebChromeClient(new WebViewChromeClient());     //크롬웹뷰 콘트롤을 위한 셋팅_커스텀
        webView.getSettings().setAllowFileAccess(true);            //로컬웹뷰 'ERR_ACCESS_DENIDED' 타켓30부터 셋팅해줘야 함
        webView.getSettings().setAllowFileAccessFromFileURLs(true);//로컬웹뷰 'ERR_INVALID_URI' 타켓30부터 셋팅해줘야 함
        webView.loadUrl("file:///android_asset/www/MyHome.html");
        webView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    if (keyCode == KeyEvent.KEYCODE_BACK)
                    {
                        if (webView != null)
                        {
                            if (webView.canGoBack()){webView.goBack();}
                            else {getActivity().onBackPressed();}
                        }
                    }
                }
                return true;
            }
        });
        return true;
    }
}
