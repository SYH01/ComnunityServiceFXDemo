package com.example.DB.DaoImplement;

import com.example.DB.Table.Appeal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface AppealDao {
    int insert(Appeal appeal) throws SQLException;
    int delete(String number);
    public List<Appeal> selectAll();
}
