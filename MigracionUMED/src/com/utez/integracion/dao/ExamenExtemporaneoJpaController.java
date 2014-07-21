/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.entity.ExamenExtemporaneo;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.TipoExamen;
import com.utez.secretariaAcademica.entity.Asignatura;
import com.utez.secretariaAcademica.entity.Asesor;
import com.utez.secretariaAcademica.entity.Alumno;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class ExamenExtemporaneoJpaController implements Serializable {

    public ExamenExtemporaneoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ExamenExtemporaneo examenExtemporaneo) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoExamen idTipoexamen = examenExtemporaneo.getIdTipoexamen();
            if (idTipoexamen != null) {
                idTipoexamen = em.getReference(idTipoexamen.getClass(), idTipoexamen.getIdTipoexamen());
                examenExtemporaneo.setIdTipoexamen(idTipoexamen);
            }
            Asignatura idAsignatura = examenExtemporaneo.getIdAsignatura();
            if (idAsignatura != null) {
                idAsignatura = em.getReference(idAsignatura.getClass(), idAsignatura.getIdasignatura());
                examenExtemporaneo.setIdAsignatura(idAsignatura);
            }
            Asesor idAsesorcalificador = examenExtemporaneo.getIdAsesorcalificador();
            if (idAsesorcalificador != null) {
                idAsesorcalificador = em.getReference(idAsesorcalificador.getClass(), idAsesorcalificador.getIdasesor());
                examenExtemporaneo.setIdAsesorcalificador(idAsesorcalificador);
            }
            Alumno matricula = examenExtemporaneo.getMatricula();
            if (matricula != null) {
                matricula = em.getReference(matricula.getClass(), matricula.getMatricula());
                examenExtemporaneo.setMatricula(matricula);
            }
            em.persist(examenExtemporaneo);
            if (idTipoexamen != null) {
                idTipoexamen.getExamenExtemporaneoList().add(examenExtemporaneo);
                idTipoexamen = em.merge(idTipoexamen);
            }
            if (idAsignatura != null) {
                idAsignatura.getExamenExtemporaneoList().add(examenExtemporaneo);
                idAsignatura = em.merge(idAsignatura);
            }
            if (idAsesorcalificador != null) {
                idAsesorcalificador.getExamenExtemporaneoList().add(examenExtemporaneo);
                idAsesorcalificador = em.merge(idAsesorcalificador);
            }
            if (matricula != null) {
                matricula.getExamenExtemporaneoList().add(examenExtemporaneo);
                matricula = em.merge(matricula);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ExamenExtemporaneo examenExtemporaneo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ExamenExtemporaneo persistentExamenExtemporaneo = em.find(ExamenExtemporaneo.class, examenExtemporaneo.getIdExamenextemporaneo());
            TipoExamen idTipoexamenOld = persistentExamenExtemporaneo.getIdTipoexamen();
            TipoExamen idTipoexamenNew = examenExtemporaneo.getIdTipoexamen();
            Asignatura idAsignaturaOld = persistentExamenExtemporaneo.getIdAsignatura();
            Asignatura idAsignaturaNew = examenExtemporaneo.getIdAsignatura();
            Asesor idAsesorcalificadorOld = persistentExamenExtemporaneo.getIdAsesorcalificador();
            Asesor idAsesorcalificadorNew = examenExtemporaneo.getIdAsesorcalificador();
            Alumno matriculaOld = persistentExamenExtemporaneo.getMatricula();
            Alumno matriculaNew = examenExtemporaneo.getMatricula();
            if (idTipoexamenNew != null) {
                idTipoexamenNew = em.getReference(idTipoexamenNew.getClass(), idTipoexamenNew.getIdTipoexamen());
                examenExtemporaneo.setIdTipoexamen(idTipoexamenNew);
            }
            if (idAsignaturaNew != null) {
                idAsignaturaNew = em.getReference(idAsignaturaNew.getClass(), idAsignaturaNew.getIdasignatura());
                examenExtemporaneo.setIdAsignatura(idAsignaturaNew);
            }
            if (idAsesorcalificadorNew != null) {
                idAsesorcalificadorNew = em.getReference(idAsesorcalificadorNew.getClass(), idAsesorcalificadorNew.getIdasesor());
                examenExtemporaneo.setIdAsesorcalificador(idAsesorcalificadorNew);
            }
            if (matriculaNew != null) {
                matriculaNew = em.getReference(matriculaNew.getClass(), matriculaNew.getMatricula());
                examenExtemporaneo.setMatricula(matriculaNew);
            }
            examenExtemporaneo = em.merge(examenExtemporaneo);
            if (idTipoexamenOld != null && !idTipoexamenOld.equals(idTipoexamenNew)) {
                idTipoexamenOld.getExamenExtemporaneoList().remove(examenExtemporaneo);
                idTipoexamenOld = em.merge(idTipoexamenOld);
            }
            if (idTipoexamenNew != null && !idTipoexamenNew.equals(idTipoexamenOld)) {
                idTipoexamenNew.getExamenExtemporaneoList().add(examenExtemporaneo);
                idTipoexamenNew = em.merge(idTipoexamenNew);
            }
            if (idAsignaturaOld != null && !idAsignaturaOld.equals(idAsignaturaNew)) {
                idAsignaturaOld.getExamenExtemporaneoList().remove(examenExtemporaneo);
                idAsignaturaOld = em.merge(idAsignaturaOld);
            }
            if (idAsignaturaNew != null && !idAsignaturaNew.equals(idAsignaturaOld)) {
                idAsignaturaNew.getExamenExtemporaneoList().add(examenExtemporaneo);
                idAsignaturaNew = em.merge(idAsignaturaNew);
            }
            if (idAsesorcalificadorOld != null && !idAsesorcalificadorOld.equals(idAsesorcalificadorNew)) {
                idAsesorcalificadorOld.getExamenExtemporaneoList().remove(examenExtemporaneo);
                idAsesorcalificadorOld = em.merge(idAsesorcalificadorOld);
            }
            if (idAsesorcalificadorNew != null && !idAsesorcalificadorNew.equals(idAsesorcalificadorOld)) {
                idAsesorcalificadorNew.getExamenExtemporaneoList().add(examenExtemporaneo);
                idAsesorcalificadorNew = em.merge(idAsesorcalificadorNew);
            }
            if (matriculaOld != null && !matriculaOld.equals(matriculaNew)) {
                matriculaOld.getExamenExtemporaneoList().remove(examenExtemporaneo);
                matriculaOld = em.merge(matriculaOld);
            }
            if (matriculaNew != null && !matriculaNew.equals(matriculaOld)) {
                matriculaNew.getExamenExtemporaneoList().add(examenExtemporaneo);
                matriculaNew = em.merge(matriculaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = examenExtemporaneo.getIdExamenextemporaneo();
                if (findExamenExtemporaneo(id) == null) {
                    throw new NonexistentEntityException("The examenExtemporaneo with id " + id + " no longer exists.");
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
            ExamenExtemporaneo examenExtemporaneo;
            try {
                examenExtemporaneo = em.getReference(ExamenExtemporaneo.class, id);
                examenExtemporaneo.getIdExamenextemporaneo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The examenExtemporaneo with id " + id + " no longer exists.", enfe);
            }
            TipoExamen idTipoexamen = examenExtemporaneo.getIdTipoexamen();
            if (idTipoexamen != null) {
                idTipoexamen.getExamenExtemporaneoList().remove(examenExtemporaneo);
                idTipoexamen = em.merge(idTipoexamen);
            }
            Asignatura idAsignatura = examenExtemporaneo.getIdAsignatura();
            if (idAsignatura != null) {
                idAsignatura.getExamenExtemporaneoList().remove(examenExtemporaneo);
                idAsignatura = em.merge(idAsignatura);
            }
            Asesor idAsesorcalificador = examenExtemporaneo.getIdAsesorcalificador();
            if (idAsesorcalificador != null) {
                idAsesorcalificador.getExamenExtemporaneoList().remove(examenExtemporaneo);
                idAsesorcalificador = em.merge(idAsesorcalificador);
            }
            Alumno matricula = examenExtemporaneo.getMatricula();
            if (matricula != null) {
                matricula.getExamenExtemporaneoList().remove(examenExtemporaneo);
                matricula = em.merge(matricula);
            }
            em.remove(examenExtemporaneo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ExamenExtemporaneo> findExamenExtemporaneoEntities() {
        return findExamenExtemporaneoEntities(true, -1, -1);
    }

    public List<ExamenExtemporaneo> findExamenExtemporaneoEntities(int maxResults, int firstResult) {
        return findExamenExtemporaneoEntities(false, maxResults, firstResult);
    }

    private List<ExamenExtemporaneo> findExamenExtemporaneoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from ExamenExtemporaneo as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public ExamenExtemporaneo findExamenExtemporaneo(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ExamenExtemporaneo.class, id);
        } finally {
            em.close();
        }
    }

    public int getExamenExtemporaneoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from ExamenExtemporaneo as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
