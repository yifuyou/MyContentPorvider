package com.example.mycontentporvider.listwithadapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.mycontentporvider.R;
import com.example.mycontentporvider.classes.Group;
import com.example.mycontentporvider.classes.Item;

import java.util.ArrayList;


public class MyExpandableListAdapter extends BaseExpandableListAdapter {
    private  LayoutInflater layoutInflater;
    private Context context;
    private  ArrayList<Group>gData;
    private ArrayList<ArrayList<Item>> iData;

    public MyExpandableListAdapter(ArrayList<Group> gData, ArrayList<ArrayList<Item>> iData, Context context){
        this.context=context;
        this.gData=gData;
        this.iData=iData;
        layoutInflater=LayoutInflater.from(context);
    }


    @Override
    public int getGroupCount() {
        return gData.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return iData.size();
    }

    @Override
    public Object getGroup(int i) {
        return gData.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return iData.get(i).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        GroupHolper groupHolper= new GroupHolper();
        if(view==null){
            view=layoutInflater.inflate(R.layout.item_group_settings,null);
            groupHolper.tv_name=view.findViewById(R.id.name_group);
            groupHolper.tv_more=view.findViewById(R.id.text_group);
            view.setTag(groupHolper);
        }else{
            groupHolper=(GroupHolper) view.getTag();
        }

        groupHolper.tv_name.setText((String)(gData.get(i).name));
        groupHolper.tv_more.setText((String)(gData.get(i).more+String.valueOf(b)));

        return view;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        ChildHolper childHolper=new ChildHolper();
        if(view==null){
            view=layoutInflater.inflate(R.layout.child_item_settings,null);
            childHolper.tv_choice=view.findViewById(R.id.content_child_item);
            view.setTag(childHolper);
        }else {
            childHolper=(ChildHolper) view.getTag();
        }
        childHolper.tv_choice.setText((String)("choice   "+iData.get(i).get(i1).string));
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }


    public static class GroupHolper{
        public TextView tv_name;
        public TextView tv_more;
    }
    public static class ChildHolper{
        public TextView tv_choice;
    }
}
