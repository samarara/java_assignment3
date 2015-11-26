/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MC;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Lucas
 */
@WebServlet(name = "UpdateManagerC", urlPatterns = {"/UpdateManagerC"})
public class UpdateManagerC extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdateManagerC</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateManagerC at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
            
            String url = "/HomeV.jsp";
            String conURL = "jdbc:mysql://localhost:3306/fs2";
            String user = "root";
            String pass = "sachi800";
            //log in to database
            DataAccess da = new DataAccess(conURL, user, pass);
            HttpSession session = request.getSession();
            String action = request.getParameter("action");
            String tableName = (String)session.getAttribute("tableName");
            String selected;
            switch (action) {
                case "Add":
                    switch (tableName){
                        case "frequents":
                            String name = request.getParameter("name");
                            String[] fsNamAndBranch = request.getParameter("updateThis").split(" ");
                            String fsName = fsNamAndBranch[0];
                            String bnum = fsNamAndBranch[1];
                            if (da.insertFrequents(name, fsName, bnum) == true){
                            
                                //sends the user to sucess page
                            }
                            else{
                                //sends the user to error page
                            }
                        break;
                        case "student":
                            String snum = request.getParameter("snum");
                            String nameStu = request.getParameter("name");
                            String major = request.getParameter("major");
                            String hobby = request.getParameter("hobby");
                            if (da.insertStudent(snum, nameStu, major, hobby) == true){
                            
                                //sends the user to sucess page
                            }
                            else{
                                //sends the user to error page
                            }
                        break;
                        case "foodservice":
                            String nameFs = request.getParameter("name");
                            String category = request.getParameter("category");
                            String rate = request.getParameter("rate");
                            if (da.insertFoodService(nameFs, category, rate) == true){
                            
                                //sends the user to sucess page
                            }
                            else{
                                //sends the user to error page
                            }
                        break;
                        case "branch":
                            String nameBr = request.getParameter("name");
                            String bNumBr = request.getParameter("bnum");
                            String location = request.getParameter("location");
                            String since = request.getParameter("since");
                            if (da.insertBranch(nameBr, bNumBr, location, since) == true){
                            
                                //sends the user to sucess page
                            }
                            else{
                                //sends the user to error page
                            }
                        break;
                        case "food":
                            String food = request.getParameter("name");
                            String cuisine = request.getParameter("cuisine");
                            String type = request.getParameter("type");
                            if (da.insertFood(food, cuisine, type) == true){
                            
                                //sends the user to sucess page
                            }
                            else{
                                //sends the user to error page
                            }
                        break;
                        case "provides":
                        break;
                        case "likes":
                            String snumL = request.getParameter("snum");
                            String foodL = request.getParameter("food");
                            if (da.insertLikes(snumL, foodL) == true){
                            
                                //sends the user to sucess page
                            }
                            else{
                                //sends the user to error page
                            }
                        break;
                    }
                            
                    
                    selected = (String)request.getParameter("showTh1is");
                    
                    //request.setAttribute("table", table);
                    session.setAttribute("table", table);

                    url = "/MenuRefreshV.jsp";
                    break;
                case "Delete":
                    selected = request.getParameter("updateThis");
                    //request.setAttribute("tableName", selected);
                    session.setAttribute("tableName", selected);

                    //request.setAttribute("branch", branch);
                    session.setAttribute("branch", branch);
                    url = "/MenuUpdateV.jsp";
                    break;
                case "Update":
                    url ="/HomeV.jsp";
                    break;
                    
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
