package com.example;

import com.example.DB.Table.*;

import java.io.Serializable;

public class Message implements Serializable {
    private int type=0;
    private User user;//type=1
    private Appeal appeal;//type=2
    private Event event;//type=3
    private Equiplace equiplace;//type=4
    private Notice notice;//type=5
    private Admin admin;//由于管理员类和表是我最后创建的，所以admin的type=6
    private Comments comments;//type=7
    private Message message;
    private int state=1;
    public Message(){}

    public Message(Message message){
        this.message=message;
        this.state=message.getState();
        if(message.getType()==1){
            this.user=message.getUser();
            this.type=1;
        }
        if(message.getType()==2){
            this.appeal=message.getAppeal();
            this.type=2;
        }
        if(message.getType()==3){
            this.event=message.getEvent();
            this.type=3;
        }
        if(message.getType()==4){
            this.equiplace=message.getEquiplace();
            this.type=4;
        }
        if(message.getType()==5){
            this.notice=message.getNotice();
            this.type=5;
        }
        if(message.getType()==6){
            this.admin=message.getAdmin();
            this.type=6;
        }
        if (message.getType()==7){
            this.comments=message.getComments();
            this.type=7;
        }
//        if (message.state==1){
//            this.type=7;
//        }else{
//            this.type=8;
//        }
        if(message.getType()==0){
            System.out.println("Message构造时type为0！错误！");
        }
    }

    public int getState() {
        return state;
    }
    public Comments getComments() {
        return comments;
    }
    public Admin getAdmin() {
        return admin;
    }
    public Notice getNotice() {
        return notice;
    }
    public Equiplace getEquiplace() {
        return equiplace;
    }
    public Event getEvent() {
        return event;
    }
    public Appeal getAppeal() {
        return appeal;
    }
    public User getUser() {
        return user;
    }
    public void setEvent(Event event) {
        this.event = event;
        this.type=3;
    }
    public void setAppeal(Appeal appeal) {
        this.appeal = appeal;
        this.type=2;
    }
    public void setUser(User user) {
        this.user = user;
        this.type=1;
    }
    public void setEquiplace(Equiplace equiplace) {
        this.equiplace = equiplace;
        this.type=4;
    }
    public void setNotice(Notice notice) {
        this.notice = notice;
        this.type=5;
    }
    public void setAdmin(Admin admin) {
        this.admin = admin;
        this.type=6;
    }
    public void setComments(Comments comments) {
        this.comments = comments;
        this.type=7;
    }
    public  void setType(int type) {
        this.type = type;
    }

    public  int getType(){
        return this.type;
    }
    public void setState(int state) {
        this.state = state;
    }
}
