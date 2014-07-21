/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.dao.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.OpcionEstudio;
import com.utez.integracion.entity.Aspirante;
import com.utez.integracion.entity.AspiranteOpcion;
import com.utez.integracion.entity.AspiranteOpcionPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class AspiranteOpcionJpaController implements Serializable {

    public AspiranteOpcionJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AspiranteOpcion aspiranteOpcion) throws PreexistingEntityException, Exception {
        if (aspiranteOpcion.getAspiranteOpcionPK() == null) {
            aspiranteOpcion.setAspiranteOpcionPK(new AspiranteOpcionPK());
        }
        aspiranteOpcion.getAspiranteOpcionPK().setIdOpcionestudio(aspiranteOpcion.getOpcionEstudio().getIdOpcionestudio());
        aspiranteOpcion.getAspiranteOpcionPK().setIdAspirante(aspiranteOpcion.getAspirante().getIdAspirante());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OpcionEstudio opcionEstudio = aspiranteOpcion.getOpcionEstudio();
            if (opcionEstudio != null) {
                opcionEstudio = em.getReference(opcionEstudio.getClass(), opcionEstudio.getIdOpcionestudio());
                aspiranteOpcion.setOpcionEstudio(opcionEstudio);
            }
            Aspirante aspirante = aspiranteOpcion.getAspirante();
            if (aspirante != null) {
                aspirante = em.getReference(aspirante.getClass(), aspirante.getIdAspirante());
                aspiranteOpcion.setAspirante(aspirante);
            }
            em.persist(aspiranteOpcion);
            if (opcionEstudio != null) {
                opcionEstudio.getAspiranteOpcionList().add(aspiranteOpcion);
                opcionEstudio = em.merge(opcionEstudio);
            }
            if (aspirante != null) {
                aspirante.getAspiranteOpcionList().add(aspiranteOpcion);
                aspirante = em.merge(aspirante);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAspiranteOpcion(aspiranteOpcion.getAspiranteOpcionPK()) != null) {
                throw new PreexistingEntityException("AspiranteOpcion " + aspiranteOpcion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AspiranteOpcion aspiranteOpcion) throws NonexistentEntityException, Exception {
        aspiranteOpcion.getAspiranteOpcionPK().setIdOpcionestudio(aspiranteOpcion.getOpcionEstudio().getIdOpcionestudio());
        aspiranteOpcion.getAspiranteOpcionPK().setIdAspirante(aspiranteOpcion.getAspirante().getIdAspirante());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AspiranteOpcion persistentAspiranteOpcion = em.find(AspiranteOpcion.class, aspiranteOpcion.getAspiranteOpcionPK());
            OpcionEstudio opcionEstudioOld = persistentAspiranteOpcion.getOpcionEstudio();
            OpcionEstudio opcionEstudioNew = aspiranteOpcion.getOpcionEstudio();
            Aspirante aspiranteOld = persistentAspiranteOpcion.getAspirante();
            Aspirante aspiranteNew = aspiranteOpcion.getAspirante();
            if (opcionEstudioNew != null) {
                opcionEstudioNew = em.getReference(opcionEstudioNew.getClass(), opcionEstudioNew.getIdOpcionestudio());
                aspiranteOpcion.setOpcionEstudio(opcionEstudioNew);
            }
            if (aspiranteNew != null) {
                aspiranteNew = em.getReference(aspiranteNew.getClass(), aspiranteNew.getIdAspirante());
                aspiranteOpcion.setAspirante(aspiranteNew);
            }
            aspiranteOpcion = em.merge(aspiranteOpcion);
            if (opcionEstudioOld != null && !opcionEstudioOld.equals(opcionEstudioNew)) {
                opcionEstudioOld.getAspiranteOpcionList().remove(aspiranteOpcion);
                opcionEstudioOld = em.merge(opcionEstudioOld);
            }
            if (opcionEstudioNew != null && !opcionEstudioNew.equals(opcionEstudioOld)) {
                opcionEstudioNew.getAspiranteOpcionList().add(aspiranteOpcion);
                opcionEstudioNew = em.merge(opcionEstudioNew);
            }
            if (aspiranteOld != null && !aspiranteOld.equals(aspiranteNew)) {
                aspiranteOld.getAspiranteOpcionList().remove(aspiranteOpcion);
                aspiranteOld = em.merge(aspiranteOld);
            }
            if (aspiranteNew != null && !aspiranteNew.equals(aspiranteOld)) {
                aspiranteNew.getAspiranteOpcionList().add(aspiranteOpcion);
                aspiranteNew = em.merge(aspiranteNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                AspiranteOpcionPK id = aspiranteOpcion.getAspiranteOpcionPK();
                if (findAspiranteOpcion(id) == null) {
                    throw new NonexistentEntityException("The aspiranteOpcion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(AspiranteOpcionPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AspiranteOpcion aspiranteOpcion;
            try {
                aspiranteOpcion = em.getReference(AspiranteOpcion.class, id);
                aspiranteOpcion.getAspiranteOpcionPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The aspiranteOpcion with id " + id + " no longer exists.", enfe);
            }
            OpcionEstudio opcionEstudio = aspiranteOpcion.getOpcionEstudio();
            if (opcionEstudio != null) {
                opcionEstudio.getAspiranteOpcionList().remove(aspiranteOpcion);
                opcionEstudio = em.merge(opcionEstudio);
            }
            Aspirante aspirante = aspiranteOpcion.getAspirante();
            if (aspirante != null) {
                aspirante.getAspiranteOpcionList().remove(aspiranteOpcion);
                aspirante = em.merge(aspirante);
            }
            em.remove(aspiranteOpcion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AspiranteOpcion> findAspiranteOpcionEntities() {
        return findAspiranteOpcionEntities(true, -1, -1);
    }

    public List<AspiranteOpcion> findAspiranteOpcionEntities(int maxResults, int firstResult) {
        return findAspiranteOpcionEntities(false, maxResults, firstResult);
    }

    private List<AspiranteOpcion> findAspiranteOpcionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from AspiranteOpcion as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public AspiranteOpcion findAspiranteOpcion(AspiranteOpcionPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AspiranteOpcion.class, id);
        } finally {
            em.close();
        }
    }

    public int getAspiranteOpcionCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from AspiranteOpcion as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
