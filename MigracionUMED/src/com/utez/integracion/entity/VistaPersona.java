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
@Table(name = "vista_persona")
@NamedQueries({
    @NamedQuery(name = "VistaPersona.findAll", query = "SELECT v FROM VistaPersona v"),
    @NamedQuery(name = "VistaPersona.findByCurp", query = "SELECT v FROM VistaPersona v WHERE v.curp = :curp"),
    @NamedQuery(name = "VistaPersona.findByRfc", query = "SELECT v FROM VistaPersona v WHERE v.rfc = :rfc"),
    @NamedQuery(name = "VistaPersona.findByNombres", query = "SELECT v FROM VistaPersona v WHERE v.nombres = :nombres"),
    @NamedQuery(name = "VistaPersona.findByApellidoPaterno", query = "SELECT v FROM VistaPersona v WHERE v.apellidoPaterno = :apellidoPaterno"),
    @NamedQuery(name = "VistaPersona.findByApellidoMaterno", query = "SELECT v FROM VistaPersona v WHERE v.apellidoMaterno = :apellidoMaterno"),
    @NamedQuery(name = "VistaPersona.findByDescripcionTiposexo", query = "SELECT v FROM VistaPersona v WHERE v.descripcionTiposexo = :descripcionTiposexo"),
    @NamedQuery(name = "VistaPersona.findByDescripcionTipoestadocivil", query = "SELECT v FROM VistaPersona v WHERE v.descripcionTipoestadocivil = :descripcionTipoestadocivil"),
    @NamedQuery(name = "VistaPersona.findByTelefonoCasa", query = "SELECT v FROM VistaPersona v WHERE v.telefonoCasa = :telefonoCasa"),
    @NamedQuery(name = "VistaPersona.findByTelefonoRecados", query = "SELECT v FROM VistaPersona v WHERE v.telefonoRecados = :telefonoRecados"),
    @NamedQuery(name = "VistaPersona.findByTelefonoMovil", query = "SELECT v FROM VistaPersona v WHERE v.telefonoMovil = :telefonoMovil"),
    @NamedQuery(name = "VistaPersona.findByCorreoElectronico", query = "SELECT v FROM VistaPersona v WHERE v.correoElectronico = :correoElectronico"),
    @NamedQuery(name = "VistaPersona.findByLugarNacimiento", query = "SELECT v FROM VistaPersona v WHERE v.lugarNacimiento = :lugarNacimiento"),
    @NamedQuery(name = "VistaPersona.findByFechaNacimiento", query = "SELECT v FROM VistaPersona v WHERE v.fechaNacimiento = :fechaNacimiento"),
    @NamedQuery(name = "VistaPersona.findByIdAsentamiento", query = "SELECT v FROM VistaPersona v WHERE v.idAsentamiento = :idAsentamiento"),
    @NamedQuery(name = "VistaPersona.findByDireccion", query = "SELECT v FROM VistaPersona v WHERE v.direccion = :direccion"),
    @NamedQuery(name = "VistaPersona.findByAsentamiento", query = "SELECT v FROM VistaPersona v WHERE v.asentamiento = :asentamiento"),
    @NamedQuery(name = "VistaPersona.findByCiudad", query = "SELECT v FROM VistaPersona v WHERE v.ciudad = :ciudad"),
    @NamedQuery(name = "VistaPersona.findByEstado", query = "SELECT v FROM VistaPersona v WHERE v.estado = :estado"),
    @NamedQuery(name = "VistaPersona.findByTelefonoTrabajo", query = "SELECT v FROM VistaPersona v WHERE v.telefonoTrabajo = :telefonoTrabajo")})
public class VistaPersona implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "curp")
    private String curp;
    @Column(name = "rfc")
    private String rfc;
    @Column(name = "nombres")
    private String nombres;
    @Column(name = "apellido_paterno")
    private String apellidoPaterno;
    @Column(name = "apellido_materno")
    private String apellidoMaterno;
    @Column(name = "descripcion_tiposexo")
    private String descripcionTiposexo;
    @Column(name = "descripcion_tipoestadocivil")
    private String descripcionTipoestadocivil;
    @Column(name = "telefono_casa")
    private String telefonoCasa;
    @Column(name = "telefono_recados")
    private String telefonoRecados;
    @Column(name = "telefono_movil")
    private String telefonoMovil;
    @Column(name = "correo_electronico")
    private String correoElectronico;
    @Column(name = "lugar_nacimiento")
    private String lugarNacimiento;
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Column(name = "id_asentamiento")
    private BigInteger idAsentamiento;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "asentamiento")
    private String asentamiento;
    @Column(name = "ciudad")
    private String ciudad;
    @Column(name = "estado")
    private String estado;
    @Column(name = "telefono_trabajo")
    private String telefonoTrabajo;

    public VistaPersona() {
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
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

    public String getDescripcionTiposexo() {
        return descripcionTiposexo;
    }

    public void setDescripcionTiposexo(String descripcionTiposexo) {
        this.descripcionTiposexo = descripcionTiposexo;
    }

    public String getDescripcionTipoestadocivil() {
        return descripcionTipoestadocivil;
    }

    public void setDescripcionTipoestadocivil(String descripcionTipoestadocivil) {
        this.descripcionTipoestadocivil = descripcionTipoestadocivil;
    }

    public String getTelefonoCasa() {
        return telefonoCasa;
    }

    public void setTelefonoCasa(String telefonoCasa) {
        this.telefonoCasa = telefonoCasa;
    }

    public String getTelefonoRecados() {
        return telefonoRecados;
    }

    public void setTelefonoRecados(String telefonoRecados) {
        this.telefonoRecados = telefonoRecados;
    }

    public String getTelefonoMovil() {
        return telefonoMovil;
    }

    public void setTelefonoMovil(String telefonoMovil) {
        this.telefonoMovil = telefonoMovil;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getLugarNacimiento() {
        return lugarNacimiento;
    }

    public void setLugarNacimiento(String lugarNacimiento) {
        this.lugarNacimiento = lugarNacimiento;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
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

    public String getAsentamiento() {
        return asentamiento;
    }

    public void setAsentamiento(String asentamiento) {
        this.asentamiento = asentamiento;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTelefonoTrabajo() {
        return telefonoTrabajo;
    }

    public void setTelefonoTrabajo(String telefonoTrabajo) {
        this.telefonoTrabajo = telefonoTrabajo;
    }
    
}
