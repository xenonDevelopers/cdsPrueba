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
import com.utez.secretariaAcademica.entity.Plantel;
import com.utez.secretariaAcademica.entity.Administrativo;
import java.util.ArrayList;
import java.util.List;
import com.utez.secretariaAcademica.entity.Asesor;
import com.utez.secretariaAcademica.entity.Recursohumano;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class RecursohumanoJpaController1 implements Serializable {

    public RecursohumanoJpaController1(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Recursohumano recursohumano) {
        if (recursohumano.getAdministrativoList() == null) {
            recursohumano.setAdministrativoList(new ArrayList<Administrativo>());
        }
        if (recursohumano.getAsesorList() == null) {
            recursohumano.setAsesorList(new ArrayList<Asesor>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Plantel idplantel = recursohumano.getIdplantel();
            if (idplantel != null) {
                idplantel = em.getReference(idplantel.getClass(), idplantel.getIdplantel());
                recursohumano.setIdplantel(idplantel);
            }
            List<Administrativo> attachedAdministrativoList = new ArrayList<Administrativo>();
            for (Administrativo administrativoListAdministrativoToAttach : recursohumano.getAdministrativoList()) {
                administrativoListAdministrativoToAttach = em.getReference(administrativoListAdministrativoToAttach.getClass(), administrativoListAdministrativoToAttach.getIdadministrativo());
                attachedAdministrativoList.add(administrativoListAdministrativoToAttach);
            }
            recursohumano.setAdministrativoList(attachedAdministrativoList);
            List<Asesor> attachedAsesorList = new ArrayList<Asesor>();
            for (Asesor asesorListAsesorToAttach : recursohumano.getAsesorList()) {
                asesorListAsesorToAttach = em.getReference(asesorListAsesorToAttach.getClass(), asesorListAsesorToAttach.getIdasesor());
                attachedAsesorList.add(asesorListAsesorToAttach);
            }
            recursohumano.setAsesorList(attachedAsesorList);
            em.persist(recursohumano);
            if (idplantel != null) {
                idplantel.getRecursohumanoList().add(recursohumano);
                idplantel = em.merge(idplantel);
            }
            for (Administrativo administrativoListAdministrativo : recursohumano.getAdministrativoList()) {
                Recursohumano oldIdrecursohumanoOfAdministrativoListAdministrativo = administrativoListAdministrativo.getIdrecursohumano();
                administrativoListAdministrativo.setIdrecursohumano(recursohumano);
                administrativoListAdministrativo = em.merge(administrativoListAdministrativo);
                if (oldIdrecursohumanoOfAdministrativoListAdministrativo != null) {
                    oldIdrecursohumanoOfAdministrativoListAdministrativo.getAdministrativoList().remove(administrativoListAdministrativo);
                    oldIdrecursohumanoOfAdministrativoListAdministrativo = em.merge(oldIdrecursohumanoOfAdministrativoListAdministrativo);
                }
            }
            for (Asesor asesorListAsesor : recursohumano.getAsesorList()) {
                Recursohumano oldIdrecursohumanoOfAsesorListAsesor = asesorListAsesor.getIdrecursohumano();
                asesorListAsesor.setIdrecursohumano(recursohumano);
                asesorListAsesor = em.merge(asesorListAsesor);
                if (oldIdrecursohumanoOfAsesorListAsesor != null) {
                    oldIdrecursohumanoOfAsesorListAsesor.getAsesorList().remove(asesorListAsesor);
                    oldIdrecursohumanoOfAsesorListAsesor = em.merge(oldIdrecursohumanoOfAsesorListAsesor);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Recursohumano recursohumano) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Recursohumano persistentRecursohumano = em.find(Recursohumano.class, recursohumano.getIdrecursohumano());
            Plantel idplantelOld = persistentRecursohumano.getIdplantel();
            Plantel idplantelNew = recursohumano.getIdplantel();
            List<Administrativo> administrativoListOld = persistentRecursohumano.getAdministrativoList();
            List<Administrativo> administrativoListNew = recursohumano.getAdministrativoList();
            List<Asesor> asesorListOld = persistentRecursohumano.getAsesorList();
            List<Asesor> asesorListNew = recursohumano.getAsesorList();
            List<String> illegalOrphanMessages = null;
            for (Administrativo administrativoListOldAdministrativo : administrativoListOld) {
                if (!administrativoListNew.contains(administrativoListOldAdministrativo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Administrativo " + administrativoListOldAdministrativo + " since its idrecursohumano field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idplantelNew != null) {
                idplantelNew = em.getReference(idplantelNew.getClass(), idplantelNew.getIdplantel());
                recursohumano.setIdplantel(idplantelNew);
            }
            List<Administrativo> attachedAdministrativoListNew = new ArrayList<Administrativo>();
            for (Administrativo administrativoListNewAdministrativoToAttach : administrativoListNew) {
                administrativoListNewAdministrativoToAttach = em.getReference(administrativoListNewAdministrativoToAttach.getClass(), administrativoListNewAdministrativoToAttach.getIdadministrativo());
                attachedAdministrativoListNew.add(administrativoListNewAdministrativoToAttach);
            }
            administrativoListNew = attachedAdministrativoListNew;
            recursohumano.setAdministrativoList(administrativoListNew);
            List<Asesor> attachedAsesorListNew = new ArrayList<Asesor>();
            for (Asesor asesorListNewAsesorToAttach : asesorListNew) {
                asesorListNewAsesorToAttach = em.getReference(asesorListNewAsesorToAttach.getClass(), asesorListNewAsesorToAttach.getIdasesor());
                attachedAsesorListNew.add(asesorListNewAsesorToAttach);
            }
            asesorListNew = attachedAsesorListNew;
            recursohumano.setAsesorList(asesorListNew);
            recursohumano = em.merge(recursohumano);
            if (idplantelOld != null && !idplantelOld.equals(idplantelNew)) {
                idplantelOld.getRecursohumanoList().remove(recursohumano);
                idplantelOld = em.merge(idplantelOld);
            }
            if (idplantelNew != null && !idplantelNew.equals(idplantelOld)) {
                idplantelNew.getRecursohumanoList().add(recursohumano);
                idplantelNew = em.merge(idplantelNew);
            }
            for (Administrativo administrativoListNewAdministrativo : administrativoListNew) {
                if (!administrativoListOld.contains(administrativoListNewAdministrativo)) {
                    Recursohumano oldIdrecursohumanoOfAdministrativoListNewAdministrativo = administrativoListNewAdministrativo.getIdrecursohumano();
                    administrativoListNewAdministrativo.setIdrecursohumano(recursohumano);
                    administrativoListNewAdministrativo = em.merge(administrativoListNewAdministrativo);
                    if (oldIdrecursohumanoOfAdministrativoListNewAdministrativo != null && !oldIdrecursohumanoOfAdministrativoListNewAdministrativo.equals(recursohumano)) {
                        oldIdrecursohumanoOfAdministrativoListNewAdministrativo.getAdministrativoList().remove(administrativoListNewAdministrativo);
                        oldIdrecursohumanoOfAdministrativoListNewAdministrativo = em.merge(oldIdrecursohumanoOfAdministrativoListNewAdministrativo);
                    }
                }
            }
            for (Asesor asesorListOldAsesor : asesorListOld) {
                if (!asesorListNew.contains(asesorListOldAsesor)) {
                    asesorListOldAsesor.setIdrecursohumano(null);
                    asesorListOldAsesor = em.merge(asesorListOldAsesor);
                }
            }
            for (Asesor asesorListNewAsesor : asesorListNew) {
                if (!asesorListOld.contains(asesorListNewAsesor)) {
                    Recursohumano oldIdrecursohumanoOfAsesorListNewAsesor = asesorListNewAsesor.getIdrecursohumano();
                    asesorListNewAsesor.setIdrecursohumano(recursohumano);
                    asesorListNewAsesor = em.merge(asesorListNewAsesor);
                    if (oldIdrecursohumanoOfAsesorListNewAsesor != null && !oldIdrecursohumanoOfAsesorListNewAsesor.equals(recursohumano)) {
                        oldIdrecursohumanoOfAsesorListNewAsesor.getAsesorList().remove(asesorListNewAsesor);
                        oldIdrecursohumanoOfAsesorListNewAsesor = em.merge(oldIdrecursohumanoOfAsesorListNewAsesor);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = recursohumano.getIdrecursohumano();
                if (findRecursohumano(id) == null) {
                    throw new NonexistentEntityException("The recursohumano with id " + id + " no longer exists.");
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
            Recursohumano recursohumano;
            try {
                recursohumano = em.getReference(Recursohumano.class, id);
                recursohumano.getIdrecursohumano();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The recursohumano with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Administrativo> administrativoListOrphanCheck = recursohumano.getAdministrativoList();
            for (Administrativo administrativoListOrphanCheckAdministrativo : administrativoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Recursohumano (" + recursohumano + ") cannot be destroyed since the Administrativo " + administrativoListOrphanCheckAdministrativo + " in its administrativoList field has a non-nullable idrecursohumano field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Plantel idplantel = recursohumano.getIdplantel();
            if (idplantel != null) {
                idplantel.getRecursohumanoList().remove(recursohumano);
                idplantel = em.merge(idplantel);
            }
            List<Asesor> asesorList = recursohumano.getAsesorList();
            for (Asesor asesorListAsesor : asesorList) {
                asesorListAsesor.setIdrecursohumano(null);
                asesorListAsesor = em.merge(asesorListAsesor);
            }
            em.remove(recursohumano);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Recursohumano> findRecursohumanoEntities() {
        return findRecursohumanoEntities(true, -1, -1);
    }

    public List<Recursohumano> findRecursohumanoEntities(int maxResults, int firstResult) {
        return findRecursohumanoEntities(false, maxResults, firstResult);
    }

    private List<Recursohumano> findRecursohumanoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Recursohumano as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Recursohumano findRecursohumano(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Recursohumano.class, id);
        } finally {
            em.close();
        }
    }

    public int getRecursohumanoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Recursohumano as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
