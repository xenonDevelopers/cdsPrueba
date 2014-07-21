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
@Table(name = "vista_validacionalumnoasignaturas")
@NamedQueries({
    @NamedQuery(name = "VistaValidacionalumnoasignaturas.findAll", query = "SELECT v FROM VistaValidacionalumnoasignaturas v"),
    @NamedQuery(name = "VistaValidacionalumnoasignaturas.findByMatricula", query = "SELECT v FROM VistaValidacionalumnoasignaturas v WHERE v.matricula = :matricula"),
    @NamedQuery(name = "VistaValidacionalumnoasignaturas.findByReprobadas", query = "SELECT v FROM VistaValidacionalumnoasignaturas v WHERE v.reprobadas = :reprobadas"),
    @NamedQuery(name = "VistaValidacionalumnoasignaturas.findByAprobadas", query = "SELECT v FROM VistaValidacionalumnoasignaturas v WHERE v.aprobadas = :aprobadas"),
    @NamedQuery(name = "VistaValidacionalumnoasignaturas.findByTotales", query = "SELECT v FROM VistaValidacionalumnoasignaturas v WHERE v.totales = :totales"),
    @NamedQuery(name = "VistaValidacionalumnoasignaturas.findByPorcentajeAprobadas", query = "SELECT v FROM VistaValidacionalumnoasignaturas v WHERE v.porcentajeAprobadas = :porcentajeAprobadas")})
public class VistaValidacionalumnoasignaturas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "matricula")
    private String matricula;
    @Column(name = "reprobadas")
    private BigInteger reprobadas;
    @Column(name = "aprobadas")
    private BigInteger aprobadas;
    @Column(name = "totales")
    private BigInteger totales;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "porcentaje_aprobadas")
    private Double porcentajeAprobadas;

    public VistaValidacionalumnoasignaturas() {
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public BigInteger getReprobadas() {
        return reprobadas;
    }

    public void setReprobadas(BigInteger reprobadas) {
        this.reprobadas = reprobadas;
    }

    public BigInteger getAprobadas() {
        return aprobadas;
    }

    public void setAprobadas(BigInteger aprobadas) {
        this.aprobadas = aprobadas;
    }

    public BigInteger getTotales() {
        return totales;
    }

    public void setTotales(BigInteger totales) {
        this.totales = totales;
    }

    public Double getPorcentajeAprobadas() {
        return porcentajeAprobadas;
    }

    public void setPorcentajeAprobadas(Double porcentajeAprobadas) {
        this.porcentajeAprobadas = porcentajeAprobadas;
    }
    
}
