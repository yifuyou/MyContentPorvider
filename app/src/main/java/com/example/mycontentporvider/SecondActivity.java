package com.example.mycontentporvider;

import android.os.Bundle;
import android.widget.ExpandableListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mycontentporvider.classes.Group;
import com.example.mycontentporvider.classes.Item;
import com.example.mycontentporvider.listwithadapter.MyExpandableListAdapter;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    private ExpandableListView expandableListView;
    private ArrayList<Group> gData=new ArrayList<>();
    private ArrayList<ArrayList<Item>> iData=new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        setData();
        expandableListView=findViewById(R.id.expand_listView);
        MyExpandableListAdapter myExpandableListAdapter=new MyExpandableListAdapter(gData,iData,getBaseContext());
        expandableListView.setAdapter(myExpandableListAdapter);

    }

    public void setData() {
        gData.add(new Group("setting_1","true"));
        gData.add(new Group("setting_2","true"));
        gData.add(new Group("setting_3","false"));
        gData.add(new Group("setting_4","true"));

        ArrayList<Item> list=new ArrayList<>();

        list.add(new Item("first"));
        list.add(new Item("second"));
        iData.add(list);

        list.add(new Item("first"));
        list.add(new Item("second"));
        list.add(new Item("three"));
        list.add(new Item("four"));
        iData.add(list);

        list.add(new Item("first"));
        list.add(new Item("second"));
        list.add(new Item("three"));
        list.add(new Item("four"));
        iData.add(list);

        list.add(new Item("first"));
        iData.add(list);

    }
}
