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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author aadikumar
 */
public class StudentAttendenceController implements Initializable {

    @FXML
    private VBox vbox;

    ObservableList<String> items = FXCollections.observableArrayList("Present", "Absent", "Excuse");

    String[] total_students;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void Student_Attendence(String semester, String course_id, String section, String teacher_id) 
    {
        int students = 0;
        int i=0;
        Connection connection = new Database_Connection().Database_Connectivity();
        String query = "SELECT COUNT(*) AS ROWS_NUM FROM courses_enrolled WHERE COURSE_ID='" + course_id + "' AND SEMESTER='" + semester + "' AND SECTION='" + section + "' AND INSTRUCTOR='" + teacher_id + "'";
        try {
            ResultSet result = connection.createStatement().executeQuery(query);
            while (result.next()) 
            {                
                students = result.getInt("ROWS_NUM");
                System.out.println(students);
            }
            total_students = new String[students];
            query = "SELECT STUDENT_ID,FIRST_NAME,LAST_NAME FROM students,courses_enrolled WHERE students.STUDENT_ID=courses_enrolled.STUDENT AND COURSE_ID='" + course_id + "' AND courses_enrolled.SEMESTER='" + semester + "' AND courses_enrolled.SECTION='" + section + "' AND courses_enrolled.INSTRUCTOR='" + teacher_id + "'";
            result = connection.createStatement().executeQuery(query);
            while (result.next()) {
                HBox box = new HBox();
                Label student_id = new Label(result.getString("STUDENT_ID"));
                Label student_name = new Label(result.getString("FIRST_NAME") + " " + result.getString("LAST_NAME"));
                ComboBox set_attendence = new ComboBox(items);
                Button submit = new Button("Submit");
                Button attendence = new Button("Attendence");
                submit.setId(result.getString("STUDENT_ID")+","+teacher_id+","+course_id+","+section);
                attendence.setId(result.getString("STUDENT_ID")+","+course_id);
                set_attendence.setValue("Present");
                box.setBorder(new Border(new BorderStroke(Paint.valueOf("BLACK"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));
                student_id.setPadding(new Insets(10, 20, 20, 20));
                student_name.setPadding(new Insets(10, 40, 20, 20));
                set_attendence.setPadding(new Insets(10, 30, 20, 20));
                submit.setPadding(new Insets(10, 30, 20, 20));
                attendence.setPadding(new Insets(10, 30, 20, 20));
                box.setAlignment(Pos.CENTER);
                student_id.setAlignment(Pos.CENTER_LEFT);
                student_name.setAlignment(Pos.CENTER_LEFT);
                student_id.setWrapText(true);
                student_name.setWrapText(true);
                student_id.setFont(new Font(24));
                student_name.setFont(new Font(24));
                
                box.getChildren().addAll(student_id, student_name, set_attendence,submit,attendence);
                vbox.getChildren().add(box);
                submit.setOnAction((event) -> 
                {
                    try {
                        String[] entity = submit.getId().split(",");
                        Database_Connection database = new Database_Connection();
                        Connection connect = database.Database_Connectivity();
                        String insert = "INSERT INTO attendence (STUDENT_ID,TEACHER_ID,COURSE_ID,SECTION,DATE,STATUS) VALUES ('"+entity[0]+"','"+entity[1]+"','"+entity[2]+"','"+entity[3]+"','"+TeacherRegistrationController.Date_Input()+"','"+set_attendence.getValue().toString()+"')";
                        PreparedStatement prepared = connect.prepareStatement(insert);
                        prepared.executeUpdate();
                        submit.setBorder(new Border(new BorderStroke(Paint.valueOf("BLUE"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));
                    } catch (SQLException ex) {
                        Logger.getLogger(StudentAttendenceController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                });
                
                attendence.setOnAction((event) -> 
                {
                    try {
                        String[] entity = attendence.getId().split(",");
                        FXMLLoader fm = new FXMLLoader(getClass().getResource("StudentAttendedData.fxml"));
                        Parent root = fm.load();
                        Stage stage = new Stage();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                        StudentAttendedDataController attend = fm.getController();
                        attend.Show_Attendence(entity[1],Integer.parseInt(entity[0]));
                    } catch (IOException ex) {
                        Logger.getLogger(StudentAttendenceController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(StudentAttendenceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    

}
