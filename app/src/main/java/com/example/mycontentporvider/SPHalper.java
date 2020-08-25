package com.example.mycontentporvider;

import android.content.Context;
import android.content.SharedPreferences;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SPHalper {
    private SharedPreferences sp;
    private Context context;

    SPHalper(Context context){
        sp = context.getSharedPreferences("mysp", Context.MODE_PRIVATE);
        this.context=context;
    }


    public void save(Items item){
        SharedPreferences.Editor editor=sp.edit();
        editor.putBoolean(item.name,true);
        Iterator<String> iterator=item.iterator;
        String i;
        while(iterator.hasNext()){
            i= iterator.next();
            editor.putString(i, item.list.get(i));
        }
        editor.apply();
    }
    public Map<String,String> read(Items item){
        Map<String,String> data=new HashMap<String,String>();
        Iterator<String> iterator=item.iterator;
        String i;
        while(iterator.hasNext()){
            String temp="0000";
            String t;
            i= iterator.next();
            if(sp.contains("setting1")) System.out.println("++++++++++++++++++++++++");
            t=sp.getString(i,temp);
            data.put(i,t);
        }
        return data;
    }



    public static class Items{
        public String name;
        public Iterator<String> iterator;
        public Map<String,String> list;
        public Items(String name,Map<String,String> list){
            init(name);
            this.list=list;
        }
        public Items(String name){init(name);}
        protected void init(String name){
            List<String> alist=new ArrayList<String>();
            alist.add("setting1");
            alist.add("setting2");
            alist.add("setting3");
            iterator=alist.iterator();
            this.name=name;
        }
    }
}
