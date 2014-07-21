/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.dao.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.AdeudoalumnoEsquematipoexamen;
import com.utez.integracion.entity.TipoAdeudo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class TipoAdeudoJpaController implements Serializable {

    public TipoAdeudoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoAdeudo tipoAdeudo) throws PreexistingEntityException, Exception {
        if (tipoAdeudo.getAdeudoalumnoEsquematipoexamenList() == null) {
            tipoAdeudo.setAdeudoalumnoEsquematipoexamenList(new ArrayList<AdeudoalumnoEsquematipoexamen>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<AdeudoalumnoEsquematipoexamen> attachedAdeudoalumnoEsquematipoexamenList = new ArrayList<AdeudoalumnoEsquematipoexamen>();
            for (AdeudoalumnoEsquematipoexamen adeudoalumnoEsquematipoexamenListAdeudoalumnoEsquematipoexamenToAttach : tipoAdeudo.getAdeudoalumnoEsquematipoexamenList()) {
                adeudoalumnoEsquematipoexamenListAdeudoalumnoEsquematipoexamenToAttach = em.getReference(adeudoalumnoEsquematipoexamenListAdeudoalumnoEsquematipoexamenToAttach.getClass(), adeudoalumnoEsquematipoexamenListAdeudoalumnoEsquematipoexamenToAttach.getAdeudoalumnoEsquematipoexamenPK());
                attachedAdeudoalumnoEsquematipoexamenList.add(adeudoalumnoEsquematipoexamenListAdeudoalumnoEsquematipoexamenToAttach);
            }
            tipoAdeudo.setAdeudoalumnoEsquematipoexamenList(attachedAdeudoalumnoEsquematipoexamenList);
            em.persist(tipoAdeudo);
            for (AdeudoalumnoEsquematipoexamen adeudoalumnoEsquematipoexamenListAdeudoalumnoEsquematipoexamen : tipoAdeudo.getAdeudoalumnoEsquematipoexamenList()) {
                TipoAdeudo oldTipoAdeudoOfAdeudoalumnoEsquematipoexamenListAdeudoalumnoEsquematipoexamen = adeudoalumnoEsquematipoexamenListAdeudoalumnoEsquematipoexamen.getTipoAdeudo();
                adeudoalumnoEsquematipoexamenListAdeudoalumnoEsquematipoexamen.setTipoAdeudo(tipoAdeudo);
                adeudoalumnoEsquematipoexamenListAdeudoalumnoEsquematipoexamen = em.merge(adeudoalumnoEsquematipoexamenListAdeudoalumnoEsquematipoexamen);
                if (oldTipoAdeudoOfAdeudoalumnoEsquematipoexamenListAdeudoalumnoEsquematipoexamen != null) {
                    oldTipoAdeudoOfAdeudoalumnoEsquematipoexamenListAdeudoalumnoEsquematipoexamen.getAdeudoalumnoEsquematipoexamenList().remove(adeudoalumnoEsquematipoexamenListAdeudoalumnoEsquematipoexamen);
                    oldTipoAdeudoOfAdeudoalumnoEsquematipoexamenListAdeudoalumnoEsquematipoexamen = em.merge(oldTipoAdeudoOfAdeudoalumnoEsquematipoexamenListAdeudoalumnoEsquematipoexamen);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTipoAdeudo(tipoAdeudo.getIdAdeudo()) != null) {
                throw new PreexistingEntityException("TipoAdeudo " + tipoAdeudo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoAdeudo tipoAdeudo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoAdeudo persistentTipoAdeudo = em.find(TipoAdeudo.class, tipoAdeudo.getIdAdeudo());
            List<AdeudoalumnoEsquematipoexamen> adeudoalumnoEsquematipoexamenListOld = persistentTipoAdeudo.getAdeudoalumnoEsquematipoexamenList();
            List<AdeudoalumnoEsquematipoexamen> adeudoalumnoEsquematipoexamenListNew = tipoAdeudo.getAdeudoalumnoEsquematipoexamenList();
            List<AdeudoalumnoEsquematipoexamen> attachedAdeudoalumnoEsquematipoexamenListNew = new ArrayList<AdeudoalumnoEsquematipoexamen>();
            for (AdeudoalumnoEsquematipoexamen adeudoalumnoEsquematipoexamenListNewAdeudoalumnoEsquematipoexamenToAttach : adeudoalumnoEsquematipoexamenListNew) {
                adeudoalumnoEsquematipoexamenListNewAdeudoalumnoEsquematipoexamenToAttach = em.getReference(adeudoalumnoEsquematipoexamenListNewAdeudoalumnoEsquematipoexamenToAttach.getClass(), adeudoalumnoEsquematipoexamenListNewAdeudoalumnoEsquematipoexamenToAttach.getAdeudoalumnoEsquematipoexamenPK());
                attachedAdeudoalumnoEsquematipoexamenListNew.add(adeudoalumnoEsquematipoexamenListNewAdeudoalumnoEsquematipoexamenToAttach);
            }
            adeudoalumnoEsquematipoexamenListNew = attachedAdeudoalumnoEsquematipoexamenListNew;
            tipoAdeudo.setAdeudoalumnoEsquematipoexamenList(adeudoalumnoEsquematipoexamenListNew);
            tipoAdeudo = em.merge(tipoAdeudo);
            for (AdeudoalumnoEsquematipoexamen adeudoalumnoEsquematipoexamenListOldAdeudoalumnoEsquematipoexamen : adeudoalumnoEsquematipoexamenListOld) {
                if (!adeudoalumnoEsquematipoexamenListNew.contains(adeudoalumnoEsquematipoexamenListOldAdeudoalumnoEsquematipoexamen)) {
                    adeudoalumnoEsquematipoexamenListOldAdeudoalumnoEsquematipoexamen.setTipoAdeudo(null);
                    adeudoalumnoEsquematipoexamenListOldAdeudoalumnoEsquematipoexamen = em.merge(adeudoalumnoEsquematipoexamenListOldAdeudoalumnoEsquematipoexamen);
                }
            }
            for (AdeudoalumnoEsquematipoexamen adeudoalumnoEsquematipoexamenListNewAdeudoalumnoEsquematipoexamen : adeudoalumnoEsquematipoexamenListNew) {
                if (!adeudoalumnoEsquematipoexamenListOld.contains(adeudoalumnoEsquematipoexamenListNewAdeudoalumnoEsquematipoexamen)) {
                    TipoAdeudo oldTipoAdeudoOfAdeudoalumnoEsquematipoexamenListNewAdeudoalumnoEsquematipoexamen = adeudoalumnoEsquematipoexamenListNewAdeudoalumnoEsquematipoexamen.getTipoAdeudo();
                    adeudoalumnoEsquematipoexamenListNewAdeudoalumnoEsquematipoexamen.setTipoAdeudo(tipoAdeudo);
                    adeudoalumnoEsquematipoexamenListNewAdeudoalumnoEsquematipoexamen = em.merge(adeudoalumnoEsquematipoexamenListNewAdeudoalumnoEsquematipoexamen);
                    if (oldTipoAdeudoOfAdeudoalumnoEsquematipoexamenListNewAdeudoalumnoEsquematipoexamen != null && !oldTipoAdeudoOfAdeudoalumnoEsquematipoexamenListNewAdeudoalumnoEsquematipoexamen.equals(tipoAdeudo)) {
                        oldTipoAdeudoOfAdeudoalumnoEsquematipoexamenListNewAdeudoalumnoEsquematipoexamen.getAdeudoalumnoEsquematipoexamenList().remove(adeudoalumnoEsquematipoexamenListNewAdeudoalumnoEsquematipoexamen);
                        oldTipoAdeudoOfAdeudoalumnoEsquematipoexamenListNewAdeudoalumnoEsquematipoexamen = em.merge(oldTipoAdeudoOfAdeudoalumnoEsquematipoexamenListNewAdeudoalumnoEsquematipoexamen);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = tipoAdeudo.getIdAdeudo();
                if (findTipoAdeudo(id) == null) {
                    throw new NonexistentEntityException("The tipoAdeudo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoAdeudo tipoAdeudo;
            try {
                tipoAdeudo = em.getReference(TipoAdeudo.class, id);
                tipoAdeudo.getIdAdeudo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoAdeudo with id " + id + " no longer exists.", enfe);
            }
            List<AdeudoalumnoEsquematipoexamen> adeudoalumnoEsquematipoexamenList = tipoAdeudo.getAdeudoalumnoEsquematipoexamenList();
            for (AdeudoalumnoEsquematipoexamen adeudoalumnoEsquematipoexamenListAdeudoalumnoEsquematipoexamen : adeudoalumnoEsquematipoexamenList) {
                adeudoalumnoEsquematipoexamenListAdeudoalumnoEsquematipoexamen.setTipoAdeudo(null);
                adeudoalumnoEsquematipoexamenListAdeudoalumnoEsquematipoexamen = em.merge(adeudoalumnoEsquematipoexamenListAdeudoalumnoEsquematipoexamen);
            }
            em.remove(tipoAdeudo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoAdeudo> findTipoAdeudoEntities() {
        return findTipoAdeudoEntities(true, -1, -1);
    }

    public List<TipoAdeudo> findTipoAdeudoEntities(int maxResults, int firstResult) {
        return findTipoAdeudoEntities(false, maxResults, firstResult);
    }

    private List<TipoAdeudo> findTipoAdeudoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from TipoAdeudo as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public TipoAdeudo findTipoAdeudo(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoAdeudo.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoAdeudoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from TipoAdeudo as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
