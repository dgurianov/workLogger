/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gud.logtimer.controller;

import gud.logtimer.configuration.Config;
import gud.logtimer.stub.InMemoryStorage;
import gud.logtimer.util.DateOperations;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Danylo
 */
public class MainController extends HttpServlet{

    @Override
    public void init() throws ServletException {
        super.init(); 
//        System.out.println("=============Main Controller================");
        this.getServletConfig().getServletContext().setAttribute("inMemoryStorage", new InMemoryStorage());
        this.getServletConfig().getServletContext().setAttribute("configuration", new Config());
        this.getServletConfig().getServletContext().setAttribute("DateOperations", new DateOperations());
//        getServletContext().setAttribute("inMemoryStorage", new InMemoryStorage());
//        System.out.println("=============Main Controller================");
//        java.util.Enumeration<String> a =  this.getServletConfig().getServletContext().getAttributeNames();
//        while (a.hasMoreElements()){
//            System.out.println(a.nextElement());
//        }
        
    }
    
    

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
    
    
    
}
