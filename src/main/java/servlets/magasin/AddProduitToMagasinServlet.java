package servlets.magasin;

import dataAccess.MagasinDAO;
import models.Magasin;
import models.Produit;
import services.MagasinService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AddProduitToMagasinServlet", value = "/magasin/addProduit")
public class AddProduitToMagasinServlet extends HttpServlet {
    MagasinService service = MagasinDAO.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            int magasinId = Integer.parseInt(request.getParameter("id"));
            Magasin magasin = service.getOne(magasinId);
            request.setAttribute("magasin", magasin);
            request.getRequestDispatcher("/jsp/magasin/addProduitMagasin.jsp").forward(request, response);
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
            Magasin magasin = service.getOne(magasinId);

            int produitId = Integer.parseInt(request.getParameter("produitId"));
            String nom = request.getParameter("nom");
            String marque = request.getParameter("marque");
            double prix = Double.parseDouble(request.getParameter("prix"));
            Produit p = new Produit(produitId, nom, marque, prix);
            
            if (nom.isBlank() || marque.isBlank()) {
                response.setStatus(400);
                out.println("nom ou marque invalide");
            } else if (!magasin.insertProduct(p)) {
                response.setStatus(400);
                out.println("Id déjà pris");
            } else {
                response.setStatus(200);
                response.sendRedirect(request.getContextPath() + "/magasin/detail?id=" + magasinId);
            }
        } catch (NumberFormatException e) {
            response.setStatus(400);
            out.println("idMagasin ou prix invalide");
        }
        out.close();
    }
}