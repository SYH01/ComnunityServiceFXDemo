package com.example.DB.DaoImplement;

import com.example.DB.Table.Admin;
import com.example.DB.Table.User;

import java.sql.SQLException;

public interface AdminDao {
    int insert(Admin admin) throws SQLException;

    int select(Admin admin) throws Exception;

    int update(Admin admin) throws Exception;
}
