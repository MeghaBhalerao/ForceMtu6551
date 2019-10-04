import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

 
public class DbConnection 
{
    //192.168.0.3:6523
    static Connection con = null;  
    
    public static synchronized Connection getConnection() throws SQLException
    {  
        
        try
        {  
            
            if(con == null || con.isClosed())
            {
                Class.forName(Constants.DRIVER_NAME);
                con = DriverManager.getConnection(Constants.CONNECTION_STRING, Constants.USER_NAME, Constants.PASSWORD);
                System.out.println("connection establish");
            }
            else
            {
               // System.out.println("connection not establish");
            }
            
    
            
        }
        catch(Exception e)
        {
            //LogMaster.saveExceptionLogDetails("getConnection",""+e);
            System.out.println(e);
        }  
        return con;  
    }  
    
    
    
}
