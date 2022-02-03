package be.technifutur.demoServlets.servlets.produit;

import be.technifutur.demoServlets.dataAccess.ProduitDAO;
import be.technifutur.demoServlets.services.ProduitService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "DeleteProduitServlet", value = "/produit/delete")
public class DeleteProduitServlet extends HttpServlet {
    private final ProduitService service = ProduitDAO.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/produit/deleteProduit.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            if (service.delete(id) != null) {
                response.sendRedirect(request.getContextPath() + "/produit");
            } else {
                response.setStatus(400);
                out.println("id invalide");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
