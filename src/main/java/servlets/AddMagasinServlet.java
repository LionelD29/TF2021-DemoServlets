package servlets;

import dataAccess.MagasinDAO;
import models.Magasin;
import services.MagasinService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AddMagasinServlet", value = "/magasin/addMagasin")
public class AddMagasinServlet extends HttpServlet {
    private final MagasinService service = MagasinDAO.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/addMagasin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String nom = request.getParameter("name");
            String rue = request.getParameter("street");
            String ville = request.getParameter("city");
            String cp = request.getParameter("postalCode");
            String tel = request.getParameter("phoneNumber");
            int superficie = Integer.parseInt(request.getParameter("area"));

            if (nom.isBlank() || rue.isBlank() || ville.isBlank() || cp.isBlank()) {
                response.setStatus(400);
                out.println("nom, rue, ville et/ou code postal invalide");
            } else {
                Magasin m = new Magasin(id, nom, rue, ville, cp, tel, superficie);

                if (!service.insert(m)) {
                    response.setStatus(400);
                    out.println("id de magasin deja pris");
                } else {
                    response.setStatus(200);
                    response.sendRedirect(request.getContextPath() + "/magasin");
                }
            }
        } catch (NumberFormatException e) {
            response.setStatus(400);
            out.println("id, tel ou superficie invalide");
        }

        out.close();
    }
}
