/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.brazzarola.servlets;

import com.mycompany.brazzarola.dao.Dao;
import com.mycompany.brazzarola.entities.Commento;
import com.mycompany.brazzarola.entities.Post;
import com.mycompany.brazzarola.other.Formatta;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author david
 */
public class CommentaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        try (PrintWriter out = response.getWriter()) {

            HttpSession session = request.getSession(false);
            String contenuto = request.getParameter("contenuto");
            if (!"".equals(contenuto)) {

                String postId = request.getParameter("postId");

                LocalDateTime dataOra = LocalDateTime.now();
                DateTimeFormatter dataOra2 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                String dataOra3 = dataOra.format(dataOra2);

                Commento c = new Commento((String) session.getAttribute("name"), contenuto, dataOra3, Long.parseLong(postId));
                Dao.getCommentoDao().insertCommento(c);
            }

            if ("admin".equals(session.getAttribute("name"))) {
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
            } else {
                request.getRequestDispatcher("index.html").include(request, response);

                List<Post> listaPost = Dao.getPostDao().findAll();
                out.print(Formatta.format(listaPost));
            }
        }
    }
}
