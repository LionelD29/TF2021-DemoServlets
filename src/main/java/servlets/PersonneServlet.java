package servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

import models.Personne;
import services.PersonneServiceImpl;

@WebServlet(name = "PersonneServlet", value = "/personne")
public class PersonneServlet extends HttpServlet {
    private final PersonneServiceImpl list = PersonneServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/personne.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String prenom = request.getParameter("firstname");
        String nom = request.getParameter("lastname");

        if (prenom != null && !prenom.isBlank() && nom != null && !nom.isBlank()) {
            list.addPersonne(new Personne(prenom, nom));
            doGet(request, response);
        } else {
            response.setStatus(400);
            out.println("prenom ou nom invalide");
        }
        out.close();
    }
}