/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.secretariaAcademica.dao;

import com.utez.integracion.entity.TipoAlumno;
import com.utez.secretariaAcademica.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.secretariaAcademica.entity.Alumno;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class TipoAlumnoJpaController implements Serializable {

    public TipoAlumnoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoAlumno tipoAlumno) {
        if (tipoAlumno.getAlumnoList() == null) {
            tipoAlumno.setAlumnoList(new ArrayList<Alumno>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Alumno> attachedAlumnoList = new ArrayList<Alumno>();
            for (Alumno alumnoListAlumnoToAttach : tipoAlumno.getAlumnoList()) {
                alumnoListAlumnoToAttach = em.getReference(alumnoListAlumnoToAttach.getClass(), alumnoListAlumnoToAttach.getMatricula());
                attachedAlumnoList.add(alumnoListAlumnoToAttach);
            }
            tipoAlumno.setAlumnoList(attachedAlumnoList);
            em.persist(tipoAlumno);
            for (Alumno alumnoListAlumno : tipoAlumno.getAlumnoList()) {
                TipoAlumno oldIdTipoalumnoOfAlumnoListAlumno = alumnoListAlumno.getIdTipoalumno();
                alumnoListAlumno.setIdTipoalumno(tipoAlumno);
                alumnoListAlumno = em.merge(alumnoListAlumno);
                if (oldIdTipoalumnoOfAlumnoListAlumno != null) {
                    oldIdTipoalumnoOfAlumnoListAlumno.getAlumnoList().remove(alumnoListAlumno);
                    oldIdTipoalumnoOfAlumnoListAlumno = em.merge(oldIdTipoalumnoOfAlumnoListAlumno);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoAlumno tipoAlumno) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoAlumno persistentTipoAlumno = em.find(TipoAlumno.class, tipoAlumno.getIdTipoalumno());
            List<Alumno> alumnoListOld = persistentTipoAlumno.getAlumnoList();
            List<Alumno> alumnoListNew = tipoAlumno.getAlumnoList();
            List<Alumno> attachedAlumnoListNew = new ArrayList<Alumno>();
            for (Alumno alumnoListNewAlumnoToAttach : alumnoListNew) {
                alumnoListNewAlumnoToAttach = em.getReference(alumnoListNewAlumnoToAttach.getClass(), alumnoListNewAlumnoToAttach.getMatricula());
                attachedAlumnoListNew.add(alumnoListNewAlumnoToAttach);
            }
            alumnoListNew = attachedAlumnoListNew;
            tipoAlumno.setAlumnoList(alumnoListNew);
            tipoAlumno = em.merge(tipoAlumno);
            for (Alumno alumnoListOldAlumno : alumnoListOld) {
                if (!alumnoListNew.contains(alumnoListOldAlumno)) {
                    alumnoListOldAlumno.setIdTipoalumno(null);
                    alumnoListOldAlumno = em.merge(alumnoListOldAlumno);
                }
            }
            for (Alumno alumnoListNewAlumno : alumnoListNew) {
                if (!alumnoListOld.contains(alumnoListNewAlumno)) {
                    TipoAlumno oldIdTipoalumnoOfAlumnoListNewAlumno = alumnoListNewAlumno.getIdTipoalumno();
                    alumnoListNewAlumno.setIdTipoalumno(tipoAlumno);
                    alumnoListNewAlumno = em.merge(alumnoListNewAlumno);
                    if (oldIdTipoalumnoOfAlumnoListNewAlumno != null && !oldIdTipoalumnoOfAlumnoListNewAlumno.equals(tipoAlumno)) {
                        oldIdTipoalumnoOfAlumnoListNewAlumno.getAlumnoList().remove(alumnoListNewAlumno);
                        oldIdTipoalumnoOfAlumnoListNewAlumno = em.merge(oldIdTipoalumnoOfAlumnoListNewAlumno);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tipoAlumno.getIdTipoalumno();
                if (findTipoAlumno(id) == null) {
                    throw new NonexistentEntityException("The tipoAlumno with id " + id + " no longer exists.");
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
            TipoAlumno tipoAlumno;
            try {
                tipoAlumno = em.getReference(TipoAlumno.class, id);
                tipoAlumno.getIdTipoalumno();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoAlumno with id " + id + " no longer exists.", enfe);
            }
            List<Alumno> alumnoList = tipoAlumno.getAlumnoList();
            for (Alumno alumnoListAlumno : alumnoList) {
                alumnoListAlumno.setIdTipoalumno(null);
                alumnoListAlumno = em.merge(alumnoListAlumno);
            }
            em.remove(tipoAlumno);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoAlumno> findTipoAlumnoEntities() {
        return findTipoAlumnoEntities(true, -1, -1);
    }

    public List<TipoAlumno> findTipoAlumnoEntities(int maxResults, int firstResult) {
        return findTipoAlumnoEntities(false, maxResults, firstResult);
    }

    private List<TipoAlumno> findTipoAlumnoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from TipoAlumno as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public TipoAlumno findTipoAlumno(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoAlumno.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoAlumnoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from TipoAlumno as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
