package servlets;

import dataAccess.MagasinDAO;
import models.Magasin;
import services.MagasinService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "GetOneMagasinServlet", value = "/magasin/detail")
public class GetOneMagasinServlet extends HttpServlet {
    private final MagasinService service = MagasinDAO.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Magasin magasin = service.getOne(id);
            if (magasin == null) {
                response.setStatus(400);
                out.println("Pas de magasin avec cet ID");
            } else {
                response.setStatus(200);
                request.setAttribute("magasin", magasin);
                request.getRequestDispatcher("/jsp/magasinDetail.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            response.setStatus(400);
            out.println("id invalide");
        }

        out.close();
    }
}
