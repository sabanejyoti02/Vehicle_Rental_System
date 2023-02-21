
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author CodeExpert
 */
public class Data {
    
    Connection cn=null;
    Statement st=null;
    
    String dbconnect()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/car_rentel","root","");
            return ("database connected");
        
        }catch(Exception ex)
        {
        return (ex.toString());
        }
    
    }
    
}
