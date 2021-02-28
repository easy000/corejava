package com.xiong.template;

import java.util.Date;

public abstract class SendCustom {

    public abstract void to();
    public abstract void from();
    public abstract void content();

    public void getDate() {
        System.out.println(new Date());
    }

    public abstract void send();

    public void sendinfo() {
        to();
        from();
        content();
        getDate();
        send();
    }

}
