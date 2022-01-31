package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "RequestServlet", value = "/request")
public class RequestServlet extends HttpServlet {
    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        PrintWriter out = res.getWriter();

        String method = req.getMethod();
        String uri = req.getRequestURI();
        String protocol = req.getProtocol();

        Map<String, String> headers = new HashMap<>();
        Enumeration<String> headerNames = req.getHeaderNames();
        while(headerNames.hasMoreElements()){
            String header = headerNames.nextElement();
            headers.put(header, req.getHeader(header));
        }

        String body = req.getReader()
                .lines()
                .reduce("",(acc, line) -> acc + line);

        out.print(method);
        out.print(' ');
        out.print(uri);
        out.print(' ');
        out.println(protocol);
        out.println(req.getContextPath());

        headers.forEach((key, value) -> out.println(key + ":" + value));

        out.println();

        out.print(body);
        out.close();
    }
}