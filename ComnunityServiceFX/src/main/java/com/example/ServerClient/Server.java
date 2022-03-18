package com.example.ServerClient;

import com.example.DB.Dao.*;
import com.example.DB.Table.*;
import com.example.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static User user;
    private static Admin admin;
    private static Appeal appeal;
    private static Event event;
    private static Equiplace equiplace;
    private static Comments comments;
    private static Message message;
    private static ServerSocket serverSocket;
    private static Socket socket;
    private static int port=9999;
    private static int port2=9998;
    private static ObjectOutputStream objectOutputStream;
    private static ObjectInputStream objectInputStream;
    private static int state;

    public Server() throws Exception {
    }

    public static void main(String[] args) throws Exception {
//        int choice;//                                   主线程只进行判断    用不到了 当没看到
        serverSocket = new ServerSocket(port);
        while (true) {
            socket = serverSocket.accept();
            System.out.println("【服务器】 Connection received！");
                new Thread(){
                    @Override
                    public void run() {
                        try {
                            new DoReceiveTcpThread().ReceiveClass(socket);
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        }

    static class DoReceiveTcpThread {   //用来接收、鉴别、处理客户端发送的类线程
//        private Socket socket;//以socket为成员变量
//        public DoReceiveTcpThread(Socket socket){
//            this.socket=socket;
//        }

        public void ReceiveClass(Socket socket) throws IOException, ClassNotFoundException, InterruptedException {
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());


//            System.out.println("Message接收成功"+message.getUser().getId());
            while(true) {
                message=new Message((Message) objectInputStream.readObject());
                System.out.println("message 的type"+message.getType());
                if(message.getType()!=0) {
                    switch (message.getType()) {
                        case 1: {
                            new Thread() {
                                @Override
                                public void run() {
                                    try {
                                        new Server.serve1().login(socket, message);//deng登录
                                        message.setType(0);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }.start();
                        }
                        break;
                        case 2: {
                            new Thread() {
                                @Override
                                public void run() {
                                    try {
                                        new Server.serve2().appealInsert( message);//诉求插入到服务器数据库中
                                        message.setType(0);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }.start();
                            break;
                        }
                        case 3:{
                            new Thread() {
                                @Override
                                public void run() {
                                    try {
                                        new Server.serve3().eventInsert( message);//活动或会议插入到服务器数据库中
                                        message.setType(0);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }.start();
                            break;
                        }
                        case 4:{
                            new Thread() {
                                @Override
                                public void run() {
                                    try {
                                        new Server.serve4().equiplaceInsert( message);//场地设备插入到服务器数据库中
                                        message.setType(0);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }.start();
                            break;
                        }

                        case 5:{
                            new Thread() {
                                @Override
                                public void run() {
                                    try {
                                        new Server.serve5().getNotice();//admin登录
                                        message.setType(0);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }.start();
                            break;
                        }

                        case 6:{
                            new Thread() {
                                @Override
                                public void run() {
                                    try {
                                        new Server.serve6().login( message);//admin登录
                                        message.setType(0);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }.start();
                            break;
                        }
                        case 7:{
                            if (message.getState()==1){
                                new Thread() {
                                    @Override
                                    public void run() {
                                        try {
                                            new Server.serve7().getComments();//获取comments信息
                                            message.setType(0);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }.start();
                            }else{
                                new Thread() {
                                    @Override
                                    public void run() {
                                        try {
                                            new Server.serve8().setComments(message);//获取comments信息
                                            message.setType(0);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }.start();
                            }
                            break;
                        }
                    }//switch的结束括号}
                }
                Thread.sleep(500);

            }//while循环结束
        }
        }

    static class serve1{  //处理登录注册
        public void login (Socket socket,Message message) throws Exception {
            //登录或注册
            user = message.getUser();
            System.out.println("Login方法"+user.getId()+user.getPassword());
            UserDaoImplement userDaoImplement = new UserDaoImplement();
            int real = userDaoImplement.select(user);
            state = real;
            objectOutputStream.writeObject(state);
            if (state == 0) {
                userDaoImplement.insert(user);
                System.out.println("用户不存在！但注册成功");
            }
        }
    }

    static class serve2{    //将诉求插入到数据库
        public void appealInsert (Message message) throws Exception {
            //诉求插入到数据库
            appeal = message.getAppeal();
            System.out.println("appeal title"+appeal.getTitle()+"appeal content"+appeal.getContent());
            AppealDaoImplement appealDaoImplement = new AppealDaoImplement();
            appealDaoImplement.insert(appeal);
            System.out.println("appeal插入成功！");

        }
    }

    static class serve3{    //将活动内容插入到数据库
        public void eventInsert (Message message) throws Exception {
            //诉求插入到数据库
            event= message.getEvent();
            System.out.println("event title"+event.getTitle());
            EventDaoImplement eventDaoImplement=new EventDaoImplement();
            eventDaoImplement.insert(event);
            System.out.println("event插入成功！");
        }
    }
    static class serve4{    //将活动内容插入到数据库
        public void equiplaceInsert (Message message) throws Exception {
            //诉求插入到数据库
            equiplace= message.getEquiplace();
            System.out.println("equiplace name"+equiplace.getName());
            EquiplaceDaoImplement equiplaceDaoImplement=new EquiplaceDaoImplement();
            equiplaceDaoImplement.insert(equiplace);
            System.out.println("equiplace插入成功！");
        }
    }
    static class serve5{    //获取notice插入到数据库
        public void getNotice() throws Exception {
            //返回notice
            NoticeDaoImplement noticeDaoImplement=new NoticeDaoImplement();
           String string=new String();
           for(int i=0;i<noticeDaoImplement.selectAll().size();i++){
               string+=string = noticeDaoImplement.selectAll().get(i).getNumber()+"\n"+noticeDaoImplement.selectAll().get(i).getContent()+"\n";
           }
           objectOutputStream.writeObject(string);
            System.out.println("noticeGet成功！"+string);
        }
    }

    static class serve6{    //将管理员插入到数据库
        public void login (Message message) throws Exception {
            //登录或注册
            admin = message.getAdmin();
            System.out.println("Login方法"+admin.getId()+admin.getPassword());
            AdminDaoImplement adminDaoImplement = new AdminDaoImplement();
            int real = adminDaoImplement.select(admin);
            state = real;
            objectOutputStream.writeObject(state);
            if (state == 0) {
                adminDaoImplement.insert(admin);
               // 用户不存在
                System.out.println("admin用户不存在！但注册成功");
            }
        }
    }
        static class serve7{    //获取comments插入到数据库
            public void getComments() throws Exception {
                //返回comments
                CommentsDaoImplement commentsDaoImplement=new CommentsDaoImplement();
                String string=new String();
                for(int i=0;i<commentsDaoImplement.selectAll().size();i++){
                    string+=string = commentsDaoImplement.selectAll().get(i).getNumber()+"\n"+commentsDaoImplement.selectAll().get(i).getContent()+"\n";
                }
                objectOutputStream.writeObject(string);
                System.out.println("comments Get成功！"+string);
            }
        }
    static class serve8{    //将留言插入到数据库
        public void setComments (Message message) throws Exception {
            //登录或注册
            comments = message.getComments();
            System.out.println("get comments方法"+comments.getContent()+comments.getNumber());
            CommentsDaoImplement commentsDaoImplement=new CommentsDaoImplement();
            commentsDaoImplement.insert(comments);
            System.out.println("comments插入成功！");
        }
    }
//        public void adminInsert (Message message) throws Exception {
//            //admin插到数据库
//            admin= message.getAdmin();
//            System.out.println("admin name"+admin.getId());
//            AdminDaoImplement adminDaoImplement=new AdminDaoImplement();
//            adminDaoImplement.insert(admin);
//            System.out.println("admin插入成功！");
//        }


}






