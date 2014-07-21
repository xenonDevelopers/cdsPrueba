/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.entity.Convenio;
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
public class ConvenioJpaController implements Serializable {

    public ConvenioJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Convenio convenio) {
        if (convenio.getAlumnoList() == null) {
            convenio.setAlumnoList(new ArrayList<Alumno>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Alumno> attachedAlumnoList = new ArrayList<Alumno>();
            for (Alumno alumnoListAlumnoToAttach : convenio.getAlumnoList()) {
                alumnoListAlumnoToAttach = em.getReference(alumnoListAlumnoToAttach.getClass(), alumnoListAlumnoToAttach.getMatricula());
                attachedAlumnoList.add(alumnoListAlumnoToAttach);
            }
            convenio.setAlumnoList(attachedAlumnoList);
            em.persist(convenio);
            for (Alumno alumnoListAlumno : convenio.getAlumnoList()) {
                alumnoListAlumno.getConvenioList().add(convenio);
                alumnoListAlumno = em.merge(alumnoListAlumno);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Convenio convenio) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Convenio persistentConvenio = em.find(Convenio.class, convenio.getIdConvenio());
            List<Alumno> alumnoListOld = persistentConvenio.getAlumnoList();
            List<Alumno> alumnoListNew = convenio.getAlumnoList();
            List<Alumno> attachedAlumnoListNew = new ArrayList<Alumno>();
            for (Alumno alumnoListNewAlumnoToAttach : alumnoListNew) {
                alumnoListNewAlumnoToAttach = em.getReference(alumnoListNewAlumnoToAttach.getClass(), alumnoListNewAlumnoToAttach.getMatricula());
                attachedAlumnoListNew.add(alumnoListNewAlumnoToAttach);
            }
            alumnoListNew = attachedAlumnoListNew;
            convenio.setAlumnoList(alumnoListNew);
            convenio = em.merge(convenio);
            for (Alumno alumnoListOldAlumno : alumnoListOld) {
                if (!alumnoListNew.contains(alumnoListOldAlumno)) {
                    alumnoListOldAlumno.getConvenioList().remove(convenio);
                    alumnoListOldAlumno = em.merge(alumnoListOldAlumno);
                }
            }
            for (Alumno alumnoListNewAlumno : alumnoListNew) {
                if (!alumnoListOld.contains(alumnoListNewAlumno)) {
                    alumnoListNewAlumno.getConvenioList().add(convenio);
                    alumnoListNewAlumno = em.merge(alumnoListNewAlumno);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = convenio.getIdConvenio();
                if (findConvenio(id) == null) {
                    throw new NonexistentEntityException("The convenio with id " + id + " no longer exists.");
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
            Convenio convenio;
            try {
                convenio = em.getReference(Convenio.class, id);
                convenio.getIdConvenio();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The convenio with id " + id + " no longer exists.", enfe);
            }
            List<Alumno> alumnoList = convenio.getAlumnoList();
            for (Alumno alumnoListAlumno : alumnoList) {
                alumnoListAlumno.getConvenioList().remove(convenio);
                alumnoListAlumno = em.merge(alumnoListAlumno);
            }
            em.remove(convenio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Convenio> findConvenioEntities() {
        return findConvenioEntities(true, -1, -1);
    }

    public List<Convenio> findConvenioEntities(int maxResults, int firstResult) {
        return findConvenioEntities(false, maxResults, firstResult);
    }

    private List<Convenio> findConvenioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Convenio as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Convenio findConvenio(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Convenio.class, id);
        } finally {
            em.close();
        }
    }

    public int getConvenioCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Convenio as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
