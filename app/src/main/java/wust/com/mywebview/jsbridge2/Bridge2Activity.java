package wust.com.mywebview.jsbridge2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import butterknife.BindView;
import butterknife.ButterKnife;
import wust.com.mywebview.R;
import wust.com.mywebview.jsbridge.JSBridgeWebChromeClient;

public class Bridge2Activity extends AppCompatActivity {

    @BindView(R.id.webview)
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bridge);
        ButterKnife.bind(this);

        initWebView();
    }

    private void initWebView() {

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.setWebChromeClient(new InjectedChromeClient());
        webView.loadUrl("file:///android_asset/jsbridge.html");

    }
}
