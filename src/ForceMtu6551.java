/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sns003
 */
public class ForceMtu6551 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        T.saveProcessIds("FORCE_MTU");
       System.out.println("FORCE_MTU "+Constants.PORT_NUMBER+" TCP v1.0 Running...");
       System.out.println();
       SocketServer s = new SocketServer();
        try {
            s.runServer();
        } catch (IOException ex) {
            Logger.getLogger(ForceMtu6551.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
