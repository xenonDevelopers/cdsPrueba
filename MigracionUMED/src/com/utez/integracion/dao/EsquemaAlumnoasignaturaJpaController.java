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
import com.utez.integracion.entity.TipoProgramacion;
import com.utez.integracion.entity.AlumnoAsignatura;
import com.utez.integracion.entity.GrupoAlumnoesquema;
import com.utez.secretariaAcademica.entity.Asesor;
import java.util.ArrayList;
import java.util.List;
import com.utez.integracion.entity.Calificacion;
import com.utez.integracion.entity.EsquemaAlumnoasignatura;
import com.utez.integracion.entity.HistoricoCalificacion;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class EsquemaAlumnoasignaturaJpaController implements Serializable {

    public EsquemaAlumnoasignaturaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EsquemaAlumnoasignatura esquemaAlumnoasignatura) {
        if (esquemaAlumnoasignatura.getAsesorList() == null) {
            esquemaAlumnoasignatura.setAsesorList(new ArrayList<Asesor>());
        }
        if (esquemaAlumnoasignatura.getCalificacionList() == null) {
            esquemaAlumnoasignatura.setCalificacionList(new ArrayList<Calificacion>());
        }
        if (esquemaAlumnoasignatura.getHistoricoCalificacionList() == null) {
            esquemaAlumnoasignatura.setHistoricoCalificacionList(new ArrayList<HistoricoCalificacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoProgramacion esquema = esquemaAlumnoasignatura.getEsquema();
            if (esquema != null) {
                esquema = em.getReference(esquema.getClass(), esquema.getIdTipoprogramacion());
                esquemaAlumnoasignatura.setEsquema(esquema);
            }
            AlumnoAsignatura idAlumnoasignatura = esquemaAlumnoasignatura.getIdAlumnoasignatura();
            if (idAlumnoasignatura != null) {
                idAlumnoasignatura = em.getReference(idAlumnoasignatura.getClass(), idAlumnoasignatura.getIdAlumnoasignatura());
                esquemaAlumnoasignatura.setIdAlumnoasignatura(idAlumnoasignatura);
            }
            GrupoAlumnoesquema grupoAlumnoesquema = esquemaAlumnoasignatura.getGrupoAlumnoesquema();
            if (grupoAlumnoesquema != null) {
                grupoAlumnoesquema = em.getReference(grupoAlumnoesquema.getClass(), grupoAlumnoesquema.getIdEsquemaalumnoasignatura());
                esquemaAlumnoasignatura.setGrupoAlumnoesquema(grupoAlumnoesquema);
            }
            List<Asesor> attachedAsesorList = new ArrayList<Asesor>();
            for (Asesor asesorListAsesorToAttach : esquemaAlumnoasignatura.getAsesorList()) {
                asesorListAsesorToAttach = em.getReference(asesorListAsesorToAttach.getClass(), asesorListAsesorToAttach.getIdasesor());
                attachedAsesorList.add(asesorListAsesorToAttach);
            }
            esquemaAlumnoasignatura.setAsesorList(attachedAsesorList);
            List<Calificacion> attachedCalificacionList = new ArrayList<Calificacion>();
            for (Calificacion calificacionListCalificacionToAttach : esquemaAlumnoasignatura.getCalificacionList()) {
                calificacionListCalificacionToAttach = em.getReference(calificacionListCalificacionToAttach.getClass(), calificacionListCalificacionToAttach.getCalificacionPK());
                attachedCalificacionList.add(calificacionListCalificacionToAttach);
            }
            esquemaAlumnoasignatura.setCalificacionList(attachedCalificacionList);
            List<HistoricoCalificacion> attachedHistoricoCalificacionList = new ArrayList<HistoricoCalificacion>();
            for (HistoricoCalificacion historicoCalificacionListHistoricoCalificacionToAttach : esquemaAlumnoasignatura.getHistoricoCalificacionList()) {
                historicoCalificacionListHistoricoCalificacionToAttach = em.getReference(historicoCalificacionListHistoricoCalificacionToAttach.getClass(), historicoCalificacionListHistoricoCalificacionToAttach.getIdHistoricocalificacion());
                attachedHistoricoCalificacionList.add(historicoCalificacionListHistoricoCalificacionToAttach);
            }
            esquemaAlumnoasignatura.setHistoricoCalificacionList(attachedHistoricoCalificacionList);
            em.persist(esquemaAlumnoasignatura);
            if (esquema != null) {
                esquema.getEsquemaAlumnoasignaturaList().add(esquemaAlumnoasignatura);
                esquema = em.merge(esquema);
            }
            if (idAlumnoasignatura != null) {
                idAlumnoasignatura.getEsquemaAlumnoasignaturaList().add(esquemaAlumnoasignatura);
                idAlumnoasignatura = em.merge(idAlumnoasignatura);
            }
            if (grupoAlumnoesquema != null) {
                EsquemaAlumnoasignatura oldEsquemaAlumnoasignaturaOfGrupoAlumnoesquema = grupoAlumnoesquema.getEsquemaAlumnoasignatura();
                if (oldEsquemaAlumnoasignaturaOfGrupoAlumnoesquema != null) {
                    oldEsquemaAlumnoasignaturaOfGrupoAlumnoesquema.setGrupoAlumnoesquema(null);
                    oldEsquemaAlumnoasignaturaOfGrupoAlumnoesquema = em.merge(oldEsquemaAlumnoasignaturaOfGrupoAlumnoesquema);
                }
                grupoAlumnoesquema.setEsquemaAlumnoasignatura(esquemaAlumnoasignatura);
                grupoAlumnoesquema = em.merge(grupoAlumnoesquema);
            }
            for (Asesor asesorListAsesor : esquemaAlumnoasignatura.getAsesorList()) {
                asesorListAsesor.getEsquemaAlumnoasignaturaList().add(esquemaAlumnoasignatura);
                asesorListAsesor = em.merge(asesorListAsesor);
            }
            for (Calificacion calificacionListCalificacion : esquemaAlumnoasignatura.getCalificacionList()) {
                EsquemaAlumnoasignatura oldEsquemaAlumnoasignaturaOfCalificacionListCalificacion = calificacionListCalificacion.getEsquemaAlumnoasignatura();
                calificacionListCalificacion.setEsquemaAlumnoasignatura(esquemaAlumnoasignatura);
                calificacionListCalificacion = em.merge(calificacionListCalificacion);
                if (oldEsquemaAlumnoasignaturaOfCalificacionListCalificacion != null) {
                    oldEsquemaAlumnoasignaturaOfCalificacionListCalificacion.getCalificacionList().remove(calificacionListCalificacion);
                    oldEsquemaAlumnoasignaturaOfCalificacionListCalificacion = em.merge(oldEsquemaAlumnoasignaturaOfCalificacionListCalificacion);
                }
            }
            for (HistoricoCalificacion historicoCalificacionListHistoricoCalificacion : esquemaAlumnoasignatura.getHistoricoCalificacionList()) {
                EsquemaAlumnoasignatura oldIdEsquemaalumnoasignaturaOfHistoricoCalificacionListHistoricoCalificacion = historicoCalificacionListHistoricoCalificacion.getIdEsquemaalumnoasignatura();
                historicoCalificacionListHistoricoCalificacion.setIdEsquemaalumnoasignatura(esquemaAlumnoasignatura);
                historicoCalificacionListHistoricoCalificacion = em.merge(historicoCalificacionListHistoricoCalificacion);
                if (oldIdEsquemaalumnoasignaturaOfHistoricoCalificacionListHistoricoCalificacion != null) {
                    oldIdEsquemaalumnoasignaturaOfHistoricoCalificacionListHistoricoCalificacion.getHistoricoCalificacionList().remove(historicoCalificacionListHistoricoCalificacion);
                    oldIdEsquemaalumnoasignaturaOfHistoricoCalificacionListHistoricoCalificacion = em.merge(oldIdEsquemaalumnoasignaturaOfHistoricoCalificacionListHistoricoCalificacion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EsquemaAlumnoasignatura esquemaAlumnoasignatura) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EsquemaAlumnoasignatura persistentEsquemaAlumnoasignatura = em.find(EsquemaAlumnoasignatura.class, esquemaAlumnoasignatura.getIdEsquemaalumnoasignatura());
            TipoProgramacion esquemaOld = persistentEsquemaAlumnoasignatura.getEsquema();
            TipoProgramacion esquemaNew = esquemaAlumnoasignatura.getEsquema();
            AlumnoAsignatura idAlumnoasignaturaOld = persistentEsquemaAlumnoasignatura.getIdAlumnoasignatura();
            AlumnoAsignatura idAlumnoasignaturaNew = esquemaAlumnoasignatura.getIdAlumnoasignatura();
            GrupoAlumnoesquema grupoAlumnoesquemaOld = persistentEsquemaAlumnoasignatura.getGrupoAlumnoesquema();
            GrupoAlumnoesquema grupoAlumnoesquemaNew = esquemaAlumnoasignatura.getGrupoAlumnoesquema();
            List<Asesor> asesorListOld = persistentEsquemaAlumnoasignatura.getAsesorList();
            List<Asesor> asesorListNew = esquemaAlumnoasignatura.getAsesorList();
            List<Calificacion> calificacionListOld = persistentEsquemaAlumnoasignatura.getCalificacionList();
            List<Calificacion> calificacionListNew = esquemaAlumnoasignatura.getCalificacionList();
            List<HistoricoCalificacion> historicoCalificacionListOld = persistentEsquemaAlumnoasignatura.getHistoricoCalificacionList();
            List<HistoricoCalificacion> historicoCalificacionListNew = esquemaAlumnoasignatura.getHistoricoCalificacionList();
            List<String> illegalOrphanMessages = null;
            if (grupoAlumnoesquemaOld != null && !grupoAlumnoesquemaOld.equals(grupoAlumnoesquemaNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain GrupoAlumnoesquema " + grupoAlumnoesquemaOld + " since its esquemaAlumnoasignatura field is not nullable.");
            }
            for (Calificacion calificacionListOldCalificacion : calificacionListOld) {
                if (!calificacionListNew.contains(calificacionListOldCalificacion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Calificacion " + calificacionListOldCalificacion + " since its esquemaAlumnoasignatura field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (esquemaNew != null) {
                esquemaNew = em.getReference(esquemaNew.getClass(), esquemaNew.getIdTipoprogramacion());
                esquemaAlumnoasignatura.setEsquema(esquemaNew);
            }
            if (idAlumnoasignaturaNew != null) {
                idAlumnoasignaturaNew = em.getReference(idAlumnoasignaturaNew.getClass(), idAlumnoasignaturaNew.getIdAlumnoasignatura());
                esquemaAlumnoasignatura.setIdAlumnoasignatura(idAlumnoasignaturaNew);
            }
            if (grupoAlumnoesquemaNew != null) {
                grupoAlumnoesquemaNew = em.getReference(grupoAlumnoesquemaNew.getClass(), grupoAlumnoesquemaNew.getIdEsquemaalumnoasignatura());
                esquemaAlumnoasignatura.setGrupoAlumnoesquema(grupoAlumnoesquemaNew);
            }
            List<Asesor> attachedAsesorListNew = new ArrayList<Asesor>();
            for (Asesor asesorListNewAsesorToAttach : asesorListNew) {
                asesorListNewAsesorToAttach = em.getReference(asesorListNewAsesorToAttach.getClass(), asesorListNewAsesorToAttach.getIdasesor());
                attachedAsesorListNew.add(asesorListNewAsesorToAttach);
            }
            asesorListNew = attachedAsesorListNew;
            esquemaAlumnoasignatura.setAsesorList(asesorListNew);
            List<Calificacion> attachedCalificacionListNew = new ArrayList<Calificacion>();
            for (Calificacion calificacionListNewCalificacionToAttach : calificacionListNew) {
                calificacionListNewCalificacionToAttach = em.getReference(calificacionListNewCalificacionToAttach.getClass(), calificacionListNewCalificacionToAttach.getCalificacionPK());
                attachedCalificacionListNew.add(calificacionListNewCalificacionToAttach);
            }
            calificacionListNew = attachedCalificacionListNew;
            esquemaAlumnoasignatura.setCalificacionList(calificacionListNew);
            List<HistoricoCalificacion> attachedHistoricoCalificacionListNew = new ArrayList<HistoricoCalificacion>();
            for (HistoricoCalificacion historicoCalificacionListNewHistoricoCalificacionToAttach : historicoCalificacionListNew) {
                historicoCalificacionListNewHistoricoCalificacionToAttach = em.getReference(historicoCalificacionListNewHistoricoCalificacionToAttach.getClass(), historicoCalificacionListNewHistoricoCalificacionToAttach.getIdHistoricocalificacion());
                attachedHistoricoCalificacionListNew.add(historicoCalificacionListNewHistoricoCalificacionToAttach);
            }
            historicoCalificacionListNew = attachedHistoricoCalificacionListNew;
            esquemaAlumnoasignatura.setHistoricoCalificacionList(historicoCalificacionListNew);
            esquemaAlumnoasignatura = em.merge(esquemaAlumnoasignatura);
            if (esquemaOld != null && !esquemaOld.equals(esquemaNew)) {
                esquemaOld.getEsquemaAlumnoasignaturaList().remove(esquemaAlumnoasignatura);
                esquemaOld = em.merge(esquemaOld);
            }
            if (esquemaNew != null && !esquemaNew.equals(esquemaOld)) {
                esquemaNew.getEsquemaAlumnoasignaturaList().add(esquemaAlumnoasignatura);
                esquemaNew = em.merge(esquemaNew);
            }
            if (idAlumnoasignaturaOld != null && !idAlumnoasignaturaOld.equals(idAlumnoasignaturaNew)) {
                idAlumnoasignaturaOld.getEsquemaAlumnoasignaturaList().remove(esquemaAlumnoasignatura);
                idAlumnoasignaturaOld = em.merge(idAlumnoasignaturaOld);
            }
            if (idAlumnoasignaturaNew != null && !idAlumnoasignaturaNew.equals(idAlumnoasignaturaOld)) {
                idAlumnoasignaturaNew.getEsquemaAlumnoasignaturaList().add(esquemaAlumnoasignatura);
                idAlumnoasignaturaNew = em.merge(idAlumnoasignaturaNew);
            }
            if (grupoAlumnoesquemaNew != null && !grupoAlumnoesquemaNew.equals(grupoAlumnoesquemaOld)) {
                EsquemaAlumnoasignatura oldEsquemaAlumnoasignaturaOfGrupoAlumnoesquema = grupoAlumnoesquemaNew.getEsquemaAlumnoasignatura();
                if (oldEsquemaAlumnoasignaturaOfGrupoAlumnoesquema != null) {
                    oldEsquemaAlumnoasignaturaOfGrupoAlumnoesquema.setGrupoAlumnoesquema(null);
                    oldEsquemaAlumnoasignaturaOfGrupoAlumnoesquema = em.merge(oldEsquemaAlumnoasignaturaOfGrupoAlumnoesquema);
                }
                grupoAlumnoesquemaNew.setEsquemaAlumnoasignatura(esquemaAlumnoasignatura);
                grupoAlumnoesquemaNew = em.merge(grupoAlumnoesquemaNew);
            }
            for (Asesor asesorListOldAsesor : asesorListOld) {
                if (!asesorListNew.contains(asesorListOldAsesor)) {
                    asesorListOldAsesor.getEsquemaAlumnoasignaturaList().remove(esquemaAlumnoasignatura);
                    asesorListOldAsesor = em.merge(asesorListOldAsesor);
                }
            }
            for (Asesor asesorListNewAsesor : asesorListNew) {
                if (!asesorListOld.contains(asesorListNewAsesor)) {
                    asesorListNewAsesor.getEsquemaAlumnoasignaturaList().add(esquemaAlumnoasignatura);
                    asesorListNewAsesor = em.merge(asesorListNewAsesor);
                }
            }
            for (Calificacion calificacionListNewCalificacion : calificacionListNew) {
                if (!calificacionListOld.contains(calificacionListNewCalificacion)) {
                    EsquemaAlumnoasignatura oldEsquemaAlumnoasignaturaOfCalificacionListNewCalificacion = calificacionListNewCalificacion.getEsquemaAlumnoasignatura();
                    calificacionListNewCalificacion.setEsquemaAlumnoasignatura(esquemaAlumnoasignatura);
                    calificacionListNewCalificacion = em.merge(calificacionListNewCalificacion);
                    if (oldEsquemaAlumnoasignaturaOfCalificacionListNewCalificacion != null && !oldEsquemaAlumnoasignaturaOfCalificacionListNewCalificacion.equals(esquemaAlumnoasignatura)) {
                        oldEsquemaAlumnoasignaturaOfCalificacionListNewCalificacion.getCalificacionList().remove(calificacionListNewCalificacion);
                        oldEsquemaAlumnoasignaturaOfCalificacionListNewCalificacion = em.merge(oldEsquemaAlumnoasignaturaOfCalificacionListNewCalificacion);
                    }
                }
            }
            for (HistoricoCalificacion historicoCalificacionListOldHistoricoCalificacion : historicoCalificacionListOld) {
                if (!historicoCalificacionListNew.contains(historicoCalificacionListOldHistoricoCalificacion)) {
                    historicoCalificacionListOldHistoricoCalificacion.setIdEsquemaalumnoasignatura(null);
                    historicoCalificacionListOldHistoricoCalificacion = em.merge(historicoCalificacionListOldHistoricoCalificacion);
                }
            }
            for (HistoricoCalificacion historicoCalificacionListNewHistoricoCalificacion : historicoCalificacionListNew) {
                if (!historicoCalificacionListOld.contains(historicoCalificacionListNewHistoricoCalificacion)) {
                    EsquemaAlumnoasignatura oldIdEsquemaalumnoasignaturaOfHistoricoCalificacionListNewHistoricoCalificacion = historicoCalificacionListNewHistoricoCalificacion.getIdEsquemaalumnoasignatura();
                    historicoCalificacionListNewHistoricoCalificacion.setIdEsquemaalumnoasignatura(esquemaAlumnoasignatura);
                    historicoCalificacionListNewHistoricoCalificacion = em.merge(historicoCalificacionListNewHistoricoCalificacion);
                    if (oldIdEsquemaalumnoasignaturaOfHistoricoCalificacionListNewHistoricoCalificacion != null && !oldIdEsquemaalumnoasignaturaOfHistoricoCalificacionListNewHistoricoCalificacion.equals(esquemaAlumnoasignatura)) {
                        oldIdEsquemaalumnoasignaturaOfHistoricoCalificacionListNewHistoricoCalificacion.getHistoricoCalificacionList().remove(historicoCalificacionListNewHistoricoCalificacion);
                        oldIdEsquemaalumnoasignaturaOfHistoricoCalificacionListNewHistoricoCalificacion = em.merge(oldIdEsquemaalumnoasignaturaOfHistoricoCalificacionListNewHistoricoCalificacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = esquemaAlumnoasignatura.getIdEsquemaalumnoasignatura();
                if (findEsquemaAlumnoasignatura(id) == null) {
                    throw new NonexistentEntityException("The esquemaAlumnoasignatura with id " + id + " no longer exists.");
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
            EsquemaAlumnoasignatura esquemaAlumnoasignatura;
            try {
                esquemaAlumnoasignatura = em.getReference(EsquemaAlumnoasignatura.class, id);
                esquemaAlumnoasignatura.getIdEsquemaalumnoasignatura();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The esquemaAlumnoasignatura with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            GrupoAlumnoesquema grupoAlumnoesquemaOrphanCheck = esquemaAlumnoasignatura.getGrupoAlumnoesquema();
            if (grupoAlumnoesquemaOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This EsquemaAlumnoasignatura (" + esquemaAlumnoasignatura + ") cannot be destroyed since the GrupoAlumnoesquema " + grupoAlumnoesquemaOrphanCheck + " in its grupoAlumnoesquema field has a non-nullable esquemaAlumnoasignatura field.");
            }
            List<Calificacion> calificacionListOrphanCheck = esquemaAlumnoasignatura.getCalificacionList();
            for (Calificacion calificacionListOrphanCheckCalificacion : calificacionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This EsquemaAlumnoasignatura (" + esquemaAlumnoasignatura + ") cannot be destroyed since the Calificacion " + calificacionListOrphanCheckCalificacion + " in its calificacionList field has a non-nullable esquemaAlumnoasignatura field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            TipoProgramacion esquema = esquemaAlumnoasignatura.getEsquema();
            if (esquema != null) {
                esquema.getEsquemaAlumnoasignaturaList().remove(esquemaAlumnoasignatura);
                esquema = em.merge(esquema);
            }
            AlumnoAsignatura idAlumnoasignatura = esquemaAlumnoasignatura.getIdAlumnoasignatura();
            if (idAlumnoasignatura != null) {
                idAlumnoasignatura.getEsquemaAlumnoasignaturaList().remove(esquemaAlumnoasignatura);
                idAlumnoasignatura = em.merge(idAlumnoasignatura);
            }
            List<Asesor> asesorList = esquemaAlumnoasignatura.getAsesorList();
            for (Asesor asesorListAsesor : asesorList) {
                asesorListAsesor.getEsquemaAlumnoasignaturaList().remove(esquemaAlumnoasignatura);
                asesorListAsesor = em.merge(asesorListAsesor);
            }
            List<HistoricoCalificacion> historicoCalificacionList = esquemaAlumnoasignatura.getHistoricoCalificacionList();
            for (HistoricoCalificacion historicoCalificacionListHistoricoCalificacion : historicoCalificacionList) {
                historicoCalificacionListHistoricoCalificacion.setIdEsquemaalumnoasignatura(null);
                historicoCalificacionListHistoricoCalificacion = em.merge(historicoCalificacionListHistoricoCalificacion);
            }
            em.remove(esquemaAlumnoasignatura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EsquemaAlumnoasignatura> findEsquemaAlumnoasignaturaEntities() {
        return findEsquemaAlumnoasignaturaEntities(true, -1, -1);
    }

    public List<EsquemaAlumnoasignatura> findEsquemaAlumnoasignaturaEntities(int maxResults, int firstResult) {
        return findEsquemaAlumnoasignaturaEntities(false, maxResults, firstResult);
    }

    private List<EsquemaAlumnoasignatura> findEsquemaAlumnoasignaturaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from EsquemaAlumnoasignatura as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public EsquemaAlumnoasignatura findEsquemaAlumnoasignatura(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EsquemaAlumnoasignatura.class, id);
        } finally {
            em.close();
        }
    }

    public int getEsquemaAlumnoasignaturaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from EsquemaAlumnoasignatura as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
