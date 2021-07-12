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
public class DeleteController implements Initializable {

    @FXML
    private ChoiceBox select_delete;
    @FXML
    private TextField delete_entity_ID;
    
    private String selectedEntity;
    private ObservableList<String> entity = FXCollections.observableArrayList("Student","Teacher","Course");
    @FXML
    private Label deleted_entity;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        select_delete.setValue("Studebt");
        select_delete.setItems(entity);
    }    

    @FXML
    private void deleteFromDatabase(ActionEvent event) 
    {
        String user = null;
        selectedEntity = select_delete.getValue().toString();
        Connection connection = new Database_Connection().Database_Connectivity();
        String query = "SELECT USER FROM users WHERE ID='"+delete_entity_ID.getText()+"'";
        try {
            Statement state = connection.createStatement();
            ResultSet res = state.executeQuery(query);
            while (res.next()) 
            {
                user = res.getString(1);
            }
            
            if (user.equals("ADMIN"))
            {
            delete_entity_ID.setText("");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DeleteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        PreparedStatement statement;
        ResultSet result;
        int executed;
        if(!delete_entity_ID.getText().equals(""))
        {
            String deleteQueryUser = "Delete from users where ID='"+ delete_entity_ID.getText() +"' AND USER='"+select_delete.getValue().toString()+"'";
            String deleteQueryStudent = "UPDATE students SET LEFT_DATE='"+ TeacherRegistrationController.Date_Input() +"',CURRENTLY_PRESENT='FALSE' where STUDENT_ID='"+ delete_entity_ID.getText() +"'";
            String deleteQueryTeacher = "UPDATE teachers SET LEFT_DATE='"+ TeacherRegistrationController.Date_Input() +"',CURRENTLY_PRESENT='FALSE' where TEACHER_ID='"+ delete_entity_ID.getText() +"'";
            String deleteQueryCourse = "Delete from courses where COURSE_ID='"+ delete_entity_ID.getText() +"'";
            try
            {
            if(selectedEntity.equals("Student"))
            {
            
            String deleteQueryStudent1 = "SELECT STUDENT_ID,FIRST_NAME,LAST_NAME,EMAIL FROM students where STUDENT_ID='"+delete_entity_ID.getText()+"'";
            result = connection.createStatement().executeQuery(deleteQueryStudent1);
            statement = connection.prepareStatement(deleteQueryStudent);
            executed = statement.executeUpdate();
            statement = null;
            executed = 0;
            statement = connection.prepareStatement(deleteQueryUser);
            executed = statement.executeUpdate();
            StartScreenController.AlertBoxConfirmation("Seccessful", "Successfully Deleted");
                while (result.next())
                {                    
                 deleted_entity.setText("ID: "+result.getString("STUDENT_ID")+" NAME: "+result.getString("FIRST_NAME")+" "+result.getString("LAST_NAME")+" EMAIL: "+result.getString("EMAIL"));
                }
            
            }
            else if(selectedEntity.equals("Teacher"))
            {
            
            String deleteQueryTeacher1 = "SELECT TEACHER_ID,FIRST_NAME,LAST_NAME,EMAIL FROM teachers where TEACHER_ID='"+delete_entity_ID.getText()+"'";
            result = connection.createStatement().executeQuery(deleteQueryTeacher1);
            statement = connection.prepareStatement(deleteQueryTeacher);
            executed = statement.executeUpdate();
            statement = null;
            executed = 0;
            statement = connection.prepareStatement(deleteQueryUser);
            executed = statement.executeUpdate();
            StartScreenController.AlertBoxConfirmation("Seccessful", "Successfully Deleted");
                while (result.next())
                {                    
                 deleted_entity.setText("ID: "+result.getString("TEACHER_ID")+" NAME: "+result.getString("FIRST_NAME")+" "+result.getString("LAST_NAME")+" EMAIL: "+result.getString("EMAIL"));
                }
            }
            else if(selectedEntity.equals("Course"))
            {
            
            String deleteQueryCourse1 = "SELECT COURSE_ID,COURSE_NAME FROM courses where COURSE_ID='"+delete_entity_ID.getText()+"'";
            result = connection.createStatement().executeQuery(deleteQueryCourse1);
            statement = connection.prepareStatement(deleteQueryCourse);
            executed = statement.executeUpdate();
            StartScreenController.AlertBoxConfirmation("Seccessful", "Successfully Deleted");
                while (result.next())
                {                    
                 deleted_entity.setText("ID: "+result.getString("COURSE_ID")+" NAME: "+result.getString("COURSE_NAME"));
                }
            }
            else
                    System.out.println("IDK");
            }
            catch(SQLException e)
            {
                StartScreenController.AlertBoxError("Error", "Operation Failed"+e);
            }
        }
        
    }
    
}
