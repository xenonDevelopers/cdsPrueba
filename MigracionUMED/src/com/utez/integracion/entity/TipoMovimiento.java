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
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "tipo_movimiento")
@NamedQueries({
    @NamedQuery(name = "TipoMovimiento.findAll", query = "SELECT t FROM TipoMovimiento t"),
    @NamedQuery(name = "TipoMovimiento.findByIdTipomovimiento", query = "SELECT t FROM TipoMovimiento t WHERE t.idTipomovimiento = :idTipomovimiento"),
    @NamedQuery(name = "TipoMovimiento.findByDescripcionTipomovimiento", query = "SELECT t FROM TipoMovimiento t WHERE t.descripcionTipomovimiento = :descripcionTipomovimiento")})
public class TipoMovimiento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipomovimiento")
    private Long idTipomovimiento;
    @Column(name = "descripcion_tipomovimiento")
    private String descripcionTipomovimiento;
    @ManyToMany(mappedBy = "tipoMovimientoList")
    private List<RecursoHumano> recursoHumanoList;
    @ManyToMany(mappedBy = "tipoMovimientoList")
    private List<TipoAmbitomovimiento> tipoAmbitomovimientoList;
    @OneToMany(mappedBy = "idMovimiento")
    private List<FolioCambioscalendario> folioCambioscalendarioList;
    @OneToMany(mappedBy = "idTipomovimiento")
    private List<BitacoraMovimiento> bitacoraMovimientoList;

    public TipoMovimiento() {
    }

    public TipoMovimiento(Long idTipomovimiento) {
        this.idTipomovimiento = idTipomovimiento;
    }

    public Long getIdTipomovimiento() {
        return idTipomovimiento;
    }

    public void setIdTipomovimiento(Long idTipomovimiento) {
        this.idTipomovimiento = idTipomovimiento;
    }

    public String getDescripcionTipomovimiento() {
        return descripcionTipomovimiento;
    }

    public void setDescripcionTipomovimiento(String descripcionTipomovimiento) {
        this.descripcionTipomovimiento = descripcionTipomovimiento;
    }

    public List<RecursoHumano> getRecursoHumanoList() {
        return recursoHumanoList;
    }

    public void setRecursoHumanoList(List<RecursoHumano> recursoHumanoList) {
        this.recursoHumanoList = recursoHumanoList;
    }

    public List<TipoAmbitomovimiento> getTipoAmbitomovimientoList() {
        return tipoAmbitomovimientoList;
    }

    public void setTipoAmbitomovimientoList(List<TipoAmbitomovimiento> tipoAmbitomovimientoList) {
        this.tipoAmbitomovimientoList = tipoAmbitomovimientoList;
    }

    public List<FolioCambioscalendario> getFolioCambioscalendarioList() {
        return folioCambioscalendarioList;
    }

    public void setFolioCambioscalendarioList(List<FolioCambioscalendario> folioCambioscalendarioList) {
        this.folioCambioscalendarioList = folioCambioscalendarioList;
    }

    public List<BitacoraMovimiento> getBitacoraMovimientoList() {
        return bitacoraMovimientoList;
    }

    public void setBitacoraMovimientoList(List<BitacoraMovimiento> bitacoraMovimientoList) {
        this.bitacoraMovimientoList = bitacoraMovimientoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipomovimiento != null ? idTipomovimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoMovimiento)) {
            return false;
        }
        TipoMovimiento other = (TipoMovimiento) object;
        if ((this.idTipomovimiento == null && other.idTipomovimiento != null) || (this.idTipomovimiento != null && !this.idTipomovimiento.equals(other.idTipomovimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.TipoMovimiento[ idTipomovimiento=" + idTipomovimiento + " ]";
    }
    
}
