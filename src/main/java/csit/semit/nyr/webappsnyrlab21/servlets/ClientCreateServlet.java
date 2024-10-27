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

@WebServlet("/clients/create") // Updated URL path
public class ClientCreateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // For creating a new client, initialize an empty Client object
        Client client = new Client();
        request.setAttribute("client", client);
        request.setAttribute("action", "Add Client");

        // Forward to client creation JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("/clients/client.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String secondName = request.getParameter("secondName");
        String region = request.getParameter("region");
        String city = request.getParameter("city");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");

        Client client = new Client(null, firstName, secondName, region, city, phone, email);
        String result = DAOClients.insertClient(client);

        if (result.contains("Error")) {
            request.setAttribute("error", result);
            request.setAttribute("client", client);
            doGet(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/clients/list");
        }
    }
}
