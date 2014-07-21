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
@Table(name = "tabulador_concepto")
@NamedQueries({
    @NamedQuery(name = "TabuladorConcepto.findAll", query = "SELECT t FROM TabuladorConcepto t"),
    @NamedQuery(name = "TabuladorConcepto.findByIdTabuladorconcepto", query = "SELECT t FROM TabuladorConcepto t WHERE t.idTabuladorconcepto = :idTabuladorconcepto"),
    @NamedQuery(name = "TabuladorConcepto.findByConcepto", query = "SELECT t FROM TabuladorConcepto t WHERE t.concepto = :concepto")})
public class TabuladorConcepto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tabuladorconcepto")
    private Long idTabuladorconcepto;
    @Column(name = "concepto")
    private String concepto;
    @OneToMany(mappedBy = "idTabuladorconcepto")
    private List<Tabulador> tabuladorList;

    public TabuladorConcepto() {
    }

    public TabuladorConcepto(Long idTabuladorconcepto) {
        this.idTabuladorconcepto = idTabuladorconcepto;
    }

    public Long getIdTabuladorconcepto() {
        return idTabuladorconcepto;
    }

    public void setIdTabuladorconcepto(Long idTabuladorconcepto) {
        this.idTabuladorconcepto = idTabuladorconcepto;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public List<Tabulador> getTabuladorList() {
        return tabuladorList;
    }

    public void setTabuladorList(List<Tabulador> tabuladorList) {
        this.tabuladorList = tabuladorList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTabuladorconcepto != null ? idTabuladorconcepto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TabuladorConcepto)) {
            return false;
        }
        TabuladorConcepto other = (TabuladorConcepto) object;
        if ((this.idTabuladorconcepto == null && other.idTabuladorconcepto != null) || (this.idTabuladorconcepto != null && !this.idTabuladorconcepto.equals(other.idTabuladorconcepto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.TabuladorConcepto[ idTabuladorconcepto=" + idTabuladorconcepto + " ]";
    }
    
}
