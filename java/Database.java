
import com.google.common.util.concurrent.Service;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.annotation.PostConstruct;
import javax.xml.ws.ServiceMode;


public class Database {
    
    Connection cn=null;
    Statement st=null;
    
   
    
    String connectdb()
    {
        try
        {
          Class.forName("com.mysql.jdbc.Driver");
          cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/car_rentel","root","");
          return ("connected");
        }catch(Exception ex)
        {
        return (ex.toString());
        }
    }
    
    
     String Insert(String sql,String msg)
    {
        try
        {
           st=cn.createStatement();
           st.executeUpdate(sql);
           return (msg);
        
        }catch(Exception ex)
        {
        return (ex.toString());
        }
    }
     
     
     String Update(String sql,String msg)
    {
        try
        {
           st=cn.createStatement();
           st.executeUpdate(sql);
           return (msg);
        
        }catch(Exception ex)
        {
        return (ex.toString());
        }
    }
    
}
