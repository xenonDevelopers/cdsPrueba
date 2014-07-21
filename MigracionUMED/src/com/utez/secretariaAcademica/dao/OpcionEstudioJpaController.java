/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.secretariaAcademica.dao;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.AspiranteOpcion;
import com.utez.integracion.entity.OpcionEstudio;
import com.utez.secretariaAcademica.dao.exceptions.IllegalOrphanException;
import com.utez.secretariaAcademica.dao.exceptions.NonexistentEntityException;
import com.utez.secretariaAcademica.dao.exceptions.PreexistingEntityException;
import java.util.ArrayList;
import java.util.List;
import com.utez.secretariaAcademica.entity.Grupo;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class OpcionEstudioJpaController implements Serializable {

    public OpcionEstudioJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(OpcionEstudio opcionEstudio) throws PreexistingEntityException, Exception {
        if (opcionEstudio.getAspiranteOpcionList() == null) {
            opcionEstudio.setAspiranteOpcionList(new ArrayList<AspiranteOpcion>());
        }
        if (opcionEstudio.getGrupoList() == null) {
            opcionEstudio.setGrupoList(new ArrayList<Grupo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<AspiranteOpcion> attachedAspiranteOpcionList = new ArrayList<AspiranteOpcion>();
            for (AspiranteOpcion aspiranteOpcionListAspiranteOpcionToAttach : opcionEstudio.getAspiranteOpcionList()) {
                aspiranteOpcionListAspiranteOpcionToAttach = em.getReference(aspiranteOpcionListAspiranteOpcionToAttach.getClass(), aspiranteOpcionListAspiranteOpcionToAttach.getAspiranteOpcionPK());
                attachedAspiranteOpcionList.add(aspiranteOpcionListAspiranteOpcionToAttach);
            }
            opcionEstudio.setAspiranteOpcionList(attachedAspiranteOpcionList);
            List<Grupo> attachedGrupoList = new ArrayList<Grupo>();
            for (Grupo grupoListGrupoToAttach : opcionEstudio.getGrupoList()) {
                grupoListGrupoToAttach = em.getReference(grupoListGrupoToAttach.getClass(), grupoListGrupoToAttach.getIdgrupo());
                attachedGrupoList.add(grupoListGrupoToAttach);
            }
            opcionEstudio.setGrupoList(attachedGrupoList);
            em.persist(opcionEstudio);
            for (AspiranteOpcion aspiranteOpcionListAspiranteOpcion : opcionEstudio.getAspiranteOpcionList()) {
                OpcionEstudio oldOpcionEstudioOfAspiranteOpcionListAspiranteOpcion = aspiranteOpcionListAspiranteOpcion.getOpcionEstudio();
                aspiranteOpcionListAspiranteOpcion.setOpcionEstudio(opcionEstudio);
                aspiranteOpcionListAspiranteOpcion = em.merge(aspiranteOpcionListAspiranteOpcion);
                if (oldOpcionEstudioOfAspiranteOpcionListAspiranteOpcion != null) {
                    oldOpcionEstudioOfAspiranteOpcionListAspiranteOpcion.getAspiranteOpcionList().remove(aspiranteOpcionListAspiranteOpcion);
                    oldOpcionEstudioOfAspiranteOpcionListAspiranteOpcion = em.merge(oldOpcionEstudioOfAspiranteOpcionListAspiranteOpcion);
                }
            }
            for (Grupo grupoListGrupo : opcionEstudio.getGrupoList()) {
                OpcionEstudio oldIdOpcionestudioOfGrupoListGrupo = grupoListGrupo.getIdOpcionestudio();
                grupoListGrupo.setIdOpcionestudio(opcionEstudio);
                grupoListGrupo = em.merge(grupoListGrupo);
                if (oldIdOpcionestudioOfGrupoListGrupo != null) {
                    oldIdOpcionestudioOfGrupoListGrupo.getGrupoList().remove(grupoListGrupo);
                    oldIdOpcionestudioOfGrupoListGrupo = em.merge(oldIdOpcionestudioOfGrupoListGrupo);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findOpcionEstudio(opcionEstudio.getIdOpcionestudio()) != null) {
                throw new PreexistingEntityException("OpcionEstudio " + opcionEstudio + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(OpcionEstudio opcionEstudio) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OpcionEstudio persistentOpcionEstudio = em.find(OpcionEstudio.class, opcionEstudio.getIdOpcionestudio());
            List<AspiranteOpcion> aspiranteOpcionListOld = persistentOpcionEstudio.getAspiranteOpcionList();
            List<AspiranteOpcion> aspiranteOpcionListNew = opcionEstudio.getAspiranteOpcionList();
            List<Grupo> grupoListOld = persistentOpcionEstudio.getGrupoList();
            List<Grupo> grupoListNew = opcionEstudio.getGrupoList();
            List<String> illegalOrphanMessages = null;
            for (AspiranteOpcion aspiranteOpcionListOldAspiranteOpcion : aspiranteOpcionListOld) {
                if (!aspiranteOpcionListNew.contains(aspiranteOpcionListOldAspiranteOpcion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain AspiranteOpcion " + aspiranteOpcionListOldAspiranteOpcion + " since its opcionEstudio field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<AspiranteOpcion> attachedAspiranteOpcionListNew = new ArrayList<AspiranteOpcion>();
            for (AspiranteOpcion aspiranteOpcionListNewAspiranteOpcionToAttach : aspiranteOpcionListNew) {
                aspiranteOpcionListNewAspiranteOpcionToAttach = em.getReference(aspiranteOpcionListNewAspiranteOpcionToAttach.getClass(), aspiranteOpcionListNewAspiranteOpcionToAttach.getAspiranteOpcionPK());
                attachedAspiranteOpcionListNew.add(aspiranteOpcionListNewAspiranteOpcionToAttach);
            }
            aspiranteOpcionListNew = attachedAspiranteOpcionListNew;
            opcionEstudio.setAspiranteOpcionList(aspiranteOpcionListNew);
            List<Grupo> attachedGrupoListNew = new ArrayList<Grupo>();
            for (Grupo grupoListNewGrupoToAttach : grupoListNew) {
                grupoListNewGrupoToAttach = em.getReference(grupoListNewGrupoToAttach.getClass(), grupoListNewGrupoToAttach.getIdgrupo());
                attachedGrupoListNew.add(grupoListNewGrupoToAttach);
            }
            grupoListNew = attachedGrupoListNew;
            opcionEstudio.setGrupoList(grupoListNew);
            opcionEstudio = em.merge(opcionEstudio);
            for (AspiranteOpcion aspiranteOpcionListNewAspiranteOpcion : aspiranteOpcionListNew) {
                if (!aspiranteOpcionListOld.contains(aspiranteOpcionListNewAspiranteOpcion)) {
                    OpcionEstudio oldOpcionEstudioOfAspiranteOpcionListNewAspiranteOpcion = aspiranteOpcionListNewAspiranteOpcion.getOpcionEstudio();
                    aspiranteOpcionListNewAspiranteOpcion.setOpcionEstudio(opcionEstudio);
                    aspiranteOpcionListNewAspiranteOpcion = em.merge(aspiranteOpcionListNewAspiranteOpcion);
                    if (oldOpcionEstudioOfAspiranteOpcionListNewAspiranteOpcion != null && !oldOpcionEstudioOfAspiranteOpcionListNewAspiranteOpcion.equals(opcionEstudio)) {
                        oldOpcionEstudioOfAspiranteOpcionListNewAspiranteOpcion.getAspiranteOpcionList().remove(aspiranteOpcionListNewAspiranteOpcion);
                        oldOpcionEstudioOfAspiranteOpcionListNewAspiranteOpcion = em.merge(oldOpcionEstudioOfAspiranteOpcionListNewAspiranteOpcion);
                    }
                }
            }
            for (Grupo grupoListOldGrupo : grupoListOld) {
                if (!grupoListNew.contains(grupoListOldGrupo)) {
                    grupoListOldGrupo.setIdOpcionestudio(null);
                    grupoListOldGrupo = em.merge(grupoListOldGrupo);
                }
            }
            for (Grupo grupoListNewGrupo : grupoListNew) {
                if (!grupoListOld.contains(grupoListNewGrupo)) {
                    OpcionEstudio oldIdOpcionestudioOfGrupoListNewGrupo = grupoListNewGrupo.getIdOpcionestudio();
                    grupoListNewGrupo.setIdOpcionestudio(opcionEstudio);
                    grupoListNewGrupo = em.merge(grupoListNewGrupo);
                    if (oldIdOpcionestudioOfGrupoListNewGrupo != null && !oldIdOpcionestudioOfGrupoListNewGrupo.equals(opcionEstudio)) {
                        oldIdOpcionestudioOfGrupoListNewGrupo.getGrupoList().remove(grupoListNewGrupo);
                        oldIdOpcionestudioOfGrupoListNewGrupo = em.merge(oldIdOpcionestudioOfGrupoListNewGrupo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = opcionEstudio.getIdOpcionestudio();
                if (findOpcionEstudio(id) == null) {
                    throw new NonexistentEntityException("The opcionEstudio with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OpcionEstudio opcionEstudio;
            try {
                opcionEstudio = em.getReference(OpcionEstudio.class, id);
                opcionEstudio.getIdOpcionestudio();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The opcionEstudio with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<AspiranteOpcion> aspiranteOpcionListOrphanCheck = opcionEstudio.getAspiranteOpcionList();
            for (AspiranteOpcion aspiranteOpcionListOrphanCheckAspiranteOpcion : aspiranteOpcionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This OpcionEstudio (" + opcionEstudio + ") cannot be destroyed since the AspiranteOpcion " + aspiranteOpcionListOrphanCheckAspiranteOpcion + " in its aspiranteOpcionList field has a non-nullable opcionEstudio field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Grupo> grupoList = opcionEstudio.getGrupoList();
            for (Grupo grupoListGrupo : grupoList) {
                grupoListGrupo.setIdOpcionestudio(null);
                grupoListGrupo = em.merge(grupoListGrupo);
            }
            em.remove(opcionEstudio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<OpcionEstudio> findOpcionEstudioEntities() {
        return findOpcionEstudioEntities(true, -1, -1);
    }

    public List<OpcionEstudio> findOpcionEstudioEntities(int maxResults, int firstResult) {
        return findOpcionEstudioEntities(false, maxResults, firstResult);
    }

    private List<OpcionEstudio> findOpcionEstudioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from OpcionEstudio as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public OpcionEstudio findOpcionEstudio(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OpcionEstudio.class, id);
        } finally {
            em.close();
        }
    }

    public int getOpcionEstudioCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from OpcionEstudio as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
