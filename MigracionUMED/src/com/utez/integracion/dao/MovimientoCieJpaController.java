/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.entity.MovimientoCie;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.secretariaAcademica.entity.Alumno;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class MovimientoCieJpaController implements Serializable {

    public MovimientoCieJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MovimientoCie movimientoCie) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Alumno matricula = movimientoCie.getMatricula();
            if (matricula != null) {
                matricula = em.getReference(matricula.getClass(), matricula.getMatricula());
                movimientoCie.setMatricula(matricula);
            }
            em.persist(movimientoCie);
            if (matricula != null) {
                matricula.getMovimientoCieList().add(movimientoCie);
                matricula = em.merge(matricula);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MovimientoCie movimientoCie) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MovimientoCie persistentMovimientoCie = em.find(MovimientoCie.class, movimientoCie.getIdMovimientocie());
            Alumno matriculaOld = persistentMovimientoCie.getMatricula();
            Alumno matriculaNew = movimientoCie.getMatricula();
            if (matriculaNew != null) {
                matriculaNew = em.getReference(matriculaNew.getClass(), matriculaNew.getMatricula());
                movimientoCie.setMatricula(matriculaNew);
            }
            movimientoCie = em.merge(movimientoCie);
            if (matriculaOld != null && !matriculaOld.equals(matriculaNew)) {
                matriculaOld.getMovimientoCieList().remove(movimientoCie);
                matriculaOld = em.merge(matriculaOld);
            }
            if (matriculaNew != null && !matriculaNew.equals(matriculaOld)) {
                matriculaNew.getMovimientoCieList().add(movimientoCie);
                matriculaNew = em.merge(matriculaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = movimientoCie.getIdMovimientocie();
                if (findMovimientoCie(id) == null) {
                    throw new NonexistentEntityException("The movimientoCie with id " + id + " no longer exists.");
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
            MovimientoCie movimientoCie;
            try {
                movimientoCie = em.getReference(MovimientoCie.class, id);
                movimientoCie.getIdMovimientocie();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The movimientoCie with id " + id + " no longer exists.", enfe);
            }
            Alumno matricula = movimientoCie.getMatricula();
            if (matricula != null) {
                matricula.getMovimientoCieList().remove(movimientoCie);
                matricula = em.merge(matricula);
            }
            em.remove(movimientoCie);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<MovimientoCie> findMovimientoCieEntities() {
        return findMovimientoCieEntities(true, -1, -1);
    }

    public List<MovimientoCie> findMovimientoCieEntities(int maxResults, int firstResult) {
        return findMovimientoCieEntities(false, maxResults, firstResult);
    }

    private List<MovimientoCie> findMovimientoCieEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from MovimientoCie as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public MovimientoCie findMovimientoCie(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MovimientoCie.class, id);
        } finally {
            em.close();
        }
    }

    public int getMovimientoCieCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from MovimientoCie as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
