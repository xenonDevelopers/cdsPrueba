/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.entity.ReporteServicio;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.ServicioSocial;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class ReporteServicioJpaController implements Serializable {

    public ReporteServicioJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ReporteServicio reporteServicio) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ServicioSocial idServiciosocial = reporteServicio.getIdServiciosocial();
            if (idServiciosocial != null) {
                idServiciosocial = em.getReference(idServiciosocial.getClass(), idServiciosocial.getIdServiciosocial());
                reporteServicio.setIdServiciosocial(idServiciosocial);
            }
            em.persist(reporteServicio);
            if (idServiciosocial != null) {
                idServiciosocial.getReporteServicioList().add(reporteServicio);
                idServiciosocial = em.merge(idServiciosocial);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ReporteServicio reporteServicio) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ReporteServicio persistentReporteServicio = em.find(ReporteServicio.class, reporteServicio.getIdReporteservicio());
            ServicioSocial idServiciosocialOld = persistentReporteServicio.getIdServiciosocial();
            ServicioSocial idServiciosocialNew = reporteServicio.getIdServiciosocial();
            if (idServiciosocialNew != null) {
                idServiciosocialNew = em.getReference(idServiciosocialNew.getClass(), idServiciosocialNew.getIdServiciosocial());
                reporteServicio.setIdServiciosocial(idServiciosocialNew);
            }
            reporteServicio = em.merge(reporteServicio);
            if (idServiciosocialOld != null && !idServiciosocialOld.equals(idServiciosocialNew)) {
                idServiciosocialOld.getReporteServicioList().remove(reporteServicio);
                idServiciosocialOld = em.merge(idServiciosocialOld);
            }
            if (idServiciosocialNew != null && !idServiciosocialNew.equals(idServiciosocialOld)) {
                idServiciosocialNew.getReporteServicioList().add(reporteServicio);
                idServiciosocialNew = em.merge(idServiciosocialNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = reporteServicio.getIdReporteservicio();
                if (findReporteServicio(id) == null) {
                    throw new NonexistentEntityException("The reporteServicio with id " + id + " no longer exists.");
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
            ReporteServicio reporteServicio;
            try {
                reporteServicio = em.getReference(ReporteServicio.class, id);
                reporteServicio.getIdReporteservicio();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The reporteServicio with id " + id + " no longer exists.", enfe);
            }
            ServicioSocial idServiciosocial = reporteServicio.getIdServiciosocial();
            if (idServiciosocial != null) {
                idServiciosocial.getReporteServicioList().remove(reporteServicio);
                idServiciosocial = em.merge(idServiciosocial);
            }
            em.remove(reporteServicio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ReporteServicio> findReporteServicioEntities() {
        return findReporteServicioEntities(true, -1, -1);
    }

    public List<ReporteServicio> findReporteServicioEntities(int maxResults, int firstResult) {
        return findReporteServicioEntities(false, maxResults, firstResult);
    }

    private List<ReporteServicio> findReporteServicioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from ReporteServicio as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public ReporteServicio findReporteServicio(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ReporteServicio.class, id);
        } finally {
            em.close();
        }
    }

    public int getReporteServicioCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from ReporteServicio as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
