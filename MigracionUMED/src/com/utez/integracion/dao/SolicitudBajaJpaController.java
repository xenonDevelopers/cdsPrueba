/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.entity.SolicitudBaja;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.TipoBaja;
import com.utez.secretariaAcademica.entity.Alumno;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class SolicitudBajaJpaController implements Serializable {

    public SolicitudBajaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SolicitudBaja solicitudBaja) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoBaja idTipobaja = solicitudBaja.getIdTipobaja();
            if (idTipobaja != null) {
                idTipobaja = em.getReference(idTipobaja.getClass(), idTipobaja.getIdTipobaja());
                solicitudBaja.setIdTipobaja(idTipobaja);
            }
            Alumno matricula = solicitudBaja.getMatricula();
            if (matricula != null) {
                matricula = em.getReference(matricula.getClass(), matricula.getMatricula());
                solicitudBaja.setMatricula(matricula);
            }
            em.persist(solicitudBaja);
            if (idTipobaja != null) {
                idTipobaja.getSolicitudBajaList().add(solicitudBaja);
                idTipobaja = em.merge(idTipobaja);
            }
            if (matricula != null) {
                matricula.getSolicitudBajaList().add(solicitudBaja);
                matricula = em.merge(matricula);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SolicitudBaja solicitudBaja) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SolicitudBaja persistentSolicitudBaja = em.find(SolicitudBaja.class, solicitudBaja.getIdSolicitudbaja());
            TipoBaja idTipobajaOld = persistentSolicitudBaja.getIdTipobaja();
            TipoBaja idTipobajaNew = solicitudBaja.getIdTipobaja();
            Alumno matriculaOld = persistentSolicitudBaja.getMatricula();
            Alumno matriculaNew = solicitudBaja.getMatricula();
            if (idTipobajaNew != null) {
                idTipobajaNew = em.getReference(idTipobajaNew.getClass(), idTipobajaNew.getIdTipobaja());
                solicitudBaja.setIdTipobaja(idTipobajaNew);
            }
            if (matriculaNew != null) {
                matriculaNew = em.getReference(matriculaNew.getClass(), matriculaNew.getMatricula());
                solicitudBaja.setMatricula(matriculaNew);
            }
            solicitudBaja = em.merge(solicitudBaja);
            if (idTipobajaOld != null && !idTipobajaOld.equals(idTipobajaNew)) {
                idTipobajaOld.getSolicitudBajaList().remove(solicitudBaja);
                idTipobajaOld = em.merge(idTipobajaOld);
            }
            if (idTipobajaNew != null && !idTipobajaNew.equals(idTipobajaOld)) {
                idTipobajaNew.getSolicitudBajaList().add(solicitudBaja);
                idTipobajaNew = em.merge(idTipobajaNew);
            }
            if (matriculaOld != null && !matriculaOld.equals(matriculaNew)) {
                matriculaOld.getSolicitudBajaList().remove(solicitudBaja);
                matriculaOld = em.merge(matriculaOld);
            }
            if (matriculaNew != null && !matriculaNew.equals(matriculaOld)) {
                matriculaNew.getSolicitudBajaList().add(solicitudBaja);
                matriculaNew = em.merge(matriculaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = solicitudBaja.getIdSolicitudbaja();
                if (findSolicitudBaja(id) == null) {
                    throw new NonexistentEntityException("The solicitudBaja with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SolicitudBaja solicitudBaja;
            try {
                solicitudBaja = em.getReference(SolicitudBaja.class, id);
                solicitudBaja.getIdSolicitudbaja();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The solicitudBaja with id " + id + " no longer exists.", enfe);
            }
            TipoBaja idTipobaja = solicitudBaja.getIdTipobaja();
            if (idTipobaja != null) {
                idTipobaja.getSolicitudBajaList().remove(solicitudBaja);
                idTipobaja = em.merge(idTipobaja);
            }
            Alumno matricula = solicitudBaja.getMatricula();
            if (matricula != null) {
                matricula.getSolicitudBajaList().remove(solicitudBaja);
                matricula = em.merge(matricula);
            }
            em.remove(solicitudBaja);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SolicitudBaja> findSolicitudBajaEntities() {
        return findSolicitudBajaEntities(true, -1, -1);
    }

    public List<SolicitudBaja> findSolicitudBajaEntities(int maxResults, int firstResult) {
        return findSolicitudBajaEntities(false, maxResults, firstResult);
    }

    private List<SolicitudBaja> findSolicitudBajaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from SolicitudBaja as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public SolicitudBaja findSolicitudBaja(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SolicitudBaja.class, id);
        } finally {
            em.close();
        }
    }

    public int getSolicitudBajaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from SolicitudBaja as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
