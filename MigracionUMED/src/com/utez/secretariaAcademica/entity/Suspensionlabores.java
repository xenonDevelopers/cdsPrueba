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
@Table(name = "suspensionlabores")
@NamedQueries({
    @NamedQuery(name = "Suspensionlabores.findAll", query = "SELECT s FROM Suspensionlabores s"),
    @NamedQuery(name = "Suspensionlabores.findByIdsuspensionlabores", query = "SELECT s FROM Suspensionlabores s WHERE s.idsuspensionlabores = :idsuspensionlabores"),
    @NamedQuery(name = "Suspensionlabores.findByFecha", query = "SELECT s FROM Suspensionlabores s WHERE s.fecha = :fecha")})
public class Suspensionlabores implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idsuspensionlabores")
    private Integer idsuspensionlabores;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinColumn(name = "idcalendariorectoria", referencedColumnName = "idcalendariorectoria")
    @ManyToOne
    private Calendariorectoria idcalendariorectoria;

    public Suspensionlabores() {
    }

    public Suspensionlabores(Integer idsuspensionlabores) {
        this.idsuspensionlabores = idsuspensionlabores;
    }

    public Integer getIdsuspensionlabores() {
        return idsuspensionlabores;
    }

    public void setIdsuspensionlabores(Integer idsuspensionlabores) {
        this.idsuspensionlabores = idsuspensionlabores;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Calendariorectoria getIdcalendariorectoria() {
        return idcalendariorectoria;
    }

    public void setIdcalendariorectoria(Calendariorectoria idcalendariorectoria) {
        this.idcalendariorectoria = idcalendariorectoria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsuspensionlabores != null ? idsuspensionlabores.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Suspensionlabores)) {
            return false;
        }
        Suspensionlabores other = (Suspensionlabores) object;
        if ((this.idsuspensionlabores == null && other.idsuspensionlabores != null) || (this.idsuspensionlabores != null && !this.idsuspensionlabores.equals(other.idsuspensionlabores))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.secretariaAcademica.entity.Suspensionlabores[ idsuspensionlabores=" + idsuspensionlabores + " ]";
    }
    
}
