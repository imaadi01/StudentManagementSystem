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
public class TeacherSearchController implements Initializable {

    @FXML
    private TableView<TeacherData> teacher_table;
    @FXML
    private TableColumn<TeacherData, Integer> col_id;
    @FXML
    private TableColumn<TeacherData, String> col_firstname;
    @FXML
    private TableColumn<TeacherData, String> col_lastname;
    @FXML
    private TableColumn<TeacherData, String> col_father;
    @FXML
    private TableColumn<TeacherData, String> col_email;
    @FXML
    private TableColumn<TeacherData, String> col_number;
    @FXML
    private TableColumn<TeacherData, String> col_dob;
    @FXML
    private TableColumn<TeacherData, String> col_gender;
    @FXML
    private TableColumn<TeacherData, String> col_department;
    @FXML
    private TableColumn<TeacherData, String> col_address;
    @FXML
    private TableColumn<TeacherData, String> col_qualification;
    @FXML
    private TableColumn<TeacherData, String> col_joined;
    @FXML
    private TableColumn<TeacherData, String> col_left;
    @FXML
    private ComboBox search_by_combobox;
    @FXML
    private TextField search_entity;

    
    private int login_id;
    
    public void setLogin_id(int login_id) {
        this.login_id = login_id;
    }
    
    
    ObservableList<TeacherData> teachers = FXCollections.observableArrayList();
    ObservableList<String> searchoptions = FXCollections.observableArrayList("TEACHER_ID","FIRST_NAME","LAST_NAME","FATHER_NAME","EMAIL","CONTACT_NUMBER");
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        search_by_combobox.setItems(searchoptions);
    }   
    
    public void Read_Data() throws SQLException {
        Connection cn = new Database_Connection().Database_Connectivity();
        String data="SELECT * FROM teachers";
        Statement statement = cn.createStatement();
        ResultSet resultset= statement.executeQuery(data);
        while(resultset.next())
        {
        teachers.add(new TeacherData(
                Integer.parseInt(resultset.getString("TEACHER_ID")),
                resultset.getString("FIRST_NAME"),
                resultset.getString("LAST_NAME"),
                resultset.getString("FATHER_NAME"),
                resultset.getString("EMAIL"),
                resultset.getString("CONTACT_NUMBER"),
                resultset.getString("DATE_OF_BIRTH"),
                resultset.getString("GENDER"),
                resultset.getString("DEPARTMENT"),
                resultset.getString("ADDRESS"),
                resultset.getString("QUALIFICATION"),
                resultset.getString("JOINED"),
                resultset.getString("LEFT_DATE")
        ));
        }
        col_id.setCellValueFactory(new PropertyValueFactory<>("teacher_id"));
        col_firstname.setCellValueFactory(new PropertyValueFactory<>("first_name"));
        col_lastname.setCellValueFactory(new PropertyValueFactory<>("last_name"));
        col_father.setCellValueFactory(new PropertyValueFactory<>("father_name"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_number.setCellValueFactory(new PropertyValueFactory<>("number"));
        col_dob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        col_department.setCellValueFactory(new PropertyValueFactory<>("department"));
        col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        col_qualification.setCellValueFactory(new PropertyValueFactory<>("qualification"));
        col_joined.setCellValueFactory(new PropertyValueFactory<>("joined"));
        col_left.setCellValueFactory(new PropertyValueFactory<>("left"));
        teacher_table.setItems(teachers);
        
    }

    @FXML
    private void SearchButton(ActionEvent event) 
    {
        teachers.clear();
        if(!search_entity.getText().equals(""))
        {
        Connection connection = new Database_Connection().Database_Connectivity();
        String searchQuery = "SELECT * FROM teachers WHERE "+search_by_combobox.getValue().toString()+"= '"+search_entity.getText()+"'";
        try {
            ResultSet resultset = connection.createStatement().executeQuery(searchQuery);
            while(resultset.next())
        {
        teachers.add(new TeacherData(
                Integer.parseInt(resultset.getString("TEACHER_ID")),
                resultset.getString("FIRST_NAME"),
                resultset.getString("LAST_NAME"),
                resultset.getString("FATHER_NAME"),
                resultset.getString("EMAIL"),
                resultset.getString("CONTACT_NUMBER"),
                resultset.getString("DATE_OF_BIRTH"),
                resultset.getString("GENDER"),
                resultset.getString("DEPARTMENT"),
                resultset.getString("ADDRESS"),
                resultset.getString("QUALIFICATION"),
                resultset.getString("JOINED"),
                resultset.getString("LEFT_DATE")
        ));
        }
            col_id.setCellValueFactory(new PropertyValueFactory<>("teacher_id"));
        col_firstname.setCellValueFactory(new PropertyValueFactory<>("first_name"));
        col_lastname.setCellValueFactory(new PropertyValueFactory<>("last_name"));
        col_father.setCellValueFactory(new PropertyValueFactory<>("father_name"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_number.setCellValueFactory(new PropertyValueFactory<>("number"));
        col_dob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        col_department.setCellValueFactory(new PropertyValueFactory<>("department"));
        col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        col_qualification.setCellValueFactory(new PropertyValueFactory<>("qualification"));
        col_joined.setCellValueFactory(new PropertyValueFactory<>("joined"));
        col_left.setCellValueFactory(new PropertyValueFactory<>("left"));
        teacher_table.setItems(teachers);
            
        } catch (SQLException ex) {
            Logger.getLogger(TeacherSearchController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        else
            try {
                Read_Data();
        } catch (SQLException ex) {
            Logger.getLogger(TeacherSearchController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}
