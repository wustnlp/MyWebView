package wust.com.mywebview.jsbridge2;

import android.webkit.JsPromptResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class InjectedChromeClient extends WebChromeClient {

    private JsCallJava mJsCallJava;

    public InjectedChromeClient() {
        mJsCallJava = new JsCallJava();
    }

    @Override
    public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
        result.confirm(mJsCallJava.call(view, message));
        return true;
    }
}
