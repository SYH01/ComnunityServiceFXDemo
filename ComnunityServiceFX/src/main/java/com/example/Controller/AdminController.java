package com.example.Controller;

import com.example.DB.Dao.AppealDaoImplement;
import com.example.DB.Dao.EquiplaceDaoImplement;
import com.example.DB.Dao.EventDaoImplement;
import com.example.DB.Dao.NoticeDaoImplement;
import com.example.DB.Table.Appeal;
import com.example.DB.Table.Equiplace;
import com.example.DB.Table.Event;
import com.example.DB.Table.Notice;
import com.example.DateString;
import com.example.ServerClient.Client;
import com.example.StageController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.sql.SQLException;

public class AdminController implements ControlledStage{
    private StageController stageController;
    public static Client client;

    //添加诉求表

    @FXML
    public TableView tableView;
    @FXML
    public TableColumn number;
    @FXML
    public TableColumn title;
    @FXML
    public TableColumn content;
    @FXML
    public TableColumn date;
    @FXML
    public TableColumn remark;
    @FXML
    public TableColumn name;

    @FXML
    public void refreshTableAppeal(){
        AppealDaoImplement appealDaoImplement=new AppealDaoImplement();
        ObservableList<Appeal> data= FXCollections.observableArrayList(
                appealDaoImplement.selectAll()
        );

        number.setCellValueFactory(new PropertyValueFactory<Appeal,String>("number"));
        title.setCellValueFactory(new PropertyValueFactory<Appeal,String>("title"));
        content.setCellValueFactory(new PropertyValueFactory<Appeal,String>("content"));
        date.setCellValueFactory(new PropertyValueFactory<Appeal,String>("date"));
        remark.setCellValueFactory(new PropertyValueFactory<Appeal,String>("remark"));
        name.setCellValueFactory(new PropertyValueFactory<Appeal,String>("name"));
        tableView.setItems(data);
    }
    @FXML
    public void deleteSelected00() {//event表
        String str = tableView.getSelectionModel().getSelectedItem().toString();
        System.out.println("str_:"+str);
        AppealDaoImplement appealDaoImplement=new AppealDaoImplement();
        appealDaoImplement.delete(str);
        refreshTableAppeal();
    }
//***********************************************************************************************
    //添加公告表及公告
    @FXML
    public TableView tableView0;
    @FXML
    public TableColumn time;
    @FXML
    public TableColumn content0;
    @FXML
    public TextArea textArea1;
    @FXML
    public void refreshTableNotice(){
        NoticeDaoImplement noticeDaoImplement=new NoticeDaoImplement();
        ObservableList<Notice> data= FXCollections.observableArrayList(
                noticeDaoImplement.selectAll()
        );

        time.setCellValueFactory(new PropertyValueFactory<Notice,String>("number"));
        content0.setCellValueFactory(new PropertyValueFactory<Notice,String>("content"));
        tableView0.setItems(data);
    }

    @FXML
    private void insertNotice() throws SQLException {
        Notice notice =new Notice();
        notice.setNumber(DateString.getDate());
        notice.setContent(textArea1.getText());
        NoticeDaoImplement noticeDaoImplement=new NoticeDaoImplement();
        noticeDaoImplement.insert(notice);
        textArea1.clear();
        refreshTableNotice();
    }
    @FXML
    public void deleteSelected() {
        String str = tableView0.getSelectionModel().getSelectedItem().toString();
        System.out.println("str_:"+str);
        NoticeDaoImplement noticeDaoImplement=new NoticeDaoImplement();
        noticeDaoImplement.delete(str);
        refreshTableNotice();
    }
//_____________________________________________________________________________________________
    //Event表
    @FXML
    public TableView tableView2;
    @FXML
    public TableColumn eventNumber2;
    @FXML
    public TableColumn title2;
    @FXML
    public TableColumn address2;
    @FXML
    public TableColumn eventDate2;
    @FXML
    public TableColumn number2;
    @FXML
    public TableColumn reason2;
    @FXML
    public TableColumn date2;
    @FXML
    public TableColumn name2;
    @FXML
    public void refreshTableEvent(){
        EventDaoImplement eventDaoImplement=new EventDaoImplement();
        ObservableList<Event> data= FXCollections.observableArrayList(
                eventDaoImplement.selectAll()
        );

        eventNumber2.setCellValueFactory(new PropertyValueFactory<Event,String>("eventNumber"));
        title2.setCellValueFactory(new PropertyValueFactory<Event,String>("title"));
        address2.setCellValueFactory(new PropertyValueFactory<Event,String>("address"));
        eventDate2.setCellValueFactory(new PropertyValueFactory<Event,String>("eventDate"));
        number2.setCellValueFactory(new PropertyValueFactory<Event,String>("number"));
        reason2.setCellValueFactory(new PropertyValueFactory<Event,String>("reason"));
        date2.setCellValueFactory(new PropertyValueFactory<Event,String>("date"));
        name2.setCellValueFactory(new PropertyValueFactory<Event,String>("name"));
        tableView2.setItems(data);
    }
    @FXML
    public void deleteSelected02() {//event表
        String str = tableView2.getSelectionModel().getSelectedItem().toString();
        System.out.println("str_:"+str);
        EventDaoImplement eventDaoImplement=new EventDaoImplement();
        eventDaoImplement.delete(str);
        refreshTableEvent();
    }

    //**********************************************************************************************
    @FXML
    public TableView tableView3;
    @FXML
    public TableColumn name3;
    @FXML
    public TableColumn type3;
    @FXML
    public TableColumn use3;
    @FXML
    public TableColumn remark3;
    @FXML
    public TableColumn state3;
    @FXML
    public TableColumn introduce3;

    @FXML
    public void refreshTableEquiplace(){
        EquiplaceDaoImplement equiplaceDaoImplement=new EquiplaceDaoImplement();
        ObservableList<Equiplace> data= FXCollections.observableArrayList(
                equiplaceDaoImplement.selectAll()
        );

        name3.setCellValueFactory(new PropertyValueFactory<Equiplace,String>("name"));
        type3.setCellValueFactory(new PropertyValueFactory<Equiplace,String>("type"));
        use3.setCellValueFactory(new PropertyValueFactory<Equiplace,String>("use"));
        remark3.setCellValueFactory(new PropertyValueFactory<Equiplace,String>("remark"));
        state3.setCellValueFactory(new PropertyValueFactory<Equiplace,String>("state"));
        introduce3.setCellValueFactory(new PropertyValueFactory<Equiplace,String>("introduce"));
        tableView3.setItems(data);
        System.out.println("Equiplace 方法执行了");
    }
    @FXML
    public void deleteSelected03() {//Equiplace表
        String str = tableView3.getSelectionModel().getSelectedItem().toString();
        System.out.println("str_:"+str);
        EquiplaceDaoImplement equiplaceDaoImplement=new EquiplaceDaoImplement();
        equiplaceDaoImplement.delete(str);
        refreshTableEquiplace();
    }

    //聊天
    //********************************************************************************************************
    @FXML
    private TextArea textArea01;
    @FXML
    private TextArea textArea02;
    private DatagramSocket DS;
    SocketAddress clientIp;
    private int Port = 9998;

    public void startUDPServer() throws Exception {//用来启动管理员的聊天线程
        UDPServer udpServer=new UDPServer();
        new Thread(udpServer).start();
    }
    class UDPServer implements Runnable{
        //由多人聊天室改编一对一聊天
        //    private ArrayList<SocketAddress> clients = new ArrayList<SocketAddress>(); //保存客户端IP地址
        public UDPServer() throws Exception{
            try {
                System.out.println("UDPServer is running!!");
                DS = new DatagramSocket(Port);
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
                    String str = new String(DP.getData(),0,DP.getLength());
                    textArea01.appendText(str+"\n");
//                if(!clients.contains(clientIp)){
//                    clients.add(clientIp);
//                }
//                    this.send(DP);
                }
            }catch(Exception ex){
            }
        }
    }
    private String str;

    @FXML
    private void send() throws Exception {
        try{
            System.out.println("enterSend方法正在运行！");
            str ="管理员"+"说：" + textArea02.getText();
            textArea01.appendText(str+"\n");
            byte[] dd = str.getBytes();
            DatagramPacket Data = new DatagramPacket(dd,dd.length,clientIp);
            DS.send(Data);
            textArea02.clear();
            System.out.println("客户端信息发送成功！");
        }catch(Exception ex){
        }
    }
    //********************************************************************************************************

    //构造方法
    @Override
    public void setStageController(StageController stageController) {
        this.stageController=stageController;
        try {
            startUDPServer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
