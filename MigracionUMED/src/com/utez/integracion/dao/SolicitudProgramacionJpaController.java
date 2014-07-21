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
import com.utez.integracion.entity.ArchivoSolicitudprogramacion;
import java.util.ArrayList;
import java.util.List;
import com.utez.integracion.entity.Programacion;
import com.utez.integracion.entity.SolicitudProgramacion;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class SolicitudProgramacionJpaController implements Serializable {

    public SolicitudProgramacionJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SolicitudProgramacion solicitudProgramacion) {
        if (solicitudProgramacion.getArchivoSolicitudprogramacionList() == null) {
            solicitudProgramacion.setArchivoSolicitudprogramacionList(new ArrayList<ArchivoSolicitudprogramacion>());
        }
        if (solicitudProgramacion.getProgramacionList() == null) {
            solicitudProgramacion.setProgramacionList(new ArrayList<Programacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Alumno matricula = solicitudProgramacion.getMatricula();
            if (matricula != null) {
                matricula = em.getReference(matricula.getClass(), matricula.getMatricula());
                solicitudProgramacion.setMatricula(matricula);
            }
            List<ArchivoSolicitudprogramacion> attachedArchivoSolicitudprogramacionList = new ArrayList<ArchivoSolicitudprogramacion>();
            for (ArchivoSolicitudprogramacion archivoSolicitudprogramacionListArchivoSolicitudprogramacionToAttach : solicitudProgramacion.getArchivoSolicitudprogramacionList()) {
                archivoSolicitudprogramacionListArchivoSolicitudprogramacionToAttach = em.getReference(archivoSolicitudprogramacionListArchivoSolicitudprogramacionToAttach.getClass(), archivoSolicitudprogramacionListArchivoSolicitudprogramacionToAttach.getIdArchivosolicitudprogramacion());
                attachedArchivoSolicitudprogramacionList.add(archivoSolicitudprogramacionListArchivoSolicitudprogramacionToAttach);
            }
            solicitudProgramacion.setArchivoSolicitudprogramacionList(attachedArchivoSolicitudprogramacionList);
            List<Programacion> attachedProgramacionList = new ArrayList<Programacion>();
            for (Programacion programacionListProgramacionToAttach : solicitudProgramacion.getProgramacionList()) {
                programacionListProgramacionToAttach = em.getReference(programacionListProgramacionToAttach.getClass(), programacionListProgramacionToAttach.getIdProgramacion());
                attachedProgramacionList.add(programacionListProgramacionToAttach);
            }
            solicitudProgramacion.setProgramacionList(attachedProgramacionList);
            em.persist(solicitudProgramacion);
            if (matricula != null) {
                matricula.getSolicitudProgramacionList().add(solicitudProgramacion);
                matricula = em.merge(matricula);
            }
            for (ArchivoSolicitudprogramacion archivoSolicitudprogramacionListArchivoSolicitudprogramacion : solicitudProgramacion.getArchivoSolicitudprogramacionList()) {
                SolicitudProgramacion oldIdSolicitudprogramacionOfArchivoSolicitudprogramacionListArchivoSolicitudprogramacion = archivoSolicitudprogramacionListArchivoSolicitudprogramacion.getIdSolicitudprogramacion();
                archivoSolicitudprogramacionListArchivoSolicitudprogramacion.setIdSolicitudprogramacion(solicitudProgramacion);
                archivoSolicitudprogramacionListArchivoSolicitudprogramacion = em.merge(archivoSolicitudprogramacionListArchivoSolicitudprogramacion);
                if (oldIdSolicitudprogramacionOfArchivoSolicitudprogramacionListArchivoSolicitudprogramacion != null) {
                    oldIdSolicitudprogramacionOfArchivoSolicitudprogramacionListArchivoSolicitudprogramacion.getArchivoSolicitudprogramacionList().remove(archivoSolicitudprogramacionListArchivoSolicitudprogramacion);
                    oldIdSolicitudprogramacionOfArchivoSolicitudprogramacionListArchivoSolicitudprogramacion = em.merge(oldIdSolicitudprogramacionOfArchivoSolicitudprogramacionListArchivoSolicitudprogramacion);
                }
            }
            for (Programacion programacionListProgramacion : solicitudProgramacion.getProgramacionList()) {
                SolicitudProgramacion oldIdSolicitudprogramacionOfProgramacionListProgramacion = programacionListProgramacion.getIdSolicitudprogramacion();
                programacionListProgramacion.setIdSolicitudprogramacion(solicitudProgramacion);
                programacionListProgramacion = em.merge(programacionListProgramacion);
                if (oldIdSolicitudprogramacionOfProgramacionListProgramacion != null) {
                    oldIdSolicitudprogramacionOfProgramacionListProgramacion.getProgramacionList().remove(programacionListProgramacion);
                    oldIdSolicitudprogramacionOfProgramacionListProgramacion = em.merge(oldIdSolicitudprogramacionOfProgramacionListProgramacion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SolicitudProgramacion solicitudProgramacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SolicitudProgramacion persistentSolicitudProgramacion = em.find(SolicitudProgramacion.class, solicitudProgramacion.getIdSolicitudprogramacion());
            Alumno matriculaOld = persistentSolicitudProgramacion.getMatricula();
            Alumno matriculaNew = solicitudProgramacion.getMatricula();
            List<ArchivoSolicitudprogramacion> archivoSolicitudprogramacionListOld = persistentSolicitudProgramacion.getArchivoSolicitudprogramacionList();
            List<ArchivoSolicitudprogramacion> archivoSolicitudprogramacionListNew = solicitudProgramacion.getArchivoSolicitudprogramacionList();
            List<Programacion> programacionListOld = persistentSolicitudProgramacion.getProgramacionList();
            List<Programacion> programacionListNew = solicitudProgramacion.getProgramacionList();
            if (matriculaNew != null) {
                matriculaNew = em.getReference(matriculaNew.getClass(), matriculaNew.getMatricula());
                solicitudProgramacion.setMatricula(matriculaNew);
            }
            List<ArchivoSolicitudprogramacion> attachedArchivoSolicitudprogramacionListNew = new ArrayList<ArchivoSolicitudprogramacion>();
            for (ArchivoSolicitudprogramacion archivoSolicitudprogramacionListNewArchivoSolicitudprogramacionToAttach : archivoSolicitudprogramacionListNew) {
                archivoSolicitudprogramacionListNewArchivoSolicitudprogramacionToAttach = em.getReference(archivoSolicitudprogramacionListNewArchivoSolicitudprogramacionToAttach.getClass(), archivoSolicitudprogramacionListNewArchivoSolicitudprogramacionToAttach.getIdArchivosolicitudprogramacion());
                attachedArchivoSolicitudprogramacionListNew.add(archivoSolicitudprogramacionListNewArchivoSolicitudprogramacionToAttach);
            }
            archivoSolicitudprogramacionListNew = attachedArchivoSolicitudprogramacionListNew;
            solicitudProgramacion.setArchivoSolicitudprogramacionList(archivoSolicitudprogramacionListNew);
            List<Programacion> attachedProgramacionListNew = new ArrayList<Programacion>();
            for (Programacion programacionListNewProgramacionToAttach : programacionListNew) {
                programacionListNewProgramacionToAttach = em.getReference(programacionListNewProgramacionToAttach.getClass(), programacionListNewProgramacionToAttach.getIdProgramacion());
                attachedProgramacionListNew.add(programacionListNewProgramacionToAttach);
            }
            programacionListNew = attachedProgramacionListNew;
            solicitudProgramacion.setProgramacionList(programacionListNew);
            solicitudProgramacion = em.merge(solicitudProgramacion);
            if (matriculaOld != null && !matriculaOld.equals(matriculaNew)) {
                matriculaOld.getSolicitudProgramacionList().remove(solicitudProgramacion);
                matriculaOld = em.merge(matriculaOld);
            }
            if (matriculaNew != null && !matriculaNew.equals(matriculaOld)) {
                matriculaNew.getSolicitudProgramacionList().add(solicitudProgramacion);
                matriculaNew = em.merge(matriculaNew);
            }
            for (ArchivoSolicitudprogramacion archivoSolicitudprogramacionListOldArchivoSolicitudprogramacion : archivoSolicitudprogramacionListOld) {
                if (!archivoSolicitudprogramacionListNew.contains(archivoSolicitudprogramacionListOldArchivoSolicitudprogramacion)) {
                    archivoSolicitudprogramacionListOldArchivoSolicitudprogramacion.setIdSolicitudprogramacion(null);
                    archivoSolicitudprogramacionListOldArchivoSolicitudprogramacion = em.merge(archivoSolicitudprogramacionListOldArchivoSolicitudprogramacion);
                }
            }
            for (ArchivoSolicitudprogramacion archivoSolicitudprogramacionListNewArchivoSolicitudprogramacion : archivoSolicitudprogramacionListNew) {
                if (!archivoSolicitudprogramacionListOld.contains(archivoSolicitudprogramacionListNewArchivoSolicitudprogramacion)) {
                    SolicitudProgramacion oldIdSolicitudprogramacionOfArchivoSolicitudprogramacionListNewArchivoSolicitudprogramacion = archivoSolicitudprogramacionListNewArchivoSolicitudprogramacion.getIdSolicitudprogramacion();
                    archivoSolicitudprogramacionListNewArchivoSolicitudprogramacion.setIdSolicitudprogramacion(solicitudProgramacion);
                    archivoSolicitudprogramacionListNewArchivoSolicitudprogramacion = em.merge(archivoSolicitudprogramacionListNewArchivoSolicitudprogramacion);
                    if (oldIdSolicitudprogramacionOfArchivoSolicitudprogramacionListNewArchivoSolicitudprogramacion != null && !oldIdSolicitudprogramacionOfArchivoSolicitudprogramacionListNewArchivoSolicitudprogramacion.equals(solicitudProgramacion)) {
                        oldIdSolicitudprogramacionOfArchivoSolicitudprogramacionListNewArchivoSolicitudprogramacion.getArchivoSolicitudprogramacionList().remove(archivoSolicitudprogramacionListNewArchivoSolicitudprogramacion);
                        oldIdSolicitudprogramacionOfArchivoSolicitudprogramacionListNewArchivoSolicitudprogramacion = em.merge(oldIdSolicitudprogramacionOfArchivoSolicitudprogramacionListNewArchivoSolicitudprogramacion);
                    }
                }
            }
            for (Programacion programacionListOldProgramacion : programacionListOld) {
                if (!programacionListNew.contains(programacionListOldProgramacion)) {
                    programacionListOldProgramacion.setIdSolicitudprogramacion(null);
                    programacionListOldProgramacion = em.merge(programacionListOldProgramacion);
                }
            }
            for (Programacion programacionListNewProgramacion : programacionListNew) {
                if (!programacionListOld.contains(programacionListNewProgramacion)) {
                    SolicitudProgramacion oldIdSolicitudprogramacionOfProgramacionListNewProgramacion = programacionListNewProgramacion.getIdSolicitudprogramacion();
                    programacionListNewProgramacion.setIdSolicitudprogramacion(solicitudProgramacion);
                    programacionListNewProgramacion = em.merge(programacionListNewProgramacion);
                    if (oldIdSolicitudprogramacionOfProgramacionListNewProgramacion != null && !oldIdSolicitudprogramacionOfProgramacionListNewProgramacion.equals(solicitudProgramacion)) {
                        oldIdSolicitudprogramacionOfProgramacionListNewProgramacion.getProgramacionList().remove(programacionListNewProgramacion);
                        oldIdSolicitudprogramacionOfProgramacionListNewProgramacion = em.merge(oldIdSolicitudprogramacionOfProgramacionListNewProgramacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = solicitudProgramacion.getIdSolicitudprogramacion();
                if (findSolicitudProgramacion(id) == null) {
                    throw new NonexistentEntityException("The solicitudProgramacion with id " + id + " no longer exists.");
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
            SolicitudProgramacion solicitudProgramacion;
            try {
                solicitudProgramacion = em.getReference(SolicitudProgramacion.class, id);
                solicitudProgramacion.getIdSolicitudprogramacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The solicitudProgramacion with id " + id + " no longer exists.", enfe);
            }
            Alumno matricula = solicitudProgramacion.getMatricula();
            if (matricula != null) {
                matricula.getSolicitudProgramacionList().remove(solicitudProgramacion);
                matricula = em.merge(matricula);
            }
            List<ArchivoSolicitudprogramacion> archivoSolicitudprogramacionList = solicitudProgramacion.getArchivoSolicitudprogramacionList();
            for (ArchivoSolicitudprogramacion archivoSolicitudprogramacionListArchivoSolicitudprogramacion : archivoSolicitudprogramacionList) {
                archivoSolicitudprogramacionListArchivoSolicitudprogramacion.setIdSolicitudprogramacion(null);
                archivoSolicitudprogramacionListArchivoSolicitudprogramacion = em.merge(archivoSolicitudprogramacionListArchivoSolicitudprogramacion);
            }
            List<Programacion> programacionList = solicitudProgramacion.getProgramacionList();
            for (Programacion programacionListProgramacion : programacionList) {
                programacionListProgramacion.setIdSolicitudprogramacion(null);
                programacionListProgramacion = em.merge(programacionListProgramacion);
            }
            em.remove(solicitudProgramacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SolicitudProgramacion> findSolicitudProgramacionEntities() {
        return findSolicitudProgramacionEntities(true, -1, -1);
    }

    public List<SolicitudProgramacion> findSolicitudProgramacionEntities(int maxResults, int firstResult) {
        return findSolicitudProgramacionEntities(false, maxResults, firstResult);
    }

    private List<SolicitudProgramacion> findSolicitudProgramacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from SolicitudProgramacion as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public SolicitudProgramacion findSolicitudProgramacion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SolicitudProgramacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getSolicitudProgramacionCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from SolicitudProgramacion as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
