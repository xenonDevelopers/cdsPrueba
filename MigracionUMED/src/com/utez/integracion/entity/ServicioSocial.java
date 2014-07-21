/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import com.utez.secretariaAcademica.entity.Alumno;
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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "servicio_social")
@NamedQueries({
    @NamedQuery(name = "ServicioSocial.findAll", query = "SELECT s FROM ServicioSocial s"),
    @NamedQuery(name = "ServicioSocial.findByIdServiciosocial", query = "SELECT s FROM ServicioSocial s WHERE s.idServiciosocial = :idServiciosocial"),
    @NamedQuery(name = "ServicioSocial.findByFechaInicio", query = "SELECT s FROM ServicioSocial s WHERE s.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "ServicioSocial.findByFechaFin", query = "SELECT s FROM ServicioSocial s WHERE s.fechaFin = :fechaFin"),
    @NamedQuery(name = "ServicioSocial.findByNombreEmpresa", query = "SELECT s FROM ServicioSocial s WHERE s.nombreEmpresa = :nombreEmpresa"),
    @NamedQuery(name = "ServicioSocial.findByNombreResponsable", query = "SELECT s FROM ServicioSocial s WHERE s.nombreResponsable = :nombreResponsable"),
    @NamedQuery(name = "ServicioSocial.findByCargoResponsable", query = "SELECT s FROM ServicioSocial s WHERE s.cargoResponsable = :cargoResponsable"),
    @NamedQuery(name = "ServicioSocial.findByEstadoServiciosocial", query = "SELECT s FROM ServicioSocial s WHERE s.estadoServiciosocial = :estadoServiciosocial"),
    @NamedQuery(name = "ServicioSocial.findByFolio", query = "SELECT s FROM ServicioSocial s WHERE s.folio = :folio")})
public class ServicioSocial implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_serviciosocial")
    private Long idServiciosocial;
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @Column(name = "nombre_empresa")
    private String nombreEmpresa;
    @Column(name = "nombre_responsable")
    private String nombreResponsable;
    @Column(name = "cargo_responsable")
    private String cargoResponsable;
    @Column(name = "estado_serviciosocial")
    private String estadoServiciosocial;
    @Column(name = "folio")
    private String folio;
    @OneToMany(mappedBy = "idServiciosocial")
    private List<ReporteServicio> reporteServicioList;
    @JoinColumn(name = "matricula", referencedColumnName = "matricula")
    @ManyToOne
    private Alumno matricula;

    public ServicioSocial() {
    }

    public ServicioSocial(Long idServiciosocial) {
        this.idServiciosocial = idServiciosocial;
    }

    public Long getIdServiciosocial() {
        return idServiciosocial;
    }

    public void setIdServiciosocial(Long idServiciosocial) {
        this.idServiciosocial = idServiciosocial;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getNombreResponsable() {
        return nombreResponsable;
    }

    public void setNombreResponsable(String nombreResponsable) {
        this.nombreResponsable = nombreResponsable;
    }

    public String getCargoResponsable() {
        return cargoResponsable;
    }

    public void setCargoResponsable(String cargoResponsable) {
        this.cargoResponsable = cargoResponsable;
    }

    public String getEstadoServiciosocial() {
        return estadoServiciosocial;
    }

    public void setEstadoServiciosocial(String estadoServiciosocial) {
        this.estadoServiciosocial = estadoServiciosocial;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public List<ReporteServicio> getReporteServicioList() {
        return reporteServicioList;
    }

    public void setReporteServicioList(List<ReporteServicio> reporteServicioList) {
        this.reporteServicioList = reporteServicioList;
    }

    public Alumno getMatricula() {
        return matricula;
    }

    public void setMatricula(Alumno matricula) {
        this.matricula = matricula;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idServiciosocial != null ? idServiciosocial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServicioSocial)) {
            return false;
        }
        ServicioSocial other = (ServicioSocial) object;
        if ((this.idServiciosocial == null && other.idServiciosocial != null) || (this.idServiciosocial != null && !this.idServiciosocial.equals(other.idServiciosocial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.ServicioSocial[ idServiciosocial=" + idServiciosocial + " ]";
    }
    
}
