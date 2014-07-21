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
@Table(name = "tipo_autor")
@NamedQueries({
    @NamedQuery(name = "TipoAutor.findAll", query = "SELECT t FROM TipoAutor t"),
    @NamedQuery(name = "TipoAutor.findByIdTipoautor", query = "SELECT t FROM TipoAutor t WHERE t.idTipoautor = :idTipoautor"),
    @NamedQuery(name = "TipoAutor.findByDescripcionTipoautor", query = "SELECT t FROM TipoAutor t WHERE t.descripcionTipoautor = :descripcionTipoautor")})
public class TipoAutor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipoautor")
    private Long idTipoautor;
    @Column(name = "descripcion_tipoautor")
    private String descripcionTipoautor;
    @OneToMany(mappedBy = "idTipoautor")
    private List<AsignacionAutorintegradora> asignacionAutorintegradoraList;
    @OneToMany(mappedBy = "idTipoautor")
    private List<AsignacionAutorbanco> asignacionAutorbancoList;

    public TipoAutor() {
    }

    public TipoAutor(Long idTipoautor) {
        this.idTipoautor = idTipoautor;
    }

    public Long getIdTipoautor() {
        return idTipoautor;
    }

    public void setIdTipoautor(Long idTipoautor) {
        this.idTipoautor = idTipoautor;
    }

    public String getDescripcionTipoautor() {
        return descripcionTipoautor;
    }

    public void setDescripcionTipoautor(String descripcionTipoautor) {
        this.descripcionTipoautor = descripcionTipoautor;
    }

    public List<AsignacionAutorintegradora> getAsignacionAutorintegradoraList() {
        return asignacionAutorintegradoraList;
    }

    public void setAsignacionAutorintegradoraList(List<AsignacionAutorintegradora> asignacionAutorintegradoraList) {
        this.asignacionAutorintegradoraList = asignacionAutorintegradoraList;
    }

    public List<AsignacionAutorbanco> getAsignacionAutorbancoList() {
        return asignacionAutorbancoList;
    }

    public void setAsignacionAutorbancoList(List<AsignacionAutorbanco> asignacionAutorbancoList) {
        this.asignacionAutorbancoList = asignacionAutorbancoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoautor != null ? idTipoautor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoAutor)) {
            return false;
        }
        TipoAutor other = (TipoAutor) object;
        if ((this.idTipoautor == null && other.idTipoautor != null) || (this.idTipoautor != null && !this.idTipoautor.equals(other.idTipoautor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.TipoAutor[ idTipoautor=" + idTipoautor + " ]";
    }
    
}
