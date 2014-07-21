/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.secretariaAcademica.dao;

import com.utez.secretariaAcademica.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.secretariaAcademica.entity.Tipopagos;
import com.utez.secretariaAcademica.entity.Alumno;
import com.utez.secretariaAcademica.entity.Movimientocie;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class MovimientocieJpaController implements Serializable {

    public MovimientocieJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Movimientocie movimientocie) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tipopagos idtipopagos = movimientocie.getIdtipopagos();
            if (idtipopagos != null) {
                idtipopagos = em.getReference(idtipopagos.getClass(), idtipopagos.getIdtipopagos());
                movimientocie.setIdtipopagos(idtipopagos);
            }
            Alumno matricula = movimientocie.getMatricula();
            if (matricula != null) {
                matricula = em.getReference(matricula.getClass(), matricula.getMatricula());
                movimientocie.setMatricula(matricula);
            }
            em.persist(movimientocie);
            if (idtipopagos != null) {
                idtipopagos.getMovimientocieList().add(movimientocie);
                idtipopagos = em.merge(idtipopagos);
            }
            if (matricula != null) {
                matricula.getMovimientocieList().add(movimientocie);
                matricula = em.merge(matricula);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Movimientocie movimientocie) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Movimientocie persistentMovimientocie = em.find(Movimientocie.class, movimientocie.getIdmovimientocie());
            Tipopagos idtipopagosOld = persistentMovimientocie.getIdtipopagos();
            Tipopagos idtipopagosNew = movimientocie.getIdtipopagos();
            Alumno matriculaOld = persistentMovimientocie.getMatricula();
            Alumno matriculaNew = movimientocie.getMatricula();
            if (idtipopagosNew != null) {
                idtipopagosNew = em.getReference(idtipopagosNew.getClass(), idtipopagosNew.getIdtipopagos());
                movimientocie.setIdtipopagos(idtipopagosNew);
            }
            if (matriculaNew != null) {
                matriculaNew = em.getReference(matriculaNew.getClass(), matriculaNew.getMatricula());
                movimientocie.setMatricula(matriculaNew);
            }
            movimientocie = em.merge(movimientocie);
            if (idtipopagosOld != null && !idtipopagosOld.equals(idtipopagosNew)) {
                idtipopagosOld.getMovimientocieList().remove(movimientocie);
                idtipopagosOld = em.merge(idtipopagosOld);
            }
            if (idtipopagosNew != null && !idtipopagosNew.equals(idtipopagosOld)) {
                idtipopagosNew.getMovimientocieList().add(movimientocie);
                idtipopagosNew = em.merge(idtipopagosNew);
            }
            if (matriculaOld != null && !matriculaOld.equals(matriculaNew)) {
                matriculaOld.getMovimientocieList().remove(movimientocie);
                matriculaOld = em.merge(matriculaOld);
            }
            if (matriculaNew != null && !matriculaNew.equals(matriculaOld)) {
                matriculaNew.getMovimientocieList().add(movimientocie);
                matriculaNew = em.merge(matriculaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = movimientocie.getIdmovimientocie();
                if (findMovimientocie(id) == null) {
                    throw new NonexistentEntityException("The movimientocie with id " + id + " no longer exists.");
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
            Movimientocie movimientocie;
            try {
                movimientocie = em.getReference(Movimientocie.class, id);
                movimientocie.getIdmovimientocie();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The movimientocie with id " + id + " no longer exists.", enfe);
            }
            Tipopagos idtipopagos = movimientocie.getIdtipopagos();
            if (idtipopagos != null) {
                idtipopagos.getMovimientocieList().remove(movimientocie);
                idtipopagos = em.merge(idtipopagos);
            }
            Alumno matricula = movimientocie.getMatricula();
            if (matricula != null) {
                matricula.getMovimientocieList().remove(movimientocie);
                matricula = em.merge(matricula);
            }
            em.remove(movimientocie);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Movimientocie> findMovimientocieEntities() {
        return findMovimientocieEntities(true, -1, -1);
    }

    public List<Movimientocie> findMovimientocieEntities(int maxResults, int firstResult) {
        return findMovimientocieEntities(false, maxResults, firstResult);
    }

    private List<Movimientocie> findMovimientocieEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Movimientocie as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Movimientocie findMovimientocie(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Movimientocie.class, id);
        } finally {
            em.close();
        }
    }

    public int getMovimientocieCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Movimientocie as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
