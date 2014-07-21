/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.IllegalOrphanException;
import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.entity.ExamenTitulacion;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.TramiteTitulacion;
import com.utez.integracion.entity.MaterialTitulacion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class ExamenTitulacionJpaController implements Serializable {

    public ExamenTitulacionJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ExamenTitulacion examenTitulacion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TramiteTitulacion idTramitetitulacion = examenTitulacion.getIdTramitetitulacion();
            if (idTramitetitulacion != null) {
                idTramitetitulacion = em.getReference(idTramitetitulacion.getClass(), idTramitetitulacion.getIdTramitetitulacion());
                examenTitulacion.setIdTramitetitulacion(idTramitetitulacion);
            }
            MaterialTitulacion materialTitulacion = examenTitulacion.getMaterialTitulacion();
            if (materialTitulacion != null) {
                materialTitulacion = em.getReference(materialTitulacion.getClass(), materialTitulacion.getIdExamentitulacion());
                examenTitulacion.setMaterialTitulacion(materialTitulacion);
            }
            em.persist(examenTitulacion);
            if (idTramitetitulacion != null) {
                idTramitetitulacion.getExamenTitulacionList().add(examenTitulacion);
                idTramitetitulacion = em.merge(idTramitetitulacion);
            }
            if (materialTitulacion != null) {
                ExamenTitulacion oldExamenTitulacionOfMaterialTitulacion = materialTitulacion.getExamenTitulacion();
                if (oldExamenTitulacionOfMaterialTitulacion != null) {
                    oldExamenTitulacionOfMaterialTitulacion.setMaterialTitulacion(null);
                    oldExamenTitulacionOfMaterialTitulacion = em.merge(oldExamenTitulacionOfMaterialTitulacion);
                }
                materialTitulacion.setExamenTitulacion(examenTitulacion);
                materialTitulacion = em.merge(materialTitulacion);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ExamenTitulacion examenTitulacion) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ExamenTitulacion persistentExamenTitulacion = em.find(ExamenTitulacion.class, examenTitulacion.getIdExamentitulacion());
            TramiteTitulacion idTramitetitulacionOld = persistentExamenTitulacion.getIdTramitetitulacion();
            TramiteTitulacion idTramitetitulacionNew = examenTitulacion.getIdTramitetitulacion();
            MaterialTitulacion materialTitulacionOld = persistentExamenTitulacion.getMaterialTitulacion();
            MaterialTitulacion materialTitulacionNew = examenTitulacion.getMaterialTitulacion();
            List<String> illegalOrphanMessages = null;
            if (materialTitulacionOld != null && !materialTitulacionOld.equals(materialTitulacionNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain MaterialTitulacion " + materialTitulacionOld + " since its examenTitulacion field is not nullable.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idTramitetitulacionNew != null) {
                idTramitetitulacionNew = em.getReference(idTramitetitulacionNew.getClass(), idTramitetitulacionNew.getIdTramitetitulacion());
                examenTitulacion.setIdTramitetitulacion(idTramitetitulacionNew);
            }
            if (materialTitulacionNew != null) {
                materialTitulacionNew = em.getReference(materialTitulacionNew.getClass(), materialTitulacionNew.getIdExamentitulacion());
                examenTitulacion.setMaterialTitulacion(materialTitulacionNew);
            }
            examenTitulacion = em.merge(examenTitulacion);
            if (idTramitetitulacionOld != null && !idTramitetitulacionOld.equals(idTramitetitulacionNew)) {
                idTramitetitulacionOld.getExamenTitulacionList().remove(examenTitulacion);
                idTramitetitulacionOld = em.merge(idTramitetitulacionOld);
            }
            if (idTramitetitulacionNew != null && !idTramitetitulacionNew.equals(idTramitetitulacionOld)) {
                idTramitetitulacionNew.getExamenTitulacionList().add(examenTitulacion);
                idTramitetitulacionNew = em.merge(idTramitetitulacionNew);
            }
            if (materialTitulacionNew != null && !materialTitulacionNew.equals(materialTitulacionOld)) {
                ExamenTitulacion oldExamenTitulacionOfMaterialTitulacion = materialTitulacionNew.getExamenTitulacion();
                if (oldExamenTitulacionOfMaterialTitulacion != null) {
                    oldExamenTitulacionOfMaterialTitulacion.setMaterialTitulacion(null);
                    oldExamenTitulacionOfMaterialTitulacion = em.merge(oldExamenTitulacionOfMaterialTitulacion);
                }
                materialTitulacionNew.setExamenTitulacion(examenTitulacion);
                materialTitulacionNew = em.merge(materialTitulacionNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = examenTitulacion.getIdExamentitulacion();
                if (findExamenTitulacion(id) == null) {
                    throw new NonexistentEntityException("The examenTitulacion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ExamenTitulacion examenTitulacion;
            try {
                examenTitulacion = em.getReference(ExamenTitulacion.class, id);
                examenTitulacion.getIdExamentitulacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The examenTitulacion with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            MaterialTitulacion materialTitulacionOrphanCheck = examenTitulacion.getMaterialTitulacion();
            if (materialTitulacionOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ExamenTitulacion (" + examenTitulacion + ") cannot be destroyed since the MaterialTitulacion " + materialTitulacionOrphanCheck + " in its materialTitulacion field has a non-nullable examenTitulacion field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            TramiteTitulacion idTramitetitulacion = examenTitulacion.getIdTramitetitulacion();
            if (idTramitetitulacion != null) {
                idTramitetitulacion.getExamenTitulacionList().remove(examenTitulacion);
                idTramitetitulacion = em.merge(idTramitetitulacion);
            }
            em.remove(examenTitulacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ExamenTitulacion> findExamenTitulacionEntities() {
        return findExamenTitulacionEntities(true, -1, -1);
    }

    public List<ExamenTitulacion> findExamenTitulacionEntities(int maxResults, int firstResult) {
        return findExamenTitulacionEntities(false, maxResults, firstResult);
    }

    private List<ExamenTitulacion> findExamenTitulacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from ExamenTitulacion as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public ExamenTitulacion findExamenTitulacion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ExamenTitulacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getExamenTitulacionCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from ExamenTitulacion as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
