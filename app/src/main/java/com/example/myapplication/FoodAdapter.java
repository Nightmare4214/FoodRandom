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

//    public void setItem(int position){
//        mContentList.
//    }

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
        ViewHolder holder=null;
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
        public void click(View v);
    }
    public FoodAdapter(Context context, List<Food> contentList, Callback callback){
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
//public class FoodAdapter extends ArrayAdapter<Food> {
//    //resourceID指定ListView的布局方式
//    private int resourceID;
//    //重写BrowserAdapter的构造器
//    public FoodAdapter(Context context, int textViewResourceID , List<Food> objects){
//        super(context,textViewResourceID,objects);
//        resourceID = textViewResourceID;
//    }
//    //重写getView()方法，这个方法在子项被滚动屏幕内时调用
//    @NonNull
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        //获取当前Browser实例
//        Food food = getItem(position);
//        View view;
//        //创建一个viewHolder来缓存控件，这样就不用每次加载的时候都要调用findViewById()来获得控件的实例了
//        ViewHolder viewHolder;
//        // 加载布局
//        if(convertView==null){
//            //创建
//            view = LayoutInflater.from(getContext()).inflate(resourceID, parent, false);
//            viewHolder = new ViewHolder();
//            viewHolder.food_flag=view.findViewById(R.id.food_flag);
//            viewHolder.food_name=view.findViewById(R.id.food_name);
//            //把viewHolder存入view中
//            view.setTag(viewHolder);
//        }else {
//            view = convertView;
//            //重新取出viewHolder
//            viewHolder = (ViewHolder) view.getTag();
//        }
//        viewHolder.food_name.setText(food.getName());
//        viewHolder.food_flag.setChecked(food.isFlag());
//        return view;
//    }
//}