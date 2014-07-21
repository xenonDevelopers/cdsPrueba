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
public class AdeudoalumnoEsquematipoexamenPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id_esquemaalumnoasignatura")
    private long idEsquemaalumnoasignatura;
    @Basic(optional = false)
    @Column(name = "id_tipoexamen")
    private long idTipoexamen;

    public AdeudoalumnoEsquematipoexamenPK() {
    }

    public AdeudoalumnoEsquematipoexamenPK(long idEsquemaalumnoasignatura, long idTipoexamen) {
        this.idEsquemaalumnoasignatura = idEsquemaalumnoasignatura;
        this.idTipoexamen = idTipoexamen;
    }

    public long getIdEsquemaalumnoasignatura() {
        return idEsquemaalumnoasignatura;
    }

    public void setIdEsquemaalumnoasignatura(long idEsquemaalumnoasignatura) {
        this.idEsquemaalumnoasignatura = idEsquemaalumnoasignatura;
    }

    public long getIdTipoexamen() {
        return idTipoexamen;
    }

    public void setIdTipoexamen(long idTipoexamen) {
        this.idTipoexamen = idTipoexamen;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idEsquemaalumnoasignatura;
        hash += (int) idTipoexamen;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdeudoalumnoEsquematipoexamenPK)) {
            return false;
        }
        AdeudoalumnoEsquematipoexamenPK other = (AdeudoalumnoEsquematipoexamenPK) object;
        if (this.idEsquemaalumnoasignatura != other.idEsquemaalumnoasignatura) {
            return false;
        }
        if (this.idTipoexamen != other.idTipoexamen) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.AdeudoalumnoEsquematipoexamenPK[ idEsquemaalumnoasignatura=" + idEsquemaalumnoasignatura + ", idTipoexamen=" + idTipoexamen + " ]";
    }
    
}
