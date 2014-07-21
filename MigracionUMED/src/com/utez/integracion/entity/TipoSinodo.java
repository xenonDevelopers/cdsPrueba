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
@Table(name = "tipo_sinodo")
@NamedQueries({
    @NamedQuery(name = "TipoSinodo.findAll", query = "SELECT t FROM TipoSinodo t"),
    @NamedQuery(name = "TipoSinodo.findByIdTiposinodo", query = "SELECT t FROM TipoSinodo t WHERE t.idTiposinodo = :idTiposinodo"),
    @NamedQuery(name = "TipoSinodo.findByDescripcionTiposinodo", query = "SELECT t FROM TipoSinodo t WHERE t.descripcionTiposinodo = :descripcionTiposinodo"),
    @NamedQuery(name = "TipoSinodo.findByOrdenSinodo", query = "SELECT t FROM TipoSinodo t WHERE t.ordenSinodo = :ordenSinodo")})
public class TipoSinodo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tiposinodo")
    private Long idTiposinodo;
    @Column(name = "descripcion_tiposinodo")
    private String descripcionTiposinodo;
    @Column(name = "orden_sinodo")
    private Integer ordenSinodo;
    @OneToMany(mappedBy = "idTiposinodo")
    private List<SinodoExamen> sinodoExamenList;

    public TipoSinodo() {
    }

    public TipoSinodo(Long idTiposinodo) {
        this.idTiposinodo = idTiposinodo;
    }

    public Long getIdTiposinodo() {
        return idTiposinodo;
    }

    public void setIdTiposinodo(Long idTiposinodo) {
        this.idTiposinodo = idTiposinodo;
    }

    public String getDescripcionTiposinodo() {
        return descripcionTiposinodo;
    }

    public void setDescripcionTiposinodo(String descripcionTiposinodo) {
        this.descripcionTiposinodo = descripcionTiposinodo;
    }

    public Integer getOrdenSinodo() {
        return ordenSinodo;
    }

    public void setOrdenSinodo(Integer ordenSinodo) {
        this.ordenSinodo = ordenSinodo;
    }

    public List<SinodoExamen> getSinodoExamenList() {
        return sinodoExamenList;
    }

    public void setSinodoExamenList(List<SinodoExamen> sinodoExamenList) {
        this.sinodoExamenList = sinodoExamenList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTiposinodo != null ? idTiposinodo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoSinodo)) {
            return false;
        }
        TipoSinodo other = (TipoSinodo) object;
        if ((this.idTiposinodo == null && other.idTiposinodo != null) || (this.idTiposinodo != null && !this.idTiposinodo.equals(other.idTiposinodo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.TipoSinodo[ idTiposinodo=" + idTiposinodo + " ]";
    }
    
}
