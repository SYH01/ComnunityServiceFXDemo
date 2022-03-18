package com.example.DB.DaoImplement;

import com.example.DB.Table.Comments;
import com.example.DB.Table.Notice;

import java.sql.SQLException;
import java.util.List;

public interface CommentsDao {
    int insert(Comments comments) throws SQLException;
    List<Comments> selectAll();
}
