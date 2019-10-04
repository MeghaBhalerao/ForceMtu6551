/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Timer;
import java.util.TimerTask;
/**
 *
 * @author sns003
 */
public class CloserThread 
{
    static TimerTask mTimerTaskClose;
    static Timer t;

    public static void closeDeadSockets()
    {
        t = new Timer("Closer Thread");
        mTimerTaskClose = new TimerTask()
        {
            public void run() 
            {
               // System.out.println("Amol");
                  
               // System.out.println("Size : "+SocketServer.CLIENT_SOCKETS.size());
                if(SocketServer.CLIENT_SOCKETS.isEmpty())
                {
                    
                    if(mTimerTaskClose != null)
                    {
                        SocketServer.count = 0;
                        mTimerTaskClose.cancel();
                        t.cancel();
                        
                    }
                }
                if(!SocketServer.CLIENT_SOCKETS.isEmpty())
                {

                    //do code here
                    for(String key: SocketServer.CLIENT_SOCKETS.keySet())
                    {
                        
                        
                        
                        SocketInfo info = SocketServer.CLIENT_SOCKETS.get(key);
                        String [] data = T.getSystemDateTime().split(" ");

                        long systemTimeSeconds = T.returnsTimeToSec(data[1]);
                        long storeTimeSeconds = T.returnsTimeToSec(info.getDateTime());
                        long compareTime = storeTimeSeconds + 300;//5 min

                        if(compareTime < systemTimeSeconds)
                        {
                            try
                            {
                                LogMaster.closeDeviceDetails(info.getDeviceId(), info.getCsIpPort());
                                LogMaster.saveDeviceDetails(
                                    "Disconnect",
                                    info.getDeviceId(),
                                    String.valueOf(info.getClientSocket().getPort()),
                                    String.valueOf(info.getClientSocket().getLocalPort()),
                                    String.valueOf(info.getClientSocket().getRemoteSocketAddress().toString().replace("/", "")),
                                    "Packet not found : socket close by server");

                                //System.out.println("Amol Wakchaure");
                                info.getClientSocket().close();
                                SocketServer.CLIENT_SOCKETS.remove(key);
                                //System.out.println("Socket Close: "+info.getClientSocket());

                            }
                            catch(Exception e)
                            {
                                System.out.println("Exception : "+e);
                            }
                        }

                    }

                }
            }};

        // public void schedule (TimerTask task, long delay, long period)
        t.schedule(mTimerTaskClose, 5000, 5000);  //
    }
    
}
