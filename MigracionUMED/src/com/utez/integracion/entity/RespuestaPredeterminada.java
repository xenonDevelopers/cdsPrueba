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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "respuesta_predeterminada")
@NamedQueries({
    @NamedQuery(name = "RespuestaPredeterminada.findAll", query = "SELECT r FROM RespuestaPredeterminada r"),
    @NamedQuery(name = "RespuestaPredeterminada.findByIdRespuestapredeterminada", query = "SELECT r FROM RespuestaPredeterminada r WHERE r.idRespuestapredeterminada = :idRespuestapredeterminada"),
    @NamedQuery(name = "RespuestaPredeterminada.findByDescripcionRespuestapredeterminada", query = "SELECT r FROM RespuestaPredeterminada r WHERE r.descripcionRespuestapredeterminada = :descripcionRespuestapredeterminada")})
public class RespuestaPredeterminada implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_respuestapredeterminada")
    private Long idRespuestapredeterminada;
    @Column(name = "descripcion_respuestapredeterminada")
    private String descripcionRespuestapredeterminada;
    @OneToMany(mappedBy = "idRespuestapredeterminada")
    private List<AsignacionRespuestapredeterminadapregunta> asignacionRespuestapredeterminadapreguntaList;

    public RespuestaPredeterminada() {
    }

    public RespuestaPredeterminada(Long idRespuestapredeterminada) {
        this.idRespuestapredeterminada = idRespuestapredeterminada;
    }

    public Long getIdRespuestapredeterminada() {
        return idRespuestapredeterminada;
    }

    public void setIdRespuestapredeterminada(Long idRespuestapredeterminada) {
        this.idRespuestapredeterminada = idRespuestapredeterminada;
    }

    public String getDescripcionRespuestapredeterminada() {
        return descripcionRespuestapredeterminada;
    }

    public void setDescripcionRespuestapredeterminada(String descripcionRespuestapredeterminada) {
        this.descripcionRespuestapredeterminada = descripcionRespuestapredeterminada;
    }

    public List<AsignacionRespuestapredeterminadapregunta> getAsignacionRespuestapredeterminadapreguntaList() {
        return asignacionRespuestapredeterminadapreguntaList;
    }

    public void setAsignacionRespuestapredeterminadapreguntaList(List<AsignacionRespuestapredeterminadapregunta> asignacionRespuestapredeterminadapreguntaList) {
        this.asignacionRespuestapredeterminadapreguntaList = asignacionRespuestapredeterminadapreguntaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRespuestapredeterminada != null ? idRespuestapredeterminada.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RespuestaPredeterminada)) {
            return false;
        }
        RespuestaPredeterminada other = (RespuestaPredeterminada) object;
        if ((this.idRespuestapredeterminada == null && other.idRespuestapredeterminada != null) || (this.idRespuestapredeterminada != null && !this.idRespuestapredeterminada.equals(other.idRespuestapredeterminada))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.RespuestaPredeterminada[ idRespuestapredeterminada=" + idRespuestapredeterminada + " ]";
    }
    
}
