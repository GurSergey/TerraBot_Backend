package main;

import config.Config;
import controllers.MappingServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.servlets.CrossOriginFilter;

public class Main {
        public static void configServlets()
        {

        }


        public static void main(String[] args) throws Exception {

            MappingServlet allRequestsServlet = new MappingServlet();
            
            ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
            context.addServlet(new ServletHolder(allRequestsServlet), "/*");

            Server server = new Server(Config.PORT);
            server.setHandler(context);
            FilterHolder filterHolder = new FilterHolder(CrossOriginFilter.class);
                        filterHolder.setInitParameter("allowedOrigins", "*");
                        filterHolder.setInitParameter("allowedMethods", "GET, POST");
                        context.addFilter(filterHolder, "/*", null);
            server.start();
            server.join();
        }
}
