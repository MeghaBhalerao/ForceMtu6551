/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

class SocketServer 
{
    int portNumber = Constants.PORT_NUMBER;
    ServerSocket serverSocket = null;
    static int count = 0;
    static ConcurrentHashMap<String,SocketInfo> CLIENT_SOCKETS = new ConcurrentHashMap<>();
    
    public void runServer() throws IOException
    {
        try
        {
            serverSocket = new ServerSocket(portNumber);
            
        }
        catch(IOException e)
        {
            System.out.println(""+e.getMessage());
        }
        
        while(true)
        {
            try
            {
                Socket clientSocket = serverSocket.accept();
                VtsRunnable m = new VtsRunnable(clientSocket);
                
                SocketInfo info = new SocketInfo();
                
                info.setClientSocket(clientSocket);
                info.setCsIpPort(clientSocket.getRemoteSocketAddress().toString().replace("/", ""));
                info.setDeviceId("NA");
                String [] data = T.getSystemDateTime().split(" ");
                info.setDateTime(data[1]);
                
                CLIENT_SOCKETS.put(clientSocket.getRemoteSocketAddress().toString().replace("/", ""),info);
                LogMaster.saveDeviceDetails(
                                        "Connected",
                                        "NA",
                                        String.valueOf(clientSocket.getPort()),
                                        String.valueOf(clientSocket.getLocalPort()),
                                        String.valueOf(clientSocket.getRemoteSocketAddress().toString().replace("/", "")),
                                        "Packet name not found, devicec connection time");
                
                //get timewait thread
                new Thread(m).start();
                System.out.println("Device connected");
                System.out.println();
                if(count == 0)
                {
                    //close unwanted socket after 5 min delay
                    CloserThread.closeDeadSockets();
                    count = 1;
                }
                
                LogMaster.saveOpenCloseDevie(clientSocket.getRemoteSocketAddress().toString().replace("/", ""), "open");
            }
            catch(Exception e)
            {
               LogMaster.saveExceptionLogDetails("new Thread(m).start();",""+e);
                System.out.println(""+e.getMessage());
            }
        }
    }
}

