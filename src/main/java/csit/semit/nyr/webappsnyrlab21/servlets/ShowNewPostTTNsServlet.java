package csit.semit.nyr.webappsnyrlab21.servlets;

import csit.semit.nyr.webappsnyrlab21.daohbn.DAONewPostTTN;
import csit.semit.nyr.webappsnyrlab21.entity.NewPostTTN;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/newpost")
public class ShowNewPostTTNsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve filter parameters
        String receiverFilter = request.getParameter("receiverFilter");
        String managerFilter = request.getParameter("managerFilter");
        String numPointFilter = request.getParameter("numPointFilter");
        String kodTTNFilter = request.getParameter("kodTTNFilter");
        String statusFilter = request.getParameter("statusFilter");

        // Use DAO to get filtered TTNs
        DAONewPostTTN daoNewPostTTN = new DAONewPostTTN();
        List<NewPostTTN> ttns = daoNewPostTTN.getFilteredTTNs(receiverFilter, managerFilter, numPointFilter, kodTTNFilter, statusFilter);

        // Set TTNs and filter parameters as request attributes
        request.setAttribute("ttns", ttns);
        request.setAttribute("receiverFilter", receiverFilter);
        request.setAttribute("managerFilter", managerFilter);
        request.setAttribute("numPointFilter", numPointFilter);
        request.setAttribute("kodTTNFilter", kodTTNFilter);
        request.setAttribute("statusFilter", statusFilter);

        // Forward to JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("/newpost/list_ttns.jsp");
        dispatcher.forward(request, response);
    }
}
