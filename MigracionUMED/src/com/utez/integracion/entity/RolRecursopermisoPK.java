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
public class RolRecursopermisoPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id_rol")
    private long idRol;
    @Basic(optional = false)
    @Column(name = "id_recurso")
    private long idRecurso;
    @Basic(optional = false)
    @Column(name = "id_permiso")
    private String idPermiso;

    public RolRecursopermisoPK() {
    }

    public RolRecursopermisoPK(long idRol, long idRecurso, String idPermiso) {
        this.idRol = idRol;
        this.idRecurso = idRecurso;
        this.idPermiso = idPermiso;
    }

    public long getIdRol() {
        return idRol;
    }

    public void setIdRol(long idRol) {
        this.idRol = idRol;
    }

    public long getIdRecurso() {
        return idRecurso;
    }

    public void setIdRecurso(long idRecurso) {
        this.idRecurso = idRecurso;
    }

    public String getIdPermiso() {
        return idPermiso;
    }

    public void setIdPermiso(String idPermiso) {
        this.idPermiso = idPermiso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idRol;
        hash += (int) idRecurso;
        hash += (idPermiso != null ? idPermiso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolRecursopermisoPK)) {
            return false;
        }
        RolRecursopermisoPK other = (RolRecursopermisoPK) object;
        if (this.idRol != other.idRol) {
            return false;
        }
        if (this.idRecurso != other.idRecurso) {
            return false;
        }
        if ((this.idPermiso == null && other.idPermiso != null) || (this.idPermiso != null && !this.idPermiso.equals(other.idPermiso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.RolRecursopermisoPK[ idRol=" + idRol + ", idRecurso=" + idRecurso + ", idPermiso=" + idPermiso + " ]";
    }
    
}
