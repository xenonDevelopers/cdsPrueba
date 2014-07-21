/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Sergio
 */
@Embeddable
public class GrupoEventoPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id_ofertaevento")
    private long idOfertaevento;
    @Basic(optional = false)
    @Column(name = "curp")
    private String curp;

    public GrupoEventoPK() {
    }

    public GrupoEventoPK(long idOfertaevento, String curp) {
        this.idOfertaevento = idOfertaevento;
        this.curp = curp;
    }

    public long getIdOfertaevento() {
        return idOfertaevento;
    }

    public void setIdOfertaevento(long idOfertaevento) {
        this.idOfertaevento = idOfertaevento;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idOfertaevento;
        hash += (curp != null ? curp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GrupoEventoPK)) {
            return false;
        }
        GrupoEventoPK other = (GrupoEventoPK) object;
        if (this.idOfertaevento != other.idOfertaevento) {
            return false;
        }
        if ((this.curp == null && other.curp != null) || (this.curp != null && !this.curp.equals(other.curp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.GrupoEventoPK[ idOfertaevento=" + idOfertaevento + ", curp=" + curp + " ]";
    }
    
}
