package com.example.mycontentporvider;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import androidx.annotation.Nullable;

public class MyContentResolver{
    private Context context;
    private ContentResolver contentResolver;

    public MyContentResolver(@Nullable Context context) {
        assert context != null;
        contentResolver=context.getContentResolver();
        this.context=context;

    }
    public void getMsg(){
        try{
            Uri uri=Uri.parse("content://sms/");
            Cursor cursor=contentResolver.query(uri,new String[]{"address","date","type","body"},null,null,null);

            assert cursor != null;
            while(cursor.moveToNext()){
                String address=cursor.getString(0);
                String date=cursor.getString(1);
                String type=cursor.getString(2);
                String body=cursor.getString(3);
                System.out.println("==== === === === === === === ===");
                System.out.println("address: "+address);
                System.out.println("date   : "+date);
                System.out.println("type   : "+type);
                System.out.println("body   : "+body);

            }

            System.out.println("==== === === === === === === ===");
            cursor.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
