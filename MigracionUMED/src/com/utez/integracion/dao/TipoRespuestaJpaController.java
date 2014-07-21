/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.RespuestaEncuesta;
import java.util.ArrayList;
import java.util.List;
import com.utez.integracion.entity.RespuestaEncuestadocente;
import com.utez.integracion.entity.TipoRespuesta;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class TipoRespuestaJpaController implements Serializable {

    public TipoRespuestaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoRespuesta tipoRespuesta) {
        if (tipoRespuesta.getRespuestaEncuestaList() == null) {
            tipoRespuesta.setRespuestaEncuestaList(new ArrayList<RespuestaEncuesta>());
        }
        if (tipoRespuesta.getRespuestaEncuestadocenteList() == null) {
            tipoRespuesta.setRespuestaEncuestadocenteList(new ArrayList<RespuestaEncuestadocente>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<RespuestaEncuesta> attachedRespuestaEncuestaList = new ArrayList<RespuestaEncuesta>();
            for (RespuestaEncuesta respuestaEncuestaListRespuestaEncuestaToAttach : tipoRespuesta.getRespuestaEncuestaList()) {
                respuestaEncuestaListRespuestaEncuestaToAttach = em.getReference(respuestaEncuestaListRespuestaEncuestaToAttach.getClass(), respuestaEncuestaListRespuestaEncuestaToAttach.getIdRespuestaencuesta());
                attachedRespuestaEncuestaList.add(respuestaEncuestaListRespuestaEncuestaToAttach);
            }
            tipoRespuesta.setRespuestaEncuestaList(attachedRespuestaEncuestaList);
            List<RespuestaEncuestadocente> attachedRespuestaEncuestadocenteList = new ArrayList<RespuestaEncuestadocente>();
            for (RespuestaEncuestadocente respuestaEncuestadocenteListRespuestaEncuestadocenteToAttach : tipoRespuesta.getRespuestaEncuestadocenteList()) {
                respuestaEncuestadocenteListRespuestaEncuestadocenteToAttach = em.getReference(respuestaEncuestadocenteListRespuestaEncuestadocenteToAttach.getClass(), respuestaEncuestadocenteListRespuestaEncuestadocenteToAttach.getIdRespuestaencuestadocente());
                attachedRespuestaEncuestadocenteList.add(respuestaEncuestadocenteListRespuestaEncuestadocenteToAttach);
            }
            tipoRespuesta.setRespuestaEncuestadocenteList(attachedRespuestaEncuestadocenteList);
            em.persist(tipoRespuesta);
            for (RespuestaEncuesta respuestaEncuestaListRespuestaEncuesta : tipoRespuesta.getRespuestaEncuestaList()) {
                TipoRespuesta oldIdTiporespuestaOfRespuestaEncuestaListRespuestaEncuesta = respuestaEncuestaListRespuestaEncuesta.getIdTiporespuesta();
                respuestaEncuestaListRespuestaEncuesta.setIdTiporespuesta(tipoRespuesta);
                respuestaEncuestaListRespuestaEncuesta = em.merge(respuestaEncuestaListRespuestaEncuesta);
                if (oldIdTiporespuestaOfRespuestaEncuestaListRespuestaEncuesta != null) {
                    oldIdTiporespuestaOfRespuestaEncuestaListRespuestaEncuesta.getRespuestaEncuestaList().remove(respuestaEncuestaListRespuestaEncuesta);
                    oldIdTiporespuestaOfRespuestaEncuestaListRespuestaEncuesta = em.merge(oldIdTiporespuestaOfRespuestaEncuestaListRespuestaEncuesta);
                }
            }
            for (RespuestaEncuestadocente respuestaEncuestadocenteListRespuestaEncuestadocente : tipoRespuesta.getRespuestaEncuestadocenteList()) {
                TipoRespuesta oldIdTiporespuestaOfRespuestaEncuestadocenteListRespuestaEncuestadocente = respuestaEncuestadocenteListRespuestaEncuestadocente.getIdTiporespuesta();
                respuestaEncuestadocenteListRespuestaEncuestadocente.setIdTiporespuesta(tipoRespuesta);
                respuestaEncuestadocenteListRespuestaEncuestadocente = em.merge(respuestaEncuestadocenteListRespuestaEncuestadocente);
                if (oldIdTiporespuestaOfRespuestaEncuestadocenteListRespuestaEncuestadocente != null) {
                    oldIdTiporespuestaOfRespuestaEncuestadocenteListRespuestaEncuestadocente.getRespuestaEncuestadocenteList().remove(respuestaEncuestadocenteListRespuestaEncuestadocente);
                    oldIdTiporespuestaOfRespuestaEncuestadocenteListRespuestaEncuestadocente = em.merge(oldIdTiporespuestaOfRespuestaEncuestadocenteListRespuestaEncuestadocente);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoRespuesta tipoRespuesta) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoRespuesta persistentTipoRespuesta = em.find(TipoRespuesta.class, tipoRespuesta.getIdTiporespuesta());
            List<RespuestaEncuesta> respuestaEncuestaListOld = persistentTipoRespuesta.getRespuestaEncuestaList();
            List<RespuestaEncuesta> respuestaEncuestaListNew = tipoRespuesta.getRespuestaEncuestaList();
            List<RespuestaEncuestadocente> respuestaEncuestadocenteListOld = persistentTipoRespuesta.getRespuestaEncuestadocenteList();
            List<RespuestaEncuestadocente> respuestaEncuestadocenteListNew = tipoRespuesta.getRespuestaEncuestadocenteList();
            List<RespuestaEncuesta> attachedRespuestaEncuestaListNew = new ArrayList<RespuestaEncuesta>();
            for (RespuestaEncuesta respuestaEncuestaListNewRespuestaEncuestaToAttach : respuestaEncuestaListNew) {
                respuestaEncuestaListNewRespuestaEncuestaToAttach = em.getReference(respuestaEncuestaListNewRespuestaEncuestaToAttach.getClass(), respuestaEncuestaListNewRespuestaEncuestaToAttach.getIdRespuestaencuesta());
                attachedRespuestaEncuestaListNew.add(respuestaEncuestaListNewRespuestaEncuestaToAttach);
            }
            respuestaEncuestaListNew = attachedRespuestaEncuestaListNew;
            tipoRespuesta.setRespuestaEncuestaList(respuestaEncuestaListNew);
            List<RespuestaEncuestadocente> attachedRespuestaEncuestadocenteListNew = new ArrayList<RespuestaEncuestadocente>();
            for (RespuestaEncuestadocente respuestaEncuestadocenteListNewRespuestaEncuestadocenteToAttach : respuestaEncuestadocenteListNew) {
                respuestaEncuestadocenteListNewRespuestaEncuestadocenteToAttach = em.getReference(respuestaEncuestadocenteListNewRespuestaEncuestadocenteToAttach.getClass(), respuestaEncuestadocenteListNewRespuestaEncuestadocenteToAttach.getIdRespuestaencuestadocente());
                attachedRespuestaEncuestadocenteListNew.add(respuestaEncuestadocenteListNewRespuestaEncuestadocenteToAttach);
            }
            respuestaEncuestadocenteListNew = attachedRespuestaEncuestadocenteListNew;
            tipoRespuesta.setRespuestaEncuestadocenteList(respuestaEncuestadocenteListNew);
            tipoRespuesta = em.merge(tipoRespuesta);
            for (RespuestaEncuesta respuestaEncuestaListOldRespuestaEncuesta : respuestaEncuestaListOld) {
                if (!respuestaEncuestaListNew.contains(respuestaEncuestaListOldRespuestaEncuesta)) {
                    respuestaEncuestaListOldRespuestaEncuesta.setIdTiporespuesta(null);
                    respuestaEncuestaListOldRespuestaEncuesta = em.merge(respuestaEncuestaListOldRespuestaEncuesta);
                }
            }
            for (RespuestaEncuesta respuestaEncuestaListNewRespuestaEncuesta : respuestaEncuestaListNew) {
                if (!respuestaEncuestaListOld.contains(respuestaEncuestaListNewRespuestaEncuesta)) {
                    TipoRespuesta oldIdTiporespuestaOfRespuestaEncuestaListNewRespuestaEncuesta = respuestaEncuestaListNewRespuestaEncuesta.getIdTiporespuesta();
                    respuestaEncuestaListNewRespuestaEncuesta.setIdTiporespuesta(tipoRespuesta);
                    respuestaEncuestaListNewRespuestaEncuesta = em.merge(respuestaEncuestaListNewRespuestaEncuesta);
                    if (oldIdTiporespuestaOfRespuestaEncuestaListNewRespuestaEncuesta != null && !oldIdTiporespuestaOfRespuestaEncuestaListNewRespuestaEncuesta.equals(tipoRespuesta)) {
                        oldIdTiporespuestaOfRespuestaEncuestaListNewRespuestaEncuesta.getRespuestaEncuestaList().remove(respuestaEncuestaListNewRespuestaEncuesta);
                        oldIdTiporespuestaOfRespuestaEncuestaListNewRespuestaEncuesta = em.merge(oldIdTiporespuestaOfRespuestaEncuestaListNewRespuestaEncuesta);
                    }
                }
            }
            for (RespuestaEncuestadocente respuestaEncuestadocenteListOldRespuestaEncuestadocente : respuestaEncuestadocenteListOld) {
                if (!respuestaEncuestadocenteListNew.contains(respuestaEncuestadocenteListOldRespuestaEncuestadocente)) {
                    respuestaEncuestadocenteListOldRespuestaEncuestadocente.setIdTiporespuesta(null);
                    respuestaEncuestadocenteListOldRespuestaEncuestadocente = em.merge(respuestaEncuestadocenteListOldRespuestaEncuestadocente);
                }
            }
            for (RespuestaEncuestadocente respuestaEncuestadocenteListNewRespuestaEncuestadocente : respuestaEncuestadocenteListNew) {
                if (!respuestaEncuestadocenteListOld.contains(respuestaEncuestadocenteListNewRespuestaEncuestadocente)) {
                    TipoRespuesta oldIdTiporespuestaOfRespuestaEncuestadocenteListNewRespuestaEncuestadocente = respuestaEncuestadocenteListNewRespuestaEncuestadocente.getIdTiporespuesta();
                    respuestaEncuestadocenteListNewRespuestaEncuestadocente.setIdTiporespuesta(tipoRespuesta);
                    respuestaEncuestadocenteListNewRespuestaEncuestadocente = em.merge(respuestaEncuestadocenteListNewRespuestaEncuestadocente);
                    if (oldIdTiporespuestaOfRespuestaEncuestadocenteListNewRespuestaEncuestadocente != null && !oldIdTiporespuestaOfRespuestaEncuestadocenteListNewRespuestaEncuestadocente.equals(tipoRespuesta)) {
                        oldIdTiporespuestaOfRespuestaEncuestadocenteListNewRespuestaEncuestadocente.getRespuestaEncuestadocenteList().remove(respuestaEncuestadocenteListNewRespuestaEncuestadocente);
                        oldIdTiporespuestaOfRespuestaEncuestadocenteListNewRespuestaEncuestadocente = em.merge(oldIdTiporespuestaOfRespuestaEncuestadocenteListNewRespuestaEncuestadocente);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tipoRespuesta.getIdTiporespuesta();
                if (findTipoRespuesta(id) == null) {
                    throw new NonexistentEntityException("The tipoRespuesta with id " + id + " no longer exists.");
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
            TipoRespuesta tipoRespuesta;
            try {
                tipoRespuesta = em.getReference(TipoRespuesta.class, id);
                tipoRespuesta.getIdTiporespuesta();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoRespuesta with id " + id + " no longer exists.", enfe);
            }
            List<RespuestaEncuesta> respuestaEncuestaList = tipoRespuesta.getRespuestaEncuestaList();
            for (RespuestaEncuesta respuestaEncuestaListRespuestaEncuesta : respuestaEncuestaList) {
                respuestaEncuestaListRespuestaEncuesta.setIdTiporespuesta(null);
                respuestaEncuestaListRespuestaEncuesta = em.merge(respuestaEncuestaListRespuestaEncuesta);
            }
            List<RespuestaEncuestadocente> respuestaEncuestadocenteList = tipoRespuesta.getRespuestaEncuestadocenteList();
            for (RespuestaEncuestadocente respuestaEncuestadocenteListRespuestaEncuestadocente : respuestaEncuestadocenteList) {
                respuestaEncuestadocenteListRespuestaEncuestadocente.setIdTiporespuesta(null);
                respuestaEncuestadocenteListRespuestaEncuestadocente = em.merge(respuestaEncuestadocenteListRespuestaEncuestadocente);
            }
            em.remove(tipoRespuesta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoRespuesta> findTipoRespuestaEntities() {
        return findTipoRespuestaEntities(true, -1, -1);
    }

    public List<TipoRespuesta> findTipoRespuestaEntities(int maxResults, int firstResult) {
        return findTipoRespuestaEntities(false, maxResults, firstResult);
    }

    private List<TipoRespuesta> findTipoRespuestaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from TipoRespuesta as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public TipoRespuesta findTipoRespuesta(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoRespuesta.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoRespuestaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from TipoRespuesta as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
