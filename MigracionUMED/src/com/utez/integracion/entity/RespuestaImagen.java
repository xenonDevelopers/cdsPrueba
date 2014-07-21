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
@Table(name = "respuesta_imagen")
@NamedQueries({
    @NamedQuery(name = "RespuestaImagen.findAll", query = "SELECT r FROM RespuestaImagen r"),
    @NamedQuery(name = "RespuestaImagen.findByIdRespuestaimagen", query = "SELECT r FROM RespuestaImagen r WHERE r.idRespuestaimagen = :idRespuestaimagen"),
    @NamedQuery(name = "RespuestaImagen.findByImagenRespuesta", query = "SELECT r FROM RespuestaImagen r WHERE r.imagenRespuesta = :imagenRespuesta")})
public class RespuestaImagen implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_respuestaimagen")
    private Long idRespuestaimagen;
    @Column(name = "imagen_respuesta")
    private String imagenRespuesta;
    @JoinColumn(name = "id_respuestareactivo", referencedColumnName = "id_respuestareactivo")
    @ManyToOne
    private RespuestaReactivo idRespuestareactivo;

    public RespuestaImagen() {
    }

    public RespuestaImagen(Long idRespuestaimagen) {
        this.idRespuestaimagen = idRespuestaimagen;
    }

    public Long getIdRespuestaimagen() {
        return idRespuestaimagen;
    }

    public void setIdRespuestaimagen(Long idRespuestaimagen) {
        this.idRespuestaimagen = idRespuestaimagen;
    }

    public String getImagenRespuesta() {
        return imagenRespuesta;
    }

    public void setImagenRespuesta(String imagenRespuesta) {
        this.imagenRespuesta = imagenRespuesta;
    }

    public RespuestaReactivo getIdRespuestareactivo() {
        return idRespuestareactivo;
    }

    public void setIdRespuestareactivo(RespuestaReactivo idRespuestareactivo) {
        this.idRespuestareactivo = idRespuestareactivo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRespuestaimagen != null ? idRespuestaimagen.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RespuestaImagen)) {
            return false;
        }
        RespuestaImagen other = (RespuestaImagen) object;
        if ((this.idRespuestaimagen == null && other.idRespuestaimagen != null) || (this.idRespuestaimagen != null && !this.idRespuestaimagen.equals(other.idRespuestaimagen))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.RespuestaImagen[ idRespuestaimagen=" + idRespuestaimagen + " ]";
    }
    
}
