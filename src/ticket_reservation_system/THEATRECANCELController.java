/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticket_reservation_system;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import static ticket_reservation_system.Ticket_Reservation_System.clientSocket;

/**
 * FXML Controller class
 *
 * @author Mamun
 */
public class THEATRECANCELController implements Initializable {

   
    @FXML
    private TextField cardnumberfield;
    @FXML
    private Label messagebox;

                        String sentence=null;
         String modifiedSentence=null;
         BufferedReader  FromServer;
         DataOutputStream  ToServer;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                                        try {
            clientSocket = new Socket("localhost", 6789);
           ToServer = new DataOutputStream(clientSocket.getOutputStream());
             FromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException ex) {
            System.out.println("Interrupted");
        }
    }    


    @FXML
    private void okayButton(ActionEvent event) throws IOException {
        
            if(cardnumberfield.getText().equals(""))
        messagebox.setText("You Have Not Entered Your Card Number");
         else
         {
                   sentence="CHECK";
                 ToServer.writeBytes(sentence + '\n') ;
                 sentence=cardnumberfield.getText();
                 ToServer.writeBytes(sentence + '\n') ;
                 modifiedSentence = FromServer.readLine();
                 if(modifiedSentence.equals("NO"))messagebox.setText("You Have Entered A Wrong Card Number");
                 else
                 {
                    sentence= "CANCELBOOKING";
                    ToServer.writeBytes(sentence + '\n') ;
                    sentence="THEATRE";
                    ToServer.writeBytes(sentence + '\n') ;
                  sentence=cardnumberfield.getText();
                 ToServer.writeBytes(sentence + '\n') ;
                 
                 
                  Ticket_Reservation_System.root = FXMLLoader.load(getClass().getResource("SUCCESSFULLYCANCEL.fxml")); 
        
           Scene scene = new Scene(Ticket_Reservation_System.root);
           
        Ticket_Reservation_System.window.setScene(scene);
          
           Ticket_Reservation_System.window.setTitle("                                                          Online Ticket Reservation System");

        
                 }
             
             
             
             
         }
    }


    
}
 /*Ticket_Reservation_System.root = FXMLLoader.load(getClass().getResource("SignUp.fxml")); 
        
           Scene scene = new Scene(Ticket_Reservation_System.root);
           
        Ticket_Reservation_System.window.setScene(scene);
         
Ticket_Reservation_System.window.setTitle("                                                                            WELCOME");
*/