/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.ContenidoReactivo;
import com.utez.integracion.entity.RespuestaImagen;
import com.utez.integracion.entity.RespuestaReactivo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class RespuestaReactivoJpaController implements Serializable {

    public RespuestaReactivoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(RespuestaReactivo respuestaReactivo) {
        if (respuestaReactivo.getRespuestaImagenList() == null) {
            respuestaReactivo.setRespuestaImagenList(new ArrayList<RespuestaImagen>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ContenidoReactivo idContenidoreactivo = respuestaReactivo.getIdContenidoreactivo();
            if (idContenidoreactivo != null) {
                idContenidoreactivo = em.getReference(idContenidoreactivo.getClass(), idContenidoreactivo.getIdContenidoreactivo());
                respuestaReactivo.setIdContenidoreactivo(idContenidoreactivo);
            }
            List<RespuestaImagen> attachedRespuestaImagenList = new ArrayList<RespuestaImagen>();
            for (RespuestaImagen respuestaImagenListRespuestaImagenToAttach : respuestaReactivo.getRespuestaImagenList()) {
                respuestaImagenListRespuestaImagenToAttach = em.getReference(respuestaImagenListRespuestaImagenToAttach.getClass(), respuestaImagenListRespuestaImagenToAttach.getIdRespuestaimagen());
                attachedRespuestaImagenList.add(respuestaImagenListRespuestaImagenToAttach);
            }
            respuestaReactivo.setRespuestaImagenList(attachedRespuestaImagenList);
            em.persist(respuestaReactivo);
            if (idContenidoreactivo != null) {
                idContenidoreactivo.getRespuestaReactivoList().add(respuestaReactivo);
                idContenidoreactivo = em.merge(idContenidoreactivo);
            }
            for (RespuestaImagen respuestaImagenListRespuestaImagen : respuestaReactivo.getRespuestaImagenList()) {
                RespuestaReactivo oldIdRespuestareactivoOfRespuestaImagenListRespuestaImagen = respuestaImagenListRespuestaImagen.getIdRespuestareactivo();
                respuestaImagenListRespuestaImagen.setIdRespuestareactivo(respuestaReactivo);
                respuestaImagenListRespuestaImagen = em.merge(respuestaImagenListRespuestaImagen);
                if (oldIdRespuestareactivoOfRespuestaImagenListRespuestaImagen != null) {
                    oldIdRespuestareactivoOfRespuestaImagenListRespuestaImagen.getRespuestaImagenList().remove(respuestaImagenListRespuestaImagen);
                    oldIdRespuestareactivoOfRespuestaImagenListRespuestaImagen = em.merge(oldIdRespuestareactivoOfRespuestaImagenListRespuestaImagen);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(RespuestaReactivo respuestaReactivo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RespuestaReactivo persistentRespuestaReactivo = em.find(RespuestaReactivo.class, respuestaReactivo.getIdRespuestareactivo());
            ContenidoReactivo idContenidoreactivoOld = persistentRespuestaReactivo.getIdContenidoreactivo();
            ContenidoReactivo idContenidoreactivoNew = respuestaReactivo.getIdContenidoreactivo();
            List<RespuestaImagen> respuestaImagenListOld = persistentRespuestaReactivo.getRespuestaImagenList();
            List<RespuestaImagen> respuestaImagenListNew = respuestaReactivo.getRespuestaImagenList();
            if (idContenidoreactivoNew != null) {
                idContenidoreactivoNew = em.getReference(idContenidoreactivoNew.getClass(), idContenidoreactivoNew.getIdContenidoreactivo());
                respuestaReactivo.setIdContenidoreactivo(idContenidoreactivoNew);
            }
            List<RespuestaImagen> attachedRespuestaImagenListNew = new ArrayList<RespuestaImagen>();
            for (RespuestaImagen respuestaImagenListNewRespuestaImagenToAttach : respuestaImagenListNew) {
                respuestaImagenListNewRespuestaImagenToAttach = em.getReference(respuestaImagenListNewRespuestaImagenToAttach.getClass(), respuestaImagenListNewRespuestaImagenToAttach.getIdRespuestaimagen());
                attachedRespuestaImagenListNew.add(respuestaImagenListNewRespuestaImagenToAttach);
            }
            respuestaImagenListNew = attachedRespuestaImagenListNew;
            respuestaReactivo.setRespuestaImagenList(respuestaImagenListNew);
            respuestaReactivo = em.merge(respuestaReactivo);
            if (idContenidoreactivoOld != null && !idContenidoreactivoOld.equals(idContenidoreactivoNew)) {
                idContenidoreactivoOld.getRespuestaReactivoList().remove(respuestaReactivo);
                idContenidoreactivoOld = em.merge(idContenidoreactivoOld);
            }
            if (idContenidoreactivoNew != null && !idContenidoreactivoNew.equals(idContenidoreactivoOld)) {
                idContenidoreactivoNew.getRespuestaReactivoList().add(respuestaReactivo);
                idContenidoreactivoNew = em.merge(idContenidoreactivoNew);
            }
            for (RespuestaImagen respuestaImagenListOldRespuestaImagen : respuestaImagenListOld) {
                if (!respuestaImagenListNew.contains(respuestaImagenListOldRespuestaImagen)) {
                    respuestaImagenListOldRespuestaImagen.setIdRespuestareactivo(null);
                    respuestaImagenListOldRespuestaImagen = em.merge(respuestaImagenListOldRespuestaImagen);
                }
            }
            for (RespuestaImagen respuestaImagenListNewRespuestaImagen : respuestaImagenListNew) {
                if (!respuestaImagenListOld.contains(respuestaImagenListNewRespuestaImagen)) {
                    RespuestaReactivo oldIdRespuestareactivoOfRespuestaImagenListNewRespuestaImagen = respuestaImagenListNewRespuestaImagen.getIdRespuestareactivo();
                    respuestaImagenListNewRespuestaImagen.setIdRespuestareactivo(respuestaReactivo);
                    respuestaImagenListNewRespuestaImagen = em.merge(respuestaImagenListNewRespuestaImagen);
                    if (oldIdRespuestareactivoOfRespuestaImagenListNewRespuestaImagen != null && !oldIdRespuestareactivoOfRespuestaImagenListNewRespuestaImagen.equals(respuestaReactivo)) {
                        oldIdRespuestareactivoOfRespuestaImagenListNewRespuestaImagen.getRespuestaImagenList().remove(respuestaImagenListNewRespuestaImagen);
                        oldIdRespuestareactivoOfRespuestaImagenListNewRespuestaImagen = em.merge(oldIdRespuestareactivoOfRespuestaImagenListNewRespuestaImagen);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = respuestaReactivo.getIdRespuestareactivo();
                if (findRespuestaReactivo(id) == null) {
                    throw new NonexistentEntityException("The respuestaReactivo with id " + id + " no longer exists.");
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
            RespuestaReactivo respuestaReactivo;
            try {
                respuestaReactivo = em.getReference(RespuestaReactivo.class, id);
                respuestaReactivo.getIdRespuestareactivo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The respuestaReactivo with id " + id + " no longer exists.", enfe);
            }
            ContenidoReactivo idContenidoreactivo = respuestaReactivo.getIdContenidoreactivo();
            if (idContenidoreactivo != null) {
                idContenidoreactivo.getRespuestaReactivoList().remove(respuestaReactivo);
                idContenidoreactivo = em.merge(idContenidoreactivo);
            }
            List<RespuestaImagen> respuestaImagenList = respuestaReactivo.getRespuestaImagenList();
            for (RespuestaImagen respuestaImagenListRespuestaImagen : respuestaImagenList) {
                respuestaImagenListRespuestaImagen.setIdRespuestareactivo(null);
                respuestaImagenListRespuestaImagen = em.merge(respuestaImagenListRespuestaImagen);
            }
            em.remove(respuestaReactivo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<RespuestaReactivo> findRespuestaReactivoEntities() {
        return findRespuestaReactivoEntities(true, -1, -1);
    }

    public List<RespuestaReactivo> findRespuestaReactivoEntities(int maxResults, int firstResult) {
        return findRespuestaReactivoEntities(false, maxResults, firstResult);
    }

    private List<RespuestaReactivo> findRespuestaReactivoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from RespuestaReactivo as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public RespuestaReactivo findRespuestaReactivo(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RespuestaReactivo.class, id);
        } finally {
            em.close();
        }
    }

    public int getRespuestaReactivoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from RespuestaReactivo as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
