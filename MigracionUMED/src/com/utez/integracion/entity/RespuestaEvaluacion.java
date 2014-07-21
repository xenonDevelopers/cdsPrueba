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
@Table(name = "respuesta_evaluacion")
@NamedQueries({
    @NamedQuery(name = "RespuestaEvaluacion.findAll", query = "SELECT r FROM RespuestaEvaluacion r"),
    @NamedQuery(name = "RespuestaEvaluacion.findByIdRespuestaevaluacion", query = "SELECT r FROM RespuestaEvaluacion r WHERE r.idRespuestaevaluacion = :idRespuestaevaluacion"),
    @NamedQuery(name = "RespuestaEvaluacion.findByIdRespuestaseleccionada", query = "SELECT r FROM RespuestaEvaluacion r WHERE r.idRespuestaseleccionada = :idRespuestaseleccionada"),
    @NamedQuery(name = "RespuestaEvaluacion.findByNumeroReactivo", query = "SELECT r FROM RespuestaEvaluacion r WHERE r.numeroReactivo = :numeroReactivo")})
public class RespuestaEvaluacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_respuestaevaluacion")
    private Long idRespuestaevaluacion;
    @Column(name = "id_respuestaseleccionada")
    private BigInteger idRespuestaseleccionada;
    @Column(name = "numero_reactivo")
    private Integer numeroReactivo;
    @JoinColumn(name = "id_resultadoevaluacion", referencedColumnName = "id_resultadoevaluacion")
    @ManyToOne
    private ResultadoEvaluacion idResultadoevaluacion;
    @JoinColumn(name = "id_contenido", referencedColumnName = "id_contenidoreactivo")
    @ManyToOne
    private ContenidoReactivo idContenido;

    public RespuestaEvaluacion() {
    }

    public RespuestaEvaluacion(Long idRespuestaevaluacion) {
        this.idRespuestaevaluacion = idRespuestaevaluacion;
    }

    public Long getIdRespuestaevaluacion() {
        return idRespuestaevaluacion;
    }

    public void setIdRespuestaevaluacion(Long idRespuestaevaluacion) {
        this.idRespuestaevaluacion = idRespuestaevaluacion;
    }

    public BigInteger getIdRespuestaseleccionada() {
        return idRespuestaseleccionada;
    }

    public void setIdRespuestaseleccionada(BigInteger idRespuestaseleccionada) {
        this.idRespuestaseleccionada = idRespuestaseleccionada;
    }

    public Integer getNumeroReactivo() {
        return numeroReactivo;
    }

    public void setNumeroReactivo(Integer numeroReactivo) {
        this.numeroReactivo = numeroReactivo;
    }

    public ResultadoEvaluacion getIdResultadoevaluacion() {
        return idResultadoevaluacion;
    }

    public void setIdResultadoevaluacion(ResultadoEvaluacion idResultadoevaluacion) {
        this.idResultadoevaluacion = idResultadoevaluacion;
    }

    public ContenidoReactivo getIdContenido() {
        return idContenido;
    }

    public void setIdContenido(ContenidoReactivo idContenido) {
        this.idContenido = idContenido;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRespuestaevaluacion != null ? idRespuestaevaluacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RespuestaEvaluacion)) {
            return false;
        }
        RespuestaEvaluacion other = (RespuestaEvaluacion) object;
        if ((this.idRespuestaevaluacion == null && other.idRespuestaevaluacion != null) || (this.idRespuestaevaluacion != null && !this.idRespuestaevaluacion.equals(other.idRespuestaevaluacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.RespuestaEvaluacion[ idRespuestaevaluacion=" + idRespuestaevaluacion + " ]";
    }
    
}
