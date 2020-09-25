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
public class BUSCANCELController implements Initializable {
    @FXML
    private TextField CardNoTextField;
    @FXML
    private Label StatusLevel;
                    String sentence=null;
         String modifiedSentence=null;
         BufferedReader  FromServer;
         DataOutputStream  ToServer;
    /**
     * Initializes the controller class.
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
    private void HandkeOkButton(ActionEvent event) throws IOException {
        if(CardNoTextField.getText().equals(""))
        StatusLevel.setText("You Have Not Entered Your Card Number");
        
        else
        {
               sentence="CHECK";
                 ToServer.writeBytes(sentence + '\n') ;
                 sentence=CardNoTextField.getText();
                 ToServer.writeBytes(sentence + '\n') ;
                 modifiedSentence = FromServer.readLine();
                 if(modifiedSentence.equals("NO"))StatusLevel.setText("You Have Entered A Wrong Card Number");
                 else
                 {
                    sentence= "CANCELBOOKING";
                    ToServer.writeBytes(sentence + '\n') ;
                    sentence="BUS";
                    ToServer.writeBytes(sentence + '\n') ;
                  sentence=CardNoTextField.getText();
                 ToServer.writeBytes(sentence + '\n') ;
                 
                 
                  Ticket_Reservation_System.root = FXMLLoader.load(getClass().getResource("SUCCESSFULLYCANCEL.fxml")); 
        
           Scene scene = new Scene(Ticket_Reservation_System.root);
           
        Ticket_Reservation_System.window.setScene(scene);
          
           Ticket_Reservation_System.window.setTitle("                                                          Online Ticket Reservation System");

        
                 }
        }
    }
    
}
