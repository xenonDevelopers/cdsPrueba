/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.secretariaAcademica.dao;

import com.utez.secretariaAcademica.dao.exceptions.NonexistentEntityException;
import com.utez.secretariaAcademica.entity.Adeudo;
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
public class AdeudoJpaController implements Serializable {

    public AdeudoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Adeudo adeudo) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Alumno matricula = adeudo.getMatricula();
            if (matricula != null) {
                matricula = em.getReference(matricula.getClass(), matricula.getMatricula());
                adeudo.setMatricula(matricula);
            }
            em.persist(adeudo);
            if (matricula != null) {
                matricula.getAdeudoList().add(adeudo);
                matricula = em.merge(matricula);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Adeudo adeudo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Adeudo persistentAdeudo = em.find(Adeudo.class, adeudo.getIdadeudo());
            Alumno matriculaOld = persistentAdeudo.getMatricula();
            Alumno matriculaNew = adeudo.getMatricula();
            if (matriculaNew != null) {
                matriculaNew = em.getReference(matriculaNew.getClass(), matriculaNew.getMatricula());
                adeudo.setMatricula(matriculaNew);
            }
            adeudo = em.merge(adeudo);
            if (matriculaOld != null && !matriculaOld.equals(matriculaNew)) {
                matriculaOld.getAdeudoList().remove(adeudo);
                matriculaOld = em.merge(matriculaOld);
            }
            if (matriculaNew != null && !matriculaNew.equals(matriculaOld)) {
                matriculaNew.getAdeudoList().add(adeudo);
                matriculaNew = em.merge(matriculaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = adeudo.getIdadeudo();
                if (findAdeudo(id) == null) {
                    throw new NonexistentEntityException("The adeudo with id " + id + " no longer exists.");
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
            Adeudo adeudo;
            try {
                adeudo = em.getReference(Adeudo.class, id);
                adeudo.getIdadeudo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The adeudo with id " + id + " no longer exists.", enfe);
            }
            Alumno matricula = adeudo.getMatricula();
            if (matricula != null) {
                matricula.getAdeudoList().remove(adeudo);
                matricula = em.merge(matricula);
            }
            em.remove(adeudo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Adeudo> findAdeudoEntities() {
        return findAdeudoEntities(true, -1, -1);
    }

    public List<Adeudo> findAdeudoEntities(int maxResults, int firstResult) {
        return findAdeudoEntities(false, maxResults, firstResult);
    }

    private List<Adeudo> findAdeudoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Adeudo as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Adeudo findAdeudo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Adeudo.class, id);
        } finally {
            em.close();
        }
    }

    public int getAdeudoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Adeudo as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
