package com.fuel.web;

import com.fuel.bean.Request;
import com.fuel.bean.Total;
import com.fuel.bean.TotalByType;
import com.fuel.dao.RequestDao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private RequestDao requestDao;
    
 

    /**
     *
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        requestDao = new RequestDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(RequestServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String action = request.getServletPath();

        switch (action) {
            case "/new":
                showNewForm(request, response);
                break;
            case "/insert":
                insertRequest(request, response);
                break;
            case "/delete":
                deleteRequest(request, response);
                break;
            case "/edit":
                showEditForm(request, response);
                break;
            case "/update":
                updateRequest(request, response);
                break;
            case "/":
                listRequests(request, response);
                break;
            default:
                listRequests(request, response);
                break;
        }
    }

    //new request
    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("fuel-form-new.jsp");
        dispatcher.forward(request, response);
    }

    //insert request
    private void insertRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String fuel = request.getParameter("fuel");
        String quantity = request.getParameter("quantity");
        String fuelId = request.getParameter("fuelId");
        double uPrice = fuelPrice(Integer.parseInt(fuelId));
        double totalPrice = (Double.parseDouble(quantity))*uPrice;
        
        Request newUser = new Request(fuel, Double.parseDouble(quantity), totalPrice);
        requestDao.insertRequest(newUser);
        response.sendRedirect("list");
    }
    
    //delete request
    private void deleteRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        try{
            requestDao.deleteRequest(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        response.sendRedirect("list");
    } 
    
    //edit request
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        
        Request exitingRequest;
        try{
            exitingRequest = requestDao.selectRequest(id);
            RequestDispatcher dispatcher = request.getRequestDispatcher("fuel-form-update.jsp");
            request.setAttribute("request", exitingRequest);
            dispatcher.forward(request, response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //update Request
    private void updateRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        
        String quantity = request.getParameter("quantity");
        
        int fuelId = id;
        String fname = fuelName(fuelId);
        double uPrice = fuelPrice(fuelId);
        double totalPrice = (Double.parseDouble(quantity))*uPrice;
        
        Request req = new Request(id, fname, Double.parseDouble(quantity), totalPrice);
        requestDao.updateRequest(req);
        response.sendRedirect("list");
    }
    
    //list all request, total group by, order count, total
    private void listRequests(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {
        
        Total totalValue;
        try{
            List<Request> listRequests = requestDao.selectAllRequests(); //get all requests to 'listRequest'
            List<TotalByType> listTotal = requestDao.getTotalByType();   //get total by type to 'listTotal'
            totalValue = requestDao.getTotalAndCount();                  //get total and order count to 'totalValue'
            
            request.setAttribute("totalValue",totalValue);
            request.setAttribute("listRequests", listRequests);
            request.setAttribute("listTotal", listTotal);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("request-list.jsp");
            dispatcher.forward(request, response);
        }catch (IOException | ServletException e){
            e.printStackTrace();
        }
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }
    
    //return fuel price by id
    private double fuelPrice(int fuel) {
        switch (fuel) {
            case 1:
                return 510.00;
            case 2:
                return 410.00;
            case 3:
                return 430.00;
            default:
                return 510.00;
        }
    } 

    //return fuel type by id
    private String fuelName(int fuel) {
        switch (fuel) {
            case 1:
                return "Petrol 95";
            case 2:
                return "Petrol 92";
            case 3:
                return "Diesel";
            default:
                return "Super Diesel";
        }
    } 
}
