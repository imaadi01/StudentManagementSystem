/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmanagementsystem;

import Database_Connection.Database_Connection;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author aadikumar
 */
public class TeacherRegistrationController implements Initializable {

    @FXML
    private Label incorrectInfo_entity;
    @FXML
    private TextField first_name_entity;
    @FXML
    private TextField last_name_entity;
    @FXML
    private TextField f_name_entity;
    @FXML
    private TextField email_entity;
    @FXML
    private TextField qualification_entity;
    @FXML
    private TextField number_entity;
    @FXML
    private DatePicker dob_entity;
    @FXML
    private ChoiceBox gender_entity;
    @FXML
    private ChoiceBox department_entity;
    @FXML
    private TextArea address_entity;

    
    private int teacher_id = 2000;
    
    private String first_name_entity_Var;
    
    private String last_name_entity_Var;
    
    private String f_name_entity_Var;
    
    private String email_entity_Var;
    
    private String number_entity_Var;
    
    private String dob_entity_Var;
    
    private String gender_Var;
    
    private String department_entity_Var;
    
    private String address_entity_Var;
    
    private String qualification_entity_Var;
    
    private String joined_entity_Var;
    
    private boolean firstname;
    
    private boolean lastname;
    
    private boolean fullname;
    
    private boolean f_name;
    
    private boolean dep_name;
    
    private String dateString;
    
    private ObservableList<String> genders;
    private ObservableList<String> departments;

    public TeacherRegistrationController() {
        this.genders = FXCollections.observableArrayList("Male","Female");
        this.departments = FXCollections.observableArrayList("BSCS","BSAF","BBA");
    }
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         gender_entity.setValue("Male");
        gender_entity.setItems(genders);
        department_entity.setValue("BSCS");
        department_entity.setItems(departments);
    }

    public static String Date_Input()
    {
    DateFormat df = new SimpleDateFormat("MMM dd,yyyy");
       Date dateobj = new Date();
       String s=df.format(dateobj);
       return s;
    }
    
    public String getNumber(String num)
    {
    if(num.length()==10)
        return this.number_entity_Var=num;
    else
    {
       incorrectInfo_entity.setText("Contatct number should be of 10 Digits");
    return this.number_entity_Var="";
    }
    }

    @FXML
    private void Register_entity(ActionEvent event) 
    {
        first_name_entity_Var = first_name_entity.getText().toUpperCase();
        last_name_entity_Var = last_name_entity.getText().toUpperCase();
        f_name_entity_Var = f_name_entity.getText().toUpperCase();
        email_entity_Var = email_entity.getText().toUpperCase();
        number_entity_Var = number_entity.getText();
        dob_entity_Var = StudentRegistrationController.getDate(dob_entity,incorrectInfo_entity);
        department_entity_Var = department_entity.getValue().toString().toUpperCase();
        address_entity_Var = address_entity.getText().toUpperCase();
        qualification_entity_Var = qualification_entity.getText().toUpperCase();
        joined_entity_Var = Date_Input();
        gender_Var = gender_entity.getValue().toString().toUpperCase();

        firstname = StudentRegistrationController.Name_Input(incorrectInfo_entity,first_name_entity_Var,"First Name");
        System.out.println(firstname);
        lastname = StudentRegistrationController.Name_Input(incorrectInfo_entity,last_name_entity_Var,"Last Name");
        System.out.println(lastname);
        f_name = StudentRegistrationController.Name_Input(incorrectInfo_entity, f_name_entity_Var,"Father's Name");
        System.out.println(f_name);
        dep_name = StudentRegistrationController.Name_Input(incorrectInfo_entity, department_entity_Var,"Department");
        System.out.println(dep_name);
        getNumber(number_entity.getText());
        
        fullname = firstname && lastname;
        if (fullname && f_name && dep_name && !dob_entity_Var.equals("") && !qualification_entity_Var.equals("") && !number_entity_Var.equals(""))
        {
            try {
                Database_Connection database = new Database_Connection();
                Connection connection = database.Database_Connectivity();
                String fetch_query = "SELECT * from teachers";
                Statement fetch_statement = connection.createStatement();
                ResultSet result = fetch_statement.executeQuery(fetch_query);
                while(result.next())
                {
                teacher_id=Integer.parseInt(result.getString(1));
                }
                String insert_stu = "INSERT INTO teachers (TEACHER_ID,FIRST_NAME,LAST_NAME,FATHER_NAME,EMAIL,CONTACT_NUMBER,DATE_OF_BIRTH,GENDER,DEPARTMENT,ADDRESS,QUALIFICATION,JOINED) VALUES ('" + (++teacher_id) + "','" + first_name_entity_Var + "','" + last_name_entity_Var + "','" + f_name_entity_Var + "','" + email_entity_Var + "','" + number_entity_Var + "','" + dob_entity_Var + "','" + gender_Var + "','" + department_entity_Var + "','" + address_entity_Var + "', '" + qualification_entity_Var +"', '" + joined_entity_Var +"')";
                Statement statement = connection.createStatement();
                statement.executeUpdate(insert_stu);
                
                String password = AfterLogInScreenForStudentController.RandomPasswordGenerator();
                
                String insert_user = "INSERT INTO users (ID,PASSWORD,USER) VALUES ('" + (teacher_id) + "','" + password + "', '"+ "TEACHER" +"')";
                Statement statement_user = connection.createStatement();
                statement_user.executeUpdate(insert_user);
                if (statement_user != null) {
                    connection.close();
                }
                
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Success");
                alert.setContentText("Your ID and Password\n ID :"+teacher_id+"\nPassword: "+password);
                alert.show();

            } catch (SQLException ex) {
                System.out.println(ex);
                Logger.getLogger(TeacherRegistrationController.class.getName()).log(Level.SEVERE, null, ex);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Database Failed");
                alert.setTitle("Info Error");
                alert.show();
            }
        }
    
    }
    
}
