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
@Table(name = "tipo_evento")
@NamedQueries({
    @NamedQuery(name = "TipoEvento.findAll", query = "SELECT t FROM TipoEvento t"),
    @NamedQuery(name = "TipoEvento.findByIdTipoevento", query = "SELECT t FROM TipoEvento t WHERE t.idTipoevento = :idTipoevento"),
    @NamedQuery(name = "TipoEvento.findByDescripcionTipoevento", query = "SELECT t FROM TipoEvento t WHERE t.descripcionTipoevento = :descripcionTipoevento")})
public class TipoEvento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipoevento")
    private Long idTipoevento;
    @Column(name = "descripcion_tipoevento")
    private String descripcionTipoevento;
    @OneToMany(mappedBy = "idTipoevento")
    private List<Evento> eventoList;

    public TipoEvento() {
    }

    public TipoEvento(Long idTipoevento) {
        this.idTipoevento = idTipoevento;
    }

    public Long getIdTipoevento() {
        return idTipoevento;
    }

    public void setIdTipoevento(Long idTipoevento) {
        this.idTipoevento = idTipoevento;
    }

    public String getDescripcionTipoevento() {
        return descripcionTipoevento;
    }

    public void setDescripcionTipoevento(String descripcionTipoevento) {
        this.descripcionTipoevento = descripcionTipoevento;
    }

    public List<Evento> getEventoList() {
        return eventoList;
    }

    public void setEventoList(List<Evento> eventoList) {
        this.eventoList = eventoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoevento != null ? idTipoevento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoEvento)) {
            return false;
        }
        TipoEvento other = (TipoEvento) object;
        if ((this.idTipoevento == null && other.idTipoevento != null) || (this.idTipoevento != null && !this.idTipoevento.equals(other.idTipoevento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.TipoEvento[ idTipoevento=" + idTipoevento + " ]";
    }
    
}
