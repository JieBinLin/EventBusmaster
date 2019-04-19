package com.ljb.eventbus_master;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class SecondActivity extends AppCompatActivity {
    private TextView content;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        content = findViewById(R.id.content);
        button = findViewById(R.id.button);
        EventBus.getDefault().register(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                startActivity(intent);
            }
        });
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(MessageEvent event) {
        content.setText(event.message);
        //删除粘性事件，以便它们不再被传递
        MessageEvent stickyEvent = EventBus.getDefault().getStickyEvent(MessageEvent.class);
        if(stickyEvent!=null){
            EventBus.getDefault().removeStickyEvent(stickyEvent);
        }
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
