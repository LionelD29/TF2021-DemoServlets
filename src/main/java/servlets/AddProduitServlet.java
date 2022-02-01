package servlets;

import dataAccess.ProduitDAO;
import models.Produit;
import services.ProduitService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AddProduitServlet", value = "/produit/add")
public class AddProduitServlet extends HttpServlet {

    private final ProduitService service = ProduitDAO.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/addProduit.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        try {
            String nom = request.getParameter("nom");
            String marque = request.getParameter("marque");
            double prix = Double.parseDouble(request.getParameter("prix"));

            if (nom == null || nom.isBlank() || marque == null || marque.isBlank()) {
                response.setStatus(400);
                out.println("marque ou nom non défini");
            } else {
                Produit p = new Produit(nom, marque, prix);
                if (service.insert(p)) {
                    response.setStatus(200);
                    response.sendRedirect(request.getContextPath() + "/produit");
                }
            }
        } catch (NumberFormatException e) {
            response.setStatus(400);
            out.print("id ou prix invalide");
        }
        out.close();
    }
}
