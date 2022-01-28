package servlets;

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

    private final ProduitService service = ProduitServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        out.print("<!doctype html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Ajout de produit</title>\n" +
                "    <link rel=\"stylesheet\" href=\"../css/style.css\">\n" +
                "    <link rel=\"stylesheet\" href=\"../css/produits.css\">\n" +
                "</head>\n" +
                "<body>\n" +
                "<div class=\"container\">\n" +
                "    <h1>Modification d'un produit</h1>\n" +
                "    <nav>\n" +
                "        <a href=\"/demoServlets/produit\">Retour</a>\n" +
                "    </nav>\n" +
                "    <form action=\""+ request.getContextPath() +"/produit/update\" method=\"post\">\n" +
                "        <input type=\"number\" name=\"id\" placeholder=\"id\"><br>\n" +
                "        <input type=\"text\" name=\"nom\" placeholder=\"nom\"><br>\n" +
                "        <input type=\"number\" name=\"prix\" placeholder=\"prix\"><br>\n" +
                "        <input type=\"submit\" value=\"Modifier\">\n" +
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
            int id = Integer.parseInt(request.getParameter("id"));
            String nom = request.getParameter("nom");
            double prix = Double.parseDouble(request.getParameter("prix"));

            if (nom != null && !nom.isBlank()) {
                ProduitForm form = new ProduitForm(nom, prix);
                try {
                    service.update(id, form);
                    response.setStatus(200);
                    out.println("Modification reussie");
                } catch (IllegalArgumentException e) {
                    response.setStatus(400);
                    out.println(e.getMessage());
                }
            } else {
                response.setStatus(400);
                out.println("Nom non conforme");
            }
        } catch (NumberFormatException e) {
            response.setStatus(400);
            out.println("id ou prix invalide");
        }

        response.sendRedirect(request.getContextPath() + "/produit");
        out.close();
    }
}