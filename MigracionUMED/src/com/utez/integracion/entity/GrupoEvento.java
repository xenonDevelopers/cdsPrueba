/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "grupo_evento")
@NamedQueries({
    @NamedQuery(name = "GrupoEvento.findAll", query = "SELECT g FROM GrupoEvento g"),
    @NamedQuery(name = "GrupoEvento.findByIdOfertaevento", query = "SELECT g FROM GrupoEvento g WHERE g.grupoEventoPK.idOfertaevento = :idOfertaevento"),
    @NamedQuery(name = "GrupoEvento.findByCurp", query = "SELECT g FROM GrupoEvento g WHERE g.grupoEventoPK.curp = :curp")})
public class GrupoEvento implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected GrupoEventoPK grupoEventoPK;

    public GrupoEvento() {
    }

    public GrupoEvento(GrupoEventoPK grupoEventoPK) {
        this.grupoEventoPK = grupoEventoPK;
    }

    public GrupoEvento(long idOfertaevento, String curp) {
        this.grupoEventoPK = new GrupoEventoPK(idOfertaevento, curp);
    }

    public GrupoEventoPK getGrupoEventoPK() {
        return grupoEventoPK;
    }

    public void setGrupoEventoPK(GrupoEventoPK grupoEventoPK) {
        this.grupoEventoPK = grupoEventoPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (grupoEventoPK != null ? grupoEventoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GrupoEvento)) {
            return false;
        }
        GrupoEvento other = (GrupoEvento) object;
        if ((this.grupoEventoPK == null && other.grupoEventoPK != null) || (this.grupoEventoPK != null && !this.grupoEventoPK.equals(other.grupoEventoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.GrupoEvento[ grupoEventoPK=" + grupoEventoPK + " ]";
    }
    
}
