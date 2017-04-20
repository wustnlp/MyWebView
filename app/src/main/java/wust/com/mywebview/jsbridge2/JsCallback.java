package wust.com.mywebview.jsbridge2;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.webkit.WebView;

import org.json.JSONObject;

import java.lang.ref.WeakReference;

public class JsCallback {


    private static Handler mHandler = new Handler(Looper.getMainLooper());

    private static final String CALLBACK_JS_FORMAT = "javascript:JsBridge.onComplete('%s', %s);";

    private String mSid;
    private WeakReference<WebView> mWebViewRef;

    public JsCallback(WebView view, String port) {
        mWebViewRef = new WeakReference(view);
        mSid = port;
    }

    public void apply(boolean isSuccess, String message, JSONObject object) {
        if (mWebViewRef.get() == null) {
            //throw new JsCallbackException("the WebView related to the JsCallback has been recycled");
            return;
        }

        JSONObject result = new JSONObject();

        try {
            JSONObject code=new JSONObject();
            code.put("code", isSuccess ? 0 : 1);
            if(!isSuccess && !TextUtils.isEmpty(message)){
                code.putOpt("msg",message);
            }
            if(isSuccess){
                code.putOpt("msg", TextUtils.isEmpty(message)?"SUCCESS":message);
            }
            result.putOpt("status", code);
            if(null!=object){
                result.putOpt("data",object);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        final String jsFunc = String.format(CALLBACK_JS_FORMAT, mSid, String.valueOf(result));

        if (mWebViewRef != null && mWebViewRef.get() != null) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mWebViewRef.get().loadUrl(jsFunc);
                }
            });

        }
    }

}
