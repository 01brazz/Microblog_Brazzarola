/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.brazzarola.entities;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author david
 */
@Entity
public class Post {

    @Id
    @GeneratedValue
    private Long id;

    @Basic
    private String titolo;

    @Basic
    private String autore;

    @Basic
    private String contenuto;

    @Basic
    private String dataOra;

    public Post() {
    }

    public Post(String titolo, String autore, String contenuto, String dataOra) {
        this.titolo = titolo;
        this.autore = autore;
        this.contenuto = contenuto;
        this.dataOra = dataOra;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public String getContenuto() {
        return contenuto;
    }

    public void setContenuto(String contenuto) {
        this.contenuto = contenuto;
    }

    public String getDataOra() {
        return dataOra;
    }

    public void setDataOra(String dataOra) {
        this.dataOra = dataOra;
    }
}
