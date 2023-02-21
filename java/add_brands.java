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
public class add_brands extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        PrintWriter out=resp.getWriter();
        String brand=req.getParameter("brand");
        String image=req.getParameter("image");
        String description=req.getParameter("description");
        String btn=req.getParameter("btn");
        
        out.println(brand);
        out.println(image);
        out.println(description);
        
        Database db=new Database();
        String result=db.connectdb();
        out.println(result);
        
        if(btn.equals("Save"))
        {
            String insert=db.Insert("insert into add_brand(brand_name,image,description,status)values('"+brand.toString()+"','"+image.toString()+"','"+description.toString()+"','1')", "Record Inserted");
            out.println(insert);
            resp.setContentType("text/html");  
            out.println("<script type=\"text/javascript\">");  
            out.println("alert('Data added successfuly');"); 
            out.println("location='add_brands.jsp';");
            out.println("</script>");
        }
        
        
        
    }

    
}
