/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.secretariaAcademica.dao;

import com.utez.secretariaAcademica.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.secretariaAcademica.entity.Calendario;
import com.utez.secretariaAcademica.entity.Asignatura;
import com.utez.secretariaAcademica.entity.Asesor;
import com.utez.secretariaAcademica.entity.Asesoriaasignatura;
import com.utez.secretariaAcademica.entity.Fechaasesoria;
import java.util.ArrayList;
import java.util.List;
import com.utez.secretariaAcademica.entity.Fechasexamen;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class AsesoriaasignaturaJpaController implements Serializable {

    public AsesoriaasignaturaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Asesoriaasignatura asesoriaasignatura) {
        if (asesoriaasignatura.getFechaasesoriaList() == null) {
            asesoriaasignatura.setFechaasesoriaList(new ArrayList<Fechaasesoria>());
        }
        if (asesoriaasignatura.getFechasexamenList() == null) {
            asesoriaasignatura.setFechasexamenList(new ArrayList<Fechasexamen>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Calendario idcalendario = asesoriaasignatura.getIdcalendario();
            if (idcalendario != null) {
                idcalendario = em.getReference(idcalendario.getClass(), idcalendario.getIdcalendario());
                asesoriaasignatura.setIdcalendario(idcalendario);
            }
            Asignatura idasignatura = asesoriaasignatura.getIdasignatura();
            if (idasignatura != null) {
                idasignatura = em.getReference(idasignatura.getClass(), idasignatura.getIdasignatura());
                asesoriaasignatura.setIdasignatura(idasignatura);
            }
            Asesor idasesor = asesoriaasignatura.getIdasesor();
            if (idasesor != null) {
                idasesor = em.getReference(idasesor.getClass(), idasesor.getIdasesor());
                asesoriaasignatura.setIdasesor(idasesor);
            }
            List<Fechaasesoria> attachedFechaasesoriaList = new ArrayList<Fechaasesoria>();
            for (Fechaasesoria fechaasesoriaListFechaasesoriaToAttach : asesoriaasignatura.getFechaasesoriaList()) {
                fechaasesoriaListFechaasesoriaToAttach = em.getReference(fechaasesoriaListFechaasesoriaToAttach.getClass(), fechaasesoriaListFechaasesoriaToAttach.getIdfechaasesoria());
                attachedFechaasesoriaList.add(fechaasesoriaListFechaasesoriaToAttach);
            }
            asesoriaasignatura.setFechaasesoriaList(attachedFechaasesoriaList);
            List<Fechasexamen> attachedFechasexamenList = new ArrayList<Fechasexamen>();
            for (Fechasexamen fechasexamenListFechasexamenToAttach : asesoriaasignatura.getFechasexamenList()) {
                fechasexamenListFechasexamenToAttach = em.getReference(fechasexamenListFechasexamenToAttach.getClass(), fechasexamenListFechasexamenToAttach.getIdfechaexamen());
                attachedFechasexamenList.add(fechasexamenListFechasexamenToAttach);
            }
            asesoriaasignatura.setFechasexamenList(attachedFechasexamenList);
            em.persist(asesoriaasignatura);
            if (idcalendario != null) {
                idcalendario.getAsesoriaasignaturaList().add(asesoriaasignatura);
                idcalendario = em.merge(idcalendario);
            }
            if (idasignatura != null) {
                idasignatura.getAsesoriaasignaturaList().add(asesoriaasignatura);
                idasignatura = em.merge(idasignatura);
            }
            if (idasesor != null) {
                idasesor.getAsesoriaasignaturaList().add(asesoriaasignatura);
                idasesor = em.merge(idasesor);
            }
            for (Fechaasesoria fechaasesoriaListFechaasesoria : asesoriaasignatura.getFechaasesoriaList()) {
                Asesoriaasignatura oldIdasesoriaasignaturaOfFechaasesoriaListFechaasesoria = fechaasesoriaListFechaasesoria.getIdasesoriaasignatura();
                fechaasesoriaListFechaasesoria.setIdasesoriaasignatura(asesoriaasignatura);
                fechaasesoriaListFechaasesoria = em.merge(fechaasesoriaListFechaasesoria);
                if (oldIdasesoriaasignaturaOfFechaasesoriaListFechaasesoria != null) {
                    oldIdasesoriaasignaturaOfFechaasesoriaListFechaasesoria.getFechaasesoriaList().remove(fechaasesoriaListFechaasesoria);
                    oldIdasesoriaasignaturaOfFechaasesoriaListFechaasesoria = em.merge(oldIdasesoriaasignaturaOfFechaasesoriaListFechaasesoria);
                }
            }
            for (Fechasexamen fechasexamenListFechasexamen : asesoriaasignatura.getFechasexamenList()) {
                Asesoriaasignatura oldIdasesoriaasignaturaOfFechasexamenListFechasexamen = fechasexamenListFechasexamen.getIdasesoriaasignatura();
                fechasexamenListFechasexamen.setIdasesoriaasignatura(asesoriaasignatura);
                fechasexamenListFechasexamen = em.merge(fechasexamenListFechasexamen);
                if (oldIdasesoriaasignaturaOfFechasexamenListFechasexamen != null) {
                    oldIdasesoriaasignaturaOfFechasexamenListFechasexamen.getFechasexamenList().remove(fechasexamenListFechasexamen);
                    oldIdasesoriaasignaturaOfFechasexamenListFechasexamen = em.merge(oldIdasesoriaasignaturaOfFechasexamenListFechasexamen);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Asesoriaasignatura asesoriaasignatura) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Asesoriaasignatura persistentAsesoriaasignatura = em.find(Asesoriaasignatura.class, asesoriaasignatura.getIdasesoriaasignatura());
            Calendario idcalendarioOld = persistentAsesoriaasignatura.getIdcalendario();
            Calendario idcalendarioNew = asesoriaasignatura.getIdcalendario();
            Asignatura idasignaturaOld = persistentAsesoriaasignatura.getIdasignatura();
            Asignatura idasignaturaNew = asesoriaasignatura.getIdasignatura();
            Asesor idasesorOld = persistentAsesoriaasignatura.getIdasesor();
            Asesor idasesorNew = asesoriaasignatura.getIdasesor();
            List<Fechaasesoria> fechaasesoriaListOld = persistentAsesoriaasignatura.getFechaasesoriaList();
            List<Fechaasesoria> fechaasesoriaListNew = asesoriaasignatura.getFechaasesoriaList();
            List<Fechasexamen> fechasexamenListOld = persistentAsesoriaasignatura.getFechasexamenList();
            List<Fechasexamen> fechasexamenListNew = asesoriaasignatura.getFechasexamenList();
            if (idcalendarioNew != null) {
                idcalendarioNew = em.getReference(idcalendarioNew.getClass(), idcalendarioNew.getIdcalendario());
                asesoriaasignatura.setIdcalendario(idcalendarioNew);
            }
            if (idasignaturaNew != null) {
                idasignaturaNew = em.getReference(idasignaturaNew.getClass(), idasignaturaNew.getIdasignatura());
                asesoriaasignatura.setIdasignatura(idasignaturaNew);
            }
            if (idasesorNew != null) {
                idasesorNew = em.getReference(idasesorNew.getClass(), idasesorNew.getIdasesor());
                asesoriaasignatura.setIdasesor(idasesorNew);
            }
            List<Fechaasesoria> attachedFechaasesoriaListNew = new ArrayList<Fechaasesoria>();
            for (Fechaasesoria fechaasesoriaListNewFechaasesoriaToAttach : fechaasesoriaListNew) {
                fechaasesoriaListNewFechaasesoriaToAttach = em.getReference(fechaasesoriaListNewFechaasesoriaToAttach.getClass(), fechaasesoriaListNewFechaasesoriaToAttach.getIdfechaasesoria());
                attachedFechaasesoriaListNew.add(fechaasesoriaListNewFechaasesoriaToAttach);
            }
            fechaasesoriaListNew = attachedFechaasesoriaListNew;
            asesoriaasignatura.setFechaasesoriaList(fechaasesoriaListNew);
            List<Fechasexamen> attachedFechasexamenListNew = new ArrayList<Fechasexamen>();
            for (Fechasexamen fechasexamenListNewFechasexamenToAttach : fechasexamenListNew) {
                fechasexamenListNewFechasexamenToAttach = em.getReference(fechasexamenListNewFechasexamenToAttach.getClass(), fechasexamenListNewFechasexamenToAttach.getIdfechaexamen());
                attachedFechasexamenListNew.add(fechasexamenListNewFechasexamenToAttach);
            }
            fechasexamenListNew = attachedFechasexamenListNew;
            asesoriaasignatura.setFechasexamenList(fechasexamenListNew);
            asesoriaasignatura = em.merge(asesoriaasignatura);
            if (idcalendarioOld != null && !idcalendarioOld.equals(idcalendarioNew)) {
                idcalendarioOld.getAsesoriaasignaturaList().remove(asesoriaasignatura);
                idcalendarioOld = em.merge(idcalendarioOld);
            }
            if (idcalendarioNew != null && !idcalendarioNew.equals(idcalendarioOld)) {
                idcalendarioNew.getAsesoriaasignaturaList().add(asesoriaasignatura);
                idcalendarioNew = em.merge(idcalendarioNew);
            }
            if (idasignaturaOld != null && !idasignaturaOld.equals(idasignaturaNew)) {
                idasignaturaOld.getAsesoriaasignaturaList().remove(asesoriaasignatura);
                idasignaturaOld = em.merge(idasignaturaOld);
            }
            if (idasignaturaNew != null && !idasignaturaNew.equals(idasignaturaOld)) {
                idasignaturaNew.getAsesoriaasignaturaList().add(asesoriaasignatura);
                idasignaturaNew = em.merge(idasignaturaNew);
            }
            if (idasesorOld != null && !idasesorOld.equals(idasesorNew)) {
                idasesorOld.getAsesoriaasignaturaList().remove(asesoriaasignatura);
                idasesorOld = em.merge(idasesorOld);
            }
            if (idasesorNew != null && !idasesorNew.equals(idasesorOld)) {
                idasesorNew.getAsesoriaasignaturaList().add(asesoriaasignatura);
                idasesorNew = em.merge(idasesorNew);
            }
            for (Fechaasesoria fechaasesoriaListOldFechaasesoria : fechaasesoriaListOld) {
                if (!fechaasesoriaListNew.contains(fechaasesoriaListOldFechaasesoria)) {
                    fechaasesoriaListOldFechaasesoria.setIdasesoriaasignatura(null);
                    fechaasesoriaListOldFechaasesoria = em.merge(fechaasesoriaListOldFechaasesoria);
                }
            }
            for (Fechaasesoria fechaasesoriaListNewFechaasesoria : fechaasesoriaListNew) {
                if (!fechaasesoriaListOld.contains(fechaasesoriaListNewFechaasesoria)) {
                    Asesoriaasignatura oldIdasesoriaasignaturaOfFechaasesoriaListNewFechaasesoria = fechaasesoriaListNewFechaasesoria.getIdasesoriaasignatura();
                    fechaasesoriaListNewFechaasesoria.setIdasesoriaasignatura(asesoriaasignatura);
                    fechaasesoriaListNewFechaasesoria = em.merge(fechaasesoriaListNewFechaasesoria);
                    if (oldIdasesoriaasignaturaOfFechaasesoriaListNewFechaasesoria != null && !oldIdasesoriaasignaturaOfFechaasesoriaListNewFechaasesoria.equals(asesoriaasignatura)) {
                        oldIdasesoriaasignaturaOfFechaasesoriaListNewFechaasesoria.getFechaasesoriaList().remove(fechaasesoriaListNewFechaasesoria);
                        oldIdasesoriaasignaturaOfFechaasesoriaListNewFechaasesoria = em.merge(oldIdasesoriaasignaturaOfFechaasesoriaListNewFechaasesoria);
                    }
                }
            }
            for (Fechasexamen fechasexamenListOldFechasexamen : fechasexamenListOld) {
                if (!fechasexamenListNew.contains(fechasexamenListOldFechasexamen)) {
                    fechasexamenListOldFechasexamen.setIdasesoriaasignatura(null);
                    fechasexamenListOldFechasexamen = em.merge(fechasexamenListOldFechasexamen);
                }
            }
            for (Fechasexamen fechasexamenListNewFechasexamen : fechasexamenListNew) {
                if (!fechasexamenListOld.contains(fechasexamenListNewFechasexamen)) {
                    Asesoriaasignatura oldIdasesoriaasignaturaOfFechasexamenListNewFechasexamen = fechasexamenListNewFechasexamen.getIdasesoriaasignatura();
                    fechasexamenListNewFechasexamen.setIdasesoriaasignatura(asesoriaasignatura);
                    fechasexamenListNewFechasexamen = em.merge(fechasexamenListNewFechasexamen);
                    if (oldIdasesoriaasignaturaOfFechasexamenListNewFechasexamen != null && !oldIdasesoriaasignaturaOfFechasexamenListNewFechasexamen.equals(asesoriaasignatura)) {
                        oldIdasesoriaasignaturaOfFechasexamenListNewFechasexamen.getFechasexamenList().remove(fechasexamenListNewFechasexamen);
                        oldIdasesoriaasignaturaOfFechasexamenListNewFechasexamen = em.merge(oldIdasesoriaasignaturaOfFechasexamenListNewFechasexamen);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = asesoriaasignatura.getIdasesoriaasignatura();
                if (findAsesoriaasignatura(id) == null) {
                    throw new NonexistentEntityException("The asesoriaasignatura with id " + id + " no longer exists.");
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
            Asesoriaasignatura asesoriaasignatura;
            try {
                asesoriaasignatura = em.getReference(Asesoriaasignatura.class, id);
                asesoriaasignatura.getIdasesoriaasignatura();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The asesoriaasignatura with id " + id + " no longer exists.", enfe);
            }
            Calendario idcalendario = asesoriaasignatura.getIdcalendario();
            if (idcalendario != null) {
                idcalendario.getAsesoriaasignaturaList().remove(asesoriaasignatura);
                idcalendario = em.merge(idcalendario);
            }
            Asignatura idasignatura = asesoriaasignatura.getIdasignatura();
            if (idasignatura != null) {
                idasignatura.getAsesoriaasignaturaList().remove(asesoriaasignatura);
                idasignatura = em.merge(idasignatura);
            }
            Asesor idasesor = asesoriaasignatura.getIdasesor();
            if (idasesor != null) {
                idasesor.getAsesoriaasignaturaList().remove(asesoriaasignatura);
                idasesor = em.merge(idasesor);
            }
            List<Fechaasesoria> fechaasesoriaList = asesoriaasignatura.getFechaasesoriaList();
            for (Fechaasesoria fechaasesoriaListFechaasesoria : fechaasesoriaList) {
                fechaasesoriaListFechaasesoria.setIdasesoriaasignatura(null);
                fechaasesoriaListFechaasesoria = em.merge(fechaasesoriaListFechaasesoria);
            }
            List<Fechasexamen> fechasexamenList = asesoriaasignatura.getFechasexamenList();
            for (Fechasexamen fechasexamenListFechasexamen : fechasexamenList) {
                fechasexamenListFechasexamen.setIdasesoriaasignatura(null);
                fechasexamenListFechasexamen = em.merge(fechasexamenListFechasexamen);
            }
            em.remove(asesoriaasignatura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Asesoriaasignatura> findAsesoriaasignaturaEntities() {
        return findAsesoriaasignaturaEntities(true, -1, -1);
    }

    public List<Asesoriaasignatura> findAsesoriaasignaturaEntities(int maxResults, int firstResult) {
        return findAsesoriaasignaturaEntities(false, maxResults, firstResult);
    }

    private List<Asesoriaasignatura> findAsesoriaasignaturaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Asesoriaasignatura as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Asesoriaasignatura findAsesoriaasignatura(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Asesoriaasignatura.class, id);
        } finally {
            em.close();
        }
    }

    public int getAsesoriaasignaturaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Asesoriaasignatura as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
