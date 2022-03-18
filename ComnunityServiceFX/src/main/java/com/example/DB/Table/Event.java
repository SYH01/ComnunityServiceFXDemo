package com.example.DB.Table;

import java.io.Serializable;

public class Event implements Serializable {
    private String eventNumber;
    private String title;
    private String address;
    private String eventDate;
    private String number;
    private String reason;
    private String date;
    private String name;

    public Event(){}

    public Event(String eventNumber,String title,String address,String eventDate,String number,String reason,String date,String name){
        this.eventNumber = eventNumber;
        this.eventDate = eventDate;
        this.title = title;
        this.number = number;
        this.address = address;
        this.reason = reason;
        this.date = date;
        this.name=name;
    }

    public String toString(){
        return this.eventNumber;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }
    public void setEventNumber(String eventNumber) {
        this.eventNumber = eventNumber;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getTitle() {
        return title;
    }
    public String getNumber() {
        return number;
    }
    public String getAddress() {
        return address;
    }
    public String getDate() {
        return date;
    }
    public String getEventDate() {
        return eventDate;
    }
    public String getEventNumber() {
        return eventNumber;
    }
    public String getReason() {
        return reason;
    }
    public String getName() {
        return name;
    }
}
