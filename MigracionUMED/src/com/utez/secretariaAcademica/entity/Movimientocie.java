/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.secretariaAcademica.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "movimientocie")
@NamedQueries({
    @NamedQuery(name = "Movimientocie.findAll", query = "SELECT m FROM Movimientocie m"),
    @NamedQuery(name = "Movimientocie.findByIdmovimientocie", query = "SELECT m FROM Movimientocie m WHERE m.idmovimientocie = :idmovimientocie"),
    @NamedQuery(name = "Movimientocie.findByFechadeposito", query = "SELECT m FROM Movimientocie m WHERE m.fechadeposito = :fechadeposito"),
    @NamedQuery(name = "Movimientocie.findByGuiacie", query = "SELECT m FROM Movimientocie m WHERE m.guiacie = :guiacie"),
    @NamedQuery(name = "Movimientocie.findByMontodepositado", query = "SELECT m FROM Movimientocie m WHERE m.montodepositado = :montodepositado"),
    @NamedQuery(name = "Movimientocie.findByNumeroreferencial", query = "SELECT m FROM Movimientocie m WHERE m.numeroreferencial = :numeroreferencial")})
public class Movimientocie implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmovimientocie")
    private Integer idmovimientocie;
    @Column(name = "fechadeposito")
    @Temporal(TemporalType.DATE)
    private Date fechadeposito;
    @Column(name = "guiacie")
    private Integer guiacie;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "montodepositado")
    private Float montodepositado;
    @Column(name = "numeroreferencial")
    private String numeroreferencial;
    @JoinColumn(name = "idtipopagos", referencedColumnName = "idtipopagos")
    @ManyToOne
    private Tipopagos idtipopagos;
    @JoinColumn(name = "matricula", referencedColumnName = "matricula")
    @ManyToOne
    private Alumno matricula;

    public Movimientocie() {
    }

    public Movimientocie(Integer idmovimientocie) {
        this.idmovimientocie = idmovimientocie;
    }

    public Integer getIdmovimientocie() {
        return idmovimientocie;
    }

    public void setIdmovimientocie(Integer idmovimientocie) {
        this.idmovimientocie = idmovimientocie;
    }

    public Date getFechadeposito() {
        return fechadeposito;
    }

    public void setFechadeposito(Date fechadeposito) {
        this.fechadeposito = fechadeposito;
    }

    public Integer getGuiacie() {
        return guiacie;
    }

    public void setGuiacie(Integer guiacie) {
        this.guiacie = guiacie;
    }

    public Float getMontodepositado() {
        return montodepositado;
    }

    public void setMontodepositado(Float montodepositado) {
        this.montodepositado = montodepositado;
    }

    public String getNumeroreferencial() {
        return numeroreferencial;
    }

    public void setNumeroreferencial(String numeroreferencial) {
        this.numeroreferencial = numeroreferencial;
    }

    public Tipopagos getIdtipopagos() {
        return idtipopagos;
    }

    public void setIdtipopagos(Tipopagos idtipopagos) {
        this.idtipopagos = idtipopagos;
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
        hash += (idmovimientocie != null ? idmovimientocie.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Movimientocie)) {
            return false;
        }
        Movimientocie other = (Movimientocie) object;
        if ((this.idmovimientocie == null && other.idmovimientocie != null) || (this.idmovimientocie != null && !this.idmovimientocie.equals(other.idmovimientocie))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.secretariaAcademica.entity.Movimientocie[ idmovimientocie=" + idmovimientocie + " ]";
    }
    
}
