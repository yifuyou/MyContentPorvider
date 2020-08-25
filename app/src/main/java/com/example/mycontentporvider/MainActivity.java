package com.example.mycontentporvider;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.security.Permission;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private MyBcReceiver myReceiver;
    private LocalBroadcastManager localBroadcastManager;
    private TextView textView;
    private Button button;
    private Button btn;
    private Map<String, String> map = null;
    private Context context = this;

    private final String[] permissions = new String[]{"android.permission.MANAGE_EXTERNAL_STORAGE",
            "android.permission.READ_SMS"};
    private final int REQUEST_CODE_ASK_PERMISSIONS = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onRequestPermissions(REQUEST_CODE_ASK_PERMISSIONS,permissions);

        textView = findViewById(R.id.textView);
        button = findViewById(R.id.open);
        btn=findViewById(R.id.main_btn);
        //本地广播的发收测试
        // localBroadcastManager=LocalBroadcastManager.getInstance(getBaseContext());
        // myReceiver = new MyBcReceiver(textView);
        //IntentFilter itFilter = new IntentFilter();
        //itFilter.addAction("com.example.sendx");
        //itFilter.addAction("com.example.sendy");
        //localBroadcastManager.registerReceiver(myReceiver,itFilter);
        //spSet();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyContentResolver contentResolver = new MyContentResolver(context);
                contentResolver.getMsg();


                //Intent intent=new Intent("com.example.sendx");
                //intent.putExtra("_name","hehehexiexiexie");

                //localBroadcastManager.sendBroadcast(intent);

            }
        });
        button.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                //Intent intent=new Intent(MainActivity.this,SecondActivity.class);
                //startActivity(intent);
                //spRead();
                Cursor cursor=context.getContentResolver().query(
                        Uri.parse("content://com.example.myContentProvider/query"),null,
                        null,null,null
                );
                if(cursor!=null){
                    while (cursor.moveToNext()){
                        System.out.println("====================");
                        System.out.println(cursor.getString(0));
                    }
                    cursor.close();
                }else{
                    System.out.println(" ====== ======     cursor : null!");
                }
                return true;
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentResolver resolver = context.getContentResolver();
                ContentValues values = new ContentValues();
                values.put("name", "测试");
                Uri uri = Uri.parse("content://com.example.myContentProvider/test");
                resolver.insert(uri, values);

                Toast.makeText(getApplicationContext(), "数据插入成功", Toast.LENGTH_SHORT).show();

            }
        });

    }

    protected void spSet(){
        map = new HashMap<>();
        map.put("setting2", "false");
        map.put("setting3", "true");
        map.put("setting1", "false");
        System.out.println("MainActivity.onCreate");
        System.out.println("MainActivity.onCreate");
        System.out.println(map.get("setting1"));

        SPHalper sp = new SPHalper(getBaseContext());
        SPHalper.Items items = new SPHalper.Items("fack", map);
        sp.save(items);
    }

    protected void spRead(){
        SPHalper sp = new SPHalper(getBaseContext());
        Map<String, String> map_1 = new HashMap<>();
        map_1 = sp.read(new SPHalper.Items("fack"));
        StringBuffer sb = new StringBuffer();
        sb.append("setting1 : ").append(map_1.get("setting1")).append("\n")
                .append("setting2 : ").append(map_1.get("setting2")).append("\n")
                .append("setting3 : ").append(map_1.get("setting3"));
        textView.setText(sb.toString());
    }


    //别忘了将广播取消掉哦~
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //unregisterReceiver(myReceiver);
    }


    public void onRequestPermissions(int requestCode, @NonNull String[] permissions) {
        int count=permissions.length;
        boolean need=false;
        while(count>0){
            count--;
            if(PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(context, permissions[count])){
                need=true;
            }
        }
        if(need)
        ActivityCompat.requestPermissions(MainActivity.this,permissions,requestCode);
    }



}