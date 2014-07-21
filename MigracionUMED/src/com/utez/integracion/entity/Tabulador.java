/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
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
@Table(name = "tabulador")
@NamedQueries({
    @NamedQuery(name = "Tabulador.findAll", query = "SELECT t FROM Tabulador t"),
    @NamedQuery(name = "Tabulador.findByIdTabulador", query = "SELECT t FROM Tabulador t WHERE t.idTabulador = :idTabulador"),
    @NamedQuery(name = "Tabulador.findByImporte", query = "SELECT t FROM Tabulador t WHERE t.importe = :importe"),
    @NamedQuery(name = "Tabulador.findByVigenciainicio", query = "SELECT t FROM Tabulador t WHERE t.vigenciainicio = :vigenciainicio"),
    @NamedQuery(name = "Tabulador.findByVigenciafin", query = "SELECT t FROM Tabulador t WHERE t.vigenciafin = :vigenciafin")})
public class Tabulador implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tabulador")
    private Long idTabulador;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "importe")
    private Double importe;
    @Column(name = "vigenciainicio")
    @Temporal(TemporalType.DATE)
    private Date vigenciainicio;
    @Column(name = "vigenciafin")
    @Temporal(TemporalType.DATE)
    private Date vigenciafin;
    @ManyToMany(mappedBy = "tabuladorList")
    private List<AreaCargo> areaCargoList;
    @JoinColumn(name = "id_tabuladorconcepto", referencedColumnName = "id_tabuladorconcepto")
    @ManyToOne
    private TabuladorConcepto idTabuladorconcepto;

    public Tabulador() {
    }

    public Tabulador(Long idTabulador) {
        this.idTabulador = idTabulador;
    }

    public Long getIdTabulador() {
        return idTabulador;
    }

    public void setIdTabulador(Long idTabulador) {
        this.idTabulador = idTabulador;
    }

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }

    public Date getVigenciainicio() {
        return vigenciainicio;
    }

    public void setVigenciainicio(Date vigenciainicio) {
        this.vigenciainicio = vigenciainicio;
    }

    public Date getVigenciafin() {
        return vigenciafin;
    }

    public void setVigenciafin(Date vigenciafin) {
        this.vigenciafin = vigenciafin;
    }

    public List<AreaCargo> getAreaCargoList() {
        return areaCargoList;
    }

    public void setAreaCargoList(List<AreaCargo> areaCargoList) {
        this.areaCargoList = areaCargoList;
    }

    public TabuladorConcepto getIdTabuladorconcepto() {
        return idTabuladorconcepto;
    }

    public void setIdTabuladorconcepto(TabuladorConcepto idTabuladorconcepto) {
        this.idTabuladorconcepto = idTabuladorconcepto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTabulador != null ? idTabulador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tabulador)) {
            return false;
        }
        Tabulador other = (Tabulador) object;
        if ((this.idTabulador == null && other.idTabulador != null) || (this.idTabulador != null && !this.idTabulador.equals(other.idTabulador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.Tabulador[ idTabulador=" + idTabulador + " ]";
    }
    
}
