/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.IllegalOrphanException;
import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.dao.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.SolicitudTitulacion;
import com.utez.integracion.entity.PlanEstudiosexterno;
import com.utez.integracion.entity.PosgradoSolicitudautorizacion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class PosgradoSolicitudautorizacionJpaController implements Serializable {

    public PosgradoSolicitudautorizacionJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PosgradoSolicitudautorizacion posgradoSolicitudautorizacion) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        SolicitudTitulacion solicitudTitulacionOrphanCheck = posgradoSolicitudautorizacion.getSolicitudTitulacion();
        if (solicitudTitulacionOrphanCheck != null) {
            PosgradoSolicitudautorizacion oldPosgradoSolicitudautorizacionOfSolicitudTitulacion = solicitudTitulacionOrphanCheck.getPosgradoSolicitudautorizacion();
            if (oldPosgradoSolicitudautorizacionOfSolicitudTitulacion != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The SolicitudTitulacion " + solicitudTitulacionOrphanCheck + " already has an item of type PosgradoSolicitudautorizacion whose solicitudTitulacion column cannot be null. Please make another selection for the solicitudTitulacion field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SolicitudTitulacion solicitudTitulacion = posgradoSolicitudautorizacion.getSolicitudTitulacion();
            if (solicitudTitulacion != null) {
                solicitudTitulacion = em.getReference(solicitudTitulacion.getClass(), solicitudTitulacion.getIdSolicitudtitulacion());
                posgradoSolicitudautorizacion.setSolicitudTitulacion(solicitudTitulacion);
            }
            PlanEstudiosexterno idPlanestudiosexterno = posgradoSolicitudautorizacion.getIdPlanestudiosexterno();
            if (idPlanestudiosexterno != null) {
                idPlanestudiosexterno = em.getReference(idPlanestudiosexterno.getClass(), idPlanestudiosexterno.getIdPlanestudiosexterno());
                posgradoSolicitudautorizacion.setIdPlanestudiosexterno(idPlanestudiosexterno);
            }
            em.persist(posgradoSolicitudautorizacion);
            if (solicitudTitulacion != null) {
                solicitudTitulacion.setPosgradoSolicitudautorizacion(posgradoSolicitudautorizacion);
                solicitudTitulacion = em.merge(solicitudTitulacion);
            }
            if (idPlanestudiosexterno != null) {
                idPlanestudiosexterno.getPosgradoSolicitudautorizacionList().add(posgradoSolicitudautorizacion);
                idPlanestudiosexterno = em.merge(idPlanestudiosexterno);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPosgradoSolicitudautorizacion(posgradoSolicitudautorizacion.getIdSolicitudtitulacion()) != null) {
                throw new PreexistingEntityException("PosgradoSolicitudautorizacion " + posgradoSolicitudautorizacion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PosgradoSolicitudautorizacion posgradoSolicitudautorizacion) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PosgradoSolicitudautorizacion persistentPosgradoSolicitudautorizacion = em.find(PosgradoSolicitudautorizacion.class, posgradoSolicitudautorizacion.getIdSolicitudtitulacion());
            SolicitudTitulacion solicitudTitulacionOld = persistentPosgradoSolicitudautorizacion.getSolicitudTitulacion();
            SolicitudTitulacion solicitudTitulacionNew = posgradoSolicitudautorizacion.getSolicitudTitulacion();
            PlanEstudiosexterno idPlanestudiosexternoOld = persistentPosgradoSolicitudautorizacion.getIdPlanestudiosexterno();
            PlanEstudiosexterno idPlanestudiosexternoNew = posgradoSolicitudautorizacion.getIdPlanestudiosexterno();
            List<String> illegalOrphanMessages = null;
            if (solicitudTitulacionNew != null && !solicitudTitulacionNew.equals(solicitudTitulacionOld)) {
                PosgradoSolicitudautorizacion oldPosgradoSolicitudautorizacionOfSolicitudTitulacion = solicitudTitulacionNew.getPosgradoSolicitudautorizacion();
                if (oldPosgradoSolicitudautorizacionOfSolicitudTitulacion != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The SolicitudTitulacion " + solicitudTitulacionNew + " already has an item of type PosgradoSolicitudautorizacion whose solicitudTitulacion column cannot be null. Please make another selection for the solicitudTitulacion field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (solicitudTitulacionNew != null) {
                solicitudTitulacionNew = em.getReference(solicitudTitulacionNew.getClass(), solicitudTitulacionNew.getIdSolicitudtitulacion());
                posgradoSolicitudautorizacion.setSolicitudTitulacion(solicitudTitulacionNew);
            }
            if (idPlanestudiosexternoNew != null) {
                idPlanestudiosexternoNew = em.getReference(idPlanestudiosexternoNew.getClass(), idPlanestudiosexternoNew.getIdPlanestudiosexterno());
                posgradoSolicitudautorizacion.setIdPlanestudiosexterno(idPlanestudiosexternoNew);
            }
            posgradoSolicitudautorizacion = em.merge(posgradoSolicitudautorizacion);
            if (solicitudTitulacionOld != null && !solicitudTitulacionOld.equals(solicitudTitulacionNew)) {
                solicitudTitulacionOld.setPosgradoSolicitudautorizacion(null);
                solicitudTitulacionOld = em.merge(solicitudTitulacionOld);
            }
            if (solicitudTitulacionNew != null && !solicitudTitulacionNew.equals(solicitudTitulacionOld)) {
                solicitudTitulacionNew.setPosgradoSolicitudautorizacion(posgradoSolicitudautorizacion);
                solicitudTitulacionNew = em.merge(solicitudTitulacionNew);
            }
            if (idPlanestudiosexternoOld != null && !idPlanestudiosexternoOld.equals(idPlanestudiosexternoNew)) {
                idPlanestudiosexternoOld.getPosgradoSolicitudautorizacionList().remove(posgradoSolicitudautorizacion);
                idPlanestudiosexternoOld = em.merge(idPlanestudiosexternoOld);
            }
            if (idPlanestudiosexternoNew != null && !idPlanestudiosexternoNew.equals(idPlanestudiosexternoOld)) {
                idPlanestudiosexternoNew.getPosgradoSolicitudautorizacionList().add(posgradoSolicitudautorizacion);
                idPlanestudiosexternoNew = em.merge(idPlanestudiosexternoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = posgradoSolicitudautorizacion.getIdSolicitudtitulacion();
                if (findPosgradoSolicitudautorizacion(id) == null) {
                    throw new NonexistentEntityException("The posgradoSolicitudautorizacion with id " + id + " no longer exists.");
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
            PosgradoSolicitudautorizacion posgradoSolicitudautorizacion;
            try {
                posgradoSolicitudautorizacion = em.getReference(PosgradoSolicitudautorizacion.class, id);
                posgradoSolicitudautorizacion.getIdSolicitudtitulacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The posgradoSolicitudautorizacion with id " + id + " no longer exists.", enfe);
            }
            SolicitudTitulacion solicitudTitulacion = posgradoSolicitudautorizacion.getSolicitudTitulacion();
            if (solicitudTitulacion != null) {
                solicitudTitulacion.setPosgradoSolicitudautorizacion(null);
                solicitudTitulacion = em.merge(solicitudTitulacion);
            }
            PlanEstudiosexterno idPlanestudiosexterno = posgradoSolicitudautorizacion.getIdPlanestudiosexterno();
            if (idPlanestudiosexterno != null) {
                idPlanestudiosexterno.getPosgradoSolicitudautorizacionList().remove(posgradoSolicitudautorizacion);
                idPlanestudiosexterno = em.merge(idPlanestudiosexterno);
            }
            em.remove(posgradoSolicitudautorizacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PosgradoSolicitudautorizacion> findPosgradoSolicitudautorizacionEntities() {
        return findPosgradoSolicitudautorizacionEntities(true, -1, -1);
    }

    public List<PosgradoSolicitudautorizacion> findPosgradoSolicitudautorizacionEntities(int maxResults, int firstResult) {
        return findPosgradoSolicitudautorizacionEntities(false, maxResults, firstResult);
    }

    private List<PosgradoSolicitudautorizacion> findPosgradoSolicitudautorizacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from PosgradoSolicitudautorizacion as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public PosgradoSolicitudautorizacion findPosgradoSolicitudautorizacion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PosgradoSolicitudautorizacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getPosgradoSolicitudautorizacionCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from PosgradoSolicitudautorizacion as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
