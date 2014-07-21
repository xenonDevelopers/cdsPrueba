/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.TipoAutor;
import com.utez.secretariaAcademica.entity.Asesor;
import com.utez.integracion.entity.ActividadIntegradora;
import com.utez.integracion.entity.AsignacionAutorintegradora;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class AsignacionAutorintegradoraJpaController implements Serializable {

    public AsignacionAutorintegradoraJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AsignacionAutorintegradora asignacionAutorintegradora) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoAutor idTipoautor = asignacionAutorintegradora.getIdTipoautor();
            if (idTipoautor != null) {
                idTipoautor = em.getReference(idTipoautor.getClass(), idTipoautor.getIdTipoautor());
                asignacionAutorintegradora.setIdTipoautor(idTipoautor);
            }
            Asesor idAutor = asignacionAutorintegradora.getIdAutor();
            if (idAutor != null) {
                idAutor = em.getReference(idAutor.getClass(), idAutor.getIdasesor());
                asignacionAutorintegradora.setIdAutor(idAutor);
            }
            ActividadIntegradora idActividadintegradora = asignacionAutorintegradora.getIdActividadintegradora();
            if (idActividadintegradora != null) {
                idActividadintegradora = em.getReference(idActividadintegradora.getClass(), idActividadintegradora.getIdActividadintegradora());
                asignacionAutorintegradora.setIdActividadintegradora(idActividadintegradora);
            }
            em.persist(asignacionAutorintegradora);
            if (idTipoautor != null) {
                idTipoautor.getAsignacionAutorintegradoraList().add(asignacionAutorintegradora);
                idTipoautor = em.merge(idTipoautor);
            }
            if (idAutor != null) {
                idAutor.getAsignacionAutorintegradoraList().add(asignacionAutorintegradora);
                idAutor = em.merge(idAutor);
            }
            if (idActividadintegradora != null) {
                idActividadintegradora.getAsignacionAutorintegradoraList().add(asignacionAutorintegradora);
                idActividadintegradora = em.merge(idActividadintegradora);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AsignacionAutorintegradora asignacionAutorintegradora) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AsignacionAutorintegradora persistentAsignacionAutorintegradora = em.find(AsignacionAutorintegradora.class, asignacionAutorintegradora.getIdAsignacionautorintegradora());
            TipoAutor idTipoautorOld = persistentAsignacionAutorintegradora.getIdTipoautor();
            TipoAutor idTipoautorNew = asignacionAutorintegradora.getIdTipoautor();
            Asesor idAutorOld = persistentAsignacionAutorintegradora.getIdAutor();
            Asesor idAutorNew = asignacionAutorintegradora.getIdAutor();
            ActividadIntegradora idActividadintegradoraOld = persistentAsignacionAutorintegradora.getIdActividadintegradora();
            ActividadIntegradora idActividadintegradoraNew = asignacionAutorintegradora.getIdActividadintegradora();
            if (idTipoautorNew != null) {
                idTipoautorNew = em.getReference(idTipoautorNew.getClass(), idTipoautorNew.getIdTipoautor());
                asignacionAutorintegradora.setIdTipoautor(idTipoautorNew);
            }
            if (idAutorNew != null) {
                idAutorNew = em.getReference(idAutorNew.getClass(), idAutorNew.getIdasesor());
                asignacionAutorintegradora.setIdAutor(idAutorNew);
            }
            if (idActividadintegradoraNew != null) {
                idActividadintegradoraNew = em.getReference(idActividadintegradoraNew.getClass(), idActividadintegradoraNew.getIdActividadintegradora());
                asignacionAutorintegradora.setIdActividadintegradora(idActividadintegradoraNew);
            }
            asignacionAutorintegradora = em.merge(asignacionAutorintegradora);
            if (idTipoautorOld != null && !idTipoautorOld.equals(idTipoautorNew)) {
                idTipoautorOld.getAsignacionAutorintegradoraList().remove(asignacionAutorintegradora);
                idTipoautorOld = em.merge(idTipoautorOld);
            }
            if (idTipoautorNew != null && !idTipoautorNew.equals(idTipoautorOld)) {
                idTipoautorNew.getAsignacionAutorintegradoraList().add(asignacionAutorintegradora);
                idTipoautorNew = em.merge(idTipoautorNew);
            }
            if (idAutorOld != null && !idAutorOld.equals(idAutorNew)) {
                idAutorOld.getAsignacionAutorintegradoraList().remove(asignacionAutorintegradora);
                idAutorOld = em.merge(idAutorOld);
            }
            if (idAutorNew != null && !idAutorNew.equals(idAutorOld)) {
                idAutorNew.getAsignacionAutorintegradoraList().add(asignacionAutorintegradora);
                idAutorNew = em.merge(idAutorNew);
            }
            if (idActividadintegradoraOld != null && !idActividadintegradoraOld.equals(idActividadintegradoraNew)) {
                idActividadintegradoraOld.getAsignacionAutorintegradoraList().remove(asignacionAutorintegradora);
                idActividadintegradoraOld = em.merge(idActividadintegradoraOld);
            }
            if (idActividadintegradoraNew != null && !idActividadintegradoraNew.equals(idActividadintegradoraOld)) {
                idActividadintegradoraNew.getAsignacionAutorintegradoraList().add(asignacionAutorintegradora);
                idActividadintegradoraNew = em.merge(idActividadintegradoraNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = asignacionAutorintegradora.getIdAsignacionautorintegradora();
                if (findAsignacionAutorintegradora(id) == null) {
                    throw new NonexistentEntityException("The asignacionAutorintegradora with id " + id + " no longer exists.");
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
            AsignacionAutorintegradora asignacionAutorintegradora;
            try {
                asignacionAutorintegradora = em.getReference(AsignacionAutorintegradora.class, id);
                asignacionAutorintegradora.getIdAsignacionautorintegradora();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The asignacionAutorintegradora with id " + id + " no longer exists.", enfe);
            }
            TipoAutor idTipoautor = asignacionAutorintegradora.getIdTipoautor();
            if (idTipoautor != null) {
                idTipoautor.getAsignacionAutorintegradoraList().remove(asignacionAutorintegradora);
                idTipoautor = em.merge(idTipoautor);
            }
            Asesor idAutor = asignacionAutorintegradora.getIdAutor();
            if (idAutor != null) {
                idAutor.getAsignacionAutorintegradoraList().remove(asignacionAutorintegradora);
                idAutor = em.merge(idAutor);
            }
            ActividadIntegradora idActividadintegradora = asignacionAutorintegradora.getIdActividadintegradora();
            if (idActividadintegradora != null) {
                idActividadintegradora.getAsignacionAutorintegradoraList().remove(asignacionAutorintegradora);
                idActividadintegradora = em.merge(idActividadintegradora);
            }
            em.remove(asignacionAutorintegradora);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AsignacionAutorintegradora> findAsignacionAutorintegradoraEntities() {
        return findAsignacionAutorintegradoraEntities(true, -1, -1);
    }

    public List<AsignacionAutorintegradora> findAsignacionAutorintegradoraEntities(int maxResults, int firstResult) {
        return findAsignacionAutorintegradoraEntities(false, maxResults, firstResult);
    }

    private List<AsignacionAutorintegradora> findAsignacionAutorintegradoraEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from AsignacionAutorintegradora as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public AsignacionAutorintegradora findAsignacionAutorintegradora(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AsignacionAutorintegradora.class, id);
        } finally {
            em.close();
        }
    }

    public int getAsignacionAutorintegradoraCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from AsignacionAutorintegradora as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
