package csit.semit.nyr.webappsnyrlab21.servlets;

import csit.semit.nyr.webappsnyrlab21.daohbn.DAONewPostTTN;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/newpost/delete")
public class NewPostTTNDeleteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the ID from the form submission
        String ttnIdStr = request.getParameter("id_ttn");

        if (ttnIdStr != null) {
            try {
                Long ttnId = Long.parseLong(ttnIdStr);

                // Attempt to delete the TTN using the DAO
                String result = DAONewPostTTN.deleteTTN(ttnId);

                if (result.contains("Error")) {
                    request.setAttribute("error", result);
                }
            } catch (NumberFormatException e) {
                request.setAttribute("error", "Invalid TTN ID format.");
            }
        } else {
            request.setAttribute("error", "No TTN ID provided.");
        }

        // Redirect back to the TTN list page
        response.sendRedirect(request.getContextPath() + "/newpost");
    }
}
