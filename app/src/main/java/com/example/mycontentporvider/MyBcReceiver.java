package com.example.mycontentporvider;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import android.widget.TextView;

public class MyBcReceiver extends BroadcastReceiver {
    private final String ACTION_BOOT = "com.example.sendx";
    private TextView textView;
    MyBcReceiver(TextView textView){
        this.textView=textView;
    }


    @Override
    public void onReceive(Context context, Intent intent) {

        if (ACTION_BOOT.equals(intent.getAction())) {
            textView.setText(intent.getStringExtra("_name") );
        }
        else
        System.out.println("==============================="+intent.getAction());
    }
}
