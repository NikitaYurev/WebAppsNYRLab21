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

@WebServlet("/clients/update")
public class ClientUpdateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long clientId = Long.parseLong(request.getParameter("id_client"));
        Client client = DAOClients.getClientById(clientId);

        request.setAttribute("client", client);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/clients/edit_client.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long clientId = Long.parseLong(request.getParameter("id_client"));
        String firstName = request.getParameter("firstName");
        String secondName = request.getParameter("secondName");
        String region = request.getParameter("region");
        String city = request.getParameter("city");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");

        Client client = new Client(clientId, firstName, secondName, region, city, phone, email);
        String result = DAOClients.updateClient(client, clientId);

        if (result.contains("Error")) {
            request.setAttribute("error", result);
            request.setAttribute("client", client);
            doGet(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/clients/list");
        }
    }
}
