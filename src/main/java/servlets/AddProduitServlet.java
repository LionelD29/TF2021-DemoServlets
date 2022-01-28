package servlets;

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
                "    <h1>Ajout d'un produit</h1>\n" +
                "    <nav>\n" +
                "        <a href=\"/demoServlets/produit\">Retour</a>\n" +
                "    </nav>\n" +
                "    <form action=\""+ request.getContextPath() +"/produit/add\" method=\"post\">\n" +
                "        <input type=\"number\" name=\"id\" placeholder=\"id\"><br>\n" +
                "        <input type=\"text\" name=\"nom\" placeholder=\"nom\"><br>\n" +
                "        <input type=\"text\" name=\"marque\" placeholder=\"marque\"><br>\n" +
                "        <input type=\"number\" name=\"prix\" placeholder=\"prix\"><br>\n" +
                "        <input type=\"submit\" value=\"Envoyer\">\n" +
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

            if (nom == null || marque == null) {
                response.setStatus(400);
                out.println("marque ou nom non défini");
            } else {
                Produit p = new Produit(id, nom, marque, prix);
                if (service.insert(p)) {
                    response.setStatus(200);
                    response.sendRedirect(request.getContextPath() + "/produit");
                } else {
                    response.setStatus(400);
                    out.println("id deja pris");
                }
            }
        } catch (NumberFormatException e) {
            response.setStatus(400);
            out.print("id ou prix invalide");
        }
        out.close();
    }
}
