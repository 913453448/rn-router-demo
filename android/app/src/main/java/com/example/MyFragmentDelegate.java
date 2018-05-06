package com.example;

import android.support.v4.app.Fragment;

/**
 * Created by yinqingyang on 2018/5/6.
 */

public interface MyFragmentDelegate {
    void push(Fragment fragment,String tag);
    void pop();
    void startRN(String params);
    void reactBack();
}
