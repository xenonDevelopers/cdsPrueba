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
import com.utez.integracion.entity.TemaSolicitudautorizacion;
import com.utez.integracion.entity.TipoTitulacion;
import com.utez.secretariaAcademica.entity.Alumno;
import com.utez.integracion.entity.ObservacionSolicitudtitulacion;
import com.utez.integracion.entity.PosgradoSolicitudautorizacion;
import com.utez.integracion.entity.SolicitudTitulacion;
import com.utez.integracion.entity.TramiteTitulacion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class SolicitudTitulacionJpaController implements Serializable {

    public SolicitudTitulacionJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SolicitudTitulacion solicitudTitulacion) {
        if (solicitudTitulacion.getTramiteTitulacionList() == null) {
            solicitudTitulacion.setTramiteTitulacionList(new ArrayList<TramiteTitulacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TemaSolicitudautorizacion temaSolicitudautorizacion = solicitudTitulacion.getTemaSolicitudautorizacion();
            if (temaSolicitudautorizacion != null) {
                temaSolicitudautorizacion = em.getReference(temaSolicitudautorizacion.getClass(), temaSolicitudautorizacion.getIdSolicitudtitulacion());
                solicitudTitulacion.setTemaSolicitudautorizacion(temaSolicitudautorizacion);
            }
            TipoTitulacion idTipotitulacion = solicitudTitulacion.getIdTipotitulacion();
            if (idTipotitulacion != null) {
                idTipotitulacion = em.getReference(idTipotitulacion.getClass(), idTipotitulacion.getIdTipotitulacion());
                solicitudTitulacion.setIdTipotitulacion(idTipotitulacion);
            }
            Alumno matricula = solicitudTitulacion.getMatricula();
            if (matricula != null) {
                matricula = em.getReference(matricula.getClass(), matricula.getMatricula());
                solicitudTitulacion.setMatricula(matricula);
            }
            ObservacionSolicitudtitulacion observacionSolicitudtitulacion = solicitudTitulacion.getObservacionSolicitudtitulacion();
            if (observacionSolicitudtitulacion != null) {
                observacionSolicitudtitulacion = em.getReference(observacionSolicitudtitulacion.getClass(), observacionSolicitudtitulacion.getIdSolicitudtitulacion());
                solicitudTitulacion.setObservacionSolicitudtitulacion(observacionSolicitudtitulacion);
            }
            PosgradoSolicitudautorizacion posgradoSolicitudautorizacion = solicitudTitulacion.getPosgradoSolicitudautorizacion();
            if (posgradoSolicitudautorizacion != null) {
                posgradoSolicitudautorizacion = em.getReference(posgradoSolicitudautorizacion.getClass(), posgradoSolicitudautorizacion.getIdSolicitudtitulacion());
                solicitudTitulacion.setPosgradoSolicitudautorizacion(posgradoSolicitudautorizacion);
            }
            List<TramiteTitulacion> attachedTramiteTitulacionList = new ArrayList<TramiteTitulacion>();
            for (TramiteTitulacion tramiteTitulacionListTramiteTitulacionToAttach : solicitudTitulacion.getTramiteTitulacionList()) {
                tramiteTitulacionListTramiteTitulacionToAttach = em.getReference(tramiteTitulacionListTramiteTitulacionToAttach.getClass(), tramiteTitulacionListTramiteTitulacionToAttach.getIdTramitetitulacion());
                attachedTramiteTitulacionList.add(tramiteTitulacionListTramiteTitulacionToAttach);
            }
            solicitudTitulacion.setTramiteTitulacionList(attachedTramiteTitulacionList);
            em.persist(solicitudTitulacion);
            if (temaSolicitudautorizacion != null) {
                SolicitudTitulacion oldSolicitudTitulacionOfTemaSolicitudautorizacion = temaSolicitudautorizacion.getSolicitudTitulacion();
                if (oldSolicitudTitulacionOfTemaSolicitudautorizacion != null) {
                    oldSolicitudTitulacionOfTemaSolicitudautorizacion.setTemaSolicitudautorizacion(null);
                    oldSolicitudTitulacionOfTemaSolicitudautorizacion = em.merge(oldSolicitudTitulacionOfTemaSolicitudautorizacion);
                }
                temaSolicitudautorizacion.setSolicitudTitulacion(solicitudTitulacion);
                temaSolicitudautorizacion = em.merge(temaSolicitudautorizacion);
            }
            if (idTipotitulacion != null) {
                idTipotitulacion.getSolicitudTitulacionList().add(solicitudTitulacion);
                idTipotitulacion = em.merge(idTipotitulacion);
            }
            if (matricula != null) {
                matricula.getSolicitudTitulacionList().add(solicitudTitulacion);
                matricula = em.merge(matricula);
            }
            if (observacionSolicitudtitulacion != null) {
                SolicitudTitulacion oldSolicitudTitulacionOfObservacionSolicitudtitulacion = observacionSolicitudtitulacion.getSolicitudTitulacion();
                if (oldSolicitudTitulacionOfObservacionSolicitudtitulacion != null) {
                    oldSolicitudTitulacionOfObservacionSolicitudtitulacion.setObservacionSolicitudtitulacion(null);
                    oldSolicitudTitulacionOfObservacionSolicitudtitulacion = em.merge(oldSolicitudTitulacionOfObservacionSolicitudtitulacion);
                }
                observacionSolicitudtitulacion.setSolicitudTitulacion(solicitudTitulacion);
                observacionSolicitudtitulacion = em.merge(observacionSolicitudtitulacion);
            }
            if (posgradoSolicitudautorizacion != null) {
                SolicitudTitulacion oldSolicitudTitulacionOfPosgradoSolicitudautorizacion = posgradoSolicitudautorizacion.getSolicitudTitulacion();
                if (oldSolicitudTitulacionOfPosgradoSolicitudautorizacion != null) {
                    oldSolicitudTitulacionOfPosgradoSolicitudautorizacion.setPosgradoSolicitudautorizacion(null);
                    oldSolicitudTitulacionOfPosgradoSolicitudautorizacion = em.merge(oldSolicitudTitulacionOfPosgradoSolicitudautorizacion);
                }
                posgradoSolicitudautorizacion.setSolicitudTitulacion(solicitudTitulacion);
                posgradoSolicitudautorizacion = em.merge(posgradoSolicitudautorizacion);
            }
            for (TramiteTitulacion tramiteTitulacionListTramiteTitulacion : solicitudTitulacion.getTramiteTitulacionList()) {
                SolicitudTitulacion oldIdSolicitudtitulacionOfTramiteTitulacionListTramiteTitulacion = tramiteTitulacionListTramiteTitulacion.getIdSolicitudtitulacion();
                tramiteTitulacionListTramiteTitulacion.setIdSolicitudtitulacion(solicitudTitulacion);
                tramiteTitulacionListTramiteTitulacion = em.merge(tramiteTitulacionListTramiteTitulacion);
                if (oldIdSolicitudtitulacionOfTramiteTitulacionListTramiteTitulacion != null) {
                    oldIdSolicitudtitulacionOfTramiteTitulacionListTramiteTitulacion.getTramiteTitulacionList().remove(tramiteTitulacionListTramiteTitulacion);
                    oldIdSolicitudtitulacionOfTramiteTitulacionListTramiteTitulacion = em.merge(oldIdSolicitudtitulacionOfTramiteTitulacionListTramiteTitulacion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SolicitudTitulacion solicitudTitulacion) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SolicitudTitulacion persistentSolicitudTitulacion = em.find(SolicitudTitulacion.class, solicitudTitulacion.getIdSolicitudtitulacion());
            TemaSolicitudautorizacion temaSolicitudautorizacionOld = persistentSolicitudTitulacion.getTemaSolicitudautorizacion();
            TemaSolicitudautorizacion temaSolicitudautorizacionNew = solicitudTitulacion.getTemaSolicitudautorizacion();
            TipoTitulacion idTipotitulacionOld = persistentSolicitudTitulacion.getIdTipotitulacion();
            TipoTitulacion idTipotitulacionNew = solicitudTitulacion.getIdTipotitulacion();
            Alumno matriculaOld = persistentSolicitudTitulacion.getMatricula();
            Alumno matriculaNew = solicitudTitulacion.getMatricula();
            ObservacionSolicitudtitulacion observacionSolicitudtitulacionOld = persistentSolicitudTitulacion.getObservacionSolicitudtitulacion();
            ObservacionSolicitudtitulacion observacionSolicitudtitulacionNew = solicitudTitulacion.getObservacionSolicitudtitulacion();
            PosgradoSolicitudautorizacion posgradoSolicitudautorizacionOld = persistentSolicitudTitulacion.getPosgradoSolicitudautorizacion();
            PosgradoSolicitudautorizacion posgradoSolicitudautorizacionNew = solicitudTitulacion.getPosgradoSolicitudautorizacion();
            List<TramiteTitulacion> tramiteTitulacionListOld = persistentSolicitudTitulacion.getTramiteTitulacionList();
            List<TramiteTitulacion> tramiteTitulacionListNew = solicitudTitulacion.getTramiteTitulacionList();
            List<String> illegalOrphanMessages = null;
            if (temaSolicitudautorizacionOld != null && !temaSolicitudautorizacionOld.equals(temaSolicitudautorizacionNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain TemaSolicitudautorizacion " + temaSolicitudautorizacionOld + " since its solicitudTitulacion field is not nullable.");
            }
            if (observacionSolicitudtitulacionOld != null && !observacionSolicitudtitulacionOld.equals(observacionSolicitudtitulacionNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain ObservacionSolicitudtitulacion " + observacionSolicitudtitulacionOld + " since its solicitudTitulacion field is not nullable.");
            }
            if (posgradoSolicitudautorizacionOld != null && !posgradoSolicitudautorizacionOld.equals(posgradoSolicitudautorizacionNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain PosgradoSolicitudautorizacion " + posgradoSolicitudautorizacionOld + " since its solicitudTitulacion field is not nullable.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (temaSolicitudautorizacionNew != null) {
                temaSolicitudautorizacionNew = em.getReference(temaSolicitudautorizacionNew.getClass(), temaSolicitudautorizacionNew.getIdSolicitudtitulacion());
                solicitudTitulacion.setTemaSolicitudautorizacion(temaSolicitudautorizacionNew);
            }
            if (idTipotitulacionNew != null) {
                idTipotitulacionNew = em.getReference(idTipotitulacionNew.getClass(), idTipotitulacionNew.getIdTipotitulacion());
                solicitudTitulacion.setIdTipotitulacion(idTipotitulacionNew);
            }
            if (matriculaNew != null) {
                matriculaNew = em.getReference(matriculaNew.getClass(), matriculaNew.getMatricula());
                solicitudTitulacion.setMatricula(matriculaNew);
            }
            if (observacionSolicitudtitulacionNew != null) {
                observacionSolicitudtitulacionNew = em.getReference(observacionSolicitudtitulacionNew.getClass(), observacionSolicitudtitulacionNew.getIdSolicitudtitulacion());
                solicitudTitulacion.setObservacionSolicitudtitulacion(observacionSolicitudtitulacionNew);
            }
            if (posgradoSolicitudautorizacionNew != null) {
                posgradoSolicitudautorizacionNew = em.getReference(posgradoSolicitudautorizacionNew.getClass(), posgradoSolicitudautorizacionNew.getIdSolicitudtitulacion());
                solicitudTitulacion.setPosgradoSolicitudautorizacion(posgradoSolicitudautorizacionNew);
            }
            List<TramiteTitulacion> attachedTramiteTitulacionListNew = new ArrayList<TramiteTitulacion>();
            for (TramiteTitulacion tramiteTitulacionListNewTramiteTitulacionToAttach : tramiteTitulacionListNew) {
                tramiteTitulacionListNewTramiteTitulacionToAttach = em.getReference(tramiteTitulacionListNewTramiteTitulacionToAttach.getClass(), tramiteTitulacionListNewTramiteTitulacionToAttach.getIdTramitetitulacion());
                attachedTramiteTitulacionListNew.add(tramiteTitulacionListNewTramiteTitulacionToAttach);
            }
            tramiteTitulacionListNew = attachedTramiteTitulacionListNew;
            solicitudTitulacion.setTramiteTitulacionList(tramiteTitulacionListNew);
            solicitudTitulacion = em.merge(solicitudTitulacion);
            if (temaSolicitudautorizacionNew != null && !temaSolicitudautorizacionNew.equals(temaSolicitudautorizacionOld)) {
                SolicitudTitulacion oldSolicitudTitulacionOfTemaSolicitudautorizacion = temaSolicitudautorizacionNew.getSolicitudTitulacion();
                if (oldSolicitudTitulacionOfTemaSolicitudautorizacion != null) {
                    oldSolicitudTitulacionOfTemaSolicitudautorizacion.setTemaSolicitudautorizacion(null);
                    oldSolicitudTitulacionOfTemaSolicitudautorizacion = em.merge(oldSolicitudTitulacionOfTemaSolicitudautorizacion);
                }
                temaSolicitudautorizacionNew.setSolicitudTitulacion(solicitudTitulacion);
                temaSolicitudautorizacionNew = em.merge(temaSolicitudautorizacionNew);
            }
            if (idTipotitulacionOld != null && !idTipotitulacionOld.equals(idTipotitulacionNew)) {
                idTipotitulacionOld.getSolicitudTitulacionList().remove(solicitudTitulacion);
                idTipotitulacionOld = em.merge(idTipotitulacionOld);
            }
            if (idTipotitulacionNew != null && !idTipotitulacionNew.equals(idTipotitulacionOld)) {
                idTipotitulacionNew.getSolicitudTitulacionList().add(solicitudTitulacion);
                idTipotitulacionNew = em.merge(idTipotitulacionNew);
            }
            if (matriculaOld != null && !matriculaOld.equals(matriculaNew)) {
                matriculaOld.getSolicitudTitulacionList().remove(solicitudTitulacion);
                matriculaOld = em.merge(matriculaOld);
            }
            if (matriculaNew != null && !matriculaNew.equals(matriculaOld)) {
                matriculaNew.getSolicitudTitulacionList().add(solicitudTitulacion);
                matriculaNew = em.merge(matriculaNew);
            }
            if (observacionSolicitudtitulacionNew != null && !observacionSolicitudtitulacionNew.equals(observacionSolicitudtitulacionOld)) {
                SolicitudTitulacion oldSolicitudTitulacionOfObservacionSolicitudtitulacion = observacionSolicitudtitulacionNew.getSolicitudTitulacion();
                if (oldSolicitudTitulacionOfObservacionSolicitudtitulacion != null) {
                    oldSolicitudTitulacionOfObservacionSolicitudtitulacion.setObservacionSolicitudtitulacion(null);
                    oldSolicitudTitulacionOfObservacionSolicitudtitulacion = em.merge(oldSolicitudTitulacionOfObservacionSolicitudtitulacion);
                }
                observacionSolicitudtitulacionNew.setSolicitudTitulacion(solicitudTitulacion);
                observacionSolicitudtitulacionNew = em.merge(observacionSolicitudtitulacionNew);
            }
            if (posgradoSolicitudautorizacionNew != null && !posgradoSolicitudautorizacionNew.equals(posgradoSolicitudautorizacionOld)) {
                SolicitudTitulacion oldSolicitudTitulacionOfPosgradoSolicitudautorizacion = posgradoSolicitudautorizacionNew.getSolicitudTitulacion();
                if (oldSolicitudTitulacionOfPosgradoSolicitudautorizacion != null) {
                    oldSolicitudTitulacionOfPosgradoSolicitudautorizacion.setPosgradoSolicitudautorizacion(null);
                    oldSolicitudTitulacionOfPosgradoSolicitudautorizacion = em.merge(oldSolicitudTitulacionOfPosgradoSolicitudautorizacion);
                }
                posgradoSolicitudautorizacionNew.setSolicitudTitulacion(solicitudTitulacion);
                posgradoSolicitudautorizacionNew = em.merge(posgradoSolicitudautorizacionNew);
            }
            for (TramiteTitulacion tramiteTitulacionListOldTramiteTitulacion : tramiteTitulacionListOld) {
                if (!tramiteTitulacionListNew.contains(tramiteTitulacionListOldTramiteTitulacion)) {
                    tramiteTitulacionListOldTramiteTitulacion.setIdSolicitudtitulacion(null);
                    tramiteTitulacionListOldTramiteTitulacion = em.merge(tramiteTitulacionListOldTramiteTitulacion);
                }
            }
            for (TramiteTitulacion tramiteTitulacionListNewTramiteTitulacion : tramiteTitulacionListNew) {
                if (!tramiteTitulacionListOld.contains(tramiteTitulacionListNewTramiteTitulacion)) {
                    SolicitudTitulacion oldIdSolicitudtitulacionOfTramiteTitulacionListNewTramiteTitulacion = tramiteTitulacionListNewTramiteTitulacion.getIdSolicitudtitulacion();
                    tramiteTitulacionListNewTramiteTitulacion.setIdSolicitudtitulacion(solicitudTitulacion);
                    tramiteTitulacionListNewTramiteTitulacion = em.merge(tramiteTitulacionListNewTramiteTitulacion);
                    if (oldIdSolicitudtitulacionOfTramiteTitulacionListNewTramiteTitulacion != null && !oldIdSolicitudtitulacionOfTramiteTitulacionListNewTramiteTitulacion.equals(solicitudTitulacion)) {
                        oldIdSolicitudtitulacionOfTramiteTitulacionListNewTramiteTitulacion.getTramiteTitulacionList().remove(tramiteTitulacionListNewTramiteTitulacion);
                        oldIdSolicitudtitulacionOfTramiteTitulacionListNewTramiteTitulacion = em.merge(oldIdSolicitudtitulacionOfTramiteTitulacionListNewTramiteTitulacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = solicitudTitulacion.getIdSolicitudtitulacion();
                if (findSolicitudTitulacion(id) == null) {
                    throw new NonexistentEntityException("The solicitudTitulacion with id " + id + " no longer exists.");
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
            SolicitudTitulacion solicitudTitulacion;
            try {
                solicitudTitulacion = em.getReference(SolicitudTitulacion.class, id);
                solicitudTitulacion.getIdSolicitudtitulacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The solicitudTitulacion with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            TemaSolicitudautorizacion temaSolicitudautorizacionOrphanCheck = solicitudTitulacion.getTemaSolicitudautorizacion();
            if (temaSolicitudautorizacionOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This SolicitudTitulacion (" + solicitudTitulacion + ") cannot be destroyed since the TemaSolicitudautorizacion " + temaSolicitudautorizacionOrphanCheck + " in its temaSolicitudautorizacion field has a non-nullable solicitudTitulacion field.");
            }
            ObservacionSolicitudtitulacion observacionSolicitudtitulacionOrphanCheck = solicitudTitulacion.getObservacionSolicitudtitulacion();
            if (observacionSolicitudtitulacionOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This SolicitudTitulacion (" + solicitudTitulacion + ") cannot be destroyed since the ObservacionSolicitudtitulacion " + observacionSolicitudtitulacionOrphanCheck + " in its observacionSolicitudtitulacion field has a non-nullable solicitudTitulacion field.");
            }
            PosgradoSolicitudautorizacion posgradoSolicitudautorizacionOrphanCheck = solicitudTitulacion.getPosgradoSolicitudautorizacion();
            if (posgradoSolicitudautorizacionOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This SolicitudTitulacion (" + solicitudTitulacion + ") cannot be destroyed since the PosgradoSolicitudautorizacion " + posgradoSolicitudautorizacionOrphanCheck + " in its posgradoSolicitudautorizacion field has a non-nullable solicitudTitulacion field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            TipoTitulacion idTipotitulacion = solicitudTitulacion.getIdTipotitulacion();
            if (idTipotitulacion != null) {
                idTipotitulacion.getSolicitudTitulacionList().remove(solicitudTitulacion);
                idTipotitulacion = em.merge(idTipotitulacion);
            }
            Alumno matricula = solicitudTitulacion.getMatricula();
            if (matricula != null) {
                matricula.getSolicitudTitulacionList().remove(solicitudTitulacion);
                matricula = em.merge(matricula);
            }
            List<TramiteTitulacion> tramiteTitulacionList = solicitudTitulacion.getTramiteTitulacionList();
            for (TramiteTitulacion tramiteTitulacionListTramiteTitulacion : tramiteTitulacionList) {
                tramiteTitulacionListTramiteTitulacion.setIdSolicitudtitulacion(null);
                tramiteTitulacionListTramiteTitulacion = em.merge(tramiteTitulacionListTramiteTitulacion);
            }
            em.remove(solicitudTitulacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SolicitudTitulacion> findSolicitudTitulacionEntities() {
        return findSolicitudTitulacionEntities(true, -1, -1);
    }

    public List<SolicitudTitulacion> findSolicitudTitulacionEntities(int maxResults, int firstResult) {
        return findSolicitudTitulacionEntities(false, maxResults, firstResult);
    }

    private List<SolicitudTitulacion> findSolicitudTitulacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from SolicitudTitulacion as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public SolicitudTitulacion findSolicitudTitulacion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SolicitudTitulacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getSolicitudTitulacionCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from SolicitudTitulacion as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
