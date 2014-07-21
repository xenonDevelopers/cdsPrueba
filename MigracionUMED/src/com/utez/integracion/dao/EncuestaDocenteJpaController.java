/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.IllegalOrphanException;
import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.dao.exceptions.PreexistingEntityException;
import com.utez.integracion.entity.EncuestaDocente;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.FechaExamen;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class EncuestaDocenteJpaController implements Serializable {

    public EncuestaDocenteJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EncuestaDocente encuestaDocente) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        FechaExamen fechaExamenOrphanCheck = encuestaDocente.getFechaExamen();
        if (fechaExamenOrphanCheck != null) {
            EncuestaDocente oldEncuestaDocenteOfFechaExamen = fechaExamenOrphanCheck.getEncuestaDocente();
            if (oldEncuestaDocenteOfFechaExamen != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The FechaExamen " + fechaExamenOrphanCheck + " already has an item of type EncuestaDocente whose fechaExamen column cannot be null. Please make another selection for the fechaExamen field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FechaExamen fechaExamen = encuestaDocente.getFechaExamen();
            if (fechaExamen != null) {
                fechaExamen = em.getReference(fechaExamen.getClass(), fechaExamen.getIdFechaexamen());
                encuestaDocente.setFechaExamen(fechaExamen);
            }
            em.persist(encuestaDocente);
            if (fechaExamen != null) {
                fechaExamen.setEncuestaDocente(encuestaDocente);
                fechaExamen = em.merge(fechaExamen);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEncuestaDocente(encuestaDocente.getIdFechaexamen()) != null) {
                throw new PreexistingEntityException("EncuestaDocente " + encuestaDocente + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EncuestaDocente encuestaDocente) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EncuestaDocente persistentEncuestaDocente = em.find(EncuestaDocente.class, encuestaDocente.getIdFechaexamen());
            FechaExamen fechaExamenOld = persistentEncuestaDocente.getFechaExamen();
            FechaExamen fechaExamenNew = encuestaDocente.getFechaExamen();
            List<String> illegalOrphanMessages = null;
            if (fechaExamenNew != null && !fechaExamenNew.equals(fechaExamenOld)) {
                EncuestaDocente oldEncuestaDocenteOfFechaExamen = fechaExamenNew.getEncuestaDocente();
                if (oldEncuestaDocenteOfFechaExamen != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The FechaExamen " + fechaExamenNew + " already has an item of type EncuestaDocente whose fechaExamen column cannot be null. Please make another selection for the fechaExamen field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (fechaExamenNew != null) {
                fechaExamenNew = em.getReference(fechaExamenNew.getClass(), fechaExamenNew.getIdFechaexamen());
                encuestaDocente.setFechaExamen(fechaExamenNew);
            }
            encuestaDocente = em.merge(encuestaDocente);
            if (fechaExamenOld != null && !fechaExamenOld.equals(fechaExamenNew)) {
                fechaExamenOld.setEncuestaDocente(null);
                fechaExamenOld = em.merge(fechaExamenOld);
            }
            if (fechaExamenNew != null && !fechaExamenNew.equals(fechaExamenOld)) {
                fechaExamenNew.setEncuestaDocente(encuestaDocente);
                fechaExamenNew = em.merge(fechaExamenNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = encuestaDocente.getIdFechaexamen();
                if (findEncuestaDocente(id) == null) {
                    throw new NonexistentEntityException("The encuestaDocente with id " + id + " no longer exists.");
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
            EncuestaDocente encuestaDocente;
            try {
                encuestaDocente = em.getReference(EncuestaDocente.class, id);
                encuestaDocente.getIdFechaexamen();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The encuestaDocente with id " + id + " no longer exists.", enfe);
            }
            FechaExamen fechaExamen = encuestaDocente.getFechaExamen();
            if (fechaExamen != null) {
                fechaExamen.setEncuestaDocente(null);
                fechaExamen = em.merge(fechaExamen);
            }
            em.remove(encuestaDocente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EncuestaDocente> findEncuestaDocenteEntities() {
        return findEncuestaDocenteEntities(true, -1, -1);
    }

    public List<EncuestaDocente> findEncuestaDocenteEntities(int maxResults, int firstResult) {
        return findEncuestaDocenteEntities(false, maxResults, firstResult);
    }

    private List<EncuestaDocente> findEncuestaDocenteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from EncuestaDocente as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public EncuestaDocente findEncuestaDocente(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EncuestaDocente.class, id);
        } finally {
            em.close();
        }
    }

    public int getEncuestaDocenteCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from EncuestaDocente as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
