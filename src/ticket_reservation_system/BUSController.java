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
public class BUSController implements Initializable {
       ObservableList<String>SourceStationList=
            FXCollections.observableArrayList("Dhaka","Rajshahi","Khulna","Barishal","Syllet");
       
      ObservableList<String>ToStationList=
            FXCollections.observableArrayList("Chittagang","Kustia","Mymensingh","Rajbari","Comilla");
      
      ObservableList<String>StartTimeList=
            FXCollections.observableArrayList("8 A.M ","10 A.M ","2 P.M ","5 P.M ","8 P.M ","10.30 P.M ");
      
             ObservableList<String>BusNameList=
            FXCollections.observableArrayList("VOLVO-1","VOLVO-2","DOYEL","ROYAL","SK-4");
    @FXML
    private Label BusLevelStatus;
    @FXML
    private ChoiceBox<String> FromChoiceBox;
    @FXML
    private ChoiceBox<String> ToChoiceBox;
    @FXML
    private ChoiceBox<String> StartTimeChoiceBox;
    @FXML
    private Label Statuslevel;
    @FXML
    private ChoiceBox<String> BusNameChoiceBox;
    @FXML
    private TextField Date;
    @FXML
    private TextField Month;
    @FXML
    private TextField Year;
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
        FromChoiceBox.setValue("Dhaka");
        FromChoiceBox.setItems(SourceStationList);
        ToChoiceBox.setValue("Chittagang");
        ToChoiceBox.setItems(ToStationList);
        StartTimeChoiceBox.setValue("8 A.M ");
        StartTimeChoiceBox.setItems(StartTimeList);
        
       BusNameChoiceBox.setValue("VOLVO-1");
        BusNameChoiceBox.setItems(BusNameList);
                           try {
            clientSocket = new Socket("localhost", 6789);
           ToServer = new DataOutputStream(clientSocket.getOutputStream());
             FromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException ex) {
            System.out.println("Interrupted");
        }
           
    }    

    @FXML
    private void HandleOkayButton(ActionEvent event)throws Exception {
        
      //  System.out.println("Hi this is Mamun");
         if(Date.getText().equals("") || Integer.parseInt(Date.getText())>31)
        Statuslevel.setText("You Have Not Entered A Valid Date");
     
         else if(Month.getText().equals("") ||  Integer.parseInt(Month.getText())>12)
                     Statuslevel.setText("You Have Not Entered A Valid Month");
         else if(Year.getText().equals("") || Integer.parseInt(Year.getText())<2016)
                   Statuslevel.setText("You Have Not Entered A Valid Year");
         else if(ticketamountfield.getText().equals(""))
                Statuslevel.setText("You Have Not Entered The Amount Of Tickets");
          else if(cardnumberfield.getText().equals(""))
                   Statuslevel.setText("You Have Not Entered Your Card Number");
         

        else
         {   
             S1=FromChoiceBox.getValue();
             S2=ToChoiceBox.getValue();
             S3=StartTimeChoiceBox.getValue();
             S4=BusNameChoiceBox.getValue();
             S5=Date.getText()+Month.getText()+Year.getText();
             S6=cardnumberfield.getText();
             
                  sentence="CARDCHECK";
                 ToServer.writeBytes(sentence + '\n') ;
                 sentence=cardnumberfield.getText();
                 ToServer.writeBytes(sentence + '\n') ;
                   sentence=ticketamountfield.getText();
                 ToServer.writeBytes(sentence + '\n') ;
                 modifiedSentence = FromServer.readLine();
                 if(modifiedSentence.equals("null"))Statuslevel.setText("You Have Entered A Wrong Card Number");
                 else if(modifiedSentence.equals("null1"))Statuslevel.setText("You Have Not Enough Money In Your Card");

             else
                 {
            Ticket_Reservation_System.root = FXMLLoader.load(getClass().getResource("BOOKINGTICKET.fxml")); 
        
           Scene scene = new Scene(Ticket_Reservation_System.root);
           
        Ticket_Reservation_System.window.setScene(scene);
          
           Ticket_Reservation_System.window.setTitle("                                                          Online Ticket Reservation System");

           
                 }
         }
    }

    @FXML
    private void HandleBusSchedule(ActionEvent event)throws Exception {
        
         // Parent root = FXMLLoader.load(getClass().getResource("BUSSCHEDULE.fxml"));
         // Stage primaryStage=new Stage();
         // Scene scene = new Scene(root);
         // primaryStage.setScene(scene);
          
           Ticket_Reservation_System.root = FXMLLoader.load(getClass().getResource("BUSSCHEDULE.fxml")); 
        
           Scene scene = new Scene(Ticket_Reservation_System.root);
           
        Ticket_Reservation_System.window.setScene(scene);
          
           Ticket_Reservation_System.window.setTitle("                                                          Online Ticket Reservation System");

        
    }

    @FXML
    private void HandleBackAction(ActionEvent event) throws IOException {
        
          Ticket_Reservation_System.root = FXMLLoader.load(getClass().getResource("FirstPage.fxml")); 
        
           Scene scene = new Scene(Ticket_Reservation_System.root);
           
        Ticket_Reservation_System.window.setScene(scene);
         
        Ticket_Reservation_System.window.setTitle("                                                WELCOME TO ONLINE TICKET RESERVATION SYSTEM");

    }

    
}
