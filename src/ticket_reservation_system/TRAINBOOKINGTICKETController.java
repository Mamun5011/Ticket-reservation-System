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
public class TRAINBOOKINGTICKETController implements Initializable {

    /**
     * Initializes the controller class.
     */
          int []a=new int[25];
      int []booking=new int[25];
      
               String sentence=null;
         String modifiedSentence=null;
         String Seat="";
         BufferedReader  FromServer;
         DataOutputStream  ToServer;
    @FXML
    private Label MESSAGE;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
            for(int i=0;i<25;i++)
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
                    sentence="TRAIN";
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
        
         Ticket_Reservation_System.root = FXMLLoader.load(getClass().getResource("TRAIN.fxml")); 
        
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
                for(int i=1;i<25;i++)
        {
            if(a[i]==1)
            {
              g=g+1;
            }
        }
        sentence=""+g;
        ToServer.writeBytes(sentence + '\n');
       sentence="TRAIN";
        ToServer.writeBytes(sentence + '\n');
       modifiedSentence = FromServer.readLine();
       if(modifiedSentence.equals("null1"))MESSAGE.setText("You Have Selected More Tickets");
       else
       {
          sentence=S1+S2+S3+S4+S5;
           ToServer.writeBytes(sentence + '\n') ;//sending busname and schedule
                   for(int i=1;i<25;i++)
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
              sentence="TRAIN";
        ToServer.writeBytes(sentence + '\n');
         sentence=S1+S2+S3+S4+S5;
         ToServer.writeBytes(sentence + '\n') ;
        for(int i=1;i<25;i++)
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
    private void HandleA(ActionEvent event) throws IOException {
        
                             sentence="SEATCHECK";
                ToServer.writeBytes(sentence + '\n');
                sentence=S1+S2+S3+S4+S5;
                 ToServer.writeBytes(sentence + '\n') ;
                  sentence="TRAIN";
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
        
        
   

        for(int i=0;i<25;i++)
        {
            if(booking[i]==1)
            {
                Ticket_Reservation_System.root = FXMLLoader.load(getClass().getResource("WARNINGTRAIN.fxml")); 
        
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
    private void HandleB(ActionEvent event) throws IOException {
        
                                     sentence="SEATCHECK";
                ToServer.writeBytes(sentence + '\n');
                sentence=S1+S2+S3+S4+S5;
                 ToServer.writeBytes(sentence + '\n') ;
                  sentence="TRAIN";
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
        
        
   

        for(int i=0;i<25;i++)
        {
            if(booking[i]==2)
            {
                Ticket_Reservation_System.root = FXMLLoader.load(getClass().getResource("WARNINGTRAIN.fxml")); 
        
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
    private void HandleC(ActionEvent event) throws IOException {
        
                  sentence="SEATCHECK";
                ToServer.writeBytes(sentence + '\n');
                sentence=S1+S2+S3+S4+S5;
                 ToServer.writeBytes(sentence + '\n') ;
                  sentence="TRAIN";
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
        
        
   

        for(int i=0;i<25;i++)
        {
            if(booking[i]==3)
            {
                Ticket_Reservation_System.root = FXMLLoader.load(getClass().getResource("WARNINGTRAIN.fxml")); 
        
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
    private void HandleD(ActionEvent event) throws IOException {
        
                         sentence="SEATCHECK";
                ToServer.writeBytes(sentence + '\n');
                sentence=S1+S2+S3+S4+S5;
                 ToServer.writeBytes(sentence + '\n') ;
                  sentence="TRAIN";
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
        
        
   

        for(int i=0;i<25;i++)
        {
            if(booking[i]==4)
            {
                Ticket_Reservation_System.root = FXMLLoader.load(getClass().getResource("WARNINGTRAIN.fxml")); 
        
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
    private void HandleE(ActionEvent event) throws IOException {
        
                         sentence="SEATCHECK";
                ToServer.writeBytes(sentence + '\n');
                sentence=S1+S2+S3+S4+S5;
                 ToServer.writeBytes(sentence + '\n') ;
                  sentence="TRAIN";
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
        
        
   

        for(int i=0;i<25;i++)
        {
            if(booking[i]==5)
            {
                Ticket_Reservation_System.root = FXMLLoader.load(getClass().getResource("WARNINGTRAIN.fxml")); 
        
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
    private void HandleF(ActionEvent event) throws IOException {
                         sentence="SEATCHECK";
                ToServer.writeBytes(sentence + '\n');
                sentence=S1+S2+S3+S4+S5;
                 ToServer.writeBytes(sentence + '\n') ;
                  sentence="TRAIN";
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
        
        
   

        for(int i=0;i<25;i++)
        {
            if(booking[i]==6)
            {
                Ticket_Reservation_System.root = FXMLLoader.load(getClass().getResource("WARNINGTRAIN.fxml")); 
        
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
    private void HandleM(ActionEvent event) throws IOException {
        
                           sentence="SEATCHECK";
                ToServer.writeBytes(sentence + '\n');
                sentence=S1+S2+S3+S4+S5;
                 ToServer.writeBytes(sentence + '\n') ;
                  sentence="TRAIN";
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
        
        
   

        for(int i=0;i<25;i++)
        {
            if(booking[i]==7)
            {
                Ticket_Reservation_System.root = FXMLLoader.load(getClass().getResource("WARNINGTRAIN.fxml")); 
        
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
    private void HandleN(ActionEvent event) throws IOException {
        
                           sentence="SEATCHECK";
                ToServer.writeBytes(sentence + '\n');
                sentence=S1+S2+S3+S4+S5;
                 ToServer.writeBytes(sentence + '\n') ;
                  sentence="TRAIN";
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
        
        
   

        for(int i=0;i<25;i++)
        {
            if(booking[i]==8)
            {
                Ticket_Reservation_System.root = FXMLLoader.load(getClass().getResource("WARNINGTRAIN.fxml")); 
        
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
    private void HandleO(ActionEvent event) throws IOException {
        
                           sentence="SEATCHECK";
                ToServer.writeBytes(sentence + '\n');
                sentence=S1+S2+S3+S4+S5;
                 ToServer.writeBytes(sentence + '\n') ;
                  sentence="TRAIN";
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
        
        
   

        for(int i=0;i<25;i++)
        {
            if(booking[i]==9)
            {
                Ticket_Reservation_System.root = FXMLLoader.load(getClass().getResource("WARNINGTRAIN.fxml")); 
        
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
    private void HandleP(ActionEvent event) throws IOException {
        
                           sentence="SEATCHECK";
                ToServer.writeBytes(sentence + '\n');
                sentence=S1+S2+S3+S4+S5;
                 ToServer.writeBytes(sentence + '\n') ;
                  sentence="TRAIN";
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
        
        
   

        for(int i=0;i<25;i++)
        {
            if(booking[i]==10)
            {
                Ticket_Reservation_System.root = FXMLLoader.load(getClass().getResource("WARNINGTRAIN.fxml")); 
        
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
    private void HandleQ(ActionEvent event) throws IOException {
        
                           sentence="SEATCHECK";
                ToServer.writeBytes(sentence + '\n');
                sentence=S1+S2+S3+S4+S5;
                 ToServer.writeBytes(sentence + '\n') ;
                  sentence="TRAIN";
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
        
        
   

        for(int i=0;i<25;i++)
        {
            if(booking[i]==11)
            {
                Ticket_Reservation_System.root = FXMLLoader.load(getClass().getResource("WARNINGTRAIN.fxml")); 
        
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
    private void HandleR(ActionEvent event) throws IOException {
        
                           sentence="SEATCHECK";
                ToServer.writeBytes(sentence + '\n');
                sentence=S1+S2+S3+S4+S5;
                 ToServer.writeBytes(sentence + '\n') ;
                  sentence="TRAIN";
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
        
        
   

        for(int i=0;i<25;i++)
        {
            if(booking[i]==12)
            {
                Ticket_Reservation_System.root = FXMLLoader.load(getClass().getResource("WARNINGTRAIN.fxml")); 
        
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
    private void HandleG(ActionEvent event) throws IOException {
        
                           sentence="SEATCHECK";
                ToServer.writeBytes(sentence + '\n');
                sentence=S1+S2+S3+S4+S5;
                 ToServer.writeBytes(sentence + '\n') ;
                  sentence="TRAIN";
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
        
        
   

        for(int i=0;i<25;i++)
        {
            if(booking[i]==13)
            {
                Ticket_Reservation_System.root = FXMLLoader.load(getClass().getResource("WARNINGTRAIN.fxml")); 
        
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
    private void HandleH(ActionEvent event) throws IOException {
        
                           sentence="SEATCHECK";
                ToServer.writeBytes(sentence + '\n');
                sentence=S1+S2+S3+S4+S5;
                 ToServer.writeBytes(sentence + '\n') ;
                  sentence="TRAIN";
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
        
        
   

        for(int i=0;i<25;i++)
        {
            if(booking[i]==14)
            {
                Ticket_Reservation_System.root = FXMLLoader.load(getClass().getResource("WARNINGTRAIN.fxml")); 
        
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
    private void HandleI(ActionEvent event) throws IOException {
                           sentence="SEATCHECK";
                ToServer.writeBytes(sentence + '\n');
                sentence=S1+S2+S3+S4+S5;
                 ToServer.writeBytes(sentence + '\n') ;
                  sentence="TRAIN";
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
        
        
   

        for(int i=0;i<25;i++)
        {
            if(booking[i]==15)
            {
                Ticket_Reservation_System.root = FXMLLoader.load(getClass().getResource("WARNINGTRAIN.fxml")); 
        
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
    private void HandleJ(ActionEvent event) throws IOException {
                           sentence="SEATCHECK";
                ToServer.writeBytes(sentence + '\n');
                sentence=S1+S2+S3+S4+S5;
                 ToServer.writeBytes(sentence + '\n') ;
                  sentence="TRAIN";
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
        
        
   

        for(int i=0;i<25;i++)
        {
            if(booking[i]==16)
            {
                Ticket_Reservation_System.root = FXMLLoader.load(getClass().getResource("WARNINGTRAIN.fxml")); 
        
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
    private void HandleK(ActionEvent event) throws IOException {
                           sentence="SEATCHECK";
                ToServer.writeBytes(sentence + '\n');
                sentence=S1+S2+S3+S4+S5;
                 ToServer.writeBytes(sentence + '\n') ;
                  sentence="TRAIN";
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
        
        
   

        for(int i=0;i<25;i++)
        {
            if(booking[i]==17)
            {
                Ticket_Reservation_System.root = FXMLLoader.load(getClass().getResource("WARNINGTRAIN.fxml")); 
        
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
    private void HandleL(ActionEvent event) throws IOException {
                           sentence="SEATCHECK";
                ToServer.writeBytes(sentence + '\n');
                sentence=S1+S2+S3+S4+S5;
                 ToServer.writeBytes(sentence + '\n') ;
                  sentence="TRAIN";
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
        
        
   

        for(int i=0;i<25;i++)
        {
            if(booking[i]==18)
            {
                Ticket_Reservation_System.root = FXMLLoader.load(getClass().getResource("WARNINGTRAIN.fxml")); 
        
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

    @FXML
    private void HandleS(ActionEvent event) throws IOException {
                           sentence="SEATCHECK";
                ToServer.writeBytes(sentence + '\n');
                sentence=S1+S2+S3+S4+S5;
                 ToServer.writeBytes(sentence + '\n') ;
                  sentence="TRAIN";
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
        
        
   

        for(int i=0;i<25;i++)
        {
            if(booking[i]==19)
            {
                Ticket_Reservation_System.root = FXMLLoader.load(getClass().getResource("WARNINGTRAIN.fxml")); 
        
           Scene scene = new Scene(Ticket_Reservation_System.root);
           
         Ticket_Reservation_System.window.setScene(scene);
         
         Ticket_Reservation_System.window.setTitle("                                                                            WELCOME");

        
            }
        }
       
            if(a[19]==0)
      {
          a[19]=1;
      }
      else
          a[19]=0;
      
    }

    @FXML
    private void HandleT(ActionEvent event) throws IOException {
                           sentence="SEATCHECK";
                ToServer.writeBytes(sentence + '\n');
                sentence=S1+S2+S3+S4+S5;
                 ToServer.writeBytes(sentence + '\n') ;
                  sentence="TRAIN";
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
        
        
   

        for(int i=0;i<25;i++)
        {
            if(booking[i]==20)
            {
                Ticket_Reservation_System.root = FXMLLoader.load(getClass().getResource("WARNINGTRAIN.fxml")); 
        
           Scene scene = new Scene(Ticket_Reservation_System.root);
           
         Ticket_Reservation_System.window.setScene(scene);
         
         Ticket_Reservation_System.window.setTitle("                                                                            WELCOME");

        
            }
        }
       
            if(a[20]==0)
      {
          a[20]=1;
      }
      else
          a[20]=0;
      
    }

    @FXML
    private void HandleU(ActionEvent event) throws IOException {
                           sentence="SEATCHECK";
                ToServer.writeBytes(sentence + '\n');
                sentence=S1+S2+S3+S4+S5;
                 ToServer.writeBytes(sentence + '\n') ;
                  sentence="TRAIN";
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
        
        
   

        for(int i=0;i<25;i++)
        {
            if(booking[i]==21)
            {
                Ticket_Reservation_System.root = FXMLLoader.load(getClass().getResource("WARNINGTRAIN.fxml")); 
        
           Scene scene = new Scene(Ticket_Reservation_System.root);
           
         Ticket_Reservation_System.window.setScene(scene);
         
         Ticket_Reservation_System.window.setTitle("                                                                            WELCOME");

        
            }
        }
       
            if(a[21]==0)
      {
          a[21]=1;
      }
      else
          a[21]=0;
      
    }

    @FXML
    private void HandleV(ActionEvent event) throws IOException {
                           sentence="SEATCHECK";
                ToServer.writeBytes(sentence + '\n');
                sentence=S1+S2+S3+S4+S5;
                 ToServer.writeBytes(sentence + '\n') ;
                  sentence="TRAIN";
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
        
        
   

        for(int i=0;i<25;i++)
        {
            if(booking[i]==22)
            {
                Ticket_Reservation_System.root = FXMLLoader.load(getClass().getResource("WARNINGTRAIN.fxml")); 
        
           Scene scene = new Scene(Ticket_Reservation_System.root);
           
         Ticket_Reservation_System.window.setScene(scene);
         
         Ticket_Reservation_System.window.setTitle("                                                                            WELCOME");

        
            }
        }
       
            if(a[22]==0)
      {
          a[22]=1;
      }
      else
          a[22]=0;
      
    }

    @FXML
    private void HandleW(ActionEvent event) throws IOException {
                           sentence="SEATCHECK";
                ToServer.writeBytes(sentence + '\n');
                sentence=S1+S2+S3+S4+S5;
                 ToServer.writeBytes(sentence + '\n') ;
                  sentence="TRAIN";
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
        
        
   

        for(int i=0;i<25;i++)
        {
            if(booking[i]==23)
            {
                Ticket_Reservation_System.root = FXMLLoader.load(getClass().getResource("WARNINGTRAIN.fxml")); 
        
           Scene scene = new Scene(Ticket_Reservation_System.root);
           
         Ticket_Reservation_System.window.setScene(scene);
         
         Ticket_Reservation_System.window.setTitle("                                                                            WELCOME");

        
            }
        }
       
            if(a[23]==0)
      {
          a[23]=1;
      }
      else
          a[23]=0;
      
    }

    @FXML
    private void HandleX(ActionEvent event) throws IOException {
        
                           sentence="SEATCHECK";
                ToServer.writeBytes(sentence + '\n');
                sentence=S1+S2+S3+S4+S5;
                 ToServer.writeBytes(sentence + '\n') ;
                  sentence="TRAIN";
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
        
        
   

        for(int i=0;i<25;i++)
        {
            if(booking[i]==24)
            {
                Ticket_Reservation_System.root = FXMLLoader.load(getClass().getResource("WARNINGTRAIN.fxml")); 
        
           Scene scene = new Scene(Ticket_Reservation_System.root);
           
         Ticket_Reservation_System.window.setScene(scene);
         
         Ticket_Reservation_System.window.setTitle("                                                                            WELCOME");

        
            }
        }
       
            if(a[24]==0)
      {
          a[24]=1;
      }
      else
          a[24]=0;
      
    }
    
}
