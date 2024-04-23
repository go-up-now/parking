
package parking.utils;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;

public class JdbcHelper {
    static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    static String dburl = "jdbc:sqlserver://localhost;databaseName=Park";
    static String user = "sa";
    static String pass = "songlong";
    
    static {
        try {
            Class.forName(driver);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
    
    public static PreparedStatement getStatement(String sql, Object...args) throws SQLException{
        Connection connection = DriverManager.getConnection(dburl, user, pass);
        PreparedStatement preparedStatement;
        if(sql.trim().startsWith("{"))
            preparedStatement = connection.prepareCall(sql); // Gọi thủ tục
        else 
            preparedStatement = connection.prepareStatement(sql); // Gọi câu lệnh sql
        if(args.length>0){
            for(int i=0; i<args.length; i++){
                preparedStatement.setObject(i+1, args[i]);
            }
        }
        return preparedStatement;
    }
    
    public static ResultSet ExecuteQuery(String sql, Object...args) throws SQLException{
        PreparedStatement preparedStatement = getStatement(sql, args);
        return preparedStatement.executeQuery();
    }
    
    public static Object getFirstValue(String sql, Object...args) throws SQLException{
        ResultSet resultSet = ExecuteQuery(sql, args);
        while(resultSet.next()){
            return resultSet.getObject(0);
        }
        resultSet.getStatement().getConnection().close();
        return null;
    }
    
    public static int executeUpdate(String sql, Object...args) throws SQLException{
        PreparedStatement preparedStatement = getStatement(sql, args);
        try {
            return preparedStatement.executeUpdate();
        } finally {
            preparedStatement.getConnection().close();
        }
    }
}
