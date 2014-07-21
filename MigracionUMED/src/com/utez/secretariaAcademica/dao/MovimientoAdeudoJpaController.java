/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.secretariaAcademica.dao;

import com.utez.integracion.entity.MovimientoAdeudo;
import com.utez.secretariaAcademica.dao.exceptions.IllegalOrphanException;
import com.utez.secretariaAcademica.dao.exceptions.NonexistentEntityException;
import com.utez.secretariaAcademica.dao.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.secretariaAcademica.entity.Alumno;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class MovimientoAdeudoJpaController implements Serializable {

    public MovimientoAdeudoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MovimientoAdeudo movimientoAdeudo) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        Alumno alumnoOrphanCheck = movimientoAdeudo.getAlumno();
        if (alumnoOrphanCheck != null) {
            MovimientoAdeudo oldMovimientoAdeudoOfAlumno = alumnoOrphanCheck.getMovimientoAdeudo();
            if (oldMovimientoAdeudoOfAlumno != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Alumno " + alumnoOrphanCheck + " already has an item of type MovimientoAdeudo whose alumno column cannot be null. Please make another selection for the alumno field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Alumno alumno = movimientoAdeudo.getAlumno();
            if (alumno != null) {
                alumno = em.getReference(alumno.getClass(), alumno.getMatricula());
                movimientoAdeudo.setAlumno(alumno);
            }
            em.persist(movimientoAdeudo);
            if (alumno != null) {
                alumno.setMovimientoAdeudo(movimientoAdeudo);
                alumno = em.merge(alumno);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMovimientoAdeudo(movimientoAdeudo.getMatricula()) != null) {
                throw new PreexistingEntityException("MovimientoAdeudo " + movimientoAdeudo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MovimientoAdeudo movimientoAdeudo) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MovimientoAdeudo persistentMovimientoAdeudo = em.find(MovimientoAdeudo.class, movimientoAdeudo.getMatricula());
            Alumno alumnoOld = persistentMovimientoAdeudo.getAlumno();
            Alumno alumnoNew = movimientoAdeudo.getAlumno();
            List<String> illegalOrphanMessages = null;
            if (alumnoNew != null && !alumnoNew.equals(alumnoOld)) {
                MovimientoAdeudo oldMovimientoAdeudoOfAlumno = alumnoNew.getMovimientoAdeudo();
                if (oldMovimientoAdeudoOfAlumno != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Alumno " + alumnoNew + " already has an item of type MovimientoAdeudo whose alumno column cannot be null. Please make another selection for the alumno field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (alumnoNew != null) {
                alumnoNew = em.getReference(alumnoNew.getClass(), alumnoNew.getMatricula());
                movimientoAdeudo.setAlumno(alumnoNew);
            }
            movimientoAdeudo = em.merge(movimientoAdeudo);
            if (alumnoOld != null && !alumnoOld.equals(alumnoNew)) {
                alumnoOld.setMovimientoAdeudo(null);
                alumnoOld = em.merge(alumnoOld);
            }
            if (alumnoNew != null && !alumnoNew.equals(alumnoOld)) {
                alumnoNew.setMovimientoAdeudo(movimientoAdeudo);
                alumnoNew = em.merge(alumnoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = movimientoAdeudo.getMatricula();
                if (findMovimientoAdeudo(id) == null) {
                    throw new NonexistentEntityException("The movimientoAdeudo with id " + id + " no longer exists.");
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
            MovimientoAdeudo movimientoAdeudo;
            try {
                movimientoAdeudo = em.getReference(MovimientoAdeudo.class, id);
                movimientoAdeudo.getMatricula();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The movimientoAdeudo with id " + id + " no longer exists.", enfe);
            }
            Alumno alumno = movimientoAdeudo.getAlumno();
            if (alumno != null) {
                alumno.setMovimientoAdeudo(null);
                alumno = em.merge(alumno);
            }
            em.remove(movimientoAdeudo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<MovimientoAdeudo> findMovimientoAdeudoEntities() {
        return findMovimientoAdeudoEntities(true, -1, -1);
    }

    public List<MovimientoAdeudo> findMovimientoAdeudoEntities(int maxResults, int firstResult) {
        return findMovimientoAdeudoEntities(false, maxResults, firstResult);
    }

    private List<MovimientoAdeudo> findMovimientoAdeudoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from MovimientoAdeudo as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public MovimientoAdeudo findMovimientoAdeudo(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MovimientoAdeudo.class, id);
        } finally {
            em.close();
        }
    }

    public int getMovimientoAdeudoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from MovimientoAdeudo as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
