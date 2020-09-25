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
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
//import static ticket_reservation_system.Ticket_Reservation_System.stopped;
import static ticket_reservation_system.Ticket_Reservation_System.window;
import static ticket_reservation_system.Ticket_Reservation_System.clientSocket;
import static ticket_reservation_system.Ticket_Reservation_System.name;
import static ticket_reservation_system.Ticket_Reservation_System.stopped;

/**
 * FXML Controller class
 *
 * @author Mamun
 */
public class LOGINController implements Initializable {
    

     Service<Void> service;
    
    
    @FXML
    private TextField UsernameTextField;
    @FXML
    private TextField PasswordTextField;
    @FXML
    private Label levelMessage;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*
        // TODO
        stopped=false;       
              try {
                welcomeSocket = new ServerSocket(6789);
                connectionSocket=null;
            } catch (IOException ex) {
                
           // System.out.println("Interrupted");
              }
              
                service = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {

                return new Task<Void>() {

                    @Override
                    protected Void call() throws Exception {
                                    int workerThreadCount = 0;
                                    int id = 1;
                                    while (true){
                                        if (stopped) break;
                                       
                                            connectionSocket = welcomeSocket.accept();

                                     
                                        Task<Void> task=new Worker(connectionSocket,id);
                                        Thread t = new Thread(task);
                                        t.setDaemon(true);
                                        t.start();
                                        workerThreadCount++;
                                       //System.out.println("Client [" + id + "] is now connected.\n");
                                       // id++;
                                    }
                               return null;
                            }
                        };
                    }



            @Override
            protected void cancelled() {
                super.cancelled();
                System.out.println("Service Canceled\n");
            }
        };

        service.start();  
        */ 
       try {
            clientSocket = new Socket("localhost", 6789);
        } catch (IOException ex) {
            System.out.println("Interrupted");
        }
             
    }    

    @FXML
    private void handleLoginAction(ActionEvent event)throws Exception {
        
    

    
      String username =UsernameTextField.getText();
      String password =PasswordTextField.getText();
      String sentence;
      String modifiedSentence;
       
   
             DataOutputStream  ToServer = new DataOutputStream(clientSocket.getOutputStream());
             BufferedReader  FromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
              sentence="U";
            ToServer.writeBytes(sentence + '\n');
            sentence=username;
            ToServer.writeBytes(sentence + '\n');
            modifiedSentence = FromServer.readLine(); 
            if(modifiedSentence.equals("null1"))levelMessage.setText("Enter Your Username");
            else if(modifiedSentence.equals("null2"))levelMessage.setText("Username is Invalid");
            else
            {
                sentence="P";
                 ToServer.writeBytes(sentence + '\n');
             sentence=password;
            ToServer.writeBytes(sentence + '\n');
             sentence=username;
            ToServer.writeBytes(sentence + '\n');
            modifiedSentence = FromServer.readLine(); 
            if(modifiedSentence.equals("null3"))levelMessage.setText("Enter Your Password");
            else if(modifiedSentence.equals("null4"))levelMessage.setText("Password is Incorrect");
            
            else
            {
                name=UsernameTextField.getText();
        levelMessage.setText("Welcome "+UsernameTextField.getText());
        Ticket_Reservation_System.root = FXMLLoader.load(getClass().getResource("FirstPage.fxml")); 
        Scene scene = new Scene(Ticket_Reservation_System.root);       
        Ticket_Reservation_System.window.setScene(scene);
            }
                 
            }
              
              
              
           
           
       }
        

    @FXML
    private void handleSignUpAction(ActionEvent event)throws Exception {
        
            Ticket_Reservation_System.root = FXMLLoader.load(getClass().getResource("SignUp.fxml")); 
        
           Scene scene = new Scene(Ticket_Reservation_System.root);
           
        Ticket_Reservation_System.window.setScene(scene);
         Ticket_Reservation_System.window.setTitle("                                                                            WELCOME");
          }
    
    /* class Worker extends Task<Void>
    {
        int id;
        Socket connectionSocket;
        public Worker(Socket connectionSocket,int id) {
            this.id = id;
            this.connectionSocket=connectionSocket;
        }

        @Override
        protected Void call() throws Exception {
            
           
            String clientSentence;
            String Result = null;
            String P = null;
            String k;
            
             DataOutputStream outToServer = new DataOutputStream(connectionSocket.getOutputStream());
              BufferedReader inFromServer = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
      
            while(true)
            {
                    clientSentence= inFromServer.readLine();
                    
                    if(stopped) {
                        connectionSocket.close();
                      
                        break;
                    }
                         
                    
                  if(clientSentence.equals("U"))
                  {
                      clientSentence= inFromServer.readLine();
                      
                      if(clientSentence.equals(""))
                      {
                          Result="null1";//Enter Your Name
                          outToServer.writeBytes(Result+ '\n');
                          
                      }
                      else
                      {
                           File file=new File("Client/"+clientSentence+".txt");
                            if(file.exists())
                            {
                            Result="okay";
                          outToServer.writeBytes(Result+ '\n');
                            }
                            else
                            {
                          Result="null2"; //Invalid Username
                          outToServer.writeBytes(Result+ '\n');
                            }
                            
                      }
    
                  } 
                  
                  
                 else if(clientSentence.equals("P"))
                 {
                  clientSentence= inFromServer.readLine();//taking password
                   String v;
                   v=clientSentence;
                   clientSentence= inFromServer.readLine();//taking Username
                                       
                      if(v.equals(""))
                      {
                          Result="null3";//enter your Password
                          outToServer.writeBytes(Result+ '\n');
                          
                      }
                  
                  else
                      {
                  
               File file=new File("Client/"+clientSentence+".txt");
               FileReader f=new FileReader(file);//or give the path of file
               BufferedReader fw=new BufferedReader(f);
                 k=fw.readLine();
               
                int i=1;
               while(k!=null)
             {
                if(i==4)
           {
               P=k;
               break;
           }
           k=fw.readLine();
           i=i+1;
       }
       fw.close();
           if(P.equals(v))
           {
               Result="okay"; 
               outToServer.writeBytes(Result+ '\n');
           }
           
           else
           {
               Result="null4";  //Invalid password
               outToServer.writeBytes(Result+ '\n');
           }
                
                      }  
        }         
                  
              else if(clientSentence.equals("S"))
                  {
                  clientSentence= inFromServer.readLine();
                File file=new File("Client/"+clientSentence+".txt");
                File filePath=new File("Client");
                if(file.exists())
                {
                    Result="null1";
                    outToServer.writeBytes(Result+ '\n');// Username  already exists
                } 
                else
                {
                      Result="okay";
                    outToServer.writeBytes(Result+ '\n');
                      
          filePath.mkdir();
      file.createNewFile();
       FileWriter m=new FileWriter(file);
       BufferedWriter fw=new BufferedWriter(m);
       fw.write("Name:"+"\n");
       clientSentence= inFromServer.readLine();
       fw.write( clientSentence+"\n");
        fw.write("Password:"+"\n");
          clientSentence= inFromServer.readLine();
       fw.write( clientSentence+"\n");
       fw.write("Card no:"+"\n");
       clientSentence= inFromServer.readLine();
       fw.write( clientSentence+"\n");
       fw.write("Gender:"+"\n");
        clientSentence= inFromServer.readLine();
       fw.write( clientSentence+"\n");
       fw.write("Address:"+"\n");
              clientSentence= inFromServer.readLine();
       fw.write( clientSentence+"\n");
        fw.write("Contact Number:"+"\n");
             clientSentence= inFromServer.readLine();
       fw.write( clientSentence+"\n");
        fw.write("Email:"+"\n");
              clientSentence= inFromServer.readLine();
       fw.write( clientSentence+"\n");
        fw.write("Age:"+"\n");
              clientSentence= inFromServer.readLine();
       fw.write( clientSentence+"\n");

         fw.write("Date of Birth:"+"\n");
             clientSentence= inFromServer.readLine();
       fw.write( clientSentence+"\n");
             clientSentence= inFromServer.readLine();
       fw.write( clientSentence+"\n");
             clientSentence= inFromServer.readLine();
       fw.write( clientSentence+"\n");
        fw.write("Occupation:"+"\n");
             clientSentence= inFromServer.readLine();
       fw.write( clientSentence+"\n");
        fw.write("Nationality:"+"\n");
             clientSentence= inFromServer.readLine();
       fw.write( clientSentence+"\n");
               clientSentence= inFromServer.readLine();
       fw.write( clientSentence+"\n");
        
       fw.close();
       
       
       
                }
                
                
                
                
                
                
                
                
                
                
                  }
                 else if(clientSentence.charAt(clientSentence.length()-1)=='B')
                  {
                  
                  }
                   else if(clientSentence.charAt(clientSentence.length()-1)=='T')
                  {
                  
                  }
                  
                   else if(clientSentence.charAt(clientSentence.length()-1)=='t')
                  {
                  
                  }

             
                }
            
            System.out.println("Client "+id+" connection Canceled");
        
            return null;
        }
    }
         */  
    
}
