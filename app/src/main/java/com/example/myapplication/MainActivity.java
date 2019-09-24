package com.example.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements FoodAdapter.Callback {
    private ListView listView;

    private TextView foodText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.listView);
        foodText=findViewById(R.id.foodText);
        Button addBtn = findViewById(R.id.addBtn);
        Button deleteBtn = findViewById(R.id.deleteBtn);
        Button selectBtn = findViewById(R.id.selectBtn);

        String[] food={"食堂","厨大爷","关键","沙县小吃","烤肉","京都鲜肉饼","德克士","黄焖鸡米饭","海底捞","自助火锅"};
        List<Food> foods=new ArrayList<>();
        for (String s : food) {
            foods.add(new Food(s, true));
        }
        listView.setAdapter(new FoodAdapter(MainActivity.this, foods,
                MainActivity.this));

        selectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FoodAdapter temp=(FoodAdapter)listView.getAdapter();
                int food_size=temp.getCount();
                List<String> food_list=new ArrayList<>();
                for(int i=0;i<food_size;++i){
                    Food temp_food=(Food)temp.getItem(i);
                    if(temp_food.isFlag()){
                        food_list.add(temp_food.getName());
                    }
                }
                if(food_list.size()>0){
                    int random= (new Random()).nextInt(food_list.size());
                    foodText.setText(food_list.get(random));
                }
                else{
                    Toast.makeText(
                            MainActivity.this,
                            "你居然啥都不想吃",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEditAlert();
            }
        });
    }

    public void addEditAlert(){
        final EditText et = new EditText(this);
        et.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
        new AlertDialog.Builder(this).setTitle("请输入想吃的")
                .setIcon(android.R.drawable.sym_def_app_icon)
                .setView(et)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FoodAdapter temp=(FoodAdapter)listView.getAdapter();
                        String input= String.valueOf(et.getText());
                        if("".equals(input)){
                            Toast.makeText(
                                    MainActivity.this,
                                    "你在想吃peach",
                                    Toast.LENGTH_SHORT).show();
                            return;
                        }
                        int food_size=temp.getCount();
                        for(int j=0;j<food_size;++j){
                            String food_name=((Food)temp.getItem(j)).getName();
                            if(input.equals(food_name)){
                                Toast.makeText(
                                        MainActivity.this,
                                        "你已经加入过这个了",
                                        Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                        temp.add(input);
                        temp.notifyDataSetChanged();
                    }
                }).setNegativeButton("取消",null).show();
    }


    @Override
    public void click(View v){
        int id=(Integer)v.getTag();
        FoodAdapter temp=(FoodAdapter)listView.getAdapter();
        Food food=(Food)temp.getItem(id);
        food.setFlag(!food.isFlag());
        temp.notifyDataSetChanged();
//        Toast.makeText(
//                MainActivity.this,
//                food.getName()+"  "+food.isFlag(),
//                Toast.LENGTH_SHORT).show();
    }
}
