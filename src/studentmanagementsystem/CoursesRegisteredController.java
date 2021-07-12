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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author aadikumar
 */
public class CoursesRegisteredController implements Initializable {

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

    public void MakeRegisteredCoursesBox() {
        int i = 1;
        Connection connection = new Database_Connection().Database_Connectivity();
        String query = "SELECT courses_enrolled.COURSE_ID,courses.COURSE_NAME,teachers.FIRST_NAME,teachers.LAST_NAME FROM courses_enrolled,courses,teachers WHERE courses_enrolled.COURSE_ID=courses.COURSE_ID AND courses_enrolled.INSTRUCTOR=teachers.TEACHER_ID AND STUDENT='" + stu_id + "'";
        try {
            ResultSet result = connection.createStatement().executeQuery(query);
            while (result.next()) {
                HBox box = new HBox();
                Label coursenumber = new Label(String.valueOf(i));
                Label courseid = new Label(result.getString("COURSE_ID"));
                Label coursename = new Label(result.getString("COURSE_NAME"));
                Label courseteacher = new Label(result.getString("FIRST_NAME") + " " + result.getString("LAST_NAME"));
                Button deregister = new Button("Deregister");
                Button attendence = new Button("Attendence");
                deregister.setId(result.getString("COURSE_ID")+","+stu_id);
                deregister.setWrapText(true);
                deregister.setMinSize(130,50);
                deregister.setPadding(new Insets(10,0,10, 20));
                attendence.setId(result.getString("COURSE_ID")+","+stu_id);
                attendence.setWrapText(true);
                attendence.setMinSize(130,50);
                attendence.setPadding(new Insets(10,0,10, 20));
                box.getChildren().addAll(coursenumber, courseid, coursename, courseteacher,deregister,attendence);
                box.setAlignment(Pos.CENTER);
                box.setPadding(new Insets(30, 0, 30, 0));
                box.setBorder(new Border(new BorderStroke(Paint.valueOf("BLUE"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
                courseid.setWrapText(true);
                coursename.setWrapText(true);
                courseteacher.setWrapText(true);
                coursenumber.setMinSize(100, 50);
                courseid.setMinSize(100, 50);
                coursename.setMinSize(150, 50);
                courseteacher.setMinSize(150, 50);
                coursenumber.setPadding(new Insets(10, 20, 10, 20));
                courseid.setPadding(new Insets(10, 20, 10, 20));
                coursename.setPadding(new Insets(10, 20, 10, 20));
                courseteacher.setPadding(new Insets(10, 20, 10, 20));
                coursenumber.setFont(new Font(24));
                courseid.setFont(new Font(24));
                coursename.setFont(new Font(24));
                courseteacher.setFont(new Font(24));
                vbox.getChildren().add(box);
                vbox.setAlignment(Pos.CENTER);
                i++;
                deregister.setOnAction((event) -> 
            {
                try {
                    String[] enrollment = deregister.getId().split(",");
                    String delete = "DELETE FROM courses_enrolled WHERE COURSE_ID='"+enrollment[0]+"' AND STUDENT='"+enrollment[1]+"'";
                    PreparedStatement statement = connection.prepareStatement(delete);
                    statement.executeUpdate();
                    StartScreenController.AlertBoxConfirmation("Success","Deregistered Successfully");
                    delete = "DELETE FROM attendence WHERE COURSE_ID='"+enrollment[0]+"' AND STUDENT_ID='"+enrollment[1]+"'";
                    statement = connection.prepareStatement(delete);
                    statement.executeUpdate();
                    System.out.println("Records Deleted");
                } catch (SQLException ex) {
                    Logger.getLogger(CoursesEnrolledRegistrationController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
                
                attendence.setOnAction((event) -> 
                {
                    try {
                    String[] enrollment = attendence.getId().split(",");
                    FXMLLoader fm = new FXMLLoader(getClass().getResource("StudentAttendedData.fxml"));
                    Parent root = fm.load();
                    StudentAttendedDataController attend = fm.getController();
                    Stage s1 = new Stage();
                    Scene scene1 = new Scene(root);
                    s1.setScene(scene1);
                    s1.setTitle("Quiz Application By Aadesh Kumar");
                    s1.show();
                    attend.Show_Attendence(enrollment[0], stu_id);
                } catch (Exception ex) {
                    Logger.getLogger(CoursesEnrolledRegistrationController.class.getName()).log(Level.SEVERE, null, ex);
                }
                });
            }
        } catch (SQLException ex) {
            Logger.getLogger(CoursesRegisteredController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

}
