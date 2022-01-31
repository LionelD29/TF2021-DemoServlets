package servlets;

import dataAccess.ProduitDAO;
import models.Produit;
import services.ProduitService;
import services.ProduitServiceImpl;

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
        PrintWriter out = response.getWriter();

        out.print("<!doctype html>\n" +
                "<html lang=\"fr\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <link rel=\"stylesheet\" href=\"../css/style.css\">\n" +
                "    <link rel=\"stylesheet\" href=\"../css/produits.css\">\n" +
                "    <title>Ajout d'un produit</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div class=\"container\">\n" +
                "    <header>\n" +
                "       <h1>MonApp</h1>\n" +
                "       <nav>\n" +
                "           <a href=\"/demoServlets/produit\">Retour</a>\n" +
                "       </nav>\n" +
                "    </header>\n" +
                "    <hr>\n" +
                "    <h2>Ajout d'un produit</h2>\n" +
                "    <form action=\""+ request.getContextPath() +"/produit/add\" method=\"post\">\n" +
                "        <input type=\"text\" name=\"nom\" placeholder=\"Nom\" maxlength=\"30\" autofocus><br>\n" +
                "        <input type=\"text\" name=\"marque\" placeholder=\"Marque\" maxlength=\"30\"><br>\n" +
                "        <input type=\"text\" name=\"prix\" placeholder=\"Prix\" required><br>\n" +
                "        <input class=\"btn\" type=\"submit\" value=\"Envoyer\">\n" +
                "    </form>\n" +
                "</div><!-- end container -->\n" +
                "</body>" +
                "</html>");
        out.close();
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
