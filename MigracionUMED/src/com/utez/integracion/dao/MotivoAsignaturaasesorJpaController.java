/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.IllegalOrphanException;
import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.AsignaturaAsesor;
import com.utez.integracion.entity.MotivoAsignaturaasesor;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class MotivoAsignaturaasesorJpaController implements Serializable {

    public MotivoAsignaturaasesorJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MotivoAsignaturaasesor motivoAsignaturaasesor) throws IllegalOrphanException {
        List<String> illegalOrphanMessages = null;
        AsignaturaAsesor asignaturaAsesorOrphanCheck = motivoAsignaturaasesor.getAsignaturaAsesor();
        if (asignaturaAsesorOrphanCheck != null) {
            MotivoAsignaturaasesor oldMotivoAsignaturaasesorOfAsignaturaAsesor = asignaturaAsesorOrphanCheck.getMotivoAsignaturaasesor();
            if (oldMotivoAsignaturaasesorOfAsignaturaAsesor != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The AsignaturaAsesor " + asignaturaAsesorOrphanCheck + " already has an item of type MotivoAsignaturaasesor whose asignaturaAsesor column cannot be null. Please make another selection for the asignaturaAsesor field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AsignaturaAsesor asignaturaAsesor = motivoAsignaturaasesor.getAsignaturaAsesor();
            if (asignaturaAsesor != null) {
                asignaturaAsesor = em.getReference(asignaturaAsesor.getClass(), asignaturaAsesor.getIdAsignaturaasesor());
                motivoAsignaturaasesor.setAsignaturaAsesor(asignaturaAsesor);
            }
            em.persist(motivoAsignaturaasesor);
            if (asignaturaAsesor != null) {
                asignaturaAsesor.setMotivoAsignaturaasesor(motivoAsignaturaasesor);
                asignaturaAsesor = em.merge(asignaturaAsesor);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MotivoAsignaturaasesor motivoAsignaturaasesor) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MotivoAsignaturaasesor persistentMotivoAsignaturaasesor = em.find(MotivoAsignaturaasesor.class, motivoAsignaturaasesor.getIdAsignaturaasesor());
            AsignaturaAsesor asignaturaAsesorOld = persistentMotivoAsignaturaasesor.getAsignaturaAsesor();
            AsignaturaAsesor asignaturaAsesorNew = motivoAsignaturaasesor.getAsignaturaAsesor();
            List<String> illegalOrphanMessages = null;
            if (asignaturaAsesorNew != null && !asignaturaAsesorNew.equals(asignaturaAsesorOld)) {
                MotivoAsignaturaasesor oldMotivoAsignaturaasesorOfAsignaturaAsesor = asignaturaAsesorNew.getMotivoAsignaturaasesor();
                if (oldMotivoAsignaturaasesorOfAsignaturaAsesor != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The AsignaturaAsesor " + asignaturaAsesorNew + " already has an item of type MotivoAsignaturaasesor whose asignaturaAsesor column cannot be null. Please make another selection for the asignaturaAsesor field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (asignaturaAsesorNew != null) {
                asignaturaAsesorNew = em.getReference(asignaturaAsesorNew.getClass(), asignaturaAsesorNew.getIdAsignaturaasesor());
                motivoAsignaturaasesor.setAsignaturaAsesor(asignaturaAsesorNew);
            }
            motivoAsignaturaasesor = em.merge(motivoAsignaturaasesor);
            if (asignaturaAsesorOld != null && !asignaturaAsesorOld.equals(asignaturaAsesorNew)) {
                asignaturaAsesorOld.setMotivoAsignaturaasesor(null);
                asignaturaAsesorOld = em.merge(asignaturaAsesorOld);
            }
            if (asignaturaAsesorNew != null && !asignaturaAsesorNew.equals(asignaturaAsesorOld)) {
                asignaturaAsesorNew.setMotivoAsignaturaasesor(motivoAsignaturaasesor);
                asignaturaAsesorNew = em.merge(asignaturaAsesorNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = motivoAsignaturaasesor.getIdAsignaturaasesor();
                if (findMotivoAsignaturaasesor(id) == null) {
                    throw new NonexistentEntityException("The motivoAsignaturaasesor with id " + id + " no longer exists.");
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
            MotivoAsignaturaasesor motivoAsignaturaasesor;
            try {
                motivoAsignaturaasesor = em.getReference(MotivoAsignaturaasesor.class, id);
                motivoAsignaturaasesor.getIdAsignaturaasesor();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The motivoAsignaturaasesor with id " + id + " no longer exists.", enfe);
            }
            AsignaturaAsesor asignaturaAsesor = motivoAsignaturaasesor.getAsignaturaAsesor();
            if (asignaturaAsesor != null) {
                asignaturaAsesor.setMotivoAsignaturaasesor(null);
                asignaturaAsesor = em.merge(asignaturaAsesor);
            }
            em.remove(motivoAsignaturaasesor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<MotivoAsignaturaasesor> findMotivoAsignaturaasesorEntities() {
        return findMotivoAsignaturaasesorEntities(true, -1, -1);
    }

    public List<MotivoAsignaturaasesor> findMotivoAsignaturaasesorEntities(int maxResults, int firstResult) {
        return findMotivoAsignaturaasesorEntities(false, maxResults, firstResult);
    }

    private List<MotivoAsignaturaasesor> findMotivoAsignaturaasesorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from MotivoAsignaturaasesor as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public MotivoAsignaturaasesor findMotivoAsignaturaasesor(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MotivoAsignaturaasesor.class, id);
        } finally {
            em.close();
        }
    }

    public int getMotivoAsignaturaasesorCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from MotivoAsignaturaasesor as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
