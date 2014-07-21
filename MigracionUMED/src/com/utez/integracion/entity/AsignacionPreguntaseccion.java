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
@Table(name = "asignacion_preguntaseccion")
@NamedQueries({
    @NamedQuery(name = "AsignacionPreguntaseccion.findAll", query = "SELECT a FROM AsignacionPreguntaseccion a"),
    @NamedQuery(name = "AsignacionPreguntaseccion.findByIdAsignacionpreguntaseccion", query = "SELECT a FROM AsignacionPreguntaseccion a WHERE a.idAsignacionpreguntaseccion = :idAsignacionpreguntaseccion"),
    @NamedQuery(name = "AsignacionPreguntaseccion.findByOrden", query = "SELECT a FROM AsignacionPreguntaseccion a WHERE a.orden = :orden")})
public class AsignacionPreguntaseccion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_asignacionpreguntaseccion")
    private Long idAsignacionpreguntaseccion;
    @Column(name = "orden")
    private BigInteger orden;
    @JoinColumn(name = "id_seccion", referencedColumnName = "id_seccion")
    @ManyToOne
    private Seccion idSeccion;
    @JoinColumn(name = "id_pregunta", referencedColumnName = "id_pregunta")
    @ManyToOne
    private Pregunta idPregunta;

    public AsignacionPreguntaseccion() {
    }

    public AsignacionPreguntaseccion(Long idAsignacionpreguntaseccion) {
        this.idAsignacionpreguntaseccion = idAsignacionpreguntaseccion;
    }

    public Long getIdAsignacionpreguntaseccion() {
        return idAsignacionpreguntaseccion;
    }

    public void setIdAsignacionpreguntaseccion(Long idAsignacionpreguntaseccion) {
        this.idAsignacionpreguntaseccion = idAsignacionpreguntaseccion;
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

    public Pregunta getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(Pregunta idPregunta) {
        this.idPregunta = idPregunta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAsignacionpreguntaseccion != null ? idAsignacionpreguntaseccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsignacionPreguntaseccion)) {
            return false;
        }
        AsignacionPreguntaseccion other = (AsignacionPreguntaseccion) object;
        if ((this.idAsignacionpreguntaseccion == null && other.idAsignacionpreguntaseccion != null) || (this.idAsignacionpreguntaseccion != null && !this.idAsignacionpreguntaseccion.equals(other.idAsignacionpreguntaseccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.AsignacionPreguntaseccion[ idAsignacionpreguntaseccion=" + idAsignacionpreguntaseccion + " ]";
    }
    
}
