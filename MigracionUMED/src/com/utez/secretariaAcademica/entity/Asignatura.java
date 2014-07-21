/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.secretariaAcademica.entity;

import com.utez.integracion.entity.Acta;
import com.utez.integracion.entity.AlumnoAsignatura;
import com.utez.integracion.entity.AsignacionAsignaturabanco;
import com.utez.integracion.entity.AsignacionAsignaturaintegradora;
import com.utez.integracion.entity.BloqueAsignatura;
import com.utez.integracion.entity.CalendarioAsignatura;
import com.utez.integracion.entity.ExamenExtemporaneo;
import com.utez.integracion.entity.PlanEstudio;
import com.utez.integracion.entity.ProgramacionAsignatura;
import com.utez.integracion.entity.TipoAsignatura;
import com.utez.integracion.entity.TipoNivelasignatura;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
@Table(name = "asignatura")
@NamedQueries({
    @NamedQuery(name = "Asignatura.findAll", query = "SELECT a FROM Asignatura a"),
    @NamedQuery(name = "Asignatura.findByIdasignatura", query = "SELECT a FROM Asignatura a WHERE a.idasignatura = :idasignatura"),
    @NamedQuery(name = "Asignatura.findByClavesep", query = "SELECT a FROM Asignatura a WHERE a.clavesep = :clavesep"),
    @NamedQuery(name = "Asignatura.findByAsignatura", query = "SELECT a FROM Asignatura a WHERE a.asignatura = :asignatura"),
    @NamedQuery(name = "Asignatura.findByCreditos", query = "SELECT a FROM Asignatura a WHERE a.creditos = :creditos"),
    @NamedQuery(name = "Asignatura.findByNivel", query = "SELECT a FROM Asignatura a WHERE a.nivel = :nivel"),
    @NamedQuery(name = "Asignatura.findByTipoasignatura", query = "SELECT a FROM Asignatura a WHERE a.tipoasignatura = :tipoasignatura")})
public class Asignatura implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_asignatura")
    private Long idAsignatura;
    @Column(name = "clave_sep")
    private String claveSep;
    @Column(name = "nombre_asignatura")
    private String nombreAsignatura;
    @JoinTable(name = "correspondencia", joinColumns = {
        @JoinColumn(name = "id_asignatura", referencedColumnName = "id_asignatura")}, inverseJoinColumns = {
        @JoinColumn(name = "id_asignaturacorrespondencia", referencedColumnName = "id_asignatura")})
    @ManyToMany
    private List<Asignatura> asignaturaList;
    @JoinTable(name = "asignatura_seriada", joinColumns = {
        @JoinColumn(name = "id_asignaturaseriada", referencedColumnName = "id_asignatura")}, inverseJoinColumns = {
        @JoinColumn(name = "id_asignatura", referencedColumnName = "id_asignatura")})
    @ManyToMany
    private List<Asignatura> asignaturaList2;
    @OneToMany(mappedBy = "idAsignatura")
    private List<AlumnoAsignatura> alumnoAsignaturaList;
    @OneToMany(mappedBy = "idAsignatura")
    private List<AsignacionAsignaturaintegradora> asignacionAsignaturaintegradoraList;
    @OneToMany(mappedBy = "idAsignatura")
    private List<ExamenExtemporaneo> examenExtemporaneoList;
    @OneToMany(mappedBy = "idAsignatura")
    private List<Acta> actaList;
    @OneToMany(mappedBy = "idAsignatura")
    private List<AsignacionAsignaturabanco> asignacionAsignaturabancoList;
    @OneToMany(mappedBy = "idAsignatura")
    private List<CalendarioAsignatura> calendarioAsignaturaList;
    @OneToMany(mappedBy = "idAsignatura")
    private List<BloqueAsignatura> bloqueAsignaturaList;
    @OneToMany(mappedBy = "idAsignatura")
    private List<ProgramacionAsignatura> programacionAsignaturaList;
    @JoinColumn(name = "id_tiponivelasignatura", referencedColumnName = "id_tiponivelasignatura")
    @ManyToOne
    private TipoNivelasignatura idTiponivelasignatura;
    @JoinColumn(name = "id_tipoasignatura", referencedColumnName = "id_tipoasignatura")
    @ManyToOne
    private TipoAsignatura idTipoasignatura;
    @JoinColumn(name = "id_planestudio", referencedColumnName = "id_planestudio")
    @ManyToOne
    private PlanEstudio idPlanestudio;
    @JoinColumn(name = "id_asignaturaseriada", referencedColumnName = "id_asignatura")
    @ManyToOne
    private Asignatura idAsignaturaseriada;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idasignatura")
    private Integer idasignatura;
    @Column(name = "clavesep")
    private String clavesep;
    @Column(name = "asignatura")
    private String asignatura;
    @Column(name = "creditos")
    private Integer creditos;
    @Column(name = "nivel")
    private String nivel;
    @Column(name = "tipoasignatura")
    private String tipoasignatura;
    @OneToMany(mappedBy = "idasignatura")
    private List<Cuadernoprogramacion> cuadernoprogramacionList;
    @JoinColumn(name = "rvoe", referencedColumnName = "rvoe")
    @ManyToOne
    private Planestudios rvoe;
//    @OneToMany(mappedBy = "seriadacon")
//    private List<Asignatura> asignaturaList;
//    @JoinColumn(name = "seriadacon", referencedColumnName = "idasignatura")
    @ManyToOne
    private Asignatura seriadacon;
    @OneToMany(mappedBy = "idasignatura")
    private List<Asesoriaasignatura> asesoriaasignaturaList;
    @OneToMany(mappedBy = "idasignatura")
    private List<Bloqueasignatura> bloqueasignaturaList;

    public Asignatura() {
    }

    public Asignatura(Integer idasignatura) {
        this.idasignatura = idasignatura;
    }

    public Integer getIdasignatura() {
        return idasignatura;
    }

    public void setIdasignatura(Integer idasignatura) {
        this.idasignatura = idasignatura;
    }

    public String getClavesep() {
        return clavesep;
    }

    public void setClavesep(String clavesep) {
        this.clavesep = clavesep;
    }

    public String getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }

    public Integer getCreditos() {
        return creditos;
    }

    public void setCreditos(Integer creditos) {
        this.creditos = creditos;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getTipoasignatura() {
        return tipoasignatura;
    }

    public void setTipoasignatura(String tipoasignatura) {
        this.tipoasignatura = tipoasignatura;
    }

    public List<Cuadernoprogramacion> getCuadernoprogramacionList() {
        return cuadernoprogramacionList;
    }

    public void setCuadernoprogramacionList(List<Cuadernoprogramacion> cuadernoprogramacionList) {
        this.cuadernoprogramacionList = cuadernoprogramacionList;
    }

    public Planestudios getRvoe() {
        return rvoe;
    }

    public void setRvoe(Planestudios rvoe) {
        this.rvoe = rvoe;
    }

    public List<Asignatura> getAsignaturaList() {
        return asignaturaList;
    }

    public void setAsignaturaList(List<Asignatura> asignaturaList) {
        this.asignaturaList = asignaturaList;
    }

    public Asignatura getSeriadacon() {
        return seriadacon;
    }

    public void setSeriadacon(Asignatura seriadacon) {
        this.seriadacon = seriadacon;
    }

    public List<Asesoriaasignatura> getAsesoriaasignaturaList() {
        return asesoriaasignaturaList;
    }

    public void setAsesoriaasignaturaList(List<Asesoriaasignatura> asesoriaasignaturaList) {
        this.asesoriaasignaturaList = asesoriaasignaturaList;
    }

    public List<Bloqueasignatura> getBloqueasignaturaList() {
        return bloqueasignaturaList;
    }

    public void setBloqueasignaturaList(List<Bloqueasignatura> bloqueasignaturaList) {
        this.bloqueasignaturaList = bloqueasignaturaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idasignatura != null ? idasignatura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asignatura)) {
            return false;
        }
        Asignatura other = (Asignatura) object;
        if ((this.idasignatura == null && other.idasignatura != null) || (this.idasignatura != null && !this.idasignatura.equals(other.idasignatura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.secretariaAcademica.entity.Asignatura[ idasignatura=" + idasignatura + " ]";
    }

    public Asignatura(Long idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    public Long getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(Long idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    public String getClaveSep() {
        return claveSep;
    }

    public void setClaveSep(String claveSep) {
        this.claveSep = claveSep;
    }

    public String getNombreAsignatura() {
        return nombreAsignatura;
    }

    public void setNombreAsignatura(String nombreAsignatura) {
        this.nombreAsignatura = nombreAsignatura;
    }

//    public List<Asignatura> getAsignaturaList() {
//        return asignaturaList;
//    }
//
//    public void setAsignaturaList(List<Asignatura> asignaturaList) {
//        this.asignaturaList = asignaturaList;
//    }

    public List<Asignatura> getAsignaturaList2() {
        return asignaturaList2;
    }

    public void setAsignaturaList2(List<Asignatura> asignaturaList2) {
        this.asignaturaList2 = asignaturaList2;
    }

    public List<AlumnoAsignatura> getAlumnoAsignaturaList() {
        return alumnoAsignaturaList;
    }

    public void setAlumnoAsignaturaList(List<AlumnoAsignatura> alumnoAsignaturaList) {
        this.alumnoAsignaturaList = alumnoAsignaturaList;
    }

    public List<AsignacionAsignaturaintegradora> getAsignacionAsignaturaintegradoraList() {
        return asignacionAsignaturaintegradoraList;
    }

    public void setAsignacionAsignaturaintegradoraList(List<AsignacionAsignaturaintegradora> asignacionAsignaturaintegradoraList) {
        this.asignacionAsignaturaintegradoraList = asignacionAsignaturaintegradoraList;
    }

    public List<ExamenExtemporaneo> getExamenExtemporaneoList() {
        return examenExtemporaneoList;
    }

    public void setExamenExtemporaneoList(List<ExamenExtemporaneo> examenExtemporaneoList) {
        this.examenExtemporaneoList = examenExtemporaneoList;
    }

    public List<Acta> getActaList() {
        return actaList;
    }

    public void setActaList(List<Acta> actaList) {
        this.actaList = actaList;
    }

    public List<AsignacionAsignaturabanco> getAsignacionAsignaturabancoList() {
        return asignacionAsignaturabancoList;
    }

    public void setAsignacionAsignaturabancoList(List<AsignacionAsignaturabanco> asignacionAsignaturabancoList) {
        this.asignacionAsignaturabancoList = asignacionAsignaturabancoList;
    }

    public List<CalendarioAsignatura> getCalendarioAsignaturaList() {
        return calendarioAsignaturaList;
    }

    public void setCalendarioAsignaturaList(List<CalendarioAsignatura> calendarioAsignaturaList) {
        this.calendarioAsignaturaList = calendarioAsignaturaList;
    }

    public List<BloqueAsignatura> getBloqueAsignaturaList() {
        return bloqueAsignaturaList;
    }

    public void setBloqueAsignaturaList(List<BloqueAsignatura> bloqueAsignaturaList) {
        this.bloqueAsignaturaList = bloqueAsignaturaList;
    }

    public List<ProgramacionAsignatura> getProgramacionAsignaturaList() {
        return programacionAsignaturaList;
    }

    public void setProgramacionAsignaturaList(List<ProgramacionAsignatura> programacionAsignaturaList) {
        this.programacionAsignaturaList = programacionAsignaturaList;
    }

    public TipoNivelasignatura getIdTiponivelasignatura() {
        return idTiponivelasignatura;
    }

    public void setIdTiponivelasignatura(TipoNivelasignatura idTiponivelasignatura) {
        this.idTiponivelasignatura = idTiponivelasignatura;
    }

    public TipoAsignatura getIdTipoasignatura() {
        return idTipoasignatura;
    }

    public void setIdTipoasignatura(TipoAsignatura idTipoasignatura) {
        this.idTipoasignatura = idTipoasignatura;
    }

    public PlanEstudio getIdPlanestudio() {
        return idPlanestudio;
    }

    public void setIdPlanestudio(PlanEstudio idPlanestudio) {
        this.idPlanestudio = idPlanestudio;
    }

    public Asignatura getIdAsignaturaseriada() {
        return idAsignaturaseriada;
    }

    public void setIdAsignaturaseriada(Asignatura idAsignaturaseriada) {
        this.idAsignaturaseriada = idAsignaturaseriada;
    }

//    @Override
//    public int hashCode() {
//        int hash = 0;
//        hash += (idAsignatura != null ? idAsignatura.hashCode() : 0);
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object object) {
//        // TODO: Warning - this method won't work in the case the id fields are not set
//        if (!(object instanceof Asignatura)) {
//            return false;
//        }
//        Asignatura other = (Asignatura) object;
//        if ((this.idAsignatura == null && other.idAsignatura != null) || (this.idAsignatura != null && !this.idAsignatura.equals(other.idAsignatura))) {
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public String toString() {
//        return "com.utez.secretariaAcademica.entity.Asignatura[ idAsignatura=" + idAsignatura + " ]";
//    }
    
}
