package com.example.DB.DaoImplement;

import com.example.DB.Table.Event;

import java.sql.SQLException;
import java.util.List;

public interface EventDao {
    int insert(Event event) throws SQLException;
    int delete(String name);
    List<Event> selectAll();
}
