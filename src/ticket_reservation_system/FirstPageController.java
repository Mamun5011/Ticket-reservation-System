/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticket_reservation_system;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.concurrent.Service;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import static ticket_reservation_system.Ticket_Reservation_System.clientSocket;

/**
 * FXML Controller class
 *
 * @author Mamun
 */
public class FirstPageController implements Initializable {
    
    int BB=1,BC=0,TB=0,TC=0,MB=0,MC=0;      
    
    @FXML
    private ToggleGroup cancel;

    /**
     * Initializes the controller class.
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void BusTicketBooking(ActionEvent event) {
        BB=1;
        BC=0;
        TB=0;
        TC=0;
        MB=0;
        MC=0;
    }

    @FXML
    private void BusTicketCancelling(ActionEvent event) {
        BB=0;
        BC=1;
        TB=0;
        TC=0;
        MB=0;
        MC=0;
    }

    @FXML
    private void TrainTicketBooking(ActionEvent event) {
        BB=0;
        BC=0;
        TB=1;
        TC=0;
        MB=0;
        MC=0;
    }

    @FXML
    private void TrainTicketCancelling(ActionEvent event) {
        BB=0;
        BC=0;
        TB=0;
        TC=1;
        MB=0;
        MC=0;
    }

    @FXML
    private void ThreatreTicketBooking(ActionEvent event) {
        BB=0;
        BC=0;
        TB=0;
        TC=0;
        MB=1;
        MC=0;
    }

    @FXML
    private void TheatreTicketCancelling(ActionEvent event) {
        BB=0;
        BC=0;
        TB=0;
        TC=0;
        MB=0;
        MC=1;
    }

    @FXML
    private void OkButtonAction(ActionEvent event)throws Exception {
       // String t=((Radio Button) event.getSource()).getText();
      if(BB==1)
      {
      
          
      Ticket_Reservation_System.root = FXMLLoader.load(getClass().getResource("BUS.fxml")); 
        
           Scene scene = new Scene(Ticket_Reservation_System.root);
           
        Ticket_Reservation_System.window.setScene(scene);
          
           Ticket_Reservation_System.window.setTitle("                                                          Online Ticket Reservation System");

        
    }
     
      else if(TB==1)
      {
         
         Ticket_Reservation_System.root = FXMLLoader.load(getClass().getResource("TRAIN.fxml")); 
        
           Scene scene = new Scene(Ticket_Reservation_System.root);
           
        Ticket_Reservation_System.window.setScene(scene);
          
           Ticket_Reservation_System.window.setTitle("                                                          Online Ticket Reservation System");

      }
      else if(MB==1)
      {
         
          Ticket_Reservation_System.root = FXMLLoader.load(getClass().getResource("THEATRE.fxml")); 
        
           Scene scene = new Scene(Ticket_Reservation_System.root);
           
        Ticket_Reservation_System.window.setScene(scene);
          
           Ticket_Reservation_System.window.setTitle("                                                          Online Ticket Reservation System");

      }
      else if(BC==1)
      {
       
         Ticket_Reservation_System.root = FXMLLoader.load(getClass().getResource("BUSCANCEL.fxml")); 
        
           Scene scene = new Scene(Ticket_Reservation_System.root);
           
        Ticket_Reservation_System.window.setScene(scene);
          
           Ticket_Reservation_System.window.setTitle("                                                          Online Ticket Reservation System");

      }
      else if(TC==1)
      {
           
       Ticket_Reservation_System.root = FXMLLoader.load(getClass().getResource("TRAINCANCEL.fxml")); 
        
           Scene scene = new Scene(Ticket_Reservation_System.root);
           
        Ticket_Reservation_System.window.setScene(scene);
          
           Ticket_Reservation_System.window.setTitle("                                                          Online Ticket Reservation System");

      }
      else if(MC==1)
      {
    
        Ticket_Reservation_System.root = FXMLLoader.load(getClass().getResource("THEATRECANCEL.fxml")); 
        
           Scene scene = new Scene(Ticket_Reservation_System.root);
           
        Ticket_Reservation_System.window.setScene(scene);
          
           Ticket_Reservation_System.window.setTitle("                                                          Online Ticket Reservation System");

      }
    
}

    @FXML
    private void HandleBAck(ActionEvent event) throws IOException {
        
        Ticket_Reservation_System.root = FXMLLoader.load(getClass().getResource("LOGIN.fxml")); 
        
           Scene scene = new Scene(Ticket_Reservation_System.root);
           
        Ticket_Reservation_System.window.setScene(scene);
         Ticket_Reservation_System.window.setTitle("                                                                            WELCOME");
    }

    @FXML
    private void HandleQuit(ActionEvent event)  {
       // Ticket_Reservation_System.stopped=true;
        
        Ticket_Reservation_System.window.close();
        
    }
}
