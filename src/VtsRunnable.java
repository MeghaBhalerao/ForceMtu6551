import java.io.*;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
public class VtsRunnable implements Runnable 
{

    protected Socket clientSocket = null;
    public VtsRunnable(Socket clientSocket) 
    {
        this.clientSocket = clientSocket;
    }
    @Override
    public void run() 
    {
        //logic goes here
        synchronized(this)
        {
            DataInputStream n = null;
            byte[] buffer = new byte[1024];
            int bytes;
            //Listen
            while (true) 
            {
                String client_address = null,hexString = null;
                try
                {
                    InputStream inputStream = clientSocket.getInputStream();
                    bytes = inputStream.read(buffer); //Problem
                    //System.out.println("DATA"+inputStream.available());
                    
                    if (bytes == -1) 
                    {
                        System.out.println("socket close from client");
                        //remove socket data when socket close
                        String key = clientSocket.getRemoteSocketAddress().toString().replace("/", "");
                        SocketServer.CLIENT_SOCKETS.remove(key);
                        //System.out.println("Close Socket IP : "+clientSocket.getRemoteSocketAddress().toString().replace("/", ""));
                        LogMaster.saveOpenCloseDevie(clientSocket.getRemoteSocketAddress().toString().replace("/", ""), "close");
                        clientSocket.close();
                        LogMaster.saveDeviceDetails(
                            "Disconected",
                            "NA",
                            String.valueOf(clientSocket.getPort()),
                            String.valueOf(clientSocket.getLocalPort()),
                            String.valueOf(clientSocket.getRemoteSocketAddress().toString().replace("/", "")),
                            "Packet Name: not found,Exception : bytes == -1");
                        break;
                    }


                    byte[] readBuf = (byte[]) buffer;
                    //String device_string = new String(readBuf, 0, bytes,StandardCharsets.UTF_16);
                    hexString = new String(readBuf, 0, bytes,"ISO_8859_1");
                    System.out.println("String : "+hexString+ "\r\nTimeStamp : "+T.getSystemDateTime());
                    System.out.println();
                    System.out.println("-------------------------------------------------------------------------------------------");
                    client_address = clientSocket.getRemoteSocketAddress().toString().replace("/", "");

                    if(hexString != "" && hexString != null)
                    {
                        String storeContent = "\r\nString : "+hexString+"\r\nTimestamp : "+T.getSystemDateTime()+"\r\nClient Address : "+client_address;
                        LogMaster.saveDeviceString(storeContent);
                    }
                    if(hexString.contains("~sns"))
                    {
                        
                        String [] data = hexString.split("~sns");
                        T.sendCommand(clientSocket,data[0],data[1]);
                    }
                    else
                    {
                        if(hexString != null || hexString.length() > 0)
                        {

                            String key = clientSocket.getRemoteSocketAddress().toString().replace("/", "");
                            if(SocketServer.CLIENT_SOCKETS.containsKey(key))
                            {
 
                                SocketInfo info = SocketServer.CLIENT_SOCKETS.get(key);
                                String [] data = T.getSystemDateTime().split(" ");
                                info.setClientSocket(clientSocket);
                                info.setCsIpPort(key);
                                info.setDateTime(data[1]);
                                SocketServer.CLIENT_SOCKETS.replace(key, info);
                            }
                            else
                            {
                                SocketInfo info = new SocketInfo();
                                info.setClientSocket(clientSocket);
                                info.setCsIpPort(clientSocket.getRemoteSocketAddress().toString().replace("/", ""));
                                String [] data = T.getSystemDateTime().split(" ");
                                info.setDateTime(data[1]);
                                SocketServer.CLIENT_SOCKETS.put(clientSocket.getRemoteSocketAddress().toString().replace("/", ""),info);
                            }
                            
                            if(hexString.contains("|"))
                            {
                                String [] dataa = hexString.split("FMTU");
                                for(int i = 1; i < dataa.length; i++)
                                {
                                    String stringDatas = "FMTU"+dataa[i];
                                    String query = "INSERT INTO tcpip(string, status, created_date, client_address) VALUES('"+stringDatas+"','0','"+T.getSystemDateTime()+"','"+client_address+"')";
                                    DbOperations.insertQuery(query);
                                    System.out.println("String added");
                                }
                            }
                            else
                            {
                                if(hexString.equals("") || hexString.isEmpty() || hexString == null)
                                    System.out.println("Empty string found");
                                else
                                {
                                    String query = "INSERT INTO tcpip(string, status, created_date, client_address) VALUES('"+hexString+"','0','"+T.getSystemDateTime()+"','"+client_address+"')";
                                    DbOperations.insertQuery(query);
                                    System.out.println("Invalid String added");
                                }
                                
                                 
                            }  
                        }
                        else
                            System.out.println("Invalid String");
                    }
                }
                catch(IOException e)
                {
                  
                   String key = clientSocket.getRemoteSocketAddress().toString().replace("/", "");
                   SocketServer.CLIENT_SOCKETS.remove(key);
                   System.out.println("socket close from server");
                   
                   try 
                   {
                        
                       LogMaster.saveOpenCloseDevie(clientSocket.getRemoteSocketAddress().toString().replace("/", ""), "close");
                   }
                   catch (IOException ex) 
                   {
                       Logger.getLogger(VtsRunnable.class.getName()).log(Level.SEVERE, null, ex);
                   }
                   break;
                   
                }
                catch(Exception e)
                {
                    System.out.println("Data : "+e);
                }
                finally
                {
                    
                }
            }  
        }
    } 
}
