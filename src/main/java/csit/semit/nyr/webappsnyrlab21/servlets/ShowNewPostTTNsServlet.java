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
        List<NewPostTTN> ttns = DAONewPostTTN.getAllTTNs();
        request.setAttribute("ttns", ttns);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/newpost/ttns.jsp");
        dispatcher.forward(request, response);
    }
}
