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
@Table(name = "asignacion_respuestapredeterminadapregunta")
@NamedQueries({
    @NamedQuery(name = "AsignacionRespuestapredeterminadapregunta.findAll", query = "SELECT a FROM AsignacionRespuestapredeterminadapregunta a"),
    @NamedQuery(name = "AsignacionRespuestapredeterminadapregunta.findByIdAsignacionrespuestapredeterminadapregunta", query = "SELECT a FROM AsignacionRespuestapredeterminadapregunta a WHERE a.idAsignacionrespuestapredeterminadapregunta = :idAsignacionrespuestapredeterminadapregunta"),
    @NamedQuery(name = "AsignacionRespuestapredeterminadapregunta.findByOrdenRespuesta", query = "SELECT a FROM AsignacionRespuestapredeterminadapregunta a WHERE a.ordenRespuesta = :ordenRespuesta"),
    @NamedQuery(name = "AsignacionRespuestapredeterminadapregunta.findByValorRespuesta", query = "SELECT a FROM AsignacionRespuestapredeterminadapregunta a WHERE a.valorRespuesta = :valorRespuesta")})
public class AsignacionRespuestapredeterminadapregunta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_asignacionrespuestapredeterminadapregunta")
    private Long idAsignacionrespuestapredeterminadapregunta;
    @Column(name = "orden_respuesta")
    private BigInteger ordenRespuesta;
    @Column(name = "valor_respuesta")
    private BigInteger valorRespuesta;
    @JoinColumn(name = "id_respuestapredeterminada", referencedColumnName = "id_respuestapredeterminada")
    @ManyToOne
    private RespuestaPredeterminada idRespuestapredeterminada;
    @JoinColumn(name = "id_pregunta", referencedColumnName = "id_pregunta")
    @ManyToOne
    private Pregunta idPregunta;

    public AsignacionRespuestapredeterminadapregunta() {
    }

    public AsignacionRespuestapredeterminadapregunta(Long idAsignacionrespuestapredeterminadapregunta) {
        this.idAsignacionrespuestapredeterminadapregunta = idAsignacionrespuestapredeterminadapregunta;
    }

    public Long getIdAsignacionrespuestapredeterminadapregunta() {
        return idAsignacionrespuestapredeterminadapregunta;
    }

    public void setIdAsignacionrespuestapredeterminadapregunta(Long idAsignacionrespuestapredeterminadapregunta) {
        this.idAsignacionrespuestapredeterminadapregunta = idAsignacionrespuestapredeterminadapregunta;
    }

    public BigInteger getOrdenRespuesta() {
        return ordenRespuesta;
    }

    public void setOrdenRespuesta(BigInteger ordenRespuesta) {
        this.ordenRespuesta = ordenRespuesta;
    }

    public BigInteger getValorRespuesta() {
        return valorRespuesta;
    }

    public void setValorRespuesta(BigInteger valorRespuesta) {
        this.valorRespuesta = valorRespuesta;
    }

    public RespuestaPredeterminada getIdRespuestapredeterminada() {
        return idRespuestapredeterminada;
    }

    public void setIdRespuestapredeterminada(RespuestaPredeterminada idRespuestapredeterminada) {
        this.idRespuestapredeterminada = idRespuestapredeterminada;
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
        hash += (idAsignacionrespuestapredeterminadapregunta != null ? idAsignacionrespuestapredeterminadapregunta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsignacionRespuestapredeterminadapregunta)) {
            return false;
        }
        AsignacionRespuestapredeterminadapregunta other = (AsignacionRespuestapredeterminadapregunta) object;
        if ((this.idAsignacionrespuestapredeterminadapregunta == null && other.idAsignacionrespuestapredeterminadapregunta != null) || (this.idAsignacionrespuestapredeterminadapregunta != null && !this.idAsignacionrespuestapredeterminadapregunta.equals(other.idAsignacionrespuestapredeterminadapregunta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.AsignacionRespuestapredeterminadapregunta[ idAsignacionrespuestapredeterminadapregunta=" + idAsignacionrespuestapredeterminadapregunta + " ]";
    }
    
}
