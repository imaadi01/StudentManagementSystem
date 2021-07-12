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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author aadikumar
 */
public class CourseRegistrationController implements Initializable {

    @FXML
    private TextField courseName_entity;
    @FXML
    private Label incorrectInfo_entity;
    
    
    int i,j;
    
    ObservableList<String> semesters = FXCollections.observableArrayList("1","2","3","4","5","6","7","8");
    @FXML
    private TextField courseDescription_entity;
    @FXML
    private ChoiceBox semester_entity;
    
    private int courseId = 3000;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
        semester_entity.setItems(semesters);
    } 
    
    

    @FXML
    private void registerCourse(ActionEvent event) 
    {
        int idFetched = 0;
        String Query;
        Statement statement;
        ResultSet result;
        if(!courseName_entity.getText().equals("") && !courseDescription_entity.getText().equals(""))
        {
        Connection connection = new Database_Connection().Database_Connectivity();
        Query = "SELECT * FROM courses";
        
        try {
        statement = connection.createStatement();
        result = statement.executeQuery(Query);
            while (result.next()) 
            {
            idFetched = Integer.parseInt(result.getString("COURSE_ID"));
            }
            if(idFetched>=3000)
                courseId = idFetched;
            
            Query = "INSERT INTO courses (COURSE_ID,COURSE_NAME,COURSE_DESCRIPTION,SEMESTER) VALUES ('"+ (++courseId) +"','"+ courseName_entity.getText() +"','"+ courseDescription_entity.getText() +"','"+ Integer.parseInt(semester_entity.getValue().toString()) +"')";
            statement.executeUpdate(Query);
            StartScreenController.AlertBoxConfirmation("Success","Inserted Successfully");
        } 
        catch (SQLException ex) {
        Logger.getLogger(CourseRegistrationController.class.getName()).log(Level.SEVERE, null, ex);
        StartScreenController.AlertBoxError("Failed","Insertion failed");
        }
        
        
        }
    }
    
}
