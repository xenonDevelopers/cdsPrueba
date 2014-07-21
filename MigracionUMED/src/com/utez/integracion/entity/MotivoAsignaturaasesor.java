/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "motivo_asignaturaasesor")
@NamedQueries({
    @NamedQuery(name = "MotivoAsignaturaasesor.findAll", query = "SELECT m FROM MotivoAsignaturaasesor m"),
    @NamedQuery(name = "MotivoAsignaturaasesor.findByIdAsignaturaasesor", query = "SELECT m FROM MotivoAsignaturaasesor m WHERE m.idAsignaturaasesor = :idAsignaturaasesor"),
    @NamedQuery(name = "MotivoAsignaturaasesor.findByMotivo", query = "SELECT m FROM MotivoAsignaturaasesor m WHERE m.motivo = :motivo")})
public class MotivoAsignaturaasesor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_asignaturaasesor")
    private Long idAsignaturaasesor;
    @Column(name = "motivo")
    private String motivo;
    @JoinColumn(name = "id_asignaturaasesor", referencedColumnName = "id_asignaturaasesor", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private AsignaturaAsesor asignaturaAsesor;

    public MotivoAsignaturaasesor() {
    }

    public MotivoAsignaturaasesor(Long idAsignaturaasesor) {
        this.idAsignaturaasesor = idAsignaturaasesor;
    }

    public Long getIdAsignaturaasesor() {
        return idAsignaturaasesor;
    }

    public void setIdAsignaturaasesor(Long idAsignaturaasesor) {
        this.idAsignaturaasesor = idAsignaturaasesor;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public AsignaturaAsesor getAsignaturaAsesor() {
        return asignaturaAsesor;
    }

    public void setAsignaturaAsesor(AsignaturaAsesor asignaturaAsesor) {
        this.asignaturaAsesor = asignaturaAsesor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAsignaturaasesor != null ? idAsignaturaasesor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MotivoAsignaturaasesor)) {
            return false;
        }
        MotivoAsignaturaasesor other = (MotivoAsignaturaasesor) object;
        if ((this.idAsignaturaasesor == null && other.idAsignaturaasesor != null) || (this.idAsignaturaasesor != null && !this.idAsignaturaasesor.equals(other.idAsignaturaasesor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.MotivoAsignaturaasesor[ idAsignaturaasesor=" + idAsignaturaasesor + " ]";
    }
    
}
