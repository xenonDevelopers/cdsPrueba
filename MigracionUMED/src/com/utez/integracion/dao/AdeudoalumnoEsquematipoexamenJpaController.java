/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.dao.exceptions.PreexistingEntityException;
import com.utez.integracion.entity.AdeudoalumnoEsquematipoexamen;
import com.utez.integracion.entity.AdeudoalumnoEsquematipoexamenPK;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.TipoAdeudo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class AdeudoalumnoEsquematipoexamenJpaController implements Serializable {

    public AdeudoalumnoEsquematipoexamenJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AdeudoalumnoEsquematipoexamen adeudoalumnoEsquematipoexamen) throws PreexistingEntityException, Exception {
        if (adeudoalumnoEsquematipoexamen.getAdeudoalumnoEsquematipoexamenPK() == null) {
            adeudoalumnoEsquematipoexamen.setAdeudoalumnoEsquematipoexamenPK(new AdeudoalumnoEsquematipoexamenPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoAdeudo tipoAdeudo = adeudoalumnoEsquematipoexamen.getTipoAdeudo();
            if (tipoAdeudo != null) {
                tipoAdeudo = em.getReference(tipoAdeudo.getClass(), tipoAdeudo.getIdAdeudo());
                adeudoalumnoEsquematipoexamen.setTipoAdeudo(tipoAdeudo);
            }
            em.persist(adeudoalumnoEsquematipoexamen);
            if (tipoAdeudo != null) {
                tipoAdeudo.getAdeudoalumnoEsquematipoexamenList().add(adeudoalumnoEsquematipoexamen);
                tipoAdeudo = em.merge(tipoAdeudo);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAdeudoalumnoEsquematipoexamen(adeudoalumnoEsquematipoexamen.getAdeudoalumnoEsquematipoexamenPK()) != null) {
                throw new PreexistingEntityException("AdeudoalumnoEsquematipoexamen " + adeudoalumnoEsquematipoexamen + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AdeudoalumnoEsquematipoexamen adeudoalumnoEsquematipoexamen) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AdeudoalumnoEsquematipoexamen persistentAdeudoalumnoEsquematipoexamen = em.find(AdeudoalumnoEsquematipoexamen.class, adeudoalumnoEsquematipoexamen.getAdeudoalumnoEsquematipoexamenPK());
            TipoAdeudo tipoAdeudoOld = persistentAdeudoalumnoEsquematipoexamen.getTipoAdeudo();
            TipoAdeudo tipoAdeudoNew = adeudoalumnoEsquematipoexamen.getTipoAdeudo();
            if (tipoAdeudoNew != null) {
                tipoAdeudoNew = em.getReference(tipoAdeudoNew.getClass(), tipoAdeudoNew.getIdAdeudo());
                adeudoalumnoEsquematipoexamen.setTipoAdeudo(tipoAdeudoNew);
            }
            adeudoalumnoEsquematipoexamen = em.merge(adeudoalumnoEsquematipoexamen);
            if (tipoAdeudoOld != null && !tipoAdeudoOld.equals(tipoAdeudoNew)) {
                tipoAdeudoOld.getAdeudoalumnoEsquematipoexamenList().remove(adeudoalumnoEsquematipoexamen);
                tipoAdeudoOld = em.merge(tipoAdeudoOld);
            }
            if (tipoAdeudoNew != null && !tipoAdeudoNew.equals(tipoAdeudoOld)) {
                tipoAdeudoNew.getAdeudoalumnoEsquematipoexamenList().add(adeudoalumnoEsquematipoexamen);
                tipoAdeudoNew = em.merge(tipoAdeudoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                AdeudoalumnoEsquematipoexamenPK id = adeudoalumnoEsquematipoexamen.getAdeudoalumnoEsquematipoexamenPK();
                if (findAdeudoalumnoEsquematipoexamen(id) == null) {
                    throw new NonexistentEntityException("The adeudoalumnoEsquematipoexamen with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(AdeudoalumnoEsquematipoexamenPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AdeudoalumnoEsquematipoexamen adeudoalumnoEsquematipoexamen;
            try {
                adeudoalumnoEsquematipoexamen = em.getReference(AdeudoalumnoEsquematipoexamen.class, id);
                adeudoalumnoEsquematipoexamen.getAdeudoalumnoEsquematipoexamenPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The adeudoalumnoEsquematipoexamen with id " + id + " no longer exists.", enfe);
            }
            TipoAdeudo tipoAdeudo = adeudoalumnoEsquematipoexamen.getTipoAdeudo();
            if (tipoAdeudo != null) {
                tipoAdeudo.getAdeudoalumnoEsquematipoexamenList().remove(adeudoalumnoEsquematipoexamen);
                tipoAdeudo = em.merge(tipoAdeudo);
            }
            em.remove(adeudoalumnoEsquematipoexamen);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AdeudoalumnoEsquematipoexamen> findAdeudoalumnoEsquematipoexamenEntities() {
        return findAdeudoalumnoEsquematipoexamenEntities(true, -1, -1);
    }

    public List<AdeudoalumnoEsquematipoexamen> findAdeudoalumnoEsquematipoexamenEntities(int maxResults, int firstResult) {
        return findAdeudoalumnoEsquematipoexamenEntities(false, maxResults, firstResult);
    }

    private List<AdeudoalumnoEsquematipoexamen> findAdeudoalumnoEsquematipoexamenEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from AdeudoalumnoEsquematipoexamen as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public AdeudoalumnoEsquematipoexamen findAdeudoalumnoEsquematipoexamen(AdeudoalumnoEsquematipoexamenPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AdeudoalumnoEsquematipoexamen.class, id);
        } finally {
            em.close();
        }
    }

    public int getAdeudoalumnoEsquematipoexamenCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from AdeudoalumnoEsquematipoexamen as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
