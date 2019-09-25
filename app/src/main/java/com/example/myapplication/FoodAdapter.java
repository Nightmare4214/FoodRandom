package com.example.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Switch;
import android.widget.TextView;

import java.util.List;

public class FoodAdapter extends BaseAdapter implements View.OnClickListener{
    private List<Food> mContentList;
    private LayoutInflater mInflater;
    private Callback mCallback;

    void add(String food){
        mContentList.add(new Food(food,true));
    }

    void delete(int position){
        mContentList.remove(position);
    }

    @Override
    public int getCount() {
        return mContentList.size();
    }

    @Override
    public Object getItem(int position) {
        return mContentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            convertView=mInflater.inflate(R.layout.food_item,null);
            holder=new ViewHolder();
            holder.food_name=convertView.findViewById(R.id.food_name);
            holder.food_flag=convertView.findViewById(R.id.food_flag);
            convertView.setTag(holder);
        } else{
          holder=(ViewHolder)convertView.getTag();
        }
        Food food=mContentList.get(position);
        holder.food_name.setText(food.getName());
        holder.food_flag.setChecked(food.isFlag());
        if(food.isFlag()){
            holder.food_name.setTextColor(Color.BLACK);
        }
        else{
            holder.food_name.setTextColor(Color.GRAY);
        }
        holder.food_flag.setOnClickListener(this);
        holder.food_flag.setTag(position);
        return convertView;
    }

    public interface Callback{
        void click(View v);
    }
    FoodAdapter(Context context, List<Food> contentList, Callback callback){
        this.mContentList=contentList;
        this.mInflater= LayoutInflater.from(context);
        this.mCallback=callback;
    }
    public class ViewHolder {
        Switch food_flag;
        TextView food_name;
    }
    @Override
    public void onClick(View v) {
        mCallback.click(v);
    }
}