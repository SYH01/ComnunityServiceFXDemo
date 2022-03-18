package com.example.ServerClient;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.util.ArrayList;

public class UDPServer implements Runnable{

    private DatagramSocket DS;
    SocketAddress clientIp;
    private int Port = 9998;
//由多人聊天室改编一对一聊天
//    private ArrayList<SocketAddress> clients = new ArrayList<SocketAddress>(); //保存客户端IP地址

    public UDPServer() throws Exception{
        try {
            System.out.println("UDPServer is running!!");
            DS = new DatagramSocket(Port);
            new Thread(this).start();
            System.out.println("UDP初始化完成！");
        } catch (Exception ex) {
        }
    }

    public void run(){
        try{
            while(true){
                byte[] data = new byte[1024];
                DatagramPacket DP = new DatagramPacket(data,data.length);
                System.out.println("服务器正在接收信息！");
                DS.receive(DP);

                clientIp = DP.getSocketAddress();
                System.out.println("UDPServer 已经收到信息！");
//                if(!clients.contains(clientIp)){
//                    clients.add(clientIp);
//                }
                this.send(DP);
            }
        }catch(Exception ex){
        }
    }

    private void send(DatagramPacket dp) throws Exception {
            DatagramPacket dd = new DatagramPacket(dp.getData(), dp.getLength(), clientIp);
            DS.send(dd);
        System.out.println("UDPServer 发送信息成功！");
    }
}
