package com.example.DB.Dao;

import com.example.DB.DBConnection;
import com.example.DB.DaoImplement.CommentsDao;
import com.example.DB.Table.Comments;
import com.example.DB.Table.Notice;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentsDaoImplement implements CommentsDao {
    private static String SQL_INSERT="insert into comments values(?,?)";
    private static String SQL_SELECT="select * from comments";
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;
    @Override
    public int insert(Comments comments) throws SQLException {
        preparedStatement= DBConnection.getConnection().prepareStatement(SQL_INSERT);
        preparedStatement.setString(1,comments.getNumber());
        preparedStatement.setString(2,comments.getContent());
        preparedStatement.executeUpdate();
        DBConnection.close(preparedStatement);
        return 0;
    }

    @Override
    public List<Comments> selectAll() {
        List<Comments> comments = new ArrayList<>();
        ResultSet resultSet;
        try{
            preparedStatement = DBConnection.getConnection().prepareStatement(SQL_SELECT);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                comments.add(new Comments(
                        resultSet.getString("number"),
                        resultSet.getString("content")));
            }
            DBConnection.close(resultSet, preparedStatement, DBConnection.getConnection());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return comments;
    }
}
