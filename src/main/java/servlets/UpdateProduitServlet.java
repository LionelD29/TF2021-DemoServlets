package servlets;

import dataAccess.ProduitDAO;
import models.Produit;
import models.ProduitForm;
import services.ProduitService;
import services.ProduitServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "UpdateProduitServlet", value = "/produit/update")
public class UpdateProduitServlet extends HttpServlet {

    private final ProduitService service = ProduitDAO.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/updateProduit.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String nom = request.getParameter("nom");
            String marque = request.getParameter("marque");
            double prix = Double.parseDouble(request.getParameter("prix"));

            ProduitForm form = new ProduitForm(nom, prix, marque);
            try {
                service.update(id, form);
                response.setStatus(200);
                response.sendRedirect(request.getContextPath() + "/produit");
            } catch (IllegalArgumentException e) {
                response.setStatus(400);
                out.println(e.getMessage());
            }

        } catch (NumberFormatException e) {
            response.setStatus(400);
            out.println("id ou prix invalide");
        }
        out.close();
    }
}