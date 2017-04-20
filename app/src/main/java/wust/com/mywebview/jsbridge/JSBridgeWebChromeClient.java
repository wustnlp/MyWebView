package wust.com.mywebview.jsbridge;

import android.webkit.JsPromptResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class JSBridgeWebChromeClient extends WebChromeClient {

    /**
     * @param view         The WebView that initiated the callback.
     * @param url          The url of the page requesting the dialog.
     * @param message      Message to be displayed in the window.
     * @param defaultValue The default value displayed in the prompt dialog.
     * @param result       A JsPromptResult used to send the user's reponse to
     *                     javascript.
     * @return boolean Whether the client will handle the prompt dialog.
     */
    @Override
    public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
        result.confirm(JSBridge.callJava(view, message));
        return true;
    }
}
