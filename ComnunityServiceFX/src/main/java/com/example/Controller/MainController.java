package com.example.Controller;

import com.example.DB.Dao.NoticeDaoImplement;
import com.example.DB.Table.Appeal;
import com.example.DB.Table.Comments;
import com.example.DB.Table.Equiplace;
import com.example.DB.Table.Event;
import com.example.DateString;
import com.example.ServerClient.AddressInfo;
import com.example.ServerClient.Client;
import com.example.StageController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.*;

public class MainController implements ControlledStage{
    private StageController stageController;
    public static Client client;


    //******************************公告代码*****************************************************************
    @FXML
    public TextArea notice;

    @FXML
    private void getNotice() throws IOException, ClassNotFoundException {
        String str=client.getNotice();
        notice.setText(str);
    }

    //*************************留言代码********************************************************
    @FXML
    public TextArea comments;
    @FXML
    public TextArea sendComments;
    @FXML
    private void getComments() throws IOException, ClassNotFoundException {
        String str=client.getComments();
        comments.setText(str);
    }
    @FXML
    private void setComments() throws IOException, ClassNotFoundException {
        Comments comments=new Comments(DateString.getDate(), LoginController.name+"留言："+sendComments.getText());
        client.setComments(comments);
        getComments();//再次获取可以同步信息
        sendComments.clear();
    }


    //*******************************这是诉求面板的内容*******************************************
    @FXML
    private TextField number;
    @FXML
    private TextField title;
    @FXML
    private TextArea content;
    @FXML
    private TextField date;
    @FXML
    private TextField remark;
    @FXML
    private TextField name;
    @FXML
    private Button button;
    @FXML
    public void test(){
        System.out.println("测试!");
    }

    @FXML
    public void appeal_M() throws IOException, ClassNotFoundException {
        System.out.println("appeal插入number title"+getAppeal().getTitle());
        client.insertAppeal(getAppeal());
//        System.out.println("appeal插入数据库成功");
    }

    private Appeal getAppeal(){
        Appeal appeal=new Appeal();
        appeal.setNumber(number.getText());
        appeal.setTitle(title.getText());
        appeal.setContent(content.getText());
        appeal.setDate(date.getText());
        appeal.setRemark(remark.getText());
        appeal.setName(name.getText());
        return appeal;
    }

    @FXML
    public void outFile() throws IOException {
        File file=new File("D:/Appeal.txt");
        FileWriter fileWriter=new FileWriter(file.getPath(),true);
        fileWriter.write("\r\n"+"诉求编号："+getAppeal().getNumber()+"\r\n");
        fileWriter.write("诉求标题："+getAppeal().getTitle()+"\r\n");
        fileWriter.write("诉求内容："+getAppeal().getContent()+"\r\n");
        fileWriter.write("诉求日期："+getAppeal().getDate()+"\r\n");
        fileWriter.write("诉求备注："+getAppeal().getRemark()+"\r\n");
        fileWriter.write("诉求人："+getAppeal().getName()+"\r\n");
        System.out.println("诉求内容，本地文件下载成功"+getAppeal().getTitle());
        fileWriter.close();
    }

    //***********************这是活动会议面板的内容***************************
    @FXML
    private TextField eventNumber;
    @FXML
    private TextField title2;
    @FXML
    private TextField address;
    @FXML
    private TextField eventDate;
    @FXML
    private TextField number2;
    @FXML
    private TextArea reason;
    @FXML
    private TextField date2;
    @FXML
    private TextField name2;
    @FXML
    private Button button2;

    private Event getEvent(){
        Event event=new Event();
        event.setEventNumber(eventNumber.getText());
        event.setTitle(title2.getText());
        event.setAddress(address.getText());
        event.setEventDate(eventDate.getText());
        event.setNumber(number2.getText());
        event.setReason(reason.getText());
        event.setDate(date2.getText());
        event.setName(name2.getText());
        return event;
    }
    @FXML
    public void event_insert() throws IOException, ClassNotFoundException {
        System.out.println("event插入number title"+getEvent().getTitle());
        client.insertEvent(getEvent());
//        System.out.println("appeal插入数据库成功");
    }

    @FXML
    public void outFile2() throws IOException {
        File file=new File("D:/Event.txt");
        FileWriter fileWriter=new FileWriter(file.getPath(),true);
        fileWriter.write("\r\n"+"活动或会议编号："+getEvent().getEventNumber()+"\r\n");
        fileWriter.write("活动或会议主题："+getEvent().getTitle()+"\r\n");
        fileWriter.write("地点："+getEvent().getAddress()+"\r\n");
        fileWriter.write("日期："+getEvent().getEventDate()+"\r\n");
        fileWriter.write("报名编号："+getEvent().getNumber()+"\r\n");
        fileWriter.write("报名原因："+getEvent().getReason()+"\r\n");
        fileWriter.write("报名日期："+getEvent().getDate()+"\r\n");
        fileWriter.write("报名人："+getEvent().getName()+"\r\n");
        System.out.println("诉求内容，本地文件下载成功"+getEvent().getTitle());
        fileWriter.close();
    }

    //*********************************************这是诉求面板的内容********************************************
    @FXML
    private TextField name3;
    @FXML
    private TextField type3;
    @FXML
    private TextField use3;
    @FXML
    private TextField remark3;
    @FXML
    private TextField state3;
    @FXML
    private TextArea introduce;
    @FXML
    private Button button3;

    private Equiplace getEquiplace(){
        Equiplace equiplace=new Equiplace();
        equiplace.setName(name3.getText());
        equiplace.setType(type3.getText());
        equiplace.setUse(use3.getText());
        equiplace.setRemark(remark3.getText());
        equiplace.setState(state3.getText());
        equiplace.setIntroduce(introduce.getText());
        return equiplace;
    }

    @FXML
    public void equiplace_insert() throws IOException, ClassNotFoundException {
        System.out.println("equiplace插入name"+getEquiplace().getName());
        client.insertEquiplace(getEquiplace());
//        System.out.println("appeal插入数据库成功");
    }

    @FXML
    public void outFile3() throws IOException {
        File file=new File("D:/Equiplace.txt");
        FileWriter fileWriter=new FileWriter(file.getPath(),true);
        fileWriter.write("\r\n"+"场地设备名称："+getEquiplace().getName()+"\r\n");
        fileWriter.write("场地设备类型："+getEquiplace().getType()+"\r\n");
        fileWriter.write("场地设备用途："+getEquiplace().getUse()+"\r\n");
        fileWriter.write("场地设备备注："+getEquiplace().getRemark()+"\r\n");
        fileWriter.write("场地设备状态："+getEquiplace().getState()+"\r\n");
        fileWriter.write("场地设备介绍："+getEquiplace().getIntroduce()+"\r\n");
        System.out.println("场地设备，本地文件下载成功"+getEquiplace().getName());
        fileWriter.close();
    }

    @FXML
    public void exit() throws IOException, ClassNotFoundException {
        client.clientClose();
    }

    //**************______________聊天界面—————————————————**************************************************8
    @FXML
    private TextArea textArea01;
    @FXML
    private TextArea textArea02;

    @FXML
    public void startUDPChat() throws SocketException, UnknownHostException {
        UDPChat udpChat=new UDPChat();
        Thread thread=new Thread(udpChat);
        thread.start();
    }
    private String str;
    private int port=9998;
    private DatagramSocket DS;

    class UDPChat implements Runnable{
        public UDPChat() throws SocketException, UnknownHostException {
            DS=new DatagramSocket();
            DS.connect(AddressInfo.IP,port);
        }
        public void run(){
            try {
                while(true){
                    byte[] data=new byte[1024];
                    DatagramPacket DP = new DatagramPacket(data,data.length);
                    DS.receive(DP);
                    String str = new String(DP.getData(),0,DP.getLength());
                    textArea01.appendText(str + '\n');
                    System.out.println("客户端接收到的信息是："+str);
                }
            } catch (IOException  e) {
                e.printStackTrace();
            }
        }
    }
    @FXML
    public void enterSend(){
        try{
            System.out.println("enterSend方法正在运行！");
            str =LoginController.name+"说：" + textArea02.getText();
            textArea01.appendText(str+"\n");
            byte[] dd = str.getBytes();
            DatagramPacket Data = new DatagramPacket(dd,dd.length);
            DS.send(Data);
            textArea02.clear();
            System.out.println("客户端信息发送成功！");
        }catch(Exception ex){
        }
    }
    //&********************——————————————***********************************************————————————————————————————
    @Override
    public void setStageController(StageController StageController) {
        this.stageController=StageController;
    }
}
