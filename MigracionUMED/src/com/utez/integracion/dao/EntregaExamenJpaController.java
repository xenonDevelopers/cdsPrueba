/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.entity.EntregaExamen;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.FechaExamenprogramado;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class EntregaExamenJpaController implements Serializable {

    public EntregaExamenJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EntregaExamen entregaExamen) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FechaExamenprogramado idFechaexamenprogramado = entregaExamen.getIdFechaexamenprogramado();
            if (idFechaexamenprogramado != null) {
                idFechaexamenprogramado = em.getReference(idFechaexamenprogramado.getClass(), idFechaexamenprogramado.getIdFechaExamen());
                entregaExamen.setIdFechaexamenprogramado(idFechaexamenprogramado);
            }
            em.persist(entregaExamen);
            if (idFechaexamenprogramado != null) {
                idFechaexamenprogramado.getEntregaExamenList().add(entregaExamen);
                idFechaexamenprogramado = em.merge(idFechaexamenprogramado);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EntregaExamen entregaExamen) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EntregaExamen persistentEntregaExamen = em.find(EntregaExamen.class, entregaExamen.getIdEntregaexamen());
            FechaExamenprogramado idFechaexamenprogramadoOld = persistentEntregaExamen.getIdFechaexamenprogramado();
            FechaExamenprogramado idFechaexamenprogramadoNew = entregaExamen.getIdFechaexamenprogramado();
            if (idFechaexamenprogramadoNew != null) {
                idFechaexamenprogramadoNew = em.getReference(idFechaexamenprogramadoNew.getClass(), idFechaexamenprogramadoNew.getIdFechaExamen());
                entregaExamen.setIdFechaexamenprogramado(idFechaexamenprogramadoNew);
            }
            entregaExamen = em.merge(entregaExamen);
            if (idFechaexamenprogramadoOld != null && !idFechaexamenprogramadoOld.equals(idFechaexamenprogramadoNew)) {
                idFechaexamenprogramadoOld.getEntregaExamenList().remove(entregaExamen);
                idFechaexamenprogramadoOld = em.merge(idFechaexamenprogramadoOld);
            }
            if (idFechaexamenprogramadoNew != null && !idFechaexamenprogramadoNew.equals(idFechaexamenprogramadoOld)) {
                idFechaexamenprogramadoNew.getEntregaExamenList().add(entregaExamen);
                idFechaexamenprogramadoNew = em.merge(idFechaexamenprogramadoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = entregaExamen.getIdEntregaexamen();
                if (findEntregaExamen(id) == null) {
                    throw new NonexistentEntityException("The entregaExamen with id " + id + " no longer exists.");
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
            EntregaExamen entregaExamen;
            try {
                entregaExamen = em.getReference(EntregaExamen.class, id);
                entregaExamen.getIdEntregaexamen();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The entregaExamen with id " + id + " no longer exists.", enfe);
            }
            FechaExamenprogramado idFechaexamenprogramado = entregaExamen.getIdFechaexamenprogramado();
            if (idFechaexamenprogramado != null) {
                idFechaexamenprogramado.getEntregaExamenList().remove(entregaExamen);
                idFechaexamenprogramado = em.merge(idFechaexamenprogramado);
            }
            em.remove(entregaExamen);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EntregaExamen> findEntregaExamenEntities() {
        return findEntregaExamenEntities(true, -1, -1);
    }

    public List<EntregaExamen> findEntregaExamenEntities(int maxResults, int firstResult) {
        return findEntregaExamenEntities(false, maxResults, firstResult);
    }

    private List<EntregaExamen> findEntregaExamenEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from EntregaExamen as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public EntregaExamen findEntregaExamen(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EntregaExamen.class, id);
        } finally {
            em.close();
        }
    }

    public int getEntregaExamenCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from EntregaExamen as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
