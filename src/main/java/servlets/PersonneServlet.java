package servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

import models.Personne;
import services.PersonneServiceImpl;

import static utils.Util.escapeSpecialCharacters;

@WebServlet(name = "PersonneServlet", value = "/personne")
public class PersonneServlet extends HttpServlet {
    private final PersonneServiceImpl list = new PersonneServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        StringBuilder responseHtml = new StringBuilder();

        responseHtml.append(
                """
                        <!DOCTYPE html>
                        <html lang="fr">
                        <head>
                            <meta charset="UTF-8">
                            <meta http-equiv="X-UA-Compatible" content="IE=edge">
                            <meta name="viewport" content="width=device-width, initial-scale=1.0">
                            <link rel="stylesheet" href="css/style.css">
                            <title>Liste de personnes</title>
                        </head>
                        <body>
                            <div class="container">
                                <header>
                                     <h1>MonApp</h1>
                                     <nav>
                                         <a href="/demoServlets">Accueil</a>
                                         <a href="/demoServlets/produit">Produits</a>
                                         <a class="active" href="/demoServlets/personne">Personnes</a>
                                     </nav>
                                 </header>
                                <hr>
                                <h2>Liste des personnes</h2>
                                <main>
                                    <ul>
                        """);

        list.getAll().forEach(
                    p -> responseHtml.append("\t\t\t\t<li>")
                            .append(escapeSpecialCharacters(p.getPrenom()))
                            .append(" ")
                            .append(escapeSpecialCharacters(p.getNom()))
                            .append("</li>")
                            .append("\n")
            );

        responseHtml.append("""
                            </ul>
                            <form method="post">
                                <input type="text"
                                        placeholder="Prenom"
                                        maxlength="30"
                                        name="firstname"
                                        required
                                        autofocus>
                                <input type="text"
                                        placeholder="Nom"
                                        maxlength="30"
                                        name="lastname"
                                        required>
                                <input class="btn" type="submit" value="Ajouter">
                            </form>
                        </main>
                    </div><!-- end container -->
                </body>
                </html>
                """);

        PrintWriter out = response.getWriter();

        out.println(responseHtml);
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String prenom = request.getParameter("firstname");
        String nom = request.getParameter("lastname");

        if (prenom != null && !prenom.isBlank() && nom != null && !nom.isBlank()) {
            list.addPersonne(new Personne(prenom, nom));
            response.sendRedirect(request.getContextPath() + "/personne");
        } else {
            response.setStatus(400);
            out.println("prenom ou nom invalide");
        }
        out.close();
    }
}