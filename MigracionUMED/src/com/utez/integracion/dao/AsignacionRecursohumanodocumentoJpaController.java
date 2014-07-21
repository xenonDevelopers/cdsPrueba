/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.entity.AsignacionRecursohumanodocumento;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.RecursoHumano;
import com.utez.integracion.entity.Documento;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class AsignacionRecursohumanodocumentoJpaController implements Serializable {

    public AsignacionRecursohumanodocumentoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AsignacionRecursohumanodocumento asignacionRecursohumanodocumento) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RecursoHumano idRecursohumano = asignacionRecursohumanodocumento.getIdRecursohumano();
            if (idRecursohumano != null) {
                idRecursohumano = em.getReference(idRecursohumano.getClass(), idRecursohumano.getIdRecursohumano());
                asignacionRecursohumanodocumento.setIdRecursohumano(idRecursohumano);
            }
            Documento idDocumento = asignacionRecursohumanodocumento.getIdDocumento();
            if (idDocumento != null) {
                idDocumento = em.getReference(idDocumento.getClass(), idDocumento.getIdDocumento());
                asignacionRecursohumanodocumento.setIdDocumento(idDocumento);
            }
            em.persist(asignacionRecursohumanodocumento);
            if (idRecursohumano != null) {
                idRecursohumano.getAsignacionRecursohumanodocumentoList().add(asignacionRecursohumanodocumento);
                idRecursohumano = em.merge(idRecursohumano);
            }
            if (idDocumento != null) {
                idDocumento.getAsignacionRecursohumanodocumentoList().add(asignacionRecursohumanodocumento);
                idDocumento = em.merge(idDocumento);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AsignacionRecursohumanodocumento asignacionRecursohumanodocumento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AsignacionRecursohumanodocumento persistentAsignacionRecursohumanodocumento = em.find(AsignacionRecursohumanodocumento.class, asignacionRecursohumanodocumento.getIdAsignacionrecursohumanodocumento());
            RecursoHumano idRecursohumanoOld = persistentAsignacionRecursohumanodocumento.getIdRecursohumano();
            RecursoHumano idRecursohumanoNew = asignacionRecursohumanodocumento.getIdRecursohumano();
            Documento idDocumentoOld = persistentAsignacionRecursohumanodocumento.getIdDocumento();
            Documento idDocumentoNew = asignacionRecursohumanodocumento.getIdDocumento();
            if (idRecursohumanoNew != null) {
                idRecursohumanoNew = em.getReference(idRecursohumanoNew.getClass(), idRecursohumanoNew.getIdRecursohumano());
                asignacionRecursohumanodocumento.setIdRecursohumano(idRecursohumanoNew);
            }
            if (idDocumentoNew != null) {
                idDocumentoNew = em.getReference(idDocumentoNew.getClass(), idDocumentoNew.getIdDocumento());
                asignacionRecursohumanodocumento.setIdDocumento(idDocumentoNew);
            }
            asignacionRecursohumanodocumento = em.merge(asignacionRecursohumanodocumento);
            if (idRecursohumanoOld != null && !idRecursohumanoOld.equals(idRecursohumanoNew)) {
                idRecursohumanoOld.getAsignacionRecursohumanodocumentoList().remove(asignacionRecursohumanodocumento);
                idRecursohumanoOld = em.merge(idRecursohumanoOld);
            }
            if (idRecursohumanoNew != null && !idRecursohumanoNew.equals(idRecursohumanoOld)) {
                idRecursohumanoNew.getAsignacionRecursohumanodocumentoList().add(asignacionRecursohumanodocumento);
                idRecursohumanoNew = em.merge(idRecursohumanoNew);
            }
            if (idDocumentoOld != null && !idDocumentoOld.equals(idDocumentoNew)) {
                idDocumentoOld.getAsignacionRecursohumanodocumentoList().remove(asignacionRecursohumanodocumento);
                idDocumentoOld = em.merge(idDocumentoOld);
            }
            if (idDocumentoNew != null && !idDocumentoNew.equals(idDocumentoOld)) {
                idDocumentoNew.getAsignacionRecursohumanodocumentoList().add(asignacionRecursohumanodocumento);
                idDocumentoNew = em.merge(idDocumentoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = asignacionRecursohumanodocumento.getIdAsignacionrecursohumanodocumento();
                if (findAsignacionRecursohumanodocumento(id) == null) {
                    throw new NonexistentEntityException("The asignacionRecursohumanodocumento with id " + id + " no longer exists.");
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
            AsignacionRecursohumanodocumento asignacionRecursohumanodocumento;
            try {
                asignacionRecursohumanodocumento = em.getReference(AsignacionRecursohumanodocumento.class, id);
                asignacionRecursohumanodocumento.getIdAsignacionrecursohumanodocumento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The asignacionRecursohumanodocumento with id " + id + " no longer exists.", enfe);
            }
            RecursoHumano idRecursohumano = asignacionRecursohumanodocumento.getIdRecursohumano();
            if (idRecursohumano != null) {
                idRecursohumano.getAsignacionRecursohumanodocumentoList().remove(asignacionRecursohumanodocumento);
                idRecursohumano = em.merge(idRecursohumano);
            }
            Documento idDocumento = asignacionRecursohumanodocumento.getIdDocumento();
            if (idDocumento != null) {
                idDocumento.getAsignacionRecursohumanodocumentoList().remove(asignacionRecursohumanodocumento);
                idDocumento = em.merge(idDocumento);
            }
            em.remove(asignacionRecursohumanodocumento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AsignacionRecursohumanodocumento> findAsignacionRecursohumanodocumentoEntities() {
        return findAsignacionRecursohumanodocumentoEntities(true, -1, -1);
    }

    public List<AsignacionRecursohumanodocumento> findAsignacionRecursohumanodocumentoEntities(int maxResults, int firstResult) {
        return findAsignacionRecursohumanodocumentoEntities(false, maxResults, firstResult);
    }

    private List<AsignacionRecursohumanodocumento> findAsignacionRecursohumanodocumentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from AsignacionRecursohumanodocumento as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public AsignacionRecursohumanodocumento findAsignacionRecursohumanodocumento(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AsignacionRecursohumanodocumento.class, id);
        } finally {
            em.close();
        }
    }

    public int getAsignacionRecursohumanodocumentoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from AsignacionRecursohumanodocumento as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
