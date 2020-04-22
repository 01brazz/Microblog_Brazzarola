/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.brazzarola.other;

import com.mycompany.brazzarola.dao.Dao;
import com.mycompany.brazzarola.entities.Commento;
import com.mycompany.brazzarola.entities.Post;
import java.util.List;

/**
 *
 * @author david
 */
public class Formatta {

    public static String format(List<Post> listaP) {
        String out = "<div class=\"list-group\">";
        for (Post p : listaP) {
            out += "<a href=\"#\" class=\"list-group-item list-group-item-action\" style=\"width: 400px\">";
            out += "<div class=\"d-flex w-100 justify-content-between\">";
            out += "<h4 class=\"mb-1\">" + p.getTitolo() + "</h4>";
            out += "<small>" + p.getDataOra() + "</small>";
            out += "</div>";
            out += "<p class=\"mb-1\">" + p.getContenuto() + "</p>";
            out += "<small>" + p.getAutore() + "</small>";
            out += "<br><br>";

            out += "<form action=\"CommentaServlet\" method=\"post\">";
            out += "<div class=\"form-group\">";
            out += "<label for=\"commento\">Commento</label>";
            out += "<textarea style=\"width: 300px\" class=\"form-control\" id=\"exampleFormControlTextarea1\" rows=\"2\"></textarea>";
            out += "</div>";
            out += "</form>";

            out += "<br>";
            out += "<ul class=\"list-group list-group-flush\" style=\"color: black\">";

            List<Commento> listaC = Dao.getCommentoDao().findCommentoByPostId(p.getId());

            for (Commento c : listaC) {
                out += "<li class=\"list-group-item\">";
                out += "<div class=\"list-group-item list-group-item-action\" style=\"width: 300px\">";
                out += "<div class=\"d-flex w-100 justify-content-between\">";
                out += "<small>" + c.getDataOra() + "</small>";
                out += "</div>";
                out += "<p class=\"mb-1\">" + c.getContenuto() + "</p>";
                out += "<small>" + c.getAutore() + "</small>";
                out += "</div>";
                out += "</li>";

                out += "</ul>";
                out += "</a>";
                out += "<br>";
            }
        }
        return out;
    }
}
