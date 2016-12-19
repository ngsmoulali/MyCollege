package ngsm.com.mycollege;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class WebSiteActivity extends Activity {

    private WebView wvResult;
    private ProgressBar pbWeb;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_site_layout);

        wvResult = (WebView) findViewById(R.id.wvWebSite);
        pbWeb = (ProgressBar) findViewById(R.id.pbWeb);

        wvResult.getSettings().setJavaScriptEnabled(true);


    }

    @Override
    protected void onStart() {
        super.onStart();



        wvResult.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                pbWeb.setVisibility(View.INVISIBLE);
                super.onPageFinished(view, url);
            }
        });

        WebSettings webSettings = wvResult.getSettings();
        webSettings.setJavaScriptEnabled(true);

        wvResult.loadUrl("http://www.svuniversity.ac.in/");





        wvResult.setWebChromeClient(new WebChromeClient());
    }

}