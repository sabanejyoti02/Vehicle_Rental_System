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
public class demo extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       
        
        PrintWriter out=resp.getWriter();
        String brand=req.getParameter("brand");
        String image=req.getParameter("image");
        String description=req.getParameter("description");
        
        out.println(brand);
        out.println(image);
        out.println(description);
        
        Data db=new Data();
        String result=db.dbconnect();
        out.println(result);
        
        
        
        
    }

    
}
