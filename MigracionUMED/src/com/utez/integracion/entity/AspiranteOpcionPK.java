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
public class AspiranteOpcionPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id_aspiranteopcion")
    private long idAspiranteopcion;
    @Basic(optional = false)
    @Column(name = "id_aspirante")
    private long idAspirante;
    @Basic(optional = false)
    @Column(name = "id_opcionestudio")
    private String idOpcionestudio;

    public AspiranteOpcionPK() {
    }

    public AspiranteOpcionPK(long idAspiranteopcion, long idAspirante, String idOpcionestudio) {
        this.idAspiranteopcion = idAspiranteopcion;
        this.idAspirante = idAspirante;
        this.idOpcionestudio = idOpcionestudio;
    }

    public long getIdAspiranteopcion() {
        return idAspiranteopcion;
    }

    public void setIdAspiranteopcion(long idAspiranteopcion) {
        this.idAspiranteopcion = idAspiranteopcion;
    }

    public long getIdAspirante() {
        return idAspirante;
    }

    public void setIdAspirante(long idAspirante) {
        this.idAspirante = idAspirante;
    }

    public String getIdOpcionestudio() {
        return idOpcionestudio;
    }

    public void setIdOpcionestudio(String idOpcionestudio) {
        this.idOpcionestudio = idOpcionestudio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idAspiranteopcion;
        hash += (int) idAspirante;
        hash += (idOpcionestudio != null ? idOpcionestudio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AspiranteOpcionPK)) {
            return false;
        }
        AspiranteOpcionPK other = (AspiranteOpcionPK) object;
        if (this.idAspiranteopcion != other.idAspiranteopcion) {
            return false;
        }
        if (this.idAspirante != other.idAspirante) {
            return false;
        }
        if ((this.idOpcionestudio == null && other.idOpcionestudio != null) || (this.idOpcionestudio != null && !this.idOpcionestudio.equals(other.idOpcionestudio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.AspiranteOpcionPK[ idAspiranteopcion=" + idAspiranteopcion + ", idAspirante=" + idAspirante + ", idOpcionestudio=" + idOpcionestudio + " ]";
    }
    
}
