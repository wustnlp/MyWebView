package wust.com.mywebview.jsbridgelib;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import wust.com.mywebview.R;

public class SampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setVisibility(View.GONE);

        initView();
    }

    public void initView() {
        String url = "file:///android_asset/bridge_demo.html";
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.webview_layout);
        if (fragment == null || !(fragment instanceof WebViewFragment)) {
            fragment = (WebViewFragment.createWebViewFragment(url));
        }
        fm.beginTransaction().replace(R.id.webview_layout, fragment).commit();
    }
}
