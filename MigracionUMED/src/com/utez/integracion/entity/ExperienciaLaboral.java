/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import java.io.Serializable;
import java.math.BigInteger;
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

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "experiencia_laboral")
@NamedQueries({
    @NamedQuery(name = "ExperienciaLaboral.findAll", query = "SELECT e FROM ExperienciaLaboral e"),
    @NamedQuery(name = "ExperienciaLaboral.findByIdExperiencialaboral", query = "SELECT e FROM ExperienciaLaboral e WHERE e.idExperiencialaboral = :idExperiencialaboral"),
    @NamedQuery(name = "ExperienciaLaboral.findByNombreInstitucion", query = "SELECT e FROM ExperienciaLaboral e WHERE e.nombreInstitucion = :nombreInstitucion"),
    @NamedQuery(name = "ExperienciaLaboral.findByIdAsentamiento", query = "SELECT e FROM ExperienciaLaboral e WHERE e.idAsentamiento = :idAsentamiento"),
    @NamedQuery(name = "ExperienciaLaboral.findByDireccion", query = "SELECT e FROM ExperienciaLaboral e WHERE e.direccion = :direccion"),
    @NamedQuery(name = "ExperienciaLaboral.findByPuesto", query = "SELECT e FROM ExperienciaLaboral e WHERE e.puesto = :puesto"),
    @NamedQuery(name = "ExperienciaLaboral.findByAntiguedad", query = "SELECT e FROM ExperienciaLaboral e WHERE e.antiguedad = :antiguedad")})
public class ExperienciaLaboral implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_experiencialaboral")
    private Long idExperiencialaboral;
    @Column(name = "nombre_institucion")
    private String nombreInstitucion;
    @Column(name = "id_asentamiento")
    private BigInteger idAsentamiento;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "puesto")
    private String puesto;
    @Column(name = "antiguedad")
    private String antiguedad;
    @JoinColumn(name = "id_tipotrabajo", referencedColumnName = "id_tipotrabajo")
    @ManyToOne
    private TipoTrabajo idTipotrabajo;
    @JoinColumn(name = "id_tipoinstitucion", referencedColumnName = "id_tipoempresa")
    @ManyToOne
    private TipoEmpresa idTipoinstitucion;
    @JoinColumn(name = "id_recursohumano", referencedColumnName = "id_recursohumano")
    @ManyToOne
    private RecursoHumano idRecursohumano;

    public ExperienciaLaboral() {
    }

    public ExperienciaLaboral(Long idExperiencialaboral) {
        this.idExperiencialaboral = idExperiencialaboral;
    }

    public Long getIdExperiencialaboral() {
        return idExperiencialaboral;
    }

    public void setIdExperiencialaboral(Long idExperiencialaboral) {
        this.idExperiencialaboral = idExperiencialaboral;
    }

    public String getNombreInstitucion() {
        return nombreInstitucion;
    }

    public void setNombreInstitucion(String nombreInstitucion) {
        this.nombreInstitucion = nombreInstitucion;
    }

    public BigInteger getIdAsentamiento() {
        return idAsentamiento;
    }

    public void setIdAsentamiento(BigInteger idAsentamiento) {
        this.idAsentamiento = idAsentamiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(String antiguedad) {
        this.antiguedad = antiguedad;
    }

    public TipoTrabajo getIdTipotrabajo() {
        return idTipotrabajo;
    }

    public void setIdTipotrabajo(TipoTrabajo idTipotrabajo) {
        this.idTipotrabajo = idTipotrabajo;
    }

    public TipoEmpresa getIdTipoinstitucion() {
        return idTipoinstitucion;
    }

    public void setIdTipoinstitucion(TipoEmpresa idTipoinstitucion) {
        this.idTipoinstitucion = idTipoinstitucion;
    }

    public RecursoHumano getIdRecursohumano() {
        return idRecursohumano;
    }

    public void setIdRecursohumano(RecursoHumano idRecursohumano) {
        this.idRecursohumano = idRecursohumano;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idExperiencialaboral != null ? idExperiencialaboral.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExperienciaLaboral)) {
            return false;
        }
        ExperienciaLaboral other = (ExperienciaLaboral) object;
        if ((this.idExperiencialaboral == null && other.idExperiencialaboral != null) || (this.idExperiencialaboral != null && !this.idExperiencialaboral.equals(other.idExperiencialaboral))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.ExperienciaLaboral[ idExperiencialaboral=" + idExperiencialaboral + " ]";
    }
    
}
