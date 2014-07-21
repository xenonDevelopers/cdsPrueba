/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.entity.Idioma;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.TipoNivelidioma;
import com.utez.integracion.entity.RecursoHumano;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class IdiomaJpaController implements Serializable {

    public IdiomaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Idioma idioma) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoNivelidioma idTiponivelidioma = idioma.getIdTiponivelidioma();
            if (idTiponivelidioma != null) {
                idTiponivelidioma = em.getReference(idTiponivelidioma.getClass(), idTiponivelidioma.getIdTiponivelidioma());
                idioma.setIdTiponivelidioma(idTiponivelidioma);
            }
            RecursoHumano idRecursohumano = idioma.getIdRecursohumano();
            if (idRecursohumano != null) {
                idRecursohumano = em.getReference(idRecursohumano.getClass(), idRecursohumano.getIdRecursohumano());
                idioma.setIdRecursohumano(idRecursohumano);
            }
            em.persist(idioma);
            if (idTiponivelidioma != null) {
                idTiponivelidioma.getIdiomaList().add(idioma);
                idTiponivelidioma = em.merge(idTiponivelidioma);
            }
            if (idRecursohumano != null) {
                idRecursohumano.getIdiomaList().add(idioma);
                idRecursohumano = em.merge(idRecursohumano);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Idioma idioma) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Idioma persistentIdioma = em.find(Idioma.class, idioma.getIdIdioma());
            TipoNivelidioma idTiponivelidiomaOld = persistentIdioma.getIdTiponivelidioma();
            TipoNivelidioma idTiponivelidiomaNew = idioma.getIdTiponivelidioma();
            RecursoHumano idRecursohumanoOld = persistentIdioma.getIdRecursohumano();
            RecursoHumano idRecursohumanoNew = idioma.getIdRecursohumano();
            if (idTiponivelidiomaNew != null) {
                idTiponivelidiomaNew = em.getReference(idTiponivelidiomaNew.getClass(), idTiponivelidiomaNew.getIdTiponivelidioma());
                idioma.setIdTiponivelidioma(idTiponivelidiomaNew);
            }
            if (idRecursohumanoNew != null) {
                idRecursohumanoNew = em.getReference(idRecursohumanoNew.getClass(), idRecursohumanoNew.getIdRecursohumano());
                idioma.setIdRecursohumano(idRecursohumanoNew);
            }
            idioma = em.merge(idioma);
            if (idTiponivelidiomaOld != null && !idTiponivelidiomaOld.equals(idTiponivelidiomaNew)) {
                idTiponivelidiomaOld.getIdiomaList().remove(idioma);
                idTiponivelidiomaOld = em.merge(idTiponivelidiomaOld);
            }
            if (idTiponivelidiomaNew != null && !idTiponivelidiomaNew.equals(idTiponivelidiomaOld)) {
                idTiponivelidiomaNew.getIdiomaList().add(idioma);
                idTiponivelidiomaNew = em.merge(idTiponivelidiomaNew);
            }
            if (idRecursohumanoOld != null && !idRecursohumanoOld.equals(idRecursohumanoNew)) {
                idRecursohumanoOld.getIdiomaList().remove(idioma);
                idRecursohumanoOld = em.merge(idRecursohumanoOld);
            }
            if (idRecursohumanoNew != null && !idRecursohumanoNew.equals(idRecursohumanoOld)) {
                idRecursohumanoNew.getIdiomaList().add(idioma);
                idRecursohumanoNew = em.merge(idRecursohumanoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = idioma.getIdIdioma();
                if (findIdioma(id) == null) {
                    throw new NonexistentEntityException("The idioma with id " + id + " no longer exists.");
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
            Idioma idioma;
            try {
                idioma = em.getReference(Idioma.class, id);
                idioma.getIdIdioma();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The idioma with id " + id + " no longer exists.", enfe);
            }
            TipoNivelidioma idTiponivelidioma = idioma.getIdTiponivelidioma();
            if (idTiponivelidioma != null) {
                idTiponivelidioma.getIdiomaList().remove(idioma);
                idTiponivelidioma = em.merge(idTiponivelidioma);
            }
            RecursoHumano idRecursohumano = idioma.getIdRecursohumano();
            if (idRecursohumano != null) {
                idRecursohumano.getIdiomaList().remove(idioma);
                idRecursohumano = em.merge(idRecursohumano);
            }
            em.remove(idioma);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Idioma> findIdiomaEntities() {
        return findIdiomaEntities(true, -1, -1);
    }

    public List<Idioma> findIdiomaEntities(int maxResults, int firstResult) {
        return findIdiomaEntities(false, maxResults, firstResult);
    }

    private List<Idioma> findIdiomaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Idioma as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Idioma findIdioma(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Idioma.class, id);
        } finally {
            em.close();
        }
    }

    public int getIdiomaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Idioma as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
