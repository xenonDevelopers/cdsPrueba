/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "vista_alumnoreporte")
@NamedQueries({
    @NamedQuery(name = "VistaAlumnoreporte.findAll", query = "SELECT v FROM VistaAlumnoreporte v"),
    @NamedQuery(name = "VistaAlumnoreporte.findByMatricula", query = "SELECT v FROM VistaAlumnoreporte v WHERE v.matricula = :matricula"),
    @NamedQuery(name = "VistaAlumnoreporte.findByNombres", query = "SELECT v FROM VistaAlumnoreporte v WHERE v.nombres = :nombres"),
    @NamedQuery(name = "VistaAlumnoreporte.findByApellidoPaterno", query = "SELECT v FROM VistaAlumnoreporte v WHERE v.apellidoPaterno = :apellidoPaterno"),
    @NamedQuery(name = "VistaAlumnoreporte.findByApellidoMaterno", query = "SELECT v FROM VistaAlumnoreporte v WHERE v.apellidoMaterno = :apellidoMaterno"),
    @NamedQuery(name = "VistaAlumnoreporte.findByFechaRegistro", query = "SELECT v FROM VistaAlumnoreporte v WHERE v.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "VistaAlumnoreporte.findByEstadoAlumno", query = "SELECT v FROM VistaAlumnoreporte v WHERE v.estadoAlumno = :estadoAlumno"),
    @NamedQuery(name = "VistaAlumnoreporte.findByIdGeneracion", query = "SELECT v FROM VistaAlumnoreporte v WHERE v.idGeneracion = :idGeneracion"),
    @NamedQuery(name = "VistaAlumnoreporte.findByIdOpcionestudio", query = "SELECT v FROM VistaAlumnoreporte v WHERE v.idOpcionestudio = :idOpcionestudio"),
    @NamedQuery(name = "VistaAlumnoreporte.findByIdPlanestudio", query = "SELECT v FROM VistaAlumnoreporte v WHERE v.idPlanestudio = :idPlanestudio"),
    @NamedQuery(name = "VistaAlumnoreporte.findByIdTiponivelacademico", query = "SELECT v FROM VistaAlumnoreporte v WHERE v.idTiponivelacademico = :idTiponivelacademico"),
    @NamedQuery(name = "VistaAlumnoreporte.findByIdPlantel", query = "SELECT v FROM VistaAlumnoreporte v WHERE v.idPlantel = :idPlantel"),
    @NamedQuery(name = "VistaAlumnoreporte.findByNombrePlanestudio", query = "SELECT v FROM VistaAlumnoreporte v WHERE v.nombrePlanestudio = :nombrePlanestudio"),
    @NamedQuery(name = "VistaAlumnoreporte.findByIdTipoalumno", query = "SELECT v FROM VistaAlumnoreporte v WHERE v.idTipoalumno = :idTipoalumno"),
    @NamedQuery(name = "VistaAlumnoreporte.findByDescripcionTipoalumno", query = "SELECT v FROM VistaAlumnoreporte v WHERE v.descripcionTipoalumno = :descripcionTipoalumno"),
    @NamedQuery(name = "VistaAlumnoreporte.findByModalidad", query = "SELECT v FROM VistaAlumnoreporte v WHERE v.modalidad = :modalidad"),
    @NamedQuery(name = "VistaAlumnoreporte.findByEstadoAsignacion", query = "SELECT v FROM VistaAlumnoreporte v WHERE v.estadoAsignacion = :estadoAsignacion"),
    @NamedQuery(name = "VistaAlumnoreporte.findByIdGrupo", query = "SELECT v FROM VistaAlumnoreporte v WHERE v.idGrupo = :idGrupo"),
    @NamedQuery(name = "VistaAlumnoreporte.findByNombreGrupo", query = "SELECT v FROM VistaAlumnoreporte v WHERE v.nombreGrupo = :nombreGrupo"),
    @NamedQuery(name = "VistaAlumnoreporte.findByIdTiposexo", query = "SELECT v FROM VistaAlumnoreporte v WHERE v.idTiposexo = :idTiposexo"),
    @NamedQuery(name = "VistaAlumnoreporte.findByCurp", query = "SELECT v FROM VistaAlumnoreporte v WHERE v.curp = :curp"),
    @NamedQuery(name = "VistaAlumnoreporte.findByDescripcionTiposexo", query = "SELECT v FROM VistaAlumnoreporte v WHERE v.descripcionTiposexo = :descripcionTiposexo"),
    @NamedQuery(name = "VistaAlumnoreporte.findByCorreoElectronico", query = "SELECT v FROM VistaAlumnoreporte v WHERE v.correoElectronico = :correoElectronico"),
    @NamedQuery(name = "VistaAlumnoreporte.findByFechaFactura", query = "SELECT v FROM VistaAlumnoreporte v WHERE v.fechaFactura = :fechaFactura"),
    @NamedQuery(name = "VistaAlumnoreporte.findByNumeroFactura", query = "SELECT v FROM VistaAlumnoreporte v WHERE v.numeroFactura = :numeroFactura"),
    @NamedQuery(name = "VistaAlumnoreporte.findByNombrePlantel", query = "SELECT v FROM VistaAlumnoreporte v WHERE v.nombrePlantel = :nombrePlantel"),
    @NamedQuery(name = "VistaAlumnoreporte.findByDescripcionOpcionestudio", query = "SELECT v FROM VistaAlumnoreporte v WHERE v.descripcionOpcionestudio = :descripcionOpcionestudio"),
    @NamedQuery(name = "VistaAlumnoreporte.findByAbreviaturaTitulo", query = "SELECT v FROM VistaAlumnoreporte v WHERE v.abreviaturaTitulo = :abreviaturaTitulo"),
    @NamedQuery(name = "VistaAlumnoreporte.findByClave", query = "SELECT v FROM VistaAlumnoreporte v WHERE v.clave = :clave"),
    @NamedQuery(name = "VistaAlumnoreporte.findByRvoe", query = "SELECT v FROM VistaAlumnoreporte v WHERE v.rvoe = :rvoe"),
    @NamedQuery(name = "VistaAlumnoreporte.findByAbreviaturaConvenio", query = "SELECT v FROM VistaAlumnoreporte v WHERE v.abreviaturaConvenio = :abreviaturaConvenio")})
public class VistaAlumnoreporte implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "matricula")
    private String matricula;
    @Column(name = "nombres")
    private String nombres;
    @Column(name = "apellido_paterno")
    private String apellidoPaterno;
    @Column(name = "apellido_materno")
    private String apellidoMaterno;
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;
    @Column(name = "estado_alumno")
    private String estadoAlumno;
    @Column(name = "id_generacion")
    private BigInteger idGeneracion;
    @Column(name = "id_opcionestudio")
    private String idOpcionestudio;
    @Column(name = "id_planestudio")
    private BigInteger idPlanestudio;
    @Column(name = "id_tiponivelacademico")
    private BigInteger idTiponivelacademico;
    @Column(name = "id_plantel")
    private BigInteger idPlantel;
    @Column(name = "nombre_planestudio")
    private String nombrePlanestudio;
    @Column(name = "id_tipoalumno")
    private BigInteger idTipoalumno;
    @Column(name = "descripcion_tipoalumno")
    private String descripcionTipoalumno;
    @Column(name = "modalidad")
    private String modalidad;
    @Column(name = "estado_asignacion")
    private String estadoAsignacion;
    @Column(name = "id_grupo")
    private BigInteger idGrupo;
    @Column(name = "nombre_grupo")
    private String nombreGrupo;
    @Column(name = "id_tiposexo")
    private BigInteger idTiposexo;
    @Column(name = "curp")
    private String curp;
    @Column(name = "descripcion_tiposexo")
    private String descripcionTiposexo;
    @Column(name = "correo_electronico")
    private String correoElectronico;
    @Column(name = "fecha_factura")
    @Temporal(TemporalType.DATE)
    private Date fechaFactura;
    @Column(name = "numero_factura")
    private Integer numeroFactura;
    @Column(name = "nombre_plantel")
    private String nombrePlantel;
    @Column(name = "descripcion_opcionestudio")
    private String descripcionOpcionestudio;
    @Column(name = "abreviatura_titulo")
    private String abreviaturaTitulo;
    @Column(name = "clave")
    private String clave;
    @Column(name = "rvoe")
    private String rvoe;
    @Column(name = "abreviatura_convenio")
    private String abreviaturaConvenio;

    public VistaAlumnoreporte() {
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getEstadoAlumno() {
        return estadoAlumno;
    }

    public void setEstadoAlumno(String estadoAlumno) {
        this.estadoAlumno = estadoAlumno;
    }

    public BigInteger getIdGeneracion() {
        return idGeneracion;
    }

    public void setIdGeneracion(BigInteger idGeneracion) {
        this.idGeneracion = idGeneracion;
    }

    public String getIdOpcionestudio() {
        return idOpcionestudio;
    }

    public void setIdOpcionestudio(String idOpcionestudio) {
        this.idOpcionestudio = idOpcionestudio;
    }

    public BigInteger getIdPlanestudio() {
        return idPlanestudio;
    }

    public void setIdPlanestudio(BigInteger idPlanestudio) {
        this.idPlanestudio = idPlanestudio;
    }

    public BigInteger getIdTiponivelacademico() {
        return idTiponivelacademico;
    }

    public void setIdTiponivelacademico(BigInteger idTiponivelacademico) {
        this.idTiponivelacademico = idTiponivelacademico;
    }

    public BigInteger getIdPlantel() {
        return idPlantel;
    }

    public void setIdPlantel(BigInteger idPlantel) {
        this.idPlantel = idPlantel;
    }

    public String getNombrePlanestudio() {
        return nombrePlanestudio;
    }

    public void setNombrePlanestudio(String nombrePlanestudio) {
        this.nombrePlanestudio = nombrePlanestudio;
    }

    public BigInteger getIdTipoalumno() {
        return idTipoalumno;
    }

    public void setIdTipoalumno(BigInteger idTipoalumno) {
        this.idTipoalumno = idTipoalumno;
    }

    public String getDescripcionTipoalumno() {
        return descripcionTipoalumno;
    }

    public void setDescripcionTipoalumno(String descripcionTipoalumno) {
        this.descripcionTipoalumno = descripcionTipoalumno;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public String getEstadoAsignacion() {
        return estadoAsignacion;
    }

    public void setEstadoAsignacion(String estadoAsignacion) {
        this.estadoAsignacion = estadoAsignacion;
    }

    public BigInteger getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(BigInteger idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getNombreGrupo() {
        return nombreGrupo;
    }

    public void setNombreGrupo(String nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
    }

    public BigInteger getIdTiposexo() {
        return idTiposexo;
    }

    public void setIdTiposexo(BigInteger idTiposexo) {
        this.idTiposexo = idTiposexo;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getDescripcionTiposexo() {
        return descripcionTiposexo;
    }

    public void setDescripcionTiposexo(String descripcionTiposexo) {
        this.descripcionTiposexo = descripcionTiposexo;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public Date getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(Date fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public Integer getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(Integer numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public String getNombrePlantel() {
        return nombrePlantel;
    }

    public void setNombrePlantel(String nombrePlantel) {
        this.nombrePlantel = nombrePlantel;
    }

    public String getDescripcionOpcionestudio() {
        return descripcionOpcionestudio;
    }

    public void setDescripcionOpcionestudio(String descripcionOpcionestudio) {
        this.descripcionOpcionestudio = descripcionOpcionestudio;
    }

    public String getAbreviaturaTitulo() {
        return abreviaturaTitulo;
    }

    public void setAbreviaturaTitulo(String abreviaturaTitulo) {
        this.abreviaturaTitulo = abreviaturaTitulo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getRvoe() {
        return rvoe;
    }

    public void setRvoe(String rvoe) {
        this.rvoe = rvoe;
    }

    public String getAbreviaturaConvenio() {
        return abreviaturaConvenio;
    }

    public void setAbreviaturaConvenio(String abreviaturaConvenio) {
        this.abreviaturaConvenio = abreviaturaConvenio;
    }
    
}
