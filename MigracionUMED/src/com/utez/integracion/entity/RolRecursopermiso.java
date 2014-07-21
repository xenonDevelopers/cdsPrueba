/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "rol_recursopermiso")
@NamedQueries({
    @NamedQuery(name = "RolRecursopermiso.findAll", query = "SELECT r FROM RolRecursopermiso r"),
    @NamedQuery(name = "RolRecursopermiso.findByIdRol", query = "SELECT r FROM RolRecursopermiso r WHERE r.rolRecursopermisoPK.idRol = :idRol"),
    @NamedQuery(name = "RolRecursopermiso.findByIdRecurso", query = "SELECT r FROM RolRecursopermiso r WHERE r.rolRecursopermisoPK.idRecurso = :idRecurso"),
    @NamedQuery(name = "RolRecursopermiso.findByIdPermiso", query = "SELECT r FROM RolRecursopermiso r WHERE r.rolRecursopermisoPK.idPermiso = :idPermiso")})
public class RolRecursopermiso implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RolRecursopermisoPK rolRecursopermisoPK;
    @JoinColumn(name = "id_rol", referencedColumnName = "id_rol", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Rol rol;
    @JoinColumn(name = "id_recurso", referencedColumnName = "id_recurso", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Recurso recurso;
    @JoinColumn(name = "id_permiso", referencedColumnName = "id_permiso", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Permiso permiso;

    public RolRecursopermiso() {
    }

    public RolRecursopermiso(RolRecursopermisoPK rolRecursopermisoPK) {
        this.rolRecursopermisoPK = rolRecursopermisoPK;
    }

    public RolRecursopermiso(long idRol, long idRecurso, String idPermiso) {
        this.rolRecursopermisoPK = new RolRecursopermisoPK(idRol, idRecurso, idPermiso);
    }

    public RolRecursopermisoPK getRolRecursopermisoPK() {
        return rolRecursopermisoPK;
    }

    public void setRolRecursopermisoPK(RolRecursopermisoPK rolRecursopermisoPK) {
        this.rolRecursopermisoPK = rolRecursopermisoPK;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Recurso getRecurso() {
        return recurso;
    }

    public void setRecurso(Recurso recurso) {
        this.recurso = recurso;
    }

    public Permiso getPermiso() {
        return permiso;
    }

    public void setPermiso(Permiso permiso) {
        this.permiso = permiso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rolRecursopermisoPK != null ? rolRecursopermisoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolRecursopermiso)) {
            return false;
        }
        RolRecursopermiso other = (RolRecursopermiso) object;
        if ((this.rolRecursopermisoPK == null && other.rolRecursopermisoPK != null) || (this.rolRecursopermisoPK != null && !this.rolRecursopermisoPK.equals(other.rolRecursopermisoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.RolRecursopermiso[ rolRecursopermisoPK=" + rolRecursopermisoPK + " ]";
    }
    
}
