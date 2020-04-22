/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.brazzarola.dao;

import com.mycompany.brazzarola.entities.Utente;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author david
 */
public class UtenteDao {

    private final EntityManager em;
    String PERSISTENCE_UNIT_NAME = "persistence";

    public UtenteDao() {
        this.em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME)
                .createEntityManager();
    }

    public boolean insertUtente(Utente u) {
        em.getTransaction().begin();
        try {
            em.persist(u);
            // -- workaround cache entity manager
            em.flush();
            em.clear();
            // --
            em.getTransaction().commit();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        }
    }

    public List<Utente> findAll() {
        TypedQuery<Utente> typedQuery = em.createQuery("SELECT U FROM Utente U", Utente.class);
        List<Utente> listU = typedQuery.getResultList();
        return listU;
    }

    public boolean findUtente(String name, String password) {
        TypedQuery<Utente> typedQuery = em.createQuery("SELECT U FROM Utente U WHERE U.name LIKE :name AND U.password LIKE :password", Utente.class);
        List<Utente> listU = typedQuery.setParameter("name", name).setParameter("password", password).getResultList();
        return !listU.isEmpty();
    }

    public boolean findUserByName(String name) {
        TypedQuery<Utente> typedQuery = em.createQuery("SELECT U FROM Utente U WHERE U.name LIKE :name", Utente.class);
        List<Utente> listU = typedQuery.setParameter("name", name).getResultList();
        return !listU.isEmpty();
    }
}
