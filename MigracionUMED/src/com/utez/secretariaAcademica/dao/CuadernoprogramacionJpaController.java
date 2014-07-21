/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.secretariaAcademica.dao;

import com.utez.secretariaAcademica.dao.exceptions.IllegalOrphanException;
import com.utez.secretariaAcademica.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.secretariaAcademica.entity.Programacionra;
import com.utez.secretariaAcademica.entity.Solicitudprogramacion;
import com.utez.secretariaAcademica.entity.Asignatura;
import com.utez.secretariaAcademica.entity.Cuadernoprogramacion;
import com.utez.secretariaAcademica.entity.Programacionopcionc;
import java.util.ArrayList;
import java.util.List;
import com.utez.secretariaAcademica.entity.Programacioncancelada;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class CuadernoprogramacionJpaController implements Serializable {

    public CuadernoprogramacionJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cuadernoprogramacion cuadernoprogramacion) {
        if (cuadernoprogramacion.getProgramacionopcioncList() == null) {
            cuadernoprogramacion.setProgramacionopcioncList(new ArrayList<Programacionopcionc>());
        }
        if (cuadernoprogramacion.getProgramacioncanceladaList() == null) {
            cuadernoprogramacion.setProgramacioncanceladaList(new ArrayList<Programacioncancelada>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Programacionra programacionra = cuadernoprogramacion.getProgramacionra();
            if (programacionra != null) {
                programacionra = em.getReference(programacionra.getClass(), programacionra.getIdprogramacionra());
                cuadernoprogramacion.setProgramacionra(programacionra);
            }
            Solicitudprogramacion idsolicitudprogramacion = cuadernoprogramacion.getIdsolicitudprogramacion();
            if (idsolicitudprogramacion != null) {
                idsolicitudprogramacion = em.getReference(idsolicitudprogramacion.getClass(), idsolicitudprogramacion.getIdsolicitudprogramacion());
                cuadernoprogramacion.setIdsolicitudprogramacion(idsolicitudprogramacion);
            }
            Asignatura idasignatura = cuadernoprogramacion.getIdasignatura();
            if (idasignatura != null) {
                idasignatura = em.getReference(idasignatura.getClass(), idasignatura.getIdasignatura());
                cuadernoprogramacion.setIdasignatura(idasignatura);
            }
            List<Programacionopcionc> attachedProgramacionopcioncList = new ArrayList<Programacionopcionc>();
            for (Programacionopcionc programacionopcioncListProgramacionopcioncToAttach : cuadernoprogramacion.getProgramacionopcioncList()) {
                programacionopcioncListProgramacionopcioncToAttach = em.getReference(programacionopcioncListProgramacionopcioncToAttach.getClass(), programacionopcioncListProgramacionopcioncToAttach.getIdprogramacionopcionc());
                attachedProgramacionopcioncList.add(programacionopcioncListProgramacionopcioncToAttach);
            }
            cuadernoprogramacion.setProgramacionopcioncList(attachedProgramacionopcioncList);
            List<Programacioncancelada> attachedProgramacioncanceladaList = new ArrayList<Programacioncancelada>();
            for (Programacioncancelada programacioncanceladaListProgramacioncanceladaToAttach : cuadernoprogramacion.getProgramacioncanceladaList()) {
                programacioncanceladaListProgramacioncanceladaToAttach = em.getReference(programacioncanceladaListProgramacioncanceladaToAttach.getClass(), programacioncanceladaListProgramacioncanceladaToAttach.getIdprogramacioncancelada());
                attachedProgramacioncanceladaList.add(programacioncanceladaListProgramacioncanceladaToAttach);
            }
            cuadernoprogramacion.setProgramacioncanceladaList(attachedProgramacioncanceladaList);
            em.persist(cuadernoprogramacion);
            if (programacionra != null) {
                Cuadernoprogramacion oldCuadernoprogramacionOfProgramacionra = programacionra.getCuadernoprogramacion();
                if (oldCuadernoprogramacionOfProgramacionra != null) {
                    oldCuadernoprogramacionOfProgramacionra.setProgramacionra(null);
                    oldCuadernoprogramacionOfProgramacionra = em.merge(oldCuadernoprogramacionOfProgramacionra);
                }
                programacionra.setCuadernoprogramacion(cuadernoprogramacion);
                programacionra = em.merge(programacionra);
            }
            if (idsolicitudprogramacion != null) {
                idsolicitudprogramacion.getCuadernoprogramacionList().add(cuadernoprogramacion);
                idsolicitudprogramacion = em.merge(idsolicitudprogramacion);
            }
            if (idasignatura != null) {
                idasignatura.getCuadernoprogramacionList().add(cuadernoprogramacion);
                idasignatura = em.merge(idasignatura);
            }
            for (Programacionopcionc programacionopcioncListProgramacionopcionc : cuadernoprogramacion.getProgramacionopcioncList()) {
                Cuadernoprogramacion oldIdcuadernoprogramacionOfProgramacionopcioncListProgramacionopcionc = programacionopcioncListProgramacionopcionc.getIdcuadernoprogramacion();
                programacionopcioncListProgramacionopcionc.setIdcuadernoprogramacion(cuadernoprogramacion);
                programacionopcioncListProgramacionopcionc = em.merge(programacionopcioncListProgramacionopcionc);
                if (oldIdcuadernoprogramacionOfProgramacionopcioncListProgramacionopcionc != null) {
                    oldIdcuadernoprogramacionOfProgramacionopcioncListProgramacionopcionc.getProgramacionopcioncList().remove(programacionopcioncListProgramacionopcionc);
                    oldIdcuadernoprogramacionOfProgramacionopcioncListProgramacionopcionc = em.merge(oldIdcuadernoprogramacionOfProgramacionopcioncListProgramacionopcionc);
                }
            }
            for (Programacioncancelada programacioncanceladaListProgramacioncancelada : cuadernoprogramacion.getProgramacioncanceladaList()) {
                Cuadernoprogramacion oldIdcuadernoprogramacionOfProgramacioncanceladaListProgramacioncancelada = programacioncanceladaListProgramacioncancelada.getIdcuadernoprogramacion();
                programacioncanceladaListProgramacioncancelada.setIdcuadernoprogramacion(cuadernoprogramacion);
                programacioncanceladaListProgramacioncancelada = em.merge(programacioncanceladaListProgramacioncancelada);
                if (oldIdcuadernoprogramacionOfProgramacioncanceladaListProgramacioncancelada != null) {
                    oldIdcuadernoprogramacionOfProgramacioncanceladaListProgramacioncancelada.getProgramacioncanceladaList().remove(programacioncanceladaListProgramacioncancelada);
                    oldIdcuadernoprogramacionOfProgramacioncanceladaListProgramacioncancelada = em.merge(oldIdcuadernoprogramacionOfProgramacioncanceladaListProgramacioncancelada);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cuadernoprogramacion cuadernoprogramacion) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cuadernoprogramacion persistentCuadernoprogramacion = em.find(Cuadernoprogramacion.class, cuadernoprogramacion.getIdcuadernoprogramacion());
            Programacionra programacionraOld = persistentCuadernoprogramacion.getProgramacionra();
            Programacionra programacionraNew = cuadernoprogramacion.getProgramacionra();
            Solicitudprogramacion idsolicitudprogramacionOld = persistentCuadernoprogramacion.getIdsolicitudprogramacion();
            Solicitudprogramacion idsolicitudprogramacionNew = cuadernoprogramacion.getIdsolicitudprogramacion();
            Asignatura idasignaturaOld = persistentCuadernoprogramacion.getIdasignatura();
            Asignatura idasignaturaNew = cuadernoprogramacion.getIdasignatura();
            List<Programacionopcionc> programacionopcioncListOld = persistentCuadernoprogramacion.getProgramacionopcioncList();
            List<Programacionopcionc> programacionopcioncListNew = cuadernoprogramacion.getProgramacionopcioncList();
            List<Programacioncancelada> programacioncanceladaListOld = persistentCuadernoprogramacion.getProgramacioncanceladaList();
            List<Programacioncancelada> programacioncanceladaListNew = cuadernoprogramacion.getProgramacioncanceladaList();
            List<String> illegalOrphanMessages = null;
            if (programacionraOld != null && !programacionraOld.equals(programacionraNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Programacionra " + programacionraOld + " since its cuadernoprogramacion field is not nullable.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (programacionraNew != null) {
                programacionraNew = em.getReference(programacionraNew.getClass(), programacionraNew.getIdprogramacionra());
                cuadernoprogramacion.setProgramacionra(programacionraNew);
            }
            if (idsolicitudprogramacionNew != null) {
                idsolicitudprogramacionNew = em.getReference(idsolicitudprogramacionNew.getClass(), idsolicitudprogramacionNew.getIdsolicitudprogramacion());
                cuadernoprogramacion.setIdsolicitudprogramacion(idsolicitudprogramacionNew);
            }
            if (idasignaturaNew != null) {
                idasignaturaNew = em.getReference(idasignaturaNew.getClass(), idasignaturaNew.getIdasignatura());
                cuadernoprogramacion.setIdasignatura(idasignaturaNew);
            }
            List<Programacionopcionc> attachedProgramacionopcioncListNew = new ArrayList<Programacionopcionc>();
            for (Programacionopcionc programacionopcioncListNewProgramacionopcioncToAttach : programacionopcioncListNew) {
                programacionopcioncListNewProgramacionopcioncToAttach = em.getReference(programacionopcioncListNewProgramacionopcioncToAttach.getClass(), programacionopcioncListNewProgramacionopcioncToAttach.getIdprogramacionopcionc());
                attachedProgramacionopcioncListNew.add(programacionopcioncListNewProgramacionopcioncToAttach);
            }
            programacionopcioncListNew = attachedProgramacionopcioncListNew;
            cuadernoprogramacion.setProgramacionopcioncList(programacionopcioncListNew);
            List<Programacioncancelada> attachedProgramacioncanceladaListNew = new ArrayList<Programacioncancelada>();
            for (Programacioncancelada programacioncanceladaListNewProgramacioncanceladaToAttach : programacioncanceladaListNew) {
                programacioncanceladaListNewProgramacioncanceladaToAttach = em.getReference(programacioncanceladaListNewProgramacioncanceladaToAttach.getClass(), programacioncanceladaListNewProgramacioncanceladaToAttach.getIdprogramacioncancelada());
                attachedProgramacioncanceladaListNew.add(programacioncanceladaListNewProgramacioncanceladaToAttach);
            }
            programacioncanceladaListNew = attachedProgramacioncanceladaListNew;
            cuadernoprogramacion.setProgramacioncanceladaList(programacioncanceladaListNew);
            cuadernoprogramacion = em.merge(cuadernoprogramacion);
            if (programacionraNew != null && !programacionraNew.equals(programacionraOld)) {
                Cuadernoprogramacion oldCuadernoprogramacionOfProgramacionra = programacionraNew.getCuadernoprogramacion();
                if (oldCuadernoprogramacionOfProgramacionra != null) {
                    oldCuadernoprogramacionOfProgramacionra.setProgramacionra(null);
                    oldCuadernoprogramacionOfProgramacionra = em.merge(oldCuadernoprogramacionOfProgramacionra);
                }
                programacionraNew.setCuadernoprogramacion(cuadernoprogramacion);
                programacionraNew = em.merge(programacionraNew);
            }
            if (idsolicitudprogramacionOld != null && !idsolicitudprogramacionOld.equals(idsolicitudprogramacionNew)) {
                idsolicitudprogramacionOld.getCuadernoprogramacionList().remove(cuadernoprogramacion);
                idsolicitudprogramacionOld = em.merge(idsolicitudprogramacionOld);
            }
            if (idsolicitudprogramacionNew != null && !idsolicitudprogramacionNew.equals(idsolicitudprogramacionOld)) {
                idsolicitudprogramacionNew.getCuadernoprogramacionList().add(cuadernoprogramacion);
                idsolicitudprogramacionNew = em.merge(idsolicitudprogramacionNew);
            }
            if (idasignaturaOld != null && !idasignaturaOld.equals(idasignaturaNew)) {
                idasignaturaOld.getCuadernoprogramacionList().remove(cuadernoprogramacion);
                idasignaturaOld = em.merge(idasignaturaOld);
            }
            if (idasignaturaNew != null && !idasignaturaNew.equals(idasignaturaOld)) {
                idasignaturaNew.getCuadernoprogramacionList().add(cuadernoprogramacion);
                idasignaturaNew = em.merge(idasignaturaNew);
            }
            for (Programacionopcionc programacionopcioncListOldProgramacionopcionc : programacionopcioncListOld) {
                if (!programacionopcioncListNew.contains(programacionopcioncListOldProgramacionopcionc)) {
                    programacionopcioncListOldProgramacionopcionc.setIdcuadernoprogramacion(null);
                    programacionopcioncListOldProgramacionopcionc = em.merge(programacionopcioncListOldProgramacionopcionc);
                }
            }
            for (Programacionopcionc programacionopcioncListNewProgramacionopcionc : programacionopcioncListNew) {
                if (!programacionopcioncListOld.contains(programacionopcioncListNewProgramacionopcionc)) {
                    Cuadernoprogramacion oldIdcuadernoprogramacionOfProgramacionopcioncListNewProgramacionopcionc = programacionopcioncListNewProgramacionopcionc.getIdcuadernoprogramacion();
                    programacionopcioncListNewProgramacionopcionc.setIdcuadernoprogramacion(cuadernoprogramacion);
                    programacionopcioncListNewProgramacionopcionc = em.merge(programacionopcioncListNewProgramacionopcionc);
                    if (oldIdcuadernoprogramacionOfProgramacionopcioncListNewProgramacionopcionc != null && !oldIdcuadernoprogramacionOfProgramacionopcioncListNewProgramacionopcionc.equals(cuadernoprogramacion)) {
                        oldIdcuadernoprogramacionOfProgramacionopcioncListNewProgramacionopcionc.getProgramacionopcioncList().remove(programacionopcioncListNewProgramacionopcionc);
                        oldIdcuadernoprogramacionOfProgramacionopcioncListNewProgramacionopcionc = em.merge(oldIdcuadernoprogramacionOfProgramacionopcioncListNewProgramacionopcionc);
                    }
                }
            }
            for (Programacioncancelada programacioncanceladaListOldProgramacioncancelada : programacioncanceladaListOld) {
                if (!programacioncanceladaListNew.contains(programacioncanceladaListOldProgramacioncancelada)) {
                    programacioncanceladaListOldProgramacioncancelada.setIdcuadernoprogramacion(null);
                    programacioncanceladaListOldProgramacioncancelada = em.merge(programacioncanceladaListOldProgramacioncancelada);
                }
            }
            for (Programacioncancelada programacioncanceladaListNewProgramacioncancelada : programacioncanceladaListNew) {
                if (!programacioncanceladaListOld.contains(programacioncanceladaListNewProgramacioncancelada)) {
                    Cuadernoprogramacion oldIdcuadernoprogramacionOfProgramacioncanceladaListNewProgramacioncancelada = programacioncanceladaListNewProgramacioncancelada.getIdcuadernoprogramacion();
                    programacioncanceladaListNewProgramacioncancelada.setIdcuadernoprogramacion(cuadernoprogramacion);
                    programacioncanceladaListNewProgramacioncancelada = em.merge(programacioncanceladaListNewProgramacioncancelada);
                    if (oldIdcuadernoprogramacionOfProgramacioncanceladaListNewProgramacioncancelada != null && !oldIdcuadernoprogramacionOfProgramacioncanceladaListNewProgramacioncancelada.equals(cuadernoprogramacion)) {
                        oldIdcuadernoprogramacionOfProgramacioncanceladaListNewProgramacioncancelada.getProgramacioncanceladaList().remove(programacioncanceladaListNewProgramacioncancelada);
                        oldIdcuadernoprogramacionOfProgramacioncanceladaListNewProgramacioncancelada = em.merge(oldIdcuadernoprogramacionOfProgramacioncanceladaListNewProgramacioncancelada);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cuadernoprogramacion.getIdcuadernoprogramacion();
                if (findCuadernoprogramacion(id) == null) {
                    throw new NonexistentEntityException("The cuadernoprogramacion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cuadernoprogramacion cuadernoprogramacion;
            try {
                cuadernoprogramacion = em.getReference(Cuadernoprogramacion.class, id);
                cuadernoprogramacion.getIdcuadernoprogramacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cuadernoprogramacion with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Programacionra programacionraOrphanCheck = cuadernoprogramacion.getProgramacionra();
            if (programacionraOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cuadernoprogramacion (" + cuadernoprogramacion + ") cannot be destroyed since the Programacionra " + programacionraOrphanCheck + " in its programacionra field has a non-nullable cuadernoprogramacion field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Solicitudprogramacion idsolicitudprogramacion = cuadernoprogramacion.getIdsolicitudprogramacion();
            if (idsolicitudprogramacion != null) {
                idsolicitudprogramacion.getCuadernoprogramacionList().remove(cuadernoprogramacion);
                idsolicitudprogramacion = em.merge(idsolicitudprogramacion);
            }
            Asignatura idasignatura = cuadernoprogramacion.getIdasignatura();
            if (idasignatura != null) {
                idasignatura.getCuadernoprogramacionList().remove(cuadernoprogramacion);
                idasignatura = em.merge(idasignatura);
            }
            List<Programacionopcionc> programacionopcioncList = cuadernoprogramacion.getProgramacionopcioncList();
            for (Programacionopcionc programacionopcioncListProgramacionopcionc : programacionopcioncList) {
                programacionopcioncListProgramacionopcionc.setIdcuadernoprogramacion(null);
                programacionopcioncListProgramacionopcionc = em.merge(programacionopcioncListProgramacionopcionc);
            }
            List<Programacioncancelada> programacioncanceladaList = cuadernoprogramacion.getProgramacioncanceladaList();
            for (Programacioncancelada programacioncanceladaListProgramacioncancelada : programacioncanceladaList) {
                programacioncanceladaListProgramacioncancelada.setIdcuadernoprogramacion(null);
                programacioncanceladaListProgramacioncancelada = em.merge(programacioncanceladaListProgramacioncancelada);
            }
            em.remove(cuadernoprogramacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cuadernoprogramacion> findCuadernoprogramacionEntities() {
        return findCuadernoprogramacionEntities(true, -1, -1);
    }

    public List<Cuadernoprogramacion> findCuadernoprogramacionEntities(int maxResults, int firstResult) {
        return findCuadernoprogramacionEntities(false, maxResults, firstResult);
    }

    private List<Cuadernoprogramacion> findCuadernoprogramacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Cuadernoprogramacion as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Cuadernoprogramacion findCuadernoprogramacion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cuadernoprogramacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getCuadernoprogramacionCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Cuadernoprogramacion as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
