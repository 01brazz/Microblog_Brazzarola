/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.brazzarola.dao;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author david
 */
public class Dao {

    String PERSISTENCE_UNIT_NAME = "persistence";
    private final EntityManager em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME)
            .createEntityManager();
    static CommentoDao commentoDao = new CommentoDao();
    static PostDao postDao = new PostDao();
    static UtenteDao utenteDao = new UtenteDao();

    public static CommentoDao getCommentoDao() {
        return Dao.commentoDao;
    }

    public static PostDao getPostDao() {
        return Dao.postDao;
    }

    public static UtenteDao getUtenteDao() {
        return Dao.utenteDao;
    }
}
