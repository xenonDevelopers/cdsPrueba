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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "area_cargo")
@NamedQueries({
    @NamedQuery(name = "AreaCargo.findAll", query = "SELECT a FROM AreaCargo a"),
    @NamedQuery(name = "AreaCargo.findByIdCargoarea", query = "SELECT a FROM AreaCargo a WHERE a.idCargoarea = :idCargoarea")})
public class AreaCargo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_cargoarea")
    private Long idCargoarea;
    @JoinTable(name = "cargo_tabulador", joinColumns = {
        @JoinColumn(name = "id_cargoarea", referencedColumnName = "id_cargoarea")}, inverseJoinColumns = {
        @JoinColumn(name = "id_tabulador", referencedColumnName = "id_tabulador")})
    @ManyToMany
    private List<Tabulador> tabuladorList;
    @JoinColumn(name = "id_cargo", referencedColumnName = "id_cargo")
    @ManyToOne
    private Cargo idCargo;
    @JoinColumn(name = "id_area", referencedColumnName = "id_area")
    @ManyToOne
    private Area idArea;
    @OneToMany(mappedBy = "idCargoarea")
    private List<HistorialCargo> historialCargoList;

    public AreaCargo() {
    }

    public AreaCargo(Long idCargoarea) {
        this.idCargoarea = idCargoarea;
    }

    public Long getIdCargoarea() {
        return idCargoarea;
    }

    public void setIdCargoarea(Long idCargoarea) {
        this.idCargoarea = idCargoarea;
    }

    public List<Tabulador> getTabuladorList() {
        return tabuladorList;
    }

    public void setTabuladorList(List<Tabulador> tabuladorList) {
        this.tabuladorList = tabuladorList;
    }

    public Cargo getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(Cargo idCargo) {
        this.idCargo = idCargo;
    }

    public Area getIdArea() {
        return idArea;
    }

    public void setIdArea(Area idArea) {
        this.idArea = idArea;
    }

    public List<HistorialCargo> getHistorialCargoList() {
        return historialCargoList;
    }

    public void setHistorialCargoList(List<HistorialCargo> historialCargoList) {
        this.historialCargoList = historialCargoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCargoarea != null ? idCargoarea.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AreaCargo)) {
            return false;
        }
        AreaCargo other = (AreaCargo) object;
        if ((this.idCargoarea == null && other.idCargoarea != null) || (this.idCargoarea != null && !this.idCargoarea.equals(other.idCargoarea))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.AreaCargo[ idCargoarea=" + idCargoarea + " ]";
    }
    
}
