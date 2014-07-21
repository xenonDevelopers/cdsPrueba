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
import com.utez.integracion.entity.FechaExamenprogramado;
import com.utez.integracion.entity.VigenciaCalificacion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class VigenciaCalificacionJpaController implements Serializable {

    public VigenciaCalificacionJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(VigenciaCalificacion vigenciaCalificacion) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        FechaExamenprogramado fechaExamenprogramadoOrphanCheck = vigenciaCalificacion.getFechaExamenprogramado();
        if (fechaExamenprogramadoOrphanCheck != null) {
            VigenciaCalificacion oldVigenciaCalificacionOfFechaExamenprogramado = fechaExamenprogramadoOrphanCheck.getVigenciaCalificacion();
            if (oldVigenciaCalificacionOfFechaExamenprogramado != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The FechaExamenprogramado " + fechaExamenprogramadoOrphanCheck + " already has an item of type VigenciaCalificacion whose fechaExamenprogramado column cannot be null. Please make another selection for the fechaExamenprogramado field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FechaExamenprogramado fechaExamenprogramado = vigenciaCalificacion.getFechaExamenprogramado();
            if (fechaExamenprogramado != null) {
                fechaExamenprogramado = em.getReference(fechaExamenprogramado.getClass(), fechaExamenprogramado.getIdFechaExamen());
                vigenciaCalificacion.setFechaExamenprogramado(fechaExamenprogramado);
            }
            em.persist(vigenciaCalificacion);
            if (fechaExamenprogramado != null) {
                fechaExamenprogramado.setVigenciaCalificacion(vigenciaCalificacion);
                fechaExamenprogramado = em.merge(fechaExamenprogramado);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findVigenciaCalificacion(vigenciaCalificacion.getIdExamen()) != null) {
                throw new PreexistingEntityException("VigenciaCalificacion " + vigenciaCalificacion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(VigenciaCalificacion vigenciaCalificacion) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            VigenciaCalificacion persistentVigenciaCalificacion = em.find(VigenciaCalificacion.class, vigenciaCalificacion.getIdExamen());
            FechaExamenprogramado fechaExamenprogramadoOld = persistentVigenciaCalificacion.getFechaExamenprogramado();
            FechaExamenprogramado fechaExamenprogramadoNew = vigenciaCalificacion.getFechaExamenprogramado();
            List<String> illegalOrphanMessages = null;
            if (fechaExamenprogramadoNew != null && !fechaExamenprogramadoNew.equals(fechaExamenprogramadoOld)) {
                VigenciaCalificacion oldVigenciaCalificacionOfFechaExamenprogramado = fechaExamenprogramadoNew.getVigenciaCalificacion();
                if (oldVigenciaCalificacionOfFechaExamenprogramado != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The FechaExamenprogramado " + fechaExamenprogramadoNew + " already has an item of type VigenciaCalificacion whose fechaExamenprogramado column cannot be null. Please make another selection for the fechaExamenprogramado field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (fechaExamenprogramadoNew != null) {
                fechaExamenprogramadoNew = em.getReference(fechaExamenprogramadoNew.getClass(), fechaExamenprogramadoNew.getIdFechaExamen());
                vigenciaCalificacion.setFechaExamenprogramado(fechaExamenprogramadoNew);
            }
            vigenciaCalificacion = em.merge(vigenciaCalificacion);
            if (fechaExamenprogramadoOld != null && !fechaExamenprogramadoOld.equals(fechaExamenprogramadoNew)) {
                fechaExamenprogramadoOld.setVigenciaCalificacion(null);
                fechaExamenprogramadoOld = em.merge(fechaExamenprogramadoOld);
            }
            if (fechaExamenprogramadoNew != null && !fechaExamenprogramadoNew.equals(fechaExamenprogramadoOld)) {
                fechaExamenprogramadoNew.setVigenciaCalificacion(vigenciaCalificacion);
                fechaExamenprogramadoNew = em.merge(fechaExamenprogramadoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = vigenciaCalificacion.getIdExamen();
                if (findVigenciaCalificacion(id) == null) {
                    throw new NonexistentEntityException("The vigenciaCalificacion with id " + id + " no longer exists.");
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
            VigenciaCalificacion vigenciaCalificacion;
            try {
                vigenciaCalificacion = em.getReference(VigenciaCalificacion.class, id);
                vigenciaCalificacion.getIdExamen();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The vigenciaCalificacion with id " + id + " no longer exists.", enfe);
            }
            FechaExamenprogramado fechaExamenprogramado = vigenciaCalificacion.getFechaExamenprogramado();
            if (fechaExamenprogramado != null) {
                fechaExamenprogramado.setVigenciaCalificacion(null);
                fechaExamenprogramado = em.merge(fechaExamenprogramado);
            }
            em.remove(vigenciaCalificacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<VigenciaCalificacion> findVigenciaCalificacionEntities() {
        return findVigenciaCalificacionEntities(true, -1, -1);
    }

    public List<VigenciaCalificacion> findVigenciaCalificacionEntities(int maxResults, int firstResult) {
        return findVigenciaCalificacionEntities(false, maxResults, firstResult);
    }

    private List<VigenciaCalificacion> findVigenciaCalificacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from VigenciaCalificacion as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public VigenciaCalificacion findVigenciaCalificacion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(VigenciaCalificacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getVigenciaCalificacionCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from VigenciaCalificacion as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
