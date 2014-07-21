/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "vista_alumno")
@NamedQueries({
    @NamedQuery(name = "VistaAlumno.findAll", query = "SELECT v FROM VistaAlumno v"),
    @NamedQuery(name = "VistaAlumno.findByMatricula", query = "SELECT v FROM VistaAlumno v WHERE v.matricula = :matricula"),
    @NamedQuery(name = "VistaAlumno.findByCurp", query = "SELECT v FROM VistaAlumno v WHERE v.curp = :curp"),
    @NamedQuery(name = "VistaAlumno.findByDescripcionTiposexo", query = "SELECT v FROM VistaAlumno v WHERE v.descripcionTiposexo = :descripcionTiposexo"),
    @NamedQuery(name = "VistaAlumno.findByNombres", query = "SELECT v FROM VistaAlumno v WHERE v.nombres = :nombres"),
    @NamedQuery(name = "VistaAlumno.findByApellidoPaterno", query = "SELECT v FROM VistaAlumno v WHERE v.apellidoPaterno = :apellidoPaterno"),
    @NamedQuery(name = "VistaAlumno.findByApellidoMaterno", query = "SELECT v FROM VistaAlumno v WHERE v.apellidoMaterno = :apellidoMaterno"),
    @NamedQuery(name = "VistaAlumno.findByCorreoElectronico", query = "SELECT v FROM VistaAlumno v WHERE v.correoElectronico = :correoElectronico"),
    @NamedQuery(name = "VistaAlumno.findByEstadoAlumno", query = "SELECT v FROM VistaAlumno v WHERE v.estadoAlumno = :estadoAlumno"),
    @NamedQuery(name = "VistaAlumno.findByIdPlanestudio", query = "SELECT v FROM VistaAlumno v WHERE v.idPlanestudio = :idPlanestudio"),
    @NamedQuery(name = "VistaAlumno.findByRvoe", query = "SELECT v FROM VistaAlumno v WHERE v.rvoe = :rvoe"),
    @NamedQuery(name = "VistaAlumno.findByPlanEstudio", query = "SELECT v FROM VistaAlumno v WHERE v.planEstudio = :planEstudio"),
    @NamedQuery(name = "VistaAlumno.findByInstitucionRegistro", query = "SELECT v FROM VistaAlumno v WHERE v.institucionRegistro = :institucionRegistro"),
    @NamedQuery(name = "VistaAlumno.findByFechaPlan", query = "SELECT v FROM VistaAlumno v WHERE v.fechaPlan = :fechaPlan"),
    @NamedQuery(name = "VistaAlumno.findByIdTiponivelacademico", query = "SELECT v FROM VistaAlumno v WHERE v.idTiponivelacademico = :idTiponivelacademico"),
    @NamedQuery(name = "VistaAlumno.findByNivelAcademico", query = "SELECT v FROM VistaAlumno v WHERE v.nivelAcademico = :nivelAcademico"),
    @NamedQuery(name = "VistaAlumno.findByDescripcionGrupoinduccion", query = "SELECT v FROM VistaAlumno v WHERE v.descripcionGrupoinduccion = :descripcionGrupoinduccion")})
public class VistaAlumno implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "matricula")
    private String matricula;
    @Column(name = "curp")
    private String curp;
    @Column(name = "descripcion_tiposexo")
    private String descripcionTiposexo;
    @Column(name = "nombres")
    private String nombres;
    @Column(name = "apellido_paterno")
    private String apellidoPaterno;
    @Column(name = "apellido_materno")
    private String apellidoMaterno;
    @Column(name = "correo_electronico")
    private String correoElectronico;
    @Column(name = "estado_alumno")
    private String estadoAlumno;
    @Column(name = "id_planestudio")
    private BigInteger idPlanestudio;
    @Column(name = "rvoe")
    private String rvoe;
    @Column(name = "plan_estudio")
    private String planEstudio;
    @Column(name = "institucion_registro")
    private String institucionRegistro;
    @Column(name = "fecha_plan")
    private String fechaPlan;
    @Column(name = "id_tiponivelacademico")
    private BigInteger idTiponivelacademico;
    @Column(name = "nivel_academico")
    private String nivelAcademico;
    @Column(name = "descripcion_grupoinduccion")
    private String descripcionGrupoinduccion;

    public VistaAlumno() {
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
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

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getEstadoAlumno() {
        return estadoAlumno;
    }

    public void setEstadoAlumno(String estadoAlumno) {
        this.estadoAlumno = estadoAlumno;
    }

    public BigInteger getIdPlanestudio() {
        return idPlanestudio;
    }

    public void setIdPlanestudio(BigInteger idPlanestudio) {
        this.idPlanestudio = idPlanestudio;
    }

    public String getRvoe() {
        return rvoe;
    }

    public void setRvoe(String rvoe) {
        this.rvoe = rvoe;
    }

    public String getPlanEstudio() {
        return planEstudio;
    }

    public void setPlanEstudio(String planEstudio) {
        this.planEstudio = planEstudio;
    }

    public String getInstitucionRegistro() {
        return institucionRegistro;
    }

    public void setInstitucionRegistro(String institucionRegistro) {
        this.institucionRegistro = institucionRegistro;
    }

    public String getFechaPlan() {
        return fechaPlan;
    }

    public void setFechaPlan(String fechaPlan) {
        this.fechaPlan = fechaPlan;
    }

    public BigInteger getIdTiponivelacademico() {
        return idTiponivelacademico;
    }

    public void setIdTiponivelacademico(BigInteger idTiponivelacademico) {
        this.idTiponivelacademico = idTiponivelacademico;
    }

    public String getNivelAcademico() {
        return nivelAcademico;
    }

    public void setNivelAcademico(String nivelAcademico) {
        this.nivelAcademico = nivelAcademico;
    }

    public String getDescripcionGrupoinduccion() {
        return descripcionGrupoinduccion;
    }

    public void setDescripcionGrupoinduccion(String descripcionGrupoinduccion) {
        this.descripcionGrupoinduccion = descripcionGrupoinduccion;
    }
    
}
