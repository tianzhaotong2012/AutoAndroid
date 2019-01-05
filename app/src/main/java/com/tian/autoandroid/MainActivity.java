package com.tian.autoandroid;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    // 用来获取获取打开其他应用的intent信使
    PackageManager packageManager ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 获取安装包管理器
        packageManager = this.getPackageManager();
        // 用来让用户打开应用的辅助功能，这样辅助服务才会有效
        Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
        startActivity(intent);
    }

    public void open_app(View view) {
        // 获取启动美团的intent
        Intent intent = packageManager.getLaunchIntentForPackage("com.tian.news");
        // 每次启动美团应用时，但是以重新启动应用的形式打开
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        // 跳转
        startActivity(intent);
    }
}
