package wust.com.mywebview.jsbridge2;

import android.text.TextUtils;

import java.util.HashMap;

public class JSBridge {

    public static final String BRIDGE_NAME = "JSBridge";

    private static JSBridge INSTANCE = new JSBridge();

    private HashMap<String, Class<? extends IInject>> mClassMap = new HashMap<>();

    private JSBridge() {
        mClassMap.put(BRIDGE_NAME, JSLogical.class);
    }

    public static JSBridge getInstance() {
        return INSTANCE;
    }

    public boolean addInjectPair(String name, Class<? extends IInject> clazz) {
        if (!mClassMap.containsKey(name)) {
            mClassMap.put(name, clazz);
            return true;
        }
        return false;
    }

    public boolean removeInjectPair(String name, Class<? extends IInject> clazz) {
        if (TextUtils.equals(name, BRIDGE_NAME)) {
            return false;
        }
        Class clazzValue = mClassMap.get(name);
        if (null != clazzValue && (clazzValue == clazz)) {
            mClassMap.remove(name);
            return true;
        }
        return false;
    }

    public HashMap<String, Class<? extends IInject>> getInjectPair() {
        return mClassMap;
    }
}

