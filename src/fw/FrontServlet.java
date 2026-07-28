package fw;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;

public class FrontServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        findResource(req, resp);
    }

    private void findResource(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String path = req.getRequestURI().substring(req.getContextPath().length());
        URL resource = getServletContext().getResource(path);

        
        boolean resourceExist = (resource != null);

        if (resourceExist && !path.endsWith(".class")) {
            getServletContext().getNamedDispatcher("default").forward(req, resp);
            return;
        }


        resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
            out.println("<html>");
            out.println("<head><title>FrontServlet</title></head>");
            out.println("<body>");
            out.println("<h1>Réponse FrontServlet</h1>");
            out.println("<p>Ressource demandée : " + path + "</p>");
            if (!resourceExist) {
                out.println("<p style='color:red'>Ressource introuvable</p>");
            }
            out.println("</body>");
            out.println("</html>");
        }
    }
}
