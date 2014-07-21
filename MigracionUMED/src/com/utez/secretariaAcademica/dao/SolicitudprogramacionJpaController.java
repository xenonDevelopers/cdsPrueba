/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.secretariaAcademica.dao;

import com.utez.secretariaAcademica.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.secretariaAcademica.entity.Cuadernoprogramacion;
import com.utez.secretariaAcademica.entity.Solicitudprogramacion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class SolicitudprogramacionJpaController implements Serializable {

    public SolicitudprogramacionJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Solicitudprogramacion solicitudprogramacion) {
        if (solicitudprogramacion.getCuadernoprogramacionList() == null) {
            solicitudprogramacion.setCuadernoprogramacionList(new ArrayList<Cuadernoprogramacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Cuadernoprogramacion> attachedCuadernoprogramacionList = new ArrayList<Cuadernoprogramacion>();
            for (Cuadernoprogramacion cuadernoprogramacionListCuadernoprogramacionToAttach : solicitudprogramacion.getCuadernoprogramacionList()) {
                cuadernoprogramacionListCuadernoprogramacionToAttach = em.getReference(cuadernoprogramacionListCuadernoprogramacionToAttach.getClass(), cuadernoprogramacionListCuadernoprogramacionToAttach.getIdcuadernoprogramacion());
                attachedCuadernoprogramacionList.add(cuadernoprogramacionListCuadernoprogramacionToAttach);
            }
            solicitudprogramacion.setCuadernoprogramacionList(attachedCuadernoprogramacionList);
            em.persist(solicitudprogramacion);
            for (Cuadernoprogramacion cuadernoprogramacionListCuadernoprogramacion : solicitudprogramacion.getCuadernoprogramacionList()) {
                Solicitudprogramacion oldIdsolicitudprogramacionOfCuadernoprogramacionListCuadernoprogramacion = cuadernoprogramacionListCuadernoprogramacion.getIdsolicitudprogramacion();
                cuadernoprogramacionListCuadernoprogramacion.setIdsolicitudprogramacion(solicitudprogramacion);
                cuadernoprogramacionListCuadernoprogramacion = em.merge(cuadernoprogramacionListCuadernoprogramacion);
                if (oldIdsolicitudprogramacionOfCuadernoprogramacionListCuadernoprogramacion != null) {
                    oldIdsolicitudprogramacionOfCuadernoprogramacionListCuadernoprogramacion.getCuadernoprogramacionList().remove(cuadernoprogramacionListCuadernoprogramacion);
                    oldIdsolicitudprogramacionOfCuadernoprogramacionListCuadernoprogramacion = em.merge(oldIdsolicitudprogramacionOfCuadernoprogramacionListCuadernoprogramacion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Solicitudprogramacion solicitudprogramacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Solicitudprogramacion persistentSolicitudprogramacion = em.find(Solicitudprogramacion.class, solicitudprogramacion.getIdsolicitudprogramacion());
            List<Cuadernoprogramacion> cuadernoprogramacionListOld = persistentSolicitudprogramacion.getCuadernoprogramacionList();
            List<Cuadernoprogramacion> cuadernoprogramacionListNew = solicitudprogramacion.getCuadernoprogramacionList();
            List<Cuadernoprogramacion> attachedCuadernoprogramacionListNew = new ArrayList<Cuadernoprogramacion>();
            for (Cuadernoprogramacion cuadernoprogramacionListNewCuadernoprogramacionToAttach : cuadernoprogramacionListNew) {
                cuadernoprogramacionListNewCuadernoprogramacionToAttach = em.getReference(cuadernoprogramacionListNewCuadernoprogramacionToAttach.getClass(), cuadernoprogramacionListNewCuadernoprogramacionToAttach.getIdcuadernoprogramacion());
                attachedCuadernoprogramacionListNew.add(cuadernoprogramacionListNewCuadernoprogramacionToAttach);
            }
            cuadernoprogramacionListNew = attachedCuadernoprogramacionListNew;
            solicitudprogramacion.setCuadernoprogramacionList(cuadernoprogramacionListNew);
            solicitudprogramacion = em.merge(solicitudprogramacion);
            for (Cuadernoprogramacion cuadernoprogramacionListOldCuadernoprogramacion : cuadernoprogramacionListOld) {
                if (!cuadernoprogramacionListNew.contains(cuadernoprogramacionListOldCuadernoprogramacion)) {
                    cuadernoprogramacionListOldCuadernoprogramacion.setIdsolicitudprogramacion(null);
                    cuadernoprogramacionListOldCuadernoprogramacion = em.merge(cuadernoprogramacionListOldCuadernoprogramacion);
                }
            }
            for (Cuadernoprogramacion cuadernoprogramacionListNewCuadernoprogramacion : cuadernoprogramacionListNew) {
                if (!cuadernoprogramacionListOld.contains(cuadernoprogramacionListNewCuadernoprogramacion)) {
                    Solicitudprogramacion oldIdsolicitudprogramacionOfCuadernoprogramacionListNewCuadernoprogramacion = cuadernoprogramacionListNewCuadernoprogramacion.getIdsolicitudprogramacion();
                    cuadernoprogramacionListNewCuadernoprogramacion.setIdsolicitudprogramacion(solicitudprogramacion);
                    cuadernoprogramacionListNewCuadernoprogramacion = em.merge(cuadernoprogramacionListNewCuadernoprogramacion);
                    if (oldIdsolicitudprogramacionOfCuadernoprogramacionListNewCuadernoprogramacion != null && !oldIdsolicitudprogramacionOfCuadernoprogramacionListNewCuadernoprogramacion.equals(solicitudprogramacion)) {
                        oldIdsolicitudprogramacionOfCuadernoprogramacionListNewCuadernoprogramacion.getCuadernoprogramacionList().remove(cuadernoprogramacionListNewCuadernoprogramacion);
                        oldIdsolicitudprogramacionOfCuadernoprogramacionListNewCuadernoprogramacion = em.merge(oldIdsolicitudprogramacionOfCuadernoprogramacionListNewCuadernoprogramacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = solicitudprogramacion.getIdsolicitudprogramacion();
                if (findSolicitudprogramacion(id) == null) {
                    throw new NonexistentEntityException("The solicitudprogramacion with id " + id + " no longer exists.");
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
            Solicitudprogramacion solicitudprogramacion;
            try {
                solicitudprogramacion = em.getReference(Solicitudprogramacion.class, id);
                solicitudprogramacion.getIdsolicitudprogramacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The solicitudprogramacion with id " + id + " no longer exists.", enfe);
            }
            List<Cuadernoprogramacion> cuadernoprogramacionList = solicitudprogramacion.getCuadernoprogramacionList();
            for (Cuadernoprogramacion cuadernoprogramacionListCuadernoprogramacion : cuadernoprogramacionList) {
                cuadernoprogramacionListCuadernoprogramacion.setIdsolicitudprogramacion(null);
                cuadernoprogramacionListCuadernoprogramacion = em.merge(cuadernoprogramacionListCuadernoprogramacion);
            }
            em.remove(solicitudprogramacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Solicitudprogramacion> findSolicitudprogramacionEntities() {
        return findSolicitudprogramacionEntities(true, -1, -1);
    }

    public List<Solicitudprogramacion> findSolicitudprogramacionEntities(int maxResults, int firstResult) {
        return findSolicitudprogramacionEntities(false, maxResults, firstResult);
    }

    private List<Solicitudprogramacion> findSolicitudprogramacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Solicitudprogramacion as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Solicitudprogramacion findSolicitudprogramacion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Solicitudprogramacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getSolicitudprogramacionCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Solicitudprogramacion as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
