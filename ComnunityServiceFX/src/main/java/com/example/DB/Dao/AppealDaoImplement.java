package com.example.DB.Dao;

import com.example.DB.Table.Appeal;
import com.example.DB.DBConnection;
import com.example.DB.DaoImplement.AppealDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppealDaoImplement implements AppealDao {
    private static String SQL_INSERT="insert into appeal values(?,?,?,?,?,?)";
    private static String SQL_SELECT="select * from appeal";
    private final String SQL_DELETE = "delete from appeal where number=?";
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;

    @Override
    public int insert(Appeal appeal) throws SQLException {
        preparedStatement= DBConnection.getConnection().prepareStatement(SQL_INSERT);
        preparedStatement.setString(1,appeal.getNumber());
        preparedStatement.setString(2,appeal.getTitle());
        preparedStatement.setString(3,appeal.getContent());
        preparedStatement.setString(4,appeal.getDate());
        preparedStatement.setString(5,appeal.getRemark());
        preparedStatement.setString(6,appeal.getName());
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
    public List<Appeal> selectAll() {
        List<Appeal> appeals = new ArrayList<>();
        ResultSet resultSet;
        try{
            preparedStatement = DBConnection.getConnection().prepareStatement(SQL_SELECT);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                appeals.add(new Appeal(
                        resultSet.getString("number"),
                        resultSet.getString("title"),
                        resultSet.getString("content"),
                        resultSet.getString("date"),
                        resultSet.getString("remark"),
                        resultSet.getString("name")));
            }
            DBConnection.close(resultSet, preparedStatement, DBConnection.getConnection());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return appeals;
    }

//    @Override
//    public ResultSet select(Appeal appeal) throws Exception {
//        preparedStatement =DBConnection.getConnection().prepareStatement(SQL_SELECT);
//        resultSet=preparedStatement.executeQuery();
//        DBConnection.close(resultSet,preparedStatement,DBConnection.getConnection());
//        return resultSet;
//    }

}
