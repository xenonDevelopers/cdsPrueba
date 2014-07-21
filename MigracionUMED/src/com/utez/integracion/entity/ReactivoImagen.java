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
@Table(name = "reactivo_imagen")
@NamedQueries({
    @NamedQuery(name = "ReactivoImagen.findAll", query = "SELECT r FROM ReactivoImagen r"),
    @NamedQuery(name = "ReactivoImagen.findByIdReactivoimagen", query = "SELECT r FROM ReactivoImagen r WHERE r.idReactivoimagen = :idReactivoimagen"),
    @NamedQuery(name = "ReactivoImagen.findByImagenReactivo", query = "SELECT r FROM ReactivoImagen r WHERE r.imagenReactivo = :imagenReactivo")})
public class ReactivoImagen implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_reactivoimagen")
    private Long idReactivoimagen;
    @Column(name = "imagen_reactivo")
    private String imagenReactivo;
    @JoinColumn(name = "id_reactivo", referencedColumnName = "id_reactivo")
    @ManyToOne
    private Reactivo idReactivo;

    public ReactivoImagen() {
    }

    public ReactivoImagen(Long idReactivoimagen) {
        this.idReactivoimagen = idReactivoimagen;
    }

    public Long getIdReactivoimagen() {
        return idReactivoimagen;
    }

    public void setIdReactivoimagen(Long idReactivoimagen) {
        this.idReactivoimagen = idReactivoimagen;
    }

    public String getImagenReactivo() {
        return imagenReactivo;
    }

    public void setImagenReactivo(String imagenReactivo) {
        this.imagenReactivo = imagenReactivo;
    }

    public Reactivo getIdReactivo() {
        return idReactivo;
    }

    public void setIdReactivo(Reactivo idReactivo) {
        this.idReactivo = idReactivo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idReactivoimagen != null ? idReactivoimagen.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReactivoImagen)) {
            return false;
        }
        ReactivoImagen other = (ReactivoImagen) object;
        if ((this.idReactivoimagen == null && other.idReactivoimagen != null) || (this.idReactivoimagen != null && !this.idReactivoimagen.equals(other.idReactivoimagen))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.ReactivoImagen[ idReactivoimagen=" + idReactivoimagen + " ]";
    }
    
}
