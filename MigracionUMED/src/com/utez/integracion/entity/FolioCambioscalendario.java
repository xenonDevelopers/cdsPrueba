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
@Table(name = "folio_cambioscalendario")
@NamedQueries({
    @NamedQuery(name = "FolioCambioscalendario.findAll", query = "SELECT f FROM FolioCambioscalendario f"),
    @NamedQuery(name = "FolioCambioscalendario.findByIdCambiocalendario", query = "SELECT f FROM FolioCambioscalendario f WHERE f.folioCambioscalendarioPK.idCambiocalendario = :idCambiocalendario"),
    @NamedQuery(name = "FolioCambioscalendario.findByAxo", query = "SELECT f FROM FolioCambioscalendario f WHERE f.folioCambioscalendarioPK.axo = :axo")})
public class FolioCambioscalendario implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected FolioCambioscalendarioPK folioCambioscalendarioPK;
    @JoinColumn(name = "id_movimiento", referencedColumnName = "id_tipomovimiento")
    @ManyToOne
    private TipoMovimiento idMovimiento;

    public FolioCambioscalendario() {
    }

    public FolioCambioscalendario(FolioCambioscalendarioPK folioCambioscalendarioPK) {
        this.folioCambioscalendarioPK = folioCambioscalendarioPK;
    }

    public FolioCambioscalendario(int idCambiocalendario, int axo) {
        this.folioCambioscalendarioPK = new FolioCambioscalendarioPK(idCambiocalendario, axo);
    }

    public FolioCambioscalendarioPK getFolioCambioscalendarioPK() {
        return folioCambioscalendarioPK;
    }

    public void setFolioCambioscalendarioPK(FolioCambioscalendarioPK folioCambioscalendarioPK) {
        this.folioCambioscalendarioPK = folioCambioscalendarioPK;
    }

    public TipoMovimiento getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(TipoMovimiento idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (folioCambioscalendarioPK != null ? folioCambioscalendarioPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FolioCambioscalendario)) {
            return false;
        }
        FolioCambioscalendario other = (FolioCambioscalendario) object;
        if ((this.folioCambioscalendarioPK == null && other.folioCambioscalendarioPK != null) || (this.folioCambioscalendarioPK != null && !this.folioCambioscalendarioPK.equals(other.folioCambioscalendarioPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.FolioCambioscalendario[ folioCambioscalendarioPK=" + folioCambioscalendarioPK + " ]";
    }
    
}
