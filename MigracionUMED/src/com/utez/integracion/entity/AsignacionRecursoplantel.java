/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import com.utez.secretariaAcademica.entity.Plantel;
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
@Table(name = "asignacion_recursoplantel")
@NamedQueries({
    @NamedQuery(name = "AsignacionRecursoplantel.findAll", query = "SELECT a FROM AsignacionRecursoplantel a"),
    @NamedQuery(name = "AsignacionRecursoplantel.findByIdAsignacionrecursoplantel", query = "SELECT a FROM AsignacionRecursoplantel a WHERE a.idAsignacionrecursoplantel = :idAsignacionrecursoplantel")})
public class AsignacionRecursoplantel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_asignacionrecursoplantel")
    private Long idAsignacionrecursoplantel;
    @JoinColumn(name = "id_recursohumano", referencedColumnName = "id_recursohumano")
    @ManyToOne
    private RecursoHumano idRecursohumano;
    @JoinColumn(name = "id_plantel", referencedColumnName = "id_plantel")
    @ManyToOne
    private Plantel idPlantel;

    public AsignacionRecursoplantel() {
    }

    public AsignacionRecursoplantel(Long idAsignacionrecursoplantel) {
        this.idAsignacionrecursoplantel = idAsignacionrecursoplantel;
    }

    public Long getIdAsignacionrecursoplantel() {
        return idAsignacionrecursoplantel;
    }

    public void setIdAsignacionrecursoplantel(Long idAsignacionrecursoplantel) {
        this.idAsignacionrecursoplantel = idAsignacionrecursoplantel;
    }

    public RecursoHumano getIdRecursohumano() {
        return idRecursohumano;
    }

    public void setIdRecursohumano(RecursoHumano idRecursohumano) {
        this.idRecursohumano = idRecursohumano;
    }

    public Plantel getIdPlantel() {
        return idPlantel;
    }

    public void setIdPlantel(Plantel idPlantel) {
        this.idPlantel = idPlantel;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAsignacionrecursoplantel != null ? idAsignacionrecursoplantel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsignacionRecursoplantel)) {
            return false;
        }
        AsignacionRecursoplantel other = (AsignacionRecursoplantel) object;
        if ((this.idAsignacionrecursoplantel == null && other.idAsignacionrecursoplantel != null) || (this.idAsignacionrecursoplantel != null && !this.idAsignacionrecursoplantel.equals(other.idAsignacionrecursoplantel))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.AsignacionRecursoplantel[ idAsignacionrecursoplantel=" + idAsignacionrecursoplantel + " ]";
    }
    
}
