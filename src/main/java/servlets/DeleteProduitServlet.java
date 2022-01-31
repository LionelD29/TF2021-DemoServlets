package servlets;

import dataAccess.ProduitDAO;
import services.ProduitService;

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
        PrintWriter out = response.getWriter();

        out.print("<!doctype html>\n" +
                "<html lang=\"fr\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <link rel=\"stylesheet\" href=\"../css/style.css\">\n" +
                "    <link rel=\"stylesheet\" href=\"../css/produits.css\">\n" +
                "    <title>Suppression d'un produit</title>\n" +
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
                "    <h2>Suppression d'un produit</h2>\n" +
                "    <form action=\""+ request.getContextPath() +"/produit/delete\" method=\"post\">\n" +
                "        <input type=\"number\" name=\"id\" placeholder=\"Id\" required autofocus><br>\n" +
                "        <input class=\"btn\" type=\"submit\" value=\"Supprimer\">\n" +
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
