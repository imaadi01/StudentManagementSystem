/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmanagementsystem;

import Database_Connection.Database_Connection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
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
public class CoursesEnrolledRegistrationController implements Initializable {

    @FXML
    private VBox vbox;
    
    private int stu_id;

    public void setStu_id(int stu_id) {
        this.stu_id = stu_id;
    }
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void MakeCoursesBox()
    {
        int semester = 0;
        String section = null;
        Connection connection = new Database_Connection().Database_Connectivity();
        String query = "SELECT SEMESTER,SECTION FROM students WHERE STUDENT_ID='"+stu_id+"'";
        try {
            ResultSet result = connection.createStatement().executeQuery(query);
            while (result.next()) 
            {                
                semester = Integer.parseInt(result.getString("SEMESTER"));
                section = result.getString("SECTION");
            }
            query = "SELECT COURSE_NAME,teachers.TEACHER_ID,courses.COURSE_ID,SECTION,courses.SEMESTER,FIRST_NAME,LAST_NAME from courses,courses_assigned,teachers where courses_assigned.TEACHER_ID=teachers.TEACHER_ID AND courses_assigned.COURSE_ID=courses.COURSE_ID AND courses_assigned.SECTION='"+section+"' AND courses_assigned.SEMESTER='"+semester+"'";
            result = connection.createStatement().executeQuery(query);
            while(result.next())
            {
            HBox box = new HBox();
            Label courseid = new Label(result.getString("COURSE_ID"));
            Label coursename = new Label(result.getString("COURSE_NAME"));
            Label instructor = new Label(result.getString("FIRST_NAME")+" "+result.getString("LAST_NAME"));
            Button enroll = new Button("Register");
            enroll.setId(result.getString("COURSE_ID")+","+result.getString("TEACHER_ID")+","+stu_id+","+semester+","+section);
            box.getChildren().addAll(courseid,coursename,instructor,enroll);
            box.setAlignment(Pos.CENTER);
            box.setBorder(new Border(new BorderStroke(Paint.valueOf("BLUE"),BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
            box.setPadding(new Insets(10,0,10, 0));
            courseid.setWrapText(true);
            coursename.setWrapText(true);
            instructor.setWrapText(true);
            enroll.setWrapText(true);
            courseid.setMinSize(100,50);
            coursename.setMinSize(150,50);
            instructor.setMinSize(100,50);
            courseid.setPadding(new Insets(10,20,10, 20));
            coursename.setPadding(new Insets(10,20,10, 20));
            instructor.setPadding(new Insets(10,20,10, 30));
            enroll.setPadding(new Insets(10,20,10, 20));
            courseid.setFont(new Font(24));
            coursename.setFont(new Font(24));
            instructor.setFont(new Font(24));
            vbox.getChildren().add(box);
            enroll.setOnAction((event) -> 
            {
                try {
                    String[] enrollment = enroll.getId().split(",");
                    String insert = "INSERT INTO courses_enrolled (COURSE_ID,INSTRUCTOR,STUDENT,SEMESTER,SECTION) VALUES ('"+enrollment[0]+"','"+enrollment[1]+"','"+enrollment[2]+"','"+enrollment[3]+"','"+enrollment[4]+"')";
                    PreparedStatement statement = connection.prepareStatement(insert);
                    statement.executeUpdate();
                    StartScreenController.AlertBoxConfirmation("Success","Registered Successfully");
                } catch (SQLException ex) {
                    StartScreenController.AlertBoxError("Error", ""+ex);
                    Logger.getLogger(CoursesEnrolledRegistrationController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            
            }
            
        }
        catch (SQLException ex) {
            Logger.getLogger(CoursesEnrolledRegistrationController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
    
}
