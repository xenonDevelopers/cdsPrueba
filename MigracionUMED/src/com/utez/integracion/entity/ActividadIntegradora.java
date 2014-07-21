/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "actividad_integradora")
@NamedQueries({
    @NamedQuery(name = "ActividadIntegradora.findAll", query = "SELECT a FROM ActividadIntegradora a"),
    @NamedQuery(name = "ActividadIntegradora.findByIdActividadintegradora", query = "SELECT a FROM ActividadIntegradora a WHERE a.idActividadintegradora = :idActividadintegradora"),
    @NamedQuery(name = "ActividadIntegradora.findByNombreActividad", query = "SELECT a FROM ActividadIntegradora a WHERE a.nombreActividad = :nombreActividad"),
    @NamedQuery(name = "ActividadIntegradora.findByNumeroControl", query = "SELECT a FROM ActividadIntegradora a WHERE a.numeroControl = :numeroControl"),
    @NamedQuery(name = "ActividadIntegradora.findByEdicion", query = "SELECT a FROM ActividadIntegradora a WHERE a.edicion = :edicion"),
    @NamedQuery(name = "ActividadIntegradora.findByFechaRegistro", query = "SELECT a FROM ActividadIntegradora a WHERE a.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "ActividadIntegradora.findByVigencia", query = "SELECT a FROM ActividadIntegradora a WHERE a.vigencia = :vigencia"),
    @NamedQuery(name = "ActividadIntegradora.findByEstadoActividadintegradora", query = "SELECT a FROM ActividadIntegradora a WHERE a.estadoActividadintegradora = :estadoActividadintegradora"),
    @NamedQuery(name = "ActividadIntegradora.findByArchivopdf", query = "SELECT a FROM ActividadIntegradora a WHERE a.archivopdf = :archivopdf")})
public class ActividadIntegradora implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_actividadintegradora")
    private Long idActividadintegradora;
    @Column(name = "nombre_actividad")
    private String nombreActividad;
    @Column(name = "numero_control")
    private Integer numeroControl;
    @Column(name = "edicion")
    private String edicion;
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;
    @Column(name = "vigencia")
    @Temporal(TemporalType.DATE)
    private Date vigencia;
    @Column(name = "estado_actividadintegradora")
    private String estadoActividadintegradora;
    @Column(name = "archivopdf")
    private String archivopdf;
    @OneToMany(mappedBy = "idActividadintegradora")
    private List<AsignacionAsignaturaintegradora> asignacionAsignaturaintegradoraList;
    @OneToMany(mappedBy = "idActividadintegradora")
    private List<AsignacionIntegradoragrupo> asignacionIntegradoragrupoList;
    @OneToMany(mappedBy = "idActividadintegradora")
    private List<AsignacionAutorintegradora> asignacionAutorintegradoraList;

    public ActividadIntegradora() {
    }

    public ActividadIntegradora(Long idActividadintegradora) {
        this.idActividadintegradora = idActividadintegradora;
    }

    public Long getIdActividadintegradora() {
        return idActividadintegradora;
    }

    public void setIdActividadintegradora(Long idActividadintegradora) {
        this.idActividadintegradora = idActividadintegradora;
    }

    public String getNombreActividad() {
        return nombreActividad;
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }

    public Integer getNumeroControl() {
        return numeroControl;
    }

    public void setNumeroControl(Integer numeroControl) {
        this.numeroControl = numeroControl;
    }

    public String getEdicion() {
        return edicion;
    }

    public void setEdicion(String edicion) {
        this.edicion = edicion;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getVigencia() {
        return vigencia;
    }

    public void setVigencia(Date vigencia) {
        this.vigencia = vigencia;
    }

    public String getEstadoActividadintegradora() {
        return estadoActividadintegradora;
    }

    public void setEstadoActividadintegradora(String estadoActividadintegradora) {
        this.estadoActividadintegradora = estadoActividadintegradora;
    }

    public String getArchivopdf() {
        return archivopdf;
    }

    public void setArchivopdf(String archivopdf) {
        this.archivopdf = archivopdf;
    }

    public List<AsignacionAsignaturaintegradora> getAsignacionAsignaturaintegradoraList() {
        return asignacionAsignaturaintegradoraList;
    }

    public void setAsignacionAsignaturaintegradoraList(List<AsignacionAsignaturaintegradora> asignacionAsignaturaintegradoraList) {
        this.asignacionAsignaturaintegradoraList = asignacionAsignaturaintegradoraList;
    }

    public List<AsignacionIntegradoragrupo> getAsignacionIntegradoragrupoList() {
        return asignacionIntegradoragrupoList;
    }

    public void setAsignacionIntegradoragrupoList(List<AsignacionIntegradoragrupo> asignacionIntegradoragrupoList) {
        this.asignacionIntegradoragrupoList = asignacionIntegradoragrupoList;
    }

    public List<AsignacionAutorintegradora> getAsignacionAutorintegradoraList() {
        return asignacionAutorintegradoraList;
    }

    public void setAsignacionAutorintegradoraList(List<AsignacionAutorintegradora> asignacionAutorintegradoraList) {
        this.asignacionAutorintegradoraList = asignacionAutorintegradoraList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idActividadintegradora != null ? idActividadintegradora.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ActividadIntegradora)) {
            return false;
        }
        ActividadIntegradora other = (ActividadIntegradora) object;
        if ((this.idActividadintegradora == null && other.idActividadintegradora != null) || (this.idActividadintegradora != null && !this.idActividadintegradora.equals(other.idActividadintegradora))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.ActividadIntegradora[ idActividadintegradora=" + idActividadintegradora + " ]";
    }
    
}
