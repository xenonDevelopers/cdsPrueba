/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import com.utez.secretariaAcademica.entity.Asignatura;
import com.utez.secretariaAcademica.entity.Grupo;
import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "acta")
@NamedQueries({
    @NamedQuery(name = "Acta.findAll", query = "SELECT a FROM Acta a"),
    @NamedQuery(name = "Acta.findByIdActa", query = "SELECT a FROM Acta a WHERE a.idActa = :idActa"),
    @NamedQuery(name = "Acta.findByFolio", query = "SELECT a FROM Acta a WHERE a.folio = :folio"),
    @NamedQuery(name = "Acta.findByFechaRegistro", query = "SELECT a FROM Acta a WHERE a.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "Acta.findByCicloEscolar", query = "SELECT a FROM Acta a WHERE a.cicloEscolar = :cicloEscolar")})
public class Acta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_acta")
    private Long idActa;
    @Column(name = "folio")
    private BigInteger folio;
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "ciclo_escolar")
    private String cicloEscolar;
    @JoinColumn(name = "id_tipoexamen", referencedColumnName = "id_tipoexamen")
    @ManyToOne
    private TipoExamen idTipoexamen;
    @JoinColumn(name = "id_tipoacta", referencedColumnName = "id_tipoacta")
    @ManyToOne
    private TipoActa idTipoacta;
    @JoinColumn(name = "responsable_acta", referencedColumnName = "id_recursohumano")
    @ManyToOne
    private RecursoHumano responsableActa;
    @JoinColumn(name = "id_grupo", referencedColumnName = "id_grupo")
    @ManyToOne
    private Grupo idGrupo;
    @JoinColumn(name = "id_fechaexamen", referencedColumnName = "id_fechaexamen")
    @ManyToOne
    private FechaExamen idFechaexamen;
    @JoinColumn(name = "id_asignatura", referencedColumnName = "id_asignatura")
    @ManyToOne
    private Asignatura idAsignatura;

    public Acta() {
    }

    public Acta(Long idActa) {
        this.idActa = idActa;
    }

    public Long getIdActa() {
        return idActa;
    }

    public void setIdActa(Long idActa) {
        this.idActa = idActa;
    }

    public BigInteger getFolio() {
        return folio;
    }

    public void setFolio(BigInteger folio) {
        this.folio = folio;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getCicloEscolar() {
        return cicloEscolar;
    }

    public void setCicloEscolar(String cicloEscolar) {
        this.cicloEscolar = cicloEscolar;
    }

    public TipoExamen getIdTipoexamen() {
        return idTipoexamen;
    }

    public void setIdTipoexamen(TipoExamen idTipoexamen) {
        this.idTipoexamen = idTipoexamen;
    }

    public TipoActa getIdTipoacta() {
        return idTipoacta;
    }

    public void setIdTipoacta(TipoActa idTipoacta) {
        this.idTipoacta = idTipoacta;
    }

    public RecursoHumano getResponsableActa() {
        return responsableActa;
    }

    public void setResponsableActa(RecursoHumano responsableActa) {
        this.responsableActa = responsableActa;
    }

    public Grupo getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Grupo idGrupo) {
        this.idGrupo = idGrupo;
    }

    public FechaExamen getIdFechaexamen() {
        return idFechaexamen;
    }

    public void setIdFechaexamen(FechaExamen idFechaexamen) {
        this.idFechaexamen = idFechaexamen;
    }

    public Asignatura getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(Asignatura idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idActa != null ? idActa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Acta)) {
            return false;
        }
        Acta other = (Acta) object;
        if ((this.idActa == null && other.idActa != null) || (this.idActa != null && !this.idActa.equals(other.idActa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.Acta[ idActa=" + idActa + " ]";
    }
    
}
