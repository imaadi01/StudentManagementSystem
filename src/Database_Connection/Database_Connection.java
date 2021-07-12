package Database_Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.scene.control.Alert;

public class Database_Connection {
    Connection cn;
    public Connection Database_Connectivity() 
    {
        String userName = "root";
        String password = "";
        String URL = "jdbc:mysql://localhost:3306/studentmanagemenysystem?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn= DriverManager.getConnection(URL,userName,password);
        } catch (ClassNotFoundException | SQLException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Connection Failed");
        alert.setTitle("Connection Error");
        alert.show();
            System.out.println(e);
        }
        return cn;
    }
    
}
