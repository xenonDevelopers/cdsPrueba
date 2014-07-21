/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.Tabulador;
import com.utez.integracion.entity.TabuladorConcepto;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class TabuladorConceptoJpaController implements Serializable {

    public TabuladorConceptoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TabuladorConcepto tabuladorConcepto) {
        if (tabuladorConcepto.getTabuladorList() == null) {
            tabuladorConcepto.setTabuladorList(new ArrayList<Tabulador>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Tabulador> attachedTabuladorList = new ArrayList<Tabulador>();
            for (Tabulador tabuladorListTabuladorToAttach : tabuladorConcepto.getTabuladorList()) {
                tabuladorListTabuladorToAttach = em.getReference(tabuladorListTabuladorToAttach.getClass(), tabuladorListTabuladorToAttach.getIdTabulador());
                attachedTabuladorList.add(tabuladorListTabuladorToAttach);
            }
            tabuladorConcepto.setTabuladorList(attachedTabuladorList);
            em.persist(tabuladorConcepto);
            for (Tabulador tabuladorListTabulador : tabuladorConcepto.getTabuladorList()) {
                TabuladorConcepto oldIdTabuladorconceptoOfTabuladorListTabulador = tabuladorListTabulador.getIdTabuladorconcepto();
                tabuladorListTabulador.setIdTabuladorconcepto(tabuladorConcepto);
                tabuladorListTabulador = em.merge(tabuladorListTabulador);
                if (oldIdTabuladorconceptoOfTabuladorListTabulador != null) {
                    oldIdTabuladorconceptoOfTabuladorListTabulador.getTabuladorList().remove(tabuladorListTabulador);
                    oldIdTabuladorconceptoOfTabuladorListTabulador = em.merge(oldIdTabuladorconceptoOfTabuladorListTabulador);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TabuladorConcepto tabuladorConcepto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TabuladorConcepto persistentTabuladorConcepto = em.find(TabuladorConcepto.class, tabuladorConcepto.getIdTabuladorconcepto());
            List<Tabulador> tabuladorListOld = persistentTabuladorConcepto.getTabuladorList();
            List<Tabulador> tabuladorListNew = tabuladorConcepto.getTabuladorList();
            List<Tabulador> attachedTabuladorListNew = new ArrayList<Tabulador>();
            for (Tabulador tabuladorListNewTabuladorToAttach : tabuladorListNew) {
                tabuladorListNewTabuladorToAttach = em.getReference(tabuladorListNewTabuladorToAttach.getClass(), tabuladorListNewTabuladorToAttach.getIdTabulador());
                attachedTabuladorListNew.add(tabuladorListNewTabuladorToAttach);
            }
            tabuladorListNew = attachedTabuladorListNew;
            tabuladorConcepto.setTabuladorList(tabuladorListNew);
            tabuladorConcepto = em.merge(tabuladorConcepto);
            for (Tabulador tabuladorListOldTabulador : tabuladorListOld) {
                if (!tabuladorListNew.contains(tabuladorListOldTabulador)) {
                    tabuladorListOldTabulador.setIdTabuladorconcepto(null);
                    tabuladorListOldTabulador = em.merge(tabuladorListOldTabulador);
                }
            }
            for (Tabulador tabuladorListNewTabulador : tabuladorListNew) {
                if (!tabuladorListOld.contains(tabuladorListNewTabulador)) {
                    TabuladorConcepto oldIdTabuladorconceptoOfTabuladorListNewTabulador = tabuladorListNewTabulador.getIdTabuladorconcepto();
                    tabuladorListNewTabulador.setIdTabuladorconcepto(tabuladorConcepto);
                    tabuladorListNewTabulador = em.merge(tabuladorListNewTabulador);
                    if (oldIdTabuladorconceptoOfTabuladorListNewTabulador != null && !oldIdTabuladorconceptoOfTabuladorListNewTabulador.equals(tabuladorConcepto)) {
                        oldIdTabuladorconceptoOfTabuladorListNewTabulador.getTabuladorList().remove(tabuladorListNewTabulador);
                        oldIdTabuladorconceptoOfTabuladorListNewTabulador = em.merge(oldIdTabuladorconceptoOfTabuladorListNewTabulador);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tabuladorConcepto.getIdTabuladorconcepto();
                if (findTabuladorConcepto(id) == null) {
                    throw new NonexistentEntityException("The tabuladorConcepto with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TabuladorConcepto tabuladorConcepto;
            try {
                tabuladorConcepto = em.getReference(TabuladorConcepto.class, id);
                tabuladorConcepto.getIdTabuladorconcepto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tabuladorConcepto with id " + id + " no longer exists.", enfe);
            }
            List<Tabulador> tabuladorList = tabuladorConcepto.getTabuladorList();
            for (Tabulador tabuladorListTabulador : tabuladorList) {
                tabuladorListTabulador.setIdTabuladorconcepto(null);
                tabuladorListTabulador = em.merge(tabuladorListTabulador);
            }
            em.remove(tabuladorConcepto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TabuladorConcepto> findTabuladorConceptoEntities() {
        return findTabuladorConceptoEntities(true, -1, -1);
    }

    public List<TabuladorConcepto> findTabuladorConceptoEntities(int maxResults, int firstResult) {
        return findTabuladorConceptoEntities(false, maxResults, firstResult);
    }

    private List<TabuladorConcepto> findTabuladorConceptoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from TabuladorConcepto as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public TabuladorConcepto findTabuladorConcepto(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TabuladorConcepto.class, id);
        } finally {
            em.close();
        }
    }

    public int getTabuladorConceptoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from TabuladorConcepto as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
