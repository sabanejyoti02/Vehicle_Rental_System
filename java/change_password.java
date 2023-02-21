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
public class change_password extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       
        
        PrintWriter out=resp.getWriter();
        String user=req.getParameter("email");
        String oldpassword=req.getParameter("oldpassword");
        String newpassword=req.getParameter("newpassword");
        String btn=req.getParameter("btn");
        
        out.println(user);
        out.println(oldpassword);
        out.println(newpassword);
        
        
        
        Database db=new Database();
        String result=db.connectdb();
        out.println(result);
        
        if(btn.equals("Change Password"))
        {
            String update=db.Update("update admin_register set password='"+newpassword.toString()+"' where email='"+user.toString()+"' and password='"+oldpassword.toString()+"'", "Password Chnaged");
            out.println(update);
            resp.setContentType("text/html");  
            out.println("<script type=\"text/javascript\">");  
            out.println("alert('Password Chnaged successfuly');"); 
            out.println("location='admin_login.jsp';");
            out.println("</script>");
        
    }

}
}
