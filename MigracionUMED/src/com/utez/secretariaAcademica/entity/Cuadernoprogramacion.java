/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.secretariaAcademica.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "cuadernoprogramacion")
@NamedQueries({
    @NamedQuery(name = "Cuadernoprogramacion.findAll", query = "SELECT c FROM Cuadernoprogramacion c"),
    @NamedQuery(name = "Cuadernoprogramacion.findByIdcuadernoprogramacion", query = "SELECT c FROM Cuadernoprogramacion c WHERE c.idcuadernoprogramacion = :idcuadernoprogramacion"),
    @NamedQuery(name = "Cuadernoprogramacion.findByFechaautorizacion", query = "SELECT c FROM Cuadernoprogramacion c WHERE c.fechaautorizacion = :fechaautorizacion"),
    @NamedQuery(name = "Cuadernoprogramacion.findByTipoprogramacion", query = "SELECT c FROM Cuadernoprogramacion c WHERE c.tipoprogramacion = :tipoprogramacion"),
    @NamedQuery(name = "Cuadernoprogramacion.findByNumeroprogramacion", query = "SELECT c FROM Cuadernoprogramacion c WHERE c.numeroprogramacion = :numeroprogramacion"),
    @NamedQuery(name = "Cuadernoprogramacion.findByEstado", query = "SELECT c FROM Cuadernoprogramacion c WHERE c.estado = :estado")})
public class Cuadernoprogramacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcuadernoprogramacion")
    private Integer idcuadernoprogramacion;
    @Column(name = "fechaautorizacion")
    @Temporal(TemporalType.DATE)
    private Date fechaautorizacion;
    @Column(name = "tipoprogramacion")
    private String tipoprogramacion;
    @Column(name = "numeroprogramacion")
    private Integer numeroprogramacion;
    @Column(name = "estado")
    private String estado;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "cuadernoprogramacion")
    private Programacionra programacionra;
    @OneToMany(mappedBy = "idcuadernoprogramacion")
    private List<Programacionopcionc> programacionopcioncList;
    @JoinColumn(name = "idsolicitudprogramacion", referencedColumnName = "idsolicitudprogramacion")
    @ManyToOne
    private Solicitudprogramacion idsolicitudprogramacion;
    @JoinColumn(name = "idasignatura", referencedColumnName = "idasignatura")
    @ManyToOne
    private Asignatura idasignatura;
    @OneToMany(mappedBy = "idcuadernoprogramacion")
    private List<Programacioncancelada> programacioncanceladaList;

    public Cuadernoprogramacion() {
    }

    public Cuadernoprogramacion(Integer idcuadernoprogramacion) {
        this.idcuadernoprogramacion = idcuadernoprogramacion;
    }

    public Integer getIdcuadernoprogramacion() {
        return idcuadernoprogramacion;
    }

    public void setIdcuadernoprogramacion(Integer idcuadernoprogramacion) {
        this.idcuadernoprogramacion = idcuadernoprogramacion;
    }

    public Date getFechaautorizacion() {
        return fechaautorizacion;
    }

    public void setFechaautorizacion(Date fechaautorizacion) {
        this.fechaautorizacion = fechaautorizacion;
    }

    public String getTipoprogramacion() {
        return tipoprogramacion;
    }

    public void setTipoprogramacion(String tipoprogramacion) {
        this.tipoprogramacion = tipoprogramacion;
    }

    public Integer getNumeroprogramacion() {
        return numeroprogramacion;
    }

    public void setNumeroprogramacion(Integer numeroprogramacion) {
        this.numeroprogramacion = numeroprogramacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Programacionra getProgramacionra() {
        return programacionra;
    }

    public void setProgramacionra(Programacionra programacionra) {
        this.programacionra = programacionra;
    }

    public List<Programacionopcionc> getProgramacionopcioncList() {
        return programacionopcioncList;
    }

    public void setProgramacionopcioncList(List<Programacionopcionc> programacionopcioncList) {
        this.programacionopcioncList = programacionopcioncList;
    }

    public Solicitudprogramacion getIdsolicitudprogramacion() {
        return idsolicitudprogramacion;
    }

    public void setIdsolicitudprogramacion(Solicitudprogramacion idsolicitudprogramacion) {
        this.idsolicitudprogramacion = idsolicitudprogramacion;
    }

    public Asignatura getIdasignatura() {
        return idasignatura;
    }

    public void setIdasignatura(Asignatura idasignatura) {
        this.idasignatura = idasignatura;
    }

    public List<Programacioncancelada> getProgramacioncanceladaList() {
        return programacioncanceladaList;
    }

    public void setProgramacioncanceladaList(List<Programacioncancelada> programacioncanceladaList) {
        this.programacioncanceladaList = programacioncanceladaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcuadernoprogramacion != null ? idcuadernoprogramacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cuadernoprogramacion)) {
            return false;
        }
        Cuadernoprogramacion other = (Cuadernoprogramacion) object;
        if ((this.idcuadernoprogramacion == null && other.idcuadernoprogramacion != null) || (this.idcuadernoprogramacion != null && !this.idcuadernoprogramacion.equals(other.idcuadernoprogramacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.secretariaAcademica.entity.Cuadernoprogramacion[ idcuadernoprogramacion=" + idcuadernoprogramacion + " ]";
    }
    
}
