package be.technifutur.demoServlets.servlets.magasin;

import be.technifutur.demoServlets.dataAccess.MagasinDAO;
import be.technifutur.demoServlets.models.Magasin;
import be.technifutur.demoServlets.services.MagasinService;

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
        request.getRequestDispatcher("/jsp/magasin/addMagasin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        try {
            String nom = request.getParameter("name");
            String rue = request.getParameter("street");
            int numero = Integer.parseInt(request.getParameter("number"));
            String ville = request.getParameter("city");
            String cp = request.getParameter("postalCode");
            int superficie = Integer.parseInt(request.getParameter("area"));

            if (nom.isBlank() || rue.isBlank() || ville.isBlank() || cp.isBlank()) {
                response.setStatus(400);
                out.println("nom, rue, ville et/ou code postal invalide");
            } else {
                Magasin m = new Magasin(nom, rue, ville, cp, numero, superficie);

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
            out.println("numero ou superficie invalide");
        }

        out.close();
    }
}
