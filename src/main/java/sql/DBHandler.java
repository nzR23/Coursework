package sql;
import java.sql.*;

public class DBHandler {
    public static Connection DBHandler() throws SQLException {
        Connection dbConnect = DriverManager.getConnection("jdbc:mysql://localhost:3306/railway", "root", "230103nazar");
        return dbConnect;
    }
    public void signUpUser(User user){
        String insert = "INSERT INTO " + Const.USER_TABLE + "(" +
                Const.USER_LOGIN + "," + Const.USER_PASSWORD + ")" +
                "VALUES(?,?)";
        try {
            PreparedStatement prSt = DBHandler().prepareStatement(insert);
            prSt.setString(1, user.getLogin());
            prSt.setString(2, user.getPassword());
            prSt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public ResultSet getUser(User user){
        ResultSet resSet = null;
        String select = "SELECT * FROM " + Const.USER_TABLE + " WHERE " + Const.USER_LOGIN + "=? AND " +
                Const.USER_PASSWORD + "=?";
        try {
            PreparedStatement prSt = DBHandler().prepareStatement(select);
            prSt.setString(1, user.getLogin());
            prSt.setString(2, user.getPassword());
            
            resSet = prSt.executeQuery();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return resSet;
    }
}
