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

import static utils.Util.escapeSpecialCharacters;

@WebServlet(name = "ProduitGetAllServlet", value = "/produit")
public class ProduitGetAllServlet extends HttpServlet {

    ProduitService service = ProduitServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Produit> list = service.getAll();
        PrintWriter out = resp.getWriter();
        out.println(
                """
                <!DOCTYPE html>
                <html lang="fr">
                <head>
                    <meta charset="UTF-8">
                    <meta http-equiv="X-UA-Compatible" content="IE=edge">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <link rel="stylesheet" href="css/style.css">
                    <link rel="stylesheet" href="css/produits.css">
                    <title>Liste des produits</title>
                </head>
                <body>
                <div class="container">
                    <header>
                        <h1>MonApp</h1>
                        <nav>
                            <a href="/demoServlets">Accueil</a>
                            <a class="active" href="/demoServlets/produit">Produits</a>
                            <a href="/demoServlets/personne">Personnes</a>
                        </nav>
                    </header>
                    <hr>
                    <h2>Liste des produits</h2>
                    <ul>
                """);

        list.stream()
            .sorted(Comparator.comparingInt(Produit::getId))
            .forEach(produit -> {
                out.print("<li>");
                out.print(produit.getId());
                out.print(" | ");
                out.print(escapeSpecialCharacters(produit.getNom()));
                out.print(" | ");
                out.print(escapeSpecialCharacters(produit.getMarque()));
                out.print(" | ");
                out.print(produit.getPrix());
                out.println("$</li>");
        });

        out.print(
                """
                    </ul>
                    <div class="options">
                        <a class="btn" href="/demoServlets/produit/add">Ajouter</a>
                        <a class="btn" href="/demoServlets/produit/update">Modifier</a>
                    </div>
                </div><!-- end container -->
                </body>
                </html>
                """
        );
        out.close();
    }
}