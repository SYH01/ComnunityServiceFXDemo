package com.example.DB.Dao;

import com.example.DB.DBConnection;
import com.example.DB.DaoImplement.EquiplaceDao;
import com.example.DB.Table.Appeal;
import com.example.DB.Table.Equiplace;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EquiplaceDaoImplement implements EquiplaceDao {
    private static String SQL_INSERT="insert into equiplace values(?,?,?,?,?,?)";
    private static String SQL_SELECT="select * from equiplace";
    private final String SQL_DELETE = "delete from equiplace where name=?";
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;

    @Override
    public int insert(Equiplace equiplace) throws SQLException {
        preparedStatement= DBConnection.getConnection().prepareStatement(SQL_INSERT);
        preparedStatement.setString(1,equiplace.getName());
        preparedStatement.setString(2,equiplace.getType());
        preparedStatement.setString(3,equiplace.getUse());
        preparedStatement.setString(4,equiplace.getRemark());
        preparedStatement.setString(5,equiplace.getState());
        preparedStatement.setString(6,equiplace.getIntroduce());
        preparedStatement.executeUpdate();
        DBConnection.close(preparedStatement);
        return 0;
    }

    @Override
    public int delete(String name) {
        try{
            preparedStatement = DBConnection.getConnection().prepareStatement(SQL_DELETE);
            preparedStatement.setString(1,name);
            preparedStatement.executeUpdate();
            DBConnection.close(resultSet,preparedStatement, DBConnection.getConnection());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Equiplace> selectAll() {
        List<Equiplace> equiplaces = new ArrayList<>();
        ResultSet resultSet;
        try{
            preparedStatement = DBConnection.getConnection().prepareStatement(SQL_SELECT);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                equiplaces.add(new Equiplace(
                        resultSet.getString("name"),
                        resultSet.getString("type"),
                        resultSet.getString("use"),
                        resultSet.getString("remark"),
                        resultSet.getString("state"),
                        resultSet.getString("introduce")));
            }
            DBConnection.close(resultSet, preparedStatement, DBConnection.getConnection());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return equiplaces;

    }
}
