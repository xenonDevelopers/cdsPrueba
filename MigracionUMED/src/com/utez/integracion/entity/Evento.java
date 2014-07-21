/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import com.utez.secretariaAcademica.entity.Plantel;
import java.io.Serializable;
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

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "evento")
@NamedQueries({
    @NamedQuery(name = "Evento.findAll", query = "SELECT e FROM Evento e"),
    @NamedQuery(name = "Evento.findByIdEvento", query = "SELECT e FROM Evento e WHERE e.idEvento = :idEvento"),
    @NamedQuery(name = "Evento.findByClaveSep", query = "SELECT e FROM Evento e WHERE e.claveSep = :claveSep"),
    @NamedQuery(name = "Evento.findByNombreEvento", query = "SELECT e FROM Evento e WHERE e.nombreEvento = :nombreEvento"),
    @NamedQuery(name = "Evento.findByDuracionHoras", query = "SELECT e FROM Evento e WHERE e.duracionHoras = :duracionHoras")})
public class Evento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_evento")
    private Long idEvento;
    @Column(name = "clave_sep")
    private String claveSep;
    @Column(name = "nombre_evento")
    private String nombreEvento;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "duracion_horas")
    private Double duracionHoras;
    @JoinColumn(name = "id_tipoevento", referencedColumnName = "id_tipoevento")
    @ManyToOne
    private TipoEvento idTipoevento;
    @JoinColumn(name = "id_plantel", referencedColumnName = "id_plantel")
    @ManyToOne
    private Plantel idPlantel;
    @OneToMany(mappedBy = "idEvento")
    private List<OfertaEvento> ofertaEventoList;
    @OneToMany(mappedBy = "idEvento")
    private List<Modulo> moduloList;

    public Evento() {
    }

    public Evento(Long idEvento) {
        this.idEvento = idEvento;
    }

    public Long getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Long idEvento) {
        this.idEvento = idEvento;
    }

    public String getClaveSep() {
        return claveSep;
    }

    public void setClaveSep(String claveSep) {
        this.claveSep = claveSep;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public Double getDuracionHoras() {
        return duracionHoras;
    }

    public void setDuracionHoras(Double duracionHoras) {
        this.duracionHoras = duracionHoras;
    }

    public TipoEvento getIdTipoevento() {
        return idTipoevento;
    }

    public void setIdTipoevento(TipoEvento idTipoevento) {
        this.idTipoevento = idTipoevento;
    }

    public Plantel getIdPlantel() {
        return idPlantel;
    }

    public void setIdPlantel(Plantel idPlantel) {
        this.idPlantel = idPlantel;
    }

    public List<OfertaEvento> getOfertaEventoList() {
        return ofertaEventoList;
    }

    public void setOfertaEventoList(List<OfertaEvento> ofertaEventoList) {
        this.ofertaEventoList = ofertaEventoList;
    }

    public List<Modulo> getModuloList() {
        return moduloList;
    }

    public void setModuloList(List<Modulo> moduloList) {
        this.moduloList = moduloList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEvento != null ? idEvento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Evento)) {
            return false;
        }
        Evento other = (Evento) object;
        if ((this.idEvento == null && other.idEvento != null) || (this.idEvento != null && !this.idEvento.equals(other.idEvento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.Evento[ idEvento=" + idEvento + " ]";
    }
    
}
