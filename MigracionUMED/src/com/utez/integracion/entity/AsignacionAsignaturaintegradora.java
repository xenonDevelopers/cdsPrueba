/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import com.utez.secretariaAcademica.entity.Asignatura;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "asignacion_asignaturaintegradora")
@NamedQueries({
    @NamedQuery(name = "AsignacionAsignaturaintegradora.findAll", query = "SELECT a FROM AsignacionAsignaturaintegradora a"),
    @NamedQuery(name = "AsignacionAsignaturaintegradora.findByIdAsignacionasignaturaintegradora", query = "SELECT a FROM AsignacionAsignaturaintegradora a WHERE a.idAsignacionasignaturaintegradora = :idAsignacionasignaturaintegradora")})
public class AsignacionAsignaturaintegradora implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_asignacionasignaturaintegradora")
    private Long idAsignacionasignaturaintegradora;
    @JoinColumn(name = "id_asignatura", referencedColumnName = "id_asignatura")
    @ManyToOne
    private Asignatura idAsignatura;
    @JoinColumn(name = "id_actividadintegradora", referencedColumnName = "id_actividadintegradora")
    @ManyToOne
    private ActividadIntegradora idActividadintegradora;

    public AsignacionAsignaturaintegradora() {
    }

    public AsignacionAsignaturaintegradora(Long idAsignacionasignaturaintegradora) {
        this.idAsignacionasignaturaintegradora = idAsignacionasignaturaintegradora;
    }

    public Long getIdAsignacionasignaturaintegradora() {
        return idAsignacionasignaturaintegradora;
    }

    public void setIdAsignacionasignaturaintegradora(Long idAsignacionasignaturaintegradora) {
        this.idAsignacionasignaturaintegradora = idAsignacionasignaturaintegradora;
    }

    public Asignatura getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(Asignatura idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    public ActividadIntegradora getIdActividadintegradora() {
        return idActividadintegradora;
    }

    public void setIdActividadintegradora(ActividadIntegradora idActividadintegradora) {
        this.idActividadintegradora = idActividadintegradora;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAsignacionasignaturaintegradora != null ? idAsignacionasignaturaintegradora.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsignacionAsignaturaintegradora)) {
            return false;
        }
        AsignacionAsignaturaintegradora other = (AsignacionAsignaturaintegradora) object;
        if ((this.idAsignacionasignaturaintegradora == null && other.idAsignacionasignaturaintegradora != null) || (this.idAsignacionasignaturaintegradora != null && !this.idAsignacionasignaturaintegradora.equals(other.idAsignacionasignaturaintegradora))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.AsignacionAsignaturaintegradora[ idAsignacionasignaturaintegradora=" + idAsignacionasignaturaintegradora + " ]";
    }
    
}
