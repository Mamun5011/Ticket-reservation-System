/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticket_reservation_system;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Mamun
 */
public class Ticket_Reservation_System extends Application {
    
    public static Parent root;
    public static Stage window;
    public static boolean stopped;
    public static String S="Online Ticket Reservation System";
   public static    Socket clientSocket;
   public static String S1,S2,S3,S4,S5,S6,name;
   public static int cardno;
   

    
    @Override
    public void start(Stage primaryStage)throws Exception {

        root = FXMLLoader.load(getClass().getResource("LOGIN.fxml")); 
        
        Scene scene = new Scene(root);   
        window = primaryStage;
        window.setScene(scene);
        window.setTitle(S);
       window.show();
        
 
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
      
    }
    
}
        
        
        
        
    

