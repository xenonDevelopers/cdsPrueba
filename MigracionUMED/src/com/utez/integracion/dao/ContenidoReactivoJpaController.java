/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.entity.ContenidoReactivo;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.Reactivo;
import com.utez.integracion.entity.RespuestaEvaluacion;
import java.util.ArrayList;
import java.util.List;
import com.utez.integracion.entity.RespuestaReactivo;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class ContenidoReactivoJpaController implements Serializable {

    public ContenidoReactivoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ContenidoReactivo contenidoReactivo) {
        if (contenidoReactivo.getRespuestaEvaluacionList() == null) {
            contenidoReactivo.setRespuestaEvaluacionList(new ArrayList<RespuestaEvaluacion>());
        }
        if (contenidoReactivo.getRespuestaReactivoList() == null) {
            contenidoReactivo.setRespuestaReactivoList(new ArrayList<RespuestaReactivo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Reactivo idReactivo = contenidoReactivo.getIdReactivo();
            if (idReactivo != null) {
                idReactivo = em.getReference(idReactivo.getClass(), idReactivo.getIdReactivo());
                contenidoReactivo.setIdReactivo(idReactivo);
            }
            List<RespuestaEvaluacion> attachedRespuestaEvaluacionList = new ArrayList<RespuestaEvaluacion>();
            for (RespuestaEvaluacion respuestaEvaluacionListRespuestaEvaluacionToAttach : contenidoReactivo.getRespuestaEvaluacionList()) {
                respuestaEvaluacionListRespuestaEvaluacionToAttach = em.getReference(respuestaEvaluacionListRespuestaEvaluacionToAttach.getClass(), respuestaEvaluacionListRespuestaEvaluacionToAttach.getIdRespuestaevaluacion());
                attachedRespuestaEvaluacionList.add(respuestaEvaluacionListRespuestaEvaluacionToAttach);
            }
            contenidoReactivo.setRespuestaEvaluacionList(attachedRespuestaEvaluacionList);
            List<RespuestaReactivo> attachedRespuestaReactivoList = new ArrayList<RespuestaReactivo>();
            for (RespuestaReactivo respuestaReactivoListRespuestaReactivoToAttach : contenidoReactivo.getRespuestaReactivoList()) {
                respuestaReactivoListRespuestaReactivoToAttach = em.getReference(respuestaReactivoListRespuestaReactivoToAttach.getClass(), respuestaReactivoListRespuestaReactivoToAttach.getIdRespuestareactivo());
                attachedRespuestaReactivoList.add(respuestaReactivoListRespuestaReactivoToAttach);
            }
            contenidoReactivo.setRespuestaReactivoList(attachedRespuestaReactivoList);
            em.persist(contenidoReactivo);
            if (idReactivo != null) {
                idReactivo.getContenidoReactivoList().add(contenidoReactivo);
                idReactivo = em.merge(idReactivo);
            }
            for (RespuestaEvaluacion respuestaEvaluacionListRespuestaEvaluacion : contenidoReactivo.getRespuestaEvaluacionList()) {
                ContenidoReactivo oldIdContenidoOfRespuestaEvaluacionListRespuestaEvaluacion = respuestaEvaluacionListRespuestaEvaluacion.getIdContenido();
                respuestaEvaluacionListRespuestaEvaluacion.setIdContenido(contenidoReactivo);
                respuestaEvaluacionListRespuestaEvaluacion = em.merge(respuestaEvaluacionListRespuestaEvaluacion);
                if (oldIdContenidoOfRespuestaEvaluacionListRespuestaEvaluacion != null) {
                    oldIdContenidoOfRespuestaEvaluacionListRespuestaEvaluacion.getRespuestaEvaluacionList().remove(respuestaEvaluacionListRespuestaEvaluacion);
                    oldIdContenidoOfRespuestaEvaluacionListRespuestaEvaluacion = em.merge(oldIdContenidoOfRespuestaEvaluacionListRespuestaEvaluacion);
                }
            }
            for (RespuestaReactivo respuestaReactivoListRespuestaReactivo : contenidoReactivo.getRespuestaReactivoList()) {
                ContenidoReactivo oldIdContenidoreactivoOfRespuestaReactivoListRespuestaReactivo = respuestaReactivoListRespuestaReactivo.getIdContenidoreactivo();
                respuestaReactivoListRespuestaReactivo.setIdContenidoreactivo(contenidoReactivo);
                respuestaReactivoListRespuestaReactivo = em.merge(respuestaReactivoListRespuestaReactivo);
                if (oldIdContenidoreactivoOfRespuestaReactivoListRespuestaReactivo != null) {
                    oldIdContenidoreactivoOfRespuestaReactivoListRespuestaReactivo.getRespuestaReactivoList().remove(respuestaReactivoListRespuestaReactivo);
                    oldIdContenidoreactivoOfRespuestaReactivoListRespuestaReactivo = em.merge(oldIdContenidoreactivoOfRespuestaReactivoListRespuestaReactivo);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ContenidoReactivo contenidoReactivo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ContenidoReactivo persistentContenidoReactivo = em.find(ContenidoReactivo.class, contenidoReactivo.getIdContenidoreactivo());
            Reactivo idReactivoOld = persistentContenidoReactivo.getIdReactivo();
            Reactivo idReactivoNew = contenidoReactivo.getIdReactivo();
            List<RespuestaEvaluacion> respuestaEvaluacionListOld = persistentContenidoReactivo.getRespuestaEvaluacionList();
            List<RespuestaEvaluacion> respuestaEvaluacionListNew = contenidoReactivo.getRespuestaEvaluacionList();
            List<RespuestaReactivo> respuestaReactivoListOld = persistentContenidoReactivo.getRespuestaReactivoList();
            List<RespuestaReactivo> respuestaReactivoListNew = contenidoReactivo.getRespuestaReactivoList();
            if (idReactivoNew != null) {
                idReactivoNew = em.getReference(idReactivoNew.getClass(), idReactivoNew.getIdReactivo());
                contenidoReactivo.setIdReactivo(idReactivoNew);
            }
            List<RespuestaEvaluacion> attachedRespuestaEvaluacionListNew = new ArrayList<RespuestaEvaluacion>();
            for (RespuestaEvaluacion respuestaEvaluacionListNewRespuestaEvaluacionToAttach : respuestaEvaluacionListNew) {
                respuestaEvaluacionListNewRespuestaEvaluacionToAttach = em.getReference(respuestaEvaluacionListNewRespuestaEvaluacionToAttach.getClass(), respuestaEvaluacionListNewRespuestaEvaluacionToAttach.getIdRespuestaevaluacion());
                attachedRespuestaEvaluacionListNew.add(respuestaEvaluacionListNewRespuestaEvaluacionToAttach);
            }
            respuestaEvaluacionListNew = attachedRespuestaEvaluacionListNew;
            contenidoReactivo.setRespuestaEvaluacionList(respuestaEvaluacionListNew);
            List<RespuestaReactivo> attachedRespuestaReactivoListNew = new ArrayList<RespuestaReactivo>();
            for (RespuestaReactivo respuestaReactivoListNewRespuestaReactivoToAttach : respuestaReactivoListNew) {
                respuestaReactivoListNewRespuestaReactivoToAttach = em.getReference(respuestaReactivoListNewRespuestaReactivoToAttach.getClass(), respuestaReactivoListNewRespuestaReactivoToAttach.getIdRespuestareactivo());
                attachedRespuestaReactivoListNew.add(respuestaReactivoListNewRespuestaReactivoToAttach);
            }
            respuestaReactivoListNew = attachedRespuestaReactivoListNew;
            contenidoReactivo.setRespuestaReactivoList(respuestaReactivoListNew);
            contenidoReactivo = em.merge(contenidoReactivo);
            if (idReactivoOld != null && !idReactivoOld.equals(idReactivoNew)) {
                idReactivoOld.getContenidoReactivoList().remove(contenidoReactivo);
                idReactivoOld = em.merge(idReactivoOld);
            }
            if (idReactivoNew != null && !idReactivoNew.equals(idReactivoOld)) {
                idReactivoNew.getContenidoReactivoList().add(contenidoReactivo);
                idReactivoNew = em.merge(idReactivoNew);
            }
            for (RespuestaEvaluacion respuestaEvaluacionListOldRespuestaEvaluacion : respuestaEvaluacionListOld) {
                if (!respuestaEvaluacionListNew.contains(respuestaEvaluacionListOldRespuestaEvaluacion)) {
                    respuestaEvaluacionListOldRespuestaEvaluacion.setIdContenido(null);
                    respuestaEvaluacionListOldRespuestaEvaluacion = em.merge(respuestaEvaluacionListOldRespuestaEvaluacion);
                }
            }
            for (RespuestaEvaluacion respuestaEvaluacionListNewRespuestaEvaluacion : respuestaEvaluacionListNew) {
                if (!respuestaEvaluacionListOld.contains(respuestaEvaluacionListNewRespuestaEvaluacion)) {
                    ContenidoReactivo oldIdContenidoOfRespuestaEvaluacionListNewRespuestaEvaluacion = respuestaEvaluacionListNewRespuestaEvaluacion.getIdContenido();
                    respuestaEvaluacionListNewRespuestaEvaluacion.setIdContenido(contenidoReactivo);
                    respuestaEvaluacionListNewRespuestaEvaluacion = em.merge(respuestaEvaluacionListNewRespuestaEvaluacion);
                    if (oldIdContenidoOfRespuestaEvaluacionListNewRespuestaEvaluacion != null && !oldIdContenidoOfRespuestaEvaluacionListNewRespuestaEvaluacion.equals(contenidoReactivo)) {
                        oldIdContenidoOfRespuestaEvaluacionListNewRespuestaEvaluacion.getRespuestaEvaluacionList().remove(respuestaEvaluacionListNewRespuestaEvaluacion);
                        oldIdContenidoOfRespuestaEvaluacionListNewRespuestaEvaluacion = em.merge(oldIdContenidoOfRespuestaEvaluacionListNewRespuestaEvaluacion);
                    }
                }
            }
            for (RespuestaReactivo respuestaReactivoListOldRespuestaReactivo : respuestaReactivoListOld) {
                if (!respuestaReactivoListNew.contains(respuestaReactivoListOldRespuestaReactivo)) {
                    respuestaReactivoListOldRespuestaReactivo.setIdContenidoreactivo(null);
                    respuestaReactivoListOldRespuestaReactivo = em.merge(respuestaReactivoListOldRespuestaReactivo);
                }
            }
            for (RespuestaReactivo respuestaReactivoListNewRespuestaReactivo : respuestaReactivoListNew) {
                if (!respuestaReactivoListOld.contains(respuestaReactivoListNewRespuestaReactivo)) {
                    ContenidoReactivo oldIdContenidoreactivoOfRespuestaReactivoListNewRespuestaReactivo = respuestaReactivoListNewRespuestaReactivo.getIdContenidoreactivo();
                    respuestaReactivoListNewRespuestaReactivo.setIdContenidoreactivo(contenidoReactivo);
                    respuestaReactivoListNewRespuestaReactivo = em.merge(respuestaReactivoListNewRespuestaReactivo);
                    if (oldIdContenidoreactivoOfRespuestaReactivoListNewRespuestaReactivo != null && !oldIdContenidoreactivoOfRespuestaReactivoListNewRespuestaReactivo.equals(contenidoReactivo)) {
                        oldIdContenidoreactivoOfRespuestaReactivoListNewRespuestaReactivo.getRespuestaReactivoList().remove(respuestaReactivoListNewRespuestaReactivo);
                        oldIdContenidoreactivoOfRespuestaReactivoListNewRespuestaReactivo = em.merge(oldIdContenidoreactivoOfRespuestaReactivoListNewRespuestaReactivo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = contenidoReactivo.getIdContenidoreactivo();
                if (findContenidoReactivo(id) == null) {
                    throw new NonexistentEntityException("The contenidoReactivo with id " + id + " no longer exists.");
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
            ContenidoReactivo contenidoReactivo;
            try {
                contenidoReactivo = em.getReference(ContenidoReactivo.class, id);
                contenidoReactivo.getIdContenidoreactivo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The contenidoReactivo with id " + id + " no longer exists.", enfe);
            }
            Reactivo idReactivo = contenidoReactivo.getIdReactivo();
            if (idReactivo != null) {
                idReactivo.getContenidoReactivoList().remove(contenidoReactivo);
                idReactivo = em.merge(idReactivo);
            }
            List<RespuestaEvaluacion> respuestaEvaluacionList = contenidoReactivo.getRespuestaEvaluacionList();
            for (RespuestaEvaluacion respuestaEvaluacionListRespuestaEvaluacion : respuestaEvaluacionList) {
                respuestaEvaluacionListRespuestaEvaluacion.setIdContenido(null);
                respuestaEvaluacionListRespuestaEvaluacion = em.merge(respuestaEvaluacionListRespuestaEvaluacion);
            }
            List<RespuestaReactivo> respuestaReactivoList = contenidoReactivo.getRespuestaReactivoList();
            for (RespuestaReactivo respuestaReactivoListRespuestaReactivo : respuestaReactivoList) {
                respuestaReactivoListRespuestaReactivo.setIdContenidoreactivo(null);
                respuestaReactivoListRespuestaReactivo = em.merge(respuestaReactivoListRespuestaReactivo);
            }
            em.remove(contenidoReactivo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ContenidoReactivo> findContenidoReactivoEntities() {
        return findContenidoReactivoEntities(true, -1, -1);
    }

    public List<ContenidoReactivo> findContenidoReactivoEntities(int maxResults, int firstResult) {
        return findContenidoReactivoEntities(false, maxResults, firstResult);
    }

    private List<ContenidoReactivo> findContenidoReactivoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from ContenidoReactivo as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public ContenidoReactivo findContenidoReactivo(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ContenidoReactivo.class, id);
        } finally {
            em.close();
        }
    }

    public int getContenidoReactivoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from ContenidoReactivo as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
