package com.example.DB.Table;

import java.io.Serializable;

public class Appeal implements Serializable {
    private String number;
    private String title;
    private String content;
    private String date;
    private String remark;
    private String name;

    public Appeal(){}

    public Appeal(String number,String title,String content,String date,String remark,String name){
        this.content=content;
        this.date=date;
        this.number=number;
        this.title=title;
        this.remark=remark;
        this.name=name;
    }

    public String toString(){
        return this.number;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public String getContent() {
        return content;
    }
    public String getDate() {
        return date;
    }
    public String getName() {
        return name;
    }
    public String getNumber() {
        return number;
    }
    public String getRemark() {
        return remark;
    }
    public String getTitle() {
        return title;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public void setTitle(String title) {
        this.title = title;
    }
}
