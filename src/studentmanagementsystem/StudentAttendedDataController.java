/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmanagementsystem;

import Database_Connection.Database_Connection;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author aadikumar
 */
public class StudentAttendedDataController implements Initializable 
{
    @FXML
    private VBox vbox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void Show_Attendence(String course_id,int stu_id)
    {
        Connection connection = new Database_Connection().Database_Connectivity();
        String select = "SELECT DATE,STATUS FROM attendence WHERE STUDENT_ID='"+stu_id+"' AND COURSE_ID='"+course_id+"'";
        try {
            ResultSet result = connection.createStatement().executeQuery(select);
            while (result.next()) 
            {
                HBox box = new HBox();
                Label date = new Label(result.getString("DATE"));
                Label status = new Label(result.getString("STATUS"));
                box.getChildren().addAll(date,status);
                date.setPadding(new Insets(10,20,10, 20));
                status.setPadding(new Insets(10,20,10, 40));
                date.setFont(new Font(24));
                status.setFont(new Font(24));
                box.setBorder(new Border(new BorderStroke(Paint.valueOf("BLUE"),BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
                vbox.getChildren().add(box);
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentAttendedDataController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
