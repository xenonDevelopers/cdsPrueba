/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.entity.BloqueAsignatura;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.secretariaAcademica.entity.Asignatura;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class BloqueAsignaturaJpaController implements Serializable {

    public BloqueAsignaturaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(BloqueAsignatura bloqueAsignatura) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Asignatura idAsignatura = bloqueAsignatura.getIdAsignatura();
            if (idAsignatura != null) {
                idAsignatura = em.getReference(idAsignatura.getClass(), idAsignatura.getIdasignatura());
                bloqueAsignatura.setIdAsignatura(idAsignatura);
            }
            em.persist(bloqueAsignatura);
            if (idAsignatura != null) {
                idAsignatura.getBloqueAsignaturaList().add(bloqueAsignatura);
                idAsignatura = em.merge(idAsignatura);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(BloqueAsignatura bloqueAsignatura) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            BloqueAsignatura persistentBloqueAsignatura = em.find(BloqueAsignatura.class, bloqueAsignatura.getIdBloqueasignatura());
            Asignatura idAsignaturaOld = persistentBloqueAsignatura.getIdAsignatura();
            Asignatura idAsignaturaNew = bloqueAsignatura.getIdAsignatura();
            if (idAsignaturaNew != null) {
                idAsignaturaNew = em.getReference(idAsignaturaNew.getClass(), idAsignaturaNew.getIdasignatura());
                bloqueAsignatura.setIdAsignatura(idAsignaturaNew);
            }
            bloqueAsignatura = em.merge(bloqueAsignatura);
            if (idAsignaturaOld != null && !idAsignaturaOld.equals(idAsignaturaNew)) {
                idAsignaturaOld.getBloqueAsignaturaList().remove(bloqueAsignatura);
                idAsignaturaOld = em.merge(idAsignaturaOld);
            }
            if (idAsignaturaNew != null && !idAsignaturaNew.equals(idAsignaturaOld)) {
                idAsignaturaNew.getBloqueAsignaturaList().add(bloqueAsignatura);
                idAsignaturaNew = em.merge(idAsignaturaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = bloqueAsignatura.getIdBloqueasignatura();
                if (findBloqueAsignatura(id) == null) {
                    throw new NonexistentEntityException("The bloqueAsignatura with id " + id + " no longer exists.");
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
            BloqueAsignatura bloqueAsignatura;
            try {
                bloqueAsignatura = em.getReference(BloqueAsignatura.class, id);
                bloqueAsignatura.getIdBloqueasignatura();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The bloqueAsignatura with id " + id + " no longer exists.", enfe);
            }
            Asignatura idAsignatura = bloqueAsignatura.getIdAsignatura();
            if (idAsignatura != null) {
                idAsignatura.getBloqueAsignaturaList().remove(bloqueAsignatura);
                idAsignatura = em.merge(idAsignatura);
            }
            em.remove(bloqueAsignatura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<BloqueAsignatura> findBloqueAsignaturaEntities() {
        return findBloqueAsignaturaEntities(true, -1, -1);
    }

    public List<BloqueAsignatura> findBloqueAsignaturaEntities(int maxResults, int firstResult) {
        return findBloqueAsignaturaEntities(false, maxResults, firstResult);
    }

    private List<BloqueAsignatura> findBloqueAsignaturaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from BloqueAsignatura as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public BloqueAsignatura findBloqueAsignatura(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(BloqueAsignatura.class, id);
        } finally {
            em.close();
        }
    }

    public int getBloqueAsignaturaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from BloqueAsignatura as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
