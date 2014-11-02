package net.golovach.example.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // set headers
        res.addHeader("Content-Type", "text/html; charset=utf-8");

        // write body
        PrintWriter writer = res.getWriter();
        writer.println("<html>");
        writer.println("  <body>");
        writer.println("    <p>Hello!</p>");
        writer.println("  </body>");
        writer.println("</html>");
    }
}
