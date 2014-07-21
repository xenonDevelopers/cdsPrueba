/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.IllegalOrphanException;
import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.AsesorTitulartitulacion;
import com.utez.integracion.entity.SolicitudTitulacion;
import com.utez.integracion.entity.OrdenTrabajo;
import java.util.ArrayList;
import java.util.List;
import com.utez.integracion.entity.SinodoExamen;
import com.utez.integracion.entity.ExamenTitulacion;
import com.utez.integracion.entity.TramiteTitulacion;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class TramiteTitulacionJpaController implements Serializable {

    public TramiteTitulacionJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TramiteTitulacion tramiteTitulacion) {
        if (tramiteTitulacion.getOrdenTrabajoList() == null) {
            tramiteTitulacion.setOrdenTrabajoList(new ArrayList<OrdenTrabajo>());
        }
        if (tramiteTitulacion.getSinodoExamenList() == null) {
            tramiteTitulacion.setSinodoExamenList(new ArrayList<SinodoExamen>());
        }
        if (tramiteTitulacion.getExamenTitulacionList() == null) {
            tramiteTitulacion.setExamenTitulacionList(new ArrayList<ExamenTitulacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AsesorTitulartitulacion asesorTitulartitulacion = tramiteTitulacion.getAsesorTitulartitulacion();
            if (asesorTitulartitulacion != null) {
                asesorTitulartitulacion = em.getReference(asesorTitulartitulacion.getClass(), asesorTitulartitulacion.getIdTramitetitulacion());
                tramiteTitulacion.setAsesorTitulartitulacion(asesorTitulartitulacion);
            }
            SolicitudTitulacion idSolicitudtitulacion = tramiteTitulacion.getIdSolicitudtitulacion();
            if (idSolicitudtitulacion != null) {
                idSolicitudtitulacion = em.getReference(idSolicitudtitulacion.getClass(), idSolicitudtitulacion.getIdSolicitudtitulacion());
                tramiteTitulacion.setIdSolicitudtitulacion(idSolicitudtitulacion);
            }
            List<OrdenTrabajo> attachedOrdenTrabajoList = new ArrayList<OrdenTrabajo>();
            for (OrdenTrabajo ordenTrabajoListOrdenTrabajoToAttach : tramiteTitulacion.getOrdenTrabajoList()) {
                ordenTrabajoListOrdenTrabajoToAttach = em.getReference(ordenTrabajoListOrdenTrabajoToAttach.getClass(), ordenTrabajoListOrdenTrabajoToAttach.getIdOrdentrabajo());
                attachedOrdenTrabajoList.add(ordenTrabajoListOrdenTrabajoToAttach);
            }
            tramiteTitulacion.setOrdenTrabajoList(attachedOrdenTrabajoList);
            List<SinodoExamen> attachedSinodoExamenList = new ArrayList<SinodoExamen>();
            for (SinodoExamen sinodoExamenListSinodoExamenToAttach : tramiteTitulacion.getSinodoExamenList()) {
                sinodoExamenListSinodoExamenToAttach = em.getReference(sinodoExamenListSinodoExamenToAttach.getClass(), sinodoExamenListSinodoExamenToAttach.getIdSinodo());
                attachedSinodoExamenList.add(sinodoExamenListSinodoExamenToAttach);
            }
            tramiteTitulacion.setSinodoExamenList(attachedSinodoExamenList);
            List<ExamenTitulacion> attachedExamenTitulacionList = new ArrayList<ExamenTitulacion>();
            for (ExamenTitulacion examenTitulacionListExamenTitulacionToAttach : tramiteTitulacion.getExamenTitulacionList()) {
                examenTitulacionListExamenTitulacionToAttach = em.getReference(examenTitulacionListExamenTitulacionToAttach.getClass(), examenTitulacionListExamenTitulacionToAttach.getIdExamentitulacion());
                attachedExamenTitulacionList.add(examenTitulacionListExamenTitulacionToAttach);
            }
            tramiteTitulacion.setExamenTitulacionList(attachedExamenTitulacionList);
            em.persist(tramiteTitulacion);
            if (asesorTitulartitulacion != null) {
                TramiteTitulacion oldTramiteTitulacionOfAsesorTitulartitulacion = asesorTitulartitulacion.getTramiteTitulacion();
                if (oldTramiteTitulacionOfAsesorTitulartitulacion != null) {
                    oldTramiteTitulacionOfAsesorTitulartitulacion.setAsesorTitulartitulacion(null);
                    oldTramiteTitulacionOfAsesorTitulartitulacion = em.merge(oldTramiteTitulacionOfAsesorTitulartitulacion);
                }
                asesorTitulartitulacion.setTramiteTitulacion(tramiteTitulacion);
                asesorTitulartitulacion = em.merge(asesorTitulartitulacion);
            }
            if (idSolicitudtitulacion != null) {
                idSolicitudtitulacion.getTramiteTitulacionList().add(tramiteTitulacion);
                idSolicitudtitulacion = em.merge(idSolicitudtitulacion);
            }
            for (OrdenTrabajo ordenTrabajoListOrdenTrabajo : tramiteTitulacion.getOrdenTrabajoList()) {
                ordenTrabajoListOrdenTrabajo.getTramiteTitulacionList().add(tramiteTitulacion);
                ordenTrabajoListOrdenTrabajo = em.merge(ordenTrabajoListOrdenTrabajo);
            }
            for (SinodoExamen sinodoExamenListSinodoExamen : tramiteTitulacion.getSinodoExamenList()) {
                TramiteTitulacion oldIdTramitetitulacionOfSinodoExamenListSinodoExamen = sinodoExamenListSinodoExamen.getIdTramitetitulacion();
                sinodoExamenListSinodoExamen.setIdTramitetitulacion(tramiteTitulacion);
                sinodoExamenListSinodoExamen = em.merge(sinodoExamenListSinodoExamen);
                if (oldIdTramitetitulacionOfSinodoExamenListSinodoExamen != null) {
                    oldIdTramitetitulacionOfSinodoExamenListSinodoExamen.getSinodoExamenList().remove(sinodoExamenListSinodoExamen);
                    oldIdTramitetitulacionOfSinodoExamenListSinodoExamen = em.merge(oldIdTramitetitulacionOfSinodoExamenListSinodoExamen);
                }
            }
            for (ExamenTitulacion examenTitulacionListExamenTitulacion : tramiteTitulacion.getExamenTitulacionList()) {
                TramiteTitulacion oldIdTramitetitulacionOfExamenTitulacionListExamenTitulacion = examenTitulacionListExamenTitulacion.getIdTramitetitulacion();
                examenTitulacionListExamenTitulacion.setIdTramitetitulacion(tramiteTitulacion);
                examenTitulacionListExamenTitulacion = em.merge(examenTitulacionListExamenTitulacion);
                if (oldIdTramitetitulacionOfExamenTitulacionListExamenTitulacion != null) {
                    oldIdTramitetitulacionOfExamenTitulacionListExamenTitulacion.getExamenTitulacionList().remove(examenTitulacionListExamenTitulacion);
                    oldIdTramitetitulacionOfExamenTitulacionListExamenTitulacion = em.merge(oldIdTramitetitulacionOfExamenTitulacionListExamenTitulacion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TramiteTitulacion tramiteTitulacion) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TramiteTitulacion persistentTramiteTitulacion = em.find(TramiteTitulacion.class, tramiteTitulacion.getIdTramitetitulacion());
            AsesorTitulartitulacion asesorTitulartitulacionOld = persistentTramiteTitulacion.getAsesorTitulartitulacion();
            AsesorTitulartitulacion asesorTitulartitulacionNew = tramiteTitulacion.getAsesorTitulartitulacion();
            SolicitudTitulacion idSolicitudtitulacionOld = persistentTramiteTitulacion.getIdSolicitudtitulacion();
            SolicitudTitulacion idSolicitudtitulacionNew = tramiteTitulacion.getIdSolicitudtitulacion();
            List<OrdenTrabajo> ordenTrabajoListOld = persistentTramiteTitulacion.getOrdenTrabajoList();
            List<OrdenTrabajo> ordenTrabajoListNew = tramiteTitulacion.getOrdenTrabajoList();
            List<SinodoExamen> sinodoExamenListOld = persistentTramiteTitulacion.getSinodoExamenList();
            List<SinodoExamen> sinodoExamenListNew = tramiteTitulacion.getSinodoExamenList();
            List<ExamenTitulacion> examenTitulacionListOld = persistentTramiteTitulacion.getExamenTitulacionList();
            List<ExamenTitulacion> examenTitulacionListNew = tramiteTitulacion.getExamenTitulacionList();
            List<String> illegalOrphanMessages = null;
            if (asesorTitulartitulacionOld != null && !asesorTitulartitulacionOld.equals(asesorTitulartitulacionNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain AsesorTitulartitulacion " + asesorTitulartitulacionOld + " since its tramiteTitulacion field is not nullable.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (asesorTitulartitulacionNew != null) {
                asesorTitulartitulacionNew = em.getReference(asesorTitulartitulacionNew.getClass(), asesorTitulartitulacionNew.getIdTramitetitulacion());
                tramiteTitulacion.setAsesorTitulartitulacion(asesorTitulartitulacionNew);
            }
            if (idSolicitudtitulacionNew != null) {
                idSolicitudtitulacionNew = em.getReference(idSolicitudtitulacionNew.getClass(), idSolicitudtitulacionNew.getIdSolicitudtitulacion());
                tramiteTitulacion.setIdSolicitudtitulacion(idSolicitudtitulacionNew);
            }
            List<OrdenTrabajo> attachedOrdenTrabajoListNew = new ArrayList<OrdenTrabajo>();
            for (OrdenTrabajo ordenTrabajoListNewOrdenTrabajoToAttach : ordenTrabajoListNew) {
                ordenTrabajoListNewOrdenTrabajoToAttach = em.getReference(ordenTrabajoListNewOrdenTrabajoToAttach.getClass(), ordenTrabajoListNewOrdenTrabajoToAttach.getIdOrdentrabajo());
                attachedOrdenTrabajoListNew.add(ordenTrabajoListNewOrdenTrabajoToAttach);
            }
            ordenTrabajoListNew = attachedOrdenTrabajoListNew;
            tramiteTitulacion.setOrdenTrabajoList(ordenTrabajoListNew);
            List<SinodoExamen> attachedSinodoExamenListNew = new ArrayList<SinodoExamen>();
            for (SinodoExamen sinodoExamenListNewSinodoExamenToAttach : sinodoExamenListNew) {
                sinodoExamenListNewSinodoExamenToAttach = em.getReference(sinodoExamenListNewSinodoExamenToAttach.getClass(), sinodoExamenListNewSinodoExamenToAttach.getIdSinodo());
                attachedSinodoExamenListNew.add(sinodoExamenListNewSinodoExamenToAttach);
            }
            sinodoExamenListNew = attachedSinodoExamenListNew;
            tramiteTitulacion.setSinodoExamenList(sinodoExamenListNew);
            List<ExamenTitulacion> attachedExamenTitulacionListNew = new ArrayList<ExamenTitulacion>();
            for (ExamenTitulacion examenTitulacionListNewExamenTitulacionToAttach : examenTitulacionListNew) {
                examenTitulacionListNewExamenTitulacionToAttach = em.getReference(examenTitulacionListNewExamenTitulacionToAttach.getClass(), examenTitulacionListNewExamenTitulacionToAttach.getIdExamentitulacion());
                attachedExamenTitulacionListNew.add(examenTitulacionListNewExamenTitulacionToAttach);
            }
            examenTitulacionListNew = attachedExamenTitulacionListNew;
            tramiteTitulacion.setExamenTitulacionList(examenTitulacionListNew);
            tramiteTitulacion = em.merge(tramiteTitulacion);
            if (asesorTitulartitulacionNew != null && !asesorTitulartitulacionNew.equals(asesorTitulartitulacionOld)) {
                TramiteTitulacion oldTramiteTitulacionOfAsesorTitulartitulacion = asesorTitulartitulacionNew.getTramiteTitulacion();
                if (oldTramiteTitulacionOfAsesorTitulartitulacion != null) {
                    oldTramiteTitulacionOfAsesorTitulartitulacion.setAsesorTitulartitulacion(null);
                    oldTramiteTitulacionOfAsesorTitulartitulacion = em.merge(oldTramiteTitulacionOfAsesorTitulartitulacion);
                }
                asesorTitulartitulacionNew.setTramiteTitulacion(tramiteTitulacion);
                asesorTitulartitulacionNew = em.merge(asesorTitulartitulacionNew);
            }
            if (idSolicitudtitulacionOld != null && !idSolicitudtitulacionOld.equals(idSolicitudtitulacionNew)) {
                idSolicitudtitulacionOld.getTramiteTitulacionList().remove(tramiteTitulacion);
                idSolicitudtitulacionOld = em.merge(idSolicitudtitulacionOld);
            }
            if (idSolicitudtitulacionNew != null && !idSolicitudtitulacionNew.equals(idSolicitudtitulacionOld)) {
                idSolicitudtitulacionNew.getTramiteTitulacionList().add(tramiteTitulacion);
                idSolicitudtitulacionNew = em.merge(idSolicitudtitulacionNew);
            }
            for (OrdenTrabajo ordenTrabajoListOldOrdenTrabajo : ordenTrabajoListOld) {
                if (!ordenTrabajoListNew.contains(ordenTrabajoListOldOrdenTrabajo)) {
                    ordenTrabajoListOldOrdenTrabajo.getTramiteTitulacionList().remove(tramiteTitulacion);
                    ordenTrabajoListOldOrdenTrabajo = em.merge(ordenTrabajoListOldOrdenTrabajo);
                }
            }
            for (OrdenTrabajo ordenTrabajoListNewOrdenTrabajo : ordenTrabajoListNew) {
                if (!ordenTrabajoListOld.contains(ordenTrabajoListNewOrdenTrabajo)) {
                    ordenTrabajoListNewOrdenTrabajo.getTramiteTitulacionList().add(tramiteTitulacion);
                    ordenTrabajoListNewOrdenTrabajo = em.merge(ordenTrabajoListNewOrdenTrabajo);
                }
            }
            for (SinodoExamen sinodoExamenListOldSinodoExamen : sinodoExamenListOld) {
                if (!sinodoExamenListNew.contains(sinodoExamenListOldSinodoExamen)) {
                    sinodoExamenListOldSinodoExamen.setIdTramitetitulacion(null);
                    sinodoExamenListOldSinodoExamen = em.merge(sinodoExamenListOldSinodoExamen);
                }
            }
            for (SinodoExamen sinodoExamenListNewSinodoExamen : sinodoExamenListNew) {
                if (!sinodoExamenListOld.contains(sinodoExamenListNewSinodoExamen)) {
                    TramiteTitulacion oldIdTramitetitulacionOfSinodoExamenListNewSinodoExamen = sinodoExamenListNewSinodoExamen.getIdTramitetitulacion();
                    sinodoExamenListNewSinodoExamen.setIdTramitetitulacion(tramiteTitulacion);
                    sinodoExamenListNewSinodoExamen = em.merge(sinodoExamenListNewSinodoExamen);
                    if (oldIdTramitetitulacionOfSinodoExamenListNewSinodoExamen != null && !oldIdTramitetitulacionOfSinodoExamenListNewSinodoExamen.equals(tramiteTitulacion)) {
                        oldIdTramitetitulacionOfSinodoExamenListNewSinodoExamen.getSinodoExamenList().remove(sinodoExamenListNewSinodoExamen);
                        oldIdTramitetitulacionOfSinodoExamenListNewSinodoExamen = em.merge(oldIdTramitetitulacionOfSinodoExamenListNewSinodoExamen);
                    }
                }
            }
            for (ExamenTitulacion examenTitulacionListOldExamenTitulacion : examenTitulacionListOld) {
                if (!examenTitulacionListNew.contains(examenTitulacionListOldExamenTitulacion)) {
                    examenTitulacionListOldExamenTitulacion.setIdTramitetitulacion(null);
                    examenTitulacionListOldExamenTitulacion = em.merge(examenTitulacionListOldExamenTitulacion);
                }
            }
            for (ExamenTitulacion examenTitulacionListNewExamenTitulacion : examenTitulacionListNew) {
                if (!examenTitulacionListOld.contains(examenTitulacionListNewExamenTitulacion)) {
                    TramiteTitulacion oldIdTramitetitulacionOfExamenTitulacionListNewExamenTitulacion = examenTitulacionListNewExamenTitulacion.getIdTramitetitulacion();
                    examenTitulacionListNewExamenTitulacion.setIdTramitetitulacion(tramiteTitulacion);
                    examenTitulacionListNewExamenTitulacion = em.merge(examenTitulacionListNewExamenTitulacion);
                    if (oldIdTramitetitulacionOfExamenTitulacionListNewExamenTitulacion != null && !oldIdTramitetitulacionOfExamenTitulacionListNewExamenTitulacion.equals(tramiteTitulacion)) {
                        oldIdTramitetitulacionOfExamenTitulacionListNewExamenTitulacion.getExamenTitulacionList().remove(examenTitulacionListNewExamenTitulacion);
                        oldIdTramitetitulacionOfExamenTitulacionListNewExamenTitulacion = em.merge(oldIdTramitetitulacionOfExamenTitulacionListNewExamenTitulacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tramiteTitulacion.getIdTramitetitulacion();
                if (findTramiteTitulacion(id) == null) {
                    throw new NonexistentEntityException("The tramiteTitulacion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TramiteTitulacion tramiteTitulacion;
            try {
                tramiteTitulacion = em.getReference(TramiteTitulacion.class, id);
                tramiteTitulacion.getIdTramitetitulacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tramiteTitulacion with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            AsesorTitulartitulacion asesorTitulartitulacionOrphanCheck = tramiteTitulacion.getAsesorTitulartitulacion();
            if (asesorTitulartitulacionOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TramiteTitulacion (" + tramiteTitulacion + ") cannot be destroyed since the AsesorTitulartitulacion " + asesorTitulartitulacionOrphanCheck + " in its asesorTitulartitulacion field has a non-nullable tramiteTitulacion field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            SolicitudTitulacion idSolicitudtitulacion = tramiteTitulacion.getIdSolicitudtitulacion();
            if (idSolicitudtitulacion != null) {
                idSolicitudtitulacion.getTramiteTitulacionList().remove(tramiteTitulacion);
                idSolicitudtitulacion = em.merge(idSolicitudtitulacion);
            }
            List<OrdenTrabajo> ordenTrabajoList = tramiteTitulacion.getOrdenTrabajoList();
            for (OrdenTrabajo ordenTrabajoListOrdenTrabajo : ordenTrabajoList) {
                ordenTrabajoListOrdenTrabajo.getTramiteTitulacionList().remove(tramiteTitulacion);
                ordenTrabajoListOrdenTrabajo = em.merge(ordenTrabajoListOrdenTrabajo);
            }
            List<SinodoExamen> sinodoExamenList = tramiteTitulacion.getSinodoExamenList();
            for (SinodoExamen sinodoExamenListSinodoExamen : sinodoExamenList) {
                sinodoExamenListSinodoExamen.setIdTramitetitulacion(null);
                sinodoExamenListSinodoExamen = em.merge(sinodoExamenListSinodoExamen);
            }
            List<ExamenTitulacion> examenTitulacionList = tramiteTitulacion.getExamenTitulacionList();
            for (ExamenTitulacion examenTitulacionListExamenTitulacion : examenTitulacionList) {
                examenTitulacionListExamenTitulacion.setIdTramitetitulacion(null);
                examenTitulacionListExamenTitulacion = em.merge(examenTitulacionListExamenTitulacion);
            }
            em.remove(tramiteTitulacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TramiteTitulacion> findTramiteTitulacionEntities() {
        return findTramiteTitulacionEntities(true, -1, -1);
    }

    public List<TramiteTitulacion> findTramiteTitulacionEntities(int maxResults, int firstResult) {
        return findTramiteTitulacionEntities(false, maxResults, firstResult);
    }

    private List<TramiteTitulacion> findTramiteTitulacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from TramiteTitulacion as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public TramiteTitulacion findTramiteTitulacion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TramiteTitulacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getTramiteTitulacionCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from TramiteTitulacion as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
