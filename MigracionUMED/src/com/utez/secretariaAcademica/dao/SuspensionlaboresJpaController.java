/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.secretariaAcademica.dao;

import com.utez.secretariaAcademica.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.secretariaAcademica.entity.Calendariorectoria;
import com.utez.secretariaAcademica.entity.Suspensionlabores;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class SuspensionlaboresJpaController implements Serializable {

    public SuspensionlaboresJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Suspensionlabores suspensionlabores) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Calendariorectoria idcalendariorectoria = suspensionlabores.getIdcalendariorectoria();
            if (idcalendariorectoria != null) {
                idcalendariorectoria = em.getReference(idcalendariorectoria.getClass(), idcalendariorectoria.getIdcalendariorectoria());
                suspensionlabores.setIdcalendariorectoria(idcalendariorectoria);
            }
            em.persist(suspensionlabores);
            if (idcalendariorectoria != null) {
                idcalendariorectoria.getSuspensionlaboresList().add(suspensionlabores);
                idcalendariorectoria = em.merge(idcalendariorectoria);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Suspensionlabores suspensionlabores) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Suspensionlabores persistentSuspensionlabores = em.find(Suspensionlabores.class, suspensionlabores.getIdsuspensionlabores());
            Calendariorectoria idcalendariorectoriaOld = persistentSuspensionlabores.getIdcalendariorectoria();
            Calendariorectoria idcalendariorectoriaNew = suspensionlabores.getIdcalendariorectoria();
            if (idcalendariorectoriaNew != null) {
                idcalendariorectoriaNew = em.getReference(idcalendariorectoriaNew.getClass(), idcalendariorectoriaNew.getIdcalendariorectoria());
                suspensionlabores.setIdcalendariorectoria(idcalendariorectoriaNew);
            }
            suspensionlabores = em.merge(suspensionlabores);
            if (idcalendariorectoriaOld != null && !idcalendariorectoriaOld.equals(idcalendariorectoriaNew)) {
                idcalendariorectoriaOld.getSuspensionlaboresList().remove(suspensionlabores);
                idcalendariorectoriaOld = em.merge(idcalendariorectoriaOld);
            }
            if (idcalendariorectoriaNew != null && !idcalendariorectoriaNew.equals(idcalendariorectoriaOld)) {
                idcalendariorectoriaNew.getSuspensionlaboresList().add(suspensionlabores);
                idcalendariorectoriaNew = em.merge(idcalendariorectoriaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = suspensionlabores.getIdsuspensionlabores();
                if (findSuspensionlabores(id) == null) {
                    throw new NonexistentEntityException("The suspensionlabores with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Suspensionlabores suspensionlabores;
            try {
                suspensionlabores = em.getReference(Suspensionlabores.class, id);
                suspensionlabores.getIdsuspensionlabores();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The suspensionlabores with id " + id + " no longer exists.", enfe);
            }
            Calendariorectoria idcalendariorectoria = suspensionlabores.getIdcalendariorectoria();
            if (idcalendariorectoria != null) {
                idcalendariorectoria.getSuspensionlaboresList().remove(suspensionlabores);
                idcalendariorectoria = em.merge(idcalendariorectoria);
            }
            em.remove(suspensionlabores);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Suspensionlabores> findSuspensionlaboresEntities() {
        return findSuspensionlaboresEntities(true, -1, -1);
    }

    public List<Suspensionlabores> findSuspensionlaboresEntities(int maxResults, int firstResult) {
        return findSuspensionlaboresEntities(false, maxResults, firstResult);
    }

    private List<Suspensionlabores> findSuspensionlaboresEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Suspensionlabores as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Suspensionlabores findSuspensionlabores(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Suspensionlabores.class, id);
        } finally {
            em.close();
        }
    }

    public int getSuspensionlaboresCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Suspensionlabores as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
