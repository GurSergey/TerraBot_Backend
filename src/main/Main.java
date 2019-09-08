package main;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.MainServlet;

public class Main {

        public static void main(String[] args) throws Exception {
            MainServlet allRequestsServlet = new MainServlet();

            ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
            context.addServlet(new ServletHolder(allRequestsServlet), "/*");

            Server server = new Server(8080);
            server.setHandler(context);

            server.start();
            server.join();
        }
}
