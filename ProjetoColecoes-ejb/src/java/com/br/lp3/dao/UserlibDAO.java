package com.br.lp3.dao;

import com.br.lp3.entities.Userlibinfo;
import com.br.lp3.entities.Userlib;
import java.util.List;
import javax.ejb.Stateful;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

/**
 *
 * @author 1147106
 */
@Stateful
@LocalBean
public class UserlibDAO implements GenericDAO<Userlib> {

    @PersistenceContext(unitName = "BaseLP3-ejbPU",
            type = PersistenceContextType.TRANSACTION)
    private EntityManager em;

    @Override
    public void insert(Userlib e) {
        em.persist(e);
    }

    @Override
    public List<Userlib> findAll() {
        Query query = em.createNamedQuery("Userlib.findAll");
        return (List<Userlib>) query.getResultList();
    }

    @Override
    public Userlib findById(long id) {
        return em.find(Userlib.class, id);
    }

    public Userlib findByUsername(String username) {
        Query query = em.createNamedQuery("Userlib.findByUsername")
                .setParameter("username", username);
        Object object = null;
        try {
            object = query.getSingleResult();
            return (Userlib)object;
        } catch (NoResultException ex) {
            return null;
        }
    }
    
    public Userlib findByEmail(String email) {
        Query query = em.createNamedQuery("Userlibinfo.findByEmail")
                .setParameter("email", email);
        Object object = null;
        try {
            object = query.getSingleResult();
            return ((Userlibinfo)object).getUserlib();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public void modify(Userlib e) {
        em.merge(e);
    }

    @Override
    public void remove(Userlib e) {
        em.merge(e);
        em.remove(e);
    }

}