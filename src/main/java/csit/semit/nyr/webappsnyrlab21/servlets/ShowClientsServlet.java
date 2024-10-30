package csit.semit.nyr.webappsnyrlab21.servlets;

import csit.semit.nyr.webappsnyrlab21.daohbn.DAOClients;
import csit.semit.nyr.webappsnyrlab21.entity.Client;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/clients/list") // Changed to "/clients/list" to ensure uniqueness
public class ShowClientsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve filter parameters
        String firstNameFilter = request.getParameter("firstNameFilter");
        String secondNameFilter = request.getParameter("secondNameFilter");
        String regionFilter = request.getParameter("regionFilter");
        String cityFilter = request.getParameter("cityFilter");
        String phoneFilter = request.getParameter("phoneFilter");
        String emailFilter = request.getParameter("emailFilter");

        // Use DAO to get filtered clients
        DAOClients daoClients = new DAOClients();
        List<Client> clients = daoClients.getFilteredClients(firstNameFilter, secondNameFilter, regionFilter, cityFilter, phoneFilter, emailFilter);

        // Set clients and filter parameters as request attributes
        request.setAttribute("clients", clients);
        request.setAttribute("firstNameFilter", firstNameFilter);
        request.setAttribute("secondNameFilter", secondNameFilter);
        request.setAttribute("regionFilter", regionFilter);
        request.setAttribute("cityFilter", cityFilter);
        request.setAttribute("phoneFilter", phoneFilter);
        request.setAttribute("emailFilter", emailFilter);

        // Forward to JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("/clients/clients.jsp");
        dispatcher.forward(request, response);
    }
}
