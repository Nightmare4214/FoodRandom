package com.example.myapplication;

public class Food{
    private String name;
    private boolean flag;
    public Food(String name,boolean flag){
        this.name=name;
        this.flag=flag;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}