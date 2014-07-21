/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import com.utez.secretariaAcademica.entity.Alumno;
import com.utez.secretariaAcademica.entity.Asesor;
import com.utez.secretariaAcademica.entity.Asignatura;
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
@Table(name = "examen_extemporaneo")
@NamedQueries({
    @NamedQuery(name = "ExamenExtemporaneo.findAll", query = "SELECT e FROM ExamenExtemporaneo e"),
    @NamedQuery(name = "ExamenExtemporaneo.findByIdExamenextemporaneo", query = "SELECT e FROM ExamenExtemporaneo e WHERE e.idExamenextemporaneo = :idExamenextemporaneo"),
    @NamedQuery(name = "ExamenExtemporaneo.findByFechaProgramada", query = "SELECT e FROM ExamenExtemporaneo e WHERE e.fechaProgramada = :fechaProgramada"),
    @NamedQuery(name = "ExamenExtemporaneo.findByEstadoExamenextemporaneo", query = "SELECT e FROM ExamenExtemporaneo e WHERE e.estadoExamenextemporaneo = :estadoExamenextemporaneo")})
public class ExamenExtemporaneo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_examenextemporaneo")
    private Long idExamenextemporaneo;
    @Column(name = "fecha_programada")
    @Temporal(TemporalType.DATE)
    private Date fechaProgramada;
    @Column(name = "estado_examenextemporaneo")
    private String estadoExamenextemporaneo;
    @JoinColumn(name = "id_tipoexamen", referencedColumnName = "id_tipoexamen")
    @ManyToOne
    private TipoExamen idTipoexamen;
    @JoinColumn(name = "id_asignatura", referencedColumnName = "id_asignatura")
    @ManyToOne
    private Asignatura idAsignatura;
    @JoinColumn(name = "id_asesorcalificador", referencedColumnName = "id_asesor")
    @ManyToOne
    private Asesor idAsesorcalificador;
    @JoinColumn(name = "matricula", referencedColumnName = "matricula")
    @ManyToOne
    private Alumno matricula;

    public ExamenExtemporaneo() {
    }

    public ExamenExtemporaneo(Long idExamenextemporaneo) {
        this.idExamenextemporaneo = idExamenextemporaneo;
    }

    public Long getIdExamenextemporaneo() {
        return idExamenextemporaneo;
    }

    public void setIdExamenextemporaneo(Long idExamenextemporaneo) {
        this.idExamenextemporaneo = idExamenextemporaneo;
    }

    public Date getFechaProgramada() {
        return fechaProgramada;
    }

    public void setFechaProgramada(Date fechaProgramada) {
        this.fechaProgramada = fechaProgramada;
    }

    public String getEstadoExamenextemporaneo() {
        return estadoExamenextemporaneo;
    }

    public void setEstadoExamenextemporaneo(String estadoExamenextemporaneo) {
        this.estadoExamenextemporaneo = estadoExamenextemporaneo;
    }

    public TipoExamen getIdTipoexamen() {
        return idTipoexamen;
    }

    public void setIdTipoexamen(TipoExamen idTipoexamen) {
        this.idTipoexamen = idTipoexamen;
    }

    public Asignatura getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(Asignatura idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    public Asesor getIdAsesorcalificador() {
        return idAsesorcalificador;
    }

    public void setIdAsesorcalificador(Asesor idAsesorcalificador) {
        this.idAsesorcalificador = idAsesorcalificador;
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
        hash += (idExamenextemporaneo != null ? idExamenextemporaneo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExamenExtemporaneo)) {
            return false;
        }
        ExamenExtemporaneo other = (ExamenExtemporaneo) object;
        if ((this.idExamenextemporaneo == null && other.idExamenextemporaneo != null) || (this.idExamenextemporaneo != null && !this.idExamenextemporaneo.equals(other.idExamenextemporaneo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.ExamenExtemporaneo[ idExamenextemporaneo=" + idExamenextemporaneo + " ]";
    }
    
}
