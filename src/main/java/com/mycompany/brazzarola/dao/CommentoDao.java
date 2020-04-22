/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.brazzarola.dao;

import com.mycompany.brazzarola.entities.Commento;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author david
 */
public class CommentoDao {

    private final EntityManager em;
    String PERSISTENCE_UNIT_NAME = "persistence";

    public CommentoDao() {
        this.em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME)
                .createEntityManager();
    }

    public boolean insertCommento(Commento c) {
        em.getTransaction().begin();
        try {
            em.persist(c);
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

    public List<Commento> findAll() {
        TypedQuery<Commento> typedQuery = em.createQuery("SELECTC C FROM Commento C", Commento.class);
        List<Commento> listC = typedQuery.getResultList();
        return listC;
    }

    public List<Commento> findCommentoByPostId(Long postId) {
        TypedQuery<Commento> typedQuery = em.createQuery("SELECT C FROM Commento C WHERE c.postId = :postId", Commento.class);
        List<Commento> listC = typedQuery.setParameter("postId", postId).getResultList();
        return listC;
    }
}
