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

@WebServlet("/newpost/manage") // Changed to "/newpost/manage"
public class NewPostTTNCreateEditServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ttnIdStr = request.getParameter("id_ttn");
        Long ttnId = ttnIdStr != null ? Long.parseLong(ttnIdStr) : null;
        NewPostTTN ttn = ttnId != null ? DAONewPostTTN.getTTNById(ttnId) : new NewPostTTN();
        List<Client> clients = DAOClients.getAllClients();

        request.setAttribute("ttn", ttn);
        request.setAttribute("clients", clients);
        request.setAttribute("statuses", DeliveryStatus.values());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/newpost/ttn.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long ttnId = request.getParameter("id_ttn") != null ? Long.parseLong(request.getParameter("id_ttn")) : null;
        Long receiverId = Long.parseLong(request.getParameter("receiver"));
        String manager = request.getParameter("manager");
        int numPoint = Integer.parseInt(request.getParameter("numPoint"));
        String kodTTN = request.getParameter("kodTTN");
        LocalDateTime sendTime = LocalDateTime.parse(request.getParameter("sendTime"));
        DeliveryStatus status = DeliveryStatus.valueOf(request.getParameter("status"));

        Client receiver = DAOClients.getClientById(receiverId);
        NewPostTTN ttn = new NewPostTTN(ttnId, receiver, manager, numPoint, kodTTN, sendTime, status);

        String result;
        if (ttnId == null) {
            result = DAONewPostTTN.insertTTN(ttn);
        } else {
            result = DAONewPostTTN.updateTTN(ttn, ttnId);
        }

        if (result.contains("Error")) {
            request.setAttribute("error", result);
            request.setAttribute("ttn", ttn);
            doGet(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/newpost/list");
        }
    }
}
