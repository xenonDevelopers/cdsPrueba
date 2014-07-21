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
@Table(name = "respuesta_abiertadocente")
@NamedQueries({
    @NamedQuery(name = "RespuestaAbiertadocente.findAll", query = "SELECT r FROM RespuestaAbiertadocente r"),
    @NamedQuery(name = "RespuestaAbiertadocente.findByIdRespuestaabiertadocente", query = "SELECT r FROM RespuestaAbiertadocente r WHERE r.idRespuestaabiertadocente = :idRespuestaabiertadocente"),
    @NamedQuery(name = "RespuestaAbiertadocente.findByDescripcionRespuestaabierta", query = "SELECT r FROM RespuestaAbiertadocente r WHERE r.descripcionRespuestaabierta = :descripcionRespuestaabierta")})
public class RespuestaAbiertadocente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_respuestaabiertadocente")
    private Long idRespuestaabiertadocente;
    @Column(name = "descripcion_respuestaabierta")
    private String descripcionRespuestaabierta;

    public RespuestaAbiertadocente() {
    }

    public RespuestaAbiertadocente(Long idRespuestaabiertadocente) {
        this.idRespuestaabiertadocente = idRespuestaabiertadocente;
    }

    public Long getIdRespuestaabiertadocente() {
        return idRespuestaabiertadocente;
    }

    public void setIdRespuestaabiertadocente(Long idRespuestaabiertadocente) {
        this.idRespuestaabiertadocente = idRespuestaabiertadocente;
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
        hash += (idRespuestaabiertadocente != null ? idRespuestaabiertadocente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RespuestaAbiertadocente)) {
            return false;
        }
        RespuestaAbiertadocente other = (RespuestaAbiertadocente) object;
        if ((this.idRespuestaabiertadocente == null && other.idRespuestaabiertadocente != null) || (this.idRespuestaabiertadocente != null && !this.idRespuestaabiertadocente.equals(other.idRespuestaabiertadocente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.RespuestaAbiertadocente[ idRespuestaabiertadocente=" + idRespuestaabiertadocente + " ]";
    }
    
}
