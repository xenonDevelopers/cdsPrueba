/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import com.utez.secretariaAcademica.entity.Asesor;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "examen_individual")
@NamedQueries({
    @NamedQuery(name = "ExamenIndividual.findAll", query = "SELECT e FROM ExamenIndividual e"),
    @NamedQuery(name = "ExamenIndividual.findByIdExamenindividual", query = "SELECT e FROM ExamenIndividual e WHERE e.idExamenindividual = :idExamenindividual"),
    @NamedQuery(name = "ExamenIndividual.findByFechaExamen", query = "SELECT e FROM ExamenIndividual e WHERE e.fechaExamen = :fechaExamen")})
public class ExamenIndividual implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_examenindividual")
    private Long idExamenindividual;
    @Column(name = "fecha_examen")
    @Temporal(TemporalType.DATE)
    private Date fechaExamen;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "examenIndividual")
    private VigenciaCalificacionindividual vigenciaCalificacionindividual;
    @JoinColumn(name = "id_tipoexamen", referencedColumnName = "id_tipoexamen")
    @ManyToOne
    private TipoExamen idTipoexamen;
    @JoinColumn(name = "id_asesor", referencedColumnName = "id_asesor")
    @ManyToOne
    private Asesor idAsesor;
    @JoinColumn(name = "id_alumnoasignatura", referencedColumnName = "id_alumnoasignatura")
    @ManyToOne
    private AlumnoAsignatura idAlumnoasignatura;

    public ExamenIndividual() {
    }

    public ExamenIndividual(Long idExamenindividual) {
        this.idExamenindividual = idExamenindividual;
    }

    public Long getIdExamenindividual() {
        return idExamenindividual;
    }

    public void setIdExamenindividual(Long idExamenindividual) {
        this.idExamenindividual = idExamenindividual;
    }

    public Date getFechaExamen() {
        return fechaExamen;
    }

    public void setFechaExamen(Date fechaExamen) {
        this.fechaExamen = fechaExamen;
    }

    public VigenciaCalificacionindividual getVigenciaCalificacionindividual() {
        return vigenciaCalificacionindividual;
    }

    public void setVigenciaCalificacionindividual(VigenciaCalificacionindividual vigenciaCalificacionindividual) {
        this.vigenciaCalificacionindividual = vigenciaCalificacionindividual;
    }

    public TipoExamen getIdTipoexamen() {
        return idTipoexamen;
    }

    public void setIdTipoexamen(TipoExamen idTipoexamen) {
        this.idTipoexamen = idTipoexamen;
    }

    public Asesor getIdAsesor() {
        return idAsesor;
    }

    public void setIdAsesor(Asesor idAsesor) {
        this.idAsesor = idAsesor;
    }

    public AlumnoAsignatura getIdAlumnoasignatura() {
        return idAlumnoasignatura;
    }

    public void setIdAlumnoasignatura(AlumnoAsignatura idAlumnoasignatura) {
        this.idAlumnoasignatura = idAlumnoasignatura;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idExamenindividual != null ? idExamenindividual.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExamenIndividual)) {
            return false;
        }
        ExamenIndividual other = (ExamenIndividual) object;
        if ((this.idExamenindividual == null && other.idExamenindividual != null) || (this.idExamenindividual != null && !this.idExamenindividual.equals(other.idExamenindividual))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.ExamenIndividual[ idExamenindividual=" + idExamenindividual + " ]";
    }
    
}
