/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmanagementsystem;

import Database_Connection.Database_Connection;
import java.awt.BorderLayout;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author aadikumar
 */
public class StudentSearchController implements Initializable {

    @FXML
    private TableView<StudentData> student_table;
    @FXML
    private TableColumn<StudentData, Integer> col_id;
    @FXML
    private TableColumn<StudentData, String> col_father;
    @FXML
    private TableColumn<StudentData, String> col_email;
    @FXML
    private TableColumn<StudentData, String> col_number;
    @FXML
    private TableColumn<StudentData, String> col_dob;
    @FXML
    private TableColumn<StudentData, String> col_gender;
    @FXML
    private TableColumn<StudentData, String> col_department;
    @FXML
    private TableColumn<StudentData, String> col_address;
    @FXML
    private TableColumn<StudentData, String> col_section;
    @FXML
    private TableColumn<StudentData, Integer> col_semester;
    @FXML
    private TableColumn<StudentData, String> col_joined;
    @FXML
    private TableColumn<StudentData, String> col_left;
    @FXML
    private ComboBox search_by_combobox;
    @FXML
    private TextField search_entity;

    private int login_id;
    
    public void setLogin_id(int login_id) {
        this.login_id = login_id;
    }
    
    
    ObservableList<StudentData> students = FXCollections.observableArrayList();
    ObservableList<String> searchoptions = FXCollections.observableArrayList("STUDENT_ID","FIRST_NAME","LAST_NAME","FATHER_NAME","EMAIL","CONTACT_NUMBER");
    
    @FXML
    private TableColumn<StudentData, String> col_firstname;
    @FXML
    private TableColumn<StudentData, String> col_lastname;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        search_by_combobox.setItems(searchoptions);
        }    
    
    public void Read_Data() throws SQLException {
        Connection cn = new Database_Connection().Database_Connectivity();
        String data="SELECT * FROM students";
        Statement statement = cn.createStatement();
        ResultSet resultset= statement.executeQuery(data);
        while(resultset.next())
        {
        students.add(new StudentData(
                Integer.parseInt(resultset.getString("STUDENT_ID")),
                resultset.getString("FIRST_NAME"),
                resultset.getString("LAST_NAME"),
                resultset.getString("FATHER_NAME"),
                resultset.getString("EMAIL"),
                resultset.getString("CONTACT_NUMBER"),
                resultset.getString("DATE_OF_BIRTH"),
                resultset.getString("GENDER"),
                resultset.getString("DEPARTMENT"),
                resultset.getString("ADDRESS"),
                resultset.getString("SECTION"),
                Integer.parseInt(resultset.getString("SEMESTER")),
                resultset.getString("JOINED_DATE"),
                resultset.getString("LEFT_DATE")
        ));
        }
        col_id.setCellValueFactory(new PropertyValueFactory<>("stu_id"));
        col_firstname.setCellValueFactory(new PropertyValueFactory<>("first_name"));
        col_lastname.setCellValueFactory(new PropertyValueFactory<>("last_name"));
        col_father.setCellValueFactory(new PropertyValueFactory<>("father_name"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_number.setCellValueFactory(new PropertyValueFactory<>("number"));
        col_dob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        col_department.setCellValueFactory(new PropertyValueFactory<>("department"));
        col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        col_section.setCellValueFactory(new PropertyValueFactory<>("section"));
        col_semester.setCellValueFactory(new PropertyValueFactory<>("semester"));
        col_joined.setCellValueFactory(new PropertyValueFactory<>("joined"));
        col_left.setCellValueFactory(new PropertyValueFactory<>("left"));
        student_table.setItems(students);
        
    }

    @FXML
    private void SearchButton(ActionEvent event) 
    {
        students.clear();
        Connection connectoin = new Database_Connection().Database_Connectivity();
        if(!search_entity.getText().equals(""))
        {
        String searchQuery = "SELECT * FROM students WHERE "+search_by_combobox.getValue().toString()+"= '"+search_entity.getText()+"'";
        try {
            ResultSet resultset = connectoin.createStatement().executeQuery(searchQuery);
            while(resultset.next())
        {
        students.add(new StudentData(
                Integer.parseInt(resultset.getString("STUDENT_ID")),
                resultset.getString("FIRST_NAME"),
                resultset.getString("LAST_NAME"),
                resultset.getString("FATHER_NAME"),
                resultset.getString("EMAIL"),
                resultset.getString("CONTACT_NUMBER"),
                resultset.getString("DATE_OF_BIRTH"),
                resultset.getString("GENDER"),
                resultset.getString("DEPARTMENT"),
                resultset.getString("ADDRESS"),
                resultset.getString("SECTION"),
                Integer.parseInt(resultset.getString("SEMESTER")),
                resultset.getString("JOINED_DATE"),
                resultset.getString("LEFT_DATE")
        ));
        }
            col_id.setCellValueFactory(new PropertyValueFactory<>("stu_id"));
        col_firstname.setCellValueFactory(new PropertyValueFactory<>("first_name"));
        col_lastname.setCellValueFactory(new PropertyValueFactory<>("last_name"));
        col_father.setCellValueFactory(new PropertyValueFactory<>("father_name"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_number.setCellValueFactory(new PropertyValueFactory<>("number"));
        col_dob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        col_department.setCellValueFactory(new PropertyValueFactory<>("department"));
        col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        col_section.setCellValueFactory(new PropertyValueFactory<>("section"));
        col_semester.setCellValueFactory(new PropertyValueFactory<>("semester"));
        col_joined.setCellValueFactory(new PropertyValueFactory<>("joined"));
        col_left.setCellValueFactory(new PropertyValueFactory<>("left"));
        student_table.setItems(students);
        } 
        catch (SQLException ex) {
            Logger.getLogger(StudentSearchController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        else
        {
            try {
                Read_Data();
            } catch (SQLException ex) {
                Logger.getLogger(StudentSearchController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
    }
    }
    

