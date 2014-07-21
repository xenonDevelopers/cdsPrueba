/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.entity.FechaInduccion;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.Horario;
import com.utez.integracion.entity.GrupoInduccion;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class FechaInduccionJpaController implements Serializable {

    public FechaInduccionJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(FechaInduccion fechaInduccion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Horario idHorario = fechaInduccion.getIdHorario();
            if (idHorario != null) {
                idHorario = em.getReference(idHorario.getClass(), idHorario.getIdHorario());
                fechaInduccion.setIdHorario(idHorario);
            }
            GrupoInduccion idGrupoinduccion = fechaInduccion.getIdGrupoinduccion();
            if (idGrupoinduccion != null) {
                idGrupoinduccion = em.getReference(idGrupoinduccion.getClass(), idGrupoinduccion.getIdGrupoinduccion());
                fechaInduccion.setIdGrupoinduccion(idGrupoinduccion);
            }
            em.persist(fechaInduccion);
            if (idHorario != null) {
                idHorario.getFechaInduccionList().add(fechaInduccion);
                idHorario = em.merge(idHorario);
            }
            if (idGrupoinduccion != null) {
                idGrupoinduccion.getFechaInduccionList().add(fechaInduccion);
                idGrupoinduccion = em.merge(idGrupoinduccion);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(FechaInduccion fechaInduccion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FechaInduccion persistentFechaInduccion = em.find(FechaInduccion.class, fechaInduccion.getIdFechainduccion());
            Horario idHorarioOld = persistentFechaInduccion.getIdHorario();
            Horario idHorarioNew = fechaInduccion.getIdHorario();
            GrupoInduccion idGrupoinduccionOld = persistentFechaInduccion.getIdGrupoinduccion();
            GrupoInduccion idGrupoinduccionNew = fechaInduccion.getIdGrupoinduccion();
            if (idHorarioNew != null) {
                idHorarioNew = em.getReference(idHorarioNew.getClass(), idHorarioNew.getIdHorario());
                fechaInduccion.setIdHorario(idHorarioNew);
            }
            if (idGrupoinduccionNew != null) {
                idGrupoinduccionNew = em.getReference(idGrupoinduccionNew.getClass(), idGrupoinduccionNew.getIdGrupoinduccion());
                fechaInduccion.setIdGrupoinduccion(idGrupoinduccionNew);
            }
            fechaInduccion = em.merge(fechaInduccion);
            if (idHorarioOld != null && !idHorarioOld.equals(idHorarioNew)) {
                idHorarioOld.getFechaInduccionList().remove(fechaInduccion);
                idHorarioOld = em.merge(idHorarioOld);
            }
            if (idHorarioNew != null && !idHorarioNew.equals(idHorarioOld)) {
                idHorarioNew.getFechaInduccionList().add(fechaInduccion);
                idHorarioNew = em.merge(idHorarioNew);
            }
            if (idGrupoinduccionOld != null && !idGrupoinduccionOld.equals(idGrupoinduccionNew)) {
                idGrupoinduccionOld.getFechaInduccionList().remove(fechaInduccion);
                idGrupoinduccionOld = em.merge(idGrupoinduccionOld);
            }
            if (idGrupoinduccionNew != null && !idGrupoinduccionNew.equals(idGrupoinduccionOld)) {
                idGrupoinduccionNew.getFechaInduccionList().add(fechaInduccion);
                idGrupoinduccionNew = em.merge(idGrupoinduccionNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = fechaInduccion.getIdFechainduccion();
                if (findFechaInduccion(id) == null) {
                    throw new NonexistentEntityException("The fechaInduccion with id " + id + " no longer exists.");
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
            FechaInduccion fechaInduccion;
            try {
                fechaInduccion = em.getReference(FechaInduccion.class, id);
                fechaInduccion.getIdFechainduccion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The fechaInduccion with id " + id + " no longer exists.", enfe);
            }
            Horario idHorario = fechaInduccion.getIdHorario();
            if (idHorario != null) {
                idHorario.getFechaInduccionList().remove(fechaInduccion);
                idHorario = em.merge(idHorario);
            }
            GrupoInduccion idGrupoinduccion = fechaInduccion.getIdGrupoinduccion();
            if (idGrupoinduccion != null) {
                idGrupoinduccion.getFechaInduccionList().remove(fechaInduccion);
                idGrupoinduccion = em.merge(idGrupoinduccion);
            }
            em.remove(fechaInduccion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<FechaInduccion> findFechaInduccionEntities() {
        return findFechaInduccionEntities(true, -1, -1);
    }

    public List<FechaInduccion> findFechaInduccionEntities(int maxResults, int firstResult) {
        return findFechaInduccionEntities(false, maxResults, firstResult);
    }

    private List<FechaInduccion> findFechaInduccionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from FechaInduccion as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public FechaInduccion findFechaInduccion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(FechaInduccion.class, id);
        } finally {
            em.close();
        }
    }

    public int getFechaInduccionCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from FechaInduccion as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
