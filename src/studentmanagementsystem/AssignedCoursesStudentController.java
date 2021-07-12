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
public class AssignedCoursesStudentController implements Initializable {

    @FXML
    private TableView<StudentData> student_table;
    @FXML
    private TableColumn<StudentData, Integer> col_id;
    @FXML
    private TableColumn<StudentData, String> col_firstname;
    @FXML
    private TableColumn<StudentData, String> col_lastname;
    @FXML
    private TableColumn<StudentData, String> col_father;
    @FXML
    private TableColumn<StudentData, String> col_email;
    @FXML
    private TableColumn<StudentData, String> col_gender;
    private ComboBox search_by_combobox;
    private TextField search_entity;

    
    ObservableList<StudentData> students = FXCollections.observableArrayList();
    ObservableList<String> searchoptions = FXCollections.observableArrayList("STUDENT_ID","FIRST_NAME","LAST_NAME","FATHER_NAME","EMAIL","CONTACT_NUMBER");
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        search_by_combobox.setItems(searchoptions);
    }   
    
    public void Students(int course_id,String teacher,String section,int semester)
    {
        
        Connection cn = new Database_Connection().Database_Connectivity();
        String data="SELECT STUDENT_ID,FIRST_NAME,LAST_NAME,FATHER_NAME,EMAIL,GENDER FROM students,courses_enrolled WHERE students.STUDENT_ID=courses_enrolled.STUDENT AND COURSE_ID='"+course_id+"' AND courses_enrolled.SEMESTER='"+semester+"' AND courses_enrolled.SECTION='"+section+"' ";
        try {
           ResultSet resultset = cn.createStatement().executeQuery(data);
            while(resultset.next())
        {
        students.add(new StudentData(
                Integer.parseInt(resultset.getString("STUDENT_ID")),
                resultset.getString("FIRST_NAME"),
                resultset.getString("LAST_NAME"),
                resultset.getString("FATHER_NAME"),
                resultset.getString("EMAIL"),
                resultset.getString("GENDER")
        ));
        }
        col_id.setCellValueFactory(new PropertyValueFactory<>("stu_id"));
        col_firstname.setCellValueFactory(new PropertyValueFactory<>("first_name"));
        col_lastname.setCellValueFactory(new PropertyValueFactory<>("last_name"));
        col_father.setCellValueFactory(new PropertyValueFactory<>("father_name"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        student_table.setItems(students);
        } catch (SQLException ex) {
            Logger.getLogger(AssignedCoursesStudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
    }

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
        col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        student_table.setItems(students);
        } 
        catch (SQLException ex) {
            Logger.getLogger(StudentSearchController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        else
        {
            
        }
    }
    
}
