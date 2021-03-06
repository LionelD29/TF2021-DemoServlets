package be.technifutur.demoServlets.servlets.magasin;

import be.technifutur.demoServlets.dataAccess.MagasinDAO;
import be.technifutur.demoServlets.models.Magasin;
import be.technifutur.demoServlets.services.MagasinService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

@WebServlet(name = "GetAllMagasinServlet", value = "/magasin")
public class GetAllMagasinServlet extends HttpServlet {
    MagasinService service = MagasinDAO.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Magasin> list = service.getAll()
                .stream()
                .sorted(Comparator.comparingInt(Magasin::getId))
                .toList();

        request.setAttribute("list", list);
        request.getRequestDispatcher("/jsp/magasin/magasin.jsp").forward(request, response);
    }
}
