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
public class add_map extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out=resp.getWriter();
        String from=req.getParameter("from");
        String to=req.getParameter("to");
        String description=req.getParameter("description");
        String btn=req.getParameter("btn");
        
        out.println(from);
        out.println(to);
        out.println(description);
        
        Database db=new Database();
        String result=db.connectdb();
        out.println(result);
        
        if(btn.equals("Save"))
        {
            String insert=db.Insert("insert into map(l_form,l_to,description,status)values('"+from.toString()+"','"+to.toString()+"','"+description.toString()+"','1')", "Record Inserted");
            out.println(insert);
            resp.setContentType("text/html");  
            out.println("<script type=\"text/javascript\">");  
            out.println("alert('Data added successfuly');"); 
            out.println("location='add_map.jsp';");
            out.println("</script>");
        }
    }

   
}
