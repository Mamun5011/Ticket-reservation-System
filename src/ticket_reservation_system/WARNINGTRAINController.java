/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticket_reservation_system;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import static ticket_reservation_system.Ticket_Reservation_System.name;

/**
 * FXML Controller class
 *
 * @author Mamun
 */
public class WARNINGTRAINController implements Initializable {
    @FXML
    private Button ok;
    @FXML
    private Label nametag;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        nametag.setText("Sorry , "+name);
    }    

    @FXML
    private void Handleokay(ActionEvent event) throws IOException {
        
        
          Ticket_Reservation_System.root = FXMLLoader.load(getClass().getResource("TRAINBOOKINGTICKET.fxml")); 
        
           Scene scene = new Scene(Ticket_Reservation_System.root);
           
         Ticket_Reservation_System.window.setScene(scene);
         
         Ticket_Reservation_System.window.setTitle("                                                                            WELCOME");

         
    }
    
}
