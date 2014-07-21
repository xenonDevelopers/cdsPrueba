/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import java.io.Serializable;
import java.math.BigInteger;
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
import javax.persistence.Table;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "asignacion_seccionencuesta")
@NamedQueries({
    @NamedQuery(name = "AsignacionSeccionencuesta.findAll", query = "SELECT a FROM AsignacionSeccionencuesta a"),
    @NamedQuery(name = "AsignacionSeccionencuesta.findByIdAsignacionseccionencuesta", query = "SELECT a FROM AsignacionSeccionencuesta a WHERE a.idAsignacionseccionencuesta = :idAsignacionseccionencuesta"),
    @NamedQuery(name = "AsignacionSeccionencuesta.findByOrden", query = "SELECT a FROM AsignacionSeccionencuesta a WHERE a.orden = :orden")})
public class AsignacionSeccionencuesta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_asignacionseccionencuesta")
    private Long idAsignacionseccionencuesta;
    @Column(name = "orden")
    private BigInteger orden;
    @JoinColumn(name = "id_seccion", referencedColumnName = "id_seccion")
    @ManyToOne
    private Seccion idSeccion;
    @JoinColumn(name = "id_encuesta", referencedColumnName = "id_encuesta")
    @ManyToOne
    private Encuesta idEncuesta;

    public AsignacionSeccionencuesta() {
    }

    public AsignacionSeccionencuesta(Long idAsignacionseccionencuesta) {
        this.idAsignacionseccionencuesta = idAsignacionseccionencuesta;
    }

    public Long getIdAsignacionseccionencuesta() {
        return idAsignacionseccionencuesta;
    }

    public void setIdAsignacionseccionencuesta(Long idAsignacionseccionencuesta) {
        this.idAsignacionseccionencuesta = idAsignacionseccionencuesta;
    }

    public BigInteger getOrden() {
        return orden;
    }

    public void setOrden(BigInteger orden) {
        this.orden = orden;
    }

    public Seccion getIdSeccion() {
        return idSeccion;
    }

    public void setIdSeccion(Seccion idSeccion) {
        this.idSeccion = idSeccion;
    }

    public Encuesta getIdEncuesta() {
        return idEncuesta;
    }

    public void setIdEncuesta(Encuesta idEncuesta) {
        this.idEncuesta = idEncuesta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAsignacionseccionencuesta != null ? idAsignacionseccionencuesta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsignacionSeccionencuesta)) {
            return false;
        }
        AsignacionSeccionencuesta other = (AsignacionSeccionencuesta) object;
        if ((this.idAsignacionseccionencuesta == null && other.idAsignacionseccionencuesta != null) || (this.idAsignacionseccionencuesta != null && !this.idAsignacionseccionencuesta.equals(other.idAsignacionseccionencuesta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.AsignacionSeccionencuesta[ idAsignacionseccionencuesta=" + idAsignacionseccionencuesta + " ]";
    }
    
}
