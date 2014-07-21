/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.AsignacionRespuestapredeterminadapregunta;
import com.utez.integracion.entity.RespuestaPredeterminada;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class RespuestaPredeterminadaJpaController implements Serializable {

    public RespuestaPredeterminadaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(RespuestaPredeterminada respuestaPredeterminada) {
        if (respuestaPredeterminada.getAsignacionRespuestapredeterminadapreguntaList() == null) {
            respuestaPredeterminada.setAsignacionRespuestapredeterminadapreguntaList(new ArrayList<AsignacionRespuestapredeterminadapregunta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<AsignacionRespuestapredeterminadapregunta> attachedAsignacionRespuestapredeterminadapreguntaList = new ArrayList<AsignacionRespuestapredeterminadapregunta>();
            for (AsignacionRespuestapredeterminadapregunta asignacionRespuestapredeterminadapreguntaListAsignacionRespuestapredeterminadapreguntaToAttach : respuestaPredeterminada.getAsignacionRespuestapredeterminadapreguntaList()) {
                asignacionRespuestapredeterminadapreguntaListAsignacionRespuestapredeterminadapreguntaToAttach = em.getReference(asignacionRespuestapredeterminadapreguntaListAsignacionRespuestapredeterminadapreguntaToAttach.getClass(), asignacionRespuestapredeterminadapreguntaListAsignacionRespuestapredeterminadapreguntaToAttach.getIdAsignacionrespuestapredeterminadapregunta());
                attachedAsignacionRespuestapredeterminadapreguntaList.add(asignacionRespuestapredeterminadapreguntaListAsignacionRespuestapredeterminadapreguntaToAttach);
            }
            respuestaPredeterminada.setAsignacionRespuestapredeterminadapreguntaList(attachedAsignacionRespuestapredeterminadapreguntaList);
            em.persist(respuestaPredeterminada);
            for (AsignacionRespuestapredeterminadapregunta asignacionRespuestapredeterminadapreguntaListAsignacionRespuestapredeterminadapregunta : respuestaPredeterminada.getAsignacionRespuestapredeterminadapreguntaList()) {
                RespuestaPredeterminada oldIdRespuestapredeterminadaOfAsignacionRespuestapredeterminadapreguntaListAsignacionRespuestapredeterminadapregunta = asignacionRespuestapredeterminadapreguntaListAsignacionRespuestapredeterminadapregunta.getIdRespuestapredeterminada();
                asignacionRespuestapredeterminadapreguntaListAsignacionRespuestapredeterminadapregunta.setIdRespuestapredeterminada(respuestaPredeterminada);
                asignacionRespuestapredeterminadapreguntaListAsignacionRespuestapredeterminadapregunta = em.merge(asignacionRespuestapredeterminadapreguntaListAsignacionRespuestapredeterminadapregunta);
                if (oldIdRespuestapredeterminadaOfAsignacionRespuestapredeterminadapreguntaListAsignacionRespuestapredeterminadapregunta != null) {
                    oldIdRespuestapredeterminadaOfAsignacionRespuestapredeterminadapreguntaListAsignacionRespuestapredeterminadapregunta.getAsignacionRespuestapredeterminadapreguntaList().remove(asignacionRespuestapredeterminadapreguntaListAsignacionRespuestapredeterminadapregunta);
                    oldIdRespuestapredeterminadaOfAsignacionRespuestapredeterminadapreguntaListAsignacionRespuestapredeterminadapregunta = em.merge(oldIdRespuestapredeterminadaOfAsignacionRespuestapredeterminadapreguntaListAsignacionRespuestapredeterminadapregunta);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(RespuestaPredeterminada respuestaPredeterminada) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RespuestaPredeterminada persistentRespuestaPredeterminada = em.find(RespuestaPredeterminada.class, respuestaPredeterminada.getIdRespuestapredeterminada());
            List<AsignacionRespuestapredeterminadapregunta> asignacionRespuestapredeterminadapreguntaListOld = persistentRespuestaPredeterminada.getAsignacionRespuestapredeterminadapreguntaList();
            List<AsignacionRespuestapredeterminadapregunta> asignacionRespuestapredeterminadapreguntaListNew = respuestaPredeterminada.getAsignacionRespuestapredeterminadapreguntaList();
            List<AsignacionRespuestapredeterminadapregunta> attachedAsignacionRespuestapredeterminadapreguntaListNew = new ArrayList<AsignacionRespuestapredeterminadapregunta>();
            for (AsignacionRespuestapredeterminadapregunta asignacionRespuestapredeterminadapreguntaListNewAsignacionRespuestapredeterminadapreguntaToAttach : asignacionRespuestapredeterminadapreguntaListNew) {
                asignacionRespuestapredeterminadapreguntaListNewAsignacionRespuestapredeterminadapreguntaToAttach = em.getReference(asignacionRespuestapredeterminadapreguntaListNewAsignacionRespuestapredeterminadapreguntaToAttach.getClass(), asignacionRespuestapredeterminadapreguntaListNewAsignacionRespuestapredeterminadapreguntaToAttach.getIdAsignacionrespuestapredeterminadapregunta());
                attachedAsignacionRespuestapredeterminadapreguntaListNew.add(asignacionRespuestapredeterminadapreguntaListNewAsignacionRespuestapredeterminadapreguntaToAttach);
            }
            asignacionRespuestapredeterminadapreguntaListNew = attachedAsignacionRespuestapredeterminadapreguntaListNew;
            respuestaPredeterminada.setAsignacionRespuestapredeterminadapreguntaList(asignacionRespuestapredeterminadapreguntaListNew);
            respuestaPredeterminada = em.merge(respuestaPredeterminada);
            for (AsignacionRespuestapredeterminadapregunta asignacionRespuestapredeterminadapreguntaListOldAsignacionRespuestapredeterminadapregunta : asignacionRespuestapredeterminadapreguntaListOld) {
                if (!asignacionRespuestapredeterminadapreguntaListNew.contains(asignacionRespuestapredeterminadapreguntaListOldAsignacionRespuestapredeterminadapregunta)) {
                    asignacionRespuestapredeterminadapreguntaListOldAsignacionRespuestapredeterminadapregunta.setIdRespuestapredeterminada(null);
                    asignacionRespuestapredeterminadapreguntaListOldAsignacionRespuestapredeterminadapregunta = em.merge(asignacionRespuestapredeterminadapreguntaListOldAsignacionRespuestapredeterminadapregunta);
                }
            }
            for (AsignacionRespuestapredeterminadapregunta asignacionRespuestapredeterminadapreguntaListNewAsignacionRespuestapredeterminadapregunta : asignacionRespuestapredeterminadapreguntaListNew) {
                if (!asignacionRespuestapredeterminadapreguntaListOld.contains(asignacionRespuestapredeterminadapreguntaListNewAsignacionRespuestapredeterminadapregunta)) {
                    RespuestaPredeterminada oldIdRespuestapredeterminadaOfAsignacionRespuestapredeterminadapreguntaListNewAsignacionRespuestapredeterminadapregunta = asignacionRespuestapredeterminadapreguntaListNewAsignacionRespuestapredeterminadapregunta.getIdRespuestapredeterminada();
                    asignacionRespuestapredeterminadapreguntaListNewAsignacionRespuestapredeterminadapregunta.setIdRespuestapredeterminada(respuestaPredeterminada);
                    asignacionRespuestapredeterminadapreguntaListNewAsignacionRespuestapredeterminadapregunta = em.merge(asignacionRespuestapredeterminadapreguntaListNewAsignacionRespuestapredeterminadapregunta);
                    if (oldIdRespuestapredeterminadaOfAsignacionRespuestapredeterminadapreguntaListNewAsignacionRespuestapredeterminadapregunta != null && !oldIdRespuestapredeterminadaOfAsignacionRespuestapredeterminadapreguntaListNewAsignacionRespuestapredeterminadapregunta.equals(respuestaPredeterminada)) {
                        oldIdRespuestapredeterminadaOfAsignacionRespuestapredeterminadapreguntaListNewAsignacionRespuestapredeterminadapregunta.getAsignacionRespuestapredeterminadapreguntaList().remove(asignacionRespuestapredeterminadapreguntaListNewAsignacionRespuestapredeterminadapregunta);
                        oldIdRespuestapredeterminadaOfAsignacionRespuestapredeterminadapreguntaListNewAsignacionRespuestapredeterminadapregunta = em.merge(oldIdRespuestapredeterminadaOfAsignacionRespuestapredeterminadapreguntaListNewAsignacionRespuestapredeterminadapregunta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = respuestaPredeterminada.getIdRespuestapredeterminada();
                if (findRespuestaPredeterminada(id) == null) {
                    throw new NonexistentEntityException("The respuestaPredeterminada with id " + id + " no longer exists.");
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
            RespuestaPredeterminada respuestaPredeterminada;
            try {
                respuestaPredeterminada = em.getReference(RespuestaPredeterminada.class, id);
                respuestaPredeterminada.getIdRespuestapredeterminada();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The respuestaPredeterminada with id " + id + " no longer exists.", enfe);
            }
            List<AsignacionRespuestapredeterminadapregunta> asignacionRespuestapredeterminadapreguntaList = respuestaPredeterminada.getAsignacionRespuestapredeterminadapreguntaList();
            for (AsignacionRespuestapredeterminadapregunta asignacionRespuestapredeterminadapreguntaListAsignacionRespuestapredeterminadapregunta : asignacionRespuestapredeterminadapreguntaList) {
                asignacionRespuestapredeterminadapreguntaListAsignacionRespuestapredeterminadapregunta.setIdRespuestapredeterminada(null);
                asignacionRespuestapredeterminadapreguntaListAsignacionRespuestapredeterminadapregunta = em.merge(asignacionRespuestapredeterminadapreguntaListAsignacionRespuestapredeterminadapregunta);
            }
            em.remove(respuestaPredeterminada);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<RespuestaPredeterminada> findRespuestaPredeterminadaEntities() {
        return findRespuestaPredeterminadaEntities(true, -1, -1);
    }

    public List<RespuestaPredeterminada> findRespuestaPredeterminadaEntities(int maxResults, int firstResult) {
        return findRespuestaPredeterminadaEntities(false, maxResults, firstResult);
    }

    private List<RespuestaPredeterminada> findRespuestaPredeterminadaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from RespuestaPredeterminada as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public RespuestaPredeterminada findRespuestaPredeterminada(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RespuestaPredeterminada.class, id);
        } finally {
            em.close();
        }
    }

    public int getRespuestaPredeterminadaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from RespuestaPredeterminada as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
