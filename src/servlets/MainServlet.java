package servlets;

import services.ExecutorCode;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        ExecutorCode executorCode = new ExecutorCode();
        executorCode.exec();
        PrintWriter out = resp.getWriter();
        out.print("<h1>Hello Servlet</h1>");

    }
}
