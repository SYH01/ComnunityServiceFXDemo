package com.example.DB.Table;

import java.io.Serializable;

public class Comments implements Serializable {
    private String number;
    private String content;

    public  Comments(){};
    public Comments(String number,String content){
        this.content=content;
        this.number=number;
    }
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
}
