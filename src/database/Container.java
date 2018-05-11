/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author itsystem
 */
@Entity
@Table(catalog = "", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Container.findAll", query = "SELECT c FROM Container c"),
    @NamedQuery(name = "Container.findByIdCtn", query = "SELECT c FROM Container c WHERE c.idCtn = :idCtn"),
    @NamedQuery(name = "Container.findByCtnreference", query = "SELECT c FROM Container c WHERE c.ctnreference = :ctnreference"),
    @NamedQuery(name = "Container.findByNumberofpackages", query = "SELECT c FROM Container c WHERE c.numberofpackages = :numberofpackages"),
    @NamedQuery(name = "Container.findByTypeofcontainer", query = "SELECT c FROM Container c WHERE c.typeofcontainer = :typeofcontainer"),
    @NamedQuery(name = "Container.findByEmptyFull", query = "SELECT c FROM Container c WHERE c.emptyFull = :emptyFull"),
    @NamedQuery(name = "Container.findByMarks1", query = "SELECT c FROM Container c WHERE c.marks1 = :marks1"),
    @NamedQuery(name = "Container.findByMarks2", query = "SELECT c FROM Container c WHERE c.marks2 = :marks2"),
    @NamedQuery(name = "Container.findByMarks3", query = "SELECT c FROM Container c WHERE c.marks3 = :marks3"),
    @NamedQuery(name = "Container.findBySealingParty", query = "SELECT c FROM Container c WHERE c.sealingParty = :sealingParty"),
    @NamedQuery(name = "Container.findByEmptyweight", query = "SELECT c FROM Container c WHERE c.emptyweight = :emptyweight"),
    @NamedQuery(name = "Container.findByGoodsweight", query = "SELECT c FROM Container c WHERE c.goodsweight = :goodsweight")})
public class Container implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id_ctn")
    private Integer idCtn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2000000000)
    @Column(name = "Ctn_reference")
    private String ctnreference;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2000000000)
    @Column(name = "Number_of_packages")
    private String numberofpackages;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2000000000)
    @Column(name = "Type_of_container")
    private String typeofcontainer;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2000000000)
    @Column(name = "Empty_Full")
    private String emptyFull;
    @Size(max = 2000000000)
    private String marks1;
    @Size(max = 2000000000)
    private String marks2;
    @Size(max = 2000000000)
    private String marks3;
    @Size(max = 2000000000)
    @Column(name = "Sealing_Party")
    private String sealingParty;
    @Size(max = 2000000000)
    @Column(name = "Empty_weight")
    private String emptyweight;
    @Size(max = 2000000000)
    @Column(name = "Goods_weight")
    private String goodsweight;
    @JoinColumn(name = "id_bol", referencedColumnName = "id_bol")
    @ManyToOne(optional = false)
    private BillOfLanding idBol;

    public Container() {
    }

    public Container(Integer idCtn) {
        this.idCtn = idCtn;
    }

    public Container(Integer idCtn, String ctnreference, String numberofpackages, String typeofcontainer, String emptyFull) {
        this.idCtn = idCtn;
        this.ctnreference = ctnreference;
        this.numberofpackages = numberofpackages;
        this.typeofcontainer = typeofcontainer;
        this.emptyFull = emptyFull;
    }

    public Integer getIdCtn() {
        return idCtn;
    }

    public void setIdCtn(Integer idCtn) {
        this.idCtn = idCtn;
    }

    public String getCtnreference() {
        return ctnreference;
    }

    public void setCtnreference(String ctnreference) {
        this.ctnreference = ctnreference;
    }

    public String getNumberofpackages() {
        return numberofpackages;
    }

    public void setNumberofpackages(String numberofpackages) {
        this.numberofpackages = numberofpackages;
    }

    public String getTypeofcontainer() {
        return typeofcontainer;
    }

    public void setTypeofcontainer(String typeofcontainer) {
        this.typeofcontainer = typeofcontainer;
    }

    public String getEmptyFull() {
        return emptyFull;
    }

    public void setEmptyFull(String emptyFull) {
        this.emptyFull = emptyFull;
    }

    public String getMarks1() {
        return marks1;
    }

    public void setMarks1(String marks1) {
        this.marks1 = marks1;
    }

    public String getMarks2() {
        return marks2;
    }

    public void setMarks2(String marks2) {
        this.marks2 = marks2;
    }

    public String getMarks3() {
        return marks3;
    }

    public void setMarks3(String marks3) {
        this.marks3 = marks3;
    }

    public String getSealingParty() {
        return sealingParty;
    }

    public void setSealingParty(String sealingParty) {
        this.sealingParty = sealingParty;
    }

    public String getEmptyweight() {
        return emptyweight;
    }

    public void setEmptyweight(String emptyweight) {
        this.emptyweight = emptyweight;
    }

    public String getGoodsweight() {
        return goodsweight;
    }

    public void setGoodsweight(String goodsweight) {
        this.goodsweight = goodsweight;
    }

    public BillOfLanding getIdBol() {
        return idBol;
    }

    public void setIdBol(BillOfLanding idBol) {
        this.idBol = idBol;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCtn != null ? idCtn.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Container)) {
            return false;
        }
        Container other = (Container) object;
        if ((this.idCtn == null && other.idCtn != null) || (this.idCtn != null && !this.idCtn.equals(other.idCtn))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "database.Container[ idCtn=" + idCtn + " ]";
    }
    
}
