/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.IllegalOrphanException;
import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.VigenciaCalificacionindividual;
import com.utez.integracion.entity.TipoExamen;
import com.utez.secretariaAcademica.entity.Asesor;
import com.utez.integracion.entity.AlumnoAsignatura;
import com.utez.integracion.entity.ExamenIndividual;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class ExamenIndividualJpaController implements Serializable {

    public ExamenIndividualJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ExamenIndividual examenIndividual) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            VigenciaCalificacionindividual vigenciaCalificacionindividual = examenIndividual.getVigenciaCalificacionindividual();
            if (vigenciaCalificacionindividual != null) {
                vigenciaCalificacionindividual = em.getReference(vigenciaCalificacionindividual.getClass(), vigenciaCalificacionindividual.getIdExamenindividual());
                examenIndividual.setVigenciaCalificacionindividual(vigenciaCalificacionindividual);
            }
            TipoExamen idTipoexamen = examenIndividual.getIdTipoexamen();
            if (idTipoexamen != null) {
                idTipoexamen = em.getReference(idTipoexamen.getClass(), idTipoexamen.getIdTipoexamen());
                examenIndividual.setIdTipoexamen(idTipoexamen);
            }
            Asesor idAsesor = examenIndividual.getIdAsesor();
            if (idAsesor != null) {
                idAsesor = em.getReference(idAsesor.getClass(), idAsesor.getIdasesor());
                examenIndividual.setIdAsesor(idAsesor);
            }
            AlumnoAsignatura idAlumnoasignatura = examenIndividual.getIdAlumnoasignatura();
            if (idAlumnoasignatura != null) {
                idAlumnoasignatura = em.getReference(idAlumnoasignatura.getClass(), idAlumnoasignatura.getIdAlumnoasignatura());
                examenIndividual.setIdAlumnoasignatura(idAlumnoasignatura);
            }
            em.persist(examenIndividual);
            if (vigenciaCalificacionindividual != null) {
                ExamenIndividual oldExamenIndividualOfVigenciaCalificacionindividual = vigenciaCalificacionindividual.getExamenIndividual();
                if (oldExamenIndividualOfVigenciaCalificacionindividual != null) {
                    oldExamenIndividualOfVigenciaCalificacionindividual.setVigenciaCalificacionindividual(null);
                    oldExamenIndividualOfVigenciaCalificacionindividual = em.merge(oldExamenIndividualOfVigenciaCalificacionindividual);
                }
                vigenciaCalificacionindividual.setExamenIndividual(examenIndividual);
                vigenciaCalificacionindividual = em.merge(vigenciaCalificacionindividual);
            }
            if (idTipoexamen != null) {
                idTipoexamen.getExamenIndividualList().add(examenIndividual);
                idTipoexamen = em.merge(idTipoexamen);
            }
            if (idAsesor != null) {
                idAsesor.getExamenIndividualList().add(examenIndividual);
                idAsesor = em.merge(idAsesor);
            }
            if (idAlumnoasignatura != null) {
                idAlumnoasignatura.getExamenIndividualList().add(examenIndividual);
                idAlumnoasignatura = em.merge(idAlumnoasignatura);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ExamenIndividual examenIndividual) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ExamenIndividual persistentExamenIndividual = em.find(ExamenIndividual.class, examenIndividual.getIdExamenindividual());
            VigenciaCalificacionindividual vigenciaCalificacionindividualOld = persistentExamenIndividual.getVigenciaCalificacionindividual();
            VigenciaCalificacionindividual vigenciaCalificacionindividualNew = examenIndividual.getVigenciaCalificacionindividual();
            TipoExamen idTipoexamenOld = persistentExamenIndividual.getIdTipoexamen();
            TipoExamen idTipoexamenNew = examenIndividual.getIdTipoexamen();
            Asesor idAsesorOld = persistentExamenIndividual.getIdAsesor();
            Asesor idAsesorNew = examenIndividual.getIdAsesor();
            AlumnoAsignatura idAlumnoasignaturaOld = persistentExamenIndividual.getIdAlumnoasignatura();
            AlumnoAsignatura idAlumnoasignaturaNew = examenIndividual.getIdAlumnoasignatura();
            List<String> illegalOrphanMessages = null;
            if (vigenciaCalificacionindividualOld != null && !vigenciaCalificacionindividualOld.equals(vigenciaCalificacionindividualNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain VigenciaCalificacionindividual " + vigenciaCalificacionindividualOld + " since its examenIndividual field is not nullable.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (vigenciaCalificacionindividualNew != null) {
                vigenciaCalificacionindividualNew = em.getReference(vigenciaCalificacionindividualNew.getClass(), vigenciaCalificacionindividualNew.getIdExamenindividual());
                examenIndividual.setVigenciaCalificacionindividual(vigenciaCalificacionindividualNew);
            }
            if (idTipoexamenNew != null) {
                idTipoexamenNew = em.getReference(idTipoexamenNew.getClass(), idTipoexamenNew.getIdTipoexamen());
                examenIndividual.setIdTipoexamen(idTipoexamenNew);
            }
            if (idAsesorNew != null) {
                idAsesorNew = em.getReference(idAsesorNew.getClass(), idAsesorNew.getIdasesor());
                examenIndividual.setIdAsesor(idAsesorNew);
            }
            if (idAlumnoasignaturaNew != null) {
                idAlumnoasignaturaNew = em.getReference(idAlumnoasignaturaNew.getClass(), idAlumnoasignaturaNew.getIdAlumnoasignatura());
                examenIndividual.setIdAlumnoasignatura(idAlumnoasignaturaNew);
            }
            examenIndividual = em.merge(examenIndividual);
            if (vigenciaCalificacionindividualNew != null && !vigenciaCalificacionindividualNew.equals(vigenciaCalificacionindividualOld)) {
                ExamenIndividual oldExamenIndividualOfVigenciaCalificacionindividual = vigenciaCalificacionindividualNew.getExamenIndividual();
                if (oldExamenIndividualOfVigenciaCalificacionindividual != null) {
                    oldExamenIndividualOfVigenciaCalificacionindividual.setVigenciaCalificacionindividual(null);
                    oldExamenIndividualOfVigenciaCalificacionindividual = em.merge(oldExamenIndividualOfVigenciaCalificacionindividual);
                }
                vigenciaCalificacionindividualNew.setExamenIndividual(examenIndividual);
                vigenciaCalificacionindividualNew = em.merge(vigenciaCalificacionindividualNew);
            }
            if (idTipoexamenOld != null && !idTipoexamenOld.equals(idTipoexamenNew)) {
                idTipoexamenOld.getExamenIndividualList().remove(examenIndividual);
                idTipoexamenOld = em.merge(idTipoexamenOld);
            }
            if (idTipoexamenNew != null && !idTipoexamenNew.equals(idTipoexamenOld)) {
                idTipoexamenNew.getExamenIndividualList().add(examenIndividual);
                idTipoexamenNew = em.merge(idTipoexamenNew);
            }
            if (idAsesorOld != null && !idAsesorOld.equals(idAsesorNew)) {
                idAsesorOld.getExamenIndividualList().remove(examenIndividual);
                idAsesorOld = em.merge(idAsesorOld);
            }
            if (idAsesorNew != null && !idAsesorNew.equals(idAsesorOld)) {
                idAsesorNew.getExamenIndividualList().add(examenIndividual);
                idAsesorNew = em.merge(idAsesorNew);
            }
            if (idAlumnoasignaturaOld != null && !idAlumnoasignaturaOld.equals(idAlumnoasignaturaNew)) {
                idAlumnoasignaturaOld.getExamenIndividualList().remove(examenIndividual);
                idAlumnoasignaturaOld = em.merge(idAlumnoasignaturaOld);
            }
            if (idAlumnoasignaturaNew != null && !idAlumnoasignaturaNew.equals(idAlumnoasignaturaOld)) {
                idAlumnoasignaturaNew.getExamenIndividualList().add(examenIndividual);
                idAlumnoasignaturaNew = em.merge(idAlumnoasignaturaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = examenIndividual.getIdExamenindividual();
                if (findExamenIndividual(id) == null) {
                    throw new NonexistentEntityException("The examenIndividual with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ExamenIndividual examenIndividual;
            try {
                examenIndividual = em.getReference(ExamenIndividual.class, id);
                examenIndividual.getIdExamenindividual();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The examenIndividual with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            VigenciaCalificacionindividual vigenciaCalificacionindividualOrphanCheck = examenIndividual.getVigenciaCalificacionindividual();
            if (vigenciaCalificacionindividualOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ExamenIndividual (" + examenIndividual + ") cannot be destroyed since the VigenciaCalificacionindividual " + vigenciaCalificacionindividualOrphanCheck + " in its vigenciaCalificacionindividual field has a non-nullable examenIndividual field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            TipoExamen idTipoexamen = examenIndividual.getIdTipoexamen();
            if (idTipoexamen != null) {
                idTipoexamen.getExamenIndividualList().remove(examenIndividual);
                idTipoexamen = em.merge(idTipoexamen);
            }
            Asesor idAsesor = examenIndividual.getIdAsesor();
            if (idAsesor != null) {
                idAsesor.getExamenIndividualList().remove(examenIndividual);
                idAsesor = em.merge(idAsesor);
            }
            AlumnoAsignatura idAlumnoasignatura = examenIndividual.getIdAlumnoasignatura();
            if (idAlumnoasignatura != null) {
                idAlumnoasignatura.getExamenIndividualList().remove(examenIndividual);
                idAlumnoasignatura = em.merge(idAlumnoasignatura);
            }
            em.remove(examenIndividual);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ExamenIndividual> findExamenIndividualEntities() {
        return findExamenIndividualEntities(true, -1, -1);
    }

    public List<ExamenIndividual> findExamenIndividualEntities(int maxResults, int firstResult) {
        return findExamenIndividualEntities(false, maxResults, firstResult);
    }

    private List<ExamenIndividual> findExamenIndividualEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from ExamenIndividual as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public ExamenIndividual findExamenIndividual(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ExamenIndividual.class, id);
        } finally {
            em.close();
        }
    }

    public int getExamenIndividualCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from ExamenIndividual as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
