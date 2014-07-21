/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "seguro_social")
@NamedQueries({
    @NamedQuery(name = "SeguroSocial.findAll", query = "SELECT s FROM SeguroSocial s"),
    @NamedQuery(name = "SeguroSocial.findByCurp", query = "SELECT s FROM SeguroSocial s WHERE s.curp = :curp"),
    @NamedQuery(name = "SeguroSocial.findByNumeroSeguro", query = "SELECT s FROM SeguroSocial s WHERE s.numeroSeguro = :numeroSeguro"),
    @NamedQuery(name = "SeguroSocial.findByFechaRegistro", query = "SELECT s FROM SeguroSocial s WHERE s.fechaRegistro = :fechaRegistro")})
public class SeguroSocial implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "curp")
    private String curp;
    @Column(name = "numero_seguro")
    private String numeroSeguro;
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;
    @JoinColumn(name = "institucion_seguro", referencedColumnName = "id_institucionseguro")
    @ManyToOne
    private TipoInstitucionseguro institucionSeguro;
    @JoinColumn(name = "curp", referencedColumnName = "curp", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Persona persona;

    public SeguroSocial() {
    }

    public SeguroSocial(String curp) {
        this.curp = curp;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getNumeroSeguro() {
        return numeroSeguro;
    }

    public void setNumeroSeguro(String numeroSeguro) {
        this.numeroSeguro = numeroSeguro;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public TipoInstitucionseguro getInstitucionSeguro() {
        return institucionSeguro;
    }

    public void setInstitucionSeguro(TipoInstitucionseguro institucionSeguro) {
        this.institucionSeguro = institucionSeguro;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (curp != null ? curp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SeguroSocial)) {
            return false;
        }
        SeguroSocial other = (SeguroSocial) object;
        if ((this.curp == null && other.curp != null) || (this.curp != null && !this.curp.equals(other.curp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.SeguroSocial[ curp=" + curp + " ]";
    }
    
}
