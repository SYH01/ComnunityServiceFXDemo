package com.example.DB.Dao;

import com.example.DB.DBConnection;
import com.example.DB.DaoImplement.EventDao;
import com.example.DB.Table.Appeal;
import com.example.DB.Table.Event;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventDaoImplement implements EventDao {
    private static String SQL_INSERT="insert into event values(?,?,?,?,?,?,?,?)";
    private static String SQL_SELECT="select * from event";
    private final String SQL_DELETE = "delete from event where eventnumber=?";
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;
    @Override
    public int insert(Event event) throws SQLException {
        preparedStatement= DBConnection.getConnection().prepareStatement(SQL_INSERT);
        preparedStatement.setString(1,event.getEventNumber());
        preparedStatement.setString(2,event.getTitle());
        preparedStatement.setString(3,event.getAddress());
        preparedStatement.setString(4,event.getEventDate());
        preparedStatement.setString(5,event.getNumber());
        preparedStatement.setString(6,event.getReason());
        preparedStatement.setString(7,event.getDate());
        preparedStatement.setString(8,event.getName());
        preparedStatement.executeUpdate();
        DBConnection.close(preparedStatement);
        return 0;
    }

    @Override
    public int delete(String eventnumber) {
        try{
            preparedStatement = DBConnection.getConnection().prepareStatement(SQL_DELETE);
            preparedStatement.setString(1,eventnumber );
            preparedStatement.executeUpdate();
            DBConnection.close(resultSet,preparedStatement, DBConnection.getConnection());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Event> selectAll() {
            List<Event> events = new ArrayList<>();
            ResultSet resultSet;
            try{
                preparedStatement = DBConnection.getConnection().prepareStatement(SQL_SELECT);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    events.add(new Event(
                            resultSet.getString("eventnumber"),
                            resultSet.getString("title"),
                            resultSet.getString("address"),
                            resultSet.getString("eventdate"),
                            resultSet.getString("number"),
                            resultSet.getString("reason"),
                            resultSet.getString("date"),
                            resultSet.getString("name")));
                }
                DBConnection.close(resultSet, preparedStatement, DBConnection.getConnection());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return events;
    }
}
