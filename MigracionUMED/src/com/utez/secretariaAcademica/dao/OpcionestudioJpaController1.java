/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.secretariaAcademica.dao;

import com.utez.secretariaAcademica.dao.exceptions.NonexistentEntityException;
import com.utez.secretariaAcademica.dao.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.secretariaAcademica.entity.Grupo;
import com.utez.secretariaAcademica.entity.Opcionestudio;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class OpcionestudioJpaController1 implements Serializable {

    public OpcionestudioJpaController1(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Opcionestudio opcionestudio) throws PreexistingEntityException, Exception {
        if (opcionestudio.getGrupoList() == null) {
            opcionestudio.setGrupoList(new ArrayList<Grupo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Grupo> attachedGrupoList = new ArrayList<Grupo>();
            for (Grupo grupoListGrupoToAttach : opcionestudio.getGrupoList()) {
                grupoListGrupoToAttach = em.getReference(grupoListGrupoToAttach.getClass(), grupoListGrupoToAttach.getIdgrupo());
                attachedGrupoList.add(grupoListGrupoToAttach);
            }
            opcionestudio.setGrupoList(attachedGrupoList);
            em.persist(opcionestudio);
            for (Grupo grupoListGrupo : opcionestudio.getGrupoList()) {
                Opcionestudio oldOpcionestudioOfGrupoListGrupo = grupoListGrupo.getOpcionestudio();
                grupoListGrupo.setOpcionestudio(opcionestudio);
                grupoListGrupo = em.merge(grupoListGrupo);
                if (oldOpcionestudioOfGrupoListGrupo != null) {
                    oldOpcionestudioOfGrupoListGrupo.getGrupoList().remove(grupoListGrupo);
                    oldOpcionestudioOfGrupoListGrupo = em.merge(oldOpcionestudioOfGrupoListGrupo);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findOpcionestudio(opcionestudio.getOpcionestudio()) != null) {
                throw new PreexistingEntityException("Opcionestudio " + opcionestudio + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Opcionestudio opcionestudio) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Opcionestudio persistentOpcionestudio = em.find(Opcionestudio.class, opcionestudio.getOpcionestudio());
            List<Grupo> grupoListOld = persistentOpcionestudio.getGrupoList();
            List<Grupo> grupoListNew = opcionestudio.getGrupoList();
            List<Grupo> attachedGrupoListNew = new ArrayList<Grupo>();
            for (Grupo grupoListNewGrupoToAttach : grupoListNew) {
                grupoListNewGrupoToAttach = em.getReference(grupoListNewGrupoToAttach.getClass(), grupoListNewGrupoToAttach.getIdgrupo());
                attachedGrupoListNew.add(grupoListNewGrupoToAttach);
            }
            grupoListNew = attachedGrupoListNew;
            opcionestudio.setGrupoList(grupoListNew);
            opcionestudio = em.merge(opcionestudio);
            for (Grupo grupoListOldGrupo : grupoListOld) {
                if (!grupoListNew.contains(grupoListOldGrupo)) {
                    grupoListOldGrupo.setOpcionestudio(null);
                    grupoListOldGrupo = em.merge(grupoListOldGrupo);
                }
            }
            for (Grupo grupoListNewGrupo : grupoListNew) {
                if (!grupoListOld.contains(grupoListNewGrupo)) {
                    Opcionestudio oldOpcionestudioOfGrupoListNewGrupo = grupoListNewGrupo.getOpcionestudio();
                    grupoListNewGrupo.setOpcionestudio(opcionestudio);
                    grupoListNewGrupo = em.merge(grupoListNewGrupo);
                    if (oldOpcionestudioOfGrupoListNewGrupo != null && !oldOpcionestudioOfGrupoListNewGrupo.equals(opcionestudio)) {
                        oldOpcionestudioOfGrupoListNewGrupo.getGrupoList().remove(grupoListNewGrupo);
                        oldOpcionestudioOfGrupoListNewGrupo = em.merge(oldOpcionestudioOfGrupoListNewGrupo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = opcionestudio.getOpcionestudio();
                if (findOpcionestudio(id) == null) {
                    throw new NonexistentEntityException("The opcionestudio with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Opcionestudio opcionestudio;
            try {
                opcionestudio = em.getReference(Opcionestudio.class, id);
                opcionestudio.getOpcionestudio();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The opcionestudio with id " + id + " no longer exists.", enfe);
            }
            List<Grupo> grupoList = opcionestudio.getGrupoList();
            for (Grupo grupoListGrupo : grupoList) {
                grupoListGrupo.setOpcionestudio(null);
                grupoListGrupo = em.merge(grupoListGrupo);
            }
            em.remove(opcionestudio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Opcionestudio> findOpcionestudioEntities() {
        return findOpcionestudioEntities(true, -1, -1);
    }

    public List<Opcionestudio> findOpcionestudioEntities(int maxResults, int firstResult) {
        return findOpcionestudioEntities(false, maxResults, firstResult);
    }

    private List<Opcionestudio> findOpcionestudioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Opcionestudio as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Opcionestudio findOpcionestudio(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Opcionestudio.class, id);
        } finally {
            em.close();
        }
    }

    public int getOpcionestudioCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Opcionestudio as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
