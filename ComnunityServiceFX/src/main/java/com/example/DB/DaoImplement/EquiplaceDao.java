package com.example.DB.DaoImplement;

import com.example.DB.Table.Appeal;
import com.example.DB.Table.Equiplace;

import java.sql.SQLException;
import java.util.List;

public interface EquiplaceDao {
    int insert(Equiplace equiplace) throws SQLException;
    int delete(String eventNumber);
    List<Equiplace> selectAll();
}
