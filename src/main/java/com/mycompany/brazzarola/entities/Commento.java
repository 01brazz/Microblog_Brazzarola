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
public class Commento {

    @Id
    @GeneratedValue
    private Long id;

    @Basic
    private String autore;

    @Basic
    private String contenuto;

    @Basic
    private String dataOra;

    @Basic
    private Long postId;

    public Commento() {
    }

    public Commento(String autore, String contenuto, String dataOra, Long postId) {
        this.autore = autore;
        this.contenuto = contenuto;
        this.dataOra = dataOra;
        this.postId = postId;
    }

    public Long getId() {
        return id;
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

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }
}
