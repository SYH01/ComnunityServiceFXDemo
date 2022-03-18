package com.example.DB.Dao;

import com.example.DB.DBConnection;
import com.example.DB.DaoImplement.NoticeDao;
import com.example.DB.Table.Appeal;
import com.example.DB.Table.Notice;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NoticeDaoImplement implements NoticeDao {
    private static String SQL_INSERT="insert into notice values(?,?)";
    private static String SQL_SELECT="select * from notice";
    private final String SQL_DELETE = "delete from notice where number=?";
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;
    @Override
    public int insert(Notice notice) throws SQLException {
        preparedStatement= DBConnection.getConnection().prepareStatement(SQL_INSERT);
        preparedStatement.setString(1,notice.getNumber());
        preparedStatement.setString(2,notice.getContent());
        preparedStatement.executeUpdate();
        DBConnection.close(preparedStatement);
        return 0;
    }

    @Override
    public int delete(String number) {
        try{
            preparedStatement = DBConnection.getConnection().prepareStatement(SQL_DELETE);
            preparedStatement.setString(1,number );
            preparedStatement.executeUpdate();
            DBConnection.close(resultSet,preparedStatement, DBConnection.getConnection());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Notice> selectAll() {
        List<Notice> notices = new ArrayList<>();
        ResultSet resultSet;
        try{
            preparedStatement = DBConnection.getConnection().prepareStatement(SQL_SELECT);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                notices.add(new Notice(
                        resultSet.getString("number"),
                        resultSet.getString("content")));
            }
            DBConnection.close(resultSet, preparedStatement, DBConnection.getConnection());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return notices;
    }

}
