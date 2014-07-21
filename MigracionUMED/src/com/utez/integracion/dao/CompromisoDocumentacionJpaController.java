/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.entity.CompromisoDocumentacion;
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
public class CompromisoDocumentacionJpaController implements Serializable {

    public CompromisoDocumentacionJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CompromisoDocumentacion compromisoDocumentacion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Alumno matricula = compromisoDocumentacion.getMatricula();
            if (matricula != null) {
                matricula = em.getReference(matricula.getClass(), matricula.getMatricula());
                compromisoDocumentacion.setMatricula(matricula);
            }
            em.persist(compromisoDocumentacion);
            if (matricula != null) {
                matricula.getCompromisoDocumentacionList().add(compromisoDocumentacion);
                matricula = em.merge(matricula);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CompromisoDocumentacion compromisoDocumentacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CompromisoDocumentacion persistentCompromisoDocumentacion = em.find(CompromisoDocumentacion.class, compromisoDocumentacion.getIdCompromisodocumentacion());
            Alumno matriculaOld = persistentCompromisoDocumentacion.getMatricula();
            Alumno matriculaNew = compromisoDocumentacion.getMatricula();
            if (matriculaNew != null) {
                matriculaNew = em.getReference(matriculaNew.getClass(), matriculaNew.getMatricula());
                compromisoDocumentacion.setMatricula(matriculaNew);
            }
            compromisoDocumentacion = em.merge(compromisoDocumentacion);
            if (matriculaOld != null && !matriculaOld.equals(matriculaNew)) {
                matriculaOld.getCompromisoDocumentacionList().remove(compromisoDocumentacion);
                matriculaOld = em.merge(matriculaOld);
            }
            if (matriculaNew != null && !matriculaNew.equals(matriculaOld)) {
                matriculaNew.getCompromisoDocumentacionList().add(compromisoDocumentacion);
                matriculaNew = em.merge(matriculaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = compromisoDocumentacion.getIdCompromisodocumentacion();
                if (findCompromisoDocumentacion(id) == null) {
                    throw new NonexistentEntityException("The compromisoDocumentacion with id " + id + " no longer exists.");
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
            CompromisoDocumentacion compromisoDocumentacion;
            try {
                compromisoDocumentacion = em.getReference(CompromisoDocumentacion.class, id);
                compromisoDocumentacion.getIdCompromisodocumentacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The compromisoDocumentacion with id " + id + " no longer exists.", enfe);
            }
            Alumno matricula = compromisoDocumentacion.getMatricula();
            if (matricula != null) {
                matricula.getCompromisoDocumentacionList().remove(compromisoDocumentacion);
                matricula = em.merge(matricula);
            }
            em.remove(compromisoDocumentacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CompromisoDocumentacion> findCompromisoDocumentacionEntities() {
        return findCompromisoDocumentacionEntities(true, -1, -1);
    }

    public List<CompromisoDocumentacion> findCompromisoDocumentacionEntities(int maxResults, int firstResult) {
        return findCompromisoDocumentacionEntities(false, maxResults, firstResult);
    }

    private List<CompromisoDocumentacion> findCompromisoDocumentacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from CompromisoDocumentacion as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public CompromisoDocumentacion findCompromisoDocumentacion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CompromisoDocumentacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getCompromisoDocumentacionCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from CompromisoDocumentacion as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
