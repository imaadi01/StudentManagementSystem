/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmanagementsystem;

import Database_Connection.Database_Connection;
import com.mysql.cj.protocol.Resultset;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author aadikumar
 */
public class SettingScreenController implements Initializable {

    @FXML
    private TextField currentPassword_entity;
    @FXML
    private TextField newPassword_entity;
    @FXML
    private TextField confirmnewPassword_entity;

    
    private int id_entity_Var;
    
    private String currentPassword="";
    
    private String newPassword="";
    
    private String confirmNewPassword="";
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void setId_entity_Var(int id_entity_Var) {
        this.id_entity_Var = id_entity_Var;
    }

    
    
    
    @FXML
    private void changePassword(ActionEvent event) 
    {
        currentPassword = currentPassword_entity.getText();
        newPassword = newPassword_entity.getText();
        confirmNewPassword = confirmnewPassword_entity.getText();
        
        System.out.println(currentPassword+":");
        
        
        if(currentPassword.equals(CheckPassword()) && newPassword.equals(confirmNewPassword))
        {
        try {
            Connection connection = new Database_Connection().Database_Connectivity();
            System.out.println(newPassword+":new");
        System.out.println(confirmNewPassword+":confirm");
            String fetchquery = "UPDATE users SET PASSWORD='" + newPassword + "' where ID=' " + id_entity_Var + " ' ";
            PreparedStatement statement = connection.prepareStatement(fetchquery);
            int data = statement.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Successful");
            alert.setContentText("Password Changed Successfully");
            alert.show();
        }
        catch (SQLException ex)
        {
            System.out.println(ex);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Successful");
            alert.setContentText("Operation Failed");
            alert.show();
        }
        }
        
    }
    
    public String CheckPassword()
    {
        ResultSet data = null;
        String password = null;
        try {
            Connection connection = new Database_Connection().Database_Connectivity();
            String fetchquery = "select PASSWORD from users where ID=' " + id_entity_Var + " ' ";
            Statement statement = connection.createStatement();
            data = statement.executeQuery(fetchquery);
            while(data.next())
                password = data.getString(1);
            data.close();
        } catch (SQLException ex) {
            Logger.getLogger(SettingScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return password;
    }
    
}
