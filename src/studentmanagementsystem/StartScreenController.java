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
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author aadikumar
 */
public class StartScreenController implements Initializable {

    @FXML
    private TextField registration_id;
    @FXML
    private PasswordField registration_password;

    private int id;
    private String password;
    private String user;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public static void AlertBoxError(String title, String context) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(context);
        alert.show();
    }

    public static void AlertBoxConfirmation(String title, String context) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setContentText(context);
        alert.show();
    }

    @FXML
    private void registration_login(ActionEvent event) {
        user = "";
        password="";
        id = Integer.parseInt(registration_id.getText());
        password = registration_password.getText();
        System.out.println(password + ":PASSWORD");
        Database_Connection database = new Database_Connection();
        Connection connection = database.Database_Connectivity();
        String query = "SELECT * FROM users WHERE ID='" + id + "'";
        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet resultset = statement.executeQuery(query);
            while (resultset.next()) {
                password = resultset.getString("PASSWORD");
                user = resultset.getString("USER");
            }
        } catch (SQLException ex) {
            Logger.getLogger(StartScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (registration_password.getText().equals(password)) {
            if (user.equals("STUDENT")) {
                FXMLLoader fm = new FXMLLoader(getClass().getResource("AfterLogInScreenForStudent.fxml"));
                Parent root = null;
                try {
                    root = fm.load();
                } catch (IOException ex) {
                    Logger.getLogger(StartScreenController.class.getName()).log(Level.SEVERE, null, ex);
                }
                AfterLogInScreenForStudentController student = fm.getController();
                Stage s1 = new Stage();
                Scene scene1 = new Scene(root);
                s1.setScene(scene1);
                s1.setTitle("Student Management System By Aadesh Kumar");
                s1.show();
                student.setId(id);
                student.setUser(user);
                student.StudentDashboardScreen();
                registration_id.getScene().getWindow().hide();
            } else if (user.equals("TEACHER")) {
                FXMLLoader fm = new FXMLLoader(getClass().getResource("AfterLogInScreenForTeacher.fxml"));
                Parent root = null;
                try {
                    root = fm.load();
                } catch (IOException ex) {
                    Logger.getLogger(StartScreenController.class.getName()).log(Level.SEVERE, null, ex);
                }
                AfterLogInScreenForTeacherController teacher = fm.getController();
                Stage s1 = new Stage();
                Scene scene1 = new Scene(root);
                s1.setScene(scene1);
                s1.setTitle("Student Management System By Aadesh Kumar and Pawan");
                s1.show();
                teacher.setId(id);
                teacher.setUser(user);
                teacher.TeacherDashboardScreen();
                registration_id.getScene().getWindow().hide();
            } else if (user.equals("ADMIN")) {
                FXMLLoader fm = new FXMLLoader(getClass().getResource("AdminScreen.fxml"));
                Parent root = null;
                try {
                    root = fm.load();
                } catch (IOException ex) {
                    Logger.getLogger(StartScreenController.class.getName()).log(Level.SEVERE, null, ex);
                }
                AdminScreenController admin = fm.getController();
                Stage s1 = new Stage();
                Scene scene1 = new Scene(root);
                s1.setScene(scene1);
                s1.setTitle("STUDENT MANAGEMENT SYSTEM By Aadesh Kumar And Pawan");
                s1.show();
                admin.setId(id);
                admin.AdminDashboardScreen();
                registration_id.getScene().getWindow().hide();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Incorrect Info");
            alert.setContentText("Incorrect Id or Password");
            alert.show();
        }
    }

}
