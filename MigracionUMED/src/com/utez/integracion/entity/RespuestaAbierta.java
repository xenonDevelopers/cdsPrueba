/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "respuesta_abierta")
@NamedQueries({
    @NamedQuery(name = "RespuestaAbierta.findAll", query = "SELECT r FROM RespuestaAbierta r"),
    @NamedQuery(name = "RespuestaAbierta.findByIdRespuestaabierta", query = "SELECT r FROM RespuestaAbierta r WHERE r.idRespuestaabierta = :idRespuestaabierta"),
    @NamedQuery(name = "RespuestaAbierta.findByDescripcionRespuestaabierta", query = "SELECT r FROM RespuestaAbierta r WHERE r.descripcionRespuestaabierta = :descripcionRespuestaabierta")})
public class RespuestaAbierta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_respuestaabierta")
    private Long idRespuestaabierta;
    @Column(name = "descripcion_respuestaabierta")
    private String descripcionRespuestaabierta;

    public RespuestaAbierta() {
    }

    public RespuestaAbierta(Long idRespuestaabierta) {
        this.idRespuestaabierta = idRespuestaabierta;
    }

    public Long getIdRespuestaabierta() {
        return idRespuestaabierta;
    }

    public void setIdRespuestaabierta(Long idRespuestaabierta) {
        this.idRespuestaabierta = idRespuestaabierta;
    }

    public String getDescripcionRespuestaabierta() {
        return descripcionRespuestaabierta;
    }

    public void setDescripcionRespuestaabierta(String descripcionRespuestaabierta) {
        this.descripcionRespuestaabierta = descripcionRespuestaabierta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRespuestaabierta != null ? idRespuestaabierta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RespuestaAbierta)) {
            return false;
        }
        RespuestaAbierta other = (RespuestaAbierta) object;
        if ((this.idRespuestaabierta == null && other.idRespuestaabierta != null) || (this.idRespuestaabierta != null && !this.idRespuestaabierta.equals(other.idRespuestaabierta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.RespuestaAbierta[ idRespuestaabierta=" + idRespuestaabierta + " ]";
    }
    
}
