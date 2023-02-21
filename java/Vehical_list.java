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
public class Vehical_list extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         PrintWriter out=resp.getWriter();
        String id=req.getParameter("id");
       
        
        
        String btn=req.getParameter("btn");
        
        out.println(id);
        
        
        Database db=new Database();
        String result=db.connectdb();
        out.println(result);
        
        if(btn.equals("Delete"))
        {
            String update=db.Update("update car_details set status1='0' where id='"+id.toString()+"' ", "Record Deleted");
            out.println(update);
            resp.setContentType("text/html");  
            out.println("<script type=\"text/javascript\">");  
            out.println("alert('Data deleted successfuly');"); 
            out.println("location='vehical_list.jsp';");
            out.println("</script>");

        }
    }

  
}
