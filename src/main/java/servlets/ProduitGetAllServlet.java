package servlets;

import models.Produit;
import services.ProduitService;
import services.ProduitServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.List;

@WebServlet(name = "ProduitGetAllServlet", value = "/produit")
public class ProduitGetAllServlet extends HttpServlet {

    ProduitService service = ProduitServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Produit> list = service.getAll();
        PrintWriter out = resp.getWriter();
        out.println(
                """
                <!doctype html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <link rel="stylesheet" href="css/style.css">
                    <link rel="stylesheet" href="css/personnes.css">
                    <title>Get all produit</title>
                </head>
                <body>
                <div class="container">
                    <h1>Liste des produits</h1>
                    <nav>
                        <a href="/demoServlets">Accueil</a>
                        <a href="/demoServlets/personne">Page des personnes</a>
                        <a href="/demoServlets/produit/add">Ajouter un produit</a>
                        <a href="/demoServlets/produit/update">Modifier un produit</a>
                    </nav>
                    <ul>
                """);

        list.stream()
            .sorted(Comparator.comparingInt(Produit::getId))
            .forEach(produit -> {
                out.print("<li>");
                out.print(produit.getId());
                out.print(" | ");
                out.print(produit.getNom());
                out.print(" | ");
                out.print(produit.getMarque());
                out.print(" | ");
                out.print(produit.getPrix());
                out.println("$</li>");
        });

        out.print(
                """
                </ul>
                </div><!-- end container -->
                </body>
                </html>
                """
        );
        out.close();
    }
}