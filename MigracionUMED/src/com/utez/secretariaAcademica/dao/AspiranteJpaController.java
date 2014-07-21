/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.secretariaAcademica.dao;

import com.utez.integracion.entity.Aspirante;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.PlanEstudio;
import com.utez.integracion.entity.Persona;
import com.utez.secretariaAcademica.entity.Periodo;
import com.utez.integracion.entity.SeguimientoAspirante;
import java.util.ArrayList;
import java.util.List;
import com.utez.integracion.entity.AspiranteOpcion;
import com.utez.secretariaAcademica.dao.exceptions.IllegalOrphanException;
import com.utez.secretariaAcademica.dao.exceptions.NonexistentEntityException;
import com.utez.secretariaAcademica.entity.Alumno;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class AspiranteJpaController implements Serializable {

    public AspiranteJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Aspirante aspirante) {
        if (aspirante.getSeguimientoAspiranteList() == null) {
            aspirante.setSeguimientoAspiranteList(new ArrayList<SeguimientoAspirante>());
        }
        if (aspirante.getAspiranteOpcionList() == null) {
            aspirante.setAspiranteOpcionList(new ArrayList<AspiranteOpcion>());
        }
        if (aspirante.getAlumnoList() == null) {
            aspirante.setAlumnoList(new ArrayList<Alumno>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PlanEstudio idPlanestudio = aspirante.getIdPlanestudio();
            if (idPlanestudio != null) {
                idPlanestudio = em.getReference(idPlanestudio.getClass(), idPlanestudio.getIdPlanestudio());
                aspirante.setIdPlanestudio(idPlanestudio);
            }
            Persona curp = aspirante.getCurp();
            if (curp != null) {
                curp = em.getReference(curp.getClass(), curp.getCurp());
                aspirante.setCurp(curp);
            }
            Periodo idPeriodo = aspirante.getIdPeriodo();
            if (idPeriodo != null) {
                idPeriodo = em.getReference(idPeriodo.getClass(), idPeriodo.getPeriodo());
                aspirante.setIdPeriodo(idPeriodo);
            }
            List<SeguimientoAspirante> attachedSeguimientoAspiranteList = new ArrayList<SeguimientoAspirante>();
            for (SeguimientoAspirante seguimientoAspiranteListSeguimientoAspiranteToAttach : aspirante.getSeguimientoAspiranteList()) {
                seguimientoAspiranteListSeguimientoAspiranteToAttach = em.getReference(seguimientoAspiranteListSeguimientoAspiranteToAttach.getClass(), seguimientoAspiranteListSeguimientoAspiranteToAttach.getIdSeguimiento());
                attachedSeguimientoAspiranteList.add(seguimientoAspiranteListSeguimientoAspiranteToAttach);
            }
            aspirante.setSeguimientoAspiranteList(attachedSeguimientoAspiranteList);
            List<AspiranteOpcion> attachedAspiranteOpcionList = new ArrayList<AspiranteOpcion>();
            for (AspiranteOpcion aspiranteOpcionListAspiranteOpcionToAttach : aspirante.getAspiranteOpcionList()) {
                aspiranteOpcionListAspiranteOpcionToAttach = em.getReference(aspiranteOpcionListAspiranteOpcionToAttach.getClass(), aspiranteOpcionListAspiranteOpcionToAttach.getAspiranteOpcionPK());
                attachedAspiranteOpcionList.add(aspiranteOpcionListAspiranteOpcionToAttach);
            }
            aspirante.setAspiranteOpcionList(attachedAspiranteOpcionList);
            List<Alumno> attachedAlumnoList = new ArrayList<Alumno>();
            for (Alumno alumnoListAlumnoToAttach : aspirante.getAlumnoList()) {
                alumnoListAlumnoToAttach = em.getReference(alumnoListAlumnoToAttach.getClass(), alumnoListAlumnoToAttach.getMatricula());
                attachedAlumnoList.add(alumnoListAlumnoToAttach);
            }
            aspirante.setAlumnoList(attachedAlumnoList);
            em.persist(aspirante);
            if (idPlanestudio != null) {
                idPlanestudio.getAspiranteList().add(aspirante);
                idPlanestudio = em.merge(idPlanestudio);
            }
            if (curp != null) {
                curp.getAspiranteList().add(aspirante);
                curp = em.merge(curp);
            }
            if (idPeriodo != null) {
                idPeriodo.getAspiranteList().add(aspirante);
                idPeriodo = em.merge(idPeriodo);
            }
            for (SeguimientoAspirante seguimientoAspiranteListSeguimientoAspirante : aspirante.getSeguimientoAspiranteList()) {
                Aspirante oldIdAspiranteOfSeguimientoAspiranteListSeguimientoAspirante = seguimientoAspiranteListSeguimientoAspirante.getIdAspirante();
                seguimientoAspiranteListSeguimientoAspirante.setIdAspirante(aspirante);
                seguimientoAspiranteListSeguimientoAspirante = em.merge(seguimientoAspiranteListSeguimientoAspirante);
                if (oldIdAspiranteOfSeguimientoAspiranteListSeguimientoAspirante != null) {
                    oldIdAspiranteOfSeguimientoAspiranteListSeguimientoAspirante.getSeguimientoAspiranteList().remove(seguimientoAspiranteListSeguimientoAspirante);
                    oldIdAspiranteOfSeguimientoAspiranteListSeguimientoAspirante = em.merge(oldIdAspiranteOfSeguimientoAspiranteListSeguimientoAspirante);
                }
            }
            for (AspiranteOpcion aspiranteOpcionListAspiranteOpcion : aspirante.getAspiranteOpcionList()) {
                Aspirante oldAspiranteOfAspiranteOpcionListAspiranteOpcion = aspiranteOpcionListAspiranteOpcion.getAspirante();
                aspiranteOpcionListAspiranteOpcion.setAspirante(aspirante);
                aspiranteOpcionListAspiranteOpcion = em.merge(aspiranteOpcionListAspiranteOpcion);
                if (oldAspiranteOfAspiranteOpcionListAspiranteOpcion != null) {
                    oldAspiranteOfAspiranteOpcionListAspiranteOpcion.getAspiranteOpcionList().remove(aspiranteOpcionListAspiranteOpcion);
                    oldAspiranteOfAspiranteOpcionListAspiranteOpcion = em.merge(oldAspiranteOfAspiranteOpcionListAspiranteOpcion);
                }
            }
            for (Alumno alumnoListAlumno : aspirante.getAlumnoList()) {
                Aspirante oldIdAspiranteOfAlumnoListAlumno = alumnoListAlumno.getIdAspirante();
                alumnoListAlumno.setIdAspirante(aspirante);
                alumnoListAlumno = em.merge(alumnoListAlumno);
                if (oldIdAspiranteOfAlumnoListAlumno != null) {
                    oldIdAspiranteOfAlumnoListAlumno.getAlumnoList().remove(alumnoListAlumno);
                    oldIdAspiranteOfAlumnoListAlumno = em.merge(oldIdAspiranteOfAlumnoListAlumno);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Aspirante aspirante) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Aspirante persistentAspirante = em.find(Aspirante.class, aspirante.getIdAspirante());
            PlanEstudio idPlanestudioOld = persistentAspirante.getIdPlanestudio();
            PlanEstudio idPlanestudioNew = aspirante.getIdPlanestudio();
            Persona curpOld = persistentAspirante.getCurp();
            Persona curpNew = aspirante.getCurp();
            Periodo idPeriodoOld = persistentAspirante.getIdPeriodo();
            Periodo idPeriodoNew = aspirante.getIdPeriodo();
            List<SeguimientoAspirante> seguimientoAspiranteListOld = persistentAspirante.getSeguimientoAspiranteList();
            List<SeguimientoAspirante> seguimientoAspiranteListNew = aspirante.getSeguimientoAspiranteList();
            List<AspiranteOpcion> aspiranteOpcionListOld = persistentAspirante.getAspiranteOpcionList();
            List<AspiranteOpcion> aspiranteOpcionListNew = aspirante.getAspiranteOpcionList();
            List<Alumno> alumnoListOld = persistentAspirante.getAlumnoList();
            List<Alumno> alumnoListNew = aspirante.getAlumnoList();
            List<String> illegalOrphanMessages = null;
            for (AspiranteOpcion aspiranteOpcionListOldAspiranteOpcion : aspiranteOpcionListOld) {
                if (!aspiranteOpcionListNew.contains(aspiranteOpcionListOldAspiranteOpcion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain AspiranteOpcion " + aspiranteOpcionListOldAspiranteOpcion + " since its aspirante field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idPlanestudioNew != null) {
                idPlanestudioNew = em.getReference(idPlanestudioNew.getClass(), idPlanestudioNew.getIdPlanestudio());
                aspirante.setIdPlanestudio(idPlanestudioNew);
            }
            if (curpNew != null) {
                curpNew = em.getReference(curpNew.getClass(), curpNew.getCurp());
                aspirante.setCurp(curpNew);
            }
            if (idPeriodoNew != null) {
                idPeriodoNew = em.getReference(idPeriodoNew.getClass(), idPeriodoNew.getPeriodo());
                aspirante.setIdPeriodo(idPeriodoNew);
            }
            List<SeguimientoAspirante> attachedSeguimientoAspiranteListNew = new ArrayList<SeguimientoAspirante>();
            for (SeguimientoAspirante seguimientoAspiranteListNewSeguimientoAspiranteToAttach : seguimientoAspiranteListNew) {
                seguimientoAspiranteListNewSeguimientoAspiranteToAttach = em.getReference(seguimientoAspiranteListNewSeguimientoAspiranteToAttach.getClass(), seguimientoAspiranteListNewSeguimientoAspiranteToAttach.getIdSeguimiento());
                attachedSeguimientoAspiranteListNew.add(seguimientoAspiranteListNewSeguimientoAspiranteToAttach);
            }
            seguimientoAspiranteListNew = attachedSeguimientoAspiranteListNew;
            aspirante.setSeguimientoAspiranteList(seguimientoAspiranteListNew);
            List<AspiranteOpcion> attachedAspiranteOpcionListNew = new ArrayList<AspiranteOpcion>();
            for (AspiranteOpcion aspiranteOpcionListNewAspiranteOpcionToAttach : aspiranteOpcionListNew) {
                aspiranteOpcionListNewAspiranteOpcionToAttach = em.getReference(aspiranteOpcionListNewAspiranteOpcionToAttach.getClass(), aspiranteOpcionListNewAspiranteOpcionToAttach.getAspiranteOpcionPK());
                attachedAspiranteOpcionListNew.add(aspiranteOpcionListNewAspiranteOpcionToAttach);
            }
            aspiranteOpcionListNew = attachedAspiranteOpcionListNew;
            aspirante.setAspiranteOpcionList(aspiranteOpcionListNew);
            List<Alumno> attachedAlumnoListNew = new ArrayList<Alumno>();
            for (Alumno alumnoListNewAlumnoToAttach : alumnoListNew) {
                alumnoListNewAlumnoToAttach = em.getReference(alumnoListNewAlumnoToAttach.getClass(), alumnoListNewAlumnoToAttach.getMatricula());
                attachedAlumnoListNew.add(alumnoListNewAlumnoToAttach);
            }
            alumnoListNew = attachedAlumnoListNew;
            aspirante.setAlumnoList(alumnoListNew);
            aspirante = em.merge(aspirante);
            if (idPlanestudioOld != null && !idPlanestudioOld.equals(idPlanestudioNew)) {
                idPlanestudioOld.getAspiranteList().remove(aspirante);
                idPlanestudioOld = em.merge(idPlanestudioOld);
            }
            if (idPlanestudioNew != null && !idPlanestudioNew.equals(idPlanestudioOld)) {
                idPlanestudioNew.getAspiranteList().add(aspirante);
                idPlanestudioNew = em.merge(idPlanestudioNew);
            }
            if (curpOld != null && !curpOld.equals(curpNew)) {
                curpOld.getAspiranteList().remove(aspirante);
                curpOld = em.merge(curpOld);
            }
            if (curpNew != null && !curpNew.equals(curpOld)) {
                curpNew.getAspiranteList().add(aspirante);
                curpNew = em.merge(curpNew);
            }
            if (idPeriodoOld != null && !idPeriodoOld.equals(idPeriodoNew)) {
                idPeriodoOld.getAspiranteList().remove(aspirante);
                idPeriodoOld = em.merge(idPeriodoOld);
            }
            if (idPeriodoNew != null && !idPeriodoNew.equals(idPeriodoOld)) {
                idPeriodoNew.getAspiranteList().add(aspirante);
                idPeriodoNew = em.merge(idPeriodoNew);
            }
            for (SeguimientoAspirante seguimientoAspiranteListOldSeguimientoAspirante : seguimientoAspiranteListOld) {
                if (!seguimientoAspiranteListNew.contains(seguimientoAspiranteListOldSeguimientoAspirante)) {
                    seguimientoAspiranteListOldSeguimientoAspirante.setIdAspirante(null);
                    seguimientoAspiranteListOldSeguimientoAspirante = em.merge(seguimientoAspiranteListOldSeguimientoAspirante);
                }
            }
            for (SeguimientoAspirante seguimientoAspiranteListNewSeguimientoAspirante : seguimientoAspiranteListNew) {
                if (!seguimientoAspiranteListOld.contains(seguimientoAspiranteListNewSeguimientoAspirante)) {
                    Aspirante oldIdAspiranteOfSeguimientoAspiranteListNewSeguimientoAspirante = seguimientoAspiranteListNewSeguimientoAspirante.getIdAspirante();
                    seguimientoAspiranteListNewSeguimientoAspirante.setIdAspirante(aspirante);
                    seguimientoAspiranteListNewSeguimientoAspirante = em.merge(seguimientoAspiranteListNewSeguimientoAspirante);
                    if (oldIdAspiranteOfSeguimientoAspiranteListNewSeguimientoAspirante != null && !oldIdAspiranteOfSeguimientoAspiranteListNewSeguimientoAspirante.equals(aspirante)) {
                        oldIdAspiranteOfSeguimientoAspiranteListNewSeguimientoAspirante.getSeguimientoAspiranteList().remove(seguimientoAspiranteListNewSeguimientoAspirante);
                        oldIdAspiranteOfSeguimientoAspiranteListNewSeguimientoAspirante = em.merge(oldIdAspiranteOfSeguimientoAspiranteListNewSeguimientoAspirante);
                    }
                }
            }
            for (AspiranteOpcion aspiranteOpcionListNewAspiranteOpcion : aspiranteOpcionListNew) {
                if (!aspiranteOpcionListOld.contains(aspiranteOpcionListNewAspiranteOpcion)) {
                    Aspirante oldAspiranteOfAspiranteOpcionListNewAspiranteOpcion = aspiranteOpcionListNewAspiranteOpcion.getAspirante();
                    aspiranteOpcionListNewAspiranteOpcion.setAspirante(aspirante);
                    aspiranteOpcionListNewAspiranteOpcion = em.merge(aspiranteOpcionListNewAspiranteOpcion);
                    if (oldAspiranteOfAspiranteOpcionListNewAspiranteOpcion != null && !oldAspiranteOfAspiranteOpcionListNewAspiranteOpcion.equals(aspirante)) {
                        oldAspiranteOfAspiranteOpcionListNewAspiranteOpcion.getAspiranteOpcionList().remove(aspiranteOpcionListNewAspiranteOpcion);
                        oldAspiranteOfAspiranteOpcionListNewAspiranteOpcion = em.merge(oldAspiranteOfAspiranteOpcionListNewAspiranteOpcion);
                    }
                }
            }
            for (Alumno alumnoListOldAlumno : alumnoListOld) {
                if (!alumnoListNew.contains(alumnoListOldAlumno)) {
                    alumnoListOldAlumno.setIdAspirante(null);
                    alumnoListOldAlumno = em.merge(alumnoListOldAlumno);
                }
            }
            for (Alumno alumnoListNewAlumno : alumnoListNew) {
                if (!alumnoListOld.contains(alumnoListNewAlumno)) {
                    Aspirante oldIdAspiranteOfAlumnoListNewAlumno = alumnoListNewAlumno.getIdAspirante();
                    alumnoListNewAlumno.setIdAspirante(aspirante);
                    alumnoListNewAlumno = em.merge(alumnoListNewAlumno);
                    if (oldIdAspiranteOfAlumnoListNewAlumno != null && !oldIdAspiranteOfAlumnoListNewAlumno.equals(aspirante)) {
                        oldIdAspiranteOfAlumnoListNewAlumno.getAlumnoList().remove(alumnoListNewAlumno);
                        oldIdAspiranteOfAlumnoListNewAlumno = em.merge(oldIdAspiranteOfAlumnoListNewAlumno);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = aspirante.getIdAspirante();
                if (findAspirante(id) == null) {
                    throw new NonexistentEntityException("The aspirante with id " + id + " no longer exists.");
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
            Aspirante aspirante;
            try {
                aspirante = em.getReference(Aspirante.class, id);
                aspirante.getIdAspirante();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The aspirante with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<AspiranteOpcion> aspiranteOpcionListOrphanCheck = aspirante.getAspiranteOpcionList();
            for (AspiranteOpcion aspiranteOpcionListOrphanCheckAspiranteOpcion : aspiranteOpcionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Aspirante (" + aspirante + ") cannot be destroyed since the AspiranteOpcion " + aspiranteOpcionListOrphanCheckAspiranteOpcion + " in its aspiranteOpcionList field has a non-nullable aspirante field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            PlanEstudio idPlanestudio = aspirante.getIdPlanestudio();
            if (idPlanestudio != null) {
                idPlanestudio.getAspiranteList().remove(aspirante);
                idPlanestudio = em.merge(idPlanestudio);
            }
            Persona curp = aspirante.getCurp();
            if (curp != null) {
                curp.getAspiranteList().remove(aspirante);
                curp = em.merge(curp);
            }
            Periodo idPeriodo = aspirante.getIdPeriodo();
            if (idPeriodo != null) {
                idPeriodo.getAspiranteList().remove(aspirante);
                idPeriodo = em.merge(idPeriodo);
            }
            List<SeguimientoAspirante> seguimientoAspiranteList = aspirante.getSeguimientoAspiranteList();
            for (SeguimientoAspirante seguimientoAspiranteListSeguimientoAspirante : seguimientoAspiranteList) {
                seguimientoAspiranteListSeguimientoAspirante.setIdAspirante(null);
                seguimientoAspiranteListSeguimientoAspirante = em.merge(seguimientoAspiranteListSeguimientoAspirante);
            }
            List<Alumno> alumnoList = aspirante.getAlumnoList();
            for (Alumno alumnoListAlumno : alumnoList) {
                alumnoListAlumno.setIdAspirante(null);
                alumnoListAlumno = em.merge(alumnoListAlumno);
            }
            em.remove(aspirante);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Aspirante> findAspiranteEntities() {
        return findAspiranteEntities(true, -1, -1);
    }

    public List<Aspirante> findAspiranteEntities(int maxResults, int firstResult) {
        return findAspiranteEntities(false, maxResults, firstResult);
    }

    private List<Aspirante> findAspiranteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Aspirante as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Aspirante findAspirante(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Aspirante.class, id);
        } finally {
            em.close();
        }
    }

    public int getAspiranteCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Aspirante as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
