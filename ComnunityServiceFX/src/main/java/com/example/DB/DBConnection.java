package com.example.DB;

import java.sql.*;

public class DBConnection {
    // 获取数据库连接
    private static Connection con=null;
    private static PreparedStatement pstmt=null;
    private static ResultSet rs=null;

    private static final String url="jdbc:mysql://localhost:3306/sun";
    private static final String name="root";
    private static final String pwd="password";

    public static Connection getConnection() {
        //你的实现代码
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url,name,pwd);
           System.out.println("数据库连接成功！");
        }catch(Exception e){
            e.printStackTrace();
        }
        return con;

    }

    //关闭数据库资源
    public static void close(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.close();
    }

    // 关闭数据库资源
    public static void close(ResultSet rs, PreparedStatement pstmt, Connection conn) throws Exception {
        //你的实现代码
        if(rs!=null){
                rs.close();
        }
        if(pstmt!=null){
                pstmt.close();
        }
        if(conn!=null){
                conn.close();
        }

    }
}

