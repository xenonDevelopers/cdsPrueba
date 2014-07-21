/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.IllegalOrphanException;
import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.dao.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.ExamenIndividual;
import com.utez.integracion.entity.VigenciaCalificacionindividual;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class VigenciaCalificacionindividualJpaController implements Serializable {

    public VigenciaCalificacionindividualJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(VigenciaCalificacionindividual vigenciaCalificacionindividual) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        ExamenIndividual examenIndividualOrphanCheck = vigenciaCalificacionindividual.getExamenIndividual();
        if (examenIndividualOrphanCheck != null) {
            VigenciaCalificacionindividual oldVigenciaCalificacionindividualOfExamenIndividual = examenIndividualOrphanCheck.getVigenciaCalificacionindividual();
            if (oldVigenciaCalificacionindividualOfExamenIndividual != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The ExamenIndividual " + examenIndividualOrphanCheck + " already has an item of type VigenciaCalificacionindividual whose examenIndividual column cannot be null. Please make another selection for the examenIndividual field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ExamenIndividual examenIndividual = vigenciaCalificacionindividual.getExamenIndividual();
            if (examenIndividual != null) {
                examenIndividual = em.getReference(examenIndividual.getClass(), examenIndividual.getIdExamenindividual());
                vigenciaCalificacionindividual.setExamenIndividual(examenIndividual);
            }
            em.persist(vigenciaCalificacionindividual);
            if (examenIndividual != null) {
                examenIndividual.setVigenciaCalificacionindividual(vigenciaCalificacionindividual);
                examenIndividual = em.merge(examenIndividual);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findVigenciaCalificacionindividual(vigenciaCalificacionindividual.getIdExamenindividual()) != null) {
                throw new PreexistingEntityException("VigenciaCalificacionindividual " + vigenciaCalificacionindividual + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(VigenciaCalificacionindividual vigenciaCalificacionindividual) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            VigenciaCalificacionindividual persistentVigenciaCalificacionindividual = em.find(VigenciaCalificacionindividual.class, vigenciaCalificacionindividual.getIdExamenindividual());
            ExamenIndividual examenIndividualOld = persistentVigenciaCalificacionindividual.getExamenIndividual();
            ExamenIndividual examenIndividualNew = vigenciaCalificacionindividual.getExamenIndividual();
            List<String> illegalOrphanMessages = null;
            if (examenIndividualNew != null && !examenIndividualNew.equals(examenIndividualOld)) {
                VigenciaCalificacionindividual oldVigenciaCalificacionindividualOfExamenIndividual = examenIndividualNew.getVigenciaCalificacionindividual();
                if (oldVigenciaCalificacionindividualOfExamenIndividual != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The ExamenIndividual " + examenIndividualNew + " already has an item of type VigenciaCalificacionindividual whose examenIndividual column cannot be null. Please make another selection for the examenIndividual field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (examenIndividualNew != null) {
                examenIndividualNew = em.getReference(examenIndividualNew.getClass(), examenIndividualNew.getIdExamenindividual());
                vigenciaCalificacionindividual.setExamenIndividual(examenIndividualNew);
            }
            vigenciaCalificacionindividual = em.merge(vigenciaCalificacionindividual);
            if (examenIndividualOld != null && !examenIndividualOld.equals(examenIndividualNew)) {
                examenIndividualOld.setVigenciaCalificacionindividual(null);
                examenIndividualOld = em.merge(examenIndividualOld);
            }
            if (examenIndividualNew != null && !examenIndividualNew.equals(examenIndividualOld)) {
                examenIndividualNew.setVigenciaCalificacionindividual(vigenciaCalificacionindividual);
                examenIndividualNew = em.merge(examenIndividualNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = vigenciaCalificacionindividual.getIdExamenindividual();
                if (findVigenciaCalificacionindividual(id) == null) {
                    throw new NonexistentEntityException("The vigenciaCalificacionindividual with id " + id + " no longer exists.");
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
            VigenciaCalificacionindividual vigenciaCalificacionindividual;
            try {
                vigenciaCalificacionindividual = em.getReference(VigenciaCalificacionindividual.class, id);
                vigenciaCalificacionindividual.getIdExamenindividual();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The vigenciaCalificacionindividual with id " + id + " no longer exists.", enfe);
            }
            ExamenIndividual examenIndividual = vigenciaCalificacionindividual.getExamenIndividual();
            if (examenIndividual != null) {
                examenIndividual.setVigenciaCalificacionindividual(null);
                examenIndividual = em.merge(examenIndividual);
            }
            em.remove(vigenciaCalificacionindividual);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<VigenciaCalificacionindividual> findVigenciaCalificacionindividualEntities() {
        return findVigenciaCalificacionindividualEntities(true, -1, -1);
    }

    public List<VigenciaCalificacionindividual> findVigenciaCalificacionindividualEntities(int maxResults, int firstResult) {
        return findVigenciaCalificacionindividualEntities(false, maxResults, firstResult);
    }

    private List<VigenciaCalificacionindividual> findVigenciaCalificacionindividualEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from VigenciaCalificacionindividual as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public VigenciaCalificacionindividual findVigenciaCalificacionindividual(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(VigenciaCalificacionindividual.class, id);
        } finally {
            em.close();
        }
    }

    public int getVigenciaCalificacionindividualCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from VigenciaCalificacionindividual as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
