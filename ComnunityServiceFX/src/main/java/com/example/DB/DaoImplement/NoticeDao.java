package com.example.DB.DaoImplement;

import com.example.DB.Table.Notice;

import java.sql.SQLException;
import java.util.List;

public interface NoticeDao {
    int insert(Notice notice) throws SQLException;
    int delete(String number);
    List<Notice> selectAll();
}
