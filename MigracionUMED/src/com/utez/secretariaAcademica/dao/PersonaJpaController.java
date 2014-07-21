/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.secretariaAcademica.dao;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.Usuario;
import com.utez.integracion.entity.SeguroSocial;
import com.utez.integracion.entity.TipoSexo;
import com.utez.integracion.entity.TipoEstadocivil;
import com.utez.integracion.entity.Municipio;
import com.utez.integracion.entity.Asentamiento;
import com.utez.integracion.entity.Fotografia;
import java.util.ArrayList;
import java.util.List;
import com.utez.integracion.entity.RecursoHumano;
import com.utez.integracion.entity.CalificacionModulo;
import com.utez.integracion.entity.Telefono;
import com.utez.integracion.entity.Aspirante;
import com.utez.integracion.entity.Persona;
import com.utez.secretariaAcademica.dao.exceptions.IllegalOrphanException;
import com.utez.secretariaAcademica.dao.exceptions.NonexistentEntityException;
import com.utez.secretariaAcademica.dao.exceptions.PreexistingEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class PersonaJpaController implements Serializable {

    public PersonaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Persona persona) throws PreexistingEntityException, Exception {
        if (persona.getFotografiaList() == null) {
            persona.setFotografiaList(new ArrayList<Fotografia>());
        }
        if (persona.getRecursoHumanoList() == null) {
            persona.setRecursoHumanoList(new ArrayList<RecursoHumano>());
        }
        if (persona.getCalificacionModuloList() == null) {
            persona.setCalificacionModuloList(new ArrayList<CalificacionModulo>());
        }
        if (persona.getTelefonoList() == null) {
            persona.setTelefonoList(new ArrayList<Telefono>());
        }
        if (persona.getAspiranteList() == null) {
            persona.setAspiranteList(new ArrayList<Aspirante>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuario = persona.getUsuario();
            if (usuario != null) {
                usuario = em.getReference(usuario.getClass(), usuario.getCurp());
                persona.setUsuario(usuario);
            }
            SeguroSocial seguroSocial = persona.getSeguroSocial();
            if (seguroSocial != null) {
                seguroSocial = em.getReference(seguroSocial.getClass(), seguroSocial.getCurp());
                persona.setSeguroSocial(seguroSocial);
            }
            TipoSexo idTiposexo = persona.getIdTiposexo();
            if (idTiposexo != null) {
                idTiposexo = em.getReference(idTiposexo.getClass(), idTiposexo.getIdTiposexo());
                persona.setIdTiposexo(idTiposexo);
            }
            TipoEstadocivil idTipoestadocivil = persona.getIdTipoestadocivil();
            if (idTipoestadocivil != null) {
                idTipoestadocivil = em.getReference(idTipoestadocivil.getClass(), idTipoestadocivil.getIdTipoestadocivil());
                persona.setIdTipoestadocivil(idTipoestadocivil);
            }
            Municipio idMunicipionacimiento = persona.getIdMunicipionacimiento();
            if (idMunicipionacimiento != null) {
                idMunicipionacimiento = em.getReference(idMunicipionacimiento.getClass(), idMunicipionacimiento.getIdMunicipio());
                persona.setIdMunicipionacimiento(idMunicipionacimiento);
            }
            Asentamiento idAsentamiento = persona.getIdAsentamiento();
            if (idAsentamiento != null) {
                idAsentamiento = em.getReference(idAsentamiento.getClass(), idAsentamiento.getIdAsentamiento());
                persona.setIdAsentamiento(idAsentamiento);
            }
            List<Fotografia> attachedFotografiaList = new ArrayList<Fotografia>();
            for (Fotografia fotografiaListFotografiaToAttach : persona.getFotografiaList()) {
                fotografiaListFotografiaToAttach = em.getReference(fotografiaListFotografiaToAttach.getClass(), fotografiaListFotografiaToAttach.getIdFotografia());
                attachedFotografiaList.add(fotografiaListFotografiaToAttach);
            }
            persona.setFotografiaList(attachedFotografiaList);
            List<RecursoHumano> attachedRecursoHumanoList = new ArrayList<RecursoHumano>();
            for (RecursoHumano recursoHumanoListRecursoHumanoToAttach : persona.getRecursoHumanoList()) {
                recursoHumanoListRecursoHumanoToAttach = em.getReference(recursoHumanoListRecursoHumanoToAttach.getClass(), recursoHumanoListRecursoHumanoToAttach.getIdRecursohumano());
                attachedRecursoHumanoList.add(recursoHumanoListRecursoHumanoToAttach);
            }
            persona.setRecursoHumanoList(attachedRecursoHumanoList);
            List<CalificacionModulo> attachedCalificacionModuloList = new ArrayList<CalificacionModulo>();
            for (CalificacionModulo calificacionModuloListCalificacionModuloToAttach : persona.getCalificacionModuloList()) {
                calificacionModuloListCalificacionModuloToAttach = em.getReference(calificacionModuloListCalificacionModuloToAttach.getClass(), calificacionModuloListCalificacionModuloToAttach.getIdCalificacionevento());
                attachedCalificacionModuloList.add(calificacionModuloListCalificacionModuloToAttach);
            }
            persona.setCalificacionModuloList(attachedCalificacionModuloList);
            List<Telefono> attachedTelefonoList = new ArrayList<Telefono>();
            for (Telefono telefonoListTelefonoToAttach : persona.getTelefonoList()) {
                telefonoListTelefonoToAttach = em.getReference(telefonoListTelefonoToAttach.getClass(), telefonoListTelefonoToAttach.getIdTelefono());
                attachedTelefonoList.add(telefonoListTelefonoToAttach);
            }
            persona.setTelefonoList(attachedTelefonoList);
            List<Aspirante> attachedAspiranteList = new ArrayList<Aspirante>();
            for (Aspirante aspiranteListAspiranteToAttach : persona.getAspiranteList()) {
                aspiranteListAspiranteToAttach = em.getReference(aspiranteListAspiranteToAttach.getClass(), aspiranteListAspiranteToAttach.getIdAspirante());
                attachedAspiranteList.add(aspiranteListAspiranteToAttach);
            }
            persona.setAspiranteList(attachedAspiranteList);
            em.persist(persona);
            if (usuario != null) {
                Persona oldPersonaOfUsuario = usuario.getPersona();
                if (oldPersonaOfUsuario != null) {
                    oldPersonaOfUsuario.setUsuario(null);
                    oldPersonaOfUsuario = em.merge(oldPersonaOfUsuario);
                }
                usuario.setPersona(persona);
                usuario = em.merge(usuario);
            }
            if (seguroSocial != null) {
                Persona oldPersonaOfSeguroSocial = seguroSocial.getPersona();
                if (oldPersonaOfSeguroSocial != null) {
                    oldPersonaOfSeguroSocial.setSeguroSocial(null);
                    oldPersonaOfSeguroSocial = em.merge(oldPersonaOfSeguroSocial);
                }
                seguroSocial.setPersona(persona);
                seguroSocial = em.merge(seguroSocial);
            }
            if (idTiposexo != null) {
                idTiposexo.getPersonaList().add(persona);
                idTiposexo = em.merge(idTiposexo);
            }
            if (idTipoestadocivil != null) {
                idTipoestadocivil.getPersonaList().add(persona);
                idTipoestadocivil = em.merge(idTipoestadocivil);
            }
            if (idMunicipionacimiento != null) {
                idMunicipionacimiento.getPersonaList().add(persona);
                idMunicipionacimiento = em.merge(idMunicipionacimiento);
            }
            if (idAsentamiento != null) {
                idAsentamiento.getPersonaList().add(persona);
                idAsentamiento = em.merge(idAsentamiento);
            }
            for (Fotografia fotografiaListFotografia : persona.getFotografiaList()) {
                Persona oldCurpOfFotografiaListFotografia = fotografiaListFotografia.getCurp();
                fotografiaListFotografia.setCurp(persona);
                fotografiaListFotografia = em.merge(fotografiaListFotografia);
                if (oldCurpOfFotografiaListFotografia != null) {
                    oldCurpOfFotografiaListFotografia.getFotografiaList().remove(fotografiaListFotografia);
                    oldCurpOfFotografiaListFotografia = em.merge(oldCurpOfFotografiaListFotografia);
                }
            }
            for (RecursoHumano recursoHumanoListRecursoHumano : persona.getRecursoHumanoList()) {
                Persona oldCurpOfRecursoHumanoListRecursoHumano = recursoHumanoListRecursoHumano.getCurp();
                recursoHumanoListRecursoHumano.setCurp(persona);
                recursoHumanoListRecursoHumano = em.merge(recursoHumanoListRecursoHumano);
                if (oldCurpOfRecursoHumanoListRecursoHumano != null) {
                    oldCurpOfRecursoHumanoListRecursoHumano.getRecursoHumanoList().remove(recursoHumanoListRecursoHumano);
                    oldCurpOfRecursoHumanoListRecursoHumano = em.merge(oldCurpOfRecursoHumanoListRecursoHumano);
                }
            }
            for (CalificacionModulo calificacionModuloListCalificacionModulo : persona.getCalificacionModuloList()) {
                Persona oldCurpOfCalificacionModuloListCalificacionModulo = calificacionModuloListCalificacionModulo.getCurp();
                calificacionModuloListCalificacionModulo.setCurp(persona);
                calificacionModuloListCalificacionModulo = em.merge(calificacionModuloListCalificacionModulo);
                if (oldCurpOfCalificacionModuloListCalificacionModulo != null) {
                    oldCurpOfCalificacionModuloListCalificacionModulo.getCalificacionModuloList().remove(calificacionModuloListCalificacionModulo);
                    oldCurpOfCalificacionModuloListCalificacionModulo = em.merge(oldCurpOfCalificacionModuloListCalificacionModulo);
                }
            }
            for (Telefono telefonoListTelefono : persona.getTelefonoList()) {
                Persona oldCurpOfTelefonoListTelefono = telefonoListTelefono.getCurp();
                telefonoListTelefono.setCurp(persona);
                telefonoListTelefono = em.merge(telefonoListTelefono);
                if (oldCurpOfTelefonoListTelefono != null) {
                    oldCurpOfTelefonoListTelefono.getTelefonoList().remove(telefonoListTelefono);
                    oldCurpOfTelefonoListTelefono = em.merge(oldCurpOfTelefonoListTelefono);
                }
            }
            for (Aspirante aspiranteListAspirante : persona.getAspiranteList()) {
                Persona oldCurpOfAspiranteListAspirante = aspiranteListAspirante.getCurp();
                aspiranteListAspirante.setCurp(persona);
                aspiranteListAspirante = em.merge(aspiranteListAspirante);
                if (oldCurpOfAspiranteListAspirante != null) {
                    oldCurpOfAspiranteListAspirante.getAspiranteList().remove(aspiranteListAspirante);
                    oldCurpOfAspiranteListAspirante = em.merge(oldCurpOfAspiranteListAspirante);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPersona(persona.getCurp()) != null) {
                throw new PreexistingEntityException("Persona " + persona + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Persona persona) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Persona persistentPersona = em.find(Persona.class, persona.getCurp());
            Usuario usuarioOld = persistentPersona.getUsuario();
            Usuario usuarioNew = persona.getUsuario();
            SeguroSocial seguroSocialOld = persistentPersona.getSeguroSocial();
            SeguroSocial seguroSocialNew = persona.getSeguroSocial();
            TipoSexo idTiposexoOld = persistentPersona.getIdTiposexo();
            TipoSexo idTiposexoNew = persona.getIdTiposexo();
            TipoEstadocivil idTipoestadocivilOld = persistentPersona.getIdTipoestadocivil();
            TipoEstadocivil idTipoestadocivilNew = persona.getIdTipoestadocivil();
            Municipio idMunicipionacimientoOld = persistentPersona.getIdMunicipionacimiento();
            Municipio idMunicipionacimientoNew = persona.getIdMunicipionacimiento();
            Asentamiento idAsentamientoOld = persistentPersona.getIdAsentamiento();
            Asentamiento idAsentamientoNew = persona.getIdAsentamiento();
            List<Fotografia> fotografiaListOld = persistentPersona.getFotografiaList();
            List<Fotografia> fotografiaListNew = persona.getFotografiaList();
            List<RecursoHumano> recursoHumanoListOld = persistentPersona.getRecursoHumanoList();
            List<RecursoHumano> recursoHumanoListNew = persona.getRecursoHumanoList();
            List<CalificacionModulo> calificacionModuloListOld = persistentPersona.getCalificacionModuloList();
            List<CalificacionModulo> calificacionModuloListNew = persona.getCalificacionModuloList();
            List<Telefono> telefonoListOld = persistentPersona.getTelefonoList();
            List<Telefono> telefonoListNew = persona.getTelefonoList();
            List<Aspirante> aspiranteListOld = persistentPersona.getAspiranteList();
            List<Aspirante> aspiranteListNew = persona.getAspiranteList();
            List<String> illegalOrphanMessages = null;
            if (usuarioOld != null && !usuarioOld.equals(usuarioNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Usuario " + usuarioOld + " since its persona field is not nullable.");
            }
            if (seguroSocialOld != null && !seguroSocialOld.equals(seguroSocialNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain SeguroSocial " + seguroSocialOld + " since its persona field is not nullable.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (usuarioNew != null) {
                usuarioNew = em.getReference(usuarioNew.getClass(), usuarioNew.getCurp());
                persona.setUsuario(usuarioNew);
            }
            if (seguroSocialNew != null) {
                seguroSocialNew = em.getReference(seguroSocialNew.getClass(), seguroSocialNew.getCurp());
                persona.setSeguroSocial(seguroSocialNew);
            }
            if (idTiposexoNew != null) {
                idTiposexoNew = em.getReference(idTiposexoNew.getClass(), idTiposexoNew.getIdTiposexo());
                persona.setIdTiposexo(idTiposexoNew);
            }
            if (idTipoestadocivilNew != null) {
                idTipoestadocivilNew = em.getReference(idTipoestadocivilNew.getClass(), idTipoestadocivilNew.getIdTipoestadocivil());
                persona.setIdTipoestadocivil(idTipoestadocivilNew);
            }
            if (idMunicipionacimientoNew != null) {
                idMunicipionacimientoNew = em.getReference(idMunicipionacimientoNew.getClass(), idMunicipionacimientoNew.getIdMunicipio());
                persona.setIdMunicipionacimiento(idMunicipionacimientoNew);
            }
            if (idAsentamientoNew != null) {
                idAsentamientoNew = em.getReference(idAsentamientoNew.getClass(), idAsentamientoNew.getIdAsentamiento());
                persona.setIdAsentamiento(idAsentamientoNew);
            }
            List<Fotografia> attachedFotografiaListNew = new ArrayList<Fotografia>();
            for (Fotografia fotografiaListNewFotografiaToAttach : fotografiaListNew) {
                fotografiaListNewFotografiaToAttach = em.getReference(fotografiaListNewFotografiaToAttach.getClass(), fotografiaListNewFotografiaToAttach.getIdFotografia());
                attachedFotografiaListNew.add(fotografiaListNewFotografiaToAttach);
            }
            fotografiaListNew = attachedFotografiaListNew;
            persona.setFotografiaList(fotografiaListNew);
            List<RecursoHumano> attachedRecursoHumanoListNew = new ArrayList<RecursoHumano>();
            for (RecursoHumano recursoHumanoListNewRecursoHumanoToAttach : recursoHumanoListNew) {
                recursoHumanoListNewRecursoHumanoToAttach = em.getReference(recursoHumanoListNewRecursoHumanoToAttach.getClass(), recursoHumanoListNewRecursoHumanoToAttach.getIdRecursohumano());
                attachedRecursoHumanoListNew.add(recursoHumanoListNewRecursoHumanoToAttach);
            }
            recursoHumanoListNew = attachedRecursoHumanoListNew;
            persona.setRecursoHumanoList(recursoHumanoListNew);
            List<CalificacionModulo> attachedCalificacionModuloListNew = new ArrayList<CalificacionModulo>();
            for (CalificacionModulo calificacionModuloListNewCalificacionModuloToAttach : calificacionModuloListNew) {
                calificacionModuloListNewCalificacionModuloToAttach = em.getReference(calificacionModuloListNewCalificacionModuloToAttach.getClass(), calificacionModuloListNewCalificacionModuloToAttach.getIdCalificacionevento());
                attachedCalificacionModuloListNew.add(calificacionModuloListNewCalificacionModuloToAttach);
            }
            calificacionModuloListNew = attachedCalificacionModuloListNew;
            persona.setCalificacionModuloList(calificacionModuloListNew);
            List<Telefono> attachedTelefonoListNew = new ArrayList<Telefono>();
            for (Telefono telefonoListNewTelefonoToAttach : telefonoListNew) {
                telefonoListNewTelefonoToAttach = em.getReference(telefonoListNewTelefonoToAttach.getClass(), telefonoListNewTelefonoToAttach.getIdTelefono());
                attachedTelefonoListNew.add(telefonoListNewTelefonoToAttach);
            }
            telefonoListNew = attachedTelefonoListNew;
            persona.setTelefonoList(telefonoListNew);
            List<Aspirante> attachedAspiranteListNew = new ArrayList<Aspirante>();
            for (Aspirante aspiranteListNewAspiranteToAttach : aspiranteListNew) {
                aspiranteListNewAspiranteToAttach = em.getReference(aspiranteListNewAspiranteToAttach.getClass(), aspiranteListNewAspiranteToAttach.getIdAspirante());
                attachedAspiranteListNew.add(aspiranteListNewAspiranteToAttach);
            }
            aspiranteListNew = attachedAspiranteListNew;
            persona.setAspiranteList(aspiranteListNew);
            persona = em.merge(persona);
            if (usuarioNew != null && !usuarioNew.equals(usuarioOld)) {
                Persona oldPersonaOfUsuario = usuarioNew.getPersona();
                if (oldPersonaOfUsuario != null) {
                    oldPersonaOfUsuario.setUsuario(null);
                    oldPersonaOfUsuario = em.merge(oldPersonaOfUsuario);
                }
                usuarioNew.setPersona(persona);
                usuarioNew = em.merge(usuarioNew);
            }
            if (seguroSocialNew != null && !seguroSocialNew.equals(seguroSocialOld)) {
                Persona oldPersonaOfSeguroSocial = seguroSocialNew.getPersona();
                if (oldPersonaOfSeguroSocial != null) {
                    oldPersonaOfSeguroSocial.setSeguroSocial(null);
                    oldPersonaOfSeguroSocial = em.merge(oldPersonaOfSeguroSocial);
                }
                seguroSocialNew.setPersona(persona);
                seguroSocialNew = em.merge(seguroSocialNew);
            }
            if (idTiposexoOld != null && !idTiposexoOld.equals(idTiposexoNew)) {
                idTiposexoOld.getPersonaList().remove(persona);
                idTiposexoOld = em.merge(idTiposexoOld);
            }
            if (idTiposexoNew != null && !idTiposexoNew.equals(idTiposexoOld)) {
                idTiposexoNew.getPersonaList().add(persona);
                idTiposexoNew = em.merge(idTiposexoNew);
            }
            if (idTipoestadocivilOld != null && !idTipoestadocivilOld.equals(idTipoestadocivilNew)) {
                idTipoestadocivilOld.getPersonaList().remove(persona);
                idTipoestadocivilOld = em.merge(idTipoestadocivilOld);
            }
            if (idTipoestadocivilNew != null && !idTipoestadocivilNew.equals(idTipoestadocivilOld)) {
                idTipoestadocivilNew.getPersonaList().add(persona);
                idTipoestadocivilNew = em.merge(idTipoestadocivilNew);
            }
            if (idMunicipionacimientoOld != null && !idMunicipionacimientoOld.equals(idMunicipionacimientoNew)) {
                idMunicipionacimientoOld.getPersonaList().remove(persona);
                idMunicipionacimientoOld = em.merge(idMunicipionacimientoOld);
            }
            if (idMunicipionacimientoNew != null && !idMunicipionacimientoNew.equals(idMunicipionacimientoOld)) {
                idMunicipionacimientoNew.getPersonaList().add(persona);
                idMunicipionacimientoNew = em.merge(idMunicipionacimientoNew);
            }
            if (idAsentamientoOld != null && !idAsentamientoOld.equals(idAsentamientoNew)) {
                idAsentamientoOld.getPersonaList().remove(persona);
                idAsentamientoOld = em.merge(idAsentamientoOld);
            }
            if (idAsentamientoNew != null && !idAsentamientoNew.equals(idAsentamientoOld)) {
                idAsentamientoNew.getPersonaList().add(persona);
                idAsentamientoNew = em.merge(idAsentamientoNew);
            }
            for (Fotografia fotografiaListOldFotografia : fotografiaListOld) {
                if (!fotografiaListNew.contains(fotografiaListOldFotografia)) {
                    fotografiaListOldFotografia.setCurp(null);
                    fotografiaListOldFotografia = em.merge(fotografiaListOldFotografia);
                }
            }
            for (Fotografia fotografiaListNewFotografia : fotografiaListNew) {
                if (!fotografiaListOld.contains(fotografiaListNewFotografia)) {
                    Persona oldCurpOfFotografiaListNewFotografia = fotografiaListNewFotografia.getCurp();
                    fotografiaListNewFotografia.setCurp(persona);
                    fotografiaListNewFotografia = em.merge(fotografiaListNewFotografia);
                    if (oldCurpOfFotografiaListNewFotografia != null && !oldCurpOfFotografiaListNewFotografia.equals(persona)) {
                        oldCurpOfFotografiaListNewFotografia.getFotografiaList().remove(fotografiaListNewFotografia);
                        oldCurpOfFotografiaListNewFotografia = em.merge(oldCurpOfFotografiaListNewFotografia);
                    }
                }
            }
            for (RecursoHumano recursoHumanoListOldRecursoHumano : recursoHumanoListOld) {
                if (!recursoHumanoListNew.contains(recursoHumanoListOldRecursoHumano)) {
                    recursoHumanoListOldRecursoHumano.setCurp(null);
                    recursoHumanoListOldRecursoHumano = em.merge(recursoHumanoListOldRecursoHumano);
                }
            }
            for (RecursoHumano recursoHumanoListNewRecursoHumano : recursoHumanoListNew) {
                if (!recursoHumanoListOld.contains(recursoHumanoListNewRecursoHumano)) {
                    Persona oldCurpOfRecursoHumanoListNewRecursoHumano = recursoHumanoListNewRecursoHumano.getCurp();
                    recursoHumanoListNewRecursoHumano.setCurp(persona);
                    recursoHumanoListNewRecursoHumano = em.merge(recursoHumanoListNewRecursoHumano);
                    if (oldCurpOfRecursoHumanoListNewRecursoHumano != null && !oldCurpOfRecursoHumanoListNewRecursoHumano.equals(persona)) {
                        oldCurpOfRecursoHumanoListNewRecursoHumano.getRecursoHumanoList().remove(recursoHumanoListNewRecursoHumano);
                        oldCurpOfRecursoHumanoListNewRecursoHumano = em.merge(oldCurpOfRecursoHumanoListNewRecursoHumano);
                    }
                }
            }
            for (CalificacionModulo calificacionModuloListOldCalificacionModulo : calificacionModuloListOld) {
                if (!calificacionModuloListNew.contains(calificacionModuloListOldCalificacionModulo)) {
                    calificacionModuloListOldCalificacionModulo.setCurp(null);
                    calificacionModuloListOldCalificacionModulo = em.merge(calificacionModuloListOldCalificacionModulo);
                }
            }
            for (CalificacionModulo calificacionModuloListNewCalificacionModulo : calificacionModuloListNew) {
                if (!calificacionModuloListOld.contains(calificacionModuloListNewCalificacionModulo)) {
                    Persona oldCurpOfCalificacionModuloListNewCalificacionModulo = calificacionModuloListNewCalificacionModulo.getCurp();
                    calificacionModuloListNewCalificacionModulo.setCurp(persona);
                    calificacionModuloListNewCalificacionModulo = em.merge(calificacionModuloListNewCalificacionModulo);
                    if (oldCurpOfCalificacionModuloListNewCalificacionModulo != null && !oldCurpOfCalificacionModuloListNewCalificacionModulo.equals(persona)) {
                        oldCurpOfCalificacionModuloListNewCalificacionModulo.getCalificacionModuloList().remove(calificacionModuloListNewCalificacionModulo);
                        oldCurpOfCalificacionModuloListNewCalificacionModulo = em.merge(oldCurpOfCalificacionModuloListNewCalificacionModulo);
                    }
                }
            }
            for (Telefono telefonoListOldTelefono : telefonoListOld) {
                if (!telefonoListNew.contains(telefonoListOldTelefono)) {
                    telefonoListOldTelefono.setCurp(null);
                    telefonoListOldTelefono = em.merge(telefonoListOldTelefono);
                }
            }
            for (Telefono telefonoListNewTelefono : telefonoListNew) {
                if (!telefonoListOld.contains(telefonoListNewTelefono)) {
                    Persona oldCurpOfTelefonoListNewTelefono = telefonoListNewTelefono.getCurp();
                    telefonoListNewTelefono.setCurp(persona);
                    telefonoListNewTelefono = em.merge(telefonoListNewTelefono);
                    if (oldCurpOfTelefonoListNewTelefono != null && !oldCurpOfTelefonoListNewTelefono.equals(persona)) {
                        oldCurpOfTelefonoListNewTelefono.getTelefonoList().remove(telefonoListNewTelefono);
                        oldCurpOfTelefonoListNewTelefono = em.merge(oldCurpOfTelefonoListNewTelefono);
                    }
                }
            }
            for (Aspirante aspiranteListOldAspirante : aspiranteListOld) {
                if (!aspiranteListNew.contains(aspiranteListOldAspirante)) {
                    aspiranteListOldAspirante.setCurp(null);
                    aspiranteListOldAspirante = em.merge(aspiranteListOldAspirante);
                }
            }
            for (Aspirante aspiranteListNewAspirante : aspiranteListNew) {
                if (!aspiranteListOld.contains(aspiranteListNewAspirante)) {
                    Persona oldCurpOfAspiranteListNewAspirante = aspiranteListNewAspirante.getCurp();
                    aspiranteListNewAspirante.setCurp(persona);
                    aspiranteListNewAspirante = em.merge(aspiranteListNewAspirante);
                    if (oldCurpOfAspiranteListNewAspirante != null && !oldCurpOfAspiranteListNewAspirante.equals(persona)) {
                        oldCurpOfAspiranteListNewAspirante.getAspiranteList().remove(aspiranteListNewAspirante);
                        oldCurpOfAspiranteListNewAspirante = em.merge(oldCurpOfAspiranteListNewAspirante);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = persona.getCurp();
                if (findPersona(id) == null) {
                    throw new NonexistentEntityException("The persona with id " + id + " no longer exists.");
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
            Persona persona;
            try {
                persona = em.getReference(Persona.class, id);
                persona.getCurp();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The persona with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Usuario usuarioOrphanCheck = persona.getUsuario();
            if (usuarioOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Persona (" + persona + ") cannot be destroyed since the Usuario " + usuarioOrphanCheck + " in its usuario field has a non-nullable persona field.");
            }
            SeguroSocial seguroSocialOrphanCheck = persona.getSeguroSocial();
            if (seguroSocialOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Persona (" + persona + ") cannot be destroyed since the SeguroSocial " + seguroSocialOrphanCheck + " in its seguroSocial field has a non-nullable persona field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            TipoSexo idTiposexo = persona.getIdTiposexo();
            if (idTiposexo != null) {
                idTiposexo.getPersonaList().remove(persona);
                idTiposexo = em.merge(idTiposexo);
            }
            TipoEstadocivil idTipoestadocivil = persona.getIdTipoestadocivil();
            if (idTipoestadocivil != null) {
                idTipoestadocivil.getPersonaList().remove(persona);
                idTipoestadocivil = em.merge(idTipoestadocivil);
            }
            Municipio idMunicipionacimiento = persona.getIdMunicipionacimiento();
            if (idMunicipionacimiento != null) {
                idMunicipionacimiento.getPersonaList().remove(persona);
                idMunicipionacimiento = em.merge(idMunicipionacimiento);
            }
            Asentamiento idAsentamiento = persona.getIdAsentamiento();
            if (idAsentamiento != null) {
                idAsentamiento.getPersonaList().remove(persona);
                idAsentamiento = em.merge(idAsentamiento);
            }
            List<Fotografia> fotografiaList = persona.getFotografiaList();
            for (Fotografia fotografiaListFotografia : fotografiaList) {
                fotografiaListFotografia.setCurp(null);
                fotografiaListFotografia = em.merge(fotografiaListFotografia);
            }
            List<RecursoHumano> recursoHumanoList = persona.getRecursoHumanoList();
            for (RecursoHumano recursoHumanoListRecursoHumano : recursoHumanoList) {
                recursoHumanoListRecursoHumano.setCurp(null);
                recursoHumanoListRecursoHumano = em.merge(recursoHumanoListRecursoHumano);
            }
            List<CalificacionModulo> calificacionModuloList = persona.getCalificacionModuloList();
            for (CalificacionModulo calificacionModuloListCalificacionModulo : calificacionModuloList) {
                calificacionModuloListCalificacionModulo.setCurp(null);
                calificacionModuloListCalificacionModulo = em.merge(calificacionModuloListCalificacionModulo);
            }
            List<Telefono> telefonoList = persona.getTelefonoList();
            for (Telefono telefonoListTelefono : telefonoList) {
                telefonoListTelefono.setCurp(null);
                telefonoListTelefono = em.merge(telefonoListTelefono);
            }
            List<Aspirante> aspiranteList = persona.getAspiranteList();
            for (Aspirante aspiranteListAspirante : aspiranteList) {
                aspiranteListAspirante.setCurp(null);
                aspiranteListAspirante = em.merge(aspiranteListAspirante);
            }
            em.remove(persona);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Persona> findPersonaEntities() {
        return findPersonaEntities(true, -1, -1);
    }

    public List<Persona> findPersonaEntities(int maxResults, int firstResult) {
        return findPersonaEntities(false, maxResults, firstResult);
    }

    private List<Persona> findPersonaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Persona as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Persona findPersona(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Persona.class, id);
        } finally {
            em.close();
        }
    }

    public int getPersonaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Persona as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
