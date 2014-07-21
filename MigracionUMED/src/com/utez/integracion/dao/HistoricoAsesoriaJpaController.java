/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.Asesoria;
import com.utez.integracion.entity.HistoricoAsesoria;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class HistoricoAsesoriaJpaController implements Serializable {

    public HistoricoAsesoriaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HistoricoAsesoria historicoAsesoria) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Asesoria idAsesoria = historicoAsesoria.getIdAsesoria();
            if (idAsesoria != null) {
                idAsesoria = em.getReference(idAsesoria.getClass(), idAsesoria.getIdAsesoria());
                historicoAsesoria.setIdAsesoria(idAsesoria);
            }
            em.persist(historicoAsesoria);
            if (idAsesoria != null) {
                idAsesoria.getHistoricoAsesoriaList().add(historicoAsesoria);
                idAsesoria = em.merge(idAsesoria);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HistoricoAsesoria historicoAsesoria) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HistoricoAsesoria persistentHistoricoAsesoria = em.find(HistoricoAsesoria.class, historicoAsesoria.getIdHistoricoasesoria());
            Asesoria idAsesoriaOld = persistentHistoricoAsesoria.getIdAsesoria();
            Asesoria idAsesoriaNew = historicoAsesoria.getIdAsesoria();
            if (idAsesoriaNew != null) {
                idAsesoriaNew = em.getReference(idAsesoriaNew.getClass(), idAsesoriaNew.getIdAsesoria());
                historicoAsesoria.setIdAsesoria(idAsesoriaNew);
            }
            historicoAsesoria = em.merge(historicoAsesoria);
            if (idAsesoriaOld != null && !idAsesoriaOld.equals(idAsesoriaNew)) {
                idAsesoriaOld.getHistoricoAsesoriaList().remove(historicoAsesoria);
                idAsesoriaOld = em.merge(idAsesoriaOld);
            }
            if (idAsesoriaNew != null && !idAsesoriaNew.equals(idAsesoriaOld)) {
                idAsesoriaNew.getHistoricoAsesoriaList().add(historicoAsesoria);
                idAsesoriaNew = em.merge(idAsesoriaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = historicoAsesoria.getIdHistoricoasesoria();
                if (findHistoricoAsesoria(id) == null) {
                    throw new NonexistentEntityException("The historicoAsesoria with id " + id + " no longer exists.");
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
            HistoricoAsesoria historicoAsesoria;
            try {
                historicoAsesoria = em.getReference(HistoricoAsesoria.class, id);
                historicoAsesoria.getIdHistoricoasesoria();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The historicoAsesoria with id " + id + " no longer exists.", enfe);
            }
            Asesoria idAsesoria = historicoAsesoria.getIdAsesoria();
            if (idAsesoria != null) {
                idAsesoria.getHistoricoAsesoriaList().remove(historicoAsesoria);
                idAsesoria = em.merge(idAsesoria);
            }
            em.remove(historicoAsesoria);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HistoricoAsesoria> findHistoricoAsesoriaEntities() {
        return findHistoricoAsesoriaEntities(true, -1, -1);
    }

    public List<HistoricoAsesoria> findHistoricoAsesoriaEntities(int maxResults, int firstResult) {
        return findHistoricoAsesoriaEntities(false, maxResults, firstResult);
    }

    private List<HistoricoAsesoria> findHistoricoAsesoriaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from HistoricoAsesoria as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public HistoricoAsesoria findHistoricoAsesoria(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HistoricoAsesoria.class, id);
        } finally {
            em.close();
        }
    }

    public int getHistoricoAsesoriaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from HistoricoAsesoria as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
