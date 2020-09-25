/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticket_reservation_system;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import static ticket_reservation_system.Ticket_Reservation_System.clientSocket;

/**
 * FXML Controller class
 *
 * @author Mamun
 */
public class SignUpController implements Initializable {
    
    String G="Male";
    ObservableList<String>MaritalStatusList=
            FXCollections.observableArrayList("Single","Married");
    @FXML
    private TextField NameTextField;
    @FXML
    private TextField AddressTextField;
    @FXML
    private TextField PhoneTextField;
    @FXML
    private TextField EmailTextField;
    @FXML
    private TextField AgeTextField;
    @FXML
    private Button OkayButton;

    @FXML
    private ToggleGroup gender;
    @FXML
    private ChoiceBox<String> MaritalStatusBox;
    @FXML
    private TextField CardNoField;
    @FXML
    private TextField DateField;
    @FXML
    private TextField MonthField;
    @FXML
    private TextField YearField;
    @FXML
    private TextField OccupationField;
    @FXML
    private TextField NationalityField;
    @FXML
    private Label StatusBox;
    @FXML
    private TextField PasswordField;
    
String h;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        MaritalStatusBox.setValue("Single");
        MaritalStatusBox.setItems(MaritalStatusList);
    }    
  

    
    

    @FXML
    private void HandleOkayButton(ActionEvent event) throws IOException {
       String sentence;
      String modifiedSentence="wwe";

       
        if(NameTextField.getText().equals(""))StatusBox.setText("Enter Your Username");
       else if(PasswordField.getText().equals(""))StatusBox.setText("Enter Your Password");
        else if(AddressTextField.getText().equals(""))StatusBox.setText("Enter Your Address");
        else if(CardNoField.getText().equals(""))StatusBox.setText("Enter Your Card Number");
       else if(PhoneTextField.getText().equals(""))StatusBox.setText("Enter Your Phone Number");
       else if(EmailTextField.getText().equals(""))StatusBox.setText("Enter Your Email Number");
        else if(OccupationField.getText().equals(""))StatusBox.setText("Enter Your Occupation");
       else if(DateField.getText().equals(""))StatusBox.setText("Enter Your Birth Date");
       else if(MonthField.getText().equals(""))StatusBox.setText("Enter Your Birth Month");
       else if(YearField.getText().equals(""))StatusBox.setText("Enter Your Birth Year");
       else if(AgeTextField.getText().equals(""))StatusBox.setText("Enter Your Age");
       else if(NationalityField.getText().equals(""))StatusBox.setText("Enter Your Nationality");
       
       else{
           try {
            clientSocket = new Socket("localhost", 6789);
        } catch (IOException ex) {
            System.out.println("Interrupted");
        }
            DataOutputStream  ToServer = new DataOutputStream(clientSocket.getOutputStream());
             BufferedReader  FromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
           
        sentence="S";
        ToServer.writeBytes(sentence + '\n');
        sentence=NameTextField.getText(); 
         ToServer.writeBytes(sentence + '\n');
         modifiedSentence = FromServer.readLine(); 
         
   
       if(modifiedSentence.equals("null1")) StatusBox.setText("UserName Already Exists"); 

        else
       {
       sentence=NameTextField.getText();
       ToServer.writeBytes(sentence + '\n');
   
       sentence=PasswordField.getText();
       ToServer.writeBytes(sentence + '\n');
       
             sentence=CardNoField.getText();
       ToServer.writeBytes(sentence + '\n');
       
       
                  sentence=G;
       ToServer.writeBytes(sentence + '\n');
       
                 sentence=AddressTextField.getText();
       ToServer.writeBytes(sentence + '\n');
      
       
        sentence=PhoneTextField.getText();
       ToServer.writeBytes(sentence + '\n');
      
       
               sentence=EmailTextField.getText();
       ToServer.writeBytes(sentence + '\n');
      
       
                   sentence=AgeTextField.getText();
       ToServer.writeBytes(sentence + '\n');
     

          sentence=DateField.getText();
       ToServer.writeBytes(sentence + '\n');
     
         
          sentence=MonthField.getText();
       ToServer.writeBytes(sentence + '\n');
       
            sentence=YearField.getText();
       ToServer.writeBytes(sentence + '\n');
       

       
                    sentence=OccupationField.getText();
       ToServer.writeBytes(sentence + '\n');
    
       
               
                    sentence=NationalityField.getText();
       ToServer.writeBytes(sentence + '\n');
 
                          sentence=MaritalStatusBox.getValue();
       ToServer.writeBytes(sentence + '\n');
      
      
    
        Ticket_Reservation_System.root = FXMLLoader.load(getClass().getResource("LOGIN.fxml")); 
        
           Scene scene = new Scene(Ticket_Reservation_System.root);
           
        Ticket_Reservation_System.window.setScene(scene);
          
           Ticket_Reservation_System.window.setTitle("                                                          Online Ticket Reservation System");
       }
       }
    }

    @FXML
    private void HandleMaleButton(ActionEvent event) {
        G="Male";
        
    }

    @FXML
    private void HandleFemaleButton(ActionEvent event) {
       G="FeMale";
    }

    @FXML
    private void handleBack(ActionEvent event) throws IOException {
        
          Ticket_Reservation_System.root = FXMLLoader.load(getClass().getResource("LOGIN.fxml")); 
        
           Scene scene = new Scene(Ticket_Reservation_System.root);
           
         Ticket_Reservation_System.window.setScene(scene);
         
         Ticket_Reservation_System.window.setTitle("                                                                            WELCOME");

        
    }
    
}
     