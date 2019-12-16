package controllers;

import com.google.gson.GsonBuilder;
import exception.ApplicationException;
import org.apache.commons.text.WordUtils;
import org.eclipse.jetty.server.Request;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;


public class MappingServlet extends HttpServlet {
    private final String NAME_PACKAGE_OF_CONTROLLERS = "controllers";
    private final String UNKNOWN_ROUTE = "This route is unknown";
    private final String CONTROLLER_CONSTANT_NAME = "Controller";
    private final String METHOD_CONSTANT_NAME =  "method";
    private final int SIZE_OF_ROUTE = 2;
    private final int NUM_OF_NAME_CLASS = 0;
    private final int NUM_OF_METHOD_NAME = 1;

    private void routeUnknown(HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        out.print(UNKNOWN_ROUTE);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MultipartConfigElement multipartConfigElement = new MultipartConfigElement((String)null);
        req.setAttribute(Request.__MULTIPART_CONFIG_ELEMENT, multipartConfigElement);
        String routeString = req.getRequestURI();
        ArrayList<String> route = new ArrayList(Arrays.asList(routeString.split("/")));
        route.remove(0);
        if(route.size()!=SIZE_OF_ROUTE)
        {
            this.routeUnknown(resp);
            return;
        }
        String controllerName = WordUtils.capitalize(route.get(NUM_OF_NAME_CLASS))+CONTROLLER_CONSTANT_NAME;
        String methodName = METHOD_CONSTANT_NAME+WordUtils.capitalize(route.get(NUM_OF_METHOD_NAME));

        try {
            Class controllerClass = Class.forName(NAME_PACKAGE_OF_CONTROLLERS+"."+controllerName);
            Object controller = controllerClass.getDeclaredConstructor().newInstance();
            Method method = controllerClass.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            Object[] args = new Object[] { req, resp };
            method.invoke(controller, args);
        }
        catch (ClassNotFoundException | NoSuchMethodException e)
        {
            this.routeUnknown(resp);
        } catch ( IllegalAccessException e)

        {
//            PrintWriter out = resp.getWriter();
//            GsonBuilder builder = new GsonBuilder();
//            Gson gson = builder.create();
//            out.print(e.toString());
            PrintWriter out = resp.getWriter();
            out.print(e.toString());
        } catch (InstantiationException e) {
            PrintWriter out = resp.getWriter();
            out.print(e.toString());
        } catch (InvocationTargetException e) {
            PrintWriter out = resp.getWriter();
            out.print(e.getCause().toString());
        }


    }
}
