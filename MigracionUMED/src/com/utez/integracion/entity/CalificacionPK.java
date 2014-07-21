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
public class CalificacionPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id_tipoexamen")
    private long idTipoexamen;
    @Basic(optional = false)
    @Column(name = "id_esquemaalumnoasignatura")
    private long idEsquemaalumnoasignatura;

    public CalificacionPK() {
    }

    public CalificacionPK(long idTipoexamen, long idEsquemaalumnoasignatura) {
        this.idTipoexamen = idTipoexamen;
        this.idEsquemaalumnoasignatura = idEsquemaalumnoasignatura;
    }

    public long getIdTipoexamen() {
        return idTipoexamen;
    }

    public void setIdTipoexamen(long idTipoexamen) {
        this.idTipoexamen = idTipoexamen;
    }

    public long getIdEsquemaalumnoasignatura() {
        return idEsquemaalumnoasignatura;
    }

    public void setIdEsquemaalumnoasignatura(long idEsquemaalumnoasignatura) {
        this.idEsquemaalumnoasignatura = idEsquemaalumnoasignatura;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idTipoexamen;
        hash += (int) idEsquemaalumnoasignatura;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CalificacionPK)) {
            return false;
        }
        CalificacionPK other = (CalificacionPK) object;
        if (this.idTipoexamen != other.idTipoexamen) {
            return false;
        }
        if (this.idEsquemaalumnoasignatura != other.idEsquemaalumnoasignatura) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.CalificacionPK[ idTipoexamen=" + idTipoexamen + ", idEsquemaalumnoasignatura=" + idEsquemaalumnoasignatura + " ]";
    }
    
}
