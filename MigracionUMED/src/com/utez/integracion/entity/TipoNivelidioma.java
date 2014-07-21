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
@Table(name = "tipo_nivelidioma")
@NamedQueries({
    @NamedQuery(name = "TipoNivelidioma.findAll", query = "SELECT t FROM TipoNivelidioma t"),
    @NamedQuery(name = "TipoNivelidioma.findByIdTiponivelidioma", query = "SELECT t FROM TipoNivelidioma t WHERE t.idTiponivelidioma = :idTiponivelidioma"),
    @NamedQuery(name = "TipoNivelidioma.findByDescripcionTiponivelidioma", query = "SELECT t FROM TipoNivelidioma t WHERE t.descripcionTiponivelidioma = :descripcionTiponivelidioma")})
public class TipoNivelidioma implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tiponivelidioma")
    private Long idTiponivelidioma;
    @Column(name = "descripcion_tiponivelidioma")
    private String descripcionTiponivelidioma;
    @OneToMany(mappedBy = "idTiponivelidioma")
    private List<Idioma> idiomaList;

    public TipoNivelidioma() {
    }

    public TipoNivelidioma(Long idTiponivelidioma) {
        this.idTiponivelidioma = idTiponivelidioma;
    }

    public Long getIdTiponivelidioma() {
        return idTiponivelidioma;
    }

    public void setIdTiponivelidioma(Long idTiponivelidioma) {
        this.idTiponivelidioma = idTiponivelidioma;
    }

    public String getDescripcionTiponivelidioma() {
        return descripcionTiponivelidioma;
    }

    public void setDescripcionTiponivelidioma(String descripcionTiponivelidioma) {
        this.descripcionTiponivelidioma = descripcionTiponivelidioma;
    }

    public List<Idioma> getIdiomaList() {
        return idiomaList;
    }

    public void setIdiomaList(List<Idioma> idiomaList) {
        this.idiomaList = idiomaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTiponivelidioma != null ? idTiponivelidioma.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoNivelidioma)) {
            return false;
        }
        TipoNivelidioma other = (TipoNivelidioma) object;
        if ((this.idTiponivelidioma == null && other.idTiponivelidioma != null) || (this.idTiponivelidioma != null && !this.idTiponivelidioma.equals(other.idTiponivelidioma))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.TipoNivelidioma[ idTiponivelidioma=" + idTiponivelidioma + " ]";
    }
    
}
