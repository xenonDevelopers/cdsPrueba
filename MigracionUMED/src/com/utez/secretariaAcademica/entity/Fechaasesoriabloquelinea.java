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
@Table(name = "fechaasesoriabloquelinea")
@NamedQueries({
    @NamedQuery(name = "Fechaasesoriabloquelinea.findAll", query = "SELECT f FROM Fechaasesoriabloquelinea f"),
    @NamedQuery(name = "Fechaasesoriabloquelinea.findByIdfechaaseseoriabloque", query = "SELECT f FROM Fechaasesoriabloquelinea f WHERE f.idfechaaseseoriabloque = :idfechaaseseoriabloque"),
    @NamedQuery(name = "Fechaasesoriabloquelinea.findByNumeroasesoria", query = "SELECT f FROM Fechaasesoriabloquelinea f WHERE f.numeroasesoria = :numeroasesoria"),
    @NamedQuery(name = "Fechaasesoriabloquelinea.findByFechainicio", query = "SELECT f FROM Fechaasesoriabloquelinea f WHERE f.fechainicio = :fechainicio"),
    @NamedQuery(name = "Fechaasesoriabloquelinea.findByFechafin", query = "SELECT f FROM Fechaasesoriabloquelinea f WHERE f.fechafin = :fechafin"),
    @NamedQuery(name = "Fechaasesoriabloquelinea.findByHora", query = "SELECT f FROM Fechaasesoriabloquelinea f WHERE f.hora = :hora"),
    @NamedQuery(name = "Fechaasesoriabloquelinea.findByNumeroorden", query = "SELECT f FROM Fechaasesoriabloquelinea f WHERE f.numeroorden = :numeroorden"),
    @NamedQuery(name = "Fechaasesoriabloquelinea.findByBloque", query = "SELECT f FROM Fechaasesoriabloquelinea f WHERE f.bloque = :bloque"),
    @NamedQuery(name = "Fechaasesoriabloquelinea.findByPeriodo", query = "SELECT f FROM Fechaasesoriabloquelinea f WHERE f.periodo = :periodo")})
public class Fechaasesoriabloquelinea implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idfechaaseseoriabloque")
    private Integer idfechaaseseoriabloque;
    @Column(name = "numeroasesoria")
    private Integer numeroasesoria;
    @Column(name = "fechainicio")
    @Temporal(TemporalType.DATE)
    private Date fechainicio;
    @Column(name = "fechafin")
    @Temporal(TemporalType.DATE)
    private Date fechafin;
    @Column(name = "hora")
    @Temporal(TemporalType.TIME)
    private Date hora;
    @Column(name = "numeroorden")
    private Integer numeroorden;
    @Column(name = "bloque")
    private Integer bloque;
    @Column(name = "periodo")
    private String periodo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idfechaaseseoriabloque")
    private List<Fechasexamenbloque> fechasexamenbloqueList;

    public Fechaasesoriabloquelinea() {
    }

    public Fechaasesoriabloquelinea(Integer idfechaaseseoriabloque) {
        this.idfechaaseseoriabloque = idfechaaseseoriabloque;
    }

    public Integer getIdfechaaseseoriabloque() {
        return idfechaaseseoriabloque;
    }

    public void setIdfechaaseseoriabloque(Integer idfechaaseseoriabloque) {
        this.idfechaaseseoriabloque = idfechaaseseoriabloque;
    }

    public Integer getNumeroasesoria() {
        return numeroasesoria;
    }

    public void setNumeroasesoria(Integer numeroasesoria) {
        this.numeroasesoria = numeroasesoria;
    }

    public Date getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(Date fechainicio) {
        this.fechainicio = fechainicio;
    }

    public Date getFechafin() {
        return fechafin;
    }

    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public Integer getNumeroorden() {
        return numeroorden;
    }

    public void setNumeroorden(Integer numeroorden) {
        this.numeroorden = numeroorden;
    }

    public Integer getBloque() {
        return bloque;
    }

    public void setBloque(Integer bloque) {
        this.bloque = bloque;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public List<Fechasexamenbloque> getFechasexamenbloqueList() {
        return fechasexamenbloqueList;
    }

    public void setFechasexamenbloqueList(List<Fechasexamenbloque> fechasexamenbloqueList) {
        this.fechasexamenbloqueList = fechasexamenbloqueList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idfechaaseseoriabloque != null ? idfechaaseseoriabloque.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fechaasesoriabloquelinea)) {
            return false;
        }
        Fechaasesoriabloquelinea other = (Fechaasesoriabloquelinea) object;
        if ((this.idfechaaseseoriabloque == null && other.idfechaaseseoriabloque != null) || (this.idfechaaseseoriabloque != null && !this.idfechaaseseoriabloque.equals(other.idfechaaseseoriabloque))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.secretariaAcademica.entity.Fechaasesoriabloquelinea[ idfechaaseseoriabloque=" + idfechaaseseoriabloque + " ]";
    }
    
}
