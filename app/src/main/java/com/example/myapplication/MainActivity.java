package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    private ListView listView;
    private String[] food={"关键","食堂","烤肉","京都鲜肉饼"};
    private List<Food> foods=new ArrayList<>();
    private FoodAdapter adapter;
    private Switch aSwitch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.listView);
        aSwitch=findViewById(R.id.food_flag);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Food temp=foods.get(position);
                Toast.makeText(MainActivity.this,
                        temp.getName().toString(),Toast.LENGTH_SHORT).show();
            }
        });

        for (String s : food) {
            foods.add(new Food(s, true));
        }
        adapter=new FoodAdapter(
                MainActivity.this,
                R.layout.food_item,
                foods
        );
        listView.setAdapter(adapter);
    }
}
