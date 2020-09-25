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
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Service;
import static ticket_reservation_system.Ticket_Reservation_System.stopped;

/**
 *
 * @author Mamun
 */
public class SERVER  {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
     int workerThreadCount = 0;
        int id = 1;
        ServerSocket welcomeSocket = new ServerSocket(6789);
        while(true)
        {
            Socket connectionSocket = welcomeSocket.accept();
            WorkerThread wt = new WorkerThread(connectionSocket,id);
            Thread t = new Thread(wt);
            t.start();
            workerThreadCount++;
            System.out.println("Client [" + id + "] is now connected. No. of worker threads = " + workerThreadCount);
            id++;
        }

        
    }
    
    
  public static  class WorkerThread implements Runnable
{
      /*
    private Socket connectionSocket;
    private int id;
    public WorkerThread(Socket ConnectionSocket, int id) 
    {
        this.connectionSocket=ConnectionSocket;
        this.id=id;
    }
    public void run()
    {
        String clientSentence;
        String capitalizedSentence;
        while(true)
        {
            try
            {
                DataOutputStream outToServer = new DataOutputStream(connectionSocket.getOutputStream());
                BufferedReader inFromServer = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));    
                clientSentence = inFromServer.readLine();
                capitalizedSentence = clientSentence.toUpperCase();
                outToServer.writeBytes(capitalizedSentence + '\n');
                
            }
            catch(Exception e)
            {
                
            }
        }
    }
    */
      
            String clientSentence="I";
            String Result="I" ;
            String P ="I";
            String k="I";
            String CARD="I";
      
       int id;
        Socket connectionSocket;
        public WorkerThread(Socket connectionSocket,int id) {
            this.id = id;
            this.connectionSocket=connectionSocket;
        }

       @Override
        public void run(){
            
           
          //  String clientSentence="I";
           // String Result="I" ;
           // String P ="I";
           // String k="I";
            
             DataOutputStream outToServer = null;
             BufferedReader inFromServer =null;
           try {
               outToServer = new DataOutputStream(connectionSocket.getOutputStream());
            inFromServer = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

           } catch (IOException ex) {
               System.out.println("Interrupted");
           }
      
            while(true)
            {
                
                try{
                    clientSentence= inFromServer.readLine();
                }catch(Exception E)
                {
                    
                }
                    
                    if(stopped) {
                      try {
                            connectionSocket.close();
                        
                        } catch (IOException ex) {
                            
                        }
                      
                        break;
                    }
                         
                    
                  if(clientSentence.equals("U"))
                  {
                    try {
                        clientSentence= inFromServer.readLine();
                    } catch (IOException ex) {
                        Logger.getLogger(SERVER.class.getName()).log(Level.SEVERE, null, ex);
                    }
                      
                      if(clientSentence.equals(""))
                      {
                          Result="null1";//Enter Your Name
                        try {
                            outToServer.writeBytes(Result+ '\n');
                        } catch (IOException ex) {
                            Logger.getLogger(SERVER.class.getName()).log(Level.SEVERE, null, ex);
                        }
                          
                      }
                      else
                      {
                           File file=new File("Client/"+clientSentence+".txt");
                            if(file.exists())
                            {
                            Result="okay";
                               try {
                                   outToServer.writeBytes(Result+ '\n');
                               } catch (IOException ex) {
                                   Logger.getLogger(SERVER.class.getName()).log(Level.SEVERE, null, ex);
                               }
                            }
                            else
                            {
                          Result="null2"; //Invalid Username
                               try {
                                   outToServer.writeBytes(Result+ '\n');
                               } catch (IOException ex) {
                                   Logger.getLogger(SERVER.class.getName()).log(Level.SEVERE, null, ex);
                               }
                            }
                            
                      }
    
                  } 
                  
                  
                 else if(clientSentence.equals("P"))
                 {
                    try {
                        clientSentence= inFromServer.readLine();//taking password
                    } catch (IOException ex) {
                        Logger.getLogger(SERVER.class.getName()).log(Level.SEVERE, null, ex);
                    }
                   String v;
                   v=clientSentence;
                    try {
                        clientSentence= inFromServer.readLine();//taking Username
                    } catch (IOException ex) {
                        Logger.getLogger(SERVER.class.getName()).log(Level.SEVERE, null, ex);
                    }
                                       
                      if(v.equals(""))
                      {
                          Result="null3";//enter your Password
                        try {
                            outToServer.writeBytes(Result+ '\n');
                        } catch (IOException ex) {
                            Logger.getLogger(SERVER.class.getName()).log(Level.SEVERE, null, ex);
                        }
                          
                      }
                  
                  else
                      {
                  
               File file=new File("Client/"+clientSentence+".txt");
               FileReader f = null;
                        try {
                            f = new FileReader(file); //or give the path of file
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(SERVER.class.getName()).log(Level.SEVERE, null, ex);
                        }
               BufferedReader fw=new BufferedReader(f);
                        try {
                            k=fw.readLine();
                        } catch (IOException ex) {
                            Logger.getLogger(SERVER.class.getName()).log(Level.SEVERE, null, ex);
                        }
               
                int i=1;
               while(k!=null)
             {
                if(i==4)
           {
               P=k;
              break;
           }
       
                   try {
                       k=fw.readLine();
                   } catch (IOException ex) {
                   }
           i=i+1;
       }
                        try {
                            fw.close();
                        } catch (IOException ex) {
                            Logger.getLogger(SERVER.class.getName()).log(Level.SEVERE, null, ex);
                        }
           if(P.equals(v))
           {
               Result="okay"; 
                   try {
                       outToServer.writeBytes(Result+ '\n');
                   } catch (IOException ex) {
                       Logger.getLogger(SERVER.class.getName()).log(Level.SEVERE, null, ex);
                   }
           }
           
           else
           {
               Result="null4";  //Invalid password
                   try {
                       outToServer.writeBytes(Result+ '\n');
                   } catch (IOException ex) {
                       Logger.getLogger(SERVER.class.getName()).log(Level.SEVERE, null, ex);
                   }
           }
                
                      }  
        }         
                  
              else if(clientSentence.equals("S"))
                  {
    
                      
                    try {
                        clientSentence= inFromServer.readLine();
                    } catch (IOException ex) {
                        System.out.println("Error");  
                    }
                File file=new File("Client/"+clientSentence+".txt");
                File filePath=new File("Client");
           
                if(file.exists())
                {
                    Result="null1";
                        try {
                            outToServer.writeBytes(Result+ '\n');// Username  already exists
                        } catch (IOException ex) {
                            Logger.getLogger(SERVER.class.getName()).log(Level.SEVERE, null, ex);
                        }
                } 
                else
                {
                      Result="okay";
                        try {
                            outToServer.writeBytes(Result+ '\n');
                        } catch (IOException ex) {
                        }
                      
          filePath.mkdir();
                        try {
                            file.createNewFile();
                        } catch (IOException ex) {
                            Logger.getLogger(SERVER.class.getName()).log(Level.SEVERE, null, ex);
                        }
                     FileWriter m = null;
                        try {
                            m = new FileWriter(file);
                        } catch (IOException ex) {
                            Logger.getLogger(SERVER.class.getName()).log(Level.SEVERE, null, ex);
                        }
       BufferedWriter fw=new BufferedWriter(m);
                        try {
                            fw.write("Name:"+"\n");
                        } catch (IOException ex) {
                            Logger.getLogger(SERVER.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            clientSentence= inFromServer.readLine();
                        } catch (IOException ex) {
                            Logger.getLogger(SERVER.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            fw.write( clientSentence+"\n");
                        } catch (IOException ex) {
                            Logger.getLogger(SERVER.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            fw.write("Password:"+"\n");
                        } catch (IOException ex) {
                            Logger.getLogger(SERVER.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            clientSentence= inFromServer.readLine();
                        } catch (IOException ex) {
                            Logger.getLogger(SERVER.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            fw.write( clientSentence+"\n");
                        } catch (IOException ex) {
                            Logger.getLogger(SERVER.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            fw.write("Card no:"+"\n");
                        } catch (IOException ex) {
                            Logger.getLogger(SERVER.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            clientSentence= inFromServer.readLine();//here create a file card number
                           CARD=clientSentence;
                            
                            
                            
                        } catch (IOException ex) {
                            Logger.getLogger(SERVER.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            fw.write( clientSentence+"\n");
                        } catch (IOException ex) {
                            Logger.getLogger(SERVER.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            fw.write("Gender:"+"\n");
                        } catch (IOException ex) {
                            Logger.getLogger(SERVER.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            clientSentence= inFromServer.readLine();
                        } catch (IOException ex) {
                            Logger.getLogger(SERVER.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            fw.write( clientSentence+"\n");
                        } catch (IOException ex) {
                            Logger.getLogger(SERVER.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            fw.write("Address:"+"\n");
                        } catch (IOException ex) {
                            Logger.getLogger(SERVER.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            clientSentence= inFromServer.readLine();
                        } catch (IOException ex) {
                            Logger.getLogger(SERVER.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            fw.write( clientSentence+"\n");
                        } catch (IOException ex) {
                            Logger.getLogger(SERVER.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            fw.write("Contact Number:"+"\n");
                        } catch (IOException ex) {
                            Logger.getLogger(SERVER.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            clientSentence= inFromServer.readLine();
                        } catch (IOException ex) {
                            Logger.getLogger(SERVER.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            fw.write( clientSentence+"\n");
                        } catch (IOException ex) {
                            Logger.getLogger(SERVER.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            fw.write("Email:"+"\n");
                        } catch (IOException ex) {
                            Logger.getLogger(SERVER.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            clientSentence= inFromServer.readLine();
                        } catch (IOException ex) {
                            Logger.getLogger(SERVER.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            fw.write( clientSentence+"\n");
                        } catch (IOException ex) {
                            Logger.getLogger(SERVER.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            fw.write("Age:"+"\n");
                        } catch (IOException ex) {
                            Logger.getLogger(SERVER.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            clientSentence= inFromServer.readLine();
                        } catch (IOException ex) {
                            Logger.getLogger(SERVER.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            fw.write( clientSentence+"\n");
                        } catch (IOException ex) {
                            Logger.getLogger(SERVER.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        try {
                            fw.write("Date of Birth:"+"\n");
                        } catch (IOException ex) {
                            Logger.getLogger(SERVER.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            clientSentence= inFromServer.readLine();
                        } catch (IOException ex) {
                            Logger.getLogger(SERVER.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            fw.write( clientSentence+"\n");
                        } catch (IOException ex) {
                            Logger.getLogger(SERVER.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            clientSentence= inFromServer.readLine();
                        } catch (IOException ex) {
                            Logger.getLogger(SERVER.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            fw.write( clientSentence+"\n");
                        } catch (IOException ex) {
                            Logger.getLogger(SERVER.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            clientSentence= inFromServer.readLine();
                        } catch (IOException ex) {
                            Logger.getLogger(SERVER.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            fw.write( clientSentence+"\n");
                        } catch (IOException ex) {
                            Logger.getLogger(SERVER.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            fw.write("Occupation:"+"\n");
                        } catch (IOException ex) {
                            Logger.getLogger(SERVER.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            clientSentence= inFromServer.readLine();
                        } catch (IOException ex) {
                            Logger.getLogger(SERVER.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            fw.write( clientSentence+"\n");
                        } catch (IOException ex) {
                            Logger.getLogger(SERVER.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            fw.write("Nationality:"+"\n");
                        } catch (IOException ex) {
                            Logger.getLogger(SERVER.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            clientSentence= inFromServer.readLine();
                        } catch (IOException ex) {
                            Logger.getLogger(SERVER.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            fw.write( clientSentence+"\n");
                        } catch (IOException ex) {
                            Logger.getLogger(SERVER.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            clientSentence= inFromServer.readLine();
                        } catch (IOException ex) {
                            Logger.getLogger(SERVER.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            fw.write( clientSentence+"\n");
                        } catch (IOException ex) {
                            Logger.getLogger(SERVER.class.getName()).log(Level.SEVERE, null, ex);
                        }
        
                        try {
                            fw.close();
                        } catch (IOException ex) {
                            Logger.getLogger(SERVER.class.getName()).log(Level.SEVERE, null, ex);
                        }
       
       
       
                }
                  
                File m=new File("CARD/"+CARD+".txt");
                File path=new File("CARD");
                  
                        
                    try {
                        path.mkdir();
                        file.createNewFile();
                        FileWriter f=new FileWriter(m);
                        BufferedWriter kw=new BufferedWriter(f);
                        kw.write("3000"+"\n");
                        kw.close();
                    } catch (IOException ex) {
                    }
                
                
                  }
                 else if(clientSentence.equals("SEATCHECK"))
                  {
                      String u = null;
                                    try {
                        clientSentence= inFromServer.readLine();//TAKING BUSNAME
                       u= clientSentence;  
                  
                    } catch (IOException ex) {
                        System.out.println("Error in reading");  
                    }
                    try {
                        clientSentence= inFromServer.readLine();
                    } catch (IOException ex) {
                        Logger.getLogger(SERVER.class.getName()).log(Level.SEVERE, null, ex);
                    }
                File file=new File(clientSentence+"/"+u+".txt");
                 
                File filePath=new File(clientSentence);
                 
                                                 
                if(file.exists())
                {
                      String k;                
                
                        try {
                         FileReader  f = new FileReader(file); //or give the path of file
                         BufferedReader fw=new BufferedReader(f);
                         k=fw.readLine();
                        
                                         
                         while(k!=null)
                              {
                              Result=k;
                        outToServer.writeBytes(Result+ '\n');            
                   try {
                       k=fw.readLine();
                   } catch (IOException ex) {}
       } 
                         Result="-1";//seat is already booked
                        outToServer.writeBytes(Result+ '\n');
                        fw.close();
                        //System.out.println("taking busname");

                  }                     catch (IOException ex) {}
                       
                }
              else
                        {
                   
                           try {
                               filePath.mkdir();
                               file.createNewFile();
                               
                               Result="-1";
                           outToServer.writeBytes(Result+ '\n');
                     } catch (IOException ex) {}
                                         
                }
                  }
                  
                   else if(clientSentence.equals("BOOKING"))
                  {
                      String h;
                    try {
                        clientSentence= inFromServer.readLine();
                    } catch (IOException ex) {
                    }
                      h=clientSentence;
                        try {

                        clientSentence= inFromServer.readLine();//TAKING fILENAME
                         File file=new File(h+"/"+clientSentence+".txt");
                        FileWriter fw=new FileWriter(file,true);
                        BufferedWriter f=new BufferedWriter(fw);
                         PrintWriter print=new PrintWriter(f);
                         clientSentence= inFromServer.readLine();
                         
                         
                         while(!clientSentence.equals("-1"))
                         {
                            print.print(clientSentence+ "\n");
                             clientSentence= inFromServer.readLine();
                             
                         }
                         print.close();
                    } catch (IOException ex) {
                        System.out.println("Error");  
                    }
                      
                  }
                  
                   else if(clientSentence.equals("CARDCHECK"))
                  {
                    try {
                        clientSentence= inFromServer.readLine();//get card number
                    } catch (IOException ex) {}
                    
                      File file=new File("CARD/"+clientSentence+".txt");
                      
                    try {
                        clientSentence= inFromServer.readLine();//get amount of card
                    } catch (IOException ex) {}
                    
                      if(file.exists())
                      {
                               
                        try {
                            FileReader f =new FileReader(file);
                            BufferedReader fw=new BufferedReader(f);
                            String t=fw.readLine();
                            int y=Integer.parseInt(t);
                             fw.close();
                            if(y<Integer.parseInt(clientSentence)*500)
                            {
                                Result="null1";
                            outToServer.writeBytes(Result+ '\n');
                            }
                            else
                            {
                                       Result="okay";
                            outToServer.writeBytes(Result+ '\n');
                            }
                           
                        } catch (IOException ex) {}
                        
                      }
                      else
                      {
                          
                           Result="null";
                        try {
                            outToServer.writeBytes(Result+ '\n');
                        } catch (IOException ex) {
                            Logger.getLogger(SERVER.class.getName()).log(Level.SEVERE, null, ex);
                        }
                                   
                  }

             
                }
                  
                       else if(clientSentence.equals("DEDUCTION"))
                  {  
                      
                    try {
                        clientSentence= inFromServer.readLine();//TAKING CARD FILE NAME
                        String j=clientSentence;
                          File file=new File("CARD/"+clientSentence+".txt");
                           FileReader f =new FileReader(file);
                            BufferedReader fw=new BufferedReader(f);
                            String t=fw.readLine();
                            int y=Integer.parseInt(t);
                            clientSentence= inFromServer.readLine();
                            y=y-(500*Integer.parseInt(clientSentence));
                             fw.close();
                             clientSentence= inFromServer.readLine();//TAKE PARAMETER OF CARD
                             if(y<0)
                             {
                                 Result="null1";
                            outToServer.writeBytes(Result+ '\n');

                             }
                             else
                             {
                          Result="okay";
                            outToServer.writeBytes(Result+ '\n');
                            FileWriter fr =new FileWriter(file);
                            BufferedWriter fr2=new BufferedWriter(fr);
                             fr2.write(""+y+ "\n");
                             fr2.close();
                             
                          File fL=new File("CARD/"+clientSentence+"/"+j+".txt");
                          File path=new File("CARD/"+clientSentence);
                         if(fL.exists())
                         {
                        FileWriter FW=new FileWriter(fL,true);
                        BufferedWriter F=new BufferedWriter(FW);
                         PrintWriter PRINT=new PrintWriter(F);
                             clientSentence= inFromServer.readLine();
                                PRINT.print(clientSentence+ "\n");
                              
                                clientSentence= inFromServer.readLine();
                                while(!clientSentence.equals("-1"))
                                {
                                    PRINT.print(clientSentence+ "\n");
                                    clientSentence= inFromServer.readLine();
                                }
                                Result="#";
                                  PRINT.print(Result+ "\n");
                               PRINT.close();
                             }
                               else
                         {
                             path.mkdir();
                             fL.createNewFile();
                              FileWriter FE =new FileWriter(fL);
                            BufferedWriter FR2=new BufferedWriter(FE);
                                clientSentence= inFromServer.readLine();
                                FR2.write(clientSentence+ "\n");
                                clientSentence= inFromServer.readLine();
                                while(!clientSentence.equals("-1"))
                                {
                                    FR2.write(clientSentence+ "\n");
                                    clientSentence= inFromServer.readLine();
                                }
                                   Result="#";
                                  FR2.write(Result+ "\n");
                            FR2.close();
                             //////
                         }
                                
                                
                             }
                             
                    } catch (IOException ex) {
                    }


                      
                  }
                  
                  else if(clientSentence.equals("CANCELBOOKING"))
                  {
                      
                      
                    try {
                        clientSentence= inFromServer.readLine();//Bus Or Train Or Theatre
                        String h=clientSentence;
                        clientSentence= inFromServer.readLine();//card number
                        String l=clientSentence;
                        File fL=new File("CARD/"+h+"/"+clientSentence+".txt");
                        FileReader D=new FileReader(fL);
                        BufferedReader DL=new BufferedReader(D);
                        String x;
                        int v=0;
                        int []a=new int [25];
                        for(int i=0;i<25;i++)a[i]=0;
                        Result=DL.readLine();
                        while(Result!=null)
                        {
                        
                           File fL2=new File(h+"/"+Result+".txt");
                          FileReader D2=new FileReader(fL2);
                        BufferedReader DL2=new BufferedReader(D2);
                        x=DL2.readLine();
                        int i=0;
                        while(x!=null)
                        {
                            a[i]=Integer.parseInt(x);
                          
                            x=DL2.readLine();
                            i=i+1;
                        }
                        DL2.close();
                        Result=DL.readLine();
                        
                        while(!Result.equals("#"))
                        {
                            for(int u=0;u<25;u++){
                            if(a[u]==Integer.parseInt(Result))
                            {
                                a[u]=0;
                                v=v+1;
                            }
                            }
                           Result=DL.readLine();
                        }
                        
                         FileWriter D3=new FileWriter(fL2);
                        BufferedWriter DL3=new BufferedWriter(D3);
                        
                               for(int u=0;u<25;u++){
                            if(a[u]!=0)DL3.write(a[u]+"\n");
                            }  
                        
                           DL3.close();
                        
                           Result=DL.readLine();
                            
                        }
                        DL.close();
                           FileWriter D5=new FileWriter(fL);
                        BufferedWriter DL5=new BufferedWriter(D5);
                        DL5.close();
                        
                           File file=new File("CARD/"+l+".txt");
                           FileReader f =new FileReader(file);
                            BufferedReader fw=new BufferedReader(f);
                            String t=fw.readLine();
                            int y=Integer.parseInt(t);
                           fw.close();
                            y=y+(490*v);
                            
                           FileWriter fr =new FileWriter(file);
                            BufferedWriter fr2=new BufferedWriter(fr);
                             fr2.write(""+y+ "\n");
                             fr2.close();
                             
                        
                        
                    } catch (IOException ex) {}
                    
                      
                  }
                  
                  
                  else if(clientSentence.equals("CHECK"))
                  {
                      
                                          try {
                        clientSentence= inFromServer.readLine();//get card number
                    } catch (IOException ex) {}
                    
                      File file=new File("CARD/"+clientSentence+".txt");
                      
              
                      if(file.exists())
                      {
                                                      
                       try {
                            Result="YES";
                              outToServer.writeBytes(Result+ '\n');
                                } catch (IOException ex) {}
                                              
                      }
                      
                      else
                      {
                                try {
                            Result="NO";
                              outToServer.writeBytes(Result+ '\n');
                                } catch (IOException ex) {}
                      }
                  }
            System.out.println("Client "+id+" connection Canceled");
        
        }
    }
    
    
}

}