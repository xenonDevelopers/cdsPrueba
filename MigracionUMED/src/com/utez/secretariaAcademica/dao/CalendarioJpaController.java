/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.secretariaAcademica.dao;

import com.utez.secretariaAcademica.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.secretariaAcademica.entity.Plantel;
import com.utez.secretariaAcademica.entity.Periodo;
import com.utez.secretariaAcademica.entity.Grupo;
import com.utez.secretariaAcademica.entity.Asesoriaasignatura;
import com.utez.secretariaAcademica.entity.Calendario;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class CalendarioJpaController implements Serializable {

    public CalendarioJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Calendario calendario) {
        if (calendario.getAsesoriaasignaturaList() == null) {
            calendario.setAsesoriaasignaturaList(new ArrayList<Asesoriaasignatura>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Plantel idplantel = calendario.getIdplantel();
            if (idplantel != null) {
                idplantel = em.getReference(idplantel.getClass(), idplantel.getIdplantel());
                calendario.setIdplantel(idplantel);
            }
            Periodo periodo = calendario.getPeriodo();
            if (periodo != null) {
                periodo = em.getReference(periodo.getClass(), periodo.getPeriodo());
                calendario.setPeriodo(periodo);
            }
            Grupo idgrupo = calendario.getIdgrupo();
            if (idgrupo != null) {
                idgrupo = em.getReference(idgrupo.getClass(), idgrupo.getIdgrupo());
                calendario.setIdgrupo(idgrupo);
            }
            List<Asesoriaasignatura> attachedAsesoriaasignaturaList = new ArrayList<Asesoriaasignatura>();
            for (Asesoriaasignatura asesoriaasignaturaListAsesoriaasignaturaToAttach : calendario.getAsesoriaasignaturaList()) {
                asesoriaasignaturaListAsesoriaasignaturaToAttach = em.getReference(asesoriaasignaturaListAsesoriaasignaturaToAttach.getClass(), asesoriaasignaturaListAsesoriaasignaturaToAttach.getIdasesoriaasignatura());
                attachedAsesoriaasignaturaList.add(asesoriaasignaturaListAsesoriaasignaturaToAttach);
            }
            calendario.setAsesoriaasignaturaList(attachedAsesoriaasignaturaList);
            em.persist(calendario);
            if (idplantel != null) {
                idplantel.getCalendarioList().add(calendario);
                idplantel = em.merge(idplantel);
            }
            if (periodo != null) {
                periodo.getCalendarioList().add(calendario);
                periodo = em.merge(periodo);
            }
            if (idgrupo != null) {
                idgrupo.getCalendarioList().add(calendario);
                idgrupo = em.merge(idgrupo);
            }
            for (Asesoriaasignatura asesoriaasignaturaListAsesoriaasignatura : calendario.getAsesoriaasignaturaList()) {
                Calendario oldIdcalendarioOfAsesoriaasignaturaListAsesoriaasignatura = asesoriaasignaturaListAsesoriaasignatura.getIdcalendario();
                asesoriaasignaturaListAsesoriaasignatura.setIdcalendario(calendario);
                asesoriaasignaturaListAsesoriaasignatura = em.merge(asesoriaasignaturaListAsesoriaasignatura);
                if (oldIdcalendarioOfAsesoriaasignaturaListAsesoriaasignatura != null) {
                    oldIdcalendarioOfAsesoriaasignaturaListAsesoriaasignatura.getAsesoriaasignaturaList().remove(asesoriaasignaturaListAsesoriaasignatura);
                    oldIdcalendarioOfAsesoriaasignaturaListAsesoriaasignatura = em.merge(oldIdcalendarioOfAsesoriaasignaturaListAsesoriaasignatura);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Calendario calendario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Calendario persistentCalendario = em.find(Calendario.class, calendario.getIdcalendario());
            Plantel idplantelOld = persistentCalendario.getIdplantel();
            Plantel idplantelNew = calendario.getIdplantel();
            Periodo periodoOld = persistentCalendario.getPeriodo();
            Periodo periodoNew = calendario.getPeriodo();
            Grupo idgrupoOld = persistentCalendario.getIdgrupo();
            Grupo idgrupoNew = calendario.getIdgrupo();
            List<Asesoriaasignatura> asesoriaasignaturaListOld = persistentCalendario.getAsesoriaasignaturaList();
            List<Asesoriaasignatura> asesoriaasignaturaListNew = calendario.getAsesoriaasignaturaList();
            if (idplantelNew != null) {
                idplantelNew = em.getReference(idplantelNew.getClass(), idplantelNew.getIdplantel());
                calendario.setIdplantel(idplantelNew);
            }
            if (periodoNew != null) {
                periodoNew = em.getReference(periodoNew.getClass(), periodoNew.getPeriodo());
                calendario.setPeriodo(periodoNew);
            }
            if (idgrupoNew != null) {
                idgrupoNew = em.getReference(idgrupoNew.getClass(), idgrupoNew.getIdgrupo());
                calendario.setIdgrupo(idgrupoNew);
            }
            List<Asesoriaasignatura> attachedAsesoriaasignaturaListNew = new ArrayList<Asesoriaasignatura>();
            for (Asesoriaasignatura asesoriaasignaturaListNewAsesoriaasignaturaToAttach : asesoriaasignaturaListNew) {
                asesoriaasignaturaListNewAsesoriaasignaturaToAttach = em.getReference(asesoriaasignaturaListNewAsesoriaasignaturaToAttach.getClass(), asesoriaasignaturaListNewAsesoriaasignaturaToAttach.getIdasesoriaasignatura());
                attachedAsesoriaasignaturaListNew.add(asesoriaasignaturaListNewAsesoriaasignaturaToAttach);
            }
            asesoriaasignaturaListNew = attachedAsesoriaasignaturaListNew;
            calendario.setAsesoriaasignaturaList(asesoriaasignaturaListNew);
            calendario = em.merge(calendario);
            if (idplantelOld != null && !idplantelOld.equals(idplantelNew)) {
                idplantelOld.getCalendarioList().remove(calendario);
                idplantelOld = em.merge(idplantelOld);
            }
            if (idplantelNew != null && !idplantelNew.equals(idplantelOld)) {
                idplantelNew.getCalendarioList().add(calendario);
                idplantelNew = em.merge(idplantelNew);
            }
            if (periodoOld != null && !periodoOld.equals(periodoNew)) {
                periodoOld.getCalendarioList().remove(calendario);
                periodoOld = em.merge(periodoOld);
            }
            if (periodoNew != null && !periodoNew.equals(periodoOld)) {
                periodoNew.getCalendarioList().add(calendario);
                periodoNew = em.merge(periodoNew);
            }
            if (idgrupoOld != null && !idgrupoOld.equals(idgrupoNew)) {
                idgrupoOld.getCalendarioList().remove(calendario);
                idgrupoOld = em.merge(idgrupoOld);
            }
            if (idgrupoNew != null && !idgrupoNew.equals(idgrupoOld)) {
                idgrupoNew.getCalendarioList().add(calendario);
                idgrupoNew = em.merge(idgrupoNew);
            }
            for (Asesoriaasignatura asesoriaasignaturaListOldAsesoriaasignatura : asesoriaasignaturaListOld) {
                if (!asesoriaasignaturaListNew.contains(asesoriaasignaturaListOldAsesoriaasignatura)) {
                    asesoriaasignaturaListOldAsesoriaasignatura.setIdcalendario(null);
                    asesoriaasignaturaListOldAsesoriaasignatura = em.merge(asesoriaasignaturaListOldAsesoriaasignatura);
                }
            }
            for (Asesoriaasignatura asesoriaasignaturaListNewAsesoriaasignatura : asesoriaasignaturaListNew) {
                if (!asesoriaasignaturaListOld.contains(asesoriaasignaturaListNewAsesoriaasignatura)) {
                    Calendario oldIdcalendarioOfAsesoriaasignaturaListNewAsesoriaasignatura = asesoriaasignaturaListNewAsesoriaasignatura.getIdcalendario();
                    asesoriaasignaturaListNewAsesoriaasignatura.setIdcalendario(calendario);
                    asesoriaasignaturaListNewAsesoriaasignatura = em.merge(asesoriaasignaturaListNewAsesoriaasignatura);
                    if (oldIdcalendarioOfAsesoriaasignaturaListNewAsesoriaasignatura != null && !oldIdcalendarioOfAsesoriaasignaturaListNewAsesoriaasignatura.equals(calendario)) {
                        oldIdcalendarioOfAsesoriaasignaturaListNewAsesoriaasignatura.getAsesoriaasignaturaList().remove(asesoriaasignaturaListNewAsesoriaasignatura);
                        oldIdcalendarioOfAsesoriaasignaturaListNewAsesoriaasignatura = em.merge(oldIdcalendarioOfAsesoriaasignaturaListNewAsesoriaasignatura);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = calendario.getIdcalendario();
                if (findCalendario(id) == null) {
                    throw new NonexistentEntityException("The calendario with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Calendario calendario;
            try {
                calendario = em.getReference(Calendario.class, id);
                calendario.getIdcalendario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The calendario with id " + id + " no longer exists.", enfe);
            }
            Plantel idplantel = calendario.getIdplantel();
            if (idplantel != null) {
                idplantel.getCalendarioList().remove(calendario);
                idplantel = em.merge(idplantel);
            }
            Periodo periodo = calendario.getPeriodo();
            if (periodo != null) {
                periodo.getCalendarioList().remove(calendario);
                periodo = em.merge(periodo);
            }
            Grupo idgrupo = calendario.getIdgrupo();
            if (idgrupo != null) {
                idgrupo.getCalendarioList().remove(calendario);
                idgrupo = em.merge(idgrupo);
            }
            List<Asesoriaasignatura> asesoriaasignaturaList = calendario.getAsesoriaasignaturaList();
            for (Asesoriaasignatura asesoriaasignaturaListAsesoriaasignatura : asesoriaasignaturaList) {
                asesoriaasignaturaListAsesoriaasignatura.setIdcalendario(null);
                asesoriaasignaturaListAsesoriaasignatura = em.merge(asesoriaasignaturaListAsesoriaasignatura);
            }
            em.remove(calendario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Calendario> findCalendarioEntities() {
        return findCalendarioEntities(true, -1, -1);
    }

    public List<Calendario> findCalendarioEntities(int maxResults, int firstResult) {
        return findCalendarioEntities(false, maxResults, firstResult);
    }

    private List<Calendario> findCalendarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Calendario as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Calendario findCalendario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Calendario.class, id);
        } finally {
            em.close();
        }
    }

    public int getCalendarioCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Calendario as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
