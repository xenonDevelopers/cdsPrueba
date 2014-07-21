/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.secretariaAcademica.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "tipopagos")
@NamedQueries({
    @NamedQuery(name = "Tipopagos.findAll", query = "SELECT t FROM Tipopagos t"),
    @NamedQuery(name = "Tipopagos.findByDescripcion", query = "SELECT t FROM Tipopagos t WHERE t.descripcion = :descripcion"),
    @NamedQuery(name = "Tipopagos.findByImporte", query = "SELECT t FROM Tipopagos t WHERE t.importe = :importe"),
    @NamedQuery(name = "Tipopagos.findByIdtipopagos", query = "SELECT t FROM Tipopagos t WHERE t.idtipopagos = :idtipopagos")})
public class Tipopagos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "descripcion")
    private String descripcion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "importe")
    private Float importe;
    @Id
    @Basic(optional = false)
    @Column(name = "idtipopagos")
    private Integer idtipopagos;
    @OneToMany(mappedBy = "idtipopagos")
    private List<Movimientocie> movimientocieList;

    public Tipopagos() {
    }

    public Tipopagos(Integer idtipopagos) {
        this.idtipopagos = idtipopagos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Float getImporte() {
        return importe;
    }

    public void setImporte(Float importe) {
        this.importe = importe;
    }

    public Integer getIdtipopagos() {
        return idtipopagos;
    }

    public void setIdtipopagos(Integer idtipopagos) {
        this.idtipopagos = idtipopagos;
    }

    public List<Movimientocie> getMovimientocieList() {
        return movimientocieList;
    }

    public void setMovimientocieList(List<Movimientocie> movimientocieList) {
        this.movimientocieList = movimientocieList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtipopagos != null ? idtipopagos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipopagos)) {
            return false;
        }
        Tipopagos other = (Tipopagos) object;
        if ((this.idtipopagos == null && other.idtipopagos != null) || (this.idtipopagos != null && !this.idtipopagos.equals(other.idtipopagos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.secretariaAcademica.entity.Tipopagos[ idtipopagos=" + idtipopagos + " ]";
    }
    
}
