/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import com.utez.secretariaAcademica.entity.Grupo;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "opcion_estudio")
@NamedQueries({
    @NamedQuery(name = "OpcionEstudio.findAll", query = "SELECT o FROM OpcionEstudio o"),
    @NamedQuery(name = "OpcionEstudio.findByIdOpcionestudio", query = "SELECT o FROM OpcionEstudio o WHERE o.idOpcionestudio = :idOpcionestudio"),
    @NamedQuery(name = "OpcionEstudio.findByCupoMaximo", query = "SELECT o FROM OpcionEstudio o WHERE o.cupoMaximo = :cupoMaximo"),
    @NamedQuery(name = "OpcionEstudio.findByCupoMinimo", query = "SELECT o FROM OpcionEstudio o WHERE o.cupoMinimo = :cupoMinimo"),
    @NamedQuery(name = "OpcionEstudio.findByDescripcionOpcionestudio", query = "SELECT o FROM OpcionEstudio o WHERE o.descripcionOpcionestudio = :descripcionOpcionestudio"),
    @NamedQuery(name = "OpcionEstudio.findByEstadoOpcionestudio", query = "SELECT o FROM OpcionEstudio o WHERE o.estadoOpcionestudio = :estadoOpcionestudio"),
    @NamedQuery(name = "OpcionEstudio.findByModalidad", query = "SELECT o FROM OpcionEstudio o WHERE o.modalidad = :modalidad")})
public class OpcionEstudio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_opcionestudio")
    private String idOpcionestudio;
    @Column(name = "cupo_maximo")
    private Integer cupoMaximo;
    @Column(name = "cupo_minimo")
    private Integer cupoMinimo;
    @Column(name = "descripcion_opcionestudio")
    private String descripcionOpcionestudio;
    @Column(name = "estado_opcionestudio")
    private String estadoOpcionestudio;
    @Column(name = "modalidad")
    private String modalidad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "opcionEstudio")
    private List<AspiranteOpcion> aspiranteOpcionList;
    @OneToMany(mappedBy = "idOpcionestudio")
    private List<Grupo> grupoList;

    public OpcionEstudio() {
    }

    public OpcionEstudio(String idOpcionestudio) {
        this.idOpcionestudio = idOpcionestudio;
    }

    public String getIdOpcionestudio() {
        return idOpcionestudio;
    }

    public void setIdOpcionestudio(String idOpcionestudio) {
        this.idOpcionestudio = idOpcionestudio;
    }

    public Integer getCupoMaximo() {
        return cupoMaximo;
    }

    public void setCupoMaximo(Integer cupoMaximo) {
        this.cupoMaximo = cupoMaximo;
    }

    public Integer getCupoMinimo() {
        return cupoMinimo;
    }

    public void setCupoMinimo(Integer cupoMinimo) {
        this.cupoMinimo = cupoMinimo;
    }

    public String getDescripcionOpcionestudio() {
        return descripcionOpcionestudio;
    }

    public void setDescripcionOpcionestudio(String descripcionOpcionestudio) {
        this.descripcionOpcionestudio = descripcionOpcionestudio;
    }

    public String getEstadoOpcionestudio() {
        return estadoOpcionestudio;
    }

    public void setEstadoOpcionestudio(String estadoOpcionestudio) {
        this.estadoOpcionestudio = estadoOpcionestudio;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public List<AspiranteOpcion> getAspiranteOpcionList() {
        return aspiranteOpcionList;
    }

    public void setAspiranteOpcionList(List<AspiranteOpcion> aspiranteOpcionList) {
        this.aspiranteOpcionList = aspiranteOpcionList;
    }

    public List<Grupo> getGrupoList() {
        return grupoList;
    }

    public void setGrupoList(List<Grupo> grupoList) {
        this.grupoList = grupoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOpcionestudio != null ? idOpcionestudio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OpcionEstudio)) {
            return false;
        }
        OpcionEstudio other = (OpcionEstudio) object;
        if ((this.idOpcionestudio == null && other.idOpcionestudio != null) || (this.idOpcionestudio != null && !this.idOpcionestudio.equals(other.idOpcionestudio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.OpcionEstudio[ idOpcionestudio=" + idOpcionestudio + " ]";
    }
    
}
