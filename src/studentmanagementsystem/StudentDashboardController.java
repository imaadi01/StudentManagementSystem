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
public class StudentDashboardController implements Initializable {

    @FXML
    private Label name_entity;
    @FXML
    private TextField email_entity;
    @FXML
    private TextField contactNumber_entity;
    @FXML
    private TextField id_entity;
    @FXML
    private TextField fatherName_entity;
    @FXML
    private TextField dateOfBirth_entity;
    @FXML
    private TextField address_entity;
    @FXML
    private TextField department_entity;
    @FXML
    private TextField gender_entity;
    
    private String stu_id;
    
    private String password;
    
    private String full_name;

    private String f_name_entity_Var;

    private String email_entity_Var;

    private String number_entity_Var;

    private String dob_entity_Var;

    private String gender_Var;

    private String department_entity_Var;

    private String address_entity_Var;
    
    private String qualification_entity_Var;
    
    private TextField qualification_entity;

    private Label qualification_label;

    @FXML
    private TextField section_entity;

    @FXML
    private TextField semester_entity;

    private String section_entity_Var;
    
    private int semester_entity_Var;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   

    public void setStu_id(String stu_id) {
        this.stu_id = stu_id;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public void setF_name_entity_Var(String f_name_entity_Var) {
        this.f_name_entity_Var = f_name_entity_Var;
    }

    public void setEmail_entity_Var(String email_entity_Var) {
        this.email_entity_Var = email_entity_Var;
    }

    public void setNumber_entity_Var(String number_entity_Var) {
        this.number_entity_Var = number_entity_Var;
    }

    public void setDob_entity_Var(String dob_entity_Var) {
        this.dob_entity_Var = dob_entity_Var;
    }

    public void setGender_Var(String gender_Var) {
        this.gender_Var = gender_Var;
    }

    public void setDepartment_entity_Var(String department_entity_Var) {
        this.department_entity_Var = department_entity_Var;
    }

    public void setAddress_entity_Var(String address_entity_Var) {
        this.address_entity_Var = address_entity_Var;
    }

    public void setQualification_entity_Var(String qualification_entity_Var) {
        this.qualification_entity_Var = qualification_entity_Var;
    }

    public void setSection_entity_Var(String section_entity_Var) {
        this.section_entity_Var = section_entity_Var;
    }

    public void setSemester_entity_Var(int semester_entity_Var) {
        this.semester_entity_Var = semester_entity_Var;
    }

    
    
    
    
    

    public void setFields()
    {
        System.out.println(stu_id);
        System.out.println(full_name);
    id_entity.setText(String.valueOf(stu_id));
    name_entity.setText(full_name);
    fatherName_entity.setText(f_name_entity_Var);
    email_entity.setText(email_entity_Var);
    contactNumber_entity.setText("0"+String.valueOf(number_entity_Var));
    dateOfBirth_entity.setText(dob_entity_Var);
    gender_entity.setText(gender_Var);
    department_entity.setText(department_entity_Var);
    address_entity.setText(address_entity_Var);
    section_entity.setText(section_entity_Var);
    semester_entity.setText(Integer.toString(semester_entity_Var));
    
    }

    @FXML
    private void edit_StudentINFO(ActionEvent event) 
    {
        email_entity.setEditable(true);
        contactNumber_entity.setEditable(true);
        address_entity.setEditable(true);
        System.out.println("EDIT");
    }

    @FXML
    private void submit_button(ActionEvent event) 
    {
        email_entity_Var = email_entity.getText();
        number_entity_Var = contactNumber_entity.getText();
        address_entity_Var = address_entity.getText();
        System.out.println(id_entity.getText());
        System.out.println(email_entity_Var);
        System.out.println(number_entity_Var);
        System.out.println(address_entity_Var);
        System.out.println("SUBMIT");
        try {
            Connection connection = new Database_Connection().Database_Connectivity();
            String updatequery = "UPDATE `students` SET `EMAIL`='"+ email_entity_Var +"',`CONTACT_NUMBER`='"+ number_entity_Var +"',`ADDRESS`='"+ address_entity_Var +"' WHERE STUDENT_ID='" + id_entity.getText() + "'";
            PreparedStatement statement = connection.prepareStatement(updatequery);
            int data = statement.executeUpdate();
            System.out.println(data);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Successful");
            alert.setContentText("Changed Successfully");
            alert.show();
            connection.close();
            email_entity.setEditable(false);
        contactNumber_entity.setEditable(false);
        address_entity.setEditable(false);
        } catch (Exception e) 
        {
            System.out.println(e);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("FAILED");
            alert.setContentText("ERROR OCCURED\n"+e);
            alert.show();
        }
    }
    
}
