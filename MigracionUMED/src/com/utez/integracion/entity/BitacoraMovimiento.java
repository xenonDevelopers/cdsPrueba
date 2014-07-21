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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "bitacora_movimiento")
@NamedQueries({
    @NamedQuery(name = "BitacoraMovimiento.findAll", query = "SELECT b FROM BitacoraMovimiento b"),
    @NamedQuery(name = "BitacoraMovimiento.findByIdBitacoramovimiento", query = "SELECT b FROM BitacoraMovimiento b WHERE b.idBitacoramovimiento = :idBitacoramovimiento"),
    @NamedQuery(name = "BitacoraMovimiento.findByFechahoraMovimiento", query = "SELECT b FROM BitacoraMovimiento b WHERE b.fechahoraMovimiento = :fechahoraMovimiento")})
public class BitacoraMovimiento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_bitacoramovimiento")
    private Long idBitacoramovimiento;
    @Column(name = "fechahora_movimiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechahoraMovimiento;
    @JoinColumn(name = "id_usuario", referencedColumnName = "curp")
    @ManyToOne
    private Usuario idUsuario;
    @JoinColumn(name = "id_tipomovimiento", referencedColumnName = "id_tipomovimiento")
    @ManyToOne
    private TipoMovimiento idTipomovimiento;
    @JoinColumn(name = "id_rol", referencedColumnName = "id_rol")
    @ManyToOne
    private Rol idRol;

    public BitacoraMovimiento() {
    }

    public BitacoraMovimiento(Long idBitacoramovimiento) {
        this.idBitacoramovimiento = idBitacoramovimiento;
    }

    public Long getIdBitacoramovimiento() {
        return idBitacoramovimiento;
    }

    public void setIdBitacoramovimiento(Long idBitacoramovimiento) {
        this.idBitacoramovimiento = idBitacoramovimiento;
    }

    public Date getFechahoraMovimiento() {
        return fechahoraMovimiento;
    }

    public void setFechahoraMovimiento(Date fechahoraMovimiento) {
        this.fechahoraMovimiento = fechahoraMovimiento;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public TipoMovimiento getIdTipomovimiento() {
        return idTipomovimiento;
    }

    public void setIdTipomovimiento(TipoMovimiento idTipomovimiento) {
        this.idTipomovimiento = idTipomovimiento;
    }

    public Rol getIdRol() {
        return idRol;
    }

    public void setIdRol(Rol idRol) {
        this.idRol = idRol;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBitacoramovimiento != null ? idBitacoramovimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BitacoraMovimiento)) {
            return false;
        }
        BitacoraMovimiento other = (BitacoraMovimiento) object;
        if ((this.idBitacoramovimiento == null && other.idBitacoramovimiento != null) || (this.idBitacoramovimiento != null && !this.idBitacoramovimiento.equals(other.idBitacoramovimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.BitacoraMovimiento[ idBitacoramovimiento=" + idBitacoramovimiento + " ]";
    }
    
}
