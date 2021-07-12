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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
public class StudentRegistrationController implements Initializable {

    @FXML
    private TextField first_name_entity;
    @FXML
    private TextField last_name_entity;
    @FXML
    private TextField f_name_entity;
    @FXML
    private TextField email_entity;
    @FXML
    private TextField number_entity;
    @FXML
    private DatePicker dob_entity;
    @FXML
    private ChoiceBox department_entity;
    @FXML
    private TextArea address_entity;
    @FXML
    private Label incorrectInfo_entity;

    private int stu_id = 1001;

    private String password;

    private String first_name_entity_Var;

    private String last_name_entity_Var;

    private String full_name;

    private String f_name_entity_Var;

    private String email_entity_Var;

    private String number_entity_Var;

    private String dob_entity_Var;

    private String gender_Var;

    private String department_entity_Var;

    private String address_entity_Var;

    private int semester_entity_Var;

    private String section_entity_Var;

    private boolean firstname;
    private boolean lastname;
    private boolean fullname;
    private boolean f_name;
    private boolean dep_name;
    private String dateString;
    @FXML
    private ChoiceBox section_entity;
    @FXML
    private ChoiceBox semester_entity;
    @FXML
    private ChoiceBox gender_entity;

    private ObservableList<String> sections = FXCollections.observableArrayList("A", "B", "C");
    private ObservableList<String> semesters = FXCollections.observableArrayList("1", "2", "3", "4", "5", "6", "7", "8");
    private ObservableList<String> genders = FXCollections.observableArrayList("Male", "Female");
    private ObservableList<String> departments = FXCollections.observableArrayList("BSCS", "BSAF", "BBA");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        section_entity.setValue("A");
        section_entity.setItems(sections);
        semester_entity.setValue("1");
        semester_entity.setItems(semesters);
        gender_entity.setValue("Male");
        gender_entity.setItems(genders);
        department_entity.setValue("BSCS");
        department_entity.setItems(departments);
    }

    public static boolean Name_Input(Label label, String name, String field) {
        name = name.toUpperCase();
        char[] c = name.toCharArray();
        boolean name_validty = true;
        for (int i = 0; i < name.length(); i++) {
            if (c[i] < 65 || c[i] > 90) {
                if (c[i] == 32) {
                    continue;
                } else {
                    name_validty = false;
                    label.setText(c[i] + " Character is not allowed in the " + field + " category");
                }
            }
        }
        if (name_validty == true) {
            return true;
        } else {
            return false;
        }
    }

    public static String getDate(DatePicker datepicker, Label label) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd, MMM, yyyy");
        LocalDate date = datepicker.getValue();
        if (date != null) {
//        dateString = formatter.format(date);
//        System.out.println(formatter.format(date));
            return formatter.format(date);
        } else {
            label.setText("Date Empty");
            return "";
        }
    }
    
    public String getNumber(String num)
    {
    if(num.length()==10)
        return this.number_entity_Var=num;
    else
    {
        incorrectInfo_entity.setText("Number should be of 11 Digits");
        return number_entity_Var="";
    }
    }

    @FXML
    private void Register_entity(ActionEvent event) {

        first_name_entity_Var = first_name_entity.getText().toUpperCase();
        last_name_entity_Var = last_name_entity.getText().toUpperCase();
        f_name_entity_Var = f_name_entity.getText().toUpperCase();
        email_entity_Var = email_entity.getText();
        number_entity_Var = getNumber(number_entity.getText());
        dob_entity_Var = getDate(dob_entity, incorrectInfo_entity);
        department_entity_Var = department_entity.getValue().toString().toUpperCase();
        address_entity_Var = address_entity.getText();
        gender_Var = gender_entity.getValue().toString().toUpperCase();
        section_entity_Var = section_entity.getValue().toString().toUpperCase();
        semester_entity_Var = Integer.parseInt(semester_entity.getValue().toString());

        firstname = Name_Input(incorrectInfo_entity, first_name_entity_Var, "First Name");

        lastname = Name_Input(incorrectInfo_entity, last_name_entity_Var, "Last Name");

        f_name = Name_Input(incorrectInfo_entity, f_name_entity_Var, "Father's Name");

        dep_name = Name_Input(incorrectInfo_entity, department_entity_Var, "Department");

        fullname = firstname && lastname;

        if (fullname && f_name && dep_name && !dob_entity_Var.equals("") && !number_entity_Var.equals("")) {
            full_name = first_name_entity_Var + " " + last_name_entity_Var;
            try {
                Database_Connection database = new Database_Connection();
                Connection connection = database.Database_Connectivity();
                String fetch_query = "SELECT * from students";
                Statement fetch_statement = connection.createStatement();
                ResultSet result = fetch_statement.executeQuery(fetch_query);
                while (result.next()) {
                    stu_id = Integer.parseInt(result.getString(1));
                }
                String insert_stu = "INSERT INTO students (STUDENT_ID,FIRST_NAME,LAST_NAME,FATHER_NAME,EMAIL,CONTACT_NUMBER,DATE_OF_BIRTH,GENDER,DEPARTMENT,ADDRESS,SECTION,SEMESTER,JOINED_DATE,LEFT_DATE) VALUES ('" + (++stu_id) + "','" + first_name_entity_Var + "','" + last_name_entity_Var + "','" + f_name_entity_Var + "','" + email_entity_Var + "','" + number_entity_Var + "','" + dob_entity_Var + "','" + gender_Var + "','" + department_entity_Var + "','" + address_entity_Var + "', '" + section_entity_Var + "', '" + semester_entity_Var + "','" + TeacherRegistrationController.Date_Input() + "',' ')";
                Statement statement = connection.createStatement();
                statement.executeUpdate(insert_stu);

                password = AfterLogInScreenForStudentController.RandomPasswordGenerator();

                String insert_user = "INSERT INTO users (ID,PASSWORD,USER) VALUES ('" + (stu_id) + "','" + password + "', '" + "STUDENT" + "')";
                Statement statement_user = connection.createStatement();
                statement_user.executeUpdate(insert_user);
                if (statement_user != null) {
                    connection.close();
                }

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Success");
                alert.setContentText("Your ID and Password\n ID :" + stu_id + "\nPassword: " + password);
                alert.show();

            } catch (SQLException ex) {
                System.out.println(ex);
                Logger.getLogger(StudentRegistrationController.class.getName()).log(Level.SEVERE, null, ex);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Database Failed");
                alert.setTitle("Info Error");
                alert.show();
            }
        }
    }

}
