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
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author aadikumar
 */
public class AssignCoursesController implements Initializable {

    @FXML
    private ChoiceBox course_entity;
    @FXML
    private ChoiceBox teacher_entity;
    @FXML
    private ChoiceBox section_entity;
    @FXML
    private ChoiceBox semester_entity;
    
    ObservableList<String> courses = FXCollections.observableArrayList();
    ObservableList<String> teachers = FXCollections.observableArrayList();
    ObservableList<String> sections = FXCollections.observableArrayList("A","B","C");
    ObservableList<String> semesters = FXCollections.observableArrayList("1","2","3","4","5","6","7","8");
    
    private int courseId;
    private int tecaherId;
    
    
    public void getCourses(String semester)
    {
        courses.clear();
        Connection connection = new Database_Connection().Database_Connectivity();
        String getQuery = "SELECT COURSE_NAME FROM courses WHERE SEMESTER='"+ semester +"'";
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(getQuery);
            while (result.next()) 
            {
                courses.add(result.getString("COURSE_NAME"));
            }
        connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(AssignCoursesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        course_entity.setItems(courses);
    }
    
    public void getTeachers()
    {
        try {
        Database_Connection database  = new Database_Connection();
        Connection connection = database.Database_Connectivity();
        String fetchQuery = "SELECT FIRST_NAME,LAST_NAME FROM teachers WHERE CURRENTLY_PRESENT='TRUE'";
        Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(fetchQuery);
            while (result.next())
            {     
                teachers.add(result.getString(1)+" "+result.getString(2));
            }
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(CourseRegistrationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        teacher_entity.setItems(teachers);
    }

    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        semester_entity.setItems(semesters);
        section_entity.setItems(sections);
        getTeachers();
    }    
    
    @FXML
    private void assignButton(ActionEvent event) 
    {
        String[] first_name = teacher_entity.getValue().toString().split(" ");
        Connection connection = new Database_Connection().Database_Connectivity();
        String insertQuery = "INSERT INTO courses_assigned (COURSE_ID,TEACHER_ID,SECTION,SEMESTER) VALUES ((SELECT COURSE_ID FROM courses WHERE COURSE_NAME='"+ course_entity.getValue().toString() +"'),(SELECT TEACHER_ID FROM teachers WHERE First_NAME='"+ first_name[0] +"' AND LAST_NAME='"+first_name[1]+"' AND CURRENTLY_PRESENT='TRUE'),'"+ section_entity.getValue().toString() +"','"+ Integer.parseInt(semester_entity.getValue().toString()) +"')";
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(insertQuery);
            StartScreenController.AlertBoxConfirmation("Success", "Successfully Inserted");
        } catch (SQLException ex) {
            Logger.getLogger(AssignCoursesController.class.getName()).log(Level.SEVERE, null, ex);
            StartScreenController.AlertBoxError("Error","Failed\n"+ex);
        }
        
    }

    @FXML
    private void assignValuesAccordingToSemester(MouseEvent event)
    {
        getCourses(semester_entity.getValue().toString());
        
        
    }
    
}
