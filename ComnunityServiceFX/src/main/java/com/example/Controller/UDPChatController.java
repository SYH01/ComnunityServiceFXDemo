package com.example.Controller;

import com.example.ServerClient.AddressInfo;
import com.example.StageController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.net.*;

public class UDPChatController implements ControlledStage,Runnable{
    private StageController stageController;

    private String name=null;
    private int port=9998;
    private DatagramSocket DS;

    @FXML
    private TextArea textArea01;
    @FXML
    private TextArea textArea02;
    @FXML
    private Button button;

//    @FXML
//    public void buttonSend(){
//
//    }

    public UDPChatController() throws SocketException, UnknownHostException {
        DS=new DatagramSocket();
        InetAddress address= AddressInfo.IP;
        DS.connect(address,port);

        new Thread(this).start();
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
        } catch (IOException e) {
        e.printStackTrace();
        }
    }

    @FXML
    public void enterSend(){
        try{
            System.out.println("enterSend方法正在运行！");
            String str =LoginController.name+"说：" + textArea02.getText();
            byte[] dd = str.getBytes();
            DatagramPacket Data = new DatagramPacket(dd,dd.length);
            DS.send(Data);
            textArea02.clear();
            System.out.println("客户端信息发送成功！");
        }catch(Exception ex){
        }
    }

    public void setStageController(StageController stageController){
        this.stageController=stageController;
    }
}
