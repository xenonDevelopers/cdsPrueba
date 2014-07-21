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
@Table(name = "oferta_evento")
@NamedQueries({
    @NamedQuery(name = "OfertaEvento.findAll", query = "SELECT o FROM OfertaEvento o"),
    @NamedQuery(name = "OfertaEvento.findByIdOfertaevento", query = "SELECT o FROM OfertaEvento o WHERE o.idOfertaevento = :idOfertaevento"),
    @NamedQuery(name = "OfertaEvento.findByFechahoraInicio", query = "SELECT o FROM OfertaEvento o WHERE o.fechahoraInicio = :fechahoraInicio"),
    @NamedQuery(name = "OfertaEvento.findByFechahoraFin", query = "SELECT o FROM OfertaEvento o WHERE o.fechahoraFin = :fechahoraFin"),
    @NamedQuery(name = "OfertaEvento.findByCupoMaximo", query = "SELECT o FROM OfertaEvento o WHERE o.cupoMaximo = :cupoMaximo"),
    @NamedQuery(name = "OfertaEvento.findByCupoMinimo", query = "SELECT o FROM OfertaEvento o WHERE o.cupoMinimo = :cupoMinimo"),
    @NamedQuery(name = "OfertaEvento.findByVigenciaInicio", query = "SELECT o FROM OfertaEvento o WHERE o.vigenciaInicio = :vigenciaInicio"),
    @NamedQuery(name = "OfertaEvento.findByVigenciaFin", query = "SELECT o FROM OfertaEvento o WHERE o.vigenciaFin = :vigenciaFin"),
    @NamedQuery(name = "OfertaEvento.findByEstadoOferta", query = "SELECT o FROM OfertaEvento o WHERE o.estadoOferta = :estadoOferta")})
public class OfertaEvento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_ofertaevento")
    private Long idOfertaevento;
    @Column(name = "fechahora_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechahoraInicio;
    @Column(name = "fechahora_fin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechahoraFin;
    @Column(name = "cupo_maximo")
    private Integer cupoMaximo;
    @Column(name = "cupo_minimo")
    private Integer cupoMinimo;
    @Column(name = "vigencia_inicio")
    @Temporal(TemporalType.DATE)
    private Date vigenciaInicio;
    @Column(name = "vigencia_fin")
    @Temporal(TemporalType.DATE)
    private Date vigenciaFin;
    @Column(name = "estado_oferta")
    private String estadoOferta;
    @JoinColumn(name = "id_instructor", referencedColumnName = "id_recursohumano")
    @ManyToOne
    private RecursoHumano idInstructor;
    @JoinColumn(name = "id_evento", referencedColumnName = "id_evento")
    @ManyToOne
    private Evento idEvento;
    @OneToMany(mappedBy = "idOfertaevento")
    private List<CalificacionModulo> calificacionModuloList;

    public OfertaEvento() {
    }

    public OfertaEvento(Long idOfertaevento) {
        this.idOfertaevento = idOfertaevento;
    }

    public Long getIdOfertaevento() {
        return idOfertaevento;
    }

    public void setIdOfertaevento(Long idOfertaevento) {
        this.idOfertaevento = idOfertaevento;
    }

    public Date getFechahoraInicio() {
        return fechahoraInicio;
    }

    public void setFechahoraInicio(Date fechahoraInicio) {
        this.fechahoraInicio = fechahoraInicio;
    }

    public Date getFechahoraFin() {
        return fechahoraFin;
    }

    public void setFechahoraFin(Date fechahoraFin) {
        this.fechahoraFin = fechahoraFin;
    }

    public Integer getCupoMaximo() {
        return cupoMaximo;
    }

    public void setCupoMaximo(Integer cupoMaximo) {
        this.cupoMaximo = cupoMaximo;
    }

    public Integer getCupoMinimo() {
        return cupoMinimo;
    }

    public void setCupoMinimo(Integer cupoMinimo) {
        this.cupoMinimo = cupoMinimo;
    }

    public Date getVigenciaInicio() {
        return vigenciaInicio;
    }

    public void setVigenciaInicio(Date vigenciaInicio) {
        this.vigenciaInicio = vigenciaInicio;
    }

    public Date getVigenciaFin() {
        return vigenciaFin;
    }

    public void setVigenciaFin(Date vigenciaFin) {
        this.vigenciaFin = vigenciaFin;
    }

    public String getEstadoOferta() {
        return estadoOferta;
    }

    public void setEstadoOferta(String estadoOferta) {
        this.estadoOferta = estadoOferta;
    }

    public RecursoHumano getIdInstructor() {
        return idInstructor;
    }

    public void setIdInstructor(RecursoHumano idInstructor) {
        this.idInstructor = idInstructor;
    }

    public Evento getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Evento idEvento) {
        this.idEvento = idEvento;
    }

    public List<CalificacionModulo> getCalificacionModuloList() {
        return calificacionModuloList;
    }

    public void setCalificacionModuloList(List<CalificacionModulo> calificacionModuloList) {
        this.calificacionModuloList = calificacionModuloList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOfertaevento != null ? idOfertaevento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OfertaEvento)) {
            return false;
        }
        OfertaEvento other = (OfertaEvento) object;
        if ((this.idOfertaevento == null && other.idOfertaevento != null) || (this.idOfertaevento != null && !this.idOfertaevento.equals(other.idOfertaevento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.OfertaEvento[ idOfertaevento=" + idOfertaevento + " ]";
    }
    
}
