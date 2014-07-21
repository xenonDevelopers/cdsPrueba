/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.secretariaAcademica.entity.Periodo;
import com.utez.secretariaAcademica.entity.Grupo;
import com.utez.integracion.entity.CalendarioAsignatura;
import com.utez.integracion.entity.CalendarioEscolar;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class CalendarioEscolarJpaController implements Serializable {

    public CalendarioEscolarJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CalendarioEscolar calendarioEscolar) {
        if (calendarioEscolar.getCalendarioAsignaturaList() == null) {
            calendarioEscolar.setCalendarioAsignaturaList(new ArrayList<CalendarioAsignatura>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Periodo idPeriodo = calendarioEscolar.getIdPeriodo();
            if (idPeriodo != null) {
                idPeriodo = em.getReference(idPeriodo.getClass(), idPeriodo.getPeriodo());
                calendarioEscolar.setIdPeriodo(idPeriodo);
            }
            Grupo idGrupo = calendarioEscolar.getIdGrupo();
            if (idGrupo != null) {
                idGrupo = em.getReference(idGrupo.getClass(), idGrupo.getIdgrupo());
                calendarioEscolar.setIdGrupo(idGrupo);
            }
            List<CalendarioAsignatura> attachedCalendarioAsignaturaList = new ArrayList<CalendarioAsignatura>();
            for (CalendarioAsignatura calendarioAsignaturaListCalendarioAsignaturaToAttach : calendarioEscolar.getCalendarioAsignaturaList()) {
                calendarioAsignaturaListCalendarioAsignaturaToAttach = em.getReference(calendarioAsignaturaListCalendarioAsignaturaToAttach.getClass(), calendarioAsignaturaListCalendarioAsignaturaToAttach.getIdCalendarioasignatura());
                attachedCalendarioAsignaturaList.add(calendarioAsignaturaListCalendarioAsignaturaToAttach);
            }
            calendarioEscolar.setCalendarioAsignaturaList(attachedCalendarioAsignaturaList);
            em.persist(calendarioEscolar);
            if (idPeriodo != null) {
                idPeriodo.getCalendarioEscolarList().add(calendarioEscolar);
                idPeriodo = em.merge(idPeriodo);
            }
            if (idGrupo != null) {
                idGrupo.getCalendarioEscolarList().add(calendarioEscolar);
                idGrupo = em.merge(idGrupo);
            }
            for (CalendarioAsignatura calendarioAsignaturaListCalendarioAsignatura : calendarioEscolar.getCalendarioAsignaturaList()) {
                CalendarioEscolar oldIdCalendarioescolarOfCalendarioAsignaturaListCalendarioAsignatura = calendarioAsignaturaListCalendarioAsignatura.getIdCalendarioescolar();
                calendarioAsignaturaListCalendarioAsignatura.setIdCalendarioescolar(calendarioEscolar);
                calendarioAsignaturaListCalendarioAsignatura = em.merge(calendarioAsignaturaListCalendarioAsignatura);
                if (oldIdCalendarioescolarOfCalendarioAsignaturaListCalendarioAsignatura != null) {
                    oldIdCalendarioescolarOfCalendarioAsignaturaListCalendarioAsignatura.getCalendarioAsignaturaList().remove(calendarioAsignaturaListCalendarioAsignatura);
                    oldIdCalendarioescolarOfCalendarioAsignaturaListCalendarioAsignatura = em.merge(oldIdCalendarioescolarOfCalendarioAsignaturaListCalendarioAsignatura);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CalendarioEscolar calendarioEscolar) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CalendarioEscolar persistentCalendarioEscolar = em.find(CalendarioEscolar.class, calendarioEscolar.getIdCalendarioescolar());
            Periodo idPeriodoOld = persistentCalendarioEscolar.getIdPeriodo();
            Periodo idPeriodoNew = calendarioEscolar.getIdPeriodo();
            Grupo idGrupoOld = persistentCalendarioEscolar.getIdGrupo();
            Grupo idGrupoNew = calendarioEscolar.getIdGrupo();
            List<CalendarioAsignatura> calendarioAsignaturaListOld = persistentCalendarioEscolar.getCalendarioAsignaturaList();
            List<CalendarioAsignatura> calendarioAsignaturaListNew = calendarioEscolar.getCalendarioAsignaturaList();
            if (idPeriodoNew != null) {
                idPeriodoNew = em.getReference(idPeriodoNew.getClass(), idPeriodoNew.getPeriodo());
                calendarioEscolar.setIdPeriodo(idPeriodoNew);
            }
            if (idGrupoNew != null) {
                idGrupoNew = em.getReference(idGrupoNew.getClass(), idGrupoNew.getIdgrupo());
                calendarioEscolar.setIdGrupo(idGrupoNew);
            }
            List<CalendarioAsignatura> attachedCalendarioAsignaturaListNew = new ArrayList<CalendarioAsignatura>();
            for (CalendarioAsignatura calendarioAsignaturaListNewCalendarioAsignaturaToAttach : calendarioAsignaturaListNew) {
                calendarioAsignaturaListNewCalendarioAsignaturaToAttach = em.getReference(calendarioAsignaturaListNewCalendarioAsignaturaToAttach.getClass(), calendarioAsignaturaListNewCalendarioAsignaturaToAttach.getIdCalendarioasignatura());
                attachedCalendarioAsignaturaListNew.add(calendarioAsignaturaListNewCalendarioAsignaturaToAttach);
            }
            calendarioAsignaturaListNew = attachedCalendarioAsignaturaListNew;
            calendarioEscolar.setCalendarioAsignaturaList(calendarioAsignaturaListNew);
            calendarioEscolar = em.merge(calendarioEscolar);
            if (idPeriodoOld != null && !idPeriodoOld.equals(idPeriodoNew)) {
                idPeriodoOld.getCalendarioEscolarList().remove(calendarioEscolar);
                idPeriodoOld = em.merge(idPeriodoOld);
            }
            if (idPeriodoNew != null && !idPeriodoNew.equals(idPeriodoOld)) {
                idPeriodoNew.getCalendarioEscolarList().add(calendarioEscolar);
                idPeriodoNew = em.merge(idPeriodoNew);
            }
            if (idGrupoOld != null && !idGrupoOld.equals(idGrupoNew)) {
                idGrupoOld.getCalendarioEscolarList().remove(calendarioEscolar);
                idGrupoOld = em.merge(idGrupoOld);
            }
            if (idGrupoNew != null && !idGrupoNew.equals(idGrupoOld)) {
                idGrupoNew.getCalendarioEscolarList().add(calendarioEscolar);
                idGrupoNew = em.merge(idGrupoNew);
            }
            for (CalendarioAsignatura calendarioAsignaturaListOldCalendarioAsignatura : calendarioAsignaturaListOld) {
                if (!calendarioAsignaturaListNew.contains(calendarioAsignaturaListOldCalendarioAsignatura)) {
                    calendarioAsignaturaListOldCalendarioAsignatura.setIdCalendarioescolar(null);
                    calendarioAsignaturaListOldCalendarioAsignatura = em.merge(calendarioAsignaturaListOldCalendarioAsignatura);
                }
            }
            for (CalendarioAsignatura calendarioAsignaturaListNewCalendarioAsignatura : calendarioAsignaturaListNew) {
                if (!calendarioAsignaturaListOld.contains(calendarioAsignaturaListNewCalendarioAsignatura)) {
                    CalendarioEscolar oldIdCalendarioescolarOfCalendarioAsignaturaListNewCalendarioAsignatura = calendarioAsignaturaListNewCalendarioAsignatura.getIdCalendarioescolar();
                    calendarioAsignaturaListNewCalendarioAsignatura.setIdCalendarioescolar(calendarioEscolar);
                    calendarioAsignaturaListNewCalendarioAsignatura = em.merge(calendarioAsignaturaListNewCalendarioAsignatura);
                    if (oldIdCalendarioescolarOfCalendarioAsignaturaListNewCalendarioAsignatura != null && !oldIdCalendarioescolarOfCalendarioAsignaturaListNewCalendarioAsignatura.equals(calendarioEscolar)) {
                        oldIdCalendarioescolarOfCalendarioAsignaturaListNewCalendarioAsignatura.getCalendarioAsignaturaList().remove(calendarioAsignaturaListNewCalendarioAsignatura);
                        oldIdCalendarioescolarOfCalendarioAsignaturaListNewCalendarioAsignatura = em.merge(oldIdCalendarioescolarOfCalendarioAsignaturaListNewCalendarioAsignatura);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = calendarioEscolar.getIdCalendarioescolar();
                if (findCalendarioEscolar(id) == null) {
                    throw new NonexistentEntityException("The calendarioEscolar with id " + id + " no longer exists.");
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
            CalendarioEscolar calendarioEscolar;
            try {
                calendarioEscolar = em.getReference(CalendarioEscolar.class, id);
                calendarioEscolar.getIdCalendarioescolar();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The calendarioEscolar with id " + id + " no longer exists.", enfe);
            }
            Periodo idPeriodo = calendarioEscolar.getIdPeriodo();
            if (idPeriodo != null) {
                idPeriodo.getCalendarioEscolarList().remove(calendarioEscolar);
                idPeriodo = em.merge(idPeriodo);
            }
            Grupo idGrupo = calendarioEscolar.getIdGrupo();
            if (idGrupo != null) {
                idGrupo.getCalendarioEscolarList().remove(calendarioEscolar);
                idGrupo = em.merge(idGrupo);
            }
            List<CalendarioAsignatura> calendarioAsignaturaList = calendarioEscolar.getCalendarioAsignaturaList();
            for (CalendarioAsignatura calendarioAsignaturaListCalendarioAsignatura : calendarioAsignaturaList) {
                calendarioAsignaturaListCalendarioAsignatura.setIdCalendarioescolar(null);
                calendarioAsignaturaListCalendarioAsignatura = em.merge(calendarioAsignaturaListCalendarioAsignatura);
            }
            em.remove(calendarioEscolar);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CalendarioEscolar> findCalendarioEscolarEntities() {
        return findCalendarioEscolarEntities(true, -1, -1);
    }

    public List<CalendarioEscolar> findCalendarioEscolarEntities(int maxResults, int firstResult) {
        return findCalendarioEscolarEntities(false, maxResults, firstResult);
    }

    private List<CalendarioEscolar> findCalendarioEscolarEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from CalendarioEscolar as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public CalendarioEscolar findCalendarioEscolar(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CalendarioEscolar.class, id);
        } finally {
            em.close();
        }
    }

    public int getCalendarioEscolarCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from CalendarioEscolar as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
