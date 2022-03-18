package com.example.Controller;

import com.example.DB.Table.Admin;
import com.example.DB.Table.User;
import com.example.ServerClient.Client;
import com.example.ServerClient.Server;
import com.example.ServerClient.UDPServer;
import com.example.StageController;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import java.io.IOException;

public class LoginController implements ControlledStage{
   private StageController stageController;
   public static Client client;

    static {
        try {
            client = new Client();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String name;
   public static int loginChoice=1;
   @FXML
   private ToggleGroup toggleGroup;
   @FXML
   private TextField idField;
   @FXML
   private PasswordField passwordField;
   @FXML
   private RadioButton radioButton2;
   @FXML
   private RadioButton radioButton1;

    public LoginController() throws IOException {
    }

    @FXML
    private void loginChoice1(){
        this.loginChoice=1;
        System.out.println("loginChoice:"+loginChoice);
    }
   @FXML
   private void loginChoice2(){
       this.loginChoice=2;
       System.out.println("loginChoice:"+loginChoice);
   }

   @FXML
   private void login() throws Exception {

       String id=idField.getText();
       String password=passwordField.getText();
       if(loginChoice==1){
           User user=new User(id,password);
           client.setUser(user);
           int state=client.getState();
           switch(state){
               case -1:
                   System.out.println("密码错误！");
                   passwordField.clear();
                   break;

               case 0:
                   System.out.println("用户不存在！");
                   break;

               case 1:
                   System.out.println("登陆成功！");
                   name=id;
                   idField.clear();
                   passwordField.clear();
                   stageController.setStage("登录","首页");
                   MainController.client=client;
//                new UDPServer();
                   break;
           }
       }else{
           Admin admin=new Admin(id,password);
           client.setAdmin(admin);
           int state=client.getState();
           switch(state){
               case -1:
                   System.out.println("密码错误！");
                   passwordField.clear();
                   break;

               case 0:
                   System.out.println("用户不存在！");
                   break;

               case 1:
                   System.out.println("登陆成功！");
                   name=id;
                   idField.clear();
                   passwordField.clear();
                   stageController.setStage("登录","管理");
                   AdminController.client=client;
//                new UDPServer();
                   break;
           }
       }

   }

   @FXML
   public void register(){
        stageController.setStage("登录","注册");
   }

   public void setStageController(StageController stageController) {
        this.stageController=stageController;
    }
}
