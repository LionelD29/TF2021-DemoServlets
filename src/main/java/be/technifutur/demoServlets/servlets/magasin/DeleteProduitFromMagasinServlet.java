package be.technifutur.demoServlets.servlets.magasin;

import be.technifutur.demoServlets.dataAccess.MagasinDAO;
import be.technifutur.demoServlets.models.Magasin;
import be.technifutur.demoServlets.services.MagasinService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "DeleteProduitFromMagasinServlet", value = "/magasin/deleteProduit")
public class DeleteProduitFromMagasinServlet extends HttpServlet {
    MagasinService service = MagasinDAO.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            int magasinId = Integer.parseInt(request.getParameter("id"));
            Magasin magasin = service.getOne(magasinId);
            request.setAttribute("magasin", magasin);
            request.getRequestDispatcher("/jsp/magasin/deleteProduitMagasin.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            response.setStatus(400);
            out.println("id invalide");
        }
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            int magasinId = Integer.parseInt(request.getParameter("magasinId"));
            int produitId = Integer.parseInt(request.getParameter("produitId"));

            if (service.removeProduct(magasinId, produitId) != null) {
                response.setStatus(200);
                response.sendRedirect(request.getContextPath() + "/magasin/detail?id=" + magasinId);
            } else {
                response.setStatus(400);
                out.println("suppression échouée");
            }
        } catch (NumberFormatException e) {
            response.setStatus(400);
            out.println("idMagasin ou prix invalide");
        }
        out.close();
    }
}
