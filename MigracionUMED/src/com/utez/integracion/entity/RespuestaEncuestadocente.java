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
@Table(name = "respuesta_encuestadocente")
@NamedQueries({
    @NamedQuery(name = "RespuestaEncuestadocente.findAll", query = "SELECT r FROM RespuestaEncuestadocente r"),
    @NamedQuery(name = "RespuestaEncuestadocente.findByIdRespuestaencuestadocente", query = "SELECT r FROM RespuestaEncuestadocente r WHERE r.idRespuestaencuestadocente = :idRespuestaencuestadocente"),
    @NamedQuery(name = "RespuestaEncuestadocente.findByIdRespuestaseleccionada", query = "SELECT r FROM RespuestaEncuestadocente r WHERE r.idRespuestaseleccionada = :idRespuestaseleccionada")})
public class RespuestaEncuestadocente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_respuestaencuestadocente")
    private Long idRespuestaencuestadocente;
    @Column(name = "id_respuestaseleccionada")
    private BigInteger idRespuestaseleccionada;
    @JoinColumn(name = "id_tiporespuesta", referencedColumnName = "id_tiporespuesta")
    @ManyToOne
    private TipoRespuesta idTiporespuesta;
    @JoinColumn(name = "id_pregunta", referencedColumnName = "id_pregunta")
    @ManyToOne
    private Pregunta idPregunta;
    @JoinColumn(name = "id_asignacionencuestadocente", referencedColumnName = "id_asignacionencuestadocente")
    @ManyToOne
    private AsignacionEncuestadocente idAsignacionencuestadocente;

    public RespuestaEncuestadocente() {
    }

    public RespuestaEncuestadocente(Long idRespuestaencuestadocente) {
        this.idRespuestaencuestadocente = idRespuestaencuestadocente;
    }

    public Long getIdRespuestaencuestadocente() {
        return idRespuestaencuestadocente;
    }

    public void setIdRespuestaencuestadocente(Long idRespuestaencuestadocente) {
        this.idRespuestaencuestadocente = idRespuestaencuestadocente;
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

    public AsignacionEncuestadocente getIdAsignacionencuestadocente() {
        return idAsignacionencuestadocente;
    }

    public void setIdAsignacionencuestadocente(AsignacionEncuestadocente idAsignacionencuestadocente) {
        this.idAsignacionencuestadocente = idAsignacionencuestadocente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRespuestaencuestadocente != null ? idRespuestaencuestadocente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RespuestaEncuestadocente)) {
            return false;
        }
        RespuestaEncuestadocente other = (RespuestaEncuestadocente) object;
        if ((this.idRespuestaencuestadocente == null && other.idRespuestaencuestadocente != null) || (this.idRespuestaencuestadocente != null && !this.idRespuestaencuestadocente.equals(other.idRespuestaencuestadocente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.RespuestaEncuestadocente[ idRespuestaencuestadocente=" + idRespuestaencuestadocente + " ]";
    }
    
}
