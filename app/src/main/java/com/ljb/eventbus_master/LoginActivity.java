package com.ljb.eventbus_master;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class LoginActivity extends AppCompatActivity {
    private Button btn_register;
    private TextView content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        content = findViewById(R.id.content);
        btn_register = findViewById(R.id.btn_register);
        //注册
        EventBus.getDefault().register(this);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    @Subscribe()
    public void onMessageEvent(RegisterEvent registerEvent) {
        Log.e("ggg", "onMessageEvent-----------------------");
        content.setText(" Name:" + registerEvent.userBean.getUserName()
                + "\n Password:" + registerEvent.userBean.getUserPassword());
    }
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onMessageEvent(MessageEvent messageEvent) {
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
