package wust.com.mywebview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import wust.com.mywebview.arouter.Main2Activity;
import wust.com.mywebview.jsbridge2.Bridge2Activity;

public class WebViewActivity extends AppCompatActivity {

    @BindView(R.id.native_inject)
    Button nativeInject;
    @BindView(R.id.jsbridge)
    Button jsbridge;
    @BindView(R.id.arouter)
    Button arouter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.native_inject, R.id.jsbridge, R.id.arouter})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.native_inject:
                skipIntent(MainActivity.class);
                break;
            case R.id.jsbridge:
                skipIntent(Bridge2Activity.class);
                break;
            case R.id.arouter:
                skipIntent(Main2Activity.class);
                break;
        }
    }

    private void skipIntent(Class<?> cls) {
        Intent intent = new Intent();
        intent.setClass(this,cls);
        startActivity(intent);
    }
}
