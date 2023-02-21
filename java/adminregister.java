/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author CodeExpert
 */
public class adminregister extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out=resp.getWriter();
        String name=req.getParameter("name");
        String address=req.getParameter("address");
        String contact=req.getParameter("contact");
        String email=req.getParameter("email");
        String password=req.getParameter("password");
        
        String btn=req.getParameter("btn");
        
        out.println(name);
        out.println(address);
        out.println(contact);
        out.println(email);
        out.println(password);
        
        Database db=new Database();
        String result=db.connectdb();
        out.println(result);
        
        if(btn.equals("Signin"))
        {
            String insert=db.Insert("insert into admin_register(name,address,contact,email,password)values('"+name.toString()+"','"+address.toString()+"','"+contact.toString()+"','"+email.toString()+"','"+password.toString()+"')", "Record Inserted");
            out.println(insert);
            resp.setContentType("text/html");  
            out.println("<script type=\"text/javascript\">");  
            out.println("alert('You sucessfully registerd');"); 
            out.println("location='admin_login.jsp';");
            out.println("</script>");
        }
    }

    
}
