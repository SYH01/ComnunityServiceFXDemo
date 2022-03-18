package com.example.DB.DaoImplement;

import com.example.DB.Table.User;

import java.sql.SQLException;

public interface UserDao {
    int insert(User user) throws SQLException;

    int select(User user) throws Exception;
}
