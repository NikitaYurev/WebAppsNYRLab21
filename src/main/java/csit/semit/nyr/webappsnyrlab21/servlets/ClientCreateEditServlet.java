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

@WebServlet("/clients/manage") // Changed to "/clients/manage"
public class ClientCreateEditServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String clientIdStr = request.getParameter("id_client");
        Long clientId = clientIdStr != null ? Long.parseLong(clientIdStr) : null;
        Client client = clientId != null ? DAOClients.getClientById(clientId) : new Client();

        request.setAttribute("client", client);
        request.setAttribute("action", clientId == null ? "Add Client" : "Edit Client");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/clients/client.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long clientId = request.getParameter("id_client") != null ? Long.parseLong(request.getParameter("id_client")) : null;
        String firstName = request.getParameter("firstName");
        String secondName = request.getParameter("secondName");
        String region = request.getParameter("region");
        String city = request.getParameter("city");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");

        Client client = new Client(clientId, firstName, secondName, region, city, phone, email);

        String result;
        if (clientId == null) {
            result = DAOClients.insertClient(client);
        } else {
            result = DAOClients.updateClient(client, clientId);
        }

        if (result.contains("Error")) {
            request.setAttribute("error", result);
            request.setAttribute("client", client);
            doGet(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/clients/list");
        }
    }
}
