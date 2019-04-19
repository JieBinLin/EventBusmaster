package com.ljb.eventbus_master;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.greenrobot.eventbus.EventBus;

public class RegisterActivity extends AppCompatActivity {
    private EditText name, password;
    private Button btn_ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserBean userBean = new UserBean();
                userBean.setUserName(name.getText().toString().trim());
                userBean.setUserPassword(password.getText().toString().trim());
                EventBus.getDefault().post(new RegisterEvent(userBean));
                finish();
            }
        });
    }

    private void initView() {
        name = findViewById(R.id.name);
        password = findViewById(R.id.password);
        btn_ok = findViewById(R.id.btn_ok);
    }
}
