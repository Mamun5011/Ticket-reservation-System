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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import static ticket_reservation_system.Ticket_Reservation_System.S1;
import static ticket_reservation_system.Ticket_Reservation_System.S2;
import static ticket_reservation_system.Ticket_Reservation_System.S3;
import static ticket_reservation_system.Ticket_Reservation_System.S4;
import static ticket_reservation_system.Ticket_Reservation_System.S5;
import static ticket_reservation_system.Ticket_Reservation_System.S6;
import static ticket_reservation_system.Ticket_Reservation_System.clientSocket;

/**
 * FXML Controller class
 *
 * @author Mamun
 */
public class THEATREController implements Initializable {
    
          ObservableList<String>MovieNameList=
            FXCollections.observableArrayList("Limitless","The Great Gatsby","Titanic","Kunfu Panda 3");
      
      ObservableList<String>StartTimeList=
            FXCollections.observableArrayList("2 P.M ","5 P.M ","8 P.M ","10 P.M ");
    @FXML
    private ChoiceBox<String> MovieChoiceBox;
    @FXML
    private ChoiceBox<String> TimeChoiceBox;
    @FXML
    private Label StatusBox;
    @FXML
    private TextField date;
    @FXML
    private TextField month;
    @FXML
    private TextField year;
    
                String sentence=null;
         String modifiedSentence=null;
         BufferedReader  FromServer;
         DataOutputStream  ToServer;
    @FXML
    private TextField ticketamountfield;
    @FXML
    private TextField cardnumberfield;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                 MovieChoiceBox.setValue("Kunfu Panda 3");
        MovieChoiceBox.setItems(MovieNameList);
        TimeChoiceBox.setValue("2 P.M ");
        TimeChoiceBox.setItems(StartTimeList);
        
                                 try {
            clientSocket = new Socket("localhost", 6789);
           ToServer = new DataOutputStream(clientSocket.getOutputStream());
             FromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException ex) {
            System.out.println("Interrupted");
        }
        
    }    

    @FXML
    private void HandleOkayButton(ActionEvent event) throws IOException {
   // Ticket_Reservation_System.root = FXMLLoader.load(getClass().getResource("THEATREBOOKINGTICKET.fxml")); 
        
       if(date.getText().equals("") || Integer.parseInt(date.getText())>31)
        StatusBox.setText("You Have Not Entered A Valid Date");
     
         else if(month.getText().equals("") ||  Integer.parseInt(month.getText())>12)
                     StatusBox.setText("You Have Not Entered A Valid Month");
         else if(year.getText().equals("") || Integer.parseInt(year.getText())<2016)
                   StatusBox.setText("You Have Not Entered A Valid Year");
         else if(ticketamountfield.getText().equals(""))
                StatusBox.setText("You Have Not Entered The Amount Of Tickets");
          else if(cardnumberfield.getText().equals(""))
          StatusBox.setText("You Have Not Entered Your Card Number");

        else
         {   
             S1=MovieChoiceBox.getValue();
             S2=TimeChoiceBox.getValue();
             S3="";
             S4="";
             S5=date.getText()+month.getText()+year.getText();
             S6=cardnumberfield.getText();

                 sentence="CARDCHECK";
                 ToServer.writeBytes(sentence + '\n') ;
                 sentence=cardnumberfield.getText();
                 ToServer.writeBytes(sentence + '\n') ;
                   sentence=ticketamountfield.getText();
                 ToServer.writeBytes(sentence + '\n') ;
                 modifiedSentence = FromServer.readLine();
                 if(modifiedSentence.equals("null"))StatusBox.setText("You Have Entered A Wrong Card Number");
                 else if(modifiedSentence.equals("null1"))StatusBox.setText("You Have Not Enough Money In Your Card");

             else
                 {
            Ticket_Reservation_System.root = FXMLLoader.load(getClass().getResource("THEATREBOOKINGTICKET.fxml")); 
        
           Scene scene = new Scene(Ticket_Reservation_System.root);
           
        Ticket_Reservation_System.window.setScene(scene);
          
           Ticket_Reservation_System.window.setTitle("                                                          Online Ticket Reservation System");

           
                 }
         }
    }

    @FXML
    private void HandleTheatreSchedule(ActionEvent event)throws Exception {
        
        
            Ticket_Reservation_System.root = FXMLLoader.load(getClass().getResource("THEATRESCHEDULE.fxml")); 
        
           Scene scene = new Scene(Ticket_Reservation_System.root);
           
        Ticket_Reservation_System.window.setScene(scene);
          
           Ticket_Reservation_System.window.setTitle("                                                          Online Ticket Reservation System");

    }

    @FXML
    private void HandleBack(ActionEvent event) throws IOException {
        
         Ticket_Reservation_System.root = FXMLLoader.load(getClass().getResource("FirstPage.fxml")); 
        
           Scene scene = new Scene(Ticket_Reservation_System.root);
           
        Ticket_Reservation_System.window.setScene(scene);
         
Ticket_Reservation_System.window.setTitle("                                                                            WELCOME");

    }
    
}
