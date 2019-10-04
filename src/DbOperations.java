import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

 
public class DbOperations 
{
    public static void insertQuery(String QUERY) 
    {
         
        Connection con = null;
        PreparedStatement ps = null;
        try
        {
            
           con = DbConnection.getConnection();
           ps = con.prepareStatement(QUERY);
           ps.executeUpdate();
          
        }
        catch(Exception e)
        {
            try
            {
                LogMaster.saveExceptionLogDetails("updateDeviceVerification",""+e);
                e.printStackTrace();
            } 
            catch (IOException ex)
            {
                Logger.getLogger(DbOperations.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        finally
        {
            try 
            {
                ps.close();
                 
                
            } catch (SQLException ex) {
                Logger.getLogger(DbOperations.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
    }
}
