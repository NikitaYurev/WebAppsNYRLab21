package csit.semit.nyr.webappsnyrlab21.servlets;

import csit.semit.nyr.webappsnyrlab21.daohbn.DAOClients;
import csit.semit.nyr.webappsnyrlab21.daohbn.DAONewPostTTN;
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
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet("/newpost/update")
public class NewPostTTNUpdateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Accessed NewPostTTNUpdateServlet - doGet");

        Long ttnId = Long.parseLong(request.getParameter("id_ttn"));
        System.out.println("TTN ID Received: " + ttnId);

        NewPostTTN ttn = DAONewPostTTN.getTTNById(ttnId);
        if (ttn == null) {
            System.out.println("TTN not found for ID: " + ttnId);
        } else {
            System.out.println("TTN found: " + ttn.getKodTTN());
        }

        List<Client> clients = DAOClients.getAllClients();
        request.setAttribute("ttn", ttn);
        request.setAttribute("clients", clients);
        request.setAttribute("statuses", DeliveryStatus.values());

        if (ttn != null && ttn.getSendTime() != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            request.setAttribute("formattedSendTime", ttn.getSendTime().format(formatter));
        } else {
            request.setAttribute("formattedSendTime", "");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/newpost/edit_ttn.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long ttnId = Long.parseLong(request.getParameter("id_ttn"));
        Long receiverId = Long.parseLong(request.getParameter("receiver"));
        String manager = request.getParameter("manager");
        int numPoint = Integer.parseInt(request.getParameter("numPoint"));
        String kodTTN = request.getParameter("kodTTN");
        LocalDateTime sendTime = LocalDateTime.parse(request.getParameter("sendTime"));
        DeliveryStatus status = DeliveryStatus.valueOf(request.getParameter("status"));

        Client receiver = DAOClients.getClientById(receiverId);
        NewPostTTN ttn = new NewPostTTN(ttnId, receiver, manager, numPoint, kodTTN, sendTime, status);

        String result = DAONewPostTTN.updateTTN(ttn);
        if (result.contains("Error")) {
            request.setAttribute("error", result);
            doGet(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/newpost");
        }
    }
}
