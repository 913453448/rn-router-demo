package com.example;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends MyReactActivity implements MyFragmentDelegate {
    private ReactFragment reactFragment;
    private List<Fragment> fragments = new LinkedList<>();

    /**
     * Returns the name of the main component registered from JavaScript.
     * This is used to schedule rendering of the component.
     */
    @Override
    protected String getMainComponentName() {
        return "Example";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (reactFragment == null) {
            reactFragment = new ReactFragment();
            reactFragment.setRootView(getRootView());
        }
        push(reactFragment, "ReactFragment");
    }

    @Override
    public void push(Fragment fragment, String tag) {
        getSupportFragmentManager().beginTransaction().add(R.id.id_main_container, fragment, tag).commit();
    }

    @Override
    public void pop() {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        getSupportFragmentManager().beginTransaction().remove(fragments.get(fragments.size()-1)).commit();
    }

    @Override
    public void startRN(String params) {
        //获取所有的fragment
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        //遍历所有的fragment
        for (Fragment fragment : fragments) {
            //当为ReactFragment的时候，显示
            if (fragment instanceof ReactFragment) {
                fragmentTransaction.show(fragment);
            } else {
                //隐藏除ReactFragment以外的所有fragment
                fragmentTransaction.hide(fragment);
            }
        }
        fragmentTransaction.commit();
        //发通知rn跳转页面
        RouterModule.startReactPage("login");
    }

    @Override
    public void reactBack() {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        for (Fragment fragment : fragments) {
            fragmentTransaction.show(fragment);
        }
        fragmentTransaction.commit();
    }
}
