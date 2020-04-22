/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.brazzarola.dao;

import com.mycompany.brazzarola.entities.Post;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author david
 */
public class PostDao {

    private final EntityManager em;
    String PERSISTENCE_UNIT_NAME = "persistence";

    public PostDao() {
        this.em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME)
                .createEntityManager();
    }

    public boolean insertPost(Post p) {
        em.getTransaction().begin();
        try {
            em.persist(p);
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

    public List<Post> findAll() {
        TypedQuery<Post> typedQuery = em.createQuery("SELECT P FROM Post P", Post.class);
        List<Post> listP = typedQuery.getResultList();
        return listP;
    }
}
