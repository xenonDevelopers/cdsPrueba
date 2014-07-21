/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.EsquemaAlumnoasignatura;
import java.util.ArrayList;
import java.util.List;
import com.utez.integracion.entity.Programacion;
import com.utez.integracion.entity.TipoProgramacion;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class TipoProgramacionJpaController implements Serializable {

    public TipoProgramacionJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoProgramacion tipoProgramacion) {
        if (tipoProgramacion.getEsquemaAlumnoasignaturaList() == null) {
            tipoProgramacion.setEsquemaAlumnoasignaturaList(new ArrayList<EsquemaAlumnoasignatura>());
        }
        if (tipoProgramacion.getProgramacionList() == null) {
            tipoProgramacion.setProgramacionList(new ArrayList<Programacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<EsquemaAlumnoasignatura> attachedEsquemaAlumnoasignaturaList = new ArrayList<EsquemaAlumnoasignatura>();
            for (EsquemaAlumnoasignatura esquemaAlumnoasignaturaListEsquemaAlumnoasignaturaToAttach : tipoProgramacion.getEsquemaAlumnoasignaturaList()) {
                esquemaAlumnoasignaturaListEsquemaAlumnoasignaturaToAttach = em.getReference(esquemaAlumnoasignaturaListEsquemaAlumnoasignaturaToAttach.getClass(), esquemaAlumnoasignaturaListEsquemaAlumnoasignaturaToAttach.getIdEsquemaalumnoasignatura());
                attachedEsquemaAlumnoasignaturaList.add(esquemaAlumnoasignaturaListEsquemaAlumnoasignaturaToAttach);
            }
            tipoProgramacion.setEsquemaAlumnoasignaturaList(attachedEsquemaAlumnoasignaturaList);
            List<Programacion> attachedProgramacionList = new ArrayList<Programacion>();
            for (Programacion programacionListProgramacionToAttach : tipoProgramacion.getProgramacionList()) {
                programacionListProgramacionToAttach = em.getReference(programacionListProgramacionToAttach.getClass(), programacionListProgramacionToAttach.getIdProgramacion());
                attachedProgramacionList.add(programacionListProgramacionToAttach);
            }
            tipoProgramacion.setProgramacionList(attachedProgramacionList);
            em.persist(tipoProgramacion);
            for (EsquemaAlumnoasignatura esquemaAlumnoasignaturaListEsquemaAlumnoasignatura : tipoProgramacion.getEsquemaAlumnoasignaturaList()) {
                TipoProgramacion oldEsquemaOfEsquemaAlumnoasignaturaListEsquemaAlumnoasignatura = esquemaAlumnoasignaturaListEsquemaAlumnoasignatura.getEsquema();
                esquemaAlumnoasignaturaListEsquemaAlumnoasignatura.setEsquema(tipoProgramacion);
                esquemaAlumnoasignaturaListEsquemaAlumnoasignatura = em.merge(esquemaAlumnoasignaturaListEsquemaAlumnoasignatura);
                if (oldEsquemaOfEsquemaAlumnoasignaturaListEsquemaAlumnoasignatura != null) {
                    oldEsquemaOfEsquemaAlumnoasignaturaListEsquemaAlumnoasignatura.getEsquemaAlumnoasignaturaList().remove(esquemaAlumnoasignaturaListEsquemaAlumnoasignatura);
                    oldEsquemaOfEsquemaAlumnoasignaturaListEsquemaAlumnoasignatura = em.merge(oldEsquemaOfEsquemaAlumnoasignaturaListEsquemaAlumnoasignatura);
                }
            }
            for (Programacion programacionListProgramacion : tipoProgramacion.getProgramacionList()) {
                TipoProgramacion oldIdTipoprogramacionOfProgramacionListProgramacion = programacionListProgramacion.getIdTipoprogramacion();
                programacionListProgramacion.setIdTipoprogramacion(tipoProgramacion);
                programacionListProgramacion = em.merge(programacionListProgramacion);
                if (oldIdTipoprogramacionOfProgramacionListProgramacion != null) {
                    oldIdTipoprogramacionOfProgramacionListProgramacion.getProgramacionList().remove(programacionListProgramacion);
                    oldIdTipoprogramacionOfProgramacionListProgramacion = em.merge(oldIdTipoprogramacionOfProgramacionListProgramacion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoProgramacion tipoProgramacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoProgramacion persistentTipoProgramacion = em.find(TipoProgramacion.class, tipoProgramacion.getIdTipoprogramacion());
            List<EsquemaAlumnoasignatura> esquemaAlumnoasignaturaListOld = persistentTipoProgramacion.getEsquemaAlumnoasignaturaList();
            List<EsquemaAlumnoasignatura> esquemaAlumnoasignaturaListNew = tipoProgramacion.getEsquemaAlumnoasignaturaList();
            List<Programacion> programacionListOld = persistentTipoProgramacion.getProgramacionList();
            List<Programacion> programacionListNew = tipoProgramacion.getProgramacionList();
            List<EsquemaAlumnoasignatura> attachedEsquemaAlumnoasignaturaListNew = new ArrayList<EsquemaAlumnoasignatura>();
            for (EsquemaAlumnoasignatura esquemaAlumnoasignaturaListNewEsquemaAlumnoasignaturaToAttach : esquemaAlumnoasignaturaListNew) {
                esquemaAlumnoasignaturaListNewEsquemaAlumnoasignaturaToAttach = em.getReference(esquemaAlumnoasignaturaListNewEsquemaAlumnoasignaturaToAttach.getClass(), esquemaAlumnoasignaturaListNewEsquemaAlumnoasignaturaToAttach.getIdEsquemaalumnoasignatura());
                attachedEsquemaAlumnoasignaturaListNew.add(esquemaAlumnoasignaturaListNewEsquemaAlumnoasignaturaToAttach);
            }
            esquemaAlumnoasignaturaListNew = attachedEsquemaAlumnoasignaturaListNew;
            tipoProgramacion.setEsquemaAlumnoasignaturaList(esquemaAlumnoasignaturaListNew);
            List<Programacion> attachedProgramacionListNew = new ArrayList<Programacion>();
            for (Programacion programacionListNewProgramacionToAttach : programacionListNew) {
                programacionListNewProgramacionToAttach = em.getReference(programacionListNewProgramacionToAttach.getClass(), programacionListNewProgramacionToAttach.getIdProgramacion());
                attachedProgramacionListNew.add(programacionListNewProgramacionToAttach);
            }
            programacionListNew = attachedProgramacionListNew;
            tipoProgramacion.setProgramacionList(programacionListNew);
            tipoProgramacion = em.merge(tipoProgramacion);
            for (EsquemaAlumnoasignatura esquemaAlumnoasignaturaListOldEsquemaAlumnoasignatura : esquemaAlumnoasignaturaListOld) {
                if (!esquemaAlumnoasignaturaListNew.contains(esquemaAlumnoasignaturaListOldEsquemaAlumnoasignatura)) {
                    esquemaAlumnoasignaturaListOldEsquemaAlumnoasignatura.setEsquema(null);
                    esquemaAlumnoasignaturaListOldEsquemaAlumnoasignatura = em.merge(esquemaAlumnoasignaturaListOldEsquemaAlumnoasignatura);
                }
            }
            for (EsquemaAlumnoasignatura esquemaAlumnoasignaturaListNewEsquemaAlumnoasignatura : esquemaAlumnoasignaturaListNew) {
                if (!esquemaAlumnoasignaturaListOld.contains(esquemaAlumnoasignaturaListNewEsquemaAlumnoasignatura)) {
                    TipoProgramacion oldEsquemaOfEsquemaAlumnoasignaturaListNewEsquemaAlumnoasignatura = esquemaAlumnoasignaturaListNewEsquemaAlumnoasignatura.getEsquema();
                    esquemaAlumnoasignaturaListNewEsquemaAlumnoasignatura.setEsquema(tipoProgramacion);
                    esquemaAlumnoasignaturaListNewEsquemaAlumnoasignatura = em.merge(esquemaAlumnoasignaturaListNewEsquemaAlumnoasignatura);
                    if (oldEsquemaOfEsquemaAlumnoasignaturaListNewEsquemaAlumnoasignatura != null && !oldEsquemaOfEsquemaAlumnoasignaturaListNewEsquemaAlumnoasignatura.equals(tipoProgramacion)) {
                        oldEsquemaOfEsquemaAlumnoasignaturaListNewEsquemaAlumnoasignatura.getEsquemaAlumnoasignaturaList().remove(esquemaAlumnoasignaturaListNewEsquemaAlumnoasignatura);
                        oldEsquemaOfEsquemaAlumnoasignaturaListNewEsquemaAlumnoasignatura = em.merge(oldEsquemaOfEsquemaAlumnoasignaturaListNewEsquemaAlumnoasignatura);
                    }
                }
            }
            for (Programacion programacionListOldProgramacion : programacionListOld) {
                if (!programacionListNew.contains(programacionListOldProgramacion)) {
                    programacionListOldProgramacion.setIdTipoprogramacion(null);
                    programacionListOldProgramacion = em.merge(programacionListOldProgramacion);
                }
            }
            for (Programacion programacionListNewProgramacion : programacionListNew) {
                if (!programacionListOld.contains(programacionListNewProgramacion)) {
                    TipoProgramacion oldIdTipoprogramacionOfProgramacionListNewProgramacion = programacionListNewProgramacion.getIdTipoprogramacion();
                    programacionListNewProgramacion.setIdTipoprogramacion(tipoProgramacion);
                    programacionListNewProgramacion = em.merge(programacionListNewProgramacion);
                    if (oldIdTipoprogramacionOfProgramacionListNewProgramacion != null && !oldIdTipoprogramacionOfProgramacionListNewProgramacion.equals(tipoProgramacion)) {
                        oldIdTipoprogramacionOfProgramacionListNewProgramacion.getProgramacionList().remove(programacionListNewProgramacion);
                        oldIdTipoprogramacionOfProgramacionListNewProgramacion = em.merge(oldIdTipoprogramacionOfProgramacionListNewProgramacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tipoProgramacion.getIdTipoprogramacion();
                if (findTipoProgramacion(id) == null) {
                    throw new NonexistentEntityException("The tipoProgramacion with id " + id + " no longer exists.");
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
            TipoProgramacion tipoProgramacion;
            try {
                tipoProgramacion = em.getReference(TipoProgramacion.class, id);
                tipoProgramacion.getIdTipoprogramacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoProgramacion with id " + id + " no longer exists.", enfe);
            }
            List<EsquemaAlumnoasignatura> esquemaAlumnoasignaturaList = tipoProgramacion.getEsquemaAlumnoasignaturaList();
            for (EsquemaAlumnoasignatura esquemaAlumnoasignaturaListEsquemaAlumnoasignatura : esquemaAlumnoasignaturaList) {
                esquemaAlumnoasignaturaListEsquemaAlumnoasignatura.setEsquema(null);
                esquemaAlumnoasignaturaListEsquemaAlumnoasignatura = em.merge(esquemaAlumnoasignaturaListEsquemaAlumnoasignatura);
            }
            List<Programacion> programacionList = tipoProgramacion.getProgramacionList();
            for (Programacion programacionListProgramacion : programacionList) {
                programacionListProgramacion.setIdTipoprogramacion(null);
                programacionListProgramacion = em.merge(programacionListProgramacion);
            }
            em.remove(tipoProgramacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoProgramacion> findTipoProgramacionEntities() {
        return findTipoProgramacionEntities(true, -1, -1);
    }

    public List<TipoProgramacion> findTipoProgramacionEntities(int maxResults, int firstResult) {
        return findTipoProgramacionEntities(false, maxResults, firstResult);
    }

    private List<TipoProgramacion> findTipoProgramacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from TipoProgramacion as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public TipoProgramacion findTipoProgramacion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoProgramacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoProgramacionCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from TipoProgramacion as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
