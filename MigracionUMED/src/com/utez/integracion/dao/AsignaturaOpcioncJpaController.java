/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.IllegalOrphanException;
import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.entity.AsignaturaOpcionc;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.MesOpcionc;
import com.utez.integracion.entity.ProgramacionOpcionc;
import java.util.ArrayList;
import java.util.List;
import com.utez.integracion.entity.FechaExamenopcionc;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class AsignaturaOpcioncJpaController implements Serializable {

    public AsignaturaOpcioncJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AsignaturaOpcionc asignaturaOpcionc) {
        if (asignaturaOpcionc.getProgramacionOpcioncList() == null) {
            asignaturaOpcionc.setProgramacionOpcioncList(new ArrayList<ProgramacionOpcionc>());
        }
        if (asignaturaOpcionc.getFechaExamenopcioncList() == null) {
            asignaturaOpcionc.setFechaExamenopcioncList(new ArrayList<FechaExamenopcionc>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MesOpcionc idMesopcionc = asignaturaOpcionc.getIdMesopcionc();
            if (idMesopcionc != null) {
                idMesopcionc = em.getReference(idMesopcionc.getClass(), idMesopcionc.getIdMesopcionc());
                asignaturaOpcionc.setIdMesopcionc(idMesopcionc);
            }
            List<ProgramacionOpcionc> attachedProgramacionOpcioncList = new ArrayList<ProgramacionOpcionc>();
            for (ProgramacionOpcionc programacionOpcioncListProgramacionOpcioncToAttach : asignaturaOpcionc.getProgramacionOpcioncList()) {
                programacionOpcioncListProgramacionOpcioncToAttach = em.getReference(programacionOpcioncListProgramacionOpcioncToAttach.getClass(), programacionOpcioncListProgramacionOpcioncToAttach.getIdProgramacionasignatura());
                attachedProgramacionOpcioncList.add(programacionOpcioncListProgramacionOpcioncToAttach);
            }
            asignaturaOpcionc.setProgramacionOpcioncList(attachedProgramacionOpcioncList);
            List<FechaExamenopcionc> attachedFechaExamenopcioncList = new ArrayList<FechaExamenopcionc>();
            for (FechaExamenopcionc fechaExamenopcioncListFechaExamenopcioncToAttach : asignaturaOpcionc.getFechaExamenopcioncList()) {
                fechaExamenopcioncListFechaExamenopcioncToAttach = em.getReference(fechaExamenopcioncListFechaExamenopcioncToAttach.getClass(), fechaExamenopcioncListFechaExamenopcioncToAttach.getFechaExamenopcioncPK());
                attachedFechaExamenopcioncList.add(fechaExamenopcioncListFechaExamenopcioncToAttach);
            }
            asignaturaOpcionc.setFechaExamenopcioncList(attachedFechaExamenopcioncList);
            em.persist(asignaturaOpcionc);
            if (idMesopcionc != null) {
                idMesopcionc.getAsignaturaOpcioncList().add(asignaturaOpcionc);
                idMesopcionc = em.merge(idMesopcionc);
            }
            for (ProgramacionOpcionc programacionOpcioncListProgramacionOpcionc : asignaturaOpcionc.getProgramacionOpcioncList()) {
                AsignaturaOpcionc oldIdCalendarioopcioncOfProgramacionOpcioncListProgramacionOpcionc = programacionOpcioncListProgramacionOpcionc.getIdCalendarioopcionc();
                programacionOpcioncListProgramacionOpcionc.setIdCalendarioopcionc(asignaturaOpcionc);
                programacionOpcioncListProgramacionOpcionc = em.merge(programacionOpcioncListProgramacionOpcionc);
                if (oldIdCalendarioopcioncOfProgramacionOpcioncListProgramacionOpcionc != null) {
                    oldIdCalendarioopcioncOfProgramacionOpcioncListProgramacionOpcionc.getProgramacionOpcioncList().remove(programacionOpcioncListProgramacionOpcionc);
                    oldIdCalendarioopcioncOfProgramacionOpcioncListProgramacionOpcionc = em.merge(oldIdCalendarioopcioncOfProgramacionOpcioncListProgramacionOpcionc);
                }
            }
            for (FechaExamenopcionc fechaExamenopcioncListFechaExamenopcionc : asignaturaOpcionc.getFechaExamenopcioncList()) {
                AsignaturaOpcionc oldAsignaturaOpcioncOfFechaExamenopcioncListFechaExamenopcionc = fechaExamenopcioncListFechaExamenopcionc.getAsignaturaOpcionc();
                fechaExamenopcioncListFechaExamenopcionc.setAsignaturaOpcionc(asignaturaOpcionc);
                fechaExamenopcioncListFechaExamenopcionc = em.merge(fechaExamenopcioncListFechaExamenopcionc);
                if (oldAsignaturaOpcioncOfFechaExamenopcioncListFechaExamenopcionc != null) {
                    oldAsignaturaOpcioncOfFechaExamenopcioncListFechaExamenopcionc.getFechaExamenopcioncList().remove(fechaExamenopcioncListFechaExamenopcionc);
                    oldAsignaturaOpcioncOfFechaExamenopcioncListFechaExamenopcionc = em.merge(oldAsignaturaOpcioncOfFechaExamenopcioncListFechaExamenopcionc);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AsignaturaOpcionc asignaturaOpcionc) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AsignaturaOpcionc persistentAsignaturaOpcionc = em.find(AsignaturaOpcionc.class, asignaturaOpcionc.getIdAsignaturaopcionc());
            MesOpcionc idMesopcioncOld = persistentAsignaturaOpcionc.getIdMesopcionc();
            MesOpcionc idMesopcioncNew = asignaturaOpcionc.getIdMesopcionc();
            List<ProgramacionOpcionc> programacionOpcioncListOld = persistentAsignaturaOpcionc.getProgramacionOpcioncList();
            List<ProgramacionOpcionc> programacionOpcioncListNew = asignaturaOpcionc.getProgramacionOpcioncList();
            List<FechaExamenopcionc> fechaExamenopcioncListOld = persistentAsignaturaOpcionc.getFechaExamenopcioncList();
            List<FechaExamenopcionc> fechaExamenopcioncListNew = asignaturaOpcionc.getFechaExamenopcioncList();
            List<String> illegalOrphanMessages = null;
            for (FechaExamenopcionc fechaExamenopcioncListOldFechaExamenopcionc : fechaExamenopcioncListOld) {
                if (!fechaExamenopcioncListNew.contains(fechaExamenopcioncListOldFechaExamenopcionc)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain FechaExamenopcionc " + fechaExamenopcioncListOldFechaExamenopcionc + " since its asignaturaOpcionc field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idMesopcioncNew != null) {
                idMesopcioncNew = em.getReference(idMesopcioncNew.getClass(), idMesopcioncNew.getIdMesopcionc());
                asignaturaOpcionc.setIdMesopcionc(idMesopcioncNew);
            }
            List<ProgramacionOpcionc> attachedProgramacionOpcioncListNew = new ArrayList<ProgramacionOpcionc>();
            for (ProgramacionOpcionc programacionOpcioncListNewProgramacionOpcioncToAttach : programacionOpcioncListNew) {
                programacionOpcioncListNewProgramacionOpcioncToAttach = em.getReference(programacionOpcioncListNewProgramacionOpcioncToAttach.getClass(), programacionOpcioncListNewProgramacionOpcioncToAttach.getIdProgramacionasignatura());
                attachedProgramacionOpcioncListNew.add(programacionOpcioncListNewProgramacionOpcioncToAttach);
            }
            programacionOpcioncListNew = attachedProgramacionOpcioncListNew;
            asignaturaOpcionc.setProgramacionOpcioncList(programacionOpcioncListNew);
            List<FechaExamenopcionc> attachedFechaExamenopcioncListNew = new ArrayList<FechaExamenopcionc>();
            for (FechaExamenopcionc fechaExamenopcioncListNewFechaExamenopcioncToAttach : fechaExamenopcioncListNew) {
                fechaExamenopcioncListNewFechaExamenopcioncToAttach = em.getReference(fechaExamenopcioncListNewFechaExamenopcioncToAttach.getClass(), fechaExamenopcioncListNewFechaExamenopcioncToAttach.getFechaExamenopcioncPK());
                attachedFechaExamenopcioncListNew.add(fechaExamenopcioncListNewFechaExamenopcioncToAttach);
            }
            fechaExamenopcioncListNew = attachedFechaExamenopcioncListNew;
            asignaturaOpcionc.setFechaExamenopcioncList(fechaExamenopcioncListNew);
            asignaturaOpcionc = em.merge(asignaturaOpcionc);
            if (idMesopcioncOld != null && !idMesopcioncOld.equals(idMesopcioncNew)) {
                idMesopcioncOld.getAsignaturaOpcioncList().remove(asignaturaOpcionc);
                idMesopcioncOld = em.merge(idMesopcioncOld);
            }
            if (idMesopcioncNew != null && !idMesopcioncNew.equals(idMesopcioncOld)) {
                idMesopcioncNew.getAsignaturaOpcioncList().add(asignaturaOpcionc);
                idMesopcioncNew = em.merge(idMesopcioncNew);
            }
            for (ProgramacionOpcionc programacionOpcioncListOldProgramacionOpcionc : programacionOpcioncListOld) {
                if (!programacionOpcioncListNew.contains(programacionOpcioncListOldProgramacionOpcionc)) {
                    programacionOpcioncListOldProgramacionOpcionc.setIdCalendarioopcionc(null);
                    programacionOpcioncListOldProgramacionOpcionc = em.merge(programacionOpcioncListOldProgramacionOpcionc);
                }
            }
            for (ProgramacionOpcionc programacionOpcioncListNewProgramacionOpcionc : programacionOpcioncListNew) {
                if (!programacionOpcioncListOld.contains(programacionOpcioncListNewProgramacionOpcionc)) {
                    AsignaturaOpcionc oldIdCalendarioopcioncOfProgramacionOpcioncListNewProgramacionOpcionc = programacionOpcioncListNewProgramacionOpcionc.getIdCalendarioopcionc();
                    programacionOpcioncListNewProgramacionOpcionc.setIdCalendarioopcionc(asignaturaOpcionc);
                    programacionOpcioncListNewProgramacionOpcionc = em.merge(programacionOpcioncListNewProgramacionOpcionc);
                    if (oldIdCalendarioopcioncOfProgramacionOpcioncListNewProgramacionOpcionc != null && !oldIdCalendarioopcioncOfProgramacionOpcioncListNewProgramacionOpcionc.equals(asignaturaOpcionc)) {
                        oldIdCalendarioopcioncOfProgramacionOpcioncListNewProgramacionOpcionc.getProgramacionOpcioncList().remove(programacionOpcioncListNewProgramacionOpcionc);
                        oldIdCalendarioopcioncOfProgramacionOpcioncListNewProgramacionOpcionc = em.merge(oldIdCalendarioopcioncOfProgramacionOpcioncListNewProgramacionOpcionc);
                    }
                }
            }
            for (FechaExamenopcionc fechaExamenopcioncListNewFechaExamenopcionc : fechaExamenopcioncListNew) {
                if (!fechaExamenopcioncListOld.contains(fechaExamenopcioncListNewFechaExamenopcionc)) {
                    AsignaturaOpcionc oldAsignaturaOpcioncOfFechaExamenopcioncListNewFechaExamenopcionc = fechaExamenopcioncListNewFechaExamenopcionc.getAsignaturaOpcionc();
                    fechaExamenopcioncListNewFechaExamenopcionc.setAsignaturaOpcionc(asignaturaOpcionc);
                    fechaExamenopcioncListNewFechaExamenopcionc = em.merge(fechaExamenopcioncListNewFechaExamenopcionc);
                    if (oldAsignaturaOpcioncOfFechaExamenopcioncListNewFechaExamenopcionc != null && !oldAsignaturaOpcioncOfFechaExamenopcioncListNewFechaExamenopcionc.equals(asignaturaOpcionc)) {
                        oldAsignaturaOpcioncOfFechaExamenopcioncListNewFechaExamenopcionc.getFechaExamenopcioncList().remove(fechaExamenopcioncListNewFechaExamenopcionc);
                        oldAsignaturaOpcioncOfFechaExamenopcioncListNewFechaExamenopcionc = em.merge(oldAsignaturaOpcioncOfFechaExamenopcioncListNewFechaExamenopcionc);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = asignaturaOpcionc.getIdAsignaturaopcionc();
                if (findAsignaturaOpcionc(id) == null) {
                    throw new NonexistentEntityException("The asignaturaOpcionc with id " + id + " no longer exists.");
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
            AsignaturaOpcionc asignaturaOpcionc;
            try {
                asignaturaOpcionc = em.getReference(AsignaturaOpcionc.class, id);
                asignaturaOpcionc.getIdAsignaturaopcionc();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The asignaturaOpcionc with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<FechaExamenopcionc> fechaExamenopcioncListOrphanCheck = asignaturaOpcionc.getFechaExamenopcioncList();
            for (FechaExamenopcionc fechaExamenopcioncListOrphanCheckFechaExamenopcionc : fechaExamenopcioncListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This AsignaturaOpcionc (" + asignaturaOpcionc + ") cannot be destroyed since the FechaExamenopcionc " + fechaExamenopcioncListOrphanCheckFechaExamenopcionc + " in its fechaExamenopcioncList field has a non-nullable asignaturaOpcionc field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            MesOpcionc idMesopcionc = asignaturaOpcionc.getIdMesopcionc();
            if (idMesopcionc != null) {
                idMesopcionc.getAsignaturaOpcioncList().remove(asignaturaOpcionc);
                idMesopcionc = em.merge(idMesopcionc);
            }
            List<ProgramacionOpcionc> programacionOpcioncList = asignaturaOpcionc.getProgramacionOpcioncList();
            for (ProgramacionOpcionc programacionOpcioncListProgramacionOpcionc : programacionOpcioncList) {
                programacionOpcioncListProgramacionOpcionc.setIdCalendarioopcionc(null);
                programacionOpcioncListProgramacionOpcionc = em.merge(programacionOpcioncListProgramacionOpcionc);
            }
            em.remove(asignaturaOpcionc);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AsignaturaOpcionc> findAsignaturaOpcioncEntities() {
        return findAsignaturaOpcioncEntities(true, -1, -1);
    }

    public List<AsignaturaOpcionc> findAsignaturaOpcioncEntities(int maxResults, int firstResult) {
        return findAsignaturaOpcioncEntities(false, maxResults, firstResult);
    }

    private List<AsignaturaOpcionc> findAsignaturaOpcioncEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from AsignaturaOpcionc as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public AsignaturaOpcionc findAsignaturaOpcionc(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AsignaturaOpcionc.class, id);
        } finally {
            em.close();
        }
    }

    public int getAsignaturaOpcioncCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from AsignaturaOpcionc as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
