/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.AsignacionSeccionencuesta;
import java.util.ArrayList;
import java.util.List;
import com.utez.integracion.entity.AsignacionPreguntaseccion;
import com.utez.integracion.entity.Seccion;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class SeccionJpaController implements Serializable {

    public SeccionJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Seccion seccion) {
        if (seccion.getAsignacionSeccionencuestaList() == null) {
            seccion.setAsignacionSeccionencuestaList(new ArrayList<AsignacionSeccionencuesta>());
        }
        if (seccion.getAsignacionPreguntaseccionList() == null) {
            seccion.setAsignacionPreguntaseccionList(new ArrayList<AsignacionPreguntaseccion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<AsignacionSeccionencuesta> attachedAsignacionSeccionencuestaList = new ArrayList<AsignacionSeccionencuesta>();
            for (AsignacionSeccionencuesta asignacionSeccionencuestaListAsignacionSeccionencuestaToAttach : seccion.getAsignacionSeccionencuestaList()) {
                asignacionSeccionencuestaListAsignacionSeccionencuestaToAttach = em.getReference(asignacionSeccionencuestaListAsignacionSeccionencuestaToAttach.getClass(), asignacionSeccionencuestaListAsignacionSeccionencuestaToAttach.getIdAsignacionseccionencuesta());
                attachedAsignacionSeccionencuestaList.add(asignacionSeccionencuestaListAsignacionSeccionencuestaToAttach);
            }
            seccion.setAsignacionSeccionencuestaList(attachedAsignacionSeccionencuestaList);
            List<AsignacionPreguntaseccion> attachedAsignacionPreguntaseccionList = new ArrayList<AsignacionPreguntaseccion>();
            for (AsignacionPreguntaseccion asignacionPreguntaseccionListAsignacionPreguntaseccionToAttach : seccion.getAsignacionPreguntaseccionList()) {
                asignacionPreguntaseccionListAsignacionPreguntaseccionToAttach = em.getReference(asignacionPreguntaseccionListAsignacionPreguntaseccionToAttach.getClass(), asignacionPreguntaseccionListAsignacionPreguntaseccionToAttach.getIdAsignacionpreguntaseccion());
                attachedAsignacionPreguntaseccionList.add(asignacionPreguntaseccionListAsignacionPreguntaseccionToAttach);
            }
            seccion.setAsignacionPreguntaseccionList(attachedAsignacionPreguntaseccionList);
            em.persist(seccion);
            for (AsignacionSeccionencuesta asignacionSeccionencuestaListAsignacionSeccionencuesta : seccion.getAsignacionSeccionencuestaList()) {
                Seccion oldIdSeccionOfAsignacionSeccionencuestaListAsignacionSeccionencuesta = asignacionSeccionencuestaListAsignacionSeccionencuesta.getIdSeccion();
                asignacionSeccionencuestaListAsignacionSeccionencuesta.setIdSeccion(seccion);
                asignacionSeccionencuestaListAsignacionSeccionencuesta = em.merge(asignacionSeccionencuestaListAsignacionSeccionencuesta);
                if (oldIdSeccionOfAsignacionSeccionencuestaListAsignacionSeccionencuesta != null) {
                    oldIdSeccionOfAsignacionSeccionencuestaListAsignacionSeccionencuesta.getAsignacionSeccionencuestaList().remove(asignacionSeccionencuestaListAsignacionSeccionencuesta);
                    oldIdSeccionOfAsignacionSeccionencuestaListAsignacionSeccionencuesta = em.merge(oldIdSeccionOfAsignacionSeccionencuestaListAsignacionSeccionencuesta);
                }
            }
            for (AsignacionPreguntaseccion asignacionPreguntaseccionListAsignacionPreguntaseccion : seccion.getAsignacionPreguntaseccionList()) {
                Seccion oldIdSeccionOfAsignacionPreguntaseccionListAsignacionPreguntaseccion = asignacionPreguntaseccionListAsignacionPreguntaseccion.getIdSeccion();
                asignacionPreguntaseccionListAsignacionPreguntaseccion.setIdSeccion(seccion);
                asignacionPreguntaseccionListAsignacionPreguntaseccion = em.merge(asignacionPreguntaseccionListAsignacionPreguntaseccion);
                if (oldIdSeccionOfAsignacionPreguntaseccionListAsignacionPreguntaseccion != null) {
                    oldIdSeccionOfAsignacionPreguntaseccionListAsignacionPreguntaseccion.getAsignacionPreguntaseccionList().remove(asignacionPreguntaseccionListAsignacionPreguntaseccion);
                    oldIdSeccionOfAsignacionPreguntaseccionListAsignacionPreguntaseccion = em.merge(oldIdSeccionOfAsignacionPreguntaseccionListAsignacionPreguntaseccion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Seccion seccion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Seccion persistentSeccion = em.find(Seccion.class, seccion.getIdSeccion());
            List<AsignacionSeccionencuesta> asignacionSeccionencuestaListOld = persistentSeccion.getAsignacionSeccionencuestaList();
            List<AsignacionSeccionencuesta> asignacionSeccionencuestaListNew = seccion.getAsignacionSeccionencuestaList();
            List<AsignacionPreguntaseccion> asignacionPreguntaseccionListOld = persistentSeccion.getAsignacionPreguntaseccionList();
            List<AsignacionPreguntaseccion> asignacionPreguntaseccionListNew = seccion.getAsignacionPreguntaseccionList();
            List<AsignacionSeccionencuesta> attachedAsignacionSeccionencuestaListNew = new ArrayList<AsignacionSeccionencuesta>();
            for (AsignacionSeccionencuesta asignacionSeccionencuestaListNewAsignacionSeccionencuestaToAttach : asignacionSeccionencuestaListNew) {
                asignacionSeccionencuestaListNewAsignacionSeccionencuestaToAttach = em.getReference(asignacionSeccionencuestaListNewAsignacionSeccionencuestaToAttach.getClass(), asignacionSeccionencuestaListNewAsignacionSeccionencuestaToAttach.getIdAsignacionseccionencuesta());
                attachedAsignacionSeccionencuestaListNew.add(asignacionSeccionencuestaListNewAsignacionSeccionencuestaToAttach);
            }
            asignacionSeccionencuestaListNew = attachedAsignacionSeccionencuestaListNew;
            seccion.setAsignacionSeccionencuestaList(asignacionSeccionencuestaListNew);
            List<AsignacionPreguntaseccion> attachedAsignacionPreguntaseccionListNew = new ArrayList<AsignacionPreguntaseccion>();
            for (AsignacionPreguntaseccion asignacionPreguntaseccionListNewAsignacionPreguntaseccionToAttach : asignacionPreguntaseccionListNew) {
                asignacionPreguntaseccionListNewAsignacionPreguntaseccionToAttach = em.getReference(asignacionPreguntaseccionListNewAsignacionPreguntaseccionToAttach.getClass(), asignacionPreguntaseccionListNewAsignacionPreguntaseccionToAttach.getIdAsignacionpreguntaseccion());
                attachedAsignacionPreguntaseccionListNew.add(asignacionPreguntaseccionListNewAsignacionPreguntaseccionToAttach);
            }
            asignacionPreguntaseccionListNew = attachedAsignacionPreguntaseccionListNew;
            seccion.setAsignacionPreguntaseccionList(asignacionPreguntaseccionListNew);
            seccion = em.merge(seccion);
            for (AsignacionSeccionencuesta asignacionSeccionencuestaListOldAsignacionSeccionencuesta : asignacionSeccionencuestaListOld) {
                if (!asignacionSeccionencuestaListNew.contains(asignacionSeccionencuestaListOldAsignacionSeccionencuesta)) {
                    asignacionSeccionencuestaListOldAsignacionSeccionencuesta.setIdSeccion(null);
                    asignacionSeccionencuestaListOldAsignacionSeccionencuesta = em.merge(asignacionSeccionencuestaListOldAsignacionSeccionencuesta);
                }
            }
            for (AsignacionSeccionencuesta asignacionSeccionencuestaListNewAsignacionSeccionencuesta : asignacionSeccionencuestaListNew) {
                if (!asignacionSeccionencuestaListOld.contains(asignacionSeccionencuestaListNewAsignacionSeccionencuesta)) {
                    Seccion oldIdSeccionOfAsignacionSeccionencuestaListNewAsignacionSeccionencuesta = asignacionSeccionencuestaListNewAsignacionSeccionencuesta.getIdSeccion();
                    asignacionSeccionencuestaListNewAsignacionSeccionencuesta.setIdSeccion(seccion);
                    asignacionSeccionencuestaListNewAsignacionSeccionencuesta = em.merge(asignacionSeccionencuestaListNewAsignacionSeccionencuesta);
                    if (oldIdSeccionOfAsignacionSeccionencuestaListNewAsignacionSeccionencuesta != null && !oldIdSeccionOfAsignacionSeccionencuestaListNewAsignacionSeccionencuesta.equals(seccion)) {
                        oldIdSeccionOfAsignacionSeccionencuestaListNewAsignacionSeccionencuesta.getAsignacionSeccionencuestaList().remove(asignacionSeccionencuestaListNewAsignacionSeccionencuesta);
                        oldIdSeccionOfAsignacionSeccionencuestaListNewAsignacionSeccionencuesta = em.merge(oldIdSeccionOfAsignacionSeccionencuestaListNewAsignacionSeccionencuesta);
                    }
                }
            }
            for (AsignacionPreguntaseccion asignacionPreguntaseccionListOldAsignacionPreguntaseccion : asignacionPreguntaseccionListOld) {
                if (!asignacionPreguntaseccionListNew.contains(asignacionPreguntaseccionListOldAsignacionPreguntaseccion)) {
                    asignacionPreguntaseccionListOldAsignacionPreguntaseccion.setIdSeccion(null);
                    asignacionPreguntaseccionListOldAsignacionPreguntaseccion = em.merge(asignacionPreguntaseccionListOldAsignacionPreguntaseccion);
                }
            }
            for (AsignacionPreguntaseccion asignacionPreguntaseccionListNewAsignacionPreguntaseccion : asignacionPreguntaseccionListNew) {
                if (!asignacionPreguntaseccionListOld.contains(asignacionPreguntaseccionListNewAsignacionPreguntaseccion)) {
                    Seccion oldIdSeccionOfAsignacionPreguntaseccionListNewAsignacionPreguntaseccion = asignacionPreguntaseccionListNewAsignacionPreguntaseccion.getIdSeccion();
                    asignacionPreguntaseccionListNewAsignacionPreguntaseccion.setIdSeccion(seccion);
                    asignacionPreguntaseccionListNewAsignacionPreguntaseccion = em.merge(asignacionPreguntaseccionListNewAsignacionPreguntaseccion);
                    if (oldIdSeccionOfAsignacionPreguntaseccionListNewAsignacionPreguntaseccion != null && !oldIdSeccionOfAsignacionPreguntaseccionListNewAsignacionPreguntaseccion.equals(seccion)) {
                        oldIdSeccionOfAsignacionPreguntaseccionListNewAsignacionPreguntaseccion.getAsignacionPreguntaseccionList().remove(asignacionPreguntaseccionListNewAsignacionPreguntaseccion);
                        oldIdSeccionOfAsignacionPreguntaseccionListNewAsignacionPreguntaseccion = em.merge(oldIdSeccionOfAsignacionPreguntaseccionListNewAsignacionPreguntaseccion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = seccion.getIdSeccion();
                if (findSeccion(id) == null) {
                    throw new NonexistentEntityException("The seccion with id " + id + " no longer exists.");
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
            Seccion seccion;
            try {
                seccion = em.getReference(Seccion.class, id);
                seccion.getIdSeccion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The seccion with id " + id + " no longer exists.", enfe);
            }
            List<AsignacionSeccionencuesta> asignacionSeccionencuestaList = seccion.getAsignacionSeccionencuestaList();
            for (AsignacionSeccionencuesta asignacionSeccionencuestaListAsignacionSeccionencuesta : asignacionSeccionencuestaList) {
                asignacionSeccionencuestaListAsignacionSeccionencuesta.setIdSeccion(null);
                asignacionSeccionencuestaListAsignacionSeccionencuesta = em.merge(asignacionSeccionencuestaListAsignacionSeccionencuesta);
            }
            List<AsignacionPreguntaseccion> asignacionPreguntaseccionList = seccion.getAsignacionPreguntaseccionList();
            for (AsignacionPreguntaseccion asignacionPreguntaseccionListAsignacionPreguntaseccion : asignacionPreguntaseccionList) {
                asignacionPreguntaseccionListAsignacionPreguntaseccion.setIdSeccion(null);
                asignacionPreguntaseccionListAsignacionPreguntaseccion = em.merge(asignacionPreguntaseccionListAsignacionPreguntaseccion);
            }
            em.remove(seccion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Seccion> findSeccionEntities() {
        return findSeccionEntities(true, -1, -1);
    }

    public List<Seccion> findSeccionEntities(int maxResults, int firstResult) {
        return findSeccionEntities(false, maxResults, firstResult);
    }

    private List<Seccion> findSeccionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Seccion as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Seccion findSeccion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Seccion.class, id);
        } finally {
            em.close();
        }
    }

    public int getSeccionCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Seccion as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
