/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.brazzarola.servlets;

import com.mycompany.brazzarola.dao.Dao;
import com.mycompany.brazzarola.entities.Utente;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.common.hash.Hashing;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author david
 */
public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        try (PrintWriter out = response.getWriter()) {

            String name = request.getParameter("name");
            String password = request.getParameter("password");

            if (Dao.getUtenteDao().findUserByName(name)) {
                out.print("<h3>User already exists!</h3>");
                out.println("<a href=\"login.html\">Login</a>");
            } else {
                Utente u = new Utente(name, Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString());
                Dao.getUtenteDao().insertUtente(u);
                request.getRequestDispatcher("index.html").include(request, response);
            }
        }
    }
}
