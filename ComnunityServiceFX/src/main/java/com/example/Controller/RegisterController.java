package com.example.Controller;

import com.example.DB.Table.Admin;
import com.example.DB.Table.User;
import com.example.ServerClient.Client;
import com.example.StageController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class RegisterController implements ControlledStage{
    private StageController stageController;
    Client client=LoginController.client;
    @FXML
    private TextField textField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button button;

    public RegisterController() throws IOException {
    }

    @FXML
    public void register() throws IOException, ClassNotFoundException {
        String id=textField.getText();
        String password=passwordField.getText();
        if(LoginController.loginChoice==1){
            User user=new User(id,password);
            System.out.println("user in "+id+password);
            client.setUser(user);
            int state=client.getState();
            if(state==0){
                System.out.println("新用户注册成功！");
            }
            else if(state==1){
                System.out.println("用户已注册！");
            }
        }else {
            Admin admin=new Admin(id,password);
            System.out.println("admin in "+id+password);
            client.setAdmin(admin);
            int state=client.getState();
            if(state==0){
                System.out.println("新用户注册成功！");
            }
            else if(state==1){
                System.out.println("用户已注册！");
            }
        }
        stageController.setStage("注册","登录");
    }

    @Override
    public void setStageController(StageController stageController) {
        this.stageController=stageController;
    }
}
