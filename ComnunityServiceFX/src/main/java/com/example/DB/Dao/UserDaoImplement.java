package com.example.DB.Dao;

import com.example.DB.DBConnection;
import com.example.DB.Table.User;
import com.example.DB.DaoImplement.UserDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImplement implements UserDao {
    private static String SQL_INSERT="insert into user values(?,?)";
    private static String SQL_SELECT="select password from user where id=?";
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;

    @Override
    public int insert(User user) throws SQLException {
        preparedStatement= DBConnection.getConnection().prepareStatement(SQL_INSERT);
        preparedStatement.setString(1,user.getId());
        preparedStatement.setString(2,user.getPassword());
        preparedStatement.executeUpdate();
        DBConnection.close(preparedStatement);
        return 0;
    }

    @Override
    public int select(User user) throws Exception {
        int state=0;
        preparedStatement =DBConnection.getConnection().prepareStatement(SQL_SELECT);
        preparedStatement.setString(1,user.getId());
        resultSet=preparedStatement.executeQuery();
        if (resultSet.next()) {//用户存在
            if (resultSet.getString("password").equals(user.getPassword()))
            {
                state=1;      //密码正确
            }else{
                state=-1;     //密码错误
            }
        }else{                  //用户不存在
                state=0;
        }
        DBConnection.close(resultSet,preparedStatement,DBConnection.getConnection());
        return state;
    }
}
