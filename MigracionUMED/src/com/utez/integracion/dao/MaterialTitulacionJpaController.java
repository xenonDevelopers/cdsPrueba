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
import com.utez.integracion.entity.ExamenTitulacion;
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
public class MaterialTitulacionJpaController implements Serializable {

    public MaterialTitulacionJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MaterialTitulacion materialTitulacion) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        ExamenTitulacion examenTitulacionOrphanCheck = materialTitulacion.getExamenTitulacion();
        if (examenTitulacionOrphanCheck != null) {
            MaterialTitulacion oldMaterialTitulacionOfExamenTitulacion = examenTitulacionOrphanCheck.getMaterialTitulacion();
            if (oldMaterialTitulacionOfExamenTitulacion != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The ExamenTitulacion " + examenTitulacionOrphanCheck + " already has an item of type MaterialTitulacion whose examenTitulacion column cannot be null. Please make another selection for the examenTitulacion field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ExamenTitulacion examenTitulacion = materialTitulacion.getExamenTitulacion();
            if (examenTitulacion != null) {
                examenTitulacion = em.getReference(examenTitulacion.getClass(), examenTitulacion.getIdExamentitulacion());
                materialTitulacion.setExamenTitulacion(examenTitulacion);
            }
            em.persist(materialTitulacion);
            if (examenTitulacion != null) {
                examenTitulacion.setMaterialTitulacion(materialTitulacion);
                examenTitulacion = em.merge(examenTitulacion);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMaterialTitulacion(materialTitulacion.getIdExamentitulacion()) != null) {
                throw new PreexistingEntityException("MaterialTitulacion " + materialTitulacion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MaterialTitulacion materialTitulacion) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MaterialTitulacion persistentMaterialTitulacion = em.find(MaterialTitulacion.class, materialTitulacion.getIdExamentitulacion());
            ExamenTitulacion examenTitulacionOld = persistentMaterialTitulacion.getExamenTitulacion();
            ExamenTitulacion examenTitulacionNew = materialTitulacion.getExamenTitulacion();
            List<String> illegalOrphanMessages = null;
            if (examenTitulacionNew != null && !examenTitulacionNew.equals(examenTitulacionOld)) {
                MaterialTitulacion oldMaterialTitulacionOfExamenTitulacion = examenTitulacionNew.getMaterialTitulacion();
                if (oldMaterialTitulacionOfExamenTitulacion != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The ExamenTitulacion " + examenTitulacionNew + " already has an item of type MaterialTitulacion whose examenTitulacion column cannot be null. Please make another selection for the examenTitulacion field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (examenTitulacionNew != null) {
                examenTitulacionNew = em.getReference(examenTitulacionNew.getClass(), examenTitulacionNew.getIdExamentitulacion());
                materialTitulacion.setExamenTitulacion(examenTitulacionNew);
            }
            materialTitulacion = em.merge(materialTitulacion);
            if (examenTitulacionOld != null && !examenTitulacionOld.equals(examenTitulacionNew)) {
                examenTitulacionOld.setMaterialTitulacion(null);
                examenTitulacionOld = em.merge(examenTitulacionOld);
            }
            if (examenTitulacionNew != null && !examenTitulacionNew.equals(examenTitulacionOld)) {
                examenTitulacionNew.setMaterialTitulacion(materialTitulacion);
                examenTitulacionNew = em.merge(examenTitulacionNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = materialTitulacion.getIdExamentitulacion();
                if (findMaterialTitulacion(id) == null) {
                    throw new NonexistentEntityException("The materialTitulacion with id " + id + " no longer exists.");
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
            MaterialTitulacion materialTitulacion;
            try {
                materialTitulacion = em.getReference(MaterialTitulacion.class, id);
                materialTitulacion.getIdExamentitulacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The materialTitulacion with id " + id + " no longer exists.", enfe);
            }
            ExamenTitulacion examenTitulacion = materialTitulacion.getExamenTitulacion();
            if (examenTitulacion != null) {
                examenTitulacion.setMaterialTitulacion(null);
                examenTitulacion = em.merge(examenTitulacion);
            }
            em.remove(materialTitulacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<MaterialTitulacion> findMaterialTitulacionEntities() {
        return findMaterialTitulacionEntities(true, -1, -1);
    }

    public List<MaterialTitulacion> findMaterialTitulacionEntities(int maxResults, int firstResult) {
        return findMaterialTitulacionEntities(false, maxResults, firstResult);
    }

    private List<MaterialTitulacion> findMaterialTitulacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from MaterialTitulacion as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public MaterialTitulacion findMaterialTitulacion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MaterialTitulacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getMaterialTitulacionCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from MaterialTitulacion as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
