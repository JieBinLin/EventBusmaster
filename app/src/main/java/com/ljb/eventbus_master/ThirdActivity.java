package com.ljb.eventbus_master;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class ThirdActivity extends AppCompatActivity {
    private TextView content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        content = findViewById(R.id.content);
        EventBus.getDefault().register(this);
    }
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(MessageEvent event) {
        content.setText(event.message);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            //注销
            EventBus.getDefault().unregister(this);
        }
    }
}
