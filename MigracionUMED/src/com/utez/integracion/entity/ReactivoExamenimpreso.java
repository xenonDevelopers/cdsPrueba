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
@Table(name = "reactivo_examenimpreso")
@NamedQueries({
    @NamedQuery(name = "ReactivoExamenimpreso.findAll", query = "SELECT r FROM ReactivoExamenimpreso r"),
    @NamedQuery(name = "ReactivoExamenimpreso.findByIdExamenimpreso", query = "SELECT r FROM ReactivoExamenimpreso r WHERE r.reactivoExamenimpresoPK.idExamenimpreso = :idExamenimpreso"),
    @NamedQuery(name = "ReactivoExamenimpreso.findByIdReactivo", query = "SELECT r FROM ReactivoExamenimpreso r WHERE r.reactivoExamenimpresoPK.idReactivo = :idReactivo"),
    @NamedQuery(name = "ReactivoExamenimpreso.findByNumeroReactivo", query = "SELECT r FROM ReactivoExamenimpreso r WHERE r.reactivoExamenimpresoPK.numeroReactivo = :numeroReactivo")})
public class ReactivoExamenimpreso implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ReactivoExamenimpresoPK reactivoExamenimpresoPK;
    @JoinColumn(name = "id_reactivo", referencedColumnName = "id_reactivo", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Reactivo reactivo;
    @JoinColumn(name = "id_examenimpreso", referencedColumnName = "id_examenimpreso", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ExamenImpreso examenImpreso;

    public ReactivoExamenimpreso() {
    }

    public ReactivoExamenimpreso(ReactivoExamenimpresoPK reactivoExamenimpresoPK) {
        this.reactivoExamenimpresoPK = reactivoExamenimpresoPK;
    }

    public ReactivoExamenimpreso(long idExamenimpreso, long idReactivo, int numeroReactivo) {
        this.reactivoExamenimpresoPK = new ReactivoExamenimpresoPK(idExamenimpreso, idReactivo, numeroReactivo);
    }

    public ReactivoExamenimpresoPK getReactivoExamenimpresoPK() {
        return reactivoExamenimpresoPK;
    }

    public void setReactivoExamenimpresoPK(ReactivoExamenimpresoPK reactivoExamenimpresoPK) {
        this.reactivoExamenimpresoPK = reactivoExamenimpresoPK;
    }

    public Reactivo getReactivo() {
        return reactivo;
    }

    public void setReactivo(Reactivo reactivo) {
        this.reactivo = reactivo;
    }

    public ExamenImpreso getExamenImpreso() {
        return examenImpreso;
    }

    public void setExamenImpreso(ExamenImpreso examenImpreso) {
        this.examenImpreso = examenImpreso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reactivoExamenimpresoPK != null ? reactivoExamenimpresoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReactivoExamenimpreso)) {
            return false;
        }
        ReactivoExamenimpreso other = (ReactivoExamenimpreso) object;
        if ((this.reactivoExamenimpresoPK == null && other.reactivoExamenimpresoPK != null) || (this.reactivoExamenimpresoPK != null && !this.reactivoExamenimpresoPK.equals(other.reactivoExamenimpresoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.ReactivoExamenimpreso[ reactivoExamenimpresoPK=" + reactivoExamenimpresoPK + " ]";
    }
    
}
