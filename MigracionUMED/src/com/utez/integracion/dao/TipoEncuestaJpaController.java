/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.Encuesta;
import com.utez.integracion.entity.TipoEncuesta;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class TipoEncuestaJpaController implements Serializable {

    public TipoEncuestaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoEncuesta tipoEncuesta) {
        if (tipoEncuesta.getEncuestaList() == null) {
            tipoEncuesta.setEncuestaList(new ArrayList<Encuesta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Encuesta> attachedEncuestaList = new ArrayList<Encuesta>();
            for (Encuesta encuestaListEncuestaToAttach : tipoEncuesta.getEncuestaList()) {
                encuestaListEncuestaToAttach = em.getReference(encuestaListEncuestaToAttach.getClass(), encuestaListEncuestaToAttach.getIdEncuesta());
                attachedEncuestaList.add(encuestaListEncuestaToAttach);
            }
            tipoEncuesta.setEncuestaList(attachedEncuestaList);
            em.persist(tipoEncuesta);
            for (Encuesta encuestaListEncuesta : tipoEncuesta.getEncuestaList()) {
                TipoEncuesta oldIdTipoencuestaOfEncuestaListEncuesta = encuestaListEncuesta.getIdTipoencuesta();
                encuestaListEncuesta.setIdTipoencuesta(tipoEncuesta);
                encuestaListEncuesta = em.merge(encuestaListEncuesta);
                if (oldIdTipoencuestaOfEncuestaListEncuesta != null) {
                    oldIdTipoencuestaOfEncuestaListEncuesta.getEncuestaList().remove(encuestaListEncuesta);
                    oldIdTipoencuestaOfEncuestaListEncuesta = em.merge(oldIdTipoencuestaOfEncuestaListEncuesta);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoEncuesta tipoEncuesta) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoEncuesta persistentTipoEncuesta = em.find(TipoEncuesta.class, tipoEncuesta.getIdTipoencuesta());
            List<Encuesta> encuestaListOld = persistentTipoEncuesta.getEncuestaList();
            List<Encuesta> encuestaListNew = tipoEncuesta.getEncuestaList();
            List<Encuesta> attachedEncuestaListNew = new ArrayList<Encuesta>();
            for (Encuesta encuestaListNewEncuestaToAttach : encuestaListNew) {
                encuestaListNewEncuestaToAttach = em.getReference(encuestaListNewEncuestaToAttach.getClass(), encuestaListNewEncuestaToAttach.getIdEncuesta());
                attachedEncuestaListNew.add(encuestaListNewEncuestaToAttach);
            }
            encuestaListNew = attachedEncuestaListNew;
            tipoEncuesta.setEncuestaList(encuestaListNew);
            tipoEncuesta = em.merge(tipoEncuesta);
            for (Encuesta encuestaListOldEncuesta : encuestaListOld) {
                if (!encuestaListNew.contains(encuestaListOldEncuesta)) {
                    encuestaListOldEncuesta.setIdTipoencuesta(null);
                    encuestaListOldEncuesta = em.merge(encuestaListOldEncuesta);
                }
            }
            for (Encuesta encuestaListNewEncuesta : encuestaListNew) {
                if (!encuestaListOld.contains(encuestaListNewEncuesta)) {
                    TipoEncuesta oldIdTipoencuestaOfEncuestaListNewEncuesta = encuestaListNewEncuesta.getIdTipoencuesta();
                    encuestaListNewEncuesta.setIdTipoencuesta(tipoEncuesta);
                    encuestaListNewEncuesta = em.merge(encuestaListNewEncuesta);
                    if (oldIdTipoencuestaOfEncuestaListNewEncuesta != null && !oldIdTipoencuestaOfEncuestaListNewEncuesta.equals(tipoEncuesta)) {
                        oldIdTipoencuestaOfEncuestaListNewEncuesta.getEncuestaList().remove(encuestaListNewEncuesta);
                        oldIdTipoencuestaOfEncuestaListNewEncuesta = em.merge(oldIdTipoencuestaOfEncuestaListNewEncuesta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tipoEncuesta.getIdTipoencuesta();
                if (findTipoEncuesta(id) == null) {
                    throw new NonexistentEntityException("The tipoEncuesta with id " + id + " no longer exists.");
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
            TipoEncuesta tipoEncuesta;
            try {
                tipoEncuesta = em.getReference(TipoEncuesta.class, id);
                tipoEncuesta.getIdTipoencuesta();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoEncuesta with id " + id + " no longer exists.", enfe);
            }
            List<Encuesta> encuestaList = tipoEncuesta.getEncuestaList();
            for (Encuesta encuestaListEncuesta : encuestaList) {
                encuestaListEncuesta.setIdTipoencuesta(null);
                encuestaListEncuesta = em.merge(encuestaListEncuesta);
            }
            em.remove(tipoEncuesta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoEncuesta> findTipoEncuestaEntities() {
        return findTipoEncuestaEntities(true, -1, -1);
    }

    public List<TipoEncuesta> findTipoEncuestaEntities(int maxResults, int firstResult) {
        return findTipoEncuestaEntities(false, maxResults, firstResult);
    }

    private List<TipoEncuesta> findTipoEncuestaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from TipoEncuesta as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public TipoEncuesta findTipoEncuesta(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoEncuesta.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoEncuestaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from TipoEncuesta as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
