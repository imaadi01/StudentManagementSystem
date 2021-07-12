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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author aadikumar
 */
public class TeacherDashboardController implements Initializable {

    
    @FXML
    private TextField email_entity;
    @FXML
    private TextField department_entity;
    @FXML
    private TextField contactNumber_entity;
    @FXML
    private TextField fatherName_entity;
    @FXML
    private TextField dateOfBirth_entity;
    @FXML
    private TextField gender_entity;
    @FXML
    private TextField address_entity;
    @FXML
    private TextField id_entity;
    @FXML
    private TextField Joined_entity;
    @FXML
    private Label name_entity;
    @FXML
    private TextField qualification_entity;
    
    private String email_entity_Var;
    
    private String department_entity_Var;
    
    private String contactNumber_entity_Var;
    
    private String fatherName_entity_Var;
    
    private String dateOfBirth_entity_Var;
    
    private String gender_entity_Var;
    
    private String address_entity_Var;
    
    private int id_entity_Var;
    
    private String Joined_entity_Var;
    
    private String name_entity_Var;
    
    private String qualification_entity_Var;

    public void setEmail_entity_Var(String email_entity_Var) {
        this.email_entity_Var = email_entity_Var;
    }

    public void setDepartment_entity_Var(String department_entity_Var) {
        this.department_entity_Var = department_entity_Var;
    }

    public void setContactNumber_entity_Var(String contactNumber_entity_Var) {
        this.contactNumber_entity_Var = contactNumber_entity_Var;
    }

    public void setFatherName_entity_Var(String fatherName_entity_Var) {
        this.fatherName_entity_Var = fatherName_entity_Var;
    }

    public void setDateOfBirth_entity_Var(String dateOfBirth_entity_Var) {
        this.dateOfBirth_entity_Var = dateOfBirth_entity_Var;
    }

    public void setGender_entity_Var(String gender_entity_Var) {
        this.gender_entity_Var = gender_entity_Var;
    }

    public void setAddress_entity_Var(String address_entity_Var) {
        this.address_entity_Var = address_entity_Var;
    }

    public void setId_entity_Var(int id_entity_Var) {
        this.id_entity_Var = id_entity_Var;
    }

    public void setJoined_entity_Var(String Joined_entity_Var) {
        this.Joined_entity_Var = Joined_entity_Var;
    }

    public void setName_entity_Var(String name_entity_Var) {
        this.name_entity_Var = name_entity_Var;
    }

    public void setQualification_entity_Var(String qualification_entity_Var) {
        this.qualification_entity_Var = qualification_entity_Var;
    }
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    
    public void setFields()
    {
    id_entity.setText(Integer.toString(id_entity_Var));
    name_entity.setText(name_entity_Var);
    fatherName_entity.setText(fatherName_entity_Var);
    qualification_entity.setText(qualification_entity_Var);
    contactNumber_entity.setText("0"+contactNumber_entity_Var);
    Joined_entity.setText(Joined_entity_Var);
    department_entity.setText(department_entity_Var);
    dateOfBirth_entity.setText(dateOfBirth_entity_Var);
    gender_entity.setText(gender_entity_Var);
    address_entity.setText(address_entity_Var);
    email_entity.setText(email_entity_Var);
    
    }

    @FXML
    private void edit_button(ActionEvent event) 
    {
        email_entity.setEditable(true);
        contactNumber_entity.setEditable(true);
        address_entity.setEditable(true);
    }

    @FXML
    private void submit_button(ActionEvent event) 
    {
        try
        {
            Connection connection = new Database_Connection().Database_Connectivity();
            String updateQuery = "UPDATE teachers SET EMAIL='"+email_entity.getText()+"',CONTACT_NUMBER='"+Integer.valueOf(contactNumber_entity.getText())+"',ADDRESS='"+address_entity.getText()+"' WHERE TEACHER_ID='"+id_entity_Var+"'";
            PreparedStatement statement = connection.prepareStatement(updateQuery);
            int updated = statement.executeUpdate();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Success");
        alert.setContentText("Update Successfully");
        alert.show();
        }
        catch(Exception e)
        {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Failed");
        alert.setContentText(""+e);
        alert.show();
        }
        email_entity.setEditable(false);
        contactNumber_entity.setEditable(false);
        address_entity.setEditable(false);
    }
    
}
