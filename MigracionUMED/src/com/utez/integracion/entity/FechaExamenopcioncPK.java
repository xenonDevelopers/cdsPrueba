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
public class FechaExamenopcioncPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id_asignaturaopcionc")
    private long idAsignaturaopcionc;
    @Basic(optional = false)
    @Column(name = "id_tipoexamen")
    private int idTipoexamen;

    public FechaExamenopcioncPK() {
    }

    public FechaExamenopcioncPK(long idAsignaturaopcionc, int idTipoexamen) {
        this.idAsignaturaopcionc = idAsignaturaopcionc;
        this.idTipoexamen = idTipoexamen;
    }

    public long getIdAsignaturaopcionc() {
        return idAsignaturaopcionc;
    }

    public void setIdAsignaturaopcionc(long idAsignaturaopcionc) {
        this.idAsignaturaopcionc = idAsignaturaopcionc;
    }

    public int getIdTipoexamen() {
        return idTipoexamen;
    }

    public void setIdTipoexamen(int idTipoexamen) {
        this.idTipoexamen = idTipoexamen;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idAsignaturaopcionc;
        hash += (int) idTipoexamen;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FechaExamenopcioncPK)) {
            return false;
        }
        FechaExamenopcioncPK other = (FechaExamenopcioncPK) object;
        if (this.idAsignaturaopcionc != other.idAsignaturaopcionc) {
            return false;
        }
        if (this.idTipoexamen != other.idTipoexamen) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.FechaExamenopcioncPK[ idAsignaturaopcionc=" + idAsignaturaopcionc + ", idTipoexamen=" + idTipoexamen + " ]";
    }
    
}
