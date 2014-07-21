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
public class FolioCambioscalendarioPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id_cambiocalendario")
    private int idCambiocalendario;
    @Basic(optional = false)
    @Column(name = "axo")
    private int axo;

    public FolioCambioscalendarioPK() {
    }

    public FolioCambioscalendarioPK(int idCambiocalendario, int axo) {
        this.idCambiocalendario = idCambiocalendario;
        this.axo = axo;
    }

    public int getIdCambiocalendario() {
        return idCambiocalendario;
    }

    public void setIdCambiocalendario(int idCambiocalendario) {
        this.idCambiocalendario = idCambiocalendario;
    }

    public int getAxo() {
        return axo;
    }

    public void setAxo(int axo) {
        this.axo = axo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idCambiocalendario;
        hash += (int) axo;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FolioCambioscalendarioPK)) {
            return false;
        }
        FolioCambioscalendarioPK other = (FolioCambioscalendarioPK) object;
        if (this.idCambiocalendario != other.idCambiocalendario) {
            return false;
        }
        if (this.axo != other.axo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.FolioCambioscalendarioPK[ idCambiocalendario=" + idCambiocalendario + ", axo=" + axo + " ]";
    }
    
}
