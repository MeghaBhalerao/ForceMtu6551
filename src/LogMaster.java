import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

 
public class LogMaster 
{
    public static void saveOpenCloseDevie(String ipPort,String status) throws IOException
    {
       // File log = new File(System.getProperty("dir")+Constants.DEVICE_DETAILS_LOG_MASTER_PATH);
        
        String [] date = T.getSystemDateTime().split(" ");
        File log ;
        
        if(status.equals("open"))
        {
            log =  new File(Constants.OPEN_DEVICES_LOG+date[0].replace("-", "_")+".txt");
        }
        else
        {
            log =  new File(Constants.CLOSE_DEVICES_LOG+date[0].replace("-", "_")+".txt");
        }
        log.getParentFile().mkdirs();
        BufferedWriter bufferedWriter = null;
        FileWriter fileWriter = null;

        try
        {
                String content = "\r\nIP :"+ipPort+"\tTimeStamp :"+T.getSystemDateTime();
                
                fileWriter = new FileWriter(log, true);

                bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(content);
                bufferedWriter.close();

                //System.out.println("Done");

            
        } 
        catch(Exception e) 
        {
             
             
        }
        finally
        {
            try
            {
                bufferedWriter.close();
                fileWriter.close();
            }
            catch(NullPointerException s)
            {
                
            }
            
        }
    }
    public static void closeDeviceDetails(
            String deviceID,
            String ipPort) throws IOException
    {
        //File log = new File(System.getProperty("dir")+Constants.CLOSE_DEVICES);
        
        String [] date = T.getSystemDateTime().split(" ");
        File log =  new File(Constants.CLOSE_DEVICES+date[0].replace("-", "_")+".txt");
        log.getParentFile().mkdirs();
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;

        try
        {
                String content = "Device ID :"+deviceID+"\r\nIp : Port :"+ipPort+"\r\nDisconnected Time :"+T.getSystemDateTime();
                fileWriter = new FileWriter(log, true);

                bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(content+"\r\n-----------------------------------------------------------------------------------------------------\r\n");
                bufferedWriter.close();

                //System.out.println("Done");

            
        } 
        catch(FileNotFoundException e) 
        {
             
            System.out.print("One : "+e);
              //saveLogDetails(deviceID,clientAddress,String.valueOf(e));  
             
        }
        catch(Exception e) 
        {
             System.out.print("Two : "+e);
              //saveLogDetails(deviceID,clientAddress,String.valueOf(e));  
             
        }
        finally
        {
            try
            {
                bufferedWriter.close();
                fileWriter.close();
            }
            catch(NullPointerException s)
            {
                System.out.print("five : "+s);
            }
            
            
            
        }
    }
    
    public static void saveExceptionLogDetails(
            String exceptionLocation,
            String exceptionDetails) throws IOException
    {
        //File log = new File(System.getProperty("dir")+Constants.LOG_MASTER_PATH);
        String [] dateTime = T.getSystemDateTime().split(" ");
        
        String [] date = T.getSystemDateTime().split(" ");
        File log =  new File(Constants.LOG_MASTER_PATH+date[0].replace("-", "_")+".txt");
        log.getParentFile().mkdirs();
        BufferedWriter bufferedWriter = null;
        FileWriter fileWriter = null;
        try
        {
                String content = "Location :"+exceptionLocation+"\r\nDate :"+dateTime[0]+"\r\nTime :"+dateTime[1]+"\r\nException :"+exceptionDetails;
                fileWriter = new FileWriter(log, true);

                bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(content+"\r\n----------------------------------------------------------------------------------------------------------------------------------------\r\n");
                bufferedWriter.close();

                System.out.println("Done");
        } 
        catch(Exception e) 
        {
              saveExceptionLogDetails("saveExceptionLogDetails",String.valueOf(e));  
        }
        finally
        {
            try
            {
                bufferedWriter.close();
                fileWriter.close();
            }
            catch(NullPointerException s)
            {
                
            }
            
        }
    }
    
    public static void saveLogDetails(
            String deviceID,
            String clientAddress,
            String exceptionDetails) throws IOException
    {
        //File log = new File(System.getProperty("dir")+Constants.LOG_MASTER_PATH);
        
        String [] date = T.getSystemDateTime().split(" ");
        File log =  new File(Constants.LOG_MASTER_PATH+date[0].replace("-", "_")+".txt");
        log.getParentFile().mkdirs();
        
        BufferedWriter bufferedWriter = null;
        FileWriter fileWriter = null;

        try
        {
                String content = "Device ID :"+deviceID+" Created Date :"+T.getSystemDateTime()+" Client Address :"+clientAddress+" Exception :"+exceptionDetails;
                fileWriter = new FileWriter(log, true);

                bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(content+"\n\n");
                bufferedWriter.close();

                //System.out.println("Done");

            
        } 
        catch(Exception e) 
        {

            System.out.println(""+e);
             
        }
        finally
        {
            try
            {
                bufferedWriter.close();
                fileWriter.close();
            }
            catch(NullPointerException s)
            {
                
            }
            
        }
    }
    
    
    public static void saveDeviceDetails(
            String status,
            String deviceID,
            String clientPort,
            String clientLocalPort,
            String clientLocalAddress,
            String packetName) throws IOException
    {
       // File log = new File(System.getProperty("dir")+Constants.DEVICE_DETAILS_LOG_MASTER_PATH);
        
        String [] date = T.getSystemDateTime().split(" ");
        File log =  new File(Constants.DEVICE_DETAILS_LOG_MASTER_PATH+date[0].replace("-", "_")+".txt");
        log.getParentFile().mkdirs();
        
        BufferedWriter bufferedWriter = null;
        FileWriter fileWriter = null;

        try
        {
                String content = 
                        "Status :"+status+
                        "\r\nPacket Name :"+packetName+
                        "\r\nDevice ID :"+deviceID+
                        "\r\nDevice Port :"+clientPort+
                        "\r\nDevice Local Port :"+clientLocalPort+
                        "\r\nDevice Local Address :"+clientLocalAddress+
                        "\r\nDate Time :"+T.getSystemDateTime();
                
                fileWriter = new FileWriter(log, true);

                bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(content+"\r\n----------------------------------------------------------------------\r\n");
                bufferedWriter.close();

                //System.out.println("Done");

            
        } 
        catch(Exception e) 
        {
            System.out.print("three : "+e);
            saveLogDetails(deviceID,clientLocalAddress,String.valueOf(e));  
             
        }
        finally
        {
            try
            {
                bufferedWriter.close();
                fileWriter.close();
            }
            catch(NullPointerException s)
            {
                System.out.print("four : "+s);
            }
            
        }
    }
    public static void saveDeviceString(String storeContent) throws IOException
    {
        //File log = new File(System.getProperty("dir")+Constants.LOG_PATH);
        
        String [] date = T.getSystemDateTime().split(" ");
        File log =  new File(Constants.DEVICE_STRING+date[0].replace("-", "_")+".txt");
        log.getParentFile().mkdirs();
        
        BufferedWriter bufferedWriter = null;
        FileWriter fileWriter = null;

        try
        {
                fileWriter = new FileWriter(log, true);
                bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(storeContent+"\r\n-----------------------------------------------------------");
                bufferedWriter.close();

            
        } 
        catch(Exception e) 
        {
             
             
        }
        finally
        {
            try
            {
                bufferedWriter.close();
                fileWriter.close();
            }
            catch(NullPointerException s)
            {
                
            }
        }
    }

}
