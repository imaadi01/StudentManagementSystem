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


public class AfterLogInScreenForStudentController implements Initializable {

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

    
    

    public static String RandomPasswordGenerator()
    {
        String alphabets = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        char characters[] = alphabets.toCharArray();
        String password = "12345";
        int passLength;
        Random random = new Random();
        passLength=random.nextInt(4);
        for (int i = 0; i < passLength; i++) {
            password+= characters[random.nextInt(0+61)];
            
        }
    return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser(String user) {
        this.user = user;
    }
    
    
    
    public void StudentDashboardScreen()
    {
    FXMLLoader fm = new FXMLLoader(getClass().getResource("StudentDashboard.fxml"));
        try {
            Parent root = fm.load();
            subscene.setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AfterLogInScreenForStudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
            StudentDashboardController dashboard =fm.getController();
        try {
        Database_Connection database = new Database_Connection();
        Connection connection = database.Database_Connectivity();
        String fetch_query = "SELECT USER FROM users where ID= '"+ id +"'";
        Statement statement1 = connection.createStatement();
            ResultSet result1 =statement1.executeQuery(fetch_query);
            while(result1.next())
            user = result1.getString(1);
         fetch_query = "SELECT * FROM students WHERE STUDENT_ID= '" + id + "'";           
         Statement statement = connection.createStatement();
            ResultSet result =statement.executeQuery(fetch_query);
            while (result.next()) 
            {                
                dashboard.setStu_id(result.getString(1));
                dashboard.setFull_name(result.getString(2)+" "+result.getString(3));
                dashboard.setF_name_entity_Var(result.getString(4));
                dashboard.setEmail_entity_Var(result.getString(5));
                dashboard.setNumber_entity_Var(result.getString(6));
                dashboard.setDob_entity_Var(result.getString(7));
                dashboard.setGender_Var(result.getString(8));
                dashboard.setDepartment_entity_Var(result.getString(9));
                dashboard.setAddress_entity_Var(result.getString(10));
                dashboard.setSection_entity_Var(result.getString(11));
                dashboard.setSemester_entity_Var(Integer.parseInt(result.getString(12)));
                dashboard.setFields();
            }
            
        } catch (SQLException ex) {
            System.out.println("Dashboard Screen Error\n"+ex);
            Logger.getLogger(AfterLogInScreenForStudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    

    @FXML
    private void Dashboard_Button(ActionEvent event) 
    {
        StudentDashboardScreen();
    }

    
    private void LoadScreen(String screen)
    {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(screen+".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(AfterLogInScreenForStudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        subscene.setRoot(root);
        
    }
    
    

    @FXML
    private void Courses_Button(ActionEvent event)
    {
        FXMLLoader fm = new FXMLLoader(getClass().getResource("CoursesRegistered.fxml"));
        try {
            Parent root = fm.load();
            CoursesRegisteredController course = fm.getController();
            subscene.setRoot(root);
            course.setStu_id(id);
            course.MakeRegisteredCoursesBox();
        } catch (IOException ex) {
            Logger.getLogger(AfterLogInScreenForStudentController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AfterLogInScreenForStudentController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AfterLogInScreenForStudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Courses_Registration_Button(ActionEvent event) 
    {
        FXMLLoader fm = new FXMLLoader(getClass().getResource("CoursesEnrolledRegistration.fxml"));
        try {
            Parent root = fm.load();
            CoursesEnrolledRegistrationController course = fm.getController();
            subscene.setRoot(root);
            course.setStu_id(id);
            course.MakeCoursesBox();
        } catch (IOException ex) {
            Logger.getLogger(AfterLogInScreenForStudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
