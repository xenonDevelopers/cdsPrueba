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
@Table(name = "tipo_respuesta")
@NamedQueries({
    @NamedQuery(name = "TipoRespuesta.findAll", query = "SELECT t FROM TipoRespuesta t"),
    @NamedQuery(name = "TipoRespuesta.findByIdTiporespuesta", query = "SELECT t FROM TipoRespuesta t WHERE t.idTiporespuesta = :idTiporespuesta"),
    @NamedQuery(name = "TipoRespuesta.findByDescripcionTiporespuesta", query = "SELECT t FROM TipoRespuesta t WHERE t.descripcionTiporespuesta = :descripcionTiporespuesta")})
public class TipoRespuesta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tiporespuesta")
    private Long idTiporespuesta;
    @Column(name = "descripcion_tiporespuesta")
    private String descripcionTiporespuesta;
    @OneToMany(mappedBy = "idTiporespuesta")
    private List<RespuestaEncuesta> respuestaEncuestaList;
    @OneToMany(mappedBy = "idTiporespuesta")
    private List<RespuestaEncuestadocente> respuestaEncuestadocenteList;

    public TipoRespuesta() {
    }

    public TipoRespuesta(Long idTiporespuesta) {
        this.idTiporespuesta = idTiporespuesta;
    }

    public Long getIdTiporespuesta() {
        return idTiporespuesta;
    }

    public void setIdTiporespuesta(Long idTiporespuesta) {
        this.idTiporespuesta = idTiporespuesta;
    }

    public String getDescripcionTiporespuesta() {
        return descripcionTiporespuesta;
    }

    public void setDescripcionTiporespuesta(String descripcionTiporespuesta) {
        this.descripcionTiporespuesta = descripcionTiporespuesta;
    }

    public List<RespuestaEncuesta> getRespuestaEncuestaList() {
        return respuestaEncuestaList;
    }

    public void setRespuestaEncuestaList(List<RespuestaEncuesta> respuestaEncuestaList) {
        this.respuestaEncuestaList = respuestaEncuestaList;
    }

    public List<RespuestaEncuestadocente> getRespuestaEncuestadocenteList() {
        return respuestaEncuestadocenteList;
    }

    public void setRespuestaEncuestadocenteList(List<RespuestaEncuestadocente> respuestaEncuestadocenteList) {
        this.respuestaEncuestadocenteList = respuestaEncuestadocenteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTiporespuesta != null ? idTiporespuesta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoRespuesta)) {
            return false;
        }
        TipoRespuesta other = (TipoRespuesta) object;
        if ((this.idTiporespuesta == null && other.idTiporespuesta != null) || (this.idTiporespuesta != null && !this.idTiporespuesta.equals(other.idTiporespuesta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.TipoRespuesta[ idTiporespuesta=" + idTiporespuesta + " ]";
    }
    
}
