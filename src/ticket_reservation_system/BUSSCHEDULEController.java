/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticket_reservation_system;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mamun
 */
public class BUSSCHEDULEController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void HandleOkButton(ActionEvent event)throws Exception {
        
         /* Parent root = FXMLLoader.load(getClass().getResource("BUS.fxml"));
          Stage primaryStage=new Stage();
          Scene scene = new Scene(root);
          primaryStage.setScene(scene);
          primaryStage.setTitle("                                                                                 WELCOME");
          primaryStage.show();*/
        
          Ticket_Reservation_System.root = FXMLLoader.load(getClass().getResource("BUS.fxml")); 
        
           Scene scene = new Scene(Ticket_Reservation_System.root);
           
        Ticket_Reservation_System.window.setScene(scene);
          
           Ticket_Reservation_System.window.setTitle("                                                          Online Ticket Reservation System");

    }
    
}
