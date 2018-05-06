package com.example;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

/**
 * Created by yinqingyang on 2018/5/6.
 */

public class RouterModule extends ReactContextBaseJavaModule {
    private static ReactApplicationContext reactApplicationContext;
    public RouterModule(ReactApplicationContext reactContext) {
        super(reactContext);
        reactApplicationContext=reactContext;
    }

    @Override
    public String getName() {
        return "RouterModule";
    }

    /**
     * 跳转到原生页面
     * @param data
     * @param callback
     */
    @ReactMethod
    public void startNative(ReadableMap data, Promise callback) {
        if(getCurrentActivity() instanceof MyFragmentDelegate){
            MyFragmentDelegate fragmentDelegate= (MyFragmentDelegate) getCurrentActivity();
            //调用push方法，添加一个fragment到activity
            fragmentDelegate.push(new PlusOneFragment(),"PlusOneFragment");
        }
    }

    /**
     * rn点击返回的时候
     */
    @ReactMethod
    public void reactBack() {
        if(getCurrentActivity() instanceof MyFragmentDelegate){
            //调用reactBack方法，使所有的fragment可见
            MyFragmentDelegate fragmentDelegate= (MyFragmentDelegate) getCurrentActivity();
            fragmentDelegate.reactBack();
        }
    }

    /**
     * 原生跳转rn页面
     * @param params
     */
    public static void startReactPage(String params){
        reactApplicationContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit("ACTION_PAGE", params);
    }
}
