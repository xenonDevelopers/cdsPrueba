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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "respuesta_reactivo")
@NamedQueries({
    @NamedQuery(name = "RespuestaReactivo.findAll", query = "SELECT r FROM RespuestaReactivo r"),
    @NamedQuery(name = "RespuestaReactivo.findByIdRespuestareactivo", query = "SELECT r FROM RespuestaReactivo r WHERE r.idRespuestareactivo = :idRespuestareactivo"),
    @NamedQuery(name = "RespuestaReactivo.findByDescripcionRespuesta", query = "SELECT r FROM RespuestaReactivo r WHERE r.descripcionRespuesta = :descripcionRespuesta"),
    @NamedQuery(name = "RespuestaReactivo.findByRespuestaCorrecta", query = "SELECT r FROM RespuestaReactivo r WHERE r.respuestaCorrecta = :respuestaCorrecta")})
public class RespuestaReactivo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_respuestareactivo")
    private Long idRespuestareactivo;
    @Column(name = "descripcion_respuesta")
    private String descripcionRespuesta;
    @Column(name = "respuesta_correcta")
    private Boolean respuestaCorrecta;
    @OneToMany(mappedBy = "idRespuestareactivo")
    private List<RespuestaImagen> respuestaImagenList;
    @JoinColumn(name = "id_contenidoreactivo", referencedColumnName = "id_contenidoreactivo")
    @ManyToOne
    private ContenidoReactivo idContenidoreactivo;

    public RespuestaReactivo() {
    }

    public RespuestaReactivo(Long idRespuestareactivo) {
        this.idRespuestareactivo = idRespuestareactivo;
    }

    public Long getIdRespuestareactivo() {
        return idRespuestareactivo;
    }

    public void setIdRespuestareactivo(Long idRespuestareactivo) {
        this.idRespuestareactivo = idRespuestareactivo;
    }

    public String getDescripcionRespuesta() {
        return descripcionRespuesta;
    }

    public void setDescripcionRespuesta(String descripcionRespuesta) {
        this.descripcionRespuesta = descripcionRespuesta;
    }

    public Boolean getRespuestaCorrecta() {
        return respuestaCorrecta;
    }

    public void setRespuestaCorrecta(Boolean respuestaCorrecta) {
        this.respuestaCorrecta = respuestaCorrecta;
    }

    public List<RespuestaImagen> getRespuestaImagenList() {
        return respuestaImagenList;
    }

    public void setRespuestaImagenList(List<RespuestaImagen> respuestaImagenList) {
        this.respuestaImagenList = respuestaImagenList;
    }

    public ContenidoReactivo getIdContenidoreactivo() {
        return idContenidoreactivo;
    }

    public void setIdContenidoreactivo(ContenidoReactivo idContenidoreactivo) {
        this.idContenidoreactivo = idContenidoreactivo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRespuestareactivo != null ? idRespuestareactivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RespuestaReactivo)) {
            return false;
        }
        RespuestaReactivo other = (RespuestaReactivo) object;
        if ((this.idRespuestareactivo == null && other.idRespuestareactivo != null) || (this.idRespuestareactivo != null && !this.idRespuestareactivo.equals(other.idRespuestareactivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.RespuestaReactivo[ idRespuestareactivo=" + idRespuestareactivo + " ]";
    }
    
}
