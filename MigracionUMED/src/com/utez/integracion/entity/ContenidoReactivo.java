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
@Table(name = "contenido_reactivo")
@NamedQueries({
    @NamedQuery(name = "ContenidoReactivo.findAll", query = "SELECT c FROM ContenidoReactivo c"),
    @NamedQuery(name = "ContenidoReactivo.findByIdContenidoreactivo", query = "SELECT c FROM ContenidoReactivo c WHERE c.idContenidoreactivo = :idContenidoreactivo"),
    @NamedQuery(name = "ContenidoReactivo.findByReactivo", query = "SELECT c FROM ContenidoReactivo c WHERE c.reactivo = :reactivo")})
public class ContenidoReactivo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_contenidoreactivo")
    private Long idContenidoreactivo;
    @Column(name = "reactivo")
    private String reactivo;
    @JoinColumn(name = "id_reactivo", referencedColumnName = "id_reactivo")
    @ManyToOne
    private Reactivo idReactivo;
    @OneToMany(mappedBy = "idContenido")
    private List<RespuestaEvaluacion> respuestaEvaluacionList;
    @OneToMany(mappedBy = "idContenidoreactivo")
    private List<RespuestaReactivo> respuestaReactivoList;

    public ContenidoReactivo() {
    }

    public ContenidoReactivo(Long idContenidoreactivo) {
        this.idContenidoreactivo = idContenidoreactivo;
    }

    public Long getIdContenidoreactivo() {
        return idContenidoreactivo;
    }

    public void setIdContenidoreactivo(Long idContenidoreactivo) {
        this.idContenidoreactivo = idContenidoreactivo;
    }

    public String getReactivo() {
        return reactivo;
    }

    public void setReactivo(String reactivo) {
        this.reactivo = reactivo;
    }

    public Reactivo getIdReactivo() {
        return idReactivo;
    }

    public void setIdReactivo(Reactivo idReactivo) {
        this.idReactivo = idReactivo;
    }

    public List<RespuestaEvaluacion> getRespuestaEvaluacionList() {
        return respuestaEvaluacionList;
    }

    public void setRespuestaEvaluacionList(List<RespuestaEvaluacion> respuestaEvaluacionList) {
        this.respuestaEvaluacionList = respuestaEvaluacionList;
    }

    public List<RespuestaReactivo> getRespuestaReactivoList() {
        return respuestaReactivoList;
    }

    public void setRespuestaReactivoList(List<RespuestaReactivo> respuestaReactivoList) {
        this.respuestaReactivoList = respuestaReactivoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idContenidoreactivo != null ? idContenidoreactivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContenidoReactivo)) {
            return false;
        }
        ContenidoReactivo other = (ContenidoReactivo) object;
        if ((this.idContenidoreactivo == null && other.idContenidoreactivo != null) || (this.idContenidoreactivo != null && !this.idContenidoreactivo.equals(other.idContenidoreactivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.ContenidoReactivo[ idContenidoreactivo=" + idContenidoreactivo + " ]";
    }
    
}
