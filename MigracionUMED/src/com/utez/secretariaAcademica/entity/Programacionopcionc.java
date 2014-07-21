/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.secretariaAcademica.entity;

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
@Table(name = "programacionopcionc")
@NamedQueries({
    @NamedQuery(name = "Programacionopcionc.findAll", query = "SELECT p FROM Programacionopcionc p"),
    @NamedQuery(name = "Programacionopcionc.findByIdasesor", query = "SELECT p FROM Programacionopcionc p WHERE p.idasesor = :idasesor"),
    @NamedQuery(name = "Programacionopcionc.findByFechaordinario", query = "SELECT p FROM Programacionopcionc p WHERE p.fechaordinario = :fechaordinario"),
    @NamedQuery(name = "Programacionopcionc.findByFechaextraordinario", query = "SELECT p FROM Programacionopcionc p WHERE p.fechaextraordinario = :fechaextraordinario"),
    @NamedQuery(name = "Programacionopcionc.findByFechatitulo", query = "SELECT p FROM Programacionopcionc p WHERE p.fechatitulo = :fechatitulo"),
    @NamedQuery(name = "Programacionopcionc.findByIdprogramacionopcionc", query = "SELECT p FROM Programacionopcionc p WHERE p.idprogramacionopcionc = :idprogramacionopcionc")})
public class Programacionopcionc implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "idasesor")
    private Integer idasesor;
    @Column(name = "fechaordinario")
    @Temporal(TemporalType.DATE)
    private Date fechaordinario;
    @Column(name = "fechaextraordinario")
    @Temporal(TemporalType.DATE)
    private Date fechaextraordinario;
    @Column(name = "fechatitulo")
    @Temporal(TemporalType.DATE)
    private Date fechatitulo;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idprogramacionopcionc")
    private Integer idprogramacionopcionc;
    @JoinColumn(name = "idcuadernoprogramacion", referencedColumnName = "idcuadernoprogramacion")
    @ManyToOne
    private Cuadernoprogramacion idcuadernoprogramacion;
    @OneToMany(mappedBy = "idprogramacionopcionc")
    private List<Historicoopcionc> historicoopcioncList;

    public Programacionopcionc() {
    }

    public Programacionopcionc(Integer idprogramacionopcionc) {
        this.idprogramacionopcionc = idprogramacionopcionc;
    }

    public Integer getIdasesor() {
        return idasesor;
    }

    public void setIdasesor(Integer idasesor) {
        this.idasesor = idasesor;
    }

    public Date getFechaordinario() {
        return fechaordinario;
    }

    public void setFechaordinario(Date fechaordinario) {
        this.fechaordinario = fechaordinario;
    }

    public Date getFechaextraordinario() {
        return fechaextraordinario;
    }

    public void setFechaextraordinario(Date fechaextraordinario) {
        this.fechaextraordinario = fechaextraordinario;
    }

    public Date getFechatitulo() {
        return fechatitulo;
    }

    public void setFechatitulo(Date fechatitulo) {
        this.fechatitulo = fechatitulo;
    }

    public Integer getIdprogramacionopcionc() {
        return idprogramacionopcionc;
    }

    public void setIdprogramacionopcionc(Integer idprogramacionopcionc) {
        this.idprogramacionopcionc = idprogramacionopcionc;
    }

    public Cuadernoprogramacion getIdcuadernoprogramacion() {
        return idcuadernoprogramacion;
    }

    public void setIdcuadernoprogramacion(Cuadernoprogramacion idcuadernoprogramacion) {
        this.idcuadernoprogramacion = idcuadernoprogramacion;
    }

    public List<Historicoopcionc> getHistoricoopcioncList() {
        return historicoopcioncList;
    }

    public void setHistoricoopcioncList(List<Historicoopcionc> historicoopcioncList) {
        this.historicoopcioncList = historicoopcioncList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idprogramacionopcionc != null ? idprogramacionopcionc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Programacionopcionc)) {
            return false;
        }
        Programacionopcionc other = (Programacionopcionc) object;
        if ((this.idprogramacionopcionc == null && other.idprogramacionopcionc != null) || (this.idprogramacionopcionc != null && !this.idprogramacionopcionc.equals(other.idprogramacionopcionc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.secretariaAcademica.entity.Programacionopcionc[ idprogramacionopcionc=" + idprogramacionopcionc + " ]";
    }
    
}
