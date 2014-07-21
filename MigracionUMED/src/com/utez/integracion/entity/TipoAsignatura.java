/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import com.utez.secretariaAcademica.entity.Asignatura;
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
@Table(name = "tipo_asignatura")
@NamedQueries({
    @NamedQuery(name = "TipoAsignatura.findAll", query = "SELECT t FROM TipoAsignatura t"),
    @NamedQuery(name = "TipoAsignatura.findByIdTipoasignatura", query = "SELECT t FROM TipoAsignatura t WHERE t.idTipoasignatura = :idTipoasignatura"),
    @NamedQuery(name = "TipoAsignatura.findByDescripcionTipoasignatura", query = "SELECT t FROM TipoAsignatura t WHERE t.descripcionTipoasignatura = :descripcionTipoasignatura")})
public class TipoAsignatura implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipoasignatura")
    private Long idTipoasignatura;
    @Column(name = "descripcion_tipoasignatura")
    private String descripcionTipoasignatura;
    @OneToMany(mappedBy = "idTipoasignatura")
    private List<Asignatura> asignaturaList;

    public TipoAsignatura() {
    }

    public TipoAsignatura(Long idTipoasignatura) {
        this.idTipoasignatura = idTipoasignatura;
    }

    public Long getIdTipoasignatura() {
        return idTipoasignatura;
    }

    public void setIdTipoasignatura(Long idTipoasignatura) {
        this.idTipoasignatura = idTipoasignatura;
    }

    public String getDescripcionTipoasignatura() {
        return descripcionTipoasignatura;
    }

    public void setDescripcionTipoasignatura(String descripcionTipoasignatura) {
        this.descripcionTipoasignatura = descripcionTipoasignatura;
    }

    public List<Asignatura> getAsignaturaList() {
        return asignaturaList;
    }

    public void setAsignaturaList(List<Asignatura> asignaturaList) {
        this.asignaturaList = asignaturaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoasignatura != null ? idTipoasignatura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoAsignatura)) {
            return false;
        }
        TipoAsignatura other = (TipoAsignatura) object;
        if ((this.idTipoasignatura == null && other.idTipoasignatura != null) || (this.idTipoasignatura != null && !this.idTipoasignatura.equals(other.idTipoasignatura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.TipoAsignatura[ idTipoasignatura=" + idTipoasignatura + " ]";
    }
    
}
