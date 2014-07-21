/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.secretariaAcademica.dao;

import com.utez.secretariaAcademica.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.secretariaAcademica.entity.Cuadernoprogramacion;
import com.utez.secretariaAcademica.entity.Historicoopcionc;
import com.utez.secretariaAcademica.entity.Programacionopcionc;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class ProgramacionopcioncJpaController implements Serializable {

    public ProgramacionopcioncJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Programacionopcionc programacionopcionc) {
        if (programacionopcionc.getHistoricoopcioncList() == null) {
            programacionopcionc.setHistoricoopcioncList(new ArrayList<Historicoopcionc>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cuadernoprogramacion idcuadernoprogramacion = programacionopcionc.getIdcuadernoprogramacion();
            if (idcuadernoprogramacion != null) {
                idcuadernoprogramacion = em.getReference(idcuadernoprogramacion.getClass(), idcuadernoprogramacion.getIdcuadernoprogramacion());
                programacionopcionc.setIdcuadernoprogramacion(idcuadernoprogramacion);
            }
            List<Historicoopcionc> attachedHistoricoopcioncList = new ArrayList<Historicoopcionc>();
            for (Historicoopcionc historicoopcioncListHistoricoopcioncToAttach : programacionopcionc.getHistoricoopcioncList()) {
                historicoopcioncListHistoricoopcioncToAttach = em.getReference(historicoopcioncListHistoricoopcioncToAttach.getClass(), historicoopcioncListHistoricoopcioncToAttach.getIdhistoricoopcionc());
                attachedHistoricoopcioncList.add(historicoopcioncListHistoricoopcioncToAttach);
            }
            programacionopcionc.setHistoricoopcioncList(attachedHistoricoopcioncList);
            em.persist(programacionopcionc);
            if (idcuadernoprogramacion != null) {
                idcuadernoprogramacion.getProgramacionopcioncList().add(programacionopcionc);
                idcuadernoprogramacion = em.merge(idcuadernoprogramacion);
            }
            for (Historicoopcionc historicoopcioncListHistoricoopcionc : programacionopcionc.getHistoricoopcioncList()) {
                Programacionopcionc oldIdprogramacionopcioncOfHistoricoopcioncListHistoricoopcionc = historicoopcioncListHistoricoopcionc.getIdprogramacionopcionc();
                historicoopcioncListHistoricoopcionc.setIdprogramacionopcionc(programacionopcionc);
                historicoopcioncListHistoricoopcionc = em.merge(historicoopcioncListHistoricoopcionc);
                if (oldIdprogramacionopcioncOfHistoricoopcioncListHistoricoopcionc != null) {
                    oldIdprogramacionopcioncOfHistoricoopcioncListHistoricoopcionc.getHistoricoopcioncList().remove(historicoopcioncListHistoricoopcionc);
                    oldIdprogramacionopcioncOfHistoricoopcioncListHistoricoopcionc = em.merge(oldIdprogramacionopcioncOfHistoricoopcioncListHistoricoopcionc);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Programacionopcionc programacionopcionc) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Programacionopcionc persistentProgramacionopcionc = em.find(Programacionopcionc.class, programacionopcionc.getIdprogramacionopcionc());
            Cuadernoprogramacion idcuadernoprogramacionOld = persistentProgramacionopcionc.getIdcuadernoprogramacion();
            Cuadernoprogramacion idcuadernoprogramacionNew = programacionopcionc.getIdcuadernoprogramacion();
            List<Historicoopcionc> historicoopcioncListOld = persistentProgramacionopcionc.getHistoricoopcioncList();
            List<Historicoopcionc> historicoopcioncListNew = programacionopcionc.getHistoricoopcioncList();
            if (idcuadernoprogramacionNew != null) {
                idcuadernoprogramacionNew = em.getReference(idcuadernoprogramacionNew.getClass(), idcuadernoprogramacionNew.getIdcuadernoprogramacion());
                programacionopcionc.setIdcuadernoprogramacion(idcuadernoprogramacionNew);
            }
            List<Historicoopcionc> attachedHistoricoopcioncListNew = new ArrayList<Historicoopcionc>();
            for (Historicoopcionc historicoopcioncListNewHistoricoopcioncToAttach : historicoopcioncListNew) {
                historicoopcioncListNewHistoricoopcioncToAttach = em.getReference(historicoopcioncListNewHistoricoopcioncToAttach.getClass(), historicoopcioncListNewHistoricoopcioncToAttach.getIdhistoricoopcionc());
                attachedHistoricoopcioncListNew.add(historicoopcioncListNewHistoricoopcioncToAttach);
            }
            historicoopcioncListNew = attachedHistoricoopcioncListNew;
            programacionopcionc.setHistoricoopcioncList(historicoopcioncListNew);
            programacionopcionc = em.merge(programacionopcionc);
            if (idcuadernoprogramacionOld != null && !idcuadernoprogramacionOld.equals(idcuadernoprogramacionNew)) {
                idcuadernoprogramacionOld.getProgramacionopcioncList().remove(programacionopcionc);
                idcuadernoprogramacionOld = em.merge(idcuadernoprogramacionOld);
            }
            if (idcuadernoprogramacionNew != null && !idcuadernoprogramacionNew.equals(idcuadernoprogramacionOld)) {
                idcuadernoprogramacionNew.getProgramacionopcioncList().add(programacionopcionc);
                idcuadernoprogramacionNew = em.merge(idcuadernoprogramacionNew);
            }
            for (Historicoopcionc historicoopcioncListOldHistoricoopcionc : historicoopcioncListOld) {
                if (!historicoopcioncListNew.contains(historicoopcioncListOldHistoricoopcionc)) {
                    historicoopcioncListOldHistoricoopcionc.setIdprogramacionopcionc(null);
                    historicoopcioncListOldHistoricoopcionc = em.merge(historicoopcioncListOldHistoricoopcionc);
                }
            }
            for (Historicoopcionc historicoopcioncListNewHistoricoopcionc : historicoopcioncListNew) {
                if (!historicoopcioncListOld.contains(historicoopcioncListNewHistoricoopcionc)) {
                    Programacionopcionc oldIdprogramacionopcioncOfHistoricoopcioncListNewHistoricoopcionc = historicoopcioncListNewHistoricoopcionc.getIdprogramacionopcionc();
                    historicoopcioncListNewHistoricoopcionc.setIdprogramacionopcionc(programacionopcionc);
                    historicoopcioncListNewHistoricoopcionc = em.merge(historicoopcioncListNewHistoricoopcionc);
                    if (oldIdprogramacionopcioncOfHistoricoopcioncListNewHistoricoopcionc != null && !oldIdprogramacionopcioncOfHistoricoopcioncListNewHistoricoopcionc.equals(programacionopcionc)) {
                        oldIdprogramacionopcioncOfHistoricoopcioncListNewHistoricoopcionc.getHistoricoopcioncList().remove(historicoopcioncListNewHistoricoopcionc);
                        oldIdprogramacionopcioncOfHistoricoopcioncListNewHistoricoopcionc = em.merge(oldIdprogramacionopcioncOfHistoricoopcioncListNewHistoricoopcionc);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = programacionopcionc.getIdprogramacionopcionc();
                if (findProgramacionopcionc(id) == null) {
                    throw new NonexistentEntityException("The programacionopcionc with id " + id + " no longer exists.");
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
            Programacionopcionc programacionopcionc;
            try {
                programacionopcionc = em.getReference(Programacionopcionc.class, id);
                programacionopcionc.getIdprogramacionopcionc();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The programacionopcionc with id " + id + " no longer exists.", enfe);
            }
            Cuadernoprogramacion idcuadernoprogramacion = programacionopcionc.getIdcuadernoprogramacion();
            if (idcuadernoprogramacion != null) {
                idcuadernoprogramacion.getProgramacionopcioncList().remove(programacionopcionc);
                idcuadernoprogramacion = em.merge(idcuadernoprogramacion);
            }
            List<Historicoopcionc> historicoopcioncList = programacionopcionc.getHistoricoopcioncList();
            for (Historicoopcionc historicoopcioncListHistoricoopcionc : historicoopcioncList) {
                historicoopcioncListHistoricoopcionc.setIdprogramacionopcionc(null);
                historicoopcioncListHistoricoopcionc = em.merge(historicoopcioncListHistoricoopcionc);
            }
            em.remove(programacionopcionc);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Programacionopcionc> findProgramacionopcioncEntities() {
        return findProgramacionopcioncEntities(true, -1, -1);
    }

    public List<Programacionopcionc> findProgramacionopcioncEntities(int maxResults, int firstResult) {
        return findProgramacionopcioncEntities(false, maxResults, firstResult);
    }

    private List<Programacionopcionc> findProgramacionopcioncEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Programacionopcionc as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Programacionopcionc findProgramacionopcionc(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Programacionopcionc.class, id);
        } finally {
            em.close();
        }
    }

    public int getProgramacionopcioncCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Programacionopcionc as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
