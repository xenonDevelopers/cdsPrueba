/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.entity.AlumnoAsignatura;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.secretariaAcademica.entity.Asignatura;
import com.utez.secretariaAcademica.entity.Alumno;
import com.utez.integracion.entity.EsquemaAlumnoasignatura;
import java.util.ArrayList;
import java.util.List;
import com.utez.integracion.entity.ExamenIndividual;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class AlumnoAsignaturaJpaController implements Serializable {

    public AlumnoAsignaturaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AlumnoAsignatura alumnoAsignatura) {
        if (alumnoAsignatura.getEsquemaAlumnoasignaturaList() == null) {
            alumnoAsignatura.setEsquemaAlumnoasignaturaList(new ArrayList<EsquemaAlumnoasignatura>());
        }
        if (alumnoAsignatura.getExamenIndividualList() == null) {
            alumnoAsignatura.setExamenIndividualList(new ArrayList<ExamenIndividual>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Asignatura idAsignatura = alumnoAsignatura.getIdAsignatura();
            if (idAsignatura != null) {
                idAsignatura = em.getReference(idAsignatura.getClass(), idAsignatura.getIdasignatura());
                alumnoAsignatura.setIdAsignatura(idAsignatura);
            }
            Alumno matricula = alumnoAsignatura.getMatricula();
            if (matricula != null) {
                matricula = em.getReference(matricula.getClass(), matricula.getMatricula());
                alumnoAsignatura.setMatricula(matricula);
            }
            List<EsquemaAlumnoasignatura> attachedEsquemaAlumnoasignaturaList = new ArrayList<EsquemaAlumnoasignatura>();
            for (EsquemaAlumnoasignatura esquemaAlumnoasignaturaListEsquemaAlumnoasignaturaToAttach : alumnoAsignatura.getEsquemaAlumnoasignaturaList()) {
                esquemaAlumnoasignaturaListEsquemaAlumnoasignaturaToAttach = em.getReference(esquemaAlumnoasignaturaListEsquemaAlumnoasignaturaToAttach.getClass(), esquemaAlumnoasignaturaListEsquemaAlumnoasignaturaToAttach.getIdEsquemaalumnoasignatura());
                attachedEsquemaAlumnoasignaturaList.add(esquemaAlumnoasignaturaListEsquemaAlumnoasignaturaToAttach);
            }
            alumnoAsignatura.setEsquemaAlumnoasignaturaList(attachedEsquemaAlumnoasignaturaList);
            List<ExamenIndividual> attachedExamenIndividualList = new ArrayList<ExamenIndividual>();
            for (ExamenIndividual examenIndividualListExamenIndividualToAttach : alumnoAsignatura.getExamenIndividualList()) {
                examenIndividualListExamenIndividualToAttach = em.getReference(examenIndividualListExamenIndividualToAttach.getClass(), examenIndividualListExamenIndividualToAttach.getIdExamenindividual());
                attachedExamenIndividualList.add(examenIndividualListExamenIndividualToAttach);
            }
            alumnoAsignatura.setExamenIndividualList(attachedExamenIndividualList);
            em.persist(alumnoAsignatura);
            if (idAsignatura != null) {
                idAsignatura.getAlumnoAsignaturaList().add(alumnoAsignatura);
                idAsignatura = em.merge(idAsignatura);
            }
            if (matricula != null) {
                matricula.getAlumnoAsignaturaList().add(alumnoAsignatura);
                matricula = em.merge(matricula);
            }
            for (EsquemaAlumnoasignatura esquemaAlumnoasignaturaListEsquemaAlumnoasignatura : alumnoAsignatura.getEsquemaAlumnoasignaturaList()) {
                AlumnoAsignatura oldIdAlumnoasignaturaOfEsquemaAlumnoasignaturaListEsquemaAlumnoasignatura = esquemaAlumnoasignaturaListEsquemaAlumnoasignatura.getIdAlumnoasignatura();
                esquemaAlumnoasignaturaListEsquemaAlumnoasignatura.setIdAlumnoasignatura(alumnoAsignatura);
                esquemaAlumnoasignaturaListEsquemaAlumnoasignatura = em.merge(esquemaAlumnoasignaturaListEsquemaAlumnoasignatura);
                if (oldIdAlumnoasignaturaOfEsquemaAlumnoasignaturaListEsquemaAlumnoasignatura != null) {
                    oldIdAlumnoasignaturaOfEsquemaAlumnoasignaturaListEsquemaAlumnoasignatura.getEsquemaAlumnoasignaturaList().remove(esquemaAlumnoasignaturaListEsquemaAlumnoasignatura);
                    oldIdAlumnoasignaturaOfEsquemaAlumnoasignaturaListEsquemaAlumnoasignatura = em.merge(oldIdAlumnoasignaturaOfEsquemaAlumnoasignaturaListEsquemaAlumnoasignatura);
                }
            }
            for (ExamenIndividual examenIndividualListExamenIndividual : alumnoAsignatura.getExamenIndividualList()) {
                AlumnoAsignatura oldIdAlumnoasignaturaOfExamenIndividualListExamenIndividual = examenIndividualListExamenIndividual.getIdAlumnoasignatura();
                examenIndividualListExamenIndividual.setIdAlumnoasignatura(alumnoAsignatura);
                examenIndividualListExamenIndividual = em.merge(examenIndividualListExamenIndividual);
                if (oldIdAlumnoasignaturaOfExamenIndividualListExamenIndividual != null) {
                    oldIdAlumnoasignaturaOfExamenIndividualListExamenIndividual.getExamenIndividualList().remove(examenIndividualListExamenIndividual);
                    oldIdAlumnoasignaturaOfExamenIndividualListExamenIndividual = em.merge(oldIdAlumnoasignaturaOfExamenIndividualListExamenIndividual);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AlumnoAsignatura alumnoAsignatura) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AlumnoAsignatura persistentAlumnoAsignatura = em.find(AlumnoAsignatura.class, alumnoAsignatura.getIdAlumnoasignatura());
            Asignatura idAsignaturaOld = persistentAlumnoAsignatura.getIdAsignatura();
            Asignatura idAsignaturaNew = alumnoAsignatura.getIdAsignatura();
            Alumno matriculaOld = persistentAlumnoAsignatura.getMatricula();
            Alumno matriculaNew = alumnoAsignatura.getMatricula();
            List<EsquemaAlumnoasignatura> esquemaAlumnoasignaturaListOld = persistentAlumnoAsignatura.getEsquemaAlumnoasignaturaList();
            List<EsquemaAlumnoasignatura> esquemaAlumnoasignaturaListNew = alumnoAsignatura.getEsquemaAlumnoasignaturaList();
            List<ExamenIndividual> examenIndividualListOld = persistentAlumnoAsignatura.getExamenIndividualList();
            List<ExamenIndividual> examenIndividualListNew = alumnoAsignatura.getExamenIndividualList();
            if (idAsignaturaNew != null) {
                idAsignaturaNew = em.getReference(idAsignaturaNew.getClass(), idAsignaturaNew.getIdasignatura());
                alumnoAsignatura.setIdAsignatura(idAsignaturaNew);
            }
            if (matriculaNew != null) {
                matriculaNew = em.getReference(matriculaNew.getClass(), matriculaNew.getMatricula());
                alumnoAsignatura.setMatricula(matriculaNew);
            }
            List<EsquemaAlumnoasignatura> attachedEsquemaAlumnoasignaturaListNew = new ArrayList<EsquemaAlumnoasignatura>();
            for (EsquemaAlumnoasignatura esquemaAlumnoasignaturaListNewEsquemaAlumnoasignaturaToAttach : esquemaAlumnoasignaturaListNew) {
                esquemaAlumnoasignaturaListNewEsquemaAlumnoasignaturaToAttach = em.getReference(esquemaAlumnoasignaturaListNewEsquemaAlumnoasignaturaToAttach.getClass(), esquemaAlumnoasignaturaListNewEsquemaAlumnoasignaturaToAttach.getIdEsquemaalumnoasignatura());
                attachedEsquemaAlumnoasignaturaListNew.add(esquemaAlumnoasignaturaListNewEsquemaAlumnoasignaturaToAttach);
            }
            esquemaAlumnoasignaturaListNew = attachedEsquemaAlumnoasignaturaListNew;
            alumnoAsignatura.setEsquemaAlumnoasignaturaList(esquemaAlumnoasignaturaListNew);
            List<ExamenIndividual> attachedExamenIndividualListNew = new ArrayList<ExamenIndividual>();
            for (ExamenIndividual examenIndividualListNewExamenIndividualToAttach : examenIndividualListNew) {
                examenIndividualListNewExamenIndividualToAttach = em.getReference(examenIndividualListNewExamenIndividualToAttach.getClass(), examenIndividualListNewExamenIndividualToAttach.getIdExamenindividual());
                attachedExamenIndividualListNew.add(examenIndividualListNewExamenIndividualToAttach);
            }
            examenIndividualListNew = attachedExamenIndividualListNew;
            alumnoAsignatura.setExamenIndividualList(examenIndividualListNew);
            alumnoAsignatura = em.merge(alumnoAsignatura);
            if (idAsignaturaOld != null && !idAsignaturaOld.equals(idAsignaturaNew)) {
                idAsignaturaOld.getAlumnoAsignaturaList().remove(alumnoAsignatura);
                idAsignaturaOld = em.merge(idAsignaturaOld);
            }
            if (idAsignaturaNew != null && !idAsignaturaNew.equals(idAsignaturaOld)) {
                idAsignaturaNew.getAlumnoAsignaturaList().add(alumnoAsignatura);
                idAsignaturaNew = em.merge(idAsignaturaNew);
            }
            if (matriculaOld != null && !matriculaOld.equals(matriculaNew)) {
                matriculaOld.getAlumnoAsignaturaList().remove(alumnoAsignatura);
                matriculaOld = em.merge(matriculaOld);
            }
            if (matriculaNew != null && !matriculaNew.equals(matriculaOld)) {
                matriculaNew.getAlumnoAsignaturaList().add(alumnoAsignatura);
                matriculaNew = em.merge(matriculaNew);
            }
            for (EsquemaAlumnoasignatura esquemaAlumnoasignaturaListOldEsquemaAlumnoasignatura : esquemaAlumnoasignaturaListOld) {
                if (!esquemaAlumnoasignaturaListNew.contains(esquemaAlumnoasignaturaListOldEsquemaAlumnoasignatura)) {
                    esquemaAlumnoasignaturaListOldEsquemaAlumnoasignatura.setIdAlumnoasignatura(null);
                    esquemaAlumnoasignaturaListOldEsquemaAlumnoasignatura = em.merge(esquemaAlumnoasignaturaListOldEsquemaAlumnoasignatura);
                }
            }
            for (EsquemaAlumnoasignatura esquemaAlumnoasignaturaListNewEsquemaAlumnoasignatura : esquemaAlumnoasignaturaListNew) {
                if (!esquemaAlumnoasignaturaListOld.contains(esquemaAlumnoasignaturaListNewEsquemaAlumnoasignatura)) {
                    AlumnoAsignatura oldIdAlumnoasignaturaOfEsquemaAlumnoasignaturaListNewEsquemaAlumnoasignatura = esquemaAlumnoasignaturaListNewEsquemaAlumnoasignatura.getIdAlumnoasignatura();
                    esquemaAlumnoasignaturaListNewEsquemaAlumnoasignatura.setIdAlumnoasignatura(alumnoAsignatura);
                    esquemaAlumnoasignaturaListNewEsquemaAlumnoasignatura = em.merge(esquemaAlumnoasignaturaListNewEsquemaAlumnoasignatura);
                    if (oldIdAlumnoasignaturaOfEsquemaAlumnoasignaturaListNewEsquemaAlumnoasignatura != null && !oldIdAlumnoasignaturaOfEsquemaAlumnoasignaturaListNewEsquemaAlumnoasignatura.equals(alumnoAsignatura)) {
                        oldIdAlumnoasignaturaOfEsquemaAlumnoasignaturaListNewEsquemaAlumnoasignatura.getEsquemaAlumnoasignaturaList().remove(esquemaAlumnoasignaturaListNewEsquemaAlumnoasignatura);
                        oldIdAlumnoasignaturaOfEsquemaAlumnoasignaturaListNewEsquemaAlumnoasignatura = em.merge(oldIdAlumnoasignaturaOfEsquemaAlumnoasignaturaListNewEsquemaAlumnoasignatura);
                    }
                }
            }
            for (ExamenIndividual examenIndividualListOldExamenIndividual : examenIndividualListOld) {
                if (!examenIndividualListNew.contains(examenIndividualListOldExamenIndividual)) {
                    examenIndividualListOldExamenIndividual.setIdAlumnoasignatura(null);
                    examenIndividualListOldExamenIndividual = em.merge(examenIndividualListOldExamenIndividual);
                }
            }
            for (ExamenIndividual examenIndividualListNewExamenIndividual : examenIndividualListNew) {
                if (!examenIndividualListOld.contains(examenIndividualListNewExamenIndividual)) {
                    AlumnoAsignatura oldIdAlumnoasignaturaOfExamenIndividualListNewExamenIndividual = examenIndividualListNewExamenIndividual.getIdAlumnoasignatura();
                    examenIndividualListNewExamenIndividual.setIdAlumnoasignatura(alumnoAsignatura);
                    examenIndividualListNewExamenIndividual = em.merge(examenIndividualListNewExamenIndividual);
                    if (oldIdAlumnoasignaturaOfExamenIndividualListNewExamenIndividual != null && !oldIdAlumnoasignaturaOfExamenIndividualListNewExamenIndividual.equals(alumnoAsignatura)) {
                        oldIdAlumnoasignaturaOfExamenIndividualListNewExamenIndividual.getExamenIndividualList().remove(examenIndividualListNewExamenIndividual);
                        oldIdAlumnoasignaturaOfExamenIndividualListNewExamenIndividual = em.merge(oldIdAlumnoasignaturaOfExamenIndividualListNewExamenIndividual);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = alumnoAsignatura.getIdAlumnoasignatura();
                if (findAlumnoAsignatura(id) == null) {
                    throw new NonexistentEntityException("The alumnoAsignatura with id " + id + " no longer exists.");
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
            AlumnoAsignatura alumnoAsignatura;
            try {
                alumnoAsignatura = em.getReference(AlumnoAsignatura.class, id);
                alumnoAsignatura.getIdAlumnoasignatura();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The alumnoAsignatura with id " + id + " no longer exists.", enfe);
            }
            Asignatura idAsignatura = alumnoAsignatura.getIdAsignatura();
            if (idAsignatura != null) {
                idAsignatura.getAlumnoAsignaturaList().remove(alumnoAsignatura);
                idAsignatura = em.merge(idAsignatura);
            }
            Alumno matricula = alumnoAsignatura.getMatricula();
            if (matricula != null) {
                matricula.getAlumnoAsignaturaList().remove(alumnoAsignatura);
                matricula = em.merge(matricula);
            }
            List<EsquemaAlumnoasignatura> esquemaAlumnoasignaturaList = alumnoAsignatura.getEsquemaAlumnoasignaturaList();
            for (EsquemaAlumnoasignatura esquemaAlumnoasignaturaListEsquemaAlumnoasignatura : esquemaAlumnoasignaturaList) {
                esquemaAlumnoasignaturaListEsquemaAlumnoasignatura.setIdAlumnoasignatura(null);
                esquemaAlumnoasignaturaListEsquemaAlumnoasignatura = em.merge(esquemaAlumnoasignaturaListEsquemaAlumnoasignatura);
            }
            List<ExamenIndividual> examenIndividualList = alumnoAsignatura.getExamenIndividualList();
            for (ExamenIndividual examenIndividualListExamenIndividual : examenIndividualList) {
                examenIndividualListExamenIndividual.setIdAlumnoasignatura(null);
                examenIndividualListExamenIndividual = em.merge(examenIndividualListExamenIndividual);
            }
            em.remove(alumnoAsignatura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AlumnoAsignatura> findAlumnoAsignaturaEntities() {
        return findAlumnoAsignaturaEntities(true, -1, -1);
    }

    public List<AlumnoAsignatura> findAlumnoAsignaturaEntities(int maxResults, int firstResult) {
        return findAlumnoAsignaturaEntities(false, maxResults, firstResult);
    }

    private List<AlumnoAsignatura> findAlumnoAsignaturaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from AlumnoAsignatura as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public AlumnoAsignatura findAlumnoAsignatura(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AlumnoAsignatura.class, id);
        } finally {
            em.close();
        }
    }

    public int getAlumnoAsignaturaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from AlumnoAsignatura as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
