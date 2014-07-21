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
@Table(name = "banco_reactivo")
@NamedQueries({
    @NamedQuery(name = "BancoReactivo.findAll", query = "SELECT b FROM BancoReactivo b"),
    @NamedQuery(name = "BancoReactivo.findByIdBancoreactivo", query = "SELECT b FROM BancoReactivo b WHERE b.idBancoreactivo = :idBancoreactivo"),
    @NamedQuery(name = "BancoReactivo.findByEdicion", query = "SELECT b FROM BancoReactivo b WHERE b.edicion = :edicion"),
    @NamedQuery(name = "BancoReactivo.findByNombreBanco", query = "SELECT b FROM BancoReactivo b WHERE b.nombreBanco = :nombreBanco"),
    @NamedQuery(name = "BancoReactivo.findByFechaRegistro", query = "SELECT b FROM BancoReactivo b WHERE b.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "BancoReactivo.findByEstadoBancoreactivo", query = "SELECT b FROM BancoReactivo b WHERE b.estadoBancoreactivo = :estadoBancoreactivo")})
public class BancoReactivo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_bancoreactivo")
    private Long idBancoreactivo;
    @Column(name = "edicion")
    private String edicion;
    @Column(name = "nombre_banco")
    private String nombreBanco;
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;
    @Column(name = "estado_bancoreactivo")
    private String estadoBancoreactivo;
    @OneToMany(mappedBy = "idBancoreactivo")
    private List<Reactivo> reactivoList;
    @OneToMany(mappedBy = "idBancoreactivo")
    private List<AsignacionAsignaturabanco> asignacionAsignaturabancoList;
    @OneToMany(mappedBy = "idBancoreactivo")
    private List<AsignacionAutorbanco> asignacionAutorbancoList;

    public BancoReactivo() {
    }

    public BancoReactivo(Long idBancoreactivo) {
        this.idBancoreactivo = idBancoreactivo;
    }

    public Long getIdBancoreactivo() {
        return idBancoreactivo;
    }

    public void setIdBancoreactivo(Long idBancoreactivo) {
        this.idBancoreactivo = idBancoreactivo;
    }

    public String getEdicion() {
        return edicion;
    }

    public void setEdicion(String edicion) {
        this.edicion = edicion;
    }

    public String getNombreBanco() {
        return nombreBanco;
    }

    public void setNombreBanco(String nombreBanco) {
        this.nombreBanco = nombreBanco;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getEstadoBancoreactivo() {
        return estadoBancoreactivo;
    }

    public void setEstadoBancoreactivo(String estadoBancoreactivo) {
        this.estadoBancoreactivo = estadoBancoreactivo;
    }

    public List<Reactivo> getReactivoList() {
        return reactivoList;
    }

    public void setReactivoList(List<Reactivo> reactivoList) {
        this.reactivoList = reactivoList;
    }

    public List<AsignacionAsignaturabanco> getAsignacionAsignaturabancoList() {
        return asignacionAsignaturabancoList;
    }

    public void setAsignacionAsignaturabancoList(List<AsignacionAsignaturabanco> asignacionAsignaturabancoList) {
        this.asignacionAsignaturabancoList = asignacionAsignaturabancoList;
    }

    public List<AsignacionAutorbanco> getAsignacionAutorbancoList() {
        return asignacionAutorbancoList;
    }

    public void setAsignacionAutorbancoList(List<AsignacionAutorbanco> asignacionAutorbancoList) {
        this.asignacionAutorbancoList = asignacionAutorbancoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBancoreactivo != null ? idBancoreactivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BancoReactivo)) {
            return false;
        }
        BancoReactivo other = (BancoReactivo) object;
        if ((this.idBancoreactivo == null && other.idBancoreactivo != null) || (this.idBancoreactivo != null && !this.idBancoreactivo.equals(other.idBancoreactivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.BancoReactivo[ idBancoreactivo=" + idBancoreactivo + " ]";
    }
    
}
