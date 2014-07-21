/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "tipo_pago")
@NamedQueries({
    @NamedQuery(name = "TipoPago.findAll", query = "SELECT t FROM TipoPago t"),
    @NamedQuery(name = "TipoPago.findByIdTipopago", query = "SELECT t FROM TipoPago t WHERE t.idTipopago = :idTipopago"),
    @NamedQuery(name = "TipoPago.findByDescripcionTipopago", query = "SELECT t FROM TipoPago t WHERE t.descripcionTipopago = :descripcionTipopago")})
public class TipoPago implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipopago")
    private Long idTipopago;
    @Column(name = "descripcion_tipopago")
    private String descripcionTipopago;
    @OneToMany(mappedBy = "idTipopago")
    private List<PagoPeriodo> pagoPeriodoList;

    public TipoPago() {
    }

    public TipoPago(Long idTipopago) {
        this.idTipopago = idTipopago;
    }

    public Long getIdTipopago() {
        return idTipopago;
    }

    public void setIdTipopago(Long idTipopago) {
        this.idTipopago = idTipopago;
    }

    public String getDescripcionTipopago() {
        return descripcionTipopago;
    }

    public void setDescripcionTipopago(String descripcionTipopago) {
        this.descripcionTipopago = descripcionTipopago;
    }

    public List<PagoPeriodo> getPagoPeriodoList() {
        return pagoPeriodoList;
    }

    public void setPagoPeriodoList(List<PagoPeriodo> pagoPeriodoList) {
        this.pagoPeriodoList = pagoPeriodoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipopago != null ? idTipopago.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoPago)) {
            return false;
        }
        TipoPago other = (TipoPago) object;
        if ((this.idTipopago == null && other.idTipopago != null) || (this.idTipopago != null && !this.idTipopago.equals(other.idTipopago))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.TipoPago[ idTipopago=" + idTipopago + " ]";
    }
    
}
