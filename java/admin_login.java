/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author CodeExpert
 */
public class admin_login extends HttpServlet {
    Connection cn=null;
    Statement st=null;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         PrintWriter out=resp.getWriter();
        String user=req.getParameter("user");
        String password=req.getParameter("password");
        
        String btn=req.getParameter("btn");
        
        out.println(user);
        out.println(password);
        
        if(btn.equals("Login"))
        {
            try
            {
            Class.forName("com.mysql.jdbc.Driver");
            cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/car_rentel","root","");
            st=cn.createStatement();
            String sql="select email,password from admin_register where email='"+user.toString()+"' and password='"+password.toString()+"'";
            ResultSet rs=st.executeQuery(sql);
            if(rs.next())
            {
              resp.sendRedirect("add_map.jsp");
            }
            else
            {
            resp.setContentType("text/html");  
            out.println("<script type=\"text/javascript\">");  
            out.println("alert('Username & password is incorrect');"); 
            out.println("location='admin_login.jsp';");
            out.println("</script>");
            }
            }catch(Exception ex)
            {
            out.println();
            }
        }
        
    }

   
}
