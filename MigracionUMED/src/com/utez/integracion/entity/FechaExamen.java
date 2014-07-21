/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "fecha_examen")
@NamedQueries({
    @NamedQuery(name = "FechaExamen.findAll", query = "SELECT f FROM FechaExamen f"),
    @NamedQuery(name = "FechaExamen.findByIdFechaexamen", query = "SELECT f FROM FechaExamen f WHERE f.idFechaexamen = :idFechaexamen"),
    @NamedQuery(name = "FechaExamen.findByFechaExamen", query = "SELECT f FROM FechaExamen f WHERE f.fechaExamen = :fechaExamen"),
    @NamedQuery(name = "FechaExamen.findByProgramado", query = "SELECT f FROM FechaExamen f WHERE f.programado = :programado")})
public class FechaExamen implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_fechaexamen")
    private Long idFechaexamen;
    @Column(name = "fecha_examen")
    @Temporal(TemporalType.DATE)
    private Date fechaExamen;
    @Column(name = "programado")
    private Integer programado;
    @ManyToMany(mappedBy = "fechaExamenList")
    private List<ExamenImpreso> examenImpresoList;
    @OneToMany(mappedBy = "idFechaexamen")
    private List<Acta> actaList;
    @OneToMany(mappedBy = "idFechaexamen")
    private List<HistoricoFechaexamen> historicoFechaexamenList;
    @JoinColumn(name = "id_tipoexamen", referencedColumnName = "id_tipoexamen")
    @ManyToOne
    private TipoExamen idTipoexamen;
    @JoinColumn(name = "id_calendarioasignatura", referencedColumnName = "id_calendarioasignatura")
    @ManyToOne
    private CalendarioAsignatura idCalendarioasignatura;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "fechaExamen")
    private EncuestaDocente encuestaDocente;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "fechaExamen1")
    private FechaExamenprogramado fechaExamenprogramado;

    public FechaExamen() {
    }

    public FechaExamen(Long idFechaexamen) {
        this.idFechaexamen = idFechaexamen;
    }

    public Long getIdFechaexamen() {
        return idFechaexamen;
    }

    public void setIdFechaexamen(Long idFechaexamen) {
        this.idFechaexamen = idFechaexamen;
    }

    public Date getFechaExamen() {
        return fechaExamen;
    }

    public void setFechaExamen(Date fechaExamen) {
        this.fechaExamen = fechaExamen;
    }

    public Integer getProgramado() {
        return programado;
    }

    public void setProgramado(Integer programado) {
        this.programado = programado;
    }

    public List<ExamenImpreso> getExamenImpresoList() {
        return examenImpresoList;
    }

    public void setExamenImpresoList(List<ExamenImpreso> examenImpresoList) {
        this.examenImpresoList = examenImpresoList;
    }

    public List<Acta> getActaList() {
        return actaList;
    }

    public void setActaList(List<Acta> actaList) {
        this.actaList = actaList;
    }

    public List<HistoricoFechaexamen> getHistoricoFechaexamenList() {
        return historicoFechaexamenList;
    }

    public void setHistoricoFechaexamenList(List<HistoricoFechaexamen> historicoFechaexamenList) {
        this.historicoFechaexamenList = historicoFechaexamenList;
    }

    public TipoExamen getIdTipoexamen() {
        return idTipoexamen;
    }

    public void setIdTipoexamen(TipoExamen idTipoexamen) {
        this.idTipoexamen = idTipoexamen;
    }

    public CalendarioAsignatura getIdCalendarioasignatura() {
        return idCalendarioasignatura;
    }

    public void setIdCalendarioasignatura(CalendarioAsignatura idCalendarioasignatura) {
        this.idCalendarioasignatura = idCalendarioasignatura;
    }

    public EncuestaDocente getEncuestaDocente() {
        return encuestaDocente;
    }

    public void setEncuestaDocente(EncuestaDocente encuestaDocente) {
        this.encuestaDocente = encuestaDocente;
    }

    public FechaExamenprogramado getFechaExamenprogramado() {
        return fechaExamenprogramado;
    }

    public void setFechaExamenprogramado(FechaExamenprogramado fechaExamenprogramado) {
        this.fechaExamenprogramado = fechaExamenprogramado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFechaexamen != null ? idFechaexamen.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FechaExamen)) {
            return false;
        }
        FechaExamen other = (FechaExamen) object;
        if ((this.idFechaexamen == null && other.idFechaexamen != null) || (this.idFechaexamen != null && !this.idFechaexamen.equals(other.idFechaexamen))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.FechaExamen[ idFechaexamen=" + idFechaexamen + " ]";
    }
    
}
