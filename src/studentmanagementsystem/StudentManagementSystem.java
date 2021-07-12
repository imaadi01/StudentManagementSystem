/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmanagementsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author aadikumar
 */
public class StudentManagementSystem extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("StartControlScreen.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
    }

    /*
    FXMLLoader fm = new FXMLLoader(getClass().getResource("OptionScreen.fxml"));
            Parent root = fm.load();
            OptionScreenController option =fm.getController();
            Stage s1 = new Stage();
            Scene scene1 = new Scene(root);
            s1.setScene(scene1);
            s1.setTitle("Quiz Application By Aadesh Kumar");
            s1.show();
    */
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
