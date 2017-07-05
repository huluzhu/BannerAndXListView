package com.example.dell.bannerandxlistview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.hjm.bottomtabbar.BottomTabBar;

public class MainActivity extends FragmentActivity {

    private BottomTabBar bottomtb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomTB();
    }

    private void BottomTB() {
        bottomtb = (BottomTabBar) findViewById(R.id.mybottomTB);
        bottomtb.init(getSupportFragmentManager()).setImgSize(20, 20)
                .setFontSize(12)
                .setTabPadding(4, 6, 10)
                .setChangeColor(Color.GREEN, Color.RED)
                .addTabItem("页面1", R.mipmap.ic_launcher, Fragment1.class)
                .addTabItem("页面2", R.mipmap.ic_launcher, Fragment2.class)
                .isShowDivider(false);

    }
}
