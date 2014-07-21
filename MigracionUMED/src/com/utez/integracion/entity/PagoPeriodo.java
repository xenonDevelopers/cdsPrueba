/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import com.utez.secretariaAcademica.entity.Periodo;
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
@Table(name = "pago_periodo")
@NamedQueries({
    @NamedQuery(name = "PagoPeriodo.findAll", query = "SELECT p FROM PagoPeriodo p"),
    @NamedQuery(name = "PagoPeriodo.findByIdPagoperiodo", query = "SELECT p FROM PagoPeriodo p WHERE p.idPagoperiodo = :idPagoperiodo"),
    @NamedQuery(name = "PagoPeriodo.findByImporte", query = "SELECT p FROM PagoPeriodo p WHERE p.importe = :importe")})
public class PagoPeriodo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_pagoperiodo")
    private Long idPagoperiodo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "importe")
    private Double importe;
    @JoinColumn(name = "id_tipopago", referencedColumnName = "id_tipopago")
    @ManyToOne
    private TipoPago idTipopago;
    @JoinColumn(name = "id_periodo", referencedColumnName = "id_periodo")
    @ManyToOne
    private Periodo idPeriodo;

    public PagoPeriodo() {
    }

    public PagoPeriodo(Long idPagoperiodo) {
        this.idPagoperiodo = idPagoperiodo;
    }

    public Long getIdPagoperiodo() {
        return idPagoperiodo;
    }

    public void setIdPagoperiodo(Long idPagoperiodo) {
        this.idPagoperiodo = idPagoperiodo;
    }

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }

    public TipoPago getIdTipopago() {
        return idTipopago;
    }

    public void setIdTipopago(TipoPago idTipopago) {
        this.idTipopago = idTipopago;
    }

    public Periodo getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(Periodo idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPagoperiodo != null ? idPagoperiodo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PagoPeriodo)) {
            return false;
        }
        PagoPeriodo other = (PagoPeriodo) object;
        if ((this.idPagoperiodo == null && other.idPagoperiodo != null) || (this.idPagoperiodo != null && !this.idPagoperiodo.equals(other.idPagoperiodo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.PagoPeriodo[ idPagoperiodo=" + idPagoperiodo + " ]";
    }
    
}
