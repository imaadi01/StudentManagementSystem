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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author aadikumar
 */
public class AdminScreenController implements Initializable {

    @FXML
    private BorderPane borderpane;
    @FXML
    private SubScene subscene;
    @FXML
    private Label profession_entity;
    
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
    
    private void LoadScreen(String screen)
    {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(screen+".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(AdminScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        subscene.setRoot(root);
        
    }
    
    public void AdminDashboardScreen()
    {
    FXMLLoader fm = new FXMLLoader(getClass().getResource("AdminDashboard.fxml"));
        try {
            Parent root = fm.load();
            borderpane.setCenter(root);
        } catch (IOException ex) {
            Logger.getLogger(AfterLogInScreenForStudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
            AdminDashboardController dashboard =fm.getController();
        try {
        Database_Connection database = new Database_Connection();
        Connection connection = database.Database_Connectivity();
        String fetch_query = null;
        Statement statement1 = null;
     
         fetch_query = "SELECT * FROM admins WHERE ADMIN_ID= '" + id + "'";           
         Statement statement = connection.createStatement();
            ResultSet result =statement.executeQuery(fetch_query);
            while (result.next()) 
            {                
                dashboard.setId_entity_Var(Integer.parseInt(result.getString("ADMIN_ID")));
                dashboard.setName_entity_Var(result.getString("FIRST_NAME")+" "+result.getString("LAST_NAME"));
                dashboard.setFatherName_entity_Var(result.getString("FATHER_NAME"));
                dashboard.setEmail_entity_Var(result.getString("EMAIL"));
                dashboard.setContactNumber_entity_Var(result.getString("CONTACT_NUMBER"));
                dashboard.setDateOfBirth_entity_Var(result.getString("DATE_OF_BIRTH"));
                dashboard.setGender_entity_Var(result.getString("GENDER"));
                dashboard.setAddress_entity_Var(result.getString("ADDRESS"));
                dashboard.setJoined_entity_Var(result.getString("JOINED"));
                dashboard.setFields();
            }
            
        } catch (SQLException ex) {
            System.out.println("Dashboard Screen Error\n"+ex);
            Logger.getLogger(AfterLogInScreenForStudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    @FXML
    private void Dashboard_Button(MouseEvent event) 
    {
        AdminDashboardScreen();
    }


    @FXML
    private void Create_Button(MouseEvent event) 
    {
        try {
            FXMLLoader fm = new FXMLLoader(getClass().getResource("Add_Registration.fxml"));
            Parent root = fm.load();
            borderpane.setCenter(root);
        } catch (IOException ex) {
            Logger.getLogger(AdminScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            Logger.getLogger(AdminScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Close_program(MouseEvent event) 
    {
        Stage stage = (Stage) borderpane.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void Delete_Button(MouseEvent event) 
    {
       try {
            FXMLLoader fm = new FXMLLoader(getClass().getResource("Delete.fxml"));
            Parent root = fm.load();
            borderpane.setCenter(root);
        } catch (IOException ex) {
            Logger.getLogger(AdminScreenController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @FXML
    private void Courses_Button(MouseEvent event) 
    {
        try {
            FXMLLoader fm = new FXMLLoader(getClass().getResource("AssignCourses.fxml"));
            Parent root = fm.load();
            borderpane.setCenter(root);
        } catch (IOException ex) {
            Logger.getLogger(AdminScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Setting_Button(MouseEvent event) 
    {
        try {
            FXMLLoader fm = new FXMLLoader(getClass().getResource("SettingScreen.fxml"));
            Parent root = fm.load();
            SettingScreenController setting = fm.getController();
            setting.setId_entity_Var(id);
            borderpane.setCenter(root);
        } catch (IOException ex) {
            Logger.getLogger(AdminScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Search_Button(MouseEvent event) 
    {
       try {
            FXMLLoader fm = new FXMLLoader(getClass().getResource("SeachScreen.fxml"));
            Parent root = fm.load();
            borderpane.setCenter(root);
            
        } catch (IOException ex) {
            Logger.getLogger(AdminScreenController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
}
