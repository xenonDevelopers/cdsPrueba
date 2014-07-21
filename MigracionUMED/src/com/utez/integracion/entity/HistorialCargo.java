/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import com.utez.secretariaAcademica.entity.Administrativo;
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
@Table(name = "historial_cargo")
@NamedQueries({
    @NamedQuery(name = "HistorialCargo.findAll", query = "SELECT h FROM HistorialCargo h"),
    @NamedQuery(name = "HistorialCargo.findByIdHistorialcargo", query = "SELECT h FROM HistorialCargo h WHERE h.idHistorialcargo = :idHistorialcargo"),
    @NamedQuery(name = "HistorialCargo.findByGestionInicio", query = "SELECT h FROM HistorialCargo h WHERE h.gestionInicio = :gestionInicio"),
    @NamedQuery(name = "HistorialCargo.findByGestionFin", query = "SELECT h FROM HistorialCargo h WHERE h.gestionFin = :gestionFin")})
public class HistorialCargo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_historialcargo")
    private Long idHistorialcargo;
    @Column(name = "gestion_inicio")
    @Temporal(TemporalType.DATE)
    private Date gestionInicio;
    @Column(name = "gestion_fin")
    @Temporal(TemporalType.DATE)
    private Date gestionFin;
    @JoinColumn(name = "id_cargoarea", referencedColumnName = "id_cargoarea")
    @ManyToOne
    private AreaCargo idCargoarea;
    @JoinColumn(name = "id_administrativo", referencedColumnName = "id_administrativo")
    @ManyToOne
    private Administrativo idAdministrativo;

    public HistorialCargo() {
    }

    public HistorialCargo(Long idHistorialcargo) {
        this.idHistorialcargo = idHistorialcargo;
    }

    public Long getIdHistorialcargo() {
        return idHistorialcargo;
    }

    public void setIdHistorialcargo(Long idHistorialcargo) {
        this.idHistorialcargo = idHistorialcargo;
    }

    public Date getGestionInicio() {
        return gestionInicio;
    }

    public void setGestionInicio(Date gestionInicio) {
        this.gestionInicio = gestionInicio;
    }

    public Date getGestionFin() {
        return gestionFin;
    }

    public void setGestionFin(Date gestionFin) {
        this.gestionFin = gestionFin;
    }

    public AreaCargo getIdCargoarea() {
        return idCargoarea;
    }

    public void setIdCargoarea(AreaCargo idCargoarea) {
        this.idCargoarea = idCargoarea;
    }

    public Administrativo getIdAdministrativo() {
        return idAdministrativo;
    }

    public void setIdAdministrativo(Administrativo idAdministrativo) {
        this.idAdministrativo = idAdministrativo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHistorialcargo != null ? idHistorialcargo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistorialCargo)) {
            return false;
        }
        HistorialCargo other = (HistorialCargo) object;
        if ((this.idHistorialcargo == null && other.idHistorialcargo != null) || (this.idHistorialcargo != null && !this.idHistorialcargo.equals(other.idHistorialcargo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.HistorialCargo[ idHistorialcargo=" + idHistorialcargo + " ]";
    }
    
}
