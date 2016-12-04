/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gud.logtimer.controller;

import gud.logtimer.entity.LogRecord;
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
public class AddActivity extends HttpServlet{
    private String formMessage ;
    private String timestamp ;
    private InMemoryStorage storage ;
    private boolean  isPending ;
    private DateOperations dateOps;

    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
//        java.util.Enumeration<String> a =  this.getServletConfig().getServletContext().getAttributeNames();
//        while (a.hasMoreElements()){
//            System.out.println(a.nextElement());
//        }
        storage = (InMemoryStorage) getServletContext().getAttribute("inMemoryStorage");
        dateOps = (DateOperations) getServletContext().getAttribute("DateOperations");
    }
    
    
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
       isPending = Boolean.valueOf( (String) getServletContext().getAttribute("isPending"));
       formMessage = req.getParameter("message");
       
       timestamp = dateOps.getTimestamp();
       
       if (isPending)  {
           storage.getLast().setEndTime(timestamp);
           if (!formMessage.isEmpty()){
                storage.getLast().setActionDone(formMessage);
           }
           storage.getLast().setDeltaTime( dateOps.delta(storage.getLast().getStartTime(), timestamp));

       }else {
           storage.addRecord(timestamp, "Pending...", formMessage);
           
       }

//        System.out.println("==========Pending is "+String.valueOf(isPending));
        for (LogRecord lr : storage.getAllRecordsAsList() ) {
            System.out.println(lr.getStartTime());
            System.out.println(lr.getEndTime());
            
        }
       
//       req.getRequestDispatcher("index.jsp").forward(req, resp);
        resp.sendRedirect("index.jsp");
    }
    
    
    
}
