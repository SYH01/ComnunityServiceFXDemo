package com.example.ServerClient;

import com.example.DB.Table.*;
import com.example.Message;
import com.example.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    private static User user;
    private static Admin admin;
    private static Appeal appeal;
    private static Equiplace equiplace;
    private static Socket socket;
    private static int port=9999;
    private static ObjectOutputStream objectOutputStream;
    private static ObjectInputStream objectInputStream;
    public static int state;//判断是否登陆成功
    private static Message message;//封装user和appeal
//    private static int choice;//发送将要进行的功能 1-登录

    public Client () throws IOException {
        socket=new Socket(AddressInfo.IP,port);
        System.out.println("Client初始化完成");
        objectOutputStream=new ObjectOutputStream(socket.getOutputStream());
        objectInputStream=new ObjectInputStream(socket.getInputStream());
    }


    //构造方法为参数为user的构造
    public void setUser(User user) throws IOException, ClassNotFoundException {
            this.user=user;
        System.out.println("user in "+user.getId()+user.getPassword());

//        choice=1;//发送给服务器告诉我要登录--1

        message=new Message();
        message.setUser(user);
        System.out.println("发送message里面的user"+message.getUser().getId()+message.getUser().getPassword());
        System.out.println("message里面的type"+message.getType());
        System.out.println("发送");
        objectOutputStream.writeObject(message);
        System.out.println("成功");

        System.out.println(user.getId()+user.getPassword());

        this.state=(int)objectInputStream.readObject();

    }

    //构造方法为参数为admin的构造
    public void setAdmin(Admin admin) throws IOException, ClassNotFoundException {
        this.admin=admin;
        System.out.println("admin in "+admin.getId()+admin.getPassword());
//        choice=1;//发送给服务器告诉我要登录--1

        message=new Message();
        message.setAdmin(admin);
        System.out.println("发送message里面的admin"+message.getAdmin().getId()+message.getAdmin().getPassword());
        System.out.println("message里面的type"+message.getType());
        System.out.println("发送");
        objectOutputStream.writeObject(message);
        System.out.println("成功");

        System.out.println(admin.getId()+admin.getPassword());

        this.state=(int)objectInputStream.readObject();

    }

    //方法用来进行将 数据类 包装 成为message类，发送到服务器，插入到服务器的数据库
    public void insertAppeal (Appeal appeal) throws IOException, ClassNotFoundException {
        this.appeal=appeal;
//        socket=new Socket(AddressInfo.IP,port);
        System.out.println("appealClient初始化完成");
//        objectOutputStream=new ObjectOutputStream(socket.getOutputStream());
//        objectInputStream=new ObjectInputStream(socket.getInputStream());
//        choice=2;//发送给服务器告诉我要登录--1
        message=new Message();
        message.setAppeal(appeal);
        objectOutputStream.writeObject(message);

        System.out.println("Appeal message 发送Server 成功  content"+message.getAppeal().getContent()+message.getType());
    }

    public void insertEvent (Event event) throws IOException, ClassNotFoundException {
        System.out.println("appealClient初始化完成");
        message=new Message();
        message.setEvent(event);
        objectOutputStream.writeObject(message);

        System.out.println("Event message 发送Server 成功  Title"+message.getEvent().getTitle()+message.getType());

    }

    public void insertEquiplace (Equiplace equiplace) throws IOException, ClassNotFoundException {
        System.out.println("EquiplaceClient初始化完成");
        message=new Message();
        message.setEquiplace(equiplace);
        objectOutputStream.writeObject(message);

        System.out.println("Equiplace message 发送Server 成功  name"+message.getEquiplace().getName()+message.getType());

    }

    public String getNotice () throws IOException, ClassNotFoundException {//获取服务器数据库里面的公告信息
        System.out.println("NoticeClient初始化完成");
        message=new Message();
        message.setNotice(null);
        message.setType(5);
        objectOutputStream.writeObject(message);
        String str =(String) objectInputStream.readObject();
        System.out.println("Notice message "+str);
        return str;
    }

    public String getComments () throws IOException, ClassNotFoundException {//获取服务器数据库里面的留言
        System.out.println("Comments Client初始化完成");
        message=new Message();
        message.setNotice(null);
        message.setType(7);//创建新的功能7
        objectOutputStream.writeObject(message);
        String str =(String) objectInputStream.readObject();
        System.out.println("comments message "+str);
        return str;
    }
    public void setComments(Comments comments) throws IOException, ClassNotFoundException {
        System.out.println("comments Client初始化完成");
        message=new Message();
        message.setState(2);
        message.setComments(comments);
        objectOutputStream.writeObject(message);
        System.out.println("comments message 发送Server 成功 state"+message.getState());
    }
    //不知道以下的方法还有没有用，请不要删
    public int getState(){  //返回注册的状态
            return state;
        }

    public  void clientClose() throws IOException, ClassNotFoundException {
        socket.close();
    }
}
