package csit.semit.nyr.webappsnyrlab21.servlets;

import csit.semit.nyr.webappsnyrlab21.daohbn.DAONewPostTTN;
import csit.semit.nyr.webappsnyrlab21.daohbn.DAOClients;
import csit.semit.nyr.webappsnyrlab21.entity.Client;
import csit.semit.nyr.webappsnyrlab21.entity.NewPostTTN;
import csit.semit.nyr.webappsnyrlab21.entity.NewPostTTN.DeliveryStatus;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet("/newpost/create")
public class NewPostTTNCreateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Client> clients = DAOClients.getAllClients();
        request.setAttribute("clients", clients);
        request.setAttribute("statuses", DeliveryStatus.values());

        RequestDispatcher dispatcher = request.getRequestDispatcher("/newpost/create_ttn.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long receiverId = Long.parseLong(request.getParameter("receiver"));
        String manager = request.getParameter("manager");
        int numPoint = Integer.parseInt(request.getParameter("numPoint"));
        String kodTTN = request.getParameter("kodTTN");
        LocalDateTime sendTime = LocalDateTime.parse(request.getParameter("sendTime"));
        DeliveryStatus status = DeliveryStatus.valueOf(request.getParameter("status"));

        Client receiver = DAOClients.getClientById(receiverId);
        NewPostTTN ttn = new NewPostTTN(null, receiver, manager, numPoint, kodTTN, sendTime, status);

        String result = DAONewPostTTN.insertTTN(ttn);
        if (result.contains("Error")) {
            request.setAttribute("error", result);
            doGet(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/newpost");
        }
    }
}
