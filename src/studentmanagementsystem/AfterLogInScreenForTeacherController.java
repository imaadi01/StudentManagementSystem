/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmanagementsystem;

import Database_Connection.Database_Connection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author aadikumar
 */
public class AfterLogInScreenForTeacherController implements Initializable {

    @FXML
    private BorderPane borderpane;
    @FXML
    private SubScene subscene;

    private int id;
    
    private String user;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
    

    public void setId(int id) {
        this.id = id;
    }

    public void setUser(String user) {
        this.user = user;
    }
    
    
    
    
    public void TeacherDashboardScreen()
    {
    FXMLLoader fm = new FXMLLoader(getClass().getResource("TeacherDashboard.fxml"));
        try {
            Parent root = fm.load();
            subscene.setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AfterLogInScreenForStudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
            TeacherDashboardController dashboard =fm.getController();
        try {
        Database_Connection database = new Database_Connection();
        Connection connection = database.Database_Connectivity();
        String fetch_query = "SELECT USER FROM users where ID= '"+ id +"'";
        Statement statement1 = connection.createStatement();
            ResultSet result1 =statement1.executeQuery(fetch_query);
            while(result1.next())
            user = result1.getString(1);
         fetch_query = "SELECT * FROM teachers WHERE TEACHER_ID= '" + id + "'";           
         Statement statement = connection.createStatement();
            ResultSet result =statement.executeQuery(fetch_query);
            while (result.next()) 
            {                
                dashboard.setId_entity_Var(Integer.valueOf(result.getString("TEACHER_ID")));
                dashboard.setName_entity_Var(result.getString("FIRST_NAME")+" "+result.getString("LAST_NAME"));
                dashboard.setFatherName_entity_Var(result.getString("FATHER_NAME"));
                dashboard.setEmail_entity_Var(result.getString("EMAIL"));
                dashboard.setDateOfBirth_entity_Var(result.getString("DATE_OF_Birth"));
                dashboard.setAddress_entity_Var(result.getString("ADDRESS"));
                dashboard.setContactNumber_entity_Var(result.getString("CONTACT_NUMBER"));
                dashboard.setDepartment_entity_Var(result.getString("DEPARTMENT"));
                dashboard.setQualification_entity_Var(result.getString("QUALIFICATION"));
                dashboard.setJoined_entity_Var(result.getString("JOINED"));
                dashboard.setGender_entity_Var(result.getString("GENDER"));
                dashboard.setFields();
            }
            
        } catch (SQLException ex) {
            System.out.println("Dashboard Screen Error\n"+ex);
            Logger.getLogger(AfterLogInScreenForTeacherController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

    @FXML
    private void Dashboard_Button(ActionEvent event) 
    {
            TeacherDashboardScreen();
        
    }

    
    private void LoadScreen(String screen)
    {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(screen+".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(AfterLogInScreenForTeacherController.class.getName()).log(Level.SEVERE, null, ex);
        }
        subscene.setRoot(root);
        
    }
    
    

    @FXML
    private void Courses_Button(ActionEvent event)
    {
        FXMLLoader fm = new FXMLLoader(getClass().getResource("CoursesTeaching.fxml"));
        try {
            Parent root = fm.load();
            CoursesTeachingController course = fm.getController();
            subscene.setRoot(root);
            course.setId(id);
            course.MakeBoxes();
        } catch (IOException ex) {
            Logger.getLogger(AfterLogInScreenForTeacherController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Attendence_Button(ActionEvent event) {
    }


    @FXML
    private void LogOut_Button(ActionEvent event)
    {
        try {
            FXMLLoader fm = new FXMLLoader(getClass().getResource("StartControlScreen.fxml"));
            Parent root = fm.load();
            Stage s1 = new Stage();
            Scene scene1 = new Scene(root);
            s1.setScene(scene1);
            s1.setTitle("Quiz Application By Aadesh Kumar");
            s1.show();
            borderpane.getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(AfterLogInScreenForTeacherController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @FXML
    private void Close_program(MouseEvent event) 
    {
        Stage stage = (Stage) borderpane.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void Setting_Button(ActionEvent event) 
    {
        try {
            FXMLLoader fm = new FXMLLoader(getClass().getResource("SettingScreen.fxml"));
            Parent root = fm.load();
            SettingScreenController setting = fm.getController();
            setting.setId_entity_Var(id);
            subscene.setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AfterLogInScreenForTeacherController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
}
