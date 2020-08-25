package com.example.mycontentporvider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mycontentporvider.db.DBHelper;

public class MyContentProvider extends ContentProvider {
    //初始化一些常量
    private static UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

    private static final int INSERT=1;
    private static final int DELETE=2;
    private static final int UPDATE=3;
    private static final int QUERY=4;


    private DBHelper dbOpenHelper;

    //为了方便直接使用UriMatcher,这里addURI,下面再调用Matcher进行匹配

    static{
        matcher.addURI("com.example.myContentProvider", "insert", INSERT);
        matcher.addURI("com.example.myContentProvider", "delete", DELETE);
        matcher.addURI("com.example.myContentProvider", "update", UPDATE);
        matcher.addURI("com.example.myContentProvider", "query" , QUERY);
        matcher.addURI("com.example.myContentProvider", "test"  , INSERT);
    }
    @Override
    public boolean onCreate() {
        dbOpenHelper = new DBHelper(this.getContext(), "test.db", null, 1);
        return true;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        //把数据库打开放到里面是想证明uri匹配完成
        if (matcher.match(uri) == INSERT) {
            SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
            long rowId = db.insert("test", null, contentValues);
            if (rowId > 0) {
                //在前面已有的Uri后面追加ID
                Uri nameUri = ContentUris.withAppendedId(uri, rowId);
                //通知数据已经发生改变
                getContext().getContentResolver().notifyChange(nameUri, null);
                System.out.println("INSERT-------------");
                return nameUri;
            }
        }
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {

        if(matcher.match(uri)==QUERY){
            SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
            return db.query("test",new String[]{"name"},null,null,null,null,null);
        }
        return null;
    }
}
