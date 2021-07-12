/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmanagementsystem;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author aadikumar
 */
public class SeachScreenController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void StudentSearch(MouseEvent event) 
    {
        try {
            FXMLLoader fm = new FXMLLoader(getClass().getResource("StudentSearch.fxml"));
            Parent root = fm.load();
            StudentSearchController student = fm.getController();
            Stage s1 = new Stage();
            Scene scene1 = new Scene(root);
            s1.setScene(scene1);
            s1.setTitle("Student Management System By Aadesh Kumar");
            s1.show();
           try {
               student.Read_Data();
           } catch (SQLException ex) {
               Logger.getLogger(AdminScreenController.class.getName()).log(Level.SEVERE, null, ex);
           }
            
        } catch (IOException ex) {
            Logger.getLogger(AdminScreenController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @FXML
    private void TeacherSearch(MouseEvent event) 
    {
        try {
            FXMLLoader fm = new FXMLLoader(getClass().getResource("TeacherSearch.fxml"));
            Parent root = fm.load();
            TeacherSearchController teacher = fm.getController();
            Stage s1 = new Stage();
            Scene scene1 = new Scene(root);
            s1.setScene(scene1);
            s1.setTitle("Student Management System By Aadesh Kumar");
            s1.show();
           try {
               teacher.Read_Data();
           } catch (SQLException ex) {
               Logger.getLogger(AdminScreenController.class.getName()).log(Level.SEVERE, null, ex);
           }
            
        } catch (IOException ex) {
            Logger.getLogger(AdminScreenController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
}
