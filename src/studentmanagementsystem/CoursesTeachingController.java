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
public class CoursesTeachingController implements Initializable {

    @FXML
    private VBox vbox;
    
    private int id;

    public void setId(int id) {
        this.id = id;
    }
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void MakeBoxes()
    {
        System.out.println(id);
        Connection connection = new Database_Connection().Database_Connectivity();
        String query = "select COURSE_ID,SECTION,SEMESTER from courses_assigned,teachers where teachers.TEACHER_ID=courses_assigned.TEACHER_ID AND teachers.TEACHER_ID='"+ id +"'";
        try {
            ResultSet result = connection.createStatement().executeQuery(query);
            while (result.next())
            {           
                System.out.println(result.getString(1));
            HBox box = new HBox();
            vbox.getChildren().add(box);
            Label course = new Label();
            Label semester = new Label();
            Label section = new Label();
            course.setWrapText(true);
            semester.setWrapText(true);
            section.setWrapText(true);
            Button course_b = new Button();
            Button attendence = new Button("Attendence");
            query = "select COURSE_NAME from courses where COURSE_ID = '"+result.getString(1)+"'";
            ResultSet result1 = connection.createStatement().executeQuery(query);
                while (result1.next()) 
                {                    
                    course.setText(result1.getString("COURSE_NAME"));
                    semester.setText(result.getString("SEMESTER"));
                    section.setText(result.getString("SECTION"));
                    course_b.setText("Students");
                    course.setMinSize(100,50);
                    section.setMinSize(50,50);
                    semester.setMinSize(50,50);
                    box.setBorder(new Border(new BorderStroke(Paint.valueOf("BLACK"),BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));
                    box.setPadding(new Insets(30,30,30, 30));
                    course.setPadding(new Insets(10,20,20, 20));
                    course.setPadding(new Insets(10,20,20, 20));
                    section.setPadding(new Insets(10,20,20, 20));
                    semester.setFont(new Font(24));
                    course.setFont(new Font(24));
                    section.setFont(new Font(24));
                    course_b.setMinSize(50,50);
                    attendence.setMinSize(100,50);
                    box.getChildren().addAll(course,section,semester,course_b,attendence);
                    box.setAlignment(Pos.CENTER);
                    course.setAlignment(Pos.CENTER);
                    semester.setAlignment(Pos.CENTER);
                    section.setAlignment(Pos.CENTER);
                    course_b.setId(id+","+result.getString("SECTION")+","+result.getString("SEMESTER")+","+result.getString("COURSE_ID"));
                    attendence.setId(id+","+result.getString("SECTION")+","+result.getString("SEMESTER")+","+result.getString("COURSE_ID"));
                    course_b.setOnAction((event) -> {
                    String[] entities= course_b.getId().split(",");
                    try
                    {
                        FXMLLoader fm = new FXMLLoader(getClass().getResource("AssignedCoursesStudent.fxml"));
                        Parent root = fm.load();
                        Stage stage = new Stage();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                        AssignedCoursesStudentController assign = fm.getController();
                        assign.Students(Integer.parseInt(entities[3]),entities[0],entities[1],Integer.parseInt(entities[2]));
                    }
                    catch(Exception ex)
                    {
                        System.out.println(ex);
                    StartScreenController.AlertBoxError("Error", ""+ex);
                    }
                    }
                    );
                    attendence.setOnAction((event) -> 
                    {
                      String[] entities= course_b.getId().split(",");
                    try
                    {
                        FXMLLoader fm = new FXMLLoader(getClass().getResource("StudentAttendence.fxml"));
                        Parent root = fm.load();
                        Stage stage = new Stage();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                        StudentAttendenceController assign = fm.getController();
                        assign.Student_Attendence(entities[2],entities[3],entities[1],entities[0]);
                    }
                    catch(Exception ex)
                    {
                    StartScreenController.AlertBoxError("Error", ""+ex);
                    }  
                    });
                }
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(CoursesTeachingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
}
