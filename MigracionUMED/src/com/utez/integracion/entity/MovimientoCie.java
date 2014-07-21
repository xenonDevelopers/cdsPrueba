/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import com.utez.secretariaAcademica.entity.Alumno;
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
@Table(name = "movimiento_cie")
@NamedQueries({
    @NamedQuery(name = "MovimientoCie.findAll", query = "SELECT m FROM MovimientoCie m"),
    @NamedQuery(name = "MovimientoCie.findByIdMovimientocie", query = "SELECT m FROM MovimientoCie m WHERE m.idMovimientocie = :idMovimientocie"),
    @NamedQuery(name = "MovimientoCie.findByFechaDeposito", query = "SELECT m FROM MovimientoCie m WHERE m.fechaDeposito = :fechaDeposito"),
    @NamedQuery(name = "MovimientoCie.findByGuiaCie", query = "SELECT m FROM MovimientoCie m WHERE m.guiaCie = :guiaCie"),
    @NamedQuery(name = "MovimientoCie.findByMontoDepositado", query = "SELECT m FROM MovimientoCie m WHERE m.montoDepositado = :montoDepositado")})
public class MovimientoCie implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_movimientocie")
    private Long idMovimientocie;
    @Column(name = "fecha_deposito")
    @Temporal(TemporalType.DATE)
    private Date fechaDeposito;
    @Column(name = "guia_cie")
    private String guiaCie;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "monto_depositado")
    private Double montoDepositado;
    @JoinColumn(name = "matricula", referencedColumnName = "matricula")
    @ManyToOne
    private Alumno matricula;

    public MovimientoCie() {
    }

    public MovimientoCie(Long idMovimientocie) {
        this.idMovimientocie = idMovimientocie;
    }

    public Long getIdMovimientocie() {
        return idMovimientocie;
    }

    public void setIdMovimientocie(Long idMovimientocie) {
        this.idMovimientocie = idMovimientocie;
    }

    public Date getFechaDeposito() {
        return fechaDeposito;
    }

    public void setFechaDeposito(Date fechaDeposito) {
        this.fechaDeposito = fechaDeposito;
    }

    public String getGuiaCie() {
        return guiaCie;
    }

    public void setGuiaCie(String guiaCie) {
        this.guiaCie = guiaCie;
    }

    public Double getMontoDepositado() {
        return montoDepositado;
    }

    public void setMontoDepositado(Double montoDepositado) {
        this.montoDepositado = montoDepositado;
    }

    public Alumno getMatricula() {
        return matricula;
    }

    public void setMatricula(Alumno matricula) {
        this.matricula = matricula;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMovimientocie != null ? idMovimientocie.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MovimientoCie)) {
            return false;
        }
        MovimientoCie other = (MovimientoCie) object;
        if ((this.idMovimientocie == null && other.idMovimientocie != null) || (this.idMovimientocie != null && !this.idMovimientocie.equals(other.idMovimientocie))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.MovimientoCie[ idMovimientocie=" + idMovimientocie + " ]";
    }
    
}
