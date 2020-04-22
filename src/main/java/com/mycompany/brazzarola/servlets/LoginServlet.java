/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.brazzarola.servlets;

import com.mycompany.brazzarola.dao.Dao;
import com.mycompany.brazzarola.entities.Post;
import com.mycompany.brazzarola.other.Formatta;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.google.common.hash.Hashing;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 *
 * @author 17726
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        try (PrintWriter out = response.getWriter()) {

            String name = request.getParameter("name");
            String password = Hashing.sha256().hashString(request.getParameter("password"), StandardCharsets.UTF_8).toString();

            if (name.equals("admin") && password.equals(Hashing.sha256().hashString("admin", StandardCharsets.UTF_8).toString())) {

                HttpSession session = request.getSession();
                session.setAttribute("name", name);

                request.getRequestDispatcher("index.html").include(request, response);

                out.println("<form action=\"CreaPostServlet\" method=\"post\">");
                out.println("<div class=\"form-group\">");
                out.println("<label for=\"titolo\">Titolo</label>");
                out.println("<input type=\"text\" class=\"form-control\" id=\"titolo\" placeholder=\"Titolo\">");
                out.println("</div>");
                out.println("<div class=\"form-group\">");
                out.println("<label for=\"contenuto\">Contenuto</label>");
                out.println("<input type=\"text\" class=\"form-control\" id=\"contenuto\" placeholder=\"Contenuto\">");
                out.println("</div>");
                out.println("<button type=\"submit\" class=\"btn btn-default\">Crea Post</button>");
                out.println("</form>");
                out.println("<br>");
                out.println("<h4>Post:</h4>");

                List<Post> listaPost = Dao.getPostDao().findAll();
                out.print(Formatta.format(listaPost));

            } else if (Dao.getUtenteDao().findUtente(name, password)) {
                HttpSession session = request.getSession();
                session.setAttribute("name", name);

                request.getRequestDispatcher("index.html").include(request, response);

                List<Post> listaPost = Dao.getPostDao().findAll();
                out.print(Formatta.format(listaPost));
            } else {
                out.println("<h3>Sorry, username or password error!</h3>");
                out.println("<a href=\"login.html\">Login</a>");
            }
        }
    }
}
