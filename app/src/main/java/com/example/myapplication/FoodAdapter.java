package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;
class ViewHodler {

    Switch food_flag;
    TextView food_name;
}
public class FoodAdapter extends ArrayAdapter<Food> {
    //resourceID指定ListView的布局方式
    private int resourceID;
    //重写BrowserAdapter的构造器
    public FoodAdapter(Context context, int textViewResourceID , List<Food> objects){
        super(context,textViewResourceID,objects);
        resourceID = textViewResourceID;
    }
    //重写getView()方法，这个方法在子项被滚动屏幕内时调用
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //获取当前Browser实例
        Food food = getItem(position);
        View view;
        //创建一个viewHolder来缓存控件，这样就不用每次加载的时候都要调用findViewById()来获得控件的实例了
        ViewHodler viewHolder;
        // 加载布局
        if(convertView==null){
            //创建
            view = LayoutInflater.from(getContext()).inflate(resourceID, parent, false);
            viewHolder = new ViewHodler();
            viewHolder.food_flag=view.findViewById(R.id.food_flag);
            viewHolder.food_name=view.findViewById(R.id.food_name);
            //把viewHolder存入view中
            view.setTag(viewHolder);
        }else {
            view = convertView;
            //重新取出viewHolder
            viewHolder = (ViewHodler) view.getTag();
        }
        viewHolder.food_name.setText(food.getName());
        viewHolder.food_flag.setChecked(food.isFlag());
        return view;
    }
}