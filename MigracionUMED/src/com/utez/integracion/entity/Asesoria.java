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
@Table(name = "asesoria")
@NamedQueries({
    @NamedQuery(name = "Asesoria.findAll", query = "SELECT a FROM Asesoria a"),
    @NamedQuery(name = "Asesoria.findByIdAsesoria", query = "SELECT a FROM Asesoria a WHERE a.idAsesoria = :idAsesoria"),
    @NamedQuery(name = "Asesoria.findByFechaAsesoria", query = "SELECT a FROM Asesoria a WHERE a.fechaAsesoria = :fechaAsesoria"),
    @NamedQuery(name = "Asesoria.findByNumeroAsesoria", query = "SELECT a FROM Asesoria a WHERE a.numeroAsesoria = :numeroAsesoria")})
public class Asesoria implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_asesoria")
    private Long idAsesoria;
    @Column(name = "fecha_asesoria")
    @Temporal(TemporalType.DATE)
    private Date fechaAsesoria;
    @Column(name = "numero_asesoria")
    private Integer numeroAsesoria;
    @OneToMany(mappedBy = "idAsesoria")
    private List<HistoricoAsesoria> historicoAsesoriaList;
    @JoinColumn(name = "id_calendarioasignatura", referencedColumnName = "id_calendarioasignatura")
    @ManyToOne
    private CalendarioAsignatura idCalendarioasignatura;

    public Asesoria() {
    }

    public Asesoria(Long idAsesoria) {
        this.idAsesoria = idAsesoria;
    }

    public Long getIdAsesoria() {
        return idAsesoria;
    }

    public void setIdAsesoria(Long idAsesoria) {
        this.idAsesoria = idAsesoria;
    }

    public Date getFechaAsesoria() {
        return fechaAsesoria;
    }

    public void setFechaAsesoria(Date fechaAsesoria) {
        this.fechaAsesoria = fechaAsesoria;
    }

    public Integer getNumeroAsesoria() {
        return numeroAsesoria;
    }

    public void setNumeroAsesoria(Integer numeroAsesoria) {
        this.numeroAsesoria = numeroAsesoria;
    }

    public List<HistoricoAsesoria> getHistoricoAsesoriaList() {
        return historicoAsesoriaList;
    }

    public void setHistoricoAsesoriaList(List<HistoricoAsesoria> historicoAsesoriaList) {
        this.historicoAsesoriaList = historicoAsesoriaList;
    }

    public CalendarioAsignatura getIdCalendarioasignatura() {
        return idCalendarioasignatura;
    }

    public void setIdCalendarioasignatura(CalendarioAsignatura idCalendarioasignatura) {
        this.idCalendarioasignatura = idCalendarioasignatura;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAsesoria != null ? idAsesoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asesoria)) {
            return false;
        }
        Asesoria other = (Asesoria) object;
        if ((this.idAsesoria == null && other.idAsesoria != null) || (this.idAsesoria != null && !this.idAsesoria.equals(other.idAsesoria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.Asesoria[ idAsesoria=" + idAsesoria + " ]";
    }
    
}
