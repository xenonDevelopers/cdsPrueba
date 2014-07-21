/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.secretariaAcademica.entity.Alumno;
import com.utez.integracion.entity.ReporteServicio;
import com.utez.integracion.entity.ServicioSocial;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class ServicioSocialJpaController implements Serializable {

    public ServicioSocialJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ServicioSocial servicioSocial) {
        if (servicioSocial.getReporteServicioList() == null) {
            servicioSocial.setReporteServicioList(new ArrayList<ReporteServicio>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Alumno matricula = servicioSocial.getMatricula();
            if (matricula != null) {
                matricula = em.getReference(matricula.getClass(), matricula.getMatricula());
                servicioSocial.setMatricula(matricula);
            }
            List<ReporteServicio> attachedReporteServicioList = new ArrayList<ReporteServicio>();
            for (ReporteServicio reporteServicioListReporteServicioToAttach : servicioSocial.getReporteServicioList()) {
                reporteServicioListReporteServicioToAttach = em.getReference(reporteServicioListReporteServicioToAttach.getClass(), reporteServicioListReporteServicioToAttach.getIdReporteservicio());
                attachedReporteServicioList.add(reporteServicioListReporteServicioToAttach);
            }
            servicioSocial.setReporteServicioList(attachedReporteServicioList);
            em.persist(servicioSocial);
            if (matricula != null) {
                matricula.getServicioSocialList().add(servicioSocial);
                matricula = em.merge(matricula);
            }
            for (ReporteServicio reporteServicioListReporteServicio : servicioSocial.getReporteServicioList()) {
                ServicioSocial oldIdServiciosocialOfReporteServicioListReporteServicio = reporteServicioListReporteServicio.getIdServiciosocial();
                reporteServicioListReporteServicio.setIdServiciosocial(servicioSocial);
                reporteServicioListReporteServicio = em.merge(reporteServicioListReporteServicio);
                if (oldIdServiciosocialOfReporteServicioListReporteServicio != null) {
                    oldIdServiciosocialOfReporteServicioListReporteServicio.getReporteServicioList().remove(reporteServicioListReporteServicio);
                    oldIdServiciosocialOfReporteServicioListReporteServicio = em.merge(oldIdServiciosocialOfReporteServicioListReporteServicio);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ServicioSocial servicioSocial) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ServicioSocial persistentServicioSocial = em.find(ServicioSocial.class, servicioSocial.getIdServiciosocial());
            Alumno matriculaOld = persistentServicioSocial.getMatricula();
            Alumno matriculaNew = servicioSocial.getMatricula();
            List<ReporteServicio> reporteServicioListOld = persistentServicioSocial.getReporteServicioList();
            List<ReporteServicio> reporteServicioListNew = servicioSocial.getReporteServicioList();
            if (matriculaNew != null) {
                matriculaNew = em.getReference(matriculaNew.getClass(), matriculaNew.getMatricula());
                servicioSocial.setMatricula(matriculaNew);
            }
            List<ReporteServicio> attachedReporteServicioListNew = new ArrayList<ReporteServicio>();
            for (ReporteServicio reporteServicioListNewReporteServicioToAttach : reporteServicioListNew) {
                reporteServicioListNewReporteServicioToAttach = em.getReference(reporteServicioListNewReporteServicioToAttach.getClass(), reporteServicioListNewReporteServicioToAttach.getIdReporteservicio());
                attachedReporteServicioListNew.add(reporteServicioListNewReporteServicioToAttach);
            }
            reporteServicioListNew = attachedReporteServicioListNew;
            servicioSocial.setReporteServicioList(reporteServicioListNew);
            servicioSocial = em.merge(servicioSocial);
            if (matriculaOld != null && !matriculaOld.equals(matriculaNew)) {
                matriculaOld.getServicioSocialList().remove(servicioSocial);
                matriculaOld = em.merge(matriculaOld);
            }
            if (matriculaNew != null && !matriculaNew.equals(matriculaOld)) {
                matriculaNew.getServicioSocialList().add(servicioSocial);
                matriculaNew = em.merge(matriculaNew);
            }
            for (ReporteServicio reporteServicioListOldReporteServicio : reporteServicioListOld) {
                if (!reporteServicioListNew.contains(reporteServicioListOldReporteServicio)) {
                    reporteServicioListOldReporteServicio.setIdServiciosocial(null);
                    reporteServicioListOldReporteServicio = em.merge(reporteServicioListOldReporteServicio);
                }
            }
            for (ReporteServicio reporteServicioListNewReporteServicio : reporteServicioListNew) {
                if (!reporteServicioListOld.contains(reporteServicioListNewReporteServicio)) {
                    ServicioSocial oldIdServiciosocialOfReporteServicioListNewReporteServicio = reporteServicioListNewReporteServicio.getIdServiciosocial();
                    reporteServicioListNewReporteServicio.setIdServiciosocial(servicioSocial);
                    reporteServicioListNewReporteServicio = em.merge(reporteServicioListNewReporteServicio);
                    if (oldIdServiciosocialOfReporteServicioListNewReporteServicio != null && !oldIdServiciosocialOfReporteServicioListNewReporteServicio.equals(servicioSocial)) {
                        oldIdServiciosocialOfReporteServicioListNewReporteServicio.getReporteServicioList().remove(reporteServicioListNewReporteServicio);
                        oldIdServiciosocialOfReporteServicioListNewReporteServicio = em.merge(oldIdServiciosocialOfReporteServicioListNewReporteServicio);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = servicioSocial.getIdServiciosocial();
                if (findServicioSocial(id) == null) {
                    throw new NonexistentEntityException("The servicioSocial with id " + id + " no longer exists.");
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
            ServicioSocial servicioSocial;
            try {
                servicioSocial = em.getReference(ServicioSocial.class, id);
                servicioSocial.getIdServiciosocial();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The servicioSocial with id " + id + " no longer exists.", enfe);
            }
            Alumno matricula = servicioSocial.getMatricula();
            if (matricula != null) {
                matricula.getServicioSocialList().remove(servicioSocial);
                matricula = em.merge(matricula);
            }
            List<ReporteServicio> reporteServicioList = servicioSocial.getReporteServicioList();
            for (ReporteServicio reporteServicioListReporteServicio : reporteServicioList) {
                reporteServicioListReporteServicio.setIdServiciosocial(null);
                reporteServicioListReporteServicio = em.merge(reporteServicioListReporteServicio);
            }
            em.remove(servicioSocial);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ServicioSocial> findServicioSocialEntities() {
        return findServicioSocialEntities(true, -1, -1);
    }

    public List<ServicioSocial> findServicioSocialEntities(int maxResults, int firstResult) {
        return findServicioSocialEntities(false, maxResults, firstResult);
    }

    private List<ServicioSocial> findServicioSocialEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from ServicioSocial as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public ServicioSocial findServicioSocial(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ServicioSocial.class, id);
        } finally {
            em.close();
        }
    }

    public int getServicioSocialCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from ServicioSocial as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
