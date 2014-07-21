/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.secretariaAcademica.entity;

import com.utez.integracion.entity.Asentamiento;
import com.utez.integracion.entity.AsignacionRecursoplantel;
import com.utez.integracion.entity.Evento;
import com.utez.integracion.entity.GrupoInduccion;
import com.utez.integracion.entity.PlanEstudio;
import com.utez.integracion.entity.SuspensionLabores;
import com.utez.integracion.entity.Vacacion;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "plantel")
@NamedQueries({
    @NamedQuery(name = "Plantel.findAll", query = "SELECT p FROM Plantel p"),
    @NamedQuery(name = "Plantel.findByIdplantel", query = "SELECT p FROM Plantel p WHERE p.idplantel = :idplantel"),
    @NamedQuery(name = "Plantel.findByNombre", query = "SELECT p FROM Plantel p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Plantel.findByLugar", query = "SELECT p FROM Plantel p WHERE p.lugar = :lugar"),
    @NamedQuery(name = "Plantel.findByDireccion", query = "SELECT p FROM Plantel p WHERE p.direccion = :direccion"),
    @NamedQuery(name = "Plantel.findByTelefono", query = "SELECT p FROM Plantel p WHERE p.telefono = :telefono"),
    @NamedQuery(name = "Plantel.findByCorreoelectronico", query = "SELECT p FROM Plantel p WHERE p.correoelectronico = :correoelectronico")})
public class Plantel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_plantel")
    private Long idPlantel;
    @Column(name = "nombre_plantel")
    private String nombrePlantel;
    @Column(name = "fax")
    private String fax;
    @JoinTable(name = "suspension_plantel", joinColumns = {
        @JoinColumn(name = "id_plantel", referencedColumnName = "id_plantel")}, inverseJoinColumns = {
        @JoinColumn(name = "id_suspension", referencedColumnName = "id_suspensionlabores")})
    @ManyToMany
    private List<SuspensionLabores> suspensionLaboresList;
    @ManyToMany(mappedBy = "plantelList")
    private List<Administrativo> administrativoList;
    @JoinTable(name = "vacacion_plantel", joinColumns = {
        @JoinColumn(name = "id_plantel", referencedColumnName = "id_plantel")}, inverseJoinColumns = {
        @JoinColumn(name = "id_vacacion", referencedColumnName = "id_vacacion")})
    @ManyToMany
    private List<Vacacion> vacacionList;
    @OneToMany(mappedBy = "idPlantel")
    private List<Evento> eventoList;
    @JoinColumn(name = "id_asentamiento", referencedColumnName = "id_asentamiento")
    @ManyToOne
    private Asentamiento idAsentamiento;
    @OneToMany(mappedBy = "idPlantel")
    private List<AsignacionRecursoplantel> asignacionRecursoplantelList;
    @OneToMany(mappedBy = "idPlantel")
    private List<PlanEstudio> planEstudioList;
    @OneToMany(mappedBy = "idPlantel")
    private List<GrupoInduccion> grupoInduccionList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idplantel")
    private Integer idplantel;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "lugar")
    private String lugar;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "correoelectronico")
    private String correoelectronico;
    @OneToMany(mappedBy = "idplantel")
    private List<Mesopcionc> mesopcioncList;
    @OneToMany(mappedBy = "idplantel")
    private List<Recursohumano> recursohumanoList;
    @OneToMany(mappedBy = "idplantel")
    private List<Grupo> grupoList;
    @OneToMany(mappedBy = "idplantel")
    private List<Calendariorectoria> calendariorectoriaList;
    @OneToMany(mappedBy = "idplantel")
    private List<Calendario> calendarioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idplantel")
    private List<Planestudios> planestudiosList;

    public Plantel() {
    }

    public Plantel(Integer idplantel) {
        this.idplantel = idplantel;
    }

    public Integer getIdplantel() {
        return idplantel;
    }

    public void setIdplantel(Integer idplantel) {
        this.idplantel = idplantel;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreoelectronico() {
        return correoelectronico;
    }

    public void setCorreoelectronico(String correoelectronico) {
        this.correoelectronico = correoelectronico;
    }

    public List<Mesopcionc> getMesopcioncList() {
        return mesopcioncList;
    }

    public void setMesopcioncList(List<Mesopcionc> mesopcioncList) {
        this.mesopcioncList = mesopcioncList;
    }

    public List<Recursohumano> getRecursohumanoList() {
        return recursohumanoList;
    }

    public void setRecursohumanoList(List<Recursohumano> recursohumanoList) {
        this.recursohumanoList = recursohumanoList;
    }

    public List<Grupo> getGrupoList() {
        return grupoList;
    }

    public void setGrupoList(List<Grupo> grupoList) {
        this.grupoList = grupoList;
    }

    public List<Calendariorectoria> getCalendariorectoriaList() {
        return calendariorectoriaList;
    }

    public void setCalendariorectoriaList(List<Calendariorectoria> calendariorectoriaList) {
        this.calendariorectoriaList = calendariorectoriaList;
    }

    public List<Calendario> getCalendarioList() {
        return calendarioList;
    }

    public void setCalendarioList(List<Calendario> calendarioList) {
        this.calendarioList = calendarioList;
    }

    public List<Planestudios> getPlanestudiosList() {
        return planestudiosList;
    }

    public void setPlanestudiosList(List<Planestudios> planestudiosList) {
        this.planestudiosList = planestudiosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idplantel != null ? idplantel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Plantel)) {
            return false;
        }
        Plantel other = (Plantel) object;
        if ((this.idplantel == null && other.idplantel != null) || (this.idplantel != null && !this.idplantel.equals(other.idplantel))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.secretariaAcademica.entity.Plantel[ idplantel=" + idplantel + " ]";
    }

    public Plantel(Long idPlantel) {
        this.idPlantel = idPlantel;
    }

    public Long getIdPlantel() {
        return idPlantel;
    }

    public void setIdPlantel(Long idPlantel) {
        this.idPlantel = idPlantel;
    }

    public String getNombrePlantel() {
        return nombrePlantel;
    }

    public void setNombrePlantel(String nombrePlantel) {
        this.nombrePlantel = nombrePlantel;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public List<SuspensionLabores> getSuspensionLaboresList() {
        return suspensionLaboresList;
    }

    public void setSuspensionLaboresList(List<SuspensionLabores> suspensionLaboresList) {
        this.suspensionLaboresList = suspensionLaboresList;
    }

    public List<Administrativo> getAdministrativoList() {
        return administrativoList;
    }

    public void setAdministrativoList(List<Administrativo> administrativoList) {
        this.administrativoList = administrativoList;
    }

    public List<Vacacion> getVacacionList() {
        return vacacionList;
    }

    public void setVacacionList(List<Vacacion> vacacionList) {
        this.vacacionList = vacacionList;
    }

    public List<Evento> getEventoList() {
        return eventoList;
    }

    public void setEventoList(List<Evento> eventoList) {
        this.eventoList = eventoList;
    }

    public Asentamiento getIdAsentamiento() {
        return idAsentamiento;
    }

    public void setIdAsentamiento(Asentamiento idAsentamiento) {
        this.idAsentamiento = idAsentamiento;
    }

    public List<AsignacionRecursoplantel> getAsignacionRecursoplantelList() {
        return asignacionRecursoplantelList;
    }

    public void setAsignacionRecursoplantelList(List<AsignacionRecursoplantel> asignacionRecursoplantelList) {
        this.asignacionRecursoplantelList = asignacionRecursoplantelList;
    }

    public List<PlanEstudio> getPlanEstudioList() {
        return planEstudioList;
    }

    public void setPlanEstudioList(List<PlanEstudio> planEstudioList) {
        this.planEstudioList = planEstudioList;
    }

    public List<GrupoInduccion> getGrupoInduccionList() {
        return grupoInduccionList;
    }

    public void setGrupoInduccionList(List<GrupoInduccion> grupoInduccionList) {
        this.grupoInduccionList = grupoInduccionList;
    }

//    @Override
//    public int hashCode() {
//        int hash = 0;
//        hash += (idPlantel != null ? idPlantel.hashCode() : 0);
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object object) {
//        // TODO: Warning - this method won't work in the case the id fields are not set
//        if (!(object instanceof Plantel)) {
//            return false;
//        }
//        Plantel other = (Plantel) object;
//        if ((this.idPlantel == null && other.idPlantel != null) || (this.idPlantel != null && !this.idPlantel.equals(other.idPlantel))) {
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public String toString() {
//        return "com.utez.secretariaAcademica.entity.Plantel[ idPlantel=" + idPlantel + " ]";
//    }
    
}
