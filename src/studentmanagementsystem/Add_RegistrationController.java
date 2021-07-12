/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmanagementsystem;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.SubScene;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author aadikumar
 */
public class Add_RegistrationController implements Initializable {

    @FXML
    private SubScene subscene;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
    
    private void LoadScene(String scene)
    {
    Parent root = null;
    try
    {
    root = FXMLLoader.load(getClass().getResource(scene+".fxml"));
    }
    catch(IOException ex)
    {
        System.out.println(ex);
    Logger.getLogger(Add_RegistrationController.class.getName()).log(Level.SEVERE, null, ex);
    }
    subscene.setRoot(root);
    }

    @FXML
    private void addStudent_entity(ActionEvent event) {
        LoadScene("StudentRegistration");
    }

    @FXML
    private void addTeacher_entity(ActionEvent event) {
        LoadScene("TeacherRegistration");
    }

    @FXML
    private void addCourse_entity(ActionEvent event) {
        LoadScene("CourseRegistration");
    }

//    private void addStudent_entity(MouseEvent event) 
//    {
////        LoadScene("StudentRegistration");
//        FXMLLoader fm = new FXMLLoader(getClass().getResource("StudentRegistration.fxml"));
//    try
//    {
//    Parent root = fm.load();
//    subscene.setRoot(root);
//    }
//    catch(IOException ex)
//    {
//        System.out.println(ex);
//    Logger.getLogger(Add_RegistrationController.class.getName()).log(Level.SEVERE, null, ex);
//    }
//    
//    }
//
//    private void addTeacher_entity(MouseEvent event) 
//    {
////        LoadScene("TeacherRegistration");
//        Parent root = null;
//    try
//    {
//    root = FXMLLoader.load(getClass().getResource("TeacherRegistration.fxml"));
//    subscene.setRoot(root);
//    }
//    catch(IOException ex)
//    {
//        System.out.println(ex);
//    Logger.getLogger(Add_RegistrationController.class.getName()).log(Level.SEVERE, null, ex);
//    }
//    
//        
//    }
//
//    private void addCourse_entity(MouseEvent event) 
//    {
////        LoadScene("CourseRegistration");
//         Parent root = null;
//    try
//    {
//    root = FXMLLoader.load(getClass().getResource("CourseRegistration.fxml"));
//    subscene.setRoot(root);
//    }
//    catch(IOException ex)
//    {
//        System.out.println(ex);
//    Logger.getLogger(Add_RegistrationController.class.getName()).log(Level.SEVERE, null, ex);
//    }
//    
//    }
    
}
