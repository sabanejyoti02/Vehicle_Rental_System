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
public class car_details extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       PrintWriter out=resp.getWriter();
        String name=req.getParameter("name");
        String model=req.getParameter("model");
        String Brand=req.getParameter("Brand");
        String vehicleno=req.getParameter("vehicleno");
        String seat=req.getParameter("seat");
        String price=req.getParameter("price");
        String fuel=req.getParameter("fuel");
        String airbag=req.getParameter("airbag");
        String status=req.getParameter("status");
        String image=req.getParameter("image");
        String category=req.getParameter("category");
        
        
        String btn=req.getParameter("btn");
        
        out.println(name);
        out.println(model);
        out.println(Brand);
        out.println(vehicleno);
        out.println(seat);
        out.println(price);
        out.println(fuel);
        out.println(airbag);
        out.println(status);
        out.println(image);
        
        Database db=new Database();
        String result=db.connectdb();
        out.println(result);
        
        if(btn.equals("Submit"))
        {
            String insert=db.Insert("insert into car_details(name,model,brand,vehicalno,seats,price,fuel,airbag,status,category,image,status1)values('"+name.toString()+"','"+model.toString()+"','"+Brand.toString()+"','"+vehicleno.toString()+"','"+seat.toString()+"','"+price.toString()+"','"+fuel.toString()+"','"+airbag.toString()+"','"+status.toString()+"','"+category.toString()+"','"+image.toString()+"','1')", "Record Inserted");
            out.println(insert);
            resp.setContentType("text/html");  
            out.println("<script type=\"text/javascript\">");  
            out.println("alert('You sucessfully inserted data');"); 
            out.println("location='car_details.jsp';");
            out.println("</script>");

        }
        
    }

    
}
