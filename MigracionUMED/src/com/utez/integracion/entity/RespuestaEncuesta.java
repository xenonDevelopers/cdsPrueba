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
@Table(name = "respuesta_encuesta")
@NamedQueries({
    @NamedQuery(name = "RespuestaEncuesta.findAll", query = "SELECT r FROM RespuestaEncuesta r"),
    @NamedQuery(name = "RespuestaEncuesta.findByIdRespuestaencuesta", query = "SELECT r FROM RespuestaEncuesta r WHERE r.idRespuestaencuesta = :idRespuestaencuesta"),
    @NamedQuery(name = "RespuestaEncuesta.findByIdRespuestaseleccionada", query = "SELECT r FROM RespuestaEncuesta r WHERE r.idRespuestaseleccionada = :idRespuestaseleccionada")})
public class RespuestaEncuesta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_respuestaencuesta")
    private Long idRespuestaencuesta;
    @Column(name = "id_respuestaseleccionada")
    private BigInteger idRespuestaseleccionada;
    @JoinColumn(name = "id_tiporespuesta", referencedColumnName = "id_tiporespuesta")
    @ManyToOne
    private TipoRespuesta idTiporespuesta;
    @JoinColumn(name = "id_pregunta", referencedColumnName = "id_pregunta")
    @ManyToOne
    private Pregunta idPregunta;
    @JoinColumn(name = "id_asignacionencuesta", referencedColumnName = "id_asignacionencuestaalumno")
    @ManyToOne
    private AsignacionEncuestaalumno idAsignacionencuesta;

    public RespuestaEncuesta() {
    }

    public RespuestaEncuesta(Long idRespuestaencuesta) {
        this.idRespuestaencuesta = idRespuestaencuesta;
    }

    public Long getIdRespuestaencuesta() {
        return idRespuestaencuesta;
    }

    public void setIdRespuestaencuesta(Long idRespuestaencuesta) {
        this.idRespuestaencuesta = idRespuestaencuesta;
    }

    public BigInteger getIdRespuestaseleccionada() {
        return idRespuestaseleccionada;
    }

    public void setIdRespuestaseleccionada(BigInteger idRespuestaseleccionada) {
        this.idRespuestaseleccionada = idRespuestaseleccionada;
    }

    public TipoRespuesta getIdTiporespuesta() {
        return idTiporespuesta;
    }

    public void setIdTiporespuesta(TipoRespuesta idTiporespuesta) {
        this.idTiporespuesta = idTiporespuesta;
    }

    public Pregunta getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(Pregunta idPregunta) {
        this.idPregunta = idPregunta;
    }

    public AsignacionEncuestaalumno getIdAsignacionencuesta() {
        return idAsignacionencuesta;
    }

    public void setIdAsignacionencuesta(AsignacionEncuestaalumno idAsignacionencuesta) {
        this.idAsignacionencuesta = idAsignacionencuesta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRespuestaencuesta != null ? idRespuestaencuesta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RespuestaEncuesta)) {
            return false;
        }
        RespuestaEncuesta other = (RespuestaEncuesta) object;
        if ((this.idRespuestaencuesta == null && other.idRespuestaencuesta != null) || (this.idRespuestaencuesta != null && !this.idRespuestaencuesta.equals(other.idRespuestaencuesta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.RespuestaEncuesta[ idRespuestaencuesta=" + idRespuestaencuesta + " ]";
    }
    
}
