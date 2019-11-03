package main;

import controllers.MappingServlet;
import servlets.MainServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Main {
        public static void configServlets()
        {

        }


        public static void main(String[] args) throws Exception {
            MappingServlet allRequestsServlet = new MappingServlet();


            ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
            context.addServlet(new ServletHolder(allRequestsServlet), "/*");


            Server server = new Server(9001);
            server.setHandler(context);

            server.start();
            server.join();
        }
}
