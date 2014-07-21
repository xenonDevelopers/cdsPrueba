/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.IllegalOrphanException;
import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.dao.exceptions.PreexistingEntityException;
import com.utez.integracion.entity.Equivalencia;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.Programacion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class EquivalenciaJpaController implements Serializable {

    public EquivalenciaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Equivalencia equivalencia) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        Programacion programacionOrphanCheck = equivalencia.getProgramacion();
        if (programacionOrphanCheck != null) {
            Equivalencia oldEquivalenciaOfProgramacion = programacionOrphanCheck.getEquivalencia();
            if (oldEquivalenciaOfProgramacion != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Programacion " + programacionOrphanCheck + " already has an item of type Equivalencia whose programacion column cannot be null. Please make another selection for the programacion field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Programacion programacion = equivalencia.getProgramacion();
            if (programacion != null) {
                programacion = em.getReference(programacion.getClass(), programacion.getIdProgramacion());
                equivalencia.setProgramacion(programacion);
            }
            em.persist(equivalencia);
            if (programacion != null) {
                programacion.setEquivalencia(equivalencia);
                programacion = em.merge(programacion);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEquivalencia(equivalencia.getIdProgramacion()) != null) {
                throw new PreexistingEntityException("Equivalencia " + equivalencia + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Equivalencia equivalencia) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Equivalencia persistentEquivalencia = em.find(Equivalencia.class, equivalencia.getIdProgramacion());
            Programacion programacionOld = persistentEquivalencia.getProgramacion();
            Programacion programacionNew = equivalencia.getProgramacion();
            List<String> illegalOrphanMessages = null;
            if (programacionNew != null && !programacionNew.equals(programacionOld)) {
                Equivalencia oldEquivalenciaOfProgramacion = programacionNew.getEquivalencia();
                if (oldEquivalenciaOfProgramacion != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Programacion " + programacionNew + " already has an item of type Equivalencia whose programacion column cannot be null. Please make another selection for the programacion field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (programacionNew != null) {
                programacionNew = em.getReference(programacionNew.getClass(), programacionNew.getIdProgramacion());
                equivalencia.setProgramacion(programacionNew);
            }
            equivalencia = em.merge(equivalencia);
            if (programacionOld != null && !programacionOld.equals(programacionNew)) {
                programacionOld.setEquivalencia(null);
                programacionOld = em.merge(programacionOld);
            }
            if (programacionNew != null && !programacionNew.equals(programacionOld)) {
                programacionNew.setEquivalencia(equivalencia);
                programacionNew = em.merge(programacionNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = equivalencia.getIdProgramacion();
                if (findEquivalencia(id) == null) {
                    throw new NonexistentEntityException("The equivalencia with id " + id + " no longer exists.");
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
            Equivalencia equivalencia;
            try {
                equivalencia = em.getReference(Equivalencia.class, id);
                equivalencia.getIdProgramacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The equivalencia with id " + id + " no longer exists.", enfe);
            }
            Programacion programacion = equivalencia.getProgramacion();
            if (programacion != null) {
                programacion.setEquivalencia(null);
                programacion = em.merge(programacion);
            }
            em.remove(equivalencia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Equivalencia> findEquivalenciaEntities() {
        return findEquivalenciaEntities(true, -1, -1);
    }

    public List<Equivalencia> findEquivalenciaEntities(int maxResults, int firstResult) {
        return findEquivalenciaEntities(false, maxResults, firstResult);
    }

    private List<Equivalencia> findEquivalenciaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Equivalencia as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Equivalencia findEquivalencia(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Equivalencia.class, id);
        } finally {
            em.close();
        }
    }

    public int getEquivalenciaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Equivalencia as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
