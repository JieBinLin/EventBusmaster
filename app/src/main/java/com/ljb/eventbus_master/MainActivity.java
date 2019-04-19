package com.ljb.eventbus_master;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {
    private TextView content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        content = findViewById(R.id.content);
        //注册
        EventBus.getDefault().register(this);
        //启动一个线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3*1000);
                    EventBus.getDefault().post(new MessageEvent("你好，我可以更新UI吗!"));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void UpdateUI(MessageEvent messageEvent) {
        content.setText(messageEvent.message);
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
