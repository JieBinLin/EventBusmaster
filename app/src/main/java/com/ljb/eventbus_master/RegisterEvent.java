package com.ljb.eventbus_master;

public class RegisterEvent {
    public final UserBean userBean;

    public RegisterEvent(UserBean userBean) {
        this.userBean = userBean;
    }
}
