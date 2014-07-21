/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.Idioma;
import com.utez.integracion.entity.TipoNivelidioma;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class TipoNivelidiomaJpaController implements Serializable {

    public TipoNivelidiomaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoNivelidioma tipoNivelidioma) {
        if (tipoNivelidioma.getIdiomaList() == null) {
            tipoNivelidioma.setIdiomaList(new ArrayList<Idioma>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Idioma> attachedIdiomaList = new ArrayList<Idioma>();
            for (Idioma idiomaListIdiomaToAttach : tipoNivelidioma.getIdiomaList()) {
                idiomaListIdiomaToAttach = em.getReference(idiomaListIdiomaToAttach.getClass(), idiomaListIdiomaToAttach.getIdIdioma());
                attachedIdiomaList.add(idiomaListIdiomaToAttach);
            }
            tipoNivelidioma.setIdiomaList(attachedIdiomaList);
            em.persist(tipoNivelidioma);
            for (Idioma idiomaListIdioma : tipoNivelidioma.getIdiomaList()) {
                TipoNivelidioma oldIdTiponivelidiomaOfIdiomaListIdioma = idiomaListIdioma.getIdTiponivelidioma();
                idiomaListIdioma.setIdTiponivelidioma(tipoNivelidioma);
                idiomaListIdioma = em.merge(idiomaListIdioma);
                if (oldIdTiponivelidiomaOfIdiomaListIdioma != null) {
                    oldIdTiponivelidiomaOfIdiomaListIdioma.getIdiomaList().remove(idiomaListIdioma);
                    oldIdTiponivelidiomaOfIdiomaListIdioma = em.merge(oldIdTiponivelidiomaOfIdiomaListIdioma);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoNivelidioma tipoNivelidioma) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoNivelidioma persistentTipoNivelidioma = em.find(TipoNivelidioma.class, tipoNivelidioma.getIdTiponivelidioma());
            List<Idioma> idiomaListOld = persistentTipoNivelidioma.getIdiomaList();
            List<Idioma> idiomaListNew = tipoNivelidioma.getIdiomaList();
            List<Idioma> attachedIdiomaListNew = new ArrayList<Idioma>();
            for (Idioma idiomaListNewIdiomaToAttach : idiomaListNew) {
                idiomaListNewIdiomaToAttach = em.getReference(idiomaListNewIdiomaToAttach.getClass(), idiomaListNewIdiomaToAttach.getIdIdioma());
                attachedIdiomaListNew.add(idiomaListNewIdiomaToAttach);
            }
            idiomaListNew = attachedIdiomaListNew;
            tipoNivelidioma.setIdiomaList(idiomaListNew);
            tipoNivelidioma = em.merge(tipoNivelidioma);
            for (Idioma idiomaListOldIdioma : idiomaListOld) {
                if (!idiomaListNew.contains(idiomaListOldIdioma)) {
                    idiomaListOldIdioma.setIdTiponivelidioma(null);
                    idiomaListOldIdioma = em.merge(idiomaListOldIdioma);
                }
            }
            for (Idioma idiomaListNewIdioma : idiomaListNew) {
                if (!idiomaListOld.contains(idiomaListNewIdioma)) {
                    TipoNivelidioma oldIdTiponivelidiomaOfIdiomaListNewIdioma = idiomaListNewIdioma.getIdTiponivelidioma();
                    idiomaListNewIdioma.setIdTiponivelidioma(tipoNivelidioma);
                    idiomaListNewIdioma = em.merge(idiomaListNewIdioma);
                    if (oldIdTiponivelidiomaOfIdiomaListNewIdioma != null && !oldIdTiponivelidiomaOfIdiomaListNewIdioma.equals(tipoNivelidioma)) {
                        oldIdTiponivelidiomaOfIdiomaListNewIdioma.getIdiomaList().remove(idiomaListNewIdioma);
                        oldIdTiponivelidiomaOfIdiomaListNewIdioma = em.merge(oldIdTiponivelidiomaOfIdiomaListNewIdioma);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tipoNivelidioma.getIdTiponivelidioma();
                if (findTipoNivelidioma(id) == null) {
                    throw new NonexistentEntityException("The tipoNivelidioma with id " + id + " no longer exists.");
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
            TipoNivelidioma tipoNivelidioma;
            try {
                tipoNivelidioma = em.getReference(TipoNivelidioma.class, id);
                tipoNivelidioma.getIdTiponivelidioma();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoNivelidioma with id " + id + " no longer exists.", enfe);
            }
            List<Idioma> idiomaList = tipoNivelidioma.getIdiomaList();
            for (Idioma idiomaListIdioma : idiomaList) {
                idiomaListIdioma.setIdTiponivelidioma(null);
                idiomaListIdioma = em.merge(idiomaListIdioma);
            }
            em.remove(tipoNivelidioma);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoNivelidioma> findTipoNivelidiomaEntities() {
        return findTipoNivelidiomaEntities(true, -1, -1);
    }

    public List<TipoNivelidioma> findTipoNivelidiomaEntities(int maxResults, int firstResult) {
        return findTipoNivelidiomaEntities(false, maxResults, firstResult);
    }

    private List<TipoNivelidioma> findTipoNivelidiomaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from TipoNivelidioma as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public TipoNivelidioma findTipoNivelidioma(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoNivelidioma.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoNivelidiomaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from TipoNivelidioma as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
