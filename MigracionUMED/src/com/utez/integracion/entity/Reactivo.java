/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "reactivo")
@NamedQueries({
    @NamedQuery(name = "Reactivo.findAll", query = "SELECT r FROM Reactivo r"),
    @NamedQuery(name = "Reactivo.findByIdReactivo", query = "SELECT r FROM Reactivo r WHERE r.idReactivo = :idReactivo"),
    @NamedQuery(name = "Reactivo.findByUnidad", query = "SELECT r FROM Reactivo r WHERE r.unidad = :unidad"),
    @NamedQuery(name = "Reactivo.findByEstadoReactivo", query = "SELECT r FROM Reactivo r WHERE r.estadoReactivo = :estadoReactivo")})
public class Reactivo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_reactivo")
    private Long idReactivo;
    @Column(name = "unidad")
    private Integer unidad;
    @Column(name = "estado_reactivo")
    private String estadoReactivo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reactivo")
    private List<ReactivoExamenimpreso> reactivoExamenimpresoList;
    @JoinColumn(name = "id_tiporeactivo", referencedColumnName = "id_tiporeactivo")
    @ManyToOne
    private TipoReactivo idTiporeactivo;
    @JoinColumn(name = "id_tipoambito", referencedColumnName = "id_tipoambito")
    @ManyToOne
    private TipoAmbito idTipoambito;
    @JoinColumn(name = "id_bancoreactivo", referencedColumnName = "id_bancoreactivo")
    @ManyToOne
    private BancoReactivo idBancoreactivo;
    @OneToMany(mappedBy = "idReactivo")
    private List<ContenidoReactivo> contenidoReactivoList;
    @OneToMany(mappedBy = "idReactivo")
    private List<ReactivoImagen> reactivoImagenList;

    public Reactivo() {
    }

    public Reactivo(Long idReactivo) {
        this.idReactivo = idReactivo;
    }

    public Long getIdReactivo() {
        return idReactivo;
    }

    public void setIdReactivo(Long idReactivo) {
        this.idReactivo = idReactivo;
    }

    public Integer getUnidad() {
        return unidad;
    }

    public void setUnidad(Integer unidad) {
        this.unidad = unidad;
    }

    public String getEstadoReactivo() {
        return estadoReactivo;
    }

    public void setEstadoReactivo(String estadoReactivo) {
        this.estadoReactivo = estadoReactivo;
    }

    public List<ReactivoExamenimpreso> getReactivoExamenimpresoList() {
        return reactivoExamenimpresoList;
    }

    public void setReactivoExamenimpresoList(List<ReactivoExamenimpreso> reactivoExamenimpresoList) {
        this.reactivoExamenimpresoList = reactivoExamenimpresoList;
    }

    public TipoReactivo getIdTiporeactivo() {
        return idTiporeactivo;
    }

    public void setIdTiporeactivo(TipoReactivo idTiporeactivo) {
        this.idTiporeactivo = idTiporeactivo;
    }

    public TipoAmbito getIdTipoambito() {
        return idTipoambito;
    }

    public void setIdTipoambito(TipoAmbito idTipoambito) {
        this.idTipoambito = idTipoambito;
    }

    public BancoReactivo getIdBancoreactivo() {
        return idBancoreactivo;
    }

    public void setIdBancoreactivo(BancoReactivo idBancoreactivo) {
        this.idBancoreactivo = idBancoreactivo;
    }

    public List<ContenidoReactivo> getContenidoReactivoList() {
        return contenidoReactivoList;
    }

    public void setContenidoReactivoList(List<ContenidoReactivo> contenidoReactivoList) {
        this.contenidoReactivoList = contenidoReactivoList;
    }

    public List<ReactivoImagen> getReactivoImagenList() {
        return reactivoImagenList;
    }

    public void setReactivoImagenList(List<ReactivoImagen> reactivoImagenList) {
        this.reactivoImagenList = reactivoImagenList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idReactivo != null ? idReactivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reactivo)) {
            return false;
        }
        Reactivo other = (Reactivo) object;
        if ((this.idReactivo == null && other.idReactivo != null) || (this.idReactivo != null && !this.idReactivo.equals(other.idReactivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.Reactivo[ idReactivo=" + idReactivo + " ]";
    }
    
}
