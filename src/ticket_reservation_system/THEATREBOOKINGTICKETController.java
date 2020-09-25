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
public class THEATREBOOKINGTICKETController implements Initializable {

    /**
     * Initializes the controller class.
     */
          int []a=new int[19];
      int []booking=new int[19];
               String sentence=null;
         String modifiedSentence=null;
         String Seat="";
         BufferedReader  FromServer;
         DataOutputStream  ToServer;
    @FXML
    private Label MSGBOX;
      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
            for(int i=0;i<19;i++)
        {
            a[i]=0;
            booking[i]=0;
        }
    
                   try {
            clientSocket = new Socket("localhost", 6789);
        } catch (IOException ex) {
            System.out.println("Interrupted");
        }
           
          try {
              ToServer = new DataOutputStream(clientSocket.getOutputStream());
             FromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             sentence="SEATCHECK";
                ToServer.writeBytes(sentence + '\n');
                sentence=S1+S2+S3+S4+S5;
                 ToServer.writeBytes(sentence + '\n') ;
                 sentence="THEATRE";
                 ToServer.writeBytes(sentence + '\n') ;
                 
                 modifiedSentence = FromServer.readLine();
                 int i=0;
                 while(!modifiedSentence.equals("-1"))
                 {
                     System.out.println(modifiedSentence);
                     booking[i]=Integer.parseInt(modifiedSentence);
                     i=i+1;
                     
                     //Seat=Seat+modifiedSentence;
                      modifiedSentence = FromServer.readLine();
                 }

          } catch (IOException ex) {
              System.out.println("interrupted");          }
           
 
    }    

    @FXML
    private void HandleBack(ActionEvent event) throws IOException {
        
         Ticket_Reservation_System.root = FXMLLoader.load(getClass().getResource("THEATRE.fxml")); 
        
           Scene scene = new Scene(Ticket_Reservation_System.root);
           
        Ticket_Reservation_System.window.setScene(scene);
         
Ticket_Reservation_System.window.setTitle("                                                                            WELCOME");

    }

    @FXML
    private void HandleOk(ActionEvent event) throws IOException {
        
                sentence="DEDUCTION";
        ToServer.writeBytes(sentence + '\n');
                sentence=S6;
        ToServer.writeBytes(sentence + '\n');
         int g=0;
                for(int i=1;i<19;i++)
        {
            if(a[i]==1)
            {
              g=g+1;
            }
        }
        sentence=""+g;
        ToServer.writeBytes(sentence + '\n');
              sentence="THEATRE";
        ToServer.writeBytes(sentence + '\n');
       modifiedSentence = FromServer.readLine();
       if(modifiedSentence.equals("null1"))MSGBOX.setText("You Have Selected More Tickets");
       else
       {
          sentence=S1+S2+S3+S4+S5;
           ToServer.writeBytes(sentence + '\n') ;//sending busname and schedule
                   for(int i=1;i<19;i++)
        {
            if(a[i]==1)
            {
                 sentence=""+i;
                 ToServer.writeBytes(sentence + '\n');
            }
        }
           sentence="-1";
        ToServer.writeBytes(sentence + '\n');
       

        
     
             sentence="BOOKING";
        ToServer.writeBytes(sentence + '\n');
              sentence="THEATRE";
        ToServer.writeBytes(sentence + '\n');
         sentence=S1+S2+S3+S4+S5;
         ToServer.writeBytes(sentence + '\n') ;
        for(int i=1;i<19;i++)
        {
            if(a[i]==1)
            {
                 sentence=""+i;
                 ToServer.writeBytes(sentence + '\n');
            }
        }
        
                    sentence="-1";
        ToServer.writeBytes(sentence + '\n');
        
        
         Ticket_Reservation_System.root = FXMLLoader.load(getClass().getResource("CONFIRMATION.fxml")); 
        
           Scene scene = new Scene(Ticket_Reservation_System.root);
           
         Ticket_Reservation_System.window.setScene(scene);
         
         Ticket_Reservation_System.window.setTitle("                                                                            WELCOME");

        
        
        
    }
    }

    @FXML
    private void HANDLEA(ActionEvent event) throws IOException {
        
                          sentence="SEATCHECK";
                ToServer.writeBytes(sentence + '\n');
                sentence=S1+S2+S3+S4+S5;
                 ToServer.writeBytes(sentence + '\n') ;
                  sentence="THEATRE";
                ToServer.writeBytes(sentence + '\n') ;
                
                 
                 modifiedSentence = FromServer.readLine();
                 int k=0;
                 while(!modifiedSentence.equals("-1"))
                 {
                     System.out.println(modifiedSentence);
                     booking[k]=Integer.parseInt(modifiedSentence);
                     k=k+1;
                     
                     //Seat=Seat+modifiedSentence;
                      modifiedSentence = FromServer.readLine();
                 }
        
        
   

        for(int i=0;i<19;i++)
        {
            if(booking[i]==1)
            {
                System.out.println("hello Mamun");
                Ticket_Reservation_System.root = FXMLLoader.load(getClass().getResource("THEATREWARNING.fxml")); 
        
           Scene scene = new Scene(Ticket_Reservation_System.root);
           
         Ticket_Reservation_System.window.setScene(scene);
         
         Ticket_Reservation_System.window.setTitle("                                                                            WELCOME");

        
            }
        }
       
            if(a[1]==0)
      {
          a[1]=1;
      }
      else
          a[1]=0;
      
    }

    @FXML
    private void HANDLEB(ActionEvent event) throws IOException {
        
                          sentence="SEATCHECK";
                ToServer.writeBytes(sentence + '\n');
                sentence=S1+S2+S3+S4+S5;
                 ToServer.writeBytes(sentence + '\n') ;
                  sentence="THEATRE";
                ToServer.writeBytes(sentence + '\n') ;
                
                 
                 modifiedSentence = FromServer.readLine();
                 int k=0;
                 while(!modifiedSentence.equals("-1"))
                 {
                     System.out.println(modifiedSentence);
                     booking[k]=Integer.parseInt(modifiedSentence);
                     k=k+1;
                     
                     //Seat=Seat+modifiedSentence;
                      modifiedSentence = FromServer.readLine();
                 }
        
        
   

        for(int i=0;i<19;i++)
        {
            if(booking[i]==2)
            {
                Ticket_Reservation_System.root = FXMLLoader.load(getClass().getResource("THEATREWARNING.fxml")); 
        
           Scene scene = new Scene(Ticket_Reservation_System.root);
           
         Ticket_Reservation_System.window.setScene(scene);
         
         Ticket_Reservation_System.window.setTitle("                                                                            WELCOME");

        
            }
        }
       
            if(a[2]==0)
      {
          a[2]=1;
      }
      else
          a[2]=0;
      
    }

    @FXML
    private void HANDLEC(ActionEvent event) throws IOException {
        
                                 sentence="SEATCHECK";
                ToServer.writeBytes(sentence + '\n');
                sentence=S1+S2+S3+S4+S5;
                 ToServer.writeBytes(sentence + '\n') ;
                  sentence="THEATRE";
                ToServer.writeBytes(sentence + '\n') ;
                
                 
                 modifiedSentence = FromServer.readLine();
                 int k=0;
                 while(!modifiedSentence.equals("-1"))
                 {
                     System.out.println(modifiedSentence);
                     booking[k]=Integer.parseInt(modifiedSentence);
                     k=k+1;
                     
                     //Seat=Seat+modifiedSentence;
                      modifiedSentence = FromServer.readLine();
                 }
        
        
   

        for(int i=0;i<19;i++)
        {
            if(booking[i]==3)
            {
                Ticket_Reservation_System.root = FXMLLoader.load(getClass().getResource("THEATREWARNING.fxml")); 
        
           Scene scene = new Scene(Ticket_Reservation_System.root);
           
         Ticket_Reservation_System.window.setScene(scene);
         
         Ticket_Reservation_System.window.setTitle("                                                                            WELCOME");

        
            }
        }
       
            if(a[3]==0)
      {
          a[3]=1;
      }
      else
          a[3]=0;
    }

    @FXML
    private void HANDLED(ActionEvent event) throws IOException {
                                 sentence="SEATCHECK";
                ToServer.writeBytes(sentence + '\n');
                sentence=S1+S2+S3+S4+S5;
                 ToServer.writeBytes(sentence + '\n') ;
                  sentence="THEATRE";
                ToServer.writeBytes(sentence + '\n') ;
                
                 
                 modifiedSentence = FromServer.readLine();
                 int k=0;
                 while(!modifiedSentence.equals("-1"))
                 {
                     System.out.println(modifiedSentence);
                     booking[k]=Integer.parseInt(modifiedSentence);
                     k=k+1;
                     
                     //Seat=Seat+modifiedSentence;
                      modifiedSentence = FromServer.readLine();
                 }
        
        
   

        for(int i=0;i<19;i++)
        {
            if(booking[i]==4)
            {
                Ticket_Reservation_System.root = FXMLLoader.load(getClass().getResource("THEATREWARNING.fxml")); 
        
           Scene scene = new Scene(Ticket_Reservation_System.root);
           
         Ticket_Reservation_System.window.setScene(scene);
         
         Ticket_Reservation_System.window.setTitle("                                                                            WELCOME");

        
            }
        }
       
            if(a[4]==0)
      {
          a[4]=1;
      }
      else
          a[4]=0;
    }

    @FXML
    private void HANDLE_E(ActionEvent event) throws IOException {
        
                                       sentence="SEATCHECK";
                ToServer.writeBytes(sentence + '\n');
                sentence=S1+S2+S3+S4+S5;
                 ToServer.writeBytes(sentence + '\n') ;
                  sentence="THEATRE";
                ToServer.writeBytes(sentence + '\n') ;
                
                 
                 modifiedSentence = FromServer.readLine();
                 int k=0;
                 while(!modifiedSentence.equals("-1"))
                 {
                     System.out.println(modifiedSentence);
                     booking[k]=Integer.parseInt(modifiedSentence);
                     k=k+1;
                     
                     //Seat=Seat+modifiedSentence;
                      modifiedSentence = FromServer.readLine();
                 }
        
        
   

        for(int i=0;i<19;i++)
        {
            if(booking[i]==5)
            {
                Ticket_Reservation_System.root = FXMLLoader.load(getClass().getResource("THEATREWARNING.fxml")); 
        
           Scene scene = new Scene(Ticket_Reservation_System.root);
           
         Ticket_Reservation_System.window.setScene(scene);
         
         Ticket_Reservation_System.window.setTitle("                                                                            WELCOME");

        
            }
        }
       
            if(a[5]==0)
      {
          a[5]=1;
      }
      else
          a[5]=0;
    }

    @FXML
    private void HANDLE_F(ActionEvent event) throws IOException {
        
                                       sentence="SEATCHECK";
                ToServer.writeBytes(sentence + '\n');
                sentence=S1+S2+S3+S4+S5;
                 ToServer.writeBytes(sentence + '\n') ;
                  sentence="THEATRE";
                ToServer.writeBytes(sentence + '\n') ;
                
                 
                 modifiedSentence = FromServer.readLine();
                 int k=0;
                 while(!modifiedSentence.equals("-1"))
                 {
                     System.out.println(modifiedSentence);
                     booking[k]=Integer.parseInt(modifiedSentence);
                     k=k+1;
                     
                     //Seat=Seat+modifiedSentence;
                      modifiedSentence = FromServer.readLine();
                 }
        
        
   

        for(int i=0;i<19;i++)
        {
            if(booking[i]==6)
            {
                Ticket_Reservation_System.root = FXMLLoader.load(getClass().getResource("THEATREWARNING.fxml")); 
        
           Scene scene = new Scene(Ticket_Reservation_System.root);
           
         Ticket_Reservation_System.window.setScene(scene);
         
         Ticket_Reservation_System.window.setTitle("                                                                            WELCOME");

        
            }
        }
       
            if(a[6]==0)
      {
          a[6]=1;
      }
      else
          a[6]=0;
    }

    @FXML
    private void HANDLE_G(ActionEvent event) throws IOException {
        
                                       sentence="SEATCHECK";
                ToServer.writeBytes(sentence + '\n');
                sentence=S1+S2+S3+S4+S5;
                 ToServer.writeBytes(sentence + '\n') ;
                  sentence="THEATRE";
                ToServer.writeBytes(sentence + '\n') ;
                
                 
                 modifiedSentence = FromServer.readLine();
                 int k=0;
                 while(!modifiedSentence.equals("-1"))
                 {
                     System.out.println(modifiedSentence);
                     booking[k]=Integer.parseInt(modifiedSentence);
                     k=k+1;
                     
                     //Seat=Seat+modifiedSentence;
                      modifiedSentence = FromServer.readLine();
                 }
        
        
   

        for(int i=0;i<19;i++)
        {
            if(booking[i]==7)
            {
                Ticket_Reservation_System.root = FXMLLoader.load(getClass().getResource("THEATREWARNING.fxml")); 
        
           Scene scene = new Scene(Ticket_Reservation_System.root);
           
         Ticket_Reservation_System.window.setScene(scene);
         
         Ticket_Reservation_System.window.setTitle("                                                                            WELCOME");

        
            }
        }
       
            if(a[7]==0)
      {
          a[7]=1;
      }
      else
          a[7]=0;
    }

    @FXML
    private void HANDLE_H(ActionEvent event) throws IOException {
        
                                       sentence="SEATCHECK";
                ToServer.writeBytes(sentence + '\n');
                sentence=S1+S2+S3+S4+S5;
                 ToServer.writeBytes(sentence + '\n') ;
                  sentence="THEATRE";
                ToServer.writeBytes(sentence + '\n') ;
                
                 
                 modifiedSentence = FromServer.readLine();
                 int k=0;
                 while(!modifiedSentence.equals("-1"))
                 {
                     System.out.println(modifiedSentence);
                     booking[k]=Integer.parseInt(modifiedSentence);
                     k=k+1;
                     
                     //Seat=Seat+modifiedSentence;
                      modifiedSentence = FromServer.readLine();
                 }
        
        
   

        for(int i=0;i<19;i++)
        {
            if(booking[i]==8)
            {
                Ticket_Reservation_System.root = FXMLLoader.load(getClass().getResource("THEATREWARNING.fxml")); 
        
           Scene scene = new Scene(Ticket_Reservation_System.root);
           
         Ticket_Reservation_System.window.setScene(scene);
         
         Ticket_Reservation_System.window.setTitle("                                                                            WELCOME");

        
            }
        }
       
            if(a[8]==0)
      {
          a[8]=1;
      }
      else
          a[8]=0;
    }

    @FXML
    private void HANDLE_I(ActionEvent event) throws IOException {
        
                                       sentence="SEATCHECK";
                ToServer.writeBytes(sentence + '\n');
                sentence=S1+S2+S3+S4+S5;
                 ToServer.writeBytes(sentence + '\n') ;
                  sentence="THEATRE";
                ToServer.writeBytes(sentence + '\n') ;
                
                 
                 modifiedSentence = FromServer.readLine();
                 int k=0;
                 while(!modifiedSentence.equals("-1"))
                 {
                     System.out.println(modifiedSentence);
                     booking[k]=Integer.parseInt(modifiedSentence);
                     k=k+1;
                     
                     //Seat=Seat+modifiedSentence;
                      modifiedSentence = FromServer.readLine();
                 }
        
        
   

        for(int i=0;i<19;i++)
        {
            if(booking[i]==9)
            {
                Ticket_Reservation_System.root = FXMLLoader.load(getClass().getResource("THEATREWARNING.fxml")); 
        
           Scene scene = new Scene(Ticket_Reservation_System.root);
           
         Ticket_Reservation_System.window.setScene(scene);
         
         Ticket_Reservation_System.window.setTitle("                                                                            WELCOME");

        
            }
        }
       
            if(a[9]==0)
      {
          a[9]=1;
      }
      else
          a[9]=0;
    }

    @FXML
    private void HANDLE_J(ActionEvent event) throws IOException {
        
                                       sentence="SEATCHECK";
                ToServer.writeBytes(sentence + '\n');
                sentence=S1+S2+S3+S4+S5;
                 ToServer.writeBytes(sentence + '\n') ;
                  sentence="THEATRE";
                ToServer.writeBytes(sentence + '\n') ;
                
                 
                 modifiedSentence = FromServer.readLine();
                 int k=0;
                 while(!modifiedSentence.equals("-1"))
                 {
                     System.out.println(modifiedSentence);
                     booking[k]=Integer.parseInt(modifiedSentence);
                     k=k+1;
                     
                     //Seat=Seat+modifiedSentence;
                      modifiedSentence = FromServer.readLine();
                 }
        
        
   

        for(int i=0;i<19;i++)
        {
            if(booking[i]==10)
            {
                Ticket_Reservation_System.root = FXMLLoader.load(getClass().getResource("THEATREWARNING.fxml")); 
        
           Scene scene = new Scene(Ticket_Reservation_System.root);
           
         Ticket_Reservation_System.window.setScene(scene);
         
         Ticket_Reservation_System.window.setTitle("                                                                            WELCOME");

        
            }
        }
       
            if(a[10]==0)
      {
          a[10]=1;
      }
      else
          a[10]=0;
    }

    @FXML
    private void HANDLE_K(ActionEvent event) throws IOException {
        
                                       sentence="SEATCHECK";
                ToServer.writeBytes(sentence + '\n');
                sentence=S1+S2+S3+S4+S5;
                 ToServer.writeBytes(sentence + '\n') ;
                  sentence="THEATRE";
                ToServer.writeBytes(sentence + '\n') ;
                
                 
                 modifiedSentence = FromServer.readLine();
                 int k=0;
                 while(!modifiedSentence.equals("-1"))
                 {
                     System.out.println(modifiedSentence);
                     booking[k]=Integer.parseInt(modifiedSentence);
                     k=k+1;
                     
                     //Seat=Seat+modifiedSentence;
                      modifiedSentence = FromServer.readLine();
                 }
        
        
   

        for(int i=0;i<19;i++)
        {
            if(booking[i]==11)
            {
                Ticket_Reservation_System.root = FXMLLoader.load(getClass().getResource("THEATREWARNING.fxml")); 
        
           Scene scene = new Scene(Ticket_Reservation_System.root);
           
         Ticket_Reservation_System.window.setScene(scene);
         
         Ticket_Reservation_System.window.setTitle("                                                                            WELCOME");

        
            }
        }
       
            if(a[11]==0)
      {
          a[11]=1;
      }
      else
          a[11]=0;
    }

    @FXML
    private void HANDLE_L(ActionEvent event) throws IOException {
        
                                       sentence="SEATCHECK";
                ToServer.writeBytes(sentence + '\n');
                sentence=S1+S2+S3+S4+S5;
                 ToServer.writeBytes(sentence + '\n') ;
                  sentence="THEATRE";
                ToServer.writeBytes(sentence + '\n') ;
                
                 
                 modifiedSentence = FromServer.readLine();
                 int k=0;
                 while(!modifiedSentence.equals("-1"))
                 {
                     System.out.println(modifiedSentence);
                     booking[k]=Integer.parseInt(modifiedSentence);
                     k=k+1;
                     
                     //Seat=Seat+modifiedSentence;
                      modifiedSentence = FromServer.readLine();
                 }
        
        
   

        for(int i=0;i<19;i++)
        {
            if(booking[i]==12)
            {
                Ticket_Reservation_System.root = FXMLLoader.load(getClass().getResource("THEATREWARNING.fxml")); 
        
           Scene scene = new Scene(Ticket_Reservation_System.root);
           
         Ticket_Reservation_System.window.setScene(scene);
         
         Ticket_Reservation_System.window.setTitle("                                                                            WELCOME");

        
            }
        }
       
            if(a[12]==0)
      {
          a[12]=1;
      }
      else
          a[12]=0;
    }

    @FXML
    private void HANDLE_M(ActionEvent event) throws IOException {
        
                                       sentence="SEATCHECK";
                ToServer.writeBytes(sentence + '\n');
                sentence=S1+S2+S3+S4+S5;
                 ToServer.writeBytes(sentence + '\n') ;
                  sentence="THEATRE";
                ToServer.writeBytes(sentence + '\n') ;
                
                 
                 modifiedSentence = FromServer.readLine();
                 int k=0;
                 while(!modifiedSentence.equals("-1"))
                 {
                     System.out.println(modifiedSentence);
                     booking[k]=Integer.parseInt(modifiedSentence);
                     k=k+1;
                     
                     //Seat=Seat+modifiedSentence;
                      modifiedSentence = FromServer.readLine();
                 }
        
        
   

        for(int i=0;i<19;i++)
        {
            if(booking[i]==13)
            {
                Ticket_Reservation_System.root = FXMLLoader.load(getClass().getResource("THEATREWARNING.fxml")); 
        
           Scene scene = new Scene(Ticket_Reservation_System.root);
           
         Ticket_Reservation_System.window.setScene(scene);
         
         Ticket_Reservation_System.window.setTitle("                                                                            WELCOME");

        
            }
        }
       
            if(a[13]==0)
      {
          a[13]=1;
      }
      else
          a[13]=0;
    }

    @FXML
    private void HANDLE_N(ActionEvent event) throws IOException {
        
                                       sentence="SEATCHECK";
                ToServer.writeBytes(sentence + '\n');
                sentence=S1+S2+S3+S4+S5;
                 ToServer.writeBytes(sentence + '\n') ;
                  sentence="THEATRE";
                ToServer.writeBytes(sentence + '\n') ;
                
                 
                 modifiedSentence = FromServer.readLine();
                 int k=0;
                 while(!modifiedSentence.equals("-1"))
                 {
                     System.out.println(modifiedSentence);
                     booking[k]=Integer.parseInt(modifiedSentence);
                     k=k+1;
                     
                     //Seat=Seat+modifiedSentence;
                      modifiedSentence = FromServer.readLine();
                 }
        
        
   

        for(int i=0;i<19;i++)
        {
            if(booking[i]==14)
            {
                Ticket_Reservation_System.root = FXMLLoader.load(getClass().getResource("THEATREWARNING.fxml")); 
        
           Scene scene = new Scene(Ticket_Reservation_System.root);
           
         Ticket_Reservation_System.window.setScene(scene);
         
         Ticket_Reservation_System.window.setTitle("                                                                            WELCOME");

        
            }
        }
       
            if(a[14]==0)
      {
          a[14]=1;
      }
      else
          a[14]=0;
    }

    @FXML
    private void HANDLE_O(ActionEvent event) throws IOException {
                                       sentence="SEATCHECK";
                ToServer.writeBytes(sentence + '\n');
                sentence=S1+S2+S3+S4+S5;
                 ToServer.writeBytes(sentence + '\n') ;
                  sentence="THEATRE";
                ToServer.writeBytes(sentence + '\n') ;
                
                 
                 modifiedSentence = FromServer.readLine();
                 int k=0;
                 while(!modifiedSentence.equals("-1"))
                 {
                     System.out.println(modifiedSentence);
                     booking[k]=Integer.parseInt(modifiedSentence);
                     k=k+1;
                     
                     //Seat=Seat+modifiedSentence;
                      modifiedSentence = FromServer.readLine();
                 }
        
        
   

        for(int i=0;i<19;i++)
        {
            if(booking[i]==15)
            {
                Ticket_Reservation_System.root = FXMLLoader.load(getClass().getResource("THEATREWARNING.fxml")); 
        
           Scene scene = new Scene(Ticket_Reservation_System.root);
           
         Ticket_Reservation_System.window.setScene(scene);
         
         Ticket_Reservation_System.window.setTitle("                                                                            WELCOME");

        
            }
        }
       
            if(a[15]==0)
      {
          a[15]=1;
      }
      else
          a[15]=0;
    }

    @FXML
    private void HANDLE_P(ActionEvent event) throws IOException {
        
                                       sentence="SEATCHECK";
                ToServer.writeBytes(sentence + '\n');
                sentence=S1+S2+S3+S4+S5;
                 ToServer.writeBytes(sentence + '\n') ;
                  sentence="THEATRE";
                ToServer.writeBytes(sentence + '\n') ;
                
                 
                 modifiedSentence = FromServer.readLine();
                 int k=0;
                 while(!modifiedSentence.equals("-1"))
                 {
                     System.out.println(modifiedSentence);
                     booking[k]=Integer.parseInt(modifiedSentence);
                     k=k+1;
                     
                     //Seat=Seat+modifiedSentence;
                      modifiedSentence = FromServer.readLine();
                 }
        
        
   

        for(int i=0;i<19;i++)
        {
            if(booking[i]==16)
            {
                Ticket_Reservation_System.root = FXMLLoader.load(getClass().getResource("THEATREWARNING.fxml")); 
        
           Scene scene = new Scene(Ticket_Reservation_System.root);
           
         Ticket_Reservation_System.window.setScene(scene);
         
         Ticket_Reservation_System.window.setTitle("                                                                            WELCOME");

        
            }
        }
       
            if(a[16]==0)
      {
          a[16]=1;
      }
      else
          a[16]=0;
    }

    @FXML
    private void HANDLE_Q(ActionEvent event) throws IOException {
        
                                       sentence="SEATCHECK";
                ToServer.writeBytes(sentence + '\n');
                sentence=S1+S2+S3+S4+S5;
                 ToServer.writeBytes(sentence + '\n') ;
                  sentence="THEATRE";
                ToServer.writeBytes(sentence + '\n') ;
                
                 
                 modifiedSentence = FromServer.readLine();
                 int k=0;
                 while(!modifiedSentence.equals("-1"))
                 {
                     System.out.println(modifiedSentence);
                     booking[k]=Integer.parseInt(modifiedSentence);
                     k=k+1;
                     
                     //Seat=Seat+modifiedSentence;
                      modifiedSentence = FromServer.readLine();
                 }
        
        
   

        for(int i=0;i<19;i++)
        {
            if(booking[i]==17)
            {
                Ticket_Reservation_System.root = FXMLLoader.load(getClass().getResource("THEATREWARNING.fxml")); 
        
           Scene scene = new Scene(Ticket_Reservation_System.root);
           
         Ticket_Reservation_System.window.setScene(scene);
         
         Ticket_Reservation_System.window.setTitle("                                                                            WELCOME");

        
            }
        }
       
            if(a[17]==0)
      {
          a[17]=1;
      }
      else
          a[17]=0;
    }

    @FXML
    private void HANDLE_R(ActionEvent event) throws IOException {
        
                                       sentence="SEATCHECK";
                ToServer.writeBytes(sentence + '\n');
                sentence=S1+S2+S3+S4+S5;
                 ToServer.writeBytes(sentence + '\n') ;
                  sentence="THEATRE";
                ToServer.writeBytes(sentence + '\n') ;
                
                 
                 modifiedSentence = FromServer.readLine();
                 int k=0;
                 while(!modifiedSentence.equals("-1"))
                 {
                     System.out.println(modifiedSentence);
                     booking[k]=Integer.parseInt(modifiedSentence);
                     k=k+1;
                     
                     //Seat=Seat+modifiedSentence;
                      modifiedSentence = FromServer.readLine();
                 }
        
        
   

        for(int i=0;i<19;i++)
        {
            if(booking[i]==18)
            {
                Ticket_Reservation_System.root = FXMLLoader.load(getClass().getResource("THEATREWARNING.fxml")); 
        
           Scene scene = new Scene(Ticket_Reservation_System.root);
           
         Ticket_Reservation_System.window.setScene(scene);
         
         Ticket_Reservation_System.window.setTitle("                                                                            WELCOME");

        
            }
        }
       
            if(a[18]==0)
      {
          a[18]=1;
      }
      else
          a[18]=0;
    }
    
}
