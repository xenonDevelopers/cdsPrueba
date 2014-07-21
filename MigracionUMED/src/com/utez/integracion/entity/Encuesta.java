/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "encuesta")
@NamedQueries({
    @NamedQuery(name = "Encuesta.findAll", query = "SELECT e FROM Encuesta e"),
    @NamedQuery(name = "Encuesta.findByIdEncuesta", query = "SELECT e FROM Encuesta e WHERE e.idEncuesta = :idEncuesta"),
    @NamedQuery(name = "Encuesta.findByNombreEncuesta", query = "SELECT e FROM Encuesta e WHERE e.nombreEncuesta = :nombreEncuesta"),
    @NamedQuery(name = "Encuesta.findByDescripcionEncuesta", query = "SELECT e FROM Encuesta e WHERE e.descripcionEncuesta = :descripcionEncuesta")})
public class Encuesta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_encuesta")
    private Long idEncuesta;
    @Column(name = "nombre_encuesta")
    private String nombreEncuesta;
    @Column(name = "descripcion_encuesta")
    private String descripcionEncuesta;
    @OneToMany(mappedBy = "idEncuesta")
    private List<AsignacionEncuestadocente> asignacionEncuestadocenteList;
    @OneToMany(mappedBy = "idEncuesta")
    private List<AsignacionSeccionencuesta> asignacionSeccionencuestaList;
    @OneToMany(mappedBy = "idEncuesta")
    private List<AsignacionEncuestaalumno> asignacionEncuestaalumnoList;
    @JoinColumn(name = "id_tipoencuesta", referencedColumnName = "id_tipoencuesta")
    @ManyToOne
    private TipoEncuesta idTipoencuesta;
    @OneToMany(mappedBy = "idEncuesta")
    private List<AsignacionGrupoencuesta> asignacionGrupoencuestaList;

    public Encuesta() {
    }

    public Encuesta(Long idEncuesta) {
        this.idEncuesta = idEncuesta;
    }

    public Long getIdEncuesta() {
        return idEncuesta;
    }

    public void setIdEncuesta(Long idEncuesta) {
        this.idEncuesta = idEncuesta;
    }

    public String getNombreEncuesta() {
        return nombreEncuesta;
    }

    public void setNombreEncuesta(String nombreEncuesta) {
        this.nombreEncuesta = nombreEncuesta;
    }

    public String getDescripcionEncuesta() {
        return descripcionEncuesta;
    }

    public void setDescripcionEncuesta(String descripcionEncuesta) {
        this.descripcionEncuesta = descripcionEncuesta;
    }

    public List<AsignacionEncuestadocente> getAsignacionEncuestadocenteList() {
        return asignacionEncuestadocenteList;
    }

    public void setAsignacionEncuestadocenteList(List<AsignacionEncuestadocente> asignacionEncuestadocenteList) {
        this.asignacionEncuestadocenteList = asignacionEncuestadocenteList;
    }

    public List<AsignacionSeccionencuesta> getAsignacionSeccionencuestaList() {
        return asignacionSeccionencuestaList;
    }

    public void setAsignacionSeccionencuestaList(List<AsignacionSeccionencuesta> asignacionSeccionencuestaList) {
        this.asignacionSeccionencuestaList = asignacionSeccionencuestaList;
    }

    public List<AsignacionEncuestaalumno> getAsignacionEncuestaalumnoList() {
        return asignacionEncuestaalumnoList;
    }

    public void setAsignacionEncuestaalumnoList(List<AsignacionEncuestaalumno> asignacionEncuestaalumnoList) {
        this.asignacionEncuestaalumnoList = asignacionEncuestaalumnoList;
    }

    public TipoEncuesta getIdTipoencuesta() {
        return idTipoencuesta;
    }

    public void setIdTipoencuesta(TipoEncuesta idTipoencuesta) {
        this.idTipoencuesta = idTipoencuesta;
    }

    public List<AsignacionGrupoencuesta> getAsignacionGrupoencuestaList() {
        return asignacionGrupoencuestaList;
    }

    public void setAsignacionGrupoencuestaList(List<AsignacionGrupoencuesta> asignacionGrupoencuestaList) {
        this.asignacionGrupoencuestaList = asignacionGrupoencuestaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEncuesta != null ? idEncuesta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Encuesta)) {
            return false;
        }
        Encuesta other = (Encuesta) object;
        if ((this.idEncuesta == null && other.idEncuesta != null) || (this.idEncuesta != null && !this.idEncuesta.equals(other.idEncuesta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.Encuesta[ idEncuesta=" + idEncuesta + " ]";
    }
    
}
