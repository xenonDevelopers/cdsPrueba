/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.entity.CalificacionModulo;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.Persona;
import com.utez.integracion.entity.OfertaEvento;
import com.utez.integracion.entity.Modulo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class CalificacionModuloJpaController implements Serializable {

    public CalificacionModuloJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CalificacionModulo calificacionModulo) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Persona curp = calificacionModulo.getCurp();
            if (curp != null) {
                curp = em.getReference(curp.getClass(), curp.getCurp());
                calificacionModulo.setCurp(curp);
            }
            OfertaEvento idOfertaevento = calificacionModulo.getIdOfertaevento();
            if (idOfertaevento != null) {
                idOfertaevento = em.getReference(idOfertaevento.getClass(), idOfertaevento.getIdOfertaevento());
                calificacionModulo.setIdOfertaevento(idOfertaevento);
            }
            Modulo idModulo = calificacionModulo.getIdModulo();
            if (idModulo != null) {
                idModulo = em.getReference(idModulo.getClass(), idModulo.getIdModulo());
                calificacionModulo.setIdModulo(idModulo);
            }
            em.persist(calificacionModulo);
            if (curp != null) {
                curp.getCalificacionModuloList().add(calificacionModulo);
                curp = em.merge(curp);
            }
            if (idOfertaevento != null) {
                idOfertaevento.getCalificacionModuloList().add(calificacionModulo);
                idOfertaevento = em.merge(idOfertaevento);
            }
            if (idModulo != null) {
                idModulo.getCalificacionModuloList().add(calificacionModulo);
                idModulo = em.merge(idModulo);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CalificacionModulo calificacionModulo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CalificacionModulo persistentCalificacionModulo = em.find(CalificacionModulo.class, calificacionModulo.getIdCalificacionevento());
            Persona curpOld = persistentCalificacionModulo.getCurp();
            Persona curpNew = calificacionModulo.getCurp();
            OfertaEvento idOfertaeventoOld = persistentCalificacionModulo.getIdOfertaevento();
            OfertaEvento idOfertaeventoNew = calificacionModulo.getIdOfertaevento();
            Modulo idModuloOld = persistentCalificacionModulo.getIdModulo();
            Modulo idModuloNew = calificacionModulo.getIdModulo();
            if (curpNew != null) {
                curpNew = em.getReference(curpNew.getClass(), curpNew.getCurp());
                calificacionModulo.setCurp(curpNew);
            }
            if (idOfertaeventoNew != null) {
                idOfertaeventoNew = em.getReference(idOfertaeventoNew.getClass(), idOfertaeventoNew.getIdOfertaevento());
                calificacionModulo.setIdOfertaevento(idOfertaeventoNew);
            }
            if (idModuloNew != null) {
                idModuloNew = em.getReference(idModuloNew.getClass(), idModuloNew.getIdModulo());
                calificacionModulo.setIdModulo(idModuloNew);
            }
            calificacionModulo = em.merge(calificacionModulo);
            if (curpOld != null && !curpOld.equals(curpNew)) {
                curpOld.getCalificacionModuloList().remove(calificacionModulo);
                curpOld = em.merge(curpOld);
            }
            if (curpNew != null && !curpNew.equals(curpOld)) {
                curpNew.getCalificacionModuloList().add(calificacionModulo);
                curpNew = em.merge(curpNew);
            }
            if (idOfertaeventoOld != null && !idOfertaeventoOld.equals(idOfertaeventoNew)) {
                idOfertaeventoOld.getCalificacionModuloList().remove(calificacionModulo);
                idOfertaeventoOld = em.merge(idOfertaeventoOld);
            }
            if (idOfertaeventoNew != null && !idOfertaeventoNew.equals(idOfertaeventoOld)) {
                idOfertaeventoNew.getCalificacionModuloList().add(calificacionModulo);
                idOfertaeventoNew = em.merge(idOfertaeventoNew);
            }
            if (idModuloOld != null && !idModuloOld.equals(idModuloNew)) {
                idModuloOld.getCalificacionModuloList().remove(calificacionModulo);
                idModuloOld = em.merge(idModuloOld);
            }
            if (idModuloNew != null && !idModuloNew.equals(idModuloOld)) {
                idModuloNew.getCalificacionModuloList().add(calificacionModulo);
                idModuloNew = em.merge(idModuloNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = calificacionModulo.getIdCalificacionevento();
                if (findCalificacionModulo(id) == null) {
                    throw new NonexistentEntityException("The calificacionModulo with id " + id + " no longer exists.");
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
            CalificacionModulo calificacionModulo;
            try {
                calificacionModulo = em.getReference(CalificacionModulo.class, id);
                calificacionModulo.getIdCalificacionevento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The calificacionModulo with id " + id + " no longer exists.", enfe);
            }
            Persona curp = calificacionModulo.getCurp();
            if (curp != null) {
                curp.getCalificacionModuloList().remove(calificacionModulo);
                curp = em.merge(curp);
            }
            OfertaEvento idOfertaevento = calificacionModulo.getIdOfertaevento();
            if (idOfertaevento != null) {
                idOfertaevento.getCalificacionModuloList().remove(calificacionModulo);
                idOfertaevento = em.merge(idOfertaevento);
            }
            Modulo idModulo = calificacionModulo.getIdModulo();
            if (idModulo != null) {
                idModulo.getCalificacionModuloList().remove(calificacionModulo);
                idModulo = em.merge(idModulo);
            }
            em.remove(calificacionModulo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CalificacionModulo> findCalificacionModuloEntities() {
        return findCalificacionModuloEntities(true, -1, -1);
    }

    public List<CalificacionModulo> findCalificacionModuloEntities(int maxResults, int firstResult) {
        return findCalificacionModuloEntities(false, maxResults, firstResult);
    }

    private List<CalificacionModulo> findCalificacionModuloEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from CalificacionModulo as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public CalificacionModulo findCalificacionModulo(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CalificacionModulo.class, id);
        } finally {
            em.close();
        }
    }

    public int getCalificacionModuloCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from CalificacionModulo as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
