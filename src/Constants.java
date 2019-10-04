/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author snsystem_amol
 */
public interface Constants 
{
   
    
    //aws mysql windows
//    String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
//    String CONNECTION_STRING = "jdbc:mysql://vtsdb.cyfgajrqdf2d.ap-south-1.rds.amazonaws.com:3306/AIS140DB";
//    //String CONNECTION_STRING = "jdbc:driver://vtsdb.cyfgajrqdf2d.ap-south-1.rds.amazonaws.com:3306/vtsdb?user=myvts&password=myvts123";
//    String USER_NAME = "vtsdba";
//    String PASSWORD = "VTSsns-9";
//    //int PORT_NUMBER = 9999;
//    int PORT_NUMBER = 9999;
    
    //aws sqlserver windows (ujwala)
    String DRIVER_NAME = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    //String CONNECTION_STRING = "jdbc:sqlserver://sns-vts.cyfgajrqdf2d.ap-south-1.rds.amazonaws.com;DatabaseName=AIS140DB;";
    String CONNECTION_STRING = "jdbc:sqlserver://sns-vts.cyfgajrqdf2d.ap-south-1.rds.amazonaws.com;DatabaseName=forcemtuDB;";
    String USER_NAME = "vtsdba";
    String PASSWORD = "VTSsns-9";
    int PORT_NUMBER = 6551;
    
    
    //local sandip
//    String DRIVER_NAME = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
//    String CONNECTION_STRING = "jdbc:sqlserver://192.168.0.220:1433;DatabaseName=VTS_180218_sandip;";
//    String USER_NAME = "sandip";
//    String PASSWORD = "sandip";
     
    
//aws mysql linux
//    String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
//    String CONNECTION_STRING = "jdbc:mysql://localhost:3306/vtsdb1.0";
//    //String CONNECTION_STRING = "jdbc:mysql://ec2-13-232-99-13.ap-south-1.compute.amazonaws.com:3306/vtsdb1.0";
//    String USER_NAME = "root";
//    String PASSWORD = "Sns@1806";
//    int PORT_NUMBER = 6552;
    
 
    
    String LOG_MASTER_PATH = "FORCE_MTU_LOG/FORCE_MTU/FORCE_MTU_";
    String PROCESS_IDS = "FORCE_MTU_LOG/Pid/Process_Ids_";
    String LOG_PATH = "FORCE_MTU_LOG/Exception/Exception_log_";
    String DEVICE_STRING = "FORCE_MTU_LOG/DeviceString/log_";
    String DEVICE_DETAILS_LOG_MASTER_PATH = "FORCE_MTU_LOG/DeviceLog/FORCE_MTUDeviceLog_";
    String CLOSE_DEVICES = "FORCE_MTU_LOG/CloseDevice/close_devices_";
    
    String OPEN_DEVICES_LOG = "FORCE_MTU_LOG/OpenDeviceLog/open_devices_log_";
    String CLOSE_DEVICES_LOG = "FORCE_MTU_LOG/CloseDeviceLog/close_devices_log_";
    
    
}
