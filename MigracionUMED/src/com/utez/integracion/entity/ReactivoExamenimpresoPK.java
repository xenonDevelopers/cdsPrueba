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
public class ReactivoExamenimpresoPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id_examenimpreso")
    private long idExamenimpreso;
    @Basic(optional = false)
    @Column(name = "id_reactivo")
    private long idReactivo;
    @Basic(optional = false)
    @Column(name = "numero_reactivo")
    private int numeroReactivo;

    public ReactivoExamenimpresoPK() {
    }

    public ReactivoExamenimpresoPK(long idExamenimpreso, long idReactivo, int numeroReactivo) {
        this.idExamenimpreso = idExamenimpreso;
        this.idReactivo = idReactivo;
        this.numeroReactivo = numeroReactivo;
    }

    public long getIdExamenimpreso() {
        return idExamenimpreso;
    }

    public void setIdExamenimpreso(long idExamenimpreso) {
        this.idExamenimpreso = idExamenimpreso;
    }

    public long getIdReactivo() {
        return idReactivo;
    }

    public void setIdReactivo(long idReactivo) {
        this.idReactivo = idReactivo;
    }

    public int getNumeroReactivo() {
        return numeroReactivo;
    }

    public void setNumeroReactivo(int numeroReactivo) {
        this.numeroReactivo = numeroReactivo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idExamenimpreso;
        hash += (int) idReactivo;
        hash += (int) numeroReactivo;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReactivoExamenimpresoPK)) {
            return false;
        }
        ReactivoExamenimpresoPK other = (ReactivoExamenimpresoPK) object;
        if (this.idExamenimpreso != other.idExamenimpreso) {
            return false;
        }
        if (this.idReactivo != other.idReactivo) {
            return false;
        }
        if (this.numeroReactivo != other.numeroReactivo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.ReactivoExamenimpresoPK[ idExamenimpreso=" + idExamenimpreso + ", idReactivo=" + idReactivo + ", numeroReactivo=" + numeroReactivo + " ]";
    }
    
}
