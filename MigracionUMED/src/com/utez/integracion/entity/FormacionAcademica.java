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
@Table(name = "formacion_academica")
@NamedQueries({
    @NamedQuery(name = "FormacionAcademica.findAll", query = "SELECT f FROM FormacionAcademica f"),
    @NamedQuery(name = "FormacionAcademica.findByIdFormacionacademica", query = "SELECT f FROM FormacionAcademica f WHERE f.idFormacionacademica = :idFormacionacademica"),
    @NamedQuery(name = "FormacionAcademica.findByNombreInstitucion", query = "SELECT f FROM FormacionAcademica f WHERE f.nombreInstitucion = :nombreInstitucion"),
    @NamedQuery(name = "FormacionAcademica.findByDireccion", query = "SELECT f FROM FormacionAcademica f WHERE f.direccion = :direccion"),
    @NamedQuery(name = "FormacionAcademica.findByFechaEgreso", query = "SELECT f FROM FormacionAcademica f WHERE f.fechaEgreso = :fechaEgreso"),
    @NamedQuery(name = "FormacionAcademica.findByFechaTitulo", query = "SELECT f FROM FormacionAcademica f WHERE f.fechaTitulo = :fechaTitulo"),
    @NamedQuery(name = "FormacionAcademica.findByTitulo", query = "SELECT f FROM FormacionAcademica f WHERE f.titulo = :titulo"),
    @NamedQuery(name = "FormacionAcademica.findByAbreviaturaTitulo", query = "SELECT f FROM FormacionAcademica f WHERE f.abreviaturaTitulo = :abreviaturaTitulo"),
    @NamedQuery(name = "FormacionAcademica.findByCedula", query = "SELECT f FROM FormacionAcademica f WHERE f.cedula = :cedula"),
    @NamedQuery(name = "FormacionAcademica.findByFechaCedula", query = "SELECT f FROM FormacionAcademica f WHERE f.fechaCedula = :fechaCedula")})
public class FormacionAcademica implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_formacionacademica")
    private Long idFormacionacademica;
    @Column(name = "nombre_institucion")
    private String nombreInstitucion;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "fecha_egreso")
    @Temporal(TemporalType.DATE)
    private Date fechaEgreso;
    @Column(name = "fecha_titulo")
    @Temporal(TemporalType.DATE)
    private Date fechaTitulo;
    @Column(name = "titulo")
    private String titulo;
    @Column(name = "abreviatura_titulo")
    private String abreviaturaTitulo;
    @Column(name = "cedula")
    private String cedula;
    @Column(name = "fecha_cedula")
    @Temporal(TemporalType.DATE)
    private Date fechaCedula;
    @JoinColumn(name = "id_tiponivelacademico", referencedColumnName = "id_tiponivelacademico")
    @ManyToOne
    private TipoNivelacademico idTiponivelacademico;
    @JoinColumn(name = "id_tipomodalidad", referencedColumnName = "id_tipomodalidad")
    @ManyToOne
    private TipoModalidad idTipomodalidad;
    @JoinColumn(name = "id_recursohumano", referencedColumnName = "id_recursohumano")
    @ManyToOne
    private RecursoHumano idRecursohumano;
    @JoinColumn(name = "id_asentamiento", referencedColumnName = "id_asentamiento")
    @ManyToOne
    private Asentamiento idAsentamiento;
    @OneToMany(mappedBy = "idFormacionacademica")
    private List<AsignacionAsesorplan> asignacionAsesorplanList;

    public FormacionAcademica() {
    }

    public FormacionAcademica(Long idFormacionacademica) {
        this.idFormacionacademica = idFormacionacademica;
    }

    public Long getIdFormacionacademica() {
        return idFormacionacademica;
    }

    public void setIdFormacionacademica(Long idFormacionacademica) {
        this.idFormacionacademica = idFormacionacademica;
    }

    public String getNombreInstitucion() {
        return nombreInstitucion;
    }

    public void setNombreInstitucion(String nombreInstitucion) {
        this.nombreInstitucion = nombreInstitucion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFechaEgreso() {
        return fechaEgreso;
    }

    public void setFechaEgreso(Date fechaEgreso) {
        this.fechaEgreso = fechaEgreso;
    }

    public Date getFechaTitulo() {
        return fechaTitulo;
    }

    public void setFechaTitulo(Date fechaTitulo) {
        this.fechaTitulo = fechaTitulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAbreviaturaTitulo() {
        return abreviaturaTitulo;
    }

    public void setAbreviaturaTitulo(String abreviaturaTitulo) {
        this.abreviaturaTitulo = abreviaturaTitulo;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public Date getFechaCedula() {
        return fechaCedula;
    }

    public void setFechaCedula(Date fechaCedula) {
        this.fechaCedula = fechaCedula;
    }

    public TipoNivelacademico getIdTiponivelacademico() {
        return idTiponivelacademico;
    }

    public void setIdTiponivelacademico(TipoNivelacademico idTiponivelacademico) {
        this.idTiponivelacademico = idTiponivelacademico;
    }

    public TipoModalidad getIdTipomodalidad() {
        return idTipomodalidad;
    }

    public void setIdTipomodalidad(TipoModalidad idTipomodalidad) {
        this.idTipomodalidad = idTipomodalidad;
    }

    public RecursoHumano getIdRecursohumano() {
        return idRecursohumano;
    }

    public void setIdRecursohumano(RecursoHumano idRecursohumano) {
        this.idRecursohumano = idRecursohumano;
    }

    public Asentamiento getIdAsentamiento() {
        return idAsentamiento;
    }

    public void setIdAsentamiento(Asentamiento idAsentamiento) {
        this.idAsentamiento = idAsentamiento;
    }

    public List<AsignacionAsesorplan> getAsignacionAsesorplanList() {
        return asignacionAsesorplanList;
    }

    public void setAsignacionAsesorplanList(List<AsignacionAsesorplan> asignacionAsesorplanList) {
        this.asignacionAsesorplanList = asignacionAsesorplanList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFormacionacademica != null ? idFormacionacademica.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FormacionAcademica)) {
            return false;
        }
        FormacionAcademica other = (FormacionAcademica) object;
        if ((this.idFormacionacademica == null && other.idFormacionacademica != null) || (this.idFormacionacademica != null && !this.idFormacionacademica.equals(other.idFormacionacademica))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.FormacionAcademica[ idFormacionacademica=" + idFormacionacademica + " ]";
    }
    
}
