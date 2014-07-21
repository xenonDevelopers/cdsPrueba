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
import com.utez.integracion.entity.TipoExamen;
import com.utez.integracion.entity.AsignaturaOpcionc;
import com.utez.integracion.entity.FechaExamenopcionc;
import com.utez.integracion.entity.FechaExamenopcioncPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class FechaExamenopcioncJpaController implements Serializable {

    public FechaExamenopcioncJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

//    public void create(FechaExamenopcionc fechaExamenopcionc) throws PreexistingEntityException, Exception {
//        if (fechaExamenopcionc.getFechaExamenopcioncPK() == null) {
//            fechaExamenopcionc.setFechaExamenopcioncPK(new FechaExamenopcioncPK());
//        }
//        fechaExamenopcionc.getFechaExamenopcioncPK().setIdAsignaturaopcionc(fechaExamenopcionc.getAsignaturaOpcionc().getIdAsignaturaopcionc());
//        fechaExamenopcionc.getFechaExamenopcioncPK().setIdTipoexamen(fechaExamenopcionc.getTipoExamen().getIdTipoexamen());
//        EntityManager em = null;
//        try {
//            em = getEntityManager();
//            em.getTransaction().begin();
//            TipoExamen tipoExamen = fechaExamenopcionc.getTipoExamen();
//            if (tipoExamen != null) {
//                tipoExamen = em.getReference(tipoExamen.getClass(), tipoExamen.getIdTipoexamen());
//                fechaExamenopcionc.setTipoExamen(tipoExamen);
//            }
//            AsignaturaOpcionc asignaturaOpcionc = fechaExamenopcionc.getAsignaturaOpcionc();
//            if (asignaturaOpcionc != null) {
//                asignaturaOpcionc = em.getReference(asignaturaOpcionc.getClass(), asignaturaOpcionc.getIdAsignaturaopcionc());
//                fechaExamenopcionc.setAsignaturaOpcionc(asignaturaOpcionc);
//            }
//            em.persist(fechaExamenopcionc);
//            if (tipoExamen != null) {
//                tipoExamen.getFechaExamenopcioncList().add(fechaExamenopcionc);
//                tipoExamen = em.merge(tipoExamen);
//            }
//            if (asignaturaOpcionc != null) {
//                asignaturaOpcionc.getFechaExamenopcioncList().add(fechaExamenopcionc);
//                asignaturaOpcionc = em.merge(asignaturaOpcionc);
//            }
//            em.getTransaction().commit();
//        } catch (Exception ex) {
//            if (findFechaExamenopcionc(fechaExamenopcionc.getFechaExamenopcioncPK()) != null) {
//                throw new PreexistingEntityException("FechaExamenopcionc " + fechaExamenopcionc + " already exists.", ex);
//            }
//            throw ex;
//        } finally {
//            if (em != null) {
//                em.close();
//            }
//        }
//    }
//
//    public void edit(FechaExamenopcionc fechaExamenopcionc) throws NonexistentEntityException, Exception {
//        fechaExamenopcionc.getFechaExamenopcioncPK().setIdAsignaturaopcionc(fechaExamenopcionc.getAsignaturaOpcionc().getIdAsignaturaopcionc());
//        fechaExamenopcionc.getFechaExamenopcioncPK().setIdTipoexamen(fechaExamenopcionc.getTipoExamen().getIdTipoexamen());
//        EntityManager em = null;
//        try {
//            em = getEntityManager();
//            em.getTransaction().begin();
//            FechaExamenopcionc persistentFechaExamenopcionc = em.find(FechaExamenopcionc.class, fechaExamenopcionc.getFechaExamenopcioncPK());
//            TipoExamen tipoExamenOld = persistentFechaExamenopcionc.getTipoExamen();
//            TipoExamen tipoExamenNew = fechaExamenopcionc.getTipoExamen();
//            AsignaturaOpcionc asignaturaOpcioncOld = persistentFechaExamenopcionc.getAsignaturaOpcionc();
//            AsignaturaOpcionc asignaturaOpcioncNew = fechaExamenopcionc.getAsignaturaOpcionc();
//            if (tipoExamenNew != null) {
//                tipoExamenNew = em.getReference(tipoExamenNew.getClass(), tipoExamenNew.getIdTipoexamen());
//                fechaExamenopcionc.setTipoExamen(tipoExamenNew);
//            }
//            if (asignaturaOpcioncNew != null) {
//                asignaturaOpcioncNew = em.getReference(asignaturaOpcioncNew.getClass(), asignaturaOpcioncNew.getIdAsignaturaopcionc());
//                fechaExamenopcionc.setAsignaturaOpcionc(asignaturaOpcioncNew);
//            }
//            fechaExamenopcionc = em.merge(fechaExamenopcionc);
//            if (tipoExamenOld != null && !tipoExamenOld.equals(tipoExamenNew)) {
//                tipoExamenOld.getFechaExamenopcioncList().remove(fechaExamenopcionc);
//                tipoExamenOld = em.merge(tipoExamenOld);
//            }
//            if (tipoExamenNew != null && !tipoExamenNew.equals(tipoExamenOld)) {
//                tipoExamenNew.getFechaExamenopcioncList().add(fechaExamenopcionc);
//                tipoExamenNew = em.merge(tipoExamenNew);
//            }
//            if (asignaturaOpcioncOld != null && !asignaturaOpcioncOld.equals(asignaturaOpcioncNew)) {
//                asignaturaOpcioncOld.getFechaExamenopcioncList().remove(fechaExamenopcionc);
//                asignaturaOpcioncOld = em.merge(asignaturaOpcioncOld);
//            }
//            if (asignaturaOpcioncNew != null && !asignaturaOpcioncNew.equals(asignaturaOpcioncOld)) {
//                asignaturaOpcioncNew.getFechaExamenopcioncList().add(fechaExamenopcionc);
//                asignaturaOpcioncNew = em.merge(asignaturaOpcioncNew);
//            }
//            em.getTransaction().commit();
//        } catch (Exception ex) {
//            String msg = ex.getLocalizedMessage();
//            if (msg == null || msg.length() == 0) {
//                FechaExamenopcioncPK id = fechaExamenopcionc.getFechaExamenopcioncPK();
//                if (findFechaExamenopcionc(id) == null) {
//                    throw new NonexistentEntityException("The fechaExamenopcionc with id " + id + " no longer exists.");
//                }
//            }
//            throw ex;
//        } finally {
//            if (em != null) {
//                em.close();
//            }
//        }
//    }

    public void destroy(FechaExamenopcioncPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FechaExamenopcionc fechaExamenopcionc;
            try {
                fechaExamenopcionc = em.getReference(FechaExamenopcionc.class, id);
                fechaExamenopcionc.getFechaExamenopcioncPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The fechaExamenopcionc with id " + id + " no longer exists.", enfe);
            }
            TipoExamen tipoExamen = fechaExamenopcionc.getTipoExamen();
            if (tipoExamen != null) {
                tipoExamen.getFechaExamenopcioncList().remove(fechaExamenopcionc);
                tipoExamen = em.merge(tipoExamen);
            }
            AsignaturaOpcionc asignaturaOpcionc = fechaExamenopcionc.getAsignaturaOpcionc();
            if (asignaturaOpcionc != null) {
                asignaturaOpcionc.getFechaExamenopcioncList().remove(fechaExamenopcionc);
                asignaturaOpcionc = em.merge(asignaturaOpcionc);
            }
            em.remove(fechaExamenopcionc);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<FechaExamenopcionc> findFechaExamenopcioncEntities() {
        return findFechaExamenopcioncEntities(true, -1, -1);
    }

    public List<FechaExamenopcionc> findFechaExamenopcioncEntities(int maxResults, int firstResult) {
        return findFechaExamenopcioncEntities(false, maxResults, firstResult);
    }

    private List<FechaExamenopcionc> findFechaExamenopcioncEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from FechaExamenopcionc as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public FechaExamenopcionc findFechaExamenopcionc(FechaExamenopcioncPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(FechaExamenopcionc.class, id);
        } finally {
            em.close();
        }
    }

    public int getFechaExamenopcioncCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from FechaExamenopcionc as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
