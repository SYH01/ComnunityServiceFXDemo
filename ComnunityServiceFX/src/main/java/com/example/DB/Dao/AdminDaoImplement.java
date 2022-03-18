package com.example.DB.Dao;

import com.example.DB.DBConnection;
import com.example.DB.DaoImplement.AdminDao;
import com.example.DB.Table.Admin;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDaoImplement implements AdminDao {
    private static String SQL_INSERT="insert into admin values(?,?)";
    private static String SQL_SELECT="select password from admin where id=?";
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;
    @Override
    public int insert(Admin admin) throws SQLException {
        preparedStatement= DBConnection.getConnection().prepareStatement(SQL_INSERT);
        preparedStatement.setString(1,admin.getId());
        preparedStatement.setString(2,admin.getPassword());
        preparedStatement.executeUpdate();
        DBConnection.close(preparedStatement);
        return 0;
    }

    @Override
    public int select(Admin admin) throws Exception {
        int state=0;
        preparedStatement =DBConnection.getConnection().prepareStatement(SQL_SELECT);
        preparedStatement.setString(1,admin.getId());
        resultSet=preparedStatement.executeQuery();
        if (resultSet.next()) {//用户存在
            if (resultSet.getString("password").equals(admin.getPassword()))
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

    @Override
    public int update(Admin admin) throws Exception {
        return 0;
    }
}
