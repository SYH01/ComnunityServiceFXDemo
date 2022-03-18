package com.example.DB.Table;

import java.io.Serializable;

public class Equiplace implements Serializable {
    private String name;
    private String type;
    private String use;
    private String remark;
    private String state;
    private String introduce;

    public Equiplace(){}

    public Equiplace(String name,String type,String use,String remark,String state,String introduce){
        this.name=name;
        this.type=type;
        this.use=use;
        this.remark=remark;
        this.state=state;
        this.introduce=introduce;
    }

    public String toString(){
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setUse(String use) {
        this.use = use;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public void setState(String state) {
        this.state = state;
    }
    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }
    public String getName() {
        return name;
    }
    public String getRemark() {
        return remark;
    }
    public String getIntroduce() {
        return introduce;
    }
    public String getState() {
        return state;
    }
    public String getType() {
        return type;
    }
    public String getUse() {
        return use;
    }
}
