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
                "<html lang=\"fr\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <link rel=\"stylesheet\" href=\"../css/style.css\">\n" +
                "    <link rel=\"stylesheet\" href=\"../css/produits.css\">\n" +
                "    <title>Modification d'un produit</title>\n" +
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
                "    <h2>Modification d'un produit</h2>\n" +
                "    <form action=\""+ request.getContextPath() +"/produit/update\" method=\"post\">\n" +
                "        <input type=\"number\" name=\"id\" placeholder=\"Id\"><br>\n" +
                "        <input type=\"text\" name=\"nom\" placeholder=\"Nom\" maxlength=\"30\"><br>\n" +
                "        <input type=\"text\" name=\"marque\" placeholder=\"Marque\" maxlength=\"30\"><br>\n" +
                "        <input type=\"text\" name=\"prix\" placeholder=\"Prix\"><br>\n" +
                "        <input class=\"btn\" type=\"submit\" value=\"Modifier\">\n" +
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
            String marque = request.getParameter("marque");
            double prix = Double.parseDouble(request.getParameter("prix"));

            if (nom != null && !nom.isBlank() && marque != null && !marque.isBlank()) {
                ProduitForm form = new ProduitForm(nom, prix, marque);
                try {
                    service.update(id, form);
                    response.setStatus(200);
                    response.sendRedirect(request.getContextPath() + "/produit");
                } catch (IllegalArgumentException e) {
                    response.setStatus(400);
                    out.println(e.getMessage());
                }
            } else {
                response.setStatus(400);
                out.println("Nom ou marque non conforme");
            }
        } catch (NumberFormatException e) {
            response.setStatus(400);
            out.println("id ou prix invalide");
        }
        out.close();
    }
}